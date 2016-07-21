package com.mangocity.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 *退票参数
 */
public class RefundParamVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7976232226842067995L;
	
	private Long ticketId;//火车票ID
	
	private BigDecimal refundRate;//退票费比例
	
	private BigDecimal refundFee;//退票费
	
	private BigDecimal returnAmount;//应退金额
	
	private BigDecimal fee;//服务费

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public BigDecimal getRefundRate() {
		return refundRate;
	}

	public void setRefundRate(BigDecimal refundRate) {
		this.refundRate = refundRate;
	}

	public BigDecimal getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(BigDecimal refundFee) {
		this.refundFee = refundFee;
	}

	public BigDecimal getReturnAmount() {
		return returnAmount;
	}

	public void setReturnAmount(BigDecimal returnAmount) {
		this.returnAmount = returnAmount;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	
	

}
