package com.mangocity.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 配送
 * 
 * @author lanlonghui
 *
 */
public class Delivery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8799370042597480044L;

	private Long id;// 配送ID

	private Date createTime;// 创建时间

	private String deliveryCn;// 配送单号

	private String type;// 配送类型

	private String status;// 配送状态

	private BigDecimal fee;// 配送费用

	private String city;// 配送城市

	private String address;// 配送地址

	private Date deliveryTime;// 配送开始时间

	private Long orderItemId;//订单项Id

	private Date updateTime;

	public Delivery() {
		super();
	}

	public Delivery(Long id, Date createTime, String deliveryCn, String type,
			String status, BigDecimal fee, String city, String address,
			Date deliveryTime, Long orderItemId, Date updateTime) {
		super();
		this.id = id;
		this.createTime = createTime;
		this.deliveryCn = deliveryCn;
		this.type = type;
		this.status = status;
		this.fee = fee;
		this.city = city;
		this.address = address;
		this.deliveryTime = deliveryTime;
		this.orderItemId = orderItemId;
		this.updateTime = updateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeliveryCn() {
		return deliveryCn;
	}

	public void setDeliveryCn(String deliveryCn) {
		this.deliveryCn = deliveryCn;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "Delivery [id=" + id + ", createTime=" + createTime
				+ ", deliveryCn=" + deliveryCn + ", type=" + type + ", status="
				+ status + ", fee=" + fee + ", city=" + city + ", address="
				+ address + ", deliveryTime=" + deliveryTime + ", orderItemId="
				+ orderItemId + ", updateTime=" + updateTime + "]";
	}

}
