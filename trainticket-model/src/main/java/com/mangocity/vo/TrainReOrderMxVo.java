package com.mangocity.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class TrainReOrderMxVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3693479168752367370L;
	
	private String departStation;//出发站
	private String arriveStation;//到达站
	private String trainNo;//车次号
	private Date departDay;//出发日期
	private String departTime;//出发时间
	private Date arriveDay;//到达日期
	private String arriveTime;//到达时间
	private String seatType;//座位类型：9:商务座，P:特等座，M:一等座，O:二等座，6:高级软卧，4:软卧，3:硬卧，2:软座，1:硬座
	private String passenger;//乘坐人姓名
	private String idNo;//证件号码
	private String ticketNo;//票号
	private BigDecimal refundPrice;//退票票价
	private BigDecimal refundFee;//退票手续费
	private BigDecimal refundAmount;//应退金额
	private String refundRatio;//退票比例
	public String getDepartStation() {
		return departStation;
	}
	@JSONField
	public void setDepartStation(String departStation) {
		this.departStation = departStation;
	}
	public String getArriveStation() {
		return arriveStation;
	}
	@JSONField
	public void setArriveStation(String arriveStation) {
		this.arriveStation = arriveStation;
	}
	public String getTrainNo() {
		return trainNo;
	}
	@JSONField
	public void setTrainNo(String trainNo) {
		this.trainNo = trainNo;
	}
	public Date getDepartDay() {
		return departDay;
	}
	@JSONField
	public void setDepartDay(Date departDay) {
		this.departDay = departDay;
	}
	public String getDepartTime() {
		return departTime;
	}
	@JSONField
	public void setDepartTime(String departTime) {
		this.departTime = departTime;
	}
	public Date getArriveDay() {
		return arriveDay;
	}
	@JSONField
	public void setArriveDay(Date arriveDay) {
		this.arriveDay = arriveDay;
	}
	public String getArriveTime() {
		return arriveTime;
	}
	@JSONField
	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}
	public String getSeatType() {
		return seatType;
	}
	@JSONField
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}
	public String getPassenger() {
		return passenger;
	}
	@JSONField
	public void setPassenger(String passenger) {
		this.passenger = passenger;
	}
	public String getIdNo() {
		return idNo;
	}
	@JSONField
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getTicketNo() {
		return ticketNo;
	}
	@JSONField
	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
	public BigDecimal getRefundPrice() {
		return refundPrice;
	}
	@JSONField
	public void setRefundPrice(BigDecimal refundPrice) {
		this.refundPrice = refundPrice;
	}
	public BigDecimal getRefundFee() {
		return refundFee;
	}
	@JSONField
	public void setRefundFee(BigDecimal refundFee) {
		this.refundFee = refundFee;
	}
	public BigDecimal getRefundAmount() {
		return refundAmount;
	}
	@JSONField
	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}
	public String getRefundRatio() {
		return refundRatio;
	}
	@JSONField
	public void setRefundRatio(String refundRatio) {
		this.refundRatio = refundRatio;
	}

}
