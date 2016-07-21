package com.mangocity.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class RefundTicketReturnVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8674819210754922220L;
	private String ticketNo;//票号
	private String passenger;//乘客姓名
	private BigDecimal returnAmount;//退款金额（成功才有值）
	private Date date;//退票成功的时间（成功才有值）
	private String returnOrderNo;//退票单号
	private String flag;//退票是否成功： 0成功，-1失败
	private String message;//退票错误信息
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
	@JSONField(name="pnm")
	public void setPassenger(String passenger) {
		this.passenger = passenger;
	}
	public BigDecimal getReturnAmount() {
		return returnAmount;
	}
	@JSONField(name="rtm")
	public void setReturnAmount(BigDecimal returnAmount) {
		this.returnAmount = returnAmount;
	}
	public Date getDate() {
		return date;
	}
	@JSONField(name="rtt")
	public void setDate(Date date) {
		this.date = date;
	}
	public String getReturnOrderNo() {
		return returnOrderNo;
	}
	@JSONField(name="rno")
	public void setReturnOrderNo(String returnOrderNo) {
		this.returnOrderNo = returnOrderNo;
	}
	public String getFlag() {
		return flag;
	}
	@JSONField(name="rst")
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getMessage() {
		return message;
	}
	@JSONField(name="rer")
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "RefundTicketReturnVo [ticketNo=" + ticketNo + ", passenger="
				+ passenger + ", returnAmount=" + returnAmount + ", date="
				+ date + ", returnOrderNo=" + returnOrderNo + ", flag=" + flag
				+ ", message=" + message + "]";
	}
	
	
}
