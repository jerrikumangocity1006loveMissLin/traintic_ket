package com.mangocity.vo;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 退单明细
 * @author lanlonghui
 *
 */
public class TrainReturnMxVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5656219632264139282L;
	
	private String passengerId;//乘坐人常旅客id
	private String ticketNo;//票号
	private String passenger;//乘坐人
	private String passengerType;//票券类型
	private String orderNo;//明细单号
	public String getPassengerId() {
		return passengerId;
	}
	@JSONField(name="pid")
	public void setPassengerId(String passengerId) {
		this.passengerId = passengerId;
	}
	public String getTicketNo() {
		return ticketNo;
	}
	@JSONField(name="tno")
	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
	public String getPassenger() {
		return passenger;
	}
	@JSONField(name="czr")
	public void setPassenger(String passenger) {
		this.passenger = passenger;
	}
	public String getPassengerType() {
		return passengerType;
	}
	@JSONField(name="crl")
	public void setPassengerType(String passengerType) {
		this.passengerType = passengerType;
	}
	public String getOrderNo() {
		return orderNo;
	}
	@JSONField(name="mxd")
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	@Override
	public String toString() {
		return "TrainReturnMxVo [passengerId=" + passengerId + ", ticketNo="
				+ ticketNo + ", passenger=" + passenger + ", passengerType="
				+ passengerType + ", orderNo=" + orderNo + "]";
	}
	
	

}
