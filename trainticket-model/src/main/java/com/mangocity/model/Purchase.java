package com.mangocity.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author lizhi
 *
 * @date 2016年5月31日
 */
public class Purchase implements Serializable {

	private static final long serialVersionUID = 1344941141943153252L;
	private Long id;
	private String orderCn;
	private BigDecimal amount;
	private String status;
	private Date createTime;
	private Date endTime;
	
	public Purchase() {
		super();
	}

	public Purchase(Long id, String orderCn, BigDecimal amount, String status,
			Date createTime, Date endTime) {
		super();
		this.id = id;
		this.orderCn = orderCn;
		this.amount = amount;
		this.status = status;
		this.createTime = createTime;
		this.endTime = endTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderCn() {
		return orderCn;
	}

	public void setOrderCn(String orderCn) {
		this.orderCn = orderCn;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "Purchase [id=" + id + ", orderCn=" + orderCn + ", amount="
				+ amount + ", status=" + status + ", createTime=" + createTime
				+ ", endTime=" + endTime + "]";
	}

}
