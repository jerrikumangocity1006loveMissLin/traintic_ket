package com.mangocity.btms.adpater.vo;

import com.mangocity.btms.approval.model.ApprovalRecord;
import com.mangocity.btms.approval.model.SelectedApprovalMan;
import com.mangocity.easy.workflow.model.FlowNode;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: fanliding
 * Date: 12-8-3
 * Time: 上午10:14
 * To change this template use File | Settings | File Templates.
 */
public class ApprovalDto implements Serializable {

    public enum Status{
        FINISH,
        RUNNING
    }

    /*
     * 审批人ID
     */
    private SelectedApprovalMan approvalHuman;

    /*
     * 订单号 列表
     */
    private List<String> ordercdList;

    private Status assembleStatus;

    //审批单流程实例的当前活动节点
    private FlowNode curActiveNode;


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

    public List<String> getOrdercdList() {
        return ordercdList;
    }

    public void setOrdercdList(List<String> ordercdList) {
        this.ordercdList = ordercdList;
    }

    public Status getAssembleStatus() {
        return assembleStatus;
    }

    public void setAssembleStatus(Status assembleStatus) {
        this.assembleStatus = assembleStatus;
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

    public FlowNode getCurActiveNode() {
        return curActiveNode;
    }

    public void setCurActiveNode(FlowNode curActiveNode) {
        this.curActiveNode = curActiveNode;
    }
}
