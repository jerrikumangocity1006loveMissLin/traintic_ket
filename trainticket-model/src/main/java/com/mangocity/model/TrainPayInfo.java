package com.mangocity.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 支付会话
 * @author lizhi
 *
 * @date 2016年5月25日
 */
public class TrainPayInfo implements Serializable {
	private static final long serialVersionUID = 2262120408774148617L;
	private Long id;
	private Long orderId;
	private String orderCd;
	private String status;
	private String type;//支付类型：0付款，1退款
	private Date dateTime;
	private Date endTime;
	private Date modifyTime;//更新时间

	public TrainPayInfo() {
		super();
	}

	public TrainPayInfo(Long id, Long orderId, String orderCd, String status,
			String type, Date dateTime, Date endTime, Date modifyTime) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.orderCd = orderCd;
		this.status = status;
		this.type = type;
		this.dateTime = dateTime;
		this.endTime = endTime;
		this.modifyTime = modifyTime;
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

	public String getOrderCd() {
		return orderCd;
	}

	public void setOrderCd(String orderCd) {
		this.orderCd = orderCd;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Override
	public String toString() {
		return "TrainPayInfo [id=" + id + ", orderId=" + orderId + ", orderCd="
				+ orderCd + ", status=" + status + ", type=" + type
				+ ", dateTime=" + dateTime + ", endTime=" + endTime
				+ ", modifyTime=" + modifyTime + "]";
	}

}
