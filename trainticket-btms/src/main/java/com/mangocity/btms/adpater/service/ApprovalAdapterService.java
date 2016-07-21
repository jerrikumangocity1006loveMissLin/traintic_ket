/**
 * Copyright MangoCity Limited (c) 2011. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of MangoCity Limited.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from MangoCity or an authorized sublicensor.
 */
package com.mangocity.btms.adpater.service;

import com.mangocity.btms.adpater.vo.ApprovalDto;
import com.mangocity.btms.adpater.vo.ApprovalManVO;
import com.mangocity.btms.approval.adapter.model.SendApprovalParameter;
import com.mangocity.btms.approval.model.*;
import com.mangocity.btms.core.model.PageQueryResult;
import com.mangocity.btms.organization.configuration.model.MessageConfiguration;
import com.mangocity.easy.workflow.model.FlowNode;

import java.util.List;
import java.util.Map;

/**
 * Date: 12-7-27 Time: 下午5:55
 *
 * @since 1.0
 */

public interface ApprovalAdapterService {

	/**
	 * 创建审批单
	 *
	 * @param approval
	 * @return
	 * @throws ApprovalServiceException
	 */
	public Approval createApproval(Approval approval) throws ApprovalServiceException;

	/**
	 * 根据id或审批单号 获得审批单信息
	 * 
	 * @param approvalId
	 * @param approvalCd
	 * @return
	 */
	public Approval retrieveApproval(long approvalId, String approvalCd);

	/**
	 * 保存审批人的审批操作信息
	 * 
	 * @param approvalId
	 * @param approvalHuman
	 * @param approvalDetailList
	 * @param approvalCd
	 * @return
	 * @throws ApprovalServiceException
	 */
	public List<ApprovalDetail> saveCustomerApproval(long approvalId, SelectedApprovalMan approvalHuman,
			List<ApprovalDetail> approvalDetailList, String approvalCd) throws ApprovalServiceException;

	/**
	 * 判断是否需要审批
	 * 
	 * @param memberShipCd
	 * @param projectId
	 * @return
	 * @throws ApprovalServiceException
	 */
	public boolean isNeedApproval(String memberShipCd, long projectId);
	
	
	/**
	 * 订单获取关联审批人
	 * @param memberShipCd
	 * @param projectId
	 * @return
	 */
	public Map<FlowNode, List<ApprovalMan>> retrieveApprovalMan(String memberShipCd, long projectId);

	/**
	 * 获得 订单集合 的 关联审批人
	 *
	 *
	 * @param memberShipCd
	 * @param projectId
	 * @return Map<K,V> K：节点 V:审批人列表
	 */
	public Map<String, Map<FlowNode, List<ApprovalManVO>>> retrieveApprovalMan(String memberShipCd, long projectId,
			boolean issend);

	/**
	 * 通过加密串后者管理员CD获得审批的订单，供EJB调用
	 * 
	 * @param approvalId
	 *            审批单ID
	 * @param encryptStr
	 *            加密串
	 * @param adminMembercd
	 *            管理员CD
	 * @return
	 * @throws ApprovalServiceException
	 */
	public ApprovalDto retrieveApprovalOrder(long approvalId, String encryptStr, String adminMembercd)
			throws ApprovalServiceException;

	/**
	 * 获得审批人基本信息
	 * 
	 * @param approvalManId
	 * @return
	 */
	public ApprovalMan retrieveApprovalMan(long approvalManId);

	/**
	 * 查询审批记录
	 * 
	 * @param approvalRecordQueryDto
	 * @return
	 */
	public PageQueryResult<ApprovalRecordVo> queryApprovalRecord(ApprovalRecordQueryDto approvalRecordQueryDto);

	/**
	 * 根据 orderCd 查询最新的未结束的审批单Id
	 * 
	 * @param orderCd
	 * @return
	 */
	public long retrieveNotFinishApprovalIdByOrderCd(String orderCd);

	/**
	 * 判断一组订单是否可以组合审批 （）
	 * 
	 * @param memberCds
	 * @return
	 */
	public boolean canCombineApproval(List<String> memberCds, long projectId);

	/**
	 * 获得 FlowNode 审批单当前活动节点信息
	 *
	 * @param flowInstanceId
	 *            审批单中流程实例ID
	 * @return
	 */
	public FlowNode retrieveApprovalCurActiveNodeId(long flowInstanceId);

	/**
	 * 获得审批单的 绑定审批流程
	 * 
	 * @param approvalId
	 * @return
	 */
	public BindingApprovalFlow retrieveApprovalBindingFlow(long approvalId);

	/**
	 * 获取审批记录
	 * 
	 * @param approvalId
	 * @return
	 */
	public List<ApprovalRecord> retrieveApprovalRecord(long approvalId);

	public ApprovalSendResult sendApprovalMsg(long nextNodeId, Approval approval, SendApprovalParameter parameters,
			String messageTypeCode, MessageConfiguration.ContentType mailSendType) throws ApprovalServiceException;

	/**
	 * 修改审批人信息
	 *
	 * @param approvalMan
	 * @return 返回修改后的 审批人
	 */
	public void updateApprovalMan(ApprovalMan approvalMan);

	/**
	 * 值为空的不更新，只更新有值的字段 更新(status, sendType 等)
	 * 
	 * @param approval
	 * @return
	 */
	public void updateApproval(Approval approval);

	/**
	 * 创建审批人
	 * 
	 * @param approvalMan
	 * @return
	 */
	public ApprovalMan createApprovalMan(ApprovalMan approvalMan);

	/**
	 * 创建审批记录
	 * 
	 * @param approvalRecord
	 */
	public void createApprovalRecord(ApprovalRecord approvalRecord);

	public SelectedApprovalMan createSelectedApprovalMan(SelectedApprovalMan selectedApprovalMan);

	public void updateApprovalDetail(List<ApprovalDetail> approvalDetailList);

	public Approval retrieveLatestApprovalByOrderCd(String orderCd);

	/**
	 * 获得最后一次审批通过时的审批记录
	 * 
	 * @param orderCd
	 * @return
	 */
	public ApprovalRecord retrieveLastAuditPassRecord(String orderCd);

	public List<ApprovalRecordVo> retrieveApprovalRecordByOrderCd(String orderCd);

	public ApprovalMessage createApprovalMessageLog(ApprovalMessage approvalMessage);

	public List<ApprovalMessage> retrieveApprovalMessageByOrderId(long orderId);

	/**
	 * 获取审批信息
	 * 
	 * @param appKey
	 * @param mobile
	 * @return
	 */
	public ApprovalDto retrieveApprovalOrderByKey(long appKey, String mobile);
}
