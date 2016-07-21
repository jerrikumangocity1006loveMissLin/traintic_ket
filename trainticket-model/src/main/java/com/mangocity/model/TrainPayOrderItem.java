package com.mangocity.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 支付明细
 * @author lizhi
 *
 * @date 2016年5月25日
 */
public class TrainPayOrderItem implements Serializable {
	private static final long serialVersionUID = -850170827745026294L;
	private Long id;
	private Long payInfoId;
	private Long goodsId;
	private String goodsType;
	private Date createTime;

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
