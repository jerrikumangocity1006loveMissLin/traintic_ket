package com.mangocity.vo;

import java.io.Serializable;
import java.util.Date;

import com.mangocity.enums.OrderStatusType;

/**
 * 订单查询条件
 * 
 * @author hongxiaodong
 *
 */
public class OrderTicketQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String orderCn;// 订单号

	private String status;// 订单状态

	private String memberCn;// 会员号

	private String passengerName;// 承机人姓名

	private String orderFrom;// 订单来源

	private String companyCode;// 公司代码

	private String bookMember;// 预订会员

	private Date oderTimeStart;// 下单开始时间

	private Date oderTimeEnd;// 下单结束时间

	private Date depTimeStart;// 出发开始时间

	private Date depTimeEnd;// 出发时间

	private String mobile;// 手机号

	private String depCity;// 出发城市

	private String arrCity;// 到底城市

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

	public Date getOderTimeStart() {
		return oderTimeStart;
	}

	public void setOderTimeStart(Date oderTimeStart) {
		this.oderTimeStart = oderTimeStart;
	}

	public Date getOderTimeEnd() {
		return oderTimeEnd;
	}

	public void setOderTimeEnd(Date oderTimeEnd) {
		this.oderTimeEnd = oderTimeEnd;
	}

	public Date getDepTimeStart() {
		return depTimeStart;
	}

	public void setDepTimeStart(Date depTimeStart) {
		this.depTimeStart = depTimeStart;
	}

	public Date getDepTimeEnd() {
		return depTimeEnd;
	}

	public void setDepTimeEnd(Date depTimeEnd) {
		this.depTimeEnd = depTimeEnd;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDepCity() {
		return depCity;
	}

	public void setDepCity(String depCity) {
		this.depCity = depCity;
	}

	public String getArrCity() {
		return arrCity;
	}

	public void setArrCity(String arrCity) {
		this.arrCity = arrCity;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "OrderTicketQuery [orderCn=" + orderCn + ", status=" + status + ", memberCn=" + memberCn + ", orderFrom="
				+ orderFrom + ", companyCode=" + companyCode + ", bookMember=" + bookMember + ", oderTimeStart="
				+ oderTimeStart + ", oderTimeEnd=" + oderTimeEnd + ", depTimeStart=" + depTimeStart + ", depTimeEnd="
				+ depTimeEnd + ", mobile=" + mobile + ", depCity=" + depCity + ", arrCity=" + arrCity + "]";
	}

}
