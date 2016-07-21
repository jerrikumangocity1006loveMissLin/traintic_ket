package com.mangocity.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 保险
 * 
 * @author lanlonghui
 *
 */
public class Insurance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8283215502270416100L;

	private Long id;// 保险ID

	private Long ticketId;// 火车票ID

	private String type;// 保险类型

	private Integer num;// 保险份数

	private String descp;// 保险说明

	private String status;// 投保状态

	private BigDecimal price;// 销售价

	private Date createTime;// 创建时间

	public Insurance() {
		super();
	}

	public Insurance(Long id, Long ticketId, String type, Integer num, String descp, String status, BigDecimal price,
			Date createTime) {
		super();
		this.id = id;
		this.ticketId = ticketId;
		this.type = type;
		this.num = num;
		this.descp = descp;
		this.status = status;
		this.price = price;
		this.createTime = createTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getDescp() {
		return descp;
	}

	public void setDescp(String descp) {
		this.descp = descp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Insurance [id=" + id + ", ticketId=" + ticketId + ", type=" + type + ", num=" + num + ", descp=" + descp
				+ ", status=" + status + ", price=" + price + ", createTime=" + createTime + "]";
	}
}
