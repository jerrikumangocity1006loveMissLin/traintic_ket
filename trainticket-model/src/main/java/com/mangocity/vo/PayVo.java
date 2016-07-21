package com.mangocity.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class PayVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5734032823213717900L;

	private String type;//业务类型：2 - 火车票订单；5 - 火车票改签
	
	private String hbOrderCn;//号百订单号
	
	private BigDecimal amount;//订单支付金额
	
	private String by1;//备用字段
	
	private Long orderId;//订单ID

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHbOrderCn() {
		return hbOrderCn;
	}

	public void setHbOrderCn(String hbOrderCn) {
		this.hbOrderCn = hbOrderCn;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getBy1() {
		return by1;
	}

	public void setBy1(String by1) {
		this.by1 = by1;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "PayVo [type=" + type + ", hbOrderCn=" + hbOrderCn + ", amount="
				+ amount + ", by1=" + by1 + ", orderId=" + orderId + "]";
	}
	
	
}
