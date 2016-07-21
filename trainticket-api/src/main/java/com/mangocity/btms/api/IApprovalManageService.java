package com.mangocity.btms.api;

import com.mangocity.btms.adpater.vo.ApprovalDto;
import com.mangocity.btms.adpater.vo.ApprovalManVO;
import com.mangocity.btms.approval.model.Approval;
import com.mangocity.btms.approval.model.ApprovalDetail;
import com.mangocity.btms.approval.model.ApprovalMan;
import com.mangocity.btms.approval.model.ApprovalRecord;
import com.mangocity.btms.approval.model.ApprovalRecordQueryDto;
import com.mangocity.btms.approval.model.ApprovalRecordVo;
import com.mangocity.btms.approval.model.BindingApprovalFlow;
import com.mangocity.btms.approval.model.SelectedApprovalMan;
import com.mangocity.btms.core.model.PageQueryResult;
import com.mangocity.btms.vo.ApprovalVO;
import com.mangocity.easy.workflow.model.FlowNode;

import java.util.List;
import java.util.Map;

/**
 * 审批管理信息
 * @author hongxiaodong
 *
 */
public interface IApprovalManageService {

    /**
     * 创建审批单
     *
     * @param approval
     * @return
     * @throws ApprovalServiceException
     */
    public Approval createApproval(Approval approval) throws Exception;

    /**
     * 根据id或审批单号 获得审批单信息
     * @param approvalId
     * @param approvalCd
     * @return
     */
	public Approval retrieveApproval(long approvalId, String approvalCd) ;

    /**
     * 保存审批人的审批操作信息
     * @param approvalId
     * @param approvalHuman
     * @param approvalDetailList
     * @param approvalCd
     * @return
     * @throws ApprovalServiceException
     */
	public List<ApprovalDetail> saveCustomerApproval(long approvalId, SelectedApprovalMan approvalHuman, List<ApprovalDetail> approvalDetailList,
                                       String approvalCd);

    /**
     * 判断是否需要审批
     * @param memberShipCd
     * @param projectId
     * @return
     * @throws ApprovalServiceException
     */
    public boolean isNeedApproval(String memberShipCd, long projectId) ;

    /**
     * 获得 订单集合 的 关联审批人
     *
     *
     * @param memberShipCd
     * @param projectId
     * @return Map<K,V>  K：节点 V:审批人列表
     */
    public Map<FlowNode, List<ApprovalMan>> retrieveApprovalMan(String memberShipCd, long projectId) ;


    /**
     * 获得审批人基本信息
     * @param approvalManId
     * @return
     */
    public ApprovalMan retrieveApprovalMan(long approvalManId);

    /**
     * 查询审批记录
     * @param approvalRecordQueryDto
     * @return
     */
    public PageQueryResult<ApprovalRecordVo> queryApprovalRecord(ApprovalRecordQueryDto approvalRecordQueryDto);

    /**
     * 根据 orderCd 查询最新的未结束的审批单Id
     * @param orderCd
     * @return
     */
    public long retrieveNotFinishApprovalIdByOrderCd(String orderCd);

    /**
     * 判断一组订单是否可以组合审批 （）
     * @param memberCds
     * @return
     */
    public boolean canCombineApproval(List<String> memberCds, long projectId);

    /**
     * 获得 FlowNode 审批单当前活动节点信息
     *
     * @param flowInstanceId 审批单中流程实例ID
     * @return
     */
    public FlowNode retrieveApprovalCurActiveNodeId(long flowInstanceId);

    /**
     * 获得审批单的 绑定审批流程
     * @param approvalId
     * @return
     */
    public BindingApprovalFlow retrieveApprovalBindingFlow(long approvalId);

    /**
     * 获取审批记录
     * @param approvalId
     * @return
     */
    public List<ApprovalRecord> retrieveApprovalRecord(long approvalId);
    

    /**
     * 创建审批人
     * @param approvalMan
     * @return
     */
    public ApprovalMan createApprovalMan(ApprovalMan approvalMan);

    /**
     * 创建审批记录
     * @param approvalRecord
     */
    public void createApprovalRecord(ApprovalRecord approvalRecord);
    

    /**
     * 获得最后一次审批通过时的审批记录
     * @param orderCd
     * @return
     */
    public ApprovalRecord retrieveLastAuditPassRecord(String orderCd);
    
    
    /**
     * 获取审批信息
     * @param appKey
     * @param mobile
     * @return
     */
    public ApprovalDto retrieveApprovalOrderByKey(long appKey, String mobile);
    
    /**
     * 获取审批的订单信息
     * @param appKey
     * @param mobile
     * @return
     */
    public ApprovalVO getApprovalOrder(String appKey, String mobile);
    
    /**
     * 审批信息展示
     * @param memberShipCd
     * @param projectId
     * @param issend
     * @return
     */
    public Map<String, Map<FlowNode, List<ApprovalManVO>>> retrieveApprovalMan(String memberShipCd, long projectId,boolean issend);
}
