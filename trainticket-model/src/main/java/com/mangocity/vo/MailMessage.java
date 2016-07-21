package com.mangocity.vo;

import java.io.Serializable;
import java.util.List;

public class MailMessage implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -6391409569988331479L;

	private String orderCn;
	
	private String allPrice;
	
	private String company;//公司
	
	private String branch;//公司分部
	
	private String operater;//操作者
	
	private String telephone;//电话
	
	private String reasonCode;//原因代码
	
	private String costCenter;//成本中心
	
	private List<MailTicket> ticketList;

	public String getOrderCn() {
		return orderCn;
	}

	public void setOrderCn(String orderCn) {
		this.orderCn = orderCn;
	}

	public String getAllPrice() {
		return allPrice;
	}

	public void setAllPrice(String allPrice) {
		this.allPrice = allPrice;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getOperater() {
		return operater;
	}

	public void setOperater(String operater) {
		this.operater = operater;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public List<MailTicket> getTicketList() {
		return ticketList;
	}

	public void setTicketList(List<MailTicket> ticketList) {
		this.ticketList = ticketList;
	}

	
}
