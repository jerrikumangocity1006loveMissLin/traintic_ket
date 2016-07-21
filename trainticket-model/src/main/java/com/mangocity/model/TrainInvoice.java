package com.mangocity.model;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 发票
 * @author lizhi
 *
 * @2016年6月25日
 */
public class TrainInvoice implements Serializable {

	private static final long serialVersionUID = -5442393156604647850L;
	private Long id;
	private String invoiceHead;
	private String address;
	private String invoiceUnit;
	private BigDecimal amount;
	private String drawer;
	private Long orderItemId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInvoiceHead() {
		return invoiceHead;
	}

	public void setInvoiceHead(String invoiceHead) {
		this.invoiceHead = invoiceHead;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getInvoiceUnit() {
		return invoiceUnit;
	}

	public void setInvoiceUnit(String invoiceUnit) {
		this.invoiceUnit = invoiceUnit;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDrawer() {
		return drawer;
	}

	public void setDrawer(String drawer) {
		this.drawer = drawer;
	}

	public Long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}

}
