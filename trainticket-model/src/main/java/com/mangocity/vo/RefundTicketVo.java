package com.mangocity.vo;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class RefundTicketVo implements Serializable{

	/**
	 * 退票信息列表
	 */
	private static final long serialVersionUID = 5029447002865673627L;
	
	private String ticketNo;//票号
	
	private String passengerName;//乘客姓名
	
	private String passportTypeSeId;//证件类别编号
	
	private String passportSeNo;//证件号码

	public String getTicketNo() {
		return ticketNo;
	}

	@JSONField
	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public String getPassengerName() {
		return passengerName;
	}
	@JSONField
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getPassportTypeSeId() {
		return passportTypeSeId;
	}
	@JSONField
	public void setPassportTypeSeId(String passportTypeSeId) {
		this.passportTypeSeId = passportTypeSeId;
	}

	public String getPassportSeNo() {
		return passportSeNo;
	}
	@JSONField
	public void setPassportSeNo(String passportSeNo) {
		this.passportSeNo = passportSeNo;
	}

	@Override
	public String toString() {
		return "RefundTicketVo [ticketNo=" + ticketNo + ", passengerName="
				+ passengerName + ", passportTypeSeId=" + passportTypeSeId
				+ ", passportSeNo=" + passportSeNo + "]";
	}
	
	

}
