package com.mangocity.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class PayDetailBean implements Serializable {

	private static final long serialVersionUID = 5586430806713913125L;
	private BigDecimal sumTicketPrice;// 总票价和
	private BigDecimal sumFee;// 总费用和
	private BigDecimal sumTmcPrice;// 总商旅价和
	private BigDecimal sumDelivery;// 配送费和
	private BigDecimal total;// 总和

	public BigDecimal getSumTicketPrice() {
		return sumTicketPrice;
	}

	public void setSumTicketPrice(BigDecimal sumTicketPrice) {
		this.sumTicketPrice = sumTicketPrice;
	}

	public BigDecimal getSumFee() {
		return sumFee;
	}

	public void setSumFee(BigDecimal sumFee) {
		this.sumFee = sumFee;
	}

	public BigDecimal getSumTmcPrice() {
		return sumTmcPrice;
	}

	public void setSumTmcPrice(BigDecimal sumTmcPrice) {
		this.sumTmcPrice = sumTmcPrice;
	}

	public BigDecimal getSumDelivery() {
		return sumDelivery;
	}

	public void setSumDelivery(BigDecimal sumDelivery) {
		this.sumDelivery = sumDelivery;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "PayDetailBean [sumTicketPrice=" + sumTicketPrice + ", sumFee=" + sumFee + ", sumTmcPrice=" + sumTmcPrice
				+ ", sumDelivery=" + sumDelivery + ", total=" + total + "]";
	}

}
