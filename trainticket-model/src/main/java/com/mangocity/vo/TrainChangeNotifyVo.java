package com.mangocity.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class TrainChangeNotifyVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3578365537624490182L;
	
	private String changeOrderNo;//改签单号
	private String changeStatus;//改签单状态
	private String orderNo;//正常单编号
	private String applicantNo;//差旅申请人工号
	private String applicantName;//差旅申请人姓名
	private Date applyTime;//申请时间
	private Date completeTime;//完成时间
	private List<TrainGqOrderMxVo> trainGqOrderMx;//改签单明细
	public String getChangeOrderNo() {
		return changeOrderNo;
	}
	@JSONField
	public void setChangeOrderNo(String changeOrderNo) {
		this.changeOrderNo = changeOrderNo;
	}
	public String getChangeStatus() {
		return changeStatus;
	}
	@JSONField
	public void setChangeStatus(String changeStatus) {
		this.changeStatus = changeStatus;
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
	public Date getCompleteTime() {
		return completeTime;
	}
	@JSONField
	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}
	public List<TrainGqOrderMxVo> getTrainGqOrderMx() {
		return trainGqOrderMx;
	}
	@JSONField
	public void setTrainGqOrderMx(List<TrainGqOrderMxVo> trainGqOrderMx) {
		this.trainGqOrderMx = trainGqOrderMx;
	}
	
	

}
