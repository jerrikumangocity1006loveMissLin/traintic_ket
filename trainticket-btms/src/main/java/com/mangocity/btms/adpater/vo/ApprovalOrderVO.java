package com.mangocity.btms.adpater.vo;

import com.mangocity.btms.approval.adapter.vo.OrderVO;
import com.mangocity.btms.approval.model.ApprovalRecordVo;

public class ApprovalOrderVO implements Cloneable{

    //审批单记录
    private ApprovalRecordVo approvalRecordVO;

    //对应的 订单 简情
    private OrderVO orderVO;

    public ApprovalRecordVo getApprovalRecordVO() {
        return approvalRecordVO;
    }

    public void setApprovalRecordVO(ApprovalRecordVo approvalRecordVO) {
        this.approvalRecordVO = approvalRecordVO;
    }

    public OrderVO getOrderVO() {
        return orderVO;
    }

    public void setOrderVO(OrderVO orderVO) {
        this.orderVO = orderVO;
    }
}
