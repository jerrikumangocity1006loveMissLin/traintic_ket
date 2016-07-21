package com.mangocity.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 退票单信息
 * @author lanlonghui
 *
 */
public class TrainReturnVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -586771488630072571L;
	
	private String trainNo;//车次
	private String oldOrderNo;//原订单编号
	private Date startDate;//出发日期
	private String startTime;//出发时间
	private String startStation;//出发站
	private String startStationName;//出发站名称
	private Date arriveDate;//到达日期
	private String arriveTime;//到达时间
	private String arriveStation;//到达站
	private String arriveStationName;//到达站名称
	private String contact;//联系人
	private String telephone;//联系人手机
	private String applyName;//申请人名称，如果后台代申请，请返回“代申请”
	private Date applyDate;//申请时间
	private String refundFlag;//是否退款
	private String refundName;//退款科目名称
	private Date refundDate;//退款时间
	private BigDecimal refundPrice;//退票价
	private BigDecimal refundAmount;//退票金额
	private BigDecimal refundFee;//退款手续费
	private String refundState;//退票状态
	private String failDesc;//下单失败原因
	public String getTrainNo() {
		return trainNo;
	}
	@JSONField(name="cch")
	public void setTrainNo(String trainNo) {
		this.trainNo = trainNo;
	}
	public String getOldOrderNo() {
		return oldOrderNo;
	}
	@JSONField(name="ord")
	public void setOldOrderNo(String oldOrderNo) {
		this.oldOrderNo = oldOrderNo;
	}
	public Date getStartDate() {
		return startDate;
	}
	@JSONField(name="sfr")
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getStartTime() {
		return startTime;
	}
	@JSONField(name="sfs")
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getStartStation() {
		return startStation;
	}
	@JSONField(name="sfz")
	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}
	public String getStartStationName() {
		return startStationName;
	}
	@JSONField(name="sfm")
	public void setStartStationName(String startStationName) {
		this.startStationName = startStationName;
	}
	public Date getArriveDate() {
		return arriveDate;
	}
	@JSONField(name="ddr")
	public void setArriveDate(Date arriveDate) {
		this.arriveDate = arriveDate;
	}
	public String getArriveTime() {
		return arriveTime;
	}
	@JSONField(name="dds")
	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}
	public String getArriveStation() {
		return arriveStation;
	}
	@JSONField(name="ddz")
	public void setArriveStation(String arriveStation) {
		this.arriveStation = arriveStation;
	}
	public String getArriveStationName() {
		return arriveStationName;
	}
	@JSONField(name="ddm")
	public void setArriveStationName(String arriveStationName) {
		this.arriveStationName = arriveStationName;
	}
	public String getContact() {
		return contact;
	}
	@JSONField(name="lxr")
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getTelephone() {
		return telephone;
	}
	@JSONField(name="lxj")
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getApplyName() {
		return applyName;
	}
	@JSONField(name="sqr")
	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	@JSONField(name="sqs")
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public String getRefundFlag() {
		return refundFlag;
	}
	@JSONField(name="tkf")
	public void setRefundFlag(String refundFlag) {
		this.refundFlag = refundFlag;
	}
	public String getRefundName() {
		return refundName;
	}
	@JSONField(name="tkm")
	public void setRefundName(String refundName) {
		this.refundName = refundName;
	}
	public Date getRefundDate() {
		return refundDate;
	}
	@JSONField(name="tks")
	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}
	public BigDecimal getRefundPrice() {
		return refundPrice;
	}
	@JSONField(name="tpj")
	public void setRefundPrice(BigDecimal refundPrice) {
		this.refundPrice = refundPrice;
	}
	public BigDecimal getRefundAmount() {
		return refundAmount;
	}
	@JSONField(name="tpe")
	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}
	public BigDecimal getRefundFee() {
		return refundFee;
	}
	@JSONField(name="sxf")
	public void setRefundFee(BigDecimal refundFee) {
		this.refundFee = refundFee;
	}
	public String getRefundState() {
		return refundState;
	}
	@JSONField(name="tpz")
	public void setRefundState(String refundState) {
		this.refundState = refundState;
	}
	public String getFailDesc() {
		return failDesc;
	}
	@JSONField(name="tdn")
	public void setFailDesc(String failDesc) {
		this.failDesc = failDesc;
	}
	@Override
	public String toString() {
		return "TrainReturnVo [trainNo=" + trainNo + ", oldOrderNo="
				+ oldOrderNo + ", startDate=" + startDate + ", startTime="
				+ startTime + ", startStation=" + startStation
				+ ", startStationName=" + startStationName + ", arriveDate="
				+ arriveDate + ", arriveTime=" + arriveTime
				+ ", arriveStation=" + arriveStation + ", arriveStationName="
				+ arriveStationName + ", contact=" + contact + ", telephone="
				+ telephone + ", applyName=" + applyName + ", applyDate="
				+ applyDate + ", refundFlag=" + refundFlag + ", refundName="
				+ refundName + ", refundDate=" + refundDate + ", refundPrice="
				+ refundPrice + ", refundAmount=" + refundAmount
				+ ", refundFee=" + refundFee + ", refundState=" + refundState
				+ ", failDesc=" + failDesc + "]";
	}
	
	

}
