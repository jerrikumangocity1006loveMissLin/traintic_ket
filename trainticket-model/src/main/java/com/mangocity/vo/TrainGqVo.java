package com.mangocity.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 改签单信息
 * @author lanlonghui
 *
 */
public class TrainGqVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4412146112856585968L;
	
	private String changOrderNo;//改签单号
	private String orderNo;//订单编号
	private String takeOrderNo;//取票单号
	private String status;//改签单状态1已申请，2已确认待支付，3 已确认，4已改签未退款，5 已改签，6 已取消，7 已完成
	private String contact;//联系人
	private String telephone;//联系电话
	private BigDecimal changeFee;//改签费用
	private String fee;//服务费
	private BigDecimal oldPrice;//原票价
	private BigDecimal newPrice;//新票价
	private String oldTrainNo;//原车次
	private String newTrainNo;//新车次
	private Date oldStartDate;//原出发日期
	private Date newStartDate;//新出发日期
	private String oldStartTime;//原出发时间
	private String newStartTime;//新出发时间
	private Date oldArrivalDate;//原到达日期
	private Date newArrivalDate;//新到达日期
	private String oldArrivalTime;//原到达时间
	private String newArrivalTime;//新到达时间
	private String startStation;//出发站
	private String arrivalStation;//到达站
	private String startStationName;//出发站名称
	private String arrivalStationName;//到达站名称
	private String payFlag;//付款否
	private String payName;//付款科目
	private Date payDate;//支付时间
	private String payAmount;//支付金额
	private Date applyDate;//申请时间
	private String applyName;//申请人，如果是后台申请，返回“代申请”
	private String tripType;//差旅类型0因公1因私
	private String changeDesc;//改签原因
	private String failDesc;//下单失败原因
	public String getChangOrderNo() {
		return changOrderNo;
	}
	@JSONField(name="gid")
	public void setChangOrderNo(String changOrderNo) {
		this.changOrderNo = changOrderNo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	@JSONField(name="ord")
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getTakeOrderNo() {
		return takeOrderNo;
	}
	@JSONField(name="ono")
	public void setTakeOrderNo(String takeOrderNo) {
		this.takeOrderNo = takeOrderNo;
	}
	public String getStatus() {
		return status;
	}
	@JSONField(name="gzt")
	public void setStatus(String status) {
		this.status = status;
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
	public BigDecimal getChangeFee() {
		return changeFee;
	}
	@JSONField(name="gfy")
	public void setChangeFee(BigDecimal changeFee) {
		this.changeFee = changeFee;
	}
	public String getFee() {
		return fee;
	}
	@JSONField(name="fee")
	public void setFee(String fee) {
		this.fee = fee;
	}
	public BigDecimal getOldPrice() {
		return oldPrice;
	}
	@JSONField(name="opj")
	public void setOldPrice(BigDecimal oldPrice) {
		this.oldPrice = oldPrice;
	}
	public BigDecimal getNewPrice() {
		return newPrice;
	}
	@JSONField(name="npj")
	public void setNewPrice(BigDecimal newPrice) {
		this.newPrice = newPrice;
	}
	public String getOldTrainNo() {
		return oldTrainNo;
	}
	@JSONField(name="occ")
	public void setOldTrainNo(String oldTrainNo) {
		this.oldTrainNo = oldTrainNo;
	}
	public String getNewTrainNo() {
		return newTrainNo;
	}
	@JSONField(name="ncc")
	public void setNewTrainNo(String newTrainNo) {
		this.newTrainNo = newTrainNo;
	}
	public Date getOldStartDate() {
		return oldStartDate;
	}
	@JSONField(name="osr")
	public void setOldStartDate(Date oldStartDate) {
		this.oldStartDate = oldStartDate;
	}
	public Date getNewStartDate() {
		return newStartDate;
	}
	@JSONField(name="nsr")
	public void setNewStartDate(Date newStartDate) {
		this.newStartDate = newStartDate;
	}
	public String getOldStartTime() {
		return oldStartTime;
	}
	@JSONField(name="osj")
	public void setOldStartTime(String oldStartTime) {
		this.oldStartTime = oldStartTime;
	}
	public String getNewStartTime() {
		return newStartTime;
	}
	@JSONField(name="nsj")
	public void setNewStartTime(String newStartTime) {
		this.newStartTime = newStartTime;
	}
	public Date getOldArrivalDate() {
		return oldArrivalDate;
	}
	@JSONField(name="odr")
	public void setOldArrivalDate(Date oldArrivalDate) {
		this.oldArrivalDate = oldArrivalDate;
	}
	public Date getNewArrivalDate() {
		return newArrivalDate;
	}
	@JSONField(name="ndr")
	public void setNewArrivalDate(Date newArrivalDate) {
		this.newArrivalDate = newArrivalDate;
	}
	public String getOldArrivalTime() {
		return oldArrivalTime;
	}
	@JSONField(name="ods")
	public void setOldArrivalTime(String oldArrivalTime) {
		this.oldArrivalTime = oldArrivalTime;
	}
	public String getNewArrivalTime() {
		return newArrivalTime;
	}
	@JSONField(name="nds")
	public void setNewArrivalTime(String newArrivalTime) {
		this.newArrivalTime = newArrivalTime;
	}
	public String getStartStation() {
		return startStation;
	}
	@JSONField(name="sfz")
	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}
	public String getArrivalStation() {
		return arrivalStation;
	}
	@JSONField(name="ddz")
	public void setArrivalStation(String arrivalStation) {
		this.arrivalStation = arrivalStation;
	}
	public String getStartStationName() {
		return startStationName;
	}
	@JSONField(name="sfm")
	public void setStartStationName(String startStationName) {
		this.startStationName = startStationName;
	}
	public String getArrivalStationName() {
		return arrivalStationName;
	}
	@JSONField(name="ddm")
	public void setArrivalStationName(String arrivalStationName) {
		this.arrivalStationName = arrivalStationName;
	}
	public String getPayFlag() {
		return payFlag;
	}
	@JSONField(name="fkf")
	public void setPayFlag(String payFlag) {
		this.payFlag = payFlag;
	}
	public String getPayName() {
		return payName;
	}
	@JSONField(name="fkc")
	public void setPayName(String payName) {
		this.payName = payName;
	}
	public Date getPayDate() {
		return payDate;
	}
	@JSONField(name="fsj")
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public String getPayAmount() {
		return payAmount;
	}
	@JSONField(name="zfje")
	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	@JSONField(name="sqs")
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public String getApplyName() {
		return applyName;
	}
	@JSONField(name="sqr")
	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}
	public String getTripType() {
		return tripType;
	}
	@JSONField(name="bst")
	public void setTripType(String tripType) {
		this.tripType = tripType;
	}
	public String getChangeDesc() {
		return changeDesc;
	}
	@JSONField(name="gqy")
	public void setChangeDesc(String changeDesc) {
		this.changeDesc = changeDesc;
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
		return "TrainGqVo [changOrderNo=" + changOrderNo + ", orderNo="
				+ orderNo + ", takeOrderNo=" + takeOrderNo + ", status="
				+ status + ", contact=" + contact + ", telephone=" + telephone
				+ ", changeFee=" + changeFee + ", fee=" + fee + ", oldPrice="
				+ oldPrice + ", newPrice=" + newPrice + ", oldTrainNo="
				+ oldTrainNo + ", newTrainNo=" + newTrainNo + ", oldStartDate="
				+ oldStartDate + ", newStartDate=" + newStartDate
				+ ", oldStartTime=" + oldStartTime + ", newStartTime="
				+ newStartTime + ", oldArrivalDate=" + oldArrivalDate
				+ ", newArrivalDate=" + newArrivalDate + ", oldArrivalTime="
				+ oldArrivalTime + ", newArrivalTime=" + newArrivalTime
				+ ", startStation=" + startStation + ", arrivalStation="
				+ arrivalStation + ", startStationName=" + startStationName
				+ ", arrivalStationName=" + arrivalStationName + ", payFlag="
				+ payFlag + ", payName=" + payName + ", payDate=" + payDate
				+ ", payAmount=" + payAmount + ", applyDate=" + applyDate
				+ ", applyName=" + applyName + ", tripType=" + tripType
				+ ", changeDesc=" + changeDesc + ", failDesc=" + failDesc + "]";
	}
	
	

}
