package com.mangocity.btms.api;

import java.util.List;

import com.mangocity.btms.approval.ApprovalException;
import com.mangocity.btms.approval.model.ApprovalTrainTicketOrder;
import com.mangocity.btms.approval.model.SelectedApprovalMan;
import com.mangocity.btms.approval.model.ApprovalDetail;
import com.mangocity.btms.vo.ApprovalVO;
import com.mangocity.btms.vo.TravelInfoVO;
import com.mangocity.enums.ApprovalType;
import com.mangocity.response.ResponseMessage;

/**
 * 消息审批管理类
 * @author hongxiaodong
 *
 */
public interface IApprovalMessageManageService {

	/**
	 * 
	 * 发送消息审批
	 * @param ticket
	 * @param memberCd
	 * @param travelInfoVO
	 * @return
	 */
	public ResponseMessage sendApprovalMessage(Long orderId,TravelInfoVO travelInfoVO) throws Exception;
	

    /**
     * 人工审批,并向联系人发送短信
     * 
     * @param orderId orderId 订单ID
     * @param approvalType "PASS" 通过, "REJECT"拒绝
     * @param memberShipCd 会员ID
     * @param projectId 项目ID
     * @return
     */
    public ResponseMessage humanApprovalOrder(Long orderId,ApprovalType approvalType);
    
    
    /**
     * 保存客户审批操作
     *
     * @param approvalId
     * @param approvalCd
     * @param approvalDetailList
     * @param selectedApprovalMan
     * @return 需要更新订单状态或出票的审批单详情列表 保存后需要更新订单状态或自动出票
     * @throws com.mangocity.btms.approval.ApprovalException
     */
    public void saveCustomerApproval(long approvalId, String approvalCd, List<ApprovalDetail> approvalDetailList, SelectedApprovalMan selectedApprovalMan) throws ApprovalException;
    
    

}
