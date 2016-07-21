package com.mangocity.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * 支付
 * @author lizhi
 *
 * @date 2016年5月25日
 */
public class TrainPay implements Serializable {

	private static final long serialVersionUID = -5253463428543591558L;
	private Long id;
	private Long payInfoId;
	private String orderCd;
	private BigDecimal amount;
	private String status;
	private Date createTime;
	private Date modifyTime;//更新时间

	public TrainPay() {
		super();
	}

	

	public TrainPay(Long id, Long payInfoId, String orderCd, BigDecimal amount,
			String status, Date createTime, Date modifyTime) {
		super();
		this.id = id;
		this.payInfoId = payInfoId;
		this.orderCd = orderCd;
		this.amount = amount;
		this.status = status;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPayInfoId() {
		return payInfoId;
	}

	public void setPayInfoId(Long payInfoId) {
		this.payInfoId = payInfoId;
	}

	public String getOrderCd() {
		return orderCd;
	}

	public void setOrderCd(String orderCd) {
		this.orderCd = orderCd;
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


	public Date getModifyTime() {
		return modifyTime;
	}


	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}



	@Override
	public String toString() {
		return "TrainPay [id=" + id + ", payInfoId=" + payInfoId + ", orderCd="
				+ orderCd + ", amount=" + amount + ", status=" + status
				+ ", createTime=" + createTime + ", modifyTime=" + modifyTime
				+ "]";
	}
}
