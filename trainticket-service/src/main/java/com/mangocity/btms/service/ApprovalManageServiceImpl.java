package com.mangocity.btms.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mangocity.btms.adpater.service.ApprovalAdapterService;
import com.mangocity.btms.adpater.service.ApprovalServiceException;
import com.mangocity.btms.adpater.vo.ApprovalDto;
import com.mangocity.btms.adpater.vo.ApprovalManVO;
import com.mangocity.btms.api.IApprovalManageService;
import com.mangocity.btms.api.IMemberManageService;
import com.mangocity.btms.approval.model.Approval;
import com.mangocity.btms.approval.model.ApprovalDetail;
import com.mangocity.btms.approval.model.ApprovalMan;
import com.mangocity.btms.approval.model.ApprovalRecord;
import com.mangocity.btms.approval.model.ApprovalRecordQueryDto;
import com.mangocity.btms.approval.model.ApprovalRecordVo;
import com.mangocity.btms.approval.model.BindingApprovalFlow;
import com.mangocity.btms.approval.model.ReferenceType;
import com.mangocity.btms.approval.model.SelectedApprovalMan;
import com.mangocity.btms.approval.service.ApprovalService;
import com.mangocity.btms.core.model.PageQueryResult;
import com.mangocity.btms.vo.ApprovalVO;
import com.mangocity.easy.workflow.model.FlowNode;
import com.mangocity.member.adapter.model.Member;
import com.mangocity.member.adapter.model.MemberShipInfo;
import com.mangocity.member.adapter.model.NaturalPerson;
import com.mangocity.member.adapter.model.PersonEmail;
import com.mangocity.member.adapter.service.MemberAdapterService;

/**
 * 审批管理信息
 * 
 * @author hongxiaodong
 *
 */
public class ApprovalManageServiceImpl implements IApprovalManageService {

	Logger logger = Logger.getLogger(ApprovalManageServiceImpl.class);

	@Autowired
	private ApprovalAdapterService approvalAdapterService;// EJB

	@Autowired
	private ApprovalService duapprovalService;// dubbo

	@Autowired
	private MemberAdapterService dumemberAdapterService;

	@Override
	public Approval createApproval(Approval approval) throws Exception {
		if (approval == null) {
			logger.error("审核信息为空");
			throw new Exception("审核信息不能为空");
		}

		return duapprovalService.createApproval(approval);
	}

	@Override
	public Approval retrieveApproval(long approvalId, String approvalCd) {

		return duapprovalService.retrieveApprovalByIdOrCd(approvalId, approvalCd);
	}

	@Override
	public List<ApprovalDetail> saveCustomerApproval(long approvalId, SelectedApprovalMan approvalHuman,
			List<ApprovalDetail> approvalDetailList, String approvalCd) {
		try {

			return approvalAdapterService.saveCustomerApproval(approvalId, approvalHuman, approvalDetailList,
					approvalCd);

		} catch (ApprovalServiceException e) {

			logger.error("保存审批人的审批操作信息出错", e);

			return null;
		}
	}

	@Override
	public boolean isNeedApproval(String memberShipCd, long projectId) {

		return duapprovalService.isNeedApproval(memberShipCd, projectId);
	}

	@Override
	public Map<FlowNode, List<ApprovalMan>> retrieveApprovalMan(String memberShipCd, long projectId) {

		return duapprovalService.retrieveApprovalMan(memberShipCd, projectId);
	}

	@Override
	public ApprovalMan retrieveApprovalMan(long approvalManId) {

		return approvalAdapterService.retrieveApprovalMan(approvalManId);
	}

	@Override
	public PageQueryResult<ApprovalRecordVo> queryApprovalRecord(ApprovalRecordQueryDto approvalRecordQueryDto) {

		return approvalAdapterService.queryApprovalRecord(approvalRecordQueryDto);
	}

	@Override
	public long retrieveNotFinishApprovalIdByOrderCd(String orderCd) {

		return duapprovalService.retrieveNotFinishApprovalIdByOrderCd(orderCd);
	}

	@Override
	public boolean canCombineApproval(List<String> memberCds, long projectId) {

		return approvalAdapterService.canCombineApproval(memberCds, projectId);
	}

	@Override
	public FlowNode retrieveApprovalCurActiveNodeId(long flowInstanceId) {

		return duapprovalService.retrieveCurrentNodeId(flowInstanceId);
	}

	@Override
	public BindingApprovalFlow retrieveApprovalBindingFlow(long approvalId) {

		return approvalAdapterService.retrieveApprovalBindingFlow(approvalId);
	}

	@Override
	public List<ApprovalRecord> retrieveApprovalRecord(long approvalId) {

		return approvalAdapterService.retrieveApprovalRecord(approvalId);
	}

	@Override
	public ApprovalMan createApprovalMan(ApprovalMan approvalMan) {

		return approvalAdapterService.createApprovalMan(approvalMan);
	}

	@Override
	public void createApprovalRecord(ApprovalRecord approvalRecord) {

		approvalAdapterService.createApprovalRecord(approvalRecord);
	}

	@Override
	public ApprovalRecord retrieveLastAuditPassRecord(String orderCd) {

		return approvalAdapterService.retrieveLastAuditPassRecord(orderCd);
	}

	@Override
	public ApprovalDto retrieveApprovalOrderByKey(long appKey, String mobile) {

		return approvalAdapterService.retrieveApprovalOrderByKey(appKey, mobile);
	}

	/**
	 * 取基本数据供短信审批
	 */
	@Override
	public ApprovalVO getApprovalOrder(String appKey, String mobile) {
		logger.info(">>>>>>>>>>>>getApprovalOrder:"+appKey+">>>>>>>>>"+mobile);
		ApprovalVO approvalVO = new ApprovalVO();
		ApprovalDto approvalDto = retrieveApprovalOrderByKeyAndMobile(Long.parseLong(appKey), mobile);
		logger.info(">>>>>>>>>>>>approvalDto:"+approvalDto.getApprovalCd()+">>>getOrdercdList>>"+approvalDto.getOrdercdList());
		approvalVO.setApprovalHuman(approvalDto.getApprovalHuman());
		List<String> ordercdList = approvalDto.getOrdercdList();
		approvalVO.setApprovalCd(approvalDto.getApprovalCd());

		Set<String> cds = new HashSet<String>();
		for (String ordercd : ordercdList) {
			cds.add(ordercd);
		}
		approvalVO.setOrderSet(cds);
		return approvalVO;
	}

	@Override
	public Map<String, Map<FlowNode, List<ApprovalManVO>>> retrieveApprovalMan(String memberShipCd, long projectId,
			boolean issend) {
		// 1. retrieve approve person
		Map<FlowNode, List<ApprovalMan>> approvalManMap = duapprovalService.retrieveApprovalMan(memberShipCd,
				projectId);

		Map<String, Map<FlowNode, List<ApprovalManVO>>> levelApprovalManVO = new LinkedHashMap<String, Map<FlowNode, List<ApprovalManVO>>>();
		// 2.iterates each node of approval persons and constructs approval
		// person VO
		for (Map.Entry<FlowNode, List<ApprovalMan>> manMap : approvalManMap.entrySet()) {
			List<ApprovalMan> approvalManList = manMap.getValue();
			FlowNode flowNode = manMap.getKey();
			List<ApprovalManVO> approvalManVoList = new ArrayList<ApprovalManVO>();
			Map<FlowNode, List<ApprovalManVO>> approvalManVOMap = new HashMap<FlowNode, List<ApprovalManVO>>();
			if (flowNode.getNodeType() == 0) { // 免审
				approvalManVOMap.put(flowNode, null);
				levelApprovalManVO.put("EXEMPT_APPROVAL", approvalManVOMap);
			} else if (approvalManList == null || approvalManList.isEmpty())
				continue;
			else {
				for (ApprovalMan approvalMan : approvalManList) {
					Member member = dumemberAdapterService.retrieveMemberByMbrShipCd(approvalMan.getMemberCd());
					ApprovalManVO approvalManVO = new ApprovalManVO();
					approvalManVO.setApprovalMan(approvalMan);
					if (null != member) {
						NaturalPerson person = member.getPerson();
						String mobile = changMobileAndEmail(approvalMan.getMemberCd(), person.getMobileNo());
						String fax = person.getFax();
						// 审批人姓名设置 不使用T_BTMS_APPROVAL_MAN的member_name冗余字段
						approvalMan.setMemberName(person.getFamilyName() + person.getName());
						String email = selectValidEmail(person.getPersonEmailList());
						approvalManVO.setEmail(email);
						approvalManVO.setFax(fax);
						approvalManVO.setMobile(mobile);
					} else {
						approvalManVO.setValid(false);
					}
					approvalManVoList.add(approvalManVO);
				}

				approvalManVOMap.put(flowNode, approvalManVoList);
				if (ReferenceType.PROJECT.name().equals(approvalManList.get(0).getReferenceType().name())) {
					// 取出上一个节点的map
					Map<FlowNode, List<ApprovalManVO>> preProMap = levelApprovalManVO.get(ReferenceType.PROJECT.name());
					if (preProMap != null && !preProMap.isEmpty())
						approvalManVOMap.putAll(preProMap);
					levelApprovalManVO.put(ReferenceType.PROJECT.name(), approvalManVOMap);
				} else {
					// 取出上一个节点的map
					Map<FlowNode, List<ApprovalManVO>> preComMap = levelApprovalManVO.get(ReferenceType.COMMON.name());
					if (preComMap != null && !preComMap.isEmpty()) {
						approvalManVOMap.putAll(preComMap);
					}
					levelApprovalManVO.put(ReferenceType.COMMON.name(), approvalManVOMap);
				}
			}
		}
		return levelApprovalManVO;
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

	private String selectValidEmail(List<PersonEmail> emailList) {
		if (emailList == null || emailList.isEmpty())
			return "";
		String validEmail = null;
		for (PersonEmail email : emailList) {
			if (PersonEmail.EmailStatus.VALID.equals(email.getEmailStatus())) {
				validEmail = email.getEmail();
				break;
			}
		}
		if (validEmail == null)
			validEmail = emailList.get(0).getEmail();
		return validEmail;
	}
	
	public ApprovalDto retrieveApprovalOrderByKeyAndMobile(long appKey, String mobile) {
		 ApprovalDto approvalDto = new ApprovalDto();
		 Approval approval = duapprovalService.retrieveApprovalByIdOrCd(appKey, "");
		 Member member = dumemberAdapterService.retrieveMemberByMobilOrEmail(mobile, "");
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
		 SelectedApprovalMan approvalMan = duapprovalService.getApprovalMan(appKey, memberCode);
		 approvalDto.setApprovalHuman(approvalMan);
		 List<ApprovalRecord> approvalHistory = duapprovalService.retrieveApprovalRecordByApprovalId(approval.getApproveId());
	     approvalDto.setApprovalRecordList(approvalHistory);
	     FlowNode activeNode = duapprovalService.retrieveCurrentNodeId(approval.getRunningFlowInstanceId());
	     approvalDto.setCurActiveNode(activeNode);
		 return approvalDto;
	}

}
