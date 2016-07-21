package com.mangocity.btms.vo;

import com.mangocity.btms.approval.model.SelectedApprovalMan;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by mazehong on 15-1-18.
 * 页面传入商旅差旅信息对象 TravelInfoVO
 */
public class TravelInfoVO implements Serializable {

    private static final long serialVersionUID = 6449286293880608739L;
    /**
     * 前后出发几小时原因代码
     */
    private String depHourReasonCode;
    /**
     * 前后到达几小时原因代码
     */
    private String arrHourReasonCode;
    /**
     * 前后出发几日原因代码
     */
    private String depDayReasonCode;
    /**
     * 前后到达几日原因代码
     */
    private String arrDayReasonCode;
    /**
     * 差旅信息项目代码
     */
    private String projectCode;
    /**
     * 商旅差旅备注说明
     */
    private String travelNotes;
    /**
     * 差旅信息出差事由
     */
    private String businessReason;
    /**
     * 差旅信息职位属性
     */
    private String jobAttribute;
    /**
     * 出行性质，因公出行还是因私出行（因公：N  因私：I）
     */
    private String travelType;
    /**
     * 是否需要同时发送邮件审批和短信审批 Y/N
     */
    private String smsAndEmailApprovalFlag;
    /**
     * 客户要求说明
     */
    private String customRequest;
    /**
     * 成本中心ID
     */
    private String costCenterId;
    /**

     * 成本中心对应名称
     */
    private String costCenterName;
    /**
     * 传进来的商旅审批人集合
     */
    private Set<SelectedApprovalMan> approvalManSet;

    public String getDepHourReasonCode() {
        return depHourReasonCode;
    }

    public void setDepHourReasonCode(String depHourReasonCode) {
        this.depHourReasonCode = depHourReasonCode;
    }

    public String getArrHourReasonCode() {
        return arrHourReasonCode;
    }

    public void setArrHourReasonCode(String arrHourReasonCode) {
        this.arrHourReasonCode = arrHourReasonCode;
    }

    public String getDepDayReasonCode() {
        return depDayReasonCode;
    }

    public void setDepDayReasonCode(String depDayReasonCode) {
        this.depDayReasonCode = depDayReasonCode;
    }

    public String getArrDayReasonCode() {
        return arrDayReasonCode;
    }

    public void setArrDayReasonCode(String arrDayReasonCode) {
        this.arrDayReasonCode = arrDayReasonCode;
    }

    public String getTravelNotes() {
        return travelNotes;
    }

    public void setTravelNotes(String travelNotes) {
        this.travelNotes = travelNotes;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getBusinessReason() {
        return businessReason;
    }

    public void setBusinessReason(String businessReason) {
        this.businessReason = businessReason;
    }

    public String getJobAttribute() {
        return jobAttribute;
    }

    public void setJobAttribute(String jobAttribute) {
        this.jobAttribute = jobAttribute;
    }

    public String getTravelType() {
        return travelType;
    }

    public void setTravelType(String travelType) {
        this.travelType = travelType;
    }

    public void setCustomRequest(String customRequest) {
        this.customRequest = customRequest;
    }

    public String getCustomRequest() {
        return customRequest;
    }

    public Set<SelectedApprovalMan> getApprovalManSet() {
        return approvalManSet;
    }

    public void setApprovalManSet(Set<SelectedApprovalMan> approvalManSet) {
        this.approvalManSet = approvalManSet;
    }

    public String getSmsAndEmailApprovalFlag() {
        return smsAndEmailApprovalFlag;
    }

    public void setSmsAndEmailApprovalFlag(String smsAndEmailApprovalFlag) {
        this.smsAndEmailApprovalFlag = smsAndEmailApprovalFlag;
    }

    public String getCostCenterId() {
        return costCenterId;
    }

    public void setCostCenterId(String costCenterId) {
        this.costCenterId = costCenterId;
    }

    public String getCostCenterName() {
        return costCenterName;
    }

    public void setCostCenterName(String costCenterName) {
        this.costCenterName = costCenterName;
    }
}
