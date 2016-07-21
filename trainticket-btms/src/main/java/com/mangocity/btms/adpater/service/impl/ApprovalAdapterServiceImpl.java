/**
 * Copyright MangoCity Limited (c) 2011. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of MangoCity Limited.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from MangoCity or an authorized sublicensor.
 */
package com.mangocity.btms.adpater.service.impl;

import com.mangocity.btms.adpater.service.ApprovalAdapterService;
import com.mangocity.btms.adpater.service.ApprovalServiceException;
import com.mangocity.btms.adpater.service.CorporationAdapterService;
import com.mangocity.btms.adpater.vo.ApprovalDto;
import com.mangocity.btms.adpater.vo.ApprovalManVO;
import com.mangocity.btms.approval.ApprovalException;
import com.mangocity.btms.approval.adapter.model.SendApprovalParameter;
import com.mangocity.btms.approval.manage.BindingApprovalFlowManage;
import com.mangocity.btms.approval.model.*;
import com.mangocity.btms.approval.service.ApprovalManService;
import com.mangocity.btms.approval.service.ApprovalMessageService;
import com.mangocity.btms.approval.service.ApprovalService;
import com.mangocity.btms.core.manager.HierarchyArchitectureManager;
import com.mangocity.btms.core.model.PageQueryResult;
import com.mangocity.btms.core.model.hierarchy.HierarchyArchitecture;
import com.mangocity.btms.organization.configuration.model.MessageConfiguration;
import com.mangocity.btms.organization.manager.DepartmentManager;
import com.mangocity.easy.workflow.model.FlowNode;
import com.mangocity.member.adapter.model.Member;
import com.mangocity.member.adapter.model.MemberShipInfo;
import com.mangocity.member.adapter.model.NaturalPerson;
import com.mangocity.member.adapter.model.PersonEmail;
import com.mangocity.member.adapter.service.MemberAdapterService;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mangocube.corenut.commons.bean.BeanDescriber;
import org.mangocube.corenut.commons.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Date: 12-7-27
 * Time: 下午5:55
 *
 * @since 1.0
 */

public class ApprovalAdapterServiceImpl implements ApprovalAdapterService {

    private static Log log = LogFactory.getLog(ApprovalAdapterServiceImpl.class);

    public enum ApprovalAdapterServiceError {
        @ErrorCode(comment = "create approval error!")
        CREATE_APPROVAL_ERROR,
        @ErrorCode(comment = "the parameter ${1} can't be all!the value  ${2} ")
        PARAM_NULL_ERROR,
        @ErrorCode(comment = "send the approval message failed!")
        SEND_MSG_ERROR,
        @ErrorCode(comment = "save customer approval error!")
        SAVE_CUSTOMER_APPROVAL_ERROR
    }
    
    @Autowired
    private CorporationAdapterService corporationAdapterService;

    private ApprovalService approvalService;

    private MemberAdapterService memberAdapterService;

    private ApprovalMessageService approvalMessageService;

    private ApprovalManService approvalManService;
    
    private BindingApprovalFlowManage bindingApprovalFlowManage;
    
    private DepartmentManager departmentManager;
    
    private HierarchyArchitectureManager hierarchyArchitectureManager;


    public Approval createApproval(Approval approval) throws ApprovalServiceException {
        try {
            return approvalService.createApproval(approval);
        } catch (ApprovalException e) {
            log.error("保存审批单失败!", e);
            throw new ApprovalServiceException(ApprovalAdapterServiceError.CREATE_APPROVAL_ERROR, e);
        }
    }

    public Approval retrieveApproval(long approvalId, String approvalCd) {
        return approvalService.retrieveApprovalByIdOrCd(approvalId, approvalCd);
    }

    public List<ApprovalDetail> saveCustomerApproval(long approvalId, SelectedApprovalMan approvalHuman, List<ApprovalDetail> approvalDetailList, String approvalCd) throws ApprovalServiceException {
        try {
        	
        	
            return approvalService.saveCustomerApproval(approvalId, approvalCd, approvalDetailList, approvalHuman);
            
        } catch (ApprovalException e) {
            log.error("保存客户审批操作失败", e);
            throw new ApprovalServiceException(ApprovalAdapterServiceError.SAVE_CUSTOMER_APPROVAL_ERROR, e);
        }
    }

    public boolean isNeedApproval(String memberShipCd, long projectId) {
        return approvalService.isNeedApproval(memberShipCd, projectId);
    }
    
    
    public Map<FlowNode, List<ApprovalMan>> retrieveApprovalMan(String memberShipCd, long projectId){
    	
    	return approvalService.retrieveApprovalMan(memberShipCd, projectId);
    }

    public Map<String, Map<FlowNode, List<ApprovalManVO>>> retrieveApprovalMan(String memberShipCd, long projectId,boolean issend) {
        //1. retrieve approve person
        Map<FlowNode, List<ApprovalMan>> approvalManMap = approvalService.retrieveApprovalMan(memberShipCd, projectId);

        Map<String, Map<FlowNode, List<ApprovalManVO>>> levelApprovalManVO = new LinkedHashMap<String, Map<FlowNode, List<ApprovalManVO>>>();
        //2.iterates each node of approval persons and constructs approval person VO
        for (Map.Entry<FlowNode, List<ApprovalMan>> manMap : approvalManMap.entrySet()) {
            List<ApprovalMan> approvalManList = manMap.getValue();
            FlowNode flowNode = manMap.getKey();
            List<ApprovalManVO> approvalManVoList = new ArrayList<ApprovalManVO>();
            Map<FlowNode, List<ApprovalManVO>> approvalManVOMap = new TreeMap<FlowNode, List<ApprovalManVO>>(new Comparator<FlowNode>() {
                public int compare(FlowNode node1, FlowNode node2) {
                    if (node1 == null || node2 == null) return 0;
                    return String.valueOf(node1.getNodePosition()).compareTo(String.valueOf(node2.getNodePosition()));
                }
            });
            if(flowNode.getNodeType()==0){ //免审
            	approvalManVOMap.put(flowNode, null);
            	levelApprovalManVO.put("EXEMPT_APPROVAL", approvalManVOMap);
            }
            else if (approvalManList == null || approvalManList.isEmpty()) continue;
            else{
	            for (ApprovalMan approvalMan : approvalManList) {
	                Member member = memberAdapterService.retrieveMemberByMbrShipCd(approvalMan.getMemberCd());
                    ApprovalManVO approvalManVO = new ApprovalManVO();
                    approvalManVO.setApprovalMan(approvalMan);
                    if(null!=member){
                        NaturalPerson person = member.getPerson();
                        String mobile = changMobileAndEmail(approvalMan.getMemberCd(), person.getMobileNo());
                        String fax = person.getFax();
                        //审批人姓名设置    不使用T_BTMS_APPROVAL_MAN的member_name冗余字段
                        approvalMan.setMemberName(person.getFamilyName() + person.getName());
                        String email = selectValidEmail(person.getPersonEmailList());
                        approvalManVO.setEmail(email);
                        approvalManVO.setFax(fax);
                        approvalManVO.setMobile(mobile);
                    }else{
                       approvalManVO.setValid(false);
                    }
                    approvalManVoList.add(approvalManVO);
	            }
            
	            approvalManVOMap.put(flowNode, approvalManVoList);
	            if (ReferenceType.PROJECT.name().equals(approvalManList.get(0).getReferenceType().name())) {
	                //取出上一个节点的map
	                Map<FlowNode, List<ApprovalManVO>> preProMap = levelApprovalManVO.get(ReferenceType.PROJECT.name());
	                if (preProMap != null && !preProMap.isEmpty()) approvalManVOMap.putAll(preProMap);
	                levelApprovalManVO.put(ReferenceType.PROJECT.name(), approvalManVOMap);
	            } else {
	                //取出上一个节点的map
	                Map<FlowNode, List<ApprovalManVO>> preComMap = levelApprovalManVO.get(ReferenceType.COMMON.name());
	                if (preComMap != null && !preComMap.isEmpty()) {approvalManVOMap.putAll(preComMap);}
	                levelApprovalManVO.put(ReferenceType.COMMON.name(), approvalManVOMap);
	            }
            }
        }
        return levelApprovalManVO;
    }


    public ApprovalDto retrieveApprovalOrder(long approvalId, String encryptStr, String adminMembercd) throws ApprovalServiceException {
        if (StringUtils.isEmpty(adminMembercd) && StringUtils.isEmpty(encryptStr)) {
            log.error("adminMemberCd and encryptStr can't be empty all!");
            throw new ApprovalServiceException(ApprovalAdapterServiceError.PARAM_NULL_ERROR, "adminMemberCd and encryptStr", "adminMemberCd = [" + adminMembercd + "] encryptStr = [" + encryptStr + "]");
        }
        ApprovalDto approvalDto = new ApprovalDto();
        List<String> orderCdList = new ArrayList<String>();
        Approval approval = retrieveApproval(approvalId, "");
        log.info("retrieveApproval,approvalId:" + approvalId + ",approval :"+ BeanDescriber.descriptBean(approval));
        approvalDto.setApprovalCd(approval.getApproveCD());
        approvalDto.setAssembleStatus(ApprovalDto.Status.valueOf(approval.getStatus().name()));
        Set<SelectedApprovalMan> humanSet = new HashSet<SelectedApprovalMan>();
        List<SelectedApprovalMan> selectedApprovalMans = approval.getSelectedApprovalMan();
        humanSet.addAll(selectedApprovalMans);
        //根据管理员会籍cd获取管理员加密串
        if (StringUtils.isEmpty(encryptStr)) {
            for (SelectedApprovalMan selectedApprovalMan : humanSet) {
                if (selectedApprovalMan.getMemberShipCd().equals(adminMembercd)) {
                    encryptStr = selectedApprovalMan.getEncryptStr();
                }
            }
        }

        //根据加密串查找审批人
        if (!StringUtils.isEmpty(encryptStr)) {
            for (SelectedApprovalMan selectedMan : humanSet) {
                if (!encryptStr.equals(selectedMan.getEncryptStr())) continue;
                ApprovalMan appMan = this.retrieveApprovalMan(selectedMan.getApprovalManId());
                if (appMan != null) {
                    selectedMan.setApprovalMan(appMan);
                }
                approvalDto.setApprovalHuman(selectedMan);
                MemberShipInfo mbrShipInfo = memberAdapterService.retrieveMbrShipByMbrshipCd(selectedMan.getMemberShipCd());
                String approvalLimit = mbrShipInfo.getApproveRole();
                if (ApprovalMan.ApprovalRole.APPROVAL_ADMIN.getValue().equals(approvalLimit)) {
                    long nodeId = this.getAdminAuditLevel(approval.getRunningFlowInstanceId());
                    selectedMan.setNodeId(nodeId);
                }

            }
        } else {
            Member member = memberAdapterService.retrieveMemberByMbrShipCd(adminMembercd);
            ApprovalMan approvalMan = new ApprovalMan();
            approvalMan.setMemberName(member.getPerson().getFamilyName() + member.getPerson().getName());
            approvalMan.setBindingFlowId(0L);
            long nodeId = this.getAdminAuditLevel(approval.getRunningFlowInstanceId());
            approvalMan.setNodeId(nodeId);
            approvalMan.setReferenceType(ReferenceType.MEMBER);
            approvalMan.setCreateBy(member.getPerson().getFamilyName() + member.getPerson().getName());
            approvalMan.setMemberCd(adminMembercd);
            approvalMan = this.createApprovalMan(approvalMan);

            SelectedApprovalMan selectedApprovalMan = new SelectedApprovalMan();
            selectedApprovalMan.setApprovalManId(approvalMan.getApprovalManId());
            selectedApprovalMan.setBindingFlowId(approvalMan.getBindingFlowId());
            selectedApprovalMan.setMemberShipCd(adminMembercd);
            selectedApprovalMan.setNodeId(nodeId);
            selectedApprovalMan.setApprovalId(approval.getApproveId());
            selectedApprovalMan = approvalManService.createSelectedApprovalMan(selectedApprovalMan);
            selectedApprovalMan.setApprovalMan(approvalMan);
            approvalDto.setApprovalHuman(selectedApprovalMan);
        }

        List<ApprovalDetail> approvalDetailList = approval.getApprovalDetailList();
        boolean isFinish = Approval.Status.FINISH.equals(approval.getStatus());
        for (ApprovalDetail approvalDetail : approvalDetailList) {
            //如果订单已经审批完成，将所有审批单详情加入
            if (isFinish) {
                orderCdList.add(approvalDetail.getOrderCd());
            } else {
                //如果审批未完成，将所有非不通过状态的审批单详情加入
                if (approvalDetail.getResult() == null || !ApprovalDetail.Result.REJECT.equals(approvalDetail.getResult())) {
                    orderCdList.add(approvalDetail.getOrderCd());
                }
            }
        }

        List<ApprovalRecord> approvalHistory = approvalService.retrieveApprovalRecordByApprovalId(approval.getApproveId());
        approvalDto.setApprovalRecordList(approvalHistory);
        approvalDto.setOrdercdList(orderCdList);
        FlowNode activeNode = this.retrieveApprovalCurActiveNodeId(approval.getRunningFlowInstanceId());
        approvalDto.setCurActiveNode(activeNode);
        return approvalDto;
    }


	public ApprovalDto retrieveApprovalOrderByKey(long appKey, String mobile) {
		 ApprovalDto approvalDto = new ApprovalDto();
		 Approval approval = retrieveApproval(appKey, "");
		 Member member = memberAdapterService.retrieveMemberByMobilOrEmail(mobile, "");
		 String memberCode = "";
		 for(MemberShipInfo shipInfo: member.getMemberShipInfoList()){
			 if(shipInfo.getCorporationId()!= 0 && shipInfo.getCorporationId() == approval.getCorporationId()){
				 memberCode = shipInfo.getMemberShipCode();
				 break;
			 }
		 }
		 approvalDto.setApprovalCd(approval.getApproveCD());
	     approvalDto.setAssembleStatus(ApprovalDto.Status.valueOf(approval.getStatus().name()));
		 List<String> orderCdList = new ArrayList<String>();
		 List<ApprovalDetail> approvalDetailList = approval.getApprovalDetailList();
         boolean isFinish = Approval.Status.FINISH.equals(approval.getStatus());
         for (ApprovalDetail approvalDetail : approvalDetailList) {
            //如果订单已经审批完成，将所有审批单详情加入
            if (isFinish) {
                orderCdList.add(approvalDetail.getOrderCd());
            } else {
                //如果审批未完成，将所有非不通过状态的审批单详情加入
                if (approvalDetail.getResult() == null || !ApprovalDetail.Result.REJECT.equals(approvalDetail.getResult())) {
                    orderCdList.add(approvalDetail.getOrderCd());
                }
            }
         }
	     approvalDto.setOrdercdList(orderCdList);
		 SelectedApprovalMan approvalMan = approvalService.getApprovalMan(appKey, memberCode);
		 approvalDto.setApprovalHuman(approvalMan);
		 List<ApprovalRecord> approvalHistory = approvalService.retrieveApprovalRecordByApprovalId(approval.getApproveId());
	     approvalDto.setApprovalRecordList(approvalHistory);
	     FlowNode activeNode = this.retrieveApprovalCurActiveNodeId(approval.getRunningFlowInstanceId());
	     approvalDto.setCurActiveNode(activeNode);
		 return approvalDto;
	}

    private long getAdminAuditLevel(long runningFLowInsId) {
        FlowNode activeNode = this.retrieveApprovalCurActiveNodeId(runningFLowInsId);
        if (activeNode == null) return 0;
        return activeNode.getNodeId();
    }

    public ApprovalMan retrieveApprovalMan(long approvalManId) {
    	ApprovalMan approvalMan = approvalManService.retrieveApprovalManById(approvalManId);
    	//获取会员姓名 覆盖审批人信息中的姓名冗余字段
        if(null != approvalMan){
        	Member member = memberAdapterService.retrieveMemberByMbrShipCd(approvalMan.getMemberCd());
        	NaturalPerson person = member.getPerson();
        	if(null != person){
        		 approvalMan.setMemberName(person.getFamilyName() + person.getName());
        	}
        }
        return approvalMan;
    }

    public PageQueryResult<ApprovalRecordVo> queryApprovalRecord(ApprovalRecordQueryDto approvalRecordQueryDto) {
        return approvalService.retrieveApprovalRecord(approvalRecordQueryDto);
    }

    /**
     * 根据 orderCd 查询最新的未结束的审批单Id  （按时间降序，取第一条记录）
     *
     * @param orderCd
     * @return 审批单ID   当无相应审批单时，返回 0
     */
    public long retrieveNotFinishApprovalIdByOrderCd(String orderCd) {
        return approvalService.retrieveNotFinishApprovalIdByOrderCd(orderCd);
    }

    /**
     * 判断一组订单是否可以组合审批 （memberCd 列表，项目ID）
     *
     * @param memberCds
     * @return
     */
    public boolean canCombineApproval(List<String> memberCds, long projectId) {
        return approvalService.canCombineOrders(memberCds.toArray(new String[1]), projectId);
    }


    /**
     * 获得 FlowNode 审批单当前活动节点信息
     *
     * @param flowInstanceId 审批单中流程实例ID
     * @return
     */
    public FlowNode retrieveApprovalCurActiveNodeId(long flowInstanceId) {
        return approvalService.retrieveCurrentNodeId(flowInstanceId);
    }

    /**
     * 获得审批单的 绑定审批流程
     *
     * @param approvalId
     * @return
     */
    public BindingApprovalFlow retrieveApprovalBindingFlow(long approvalId) {
        return approvalService.retrieveBindingApprovalById(approvalId);
    }

    public List<ApprovalRecord> retrieveApprovalRecord(long approvalId) {
        return approvalService.retrieveApprovalRecordByApprovalId(approvalId);
    }

    private String changMobileAndEmail(String membercd, String str) {
        String result = str;
        if (result != null && result.indexOf("$") > 0 && result.split("\\$").length == 2) {
            if (result.startsWith(membercd + "$")) {
                result = result.substring(str.indexOf("$") + 1);
            }
        }
        return result;
    }

    public ApprovalSendResult sendApprovalMsg(long nextNodeId, Approval approval, SendApprovalParameter parameters, String messageTypeCode, MessageConfiguration.ContentType mailSendType) throws ApprovalServiceException {
        try {
            return approvalMessageService.sendApprovalMsg(nextNodeId, approval, parameters, messageTypeCode, mailSendType);
        } catch (Exception e) {
            log.error("发送审批消息失败!", e);
            throw new ApprovalServiceException(ApprovalAdapterServiceError.SEND_MSG_ERROR, e);
        }
    }

    private String selectValidEmail(List<PersonEmail> emailList) {
        if (emailList == null || emailList.isEmpty()) return "";
        String validEmail = null;
        for (PersonEmail email : emailList) {
            if (PersonEmail.EmailStatus.VALID.equals(email.getEmailStatus())) {
                validEmail = email.getEmail();
                break;
            }
        }
        if (validEmail == null) validEmail = emailList.get(0).getEmail();
        return validEmail;
    }

    /**
     * 修改审批人信息
     *
     * @param approvalMan
     * @return 返回修改后的 审批人
     */
    public void updateApprovalMan(ApprovalMan approvalMan) {
        approvalManService.updateApprovalMan(approvalMan);
    }

    /**
     * 值为空的不更新，只更新有值的字段 更新(status, sendType 等) 以及更新 关联审批人
     *
     * @param approval
     * @return
     */
    public void updateApproval(Approval approval) {
        approvalService.updateApproval(approval);
    }

    /**
     * 创建审批人
     *
     * @param approvalMan
     * @return
     */
    public ApprovalMan createApprovalMan(ApprovalMan approvalMan) {
        return approvalManService.createApprovalMan(approvalMan);
    }

    /**
     * 创建审批记录
     *
     * @param approvalRecord
     */
    public void createApprovalRecord(ApprovalRecord approvalRecord) {
        approvalService.createApprovalRecord(approvalRecord);
    }

    public SelectedApprovalMan createSelectedApprovalMan(SelectedApprovalMan selectedApprovalMan) {
        return approvalManService.createSelectedApprovalMan(selectedApprovalMan);
    }

    public void updateApprovalDetail(List<ApprovalDetail> approvalDetailList) {
        approvalService.batchUpdateApprovalDetail(approvalDetailList);
    }

    public Approval retrieveLatestApprovalByOrderCd(String orderCd) {
        return approvalService.retrieveLatestApprovalByOrderCd(orderCd);
    }

    public ApprovalRecord retrieveLastAuditPassRecord(String orderCd) {
        return approvalService.retrieveLastAuditPassRecord(orderCd);
    }

    public List<ApprovalRecordVo> retrieveApprovalRecordByOrderCd(String orderCd) {
        return approvalService.retrieveApprovalRecordByOrderCd(orderCd);
    }

    public ApprovalMessage createApprovalMessageLog(ApprovalMessage approvalMessage) {
       return approvalService.createApprovalMessageLog(approvalMessage);
    }

    public List<ApprovalMessage> retrieveApprovalMessageByOrderId(long orderId) {
        return approvalService.retrieveApprovalMessageByOrderId(orderId);
    }
    
    public void setApprovalService(ApprovalService approvalService) {
        this.approvalService = approvalService;
    }

    public void setApprovalManService(ApprovalManService approvalManService) {
        this.approvalManService = approvalManService;
    }

    public void setMemberAdapterService(MemberAdapterService memberAdapterService) {
        this.memberAdapterService = memberAdapterService;
    }

    public void setApprovalMessageService(ApprovalMessageService approvalMessageService) {
        this.approvalMessageService = approvalMessageService;
    }

	public BindingApprovalFlowManage getBindingApprovalFlowManage() {
		return bindingApprovalFlowManage;
	}

	public void setBindingApprovalFlowManage(BindingApprovalFlowManage bindingApprovalFlowManage) {
		this.bindingApprovalFlowManage = bindingApprovalFlowManage;
	}

	public DepartmentManager getDepartmentManager() {
		return departmentManager;
	}

	public void setDepartmentManager(DepartmentManager departmentManager) {
		this.departmentManager = departmentManager;
	}

	public HierarchyArchitectureManager getHierarchyArchitectureManager() {
		return hierarchyArchitectureManager;
	}

	public void setHierarchyArchitectureManager(HierarchyArchitectureManager hierarchyArchitectureManager) {
		this.hierarchyArchitectureManager = hierarchyArchitectureManager;
	}
	
	
    
    
}
