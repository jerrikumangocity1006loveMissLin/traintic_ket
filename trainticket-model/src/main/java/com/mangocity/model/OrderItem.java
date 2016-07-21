package com.mangocity.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订购项
 * 
 * @author lanlonghui
 *
 */
public class OrderItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2800124523641460283L;

	private Long id;// 订购项ID

	private Long orderId;// 订单ID

	private String goodsType;// 商品类型
	
	private BigDecimal price;//订购项价格

	private Date orderTime;// 订购日期

	public OrderItem() {
		super();
	}

	public OrderItem(Long id, Long orderId, String goodsType, BigDecimal price,
			Date orderTime) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.goodsType = goodsType;
		this.price = price;
		this.orderTime = orderTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", orderId=" + orderId + ", goodsType="
				+ goodsType + ", price=" + price + ", orderTime=" + orderTime
				+ "]";
	}
	
}
