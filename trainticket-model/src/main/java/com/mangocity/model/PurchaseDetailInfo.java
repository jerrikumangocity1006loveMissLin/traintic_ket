package com.mangocity.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author lizhi
 *
 * @date 2016年6月1日
 */
public class PurchaseDetailInfo implements Serializable {

	private static final long serialVersionUID = 5051197127803747777L;
	private Long id;
	private Long purchaseItemId;
	private BigDecimal amount;
	private String type;
	private String priceType;//价格类型
	private Integer acount;
	
	public PurchaseDetailInfo() {
		super();
	}

	public PurchaseDetailInfo(Long id, Long purchaseItemId, BigDecimal amount,
			String type, String priceType, Integer acount) {
		super();
		this.id = id;
		this.purchaseItemId = purchaseItemId;
		this.amount = amount;
		this.type = type;
		this.priceType = priceType;
		this.acount = acount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPurchaseItemId() {
		return purchaseItemId;
	}

	public void setPurchaseItemId(Long purchaseItemId) {
		this.purchaseItemId = purchaseItemId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public Integer getAcount() {
		return acount;
	}

	public void setAcount(Integer acount) {
		this.acount = acount;
	}

	@Override
	public String toString() {
		return "PurchaseDetailInfo [id=" + id + ", purchaseItemId="
				+ purchaseItemId + ", amount=" + amount + ", type=" + type
				+ ", priceType=" + priceType + ", acount=" + acount + "]";
	}
}
