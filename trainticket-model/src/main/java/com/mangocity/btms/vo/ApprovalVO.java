package com.mangocity.btms.vo;

import com.mangocity.btms.approval.model.ApprovalRecord;
import com.mangocity.btms.approval.model.SelectedApprovalMan;

import java.util.List;
import java.util.Set;



public class ApprovalVO {

    /*
     * 审批人ID
     */
    private SelectedApprovalMan approvalHuman;

    /*
     * 订单集合
     */
    private Set orderSet;

    //NOAPPROVED：未审批，APPROVING：审批中， FINISHED：审批结束
    private String assembleStatus;

    //审批单流程实例的当前活动节点ID
    private Long curActiveNodeId;

    // 审批日志
    private List<ApprovalRecord> approvalRecordList;

    // 审批号
    private String approvalCd;

    public SelectedApprovalMan getApprovalHuman() {
        return approvalHuman;
    }

    public void setApprovalHuman(SelectedApprovalMan approvalHuman) {
        this.approvalHuman = approvalHuman;
    }

    public Set getOrderSet() {
        return orderSet;
    }

    public void setOrderSet(Set orderSet) {
        this.orderSet = orderSet;
    }

    public String getAssembleStatus() {
        return assembleStatus;
    }

    public void setAssembleStatus(String status) {
        this.assembleStatus = status;
    }

    public List<ApprovalRecord> getApprovalRecordList() {
        return approvalRecordList;
    }

    public void setApprovalRecordList(List<ApprovalRecord> approvalRecordList) {
        this.approvalRecordList = approvalRecordList;
    }

    public String getApprovalCd() {
        return approvalCd;
    }

    public void setApprovalCd(String approvalCd) {
        this.approvalCd = approvalCd;
    }

    public Long getCurActiveNodeId() {
        return curActiveNodeId;
    }

    public void setCurActiveNodeId(Long curActiveNodeId) {
        this.curActiveNodeId = curActiveNodeId;
    }
}
