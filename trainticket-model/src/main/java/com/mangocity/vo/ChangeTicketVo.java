package com.mangocity.vo;

import java.io.Serializable;
/**
 * 火车票改签申请请求参数
 *
 */
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
/**
 * 火车票改签申请请求参数
 *
 */
public class ChangeTicketVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3077718664331612488L;

	private String orderNo;//号百原订单编号
	private String newTrainCn;//新车次
	private String seatType;//座位类型
	private Date startDate;//出发日期
	private String startTime;//出发时间
	private String arriveDate;//达到日期
	private String arriveTime;//到达时间
	private String queryFrom;//请求来源： 2002209 差旅安卓终端；2002210差旅苹果终端；2002211 差旅微信
	private String memberId;//会员id
	private String loginUserId;//常旅客id
	private String contact;//联系人
	private String telephone;//联系人手机
	private String mail;//联系人邮件
	private List<TrainChangeTicketVo> ticketList;//原票信息，目前只支持单张改签
	public String getOrderNo() {
		return orderNo;
	}
	@JSONField(name="orderNo")
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getNewTrainCn() {
		return newTrainCn;
	}
	@JSONField(name="newCch")
	public void setNewTrainCn(String newTrainCn) {
		this.newTrainCn = newTrainCn;
	}
	public String getSeatType() {
		return seatType;
	}
	@JSONField(name="zwlx")
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}
	public Date getStartDate() {
		return startDate;
	}
	@JSONField(name="cfDateNew")
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getStartTime() {
		return startTime;
	}
	@JSONField(name="cfTimeNew")
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getArriveDate() {
		return arriveDate;
	}
	@JSONField(name="ddDateNew")
	public void setArriveDate(String arriveDate) {
		this.arriveDate = arriveDate;
	}
	public String getArriveTime() {
		return arriveTime;
	}
	@JSONField(name="ddTimeNew")
	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}
	public String getQueryFrom() {
		return queryFrom;
	}
	@JSONField(name="qqly")
	public void setQueryFrom(String queryFrom) {
		this.queryFrom = queryFrom;
	}
	public String getMemberId() {
		return memberId;
	}
	@JSONField(name="memberId")
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getLoginUserId() {
		return loginUserId;
	}
	@JSONField(name="loginUserId")
	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}
	public String getContact() {
		return contact;
	}
	@JSONField(name="lxr")
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getTelephone() {
		return telephone;
	}
	@JSONField(name="lxrsj")
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMail() {
		return mail;
	}
	@JSONField(name="lxrEmail")
	public void setMail(String mail) {
		this.mail = mail;
	}
	public List<TrainChangeTicketVo> getTicketList() {
		return ticketList;
	}
	@JSONField(name="ticketList")
	public void setTicketList(List<TrainChangeTicketVo> ticketList) {
		this.ticketList = ticketList;
	}
}
