package com.mangocity.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.mangocity.model.Passenger;
import com.mangocity.model.Ticket;

/**
 * 乘机行程信息
 * 
 * @author hongxiaodong
 *
 */
public class TicketInfoVo extends Ticket implements Serializable {

	private static final long serialVersionUID = -4320135124024335639L;

	private Passenger passenger;// 乘机人信息

	private BigDecimal refundRate;//退票例
	
	private BigDecimal refundFee;//退票费
	
	private BigDecimal returnAmount;//退还金额
	
	private BigDecimal preSalePrice;//预订票价
	
	private BigDecimal preFee;//预订服务费
	
	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
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

	public BigDecimal getPreSalePrice() {
		return preSalePrice;
	}

	public void setPreSalePrice(BigDecimal preSalePrice) {
		this.preSalePrice = preSalePrice;
	}

	public BigDecimal getPreFee() {
		return preFee;
	}

	public void setPreFee(BigDecimal preFee) {
		this.preFee = preFee;
	}

}
