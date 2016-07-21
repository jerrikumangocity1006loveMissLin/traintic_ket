package com.mangocity.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 服务费
 * @author lanlonghui
 *
 */
public class Charge implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -138303531479740099L;

	private Long id;//服务费ID
	
	private String name;//服务费名称
	
	private BigDecimal price;//销售价
	
	private String type;//服务类型
	
	private Date createTime;//创建时间
	
	private Long orderItemId;//订购项ID

	public Charge() {
		super();
	}

	public Charge(Long id, String name, BigDecimal price, String type,
			Date createTime, Long orderItemId) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.type = type;
		this.createTime = createTime;
		this.orderItemId = orderItemId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}

	@Override
	public String toString() {
		return "Charge [id=" + id + ", name=" + name + ", price=" + price
				+ ", type=" + type + ", createTime=" + createTime
				+ ", orderItemId=" + orderItemId + "]";
	}

}
