package com.mangocity.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author lizhi
 *
 * @date 2016年6月1日
 */
public class PurchaseItem implements Serializable {

	private static final long serialVersionUID = 728936851853132915L;
	private Long id;
	private Long purchaseId;
	private Long goodsId;
	private String goodsType;
	private String status;
	private BigDecimal amount;
	private String supplier;
	private String hbOrderId;//号百订单号
	private String trainNo;//票号
	private String trainSeat;//车厢座位
	private String orderNo;//12306单号
	private Date createTime;

	public PurchaseItem() {
		super();
	}

	public PurchaseItem(Long id, Long purchaseId, Long goodsId,
			String goodsType, String status, BigDecimal amount,
			String supplier, String hbOrderId, String trainNo,
			String trainSeat, String orderNo, Date createTime) {
		super();
		this.id = id;
		this.purchaseId = purchaseId;
		this.goodsId = goodsId;
		this.goodsType = goodsType;
		this.status = status;
		this.amount = amount;
		this.supplier = supplier;
		this.hbOrderId = hbOrderId;
		this.trainNo = trainNo;
		this.trainSeat = trainSeat;
		this.orderNo = orderNo;
		this.createTime = createTime;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPurchaseId() {
		return purchaseId;
	}


	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsType() {
		return goodsType;
	}


	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getHbOrderId() {
		return hbOrderId;
	}

	public void setHbOrderId(String hbOrderId) {
		this.hbOrderId = hbOrderId;
	}

	public String getTrainNo() {
		return trainNo;
	}

	public void setTrainNo(String trainNo) {
		this.trainNo = trainNo;
	}

	public String getTrainSeat() {
		return trainSeat;
	}

	public void setTrainSeat(String trainSeat) {
		this.trainSeat = trainSeat;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	@Override
	public String toString() {
		return "PurchaseItem [id=" + id + ", purchaseId=" + purchaseId
				+ ", goodsId=" + goodsId + ", goodsType=" + goodsType
				+ ", status=" + status + ", amount=" + amount + ", supplier="
				+ supplier + ", hbOrderId=" + hbOrderId + ", trainNo="
				+ trainNo + ", orderNo=" + orderNo + ", createTime="
				+ createTime + "]";
	}
}
