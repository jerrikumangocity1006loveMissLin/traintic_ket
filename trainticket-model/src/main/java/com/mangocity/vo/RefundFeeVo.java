package com.mangocity.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class RefundFeeVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4438647573805650377L;

	private BigDecimal salePrice;//票面退款
	
	private BigDecimal chargerFee;//服务费
	
	private BigDecimal deliveryFee;//快递费
	
	private BigDecimal otherFee;//其他费
	
	private BigDecimal cash;//现金应退
	
	private BigDecimal point;//积分应退
	
	private BigDecimal pointNum;//积分数
	
	private BigDecimal totalPrice;//合计
	
	private List<RefundParamVo> refundParamVoList;//退票参数
	
	public RefundFeeVo() {
		this.salePrice = new BigDecimal("0.0");
		this.chargerFee = new BigDecimal("0.0");
		this.deliveryFee = new BigDecimal("0.0");
		this.otherFee = new BigDecimal("0.0");
		this.cash = new BigDecimal("0.0");
		this.point = new BigDecimal("0.0");
		this.pointNum = new BigDecimal("0.0");
		this.totalPrice = new BigDecimal("0.0");
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public BigDecimal getChargerFee() {
		return chargerFee;
	}

	public void setChargerFee(BigDecimal chargerFee) {
		this.chargerFee = chargerFee;
	}

	public BigDecimal getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(BigDecimal deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public BigDecimal getOtherFee() {
		return otherFee;
	}

	public void setOtherFee(BigDecimal otherFee) {
		this.otherFee = otherFee;
	}

	public BigDecimal getCash() {
		return cash;
	}

	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}

	public BigDecimal getPoint() {
		return point;
	}

	public void setPoint(BigDecimal point) {
		this.point = point;
	}

	public BigDecimal getPointNum() {
		return pointNum;
	}

	public void setPointNum(BigDecimal pointNum) {
		this.pointNum = pointNum;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<RefundParamVo> getRefundParamVoList() {
		return refundParamVoList;
	}

	public void setRefundParamVoList(List<RefundParamVo> refundParamVoList) {
		this.refundParamVoList = refundParamVoList;
	}
	
}
