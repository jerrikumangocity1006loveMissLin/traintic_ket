package com.mangocity.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.alibaba.fastjson.annotation.JSONField;

public class TrainChangeTicketVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4049180006318578678L;
	
	private String ticketNo;//原票号
	
	private String newTicketNo;//新票号，改签成功后返回
	
	private BigDecimal price;//票价，改签成功后返回

	public String getTicketNo() {
		return ticketNo;
	}
	@JSONField(name="otn")
	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public String getNewTicketNo() {
		return newTicketNo;
	}

	@JSONField(name="tno")
	public void setNewTicketNo(String newTicketNo) {
		this.newTicketNo = newTicketNo;
	}

	public BigDecimal getPrice() {
		return price;
	}

	@JSONField(name="pj")
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	

}
