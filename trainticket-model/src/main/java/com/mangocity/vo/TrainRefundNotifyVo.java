package com.mangocity.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 火车票退票结果通知
 * @author lanlonghui
 *
 */
public class TrainRefundNotifyVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6495140034195085308L;
	
	private String refundOrderNo;//退票单号
	private String refundStatus;//退票状态
	private String orderNo;//订单编号
	private String applicantNo;//差旅申请人工号
	private String applicantName;//差旅申请人姓名
	private Date applyTime;//申请时间
	private Date auditTime;//审核时间
	private List<TrainReOrderMxVo> trainReOrderMx;//退票明细
	public String getRefundOrderNo() {
		return refundOrderNo;
	}
	@JSONField
	public void setRefundOrderNo(String refundOrderNo) {
		this.refundOrderNo = refundOrderNo;
	}
	public String getRefundStatus() {
		return refundStatus;
	}
	@JSONField
	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}
	public String getOrderNo() {
		return orderNo;
	}
	@JSONField
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getApplicantNo() {
		return applicantNo;
	}
	@JSONField
	public void setApplicantNo(String applicantNo) {
		this.applicantNo = applicantNo;
	}
	public String getApplicantName() {
		return applicantName;
	}
	@JSONField
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	@JSONField
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	@JSONField
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public List<TrainReOrderMxVo> getTrainReOrderMx() {
		return trainReOrderMx;
	}
	@JSONField
	public void setTrainReOrderMx(List<TrainReOrderMxVo> trainReOrderMx) {
		this.trainReOrderMx = trainReOrderMx;
	}
	
}
