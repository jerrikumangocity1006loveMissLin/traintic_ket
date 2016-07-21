package com.mangocity.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 订单
 * 
 * @author lanlonghui
 *
 */
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2997676210540094235L;

	private Long id;// 订单ID

	private String orderCn;// 订单号

	private String status;// 订单状态

	private String memberCn;// 会员号

	private String orderFrom;// 订单来源

	private String companyCode;// 公司代码

	private String bookMember;// 预订会员

	private Date createTime;// 创建时间

	private String costCenter;// 成本中心

	private Date updateTime;// 更新时间

	private String travelType;// 出行性质

	private String projectCode;// 项目代码

	private String paymentStatus;// 支付状态

	private String orderMethod;// 预订方式

	private String orderChannel;// 预订渠道

	private String outMethod;// 出票方式

	private String orderAudit;// 审批

	private String frontRemark;// 前台备注

	private String middRemark;// 中台备注

	private String routeType;// 行程类型 0-单程 1-往返

	private String settlement;// 结算单位

	private String department;// 部门

	private String paymentMethod;// 付款方式 0- 1-

	private int isNeedAudit;

	public Order(Long id, String orderCn, String status, String memberCn, String orderFrom, String companyCode,
			String bookMember, Date createTime, String costCenter, Date updateTime, String travelType,
			String projectCode, String settlement, String department) {
		super();
		this.id = id;
		this.orderCn = orderCn;
		this.status = status;
		this.memberCn = memberCn;
		this.orderFrom = orderFrom;
		this.companyCode = companyCode;
		this.bookMember = bookMember;
		this.createTime = createTime;
		this.costCenter = costCenter;
		this.updateTime = updateTime;
		this.travelType = travelType;
		this.projectCode = projectCode;
		this.department = department;
		this.settlement = settlement;
	}

	public Order() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderCn() {
		return orderCn;
	}

	public void setOrderCn(String orderCn) {
		this.orderCn = orderCn;
	}

	public String getMemberCn() {
		return memberCn;
	}

	public void setMemberCn(String memberCn) {
		this.memberCn = memberCn;
	}

	public String getOrderFrom() {
		return orderFrom;
	}

	public void setOrderFrom(String orderFrom) {
		this.orderFrom = orderFrom;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getBookMember() {
		return bookMember;
	}

	public void setBookMember(String bookMember) {
		this.bookMember = bookMember;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getTravelType() {
		return travelType;
	}

	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getOrderMethod() {
		return orderMethod;
	}

	public void setOrderMethod(String orderMethod) {
		this.orderMethod = orderMethod;
	}

	public String getOrderChannel() {
		return orderChannel;
	}

	public void setOrderChannel(String orderChannel) {
		this.orderChannel = orderChannel;
	}

	public String getOutMethod() {
		return outMethod;
	}

	public void setOutMethod(String outMethod) {
		this.outMethod = outMethod;
	}

	public String getOrderAudit() {
		return orderAudit;
	}

	public void setOrderAudit(String orderAudit) {
		this.orderAudit = orderAudit;
	}

	public String getFrontRemark() {
		return frontRemark;
	}

	public void setFrontRemark(String frontRemark) {
		this.frontRemark = frontRemark;
	}

	public String getMiddRemark() {
		return middRemark;
	}

	public void setMiddRemark(String middRemark) {
		this.middRemark = middRemark;
	}

	public String getRouteType() {
		return routeType;
	}

	public void setRouteType(String routeType) {
		this.routeType = routeType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSettlement() {
		return settlement;
	}

	public void setSettlement(String settlement) {
		this.settlement = settlement;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public int getIsNeedAudit() {
		return isNeedAudit;
	}

	public void setIsNeedAudit(int isNeedAudit) {
		this.isNeedAudit = isNeedAudit;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderCn=" + orderCn + ", status=" + status + ", memberCn=" + memberCn
				+ ", orderFrom=" + orderFrom + ", companyCode=" + companyCode + ", bookMember=" + bookMember
				+ ", createTime=" + createTime + ", costCenter=" + costCenter + ", updateTime=" + updateTime
				+ ", travelType=" + travelType + ", projectCode=" + projectCode + ", paymentStatus=" + paymentStatus
				+ ", orderMethod=" + orderMethod + ", orderChannel=" + orderChannel + ", outMethod=" + outMethod
				+ ", orderAudit=" + orderAudit + ", frontRemark=" + frontRemark + ", middRemark=" + middRemark
				+ ", routeType=" + routeType + ", settlement=" + settlement + ", department=" + department
				+ ", paymentMethod=" + paymentMethod + "]";
	}

}
