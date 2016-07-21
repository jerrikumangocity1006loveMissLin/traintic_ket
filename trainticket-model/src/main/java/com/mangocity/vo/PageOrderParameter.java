package com.mangocity.vo;

import java.io.Serializable;

public class PageOrderParameter implements Serializable {

	private static final long serialVersionUID = -4832820620110959100L;
	// 订单编号
	private String orderCd;
	// 会籍编号
	private String memberCd;
	// 会员名
	private String memeberName;
	// 订单状态
	private String orderStatus;
	// 预定时间起
	private String orderTimeStart;
	// 预定时间至
	private String orderTimeEnd;
	// 乘客姓名
	private String passengerName;
	// 行程from
	private String travelFrom;
	// 行程to
	private String travelTo;
	// 支付状态
	private String payStatus;
	// 出行时间起
	private String travelTimeStart;
	// 出行时间至
	private String travelTimeEnd;
	// 车次
	private String travelCd;
	// 号百平台订单号
	private String platCd100;
	// 12306平台订单号
	private String platCd12306;
	// 当前页
	private int pageNo;
	// 每页数
	private int pageSize;
	// 火车票类型 0-订票 1-改签 2-退票
	private String type;

	public String getOrderCd() {
		return orderCd;
	}

	public void setOrderCd(String orderCd) {
		this.orderCd = orderCd;
	}

	public String getMemberCd() {
		return memberCd;
	}

	public void setMemberCd(String memberCd) {
		this.memberCd = memberCd;
	}

	public String getMemeberName() {
		return memeberName;
	}

	public void setMemeberName(String memeberName) {
		this.memeberName = memeberName;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderTimeStart() {
		return orderTimeStart;
	}

	public void setOrderTimeStart(String orderTimeStart) {
		this.orderTimeStart = orderTimeStart;
	}

	public String getOrderTimeEnd() {
		return orderTimeEnd;
	}

	public void setOrderTimeEnd(String orderTimeEnd) {
		this.orderTimeEnd = orderTimeEnd;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getTravelFrom() {
		return travelFrom;
	}

	public void setTravelFrom(String travelFrom) {
		this.travelFrom = travelFrom;
	}

	public String getTravelTo() {
		return travelTo;
	}

	public void setTravelTo(String travelTo) {
		this.travelTo = travelTo;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getTravelTimeStart() {
		return travelTimeStart;
	}

	public void setTravelTimeStart(String travelTimeStart) {
		this.travelTimeStart = travelTimeStart;
	}

	public String getTravelTimeEnd() {
		return travelTimeEnd;
	}

	public void setTravelTimeEnd(String travelTimeEnd) {
		this.travelTimeEnd = travelTimeEnd;
	}

	public String getTravelCd() {
		return travelCd;
	}

	public void setTravelCd(String travelCd) {
		this.travelCd = travelCd;
	}

	public String getPlatCd100() {
		return platCd100;
	}

	public void setPlatCd100(String platCd100) {
		this.platCd100 = platCd100;
	}

	public String getPlatCd12306() {
		return platCd12306;
	}

	public void setPlatCd12306(String platCd12306) {
		this.platCd12306 = platCd12306;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "PageOrderParameter [orderCd=" + orderCd + ", memberCd=" + memberCd + ", memeberName=" + memeberName
				+ ", orderStatus=" + orderStatus + ", orderTimeStart=" + orderTimeStart + ", orderTimeEnd="
				+ orderTimeEnd + ", passengerName=" + passengerName + ", travelFrom=" + travelFrom + ", travelTo="
				+ travelTo + ", payStatus=" + payStatus + ", travelTimeStart=" + travelTimeStart + ", travelTimeEnd="
				+ travelTimeEnd + ", travelCd=" + travelCd + ", platCd100=" + platCd100 + ", platCd12306=" + platCd12306
				+ ", pageNo=" + pageNo + ", pageSize=" + pageSize + ", type=" + type + "]";
	}

}
