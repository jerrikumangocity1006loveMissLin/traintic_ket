package com.mangocity.model;

import java.io.Serializable;
import java.util.Date;

public class MessageReceiver implements Serializable {

	private static final long serialVersionUID = -2838914258118785649L;
	private Long id;
	private String receiver;// 信息接受者名称
	private String mobile;// 手机
	private String mail;// 邮箱
	private Date createTime;// 创建时间
	private Long orderId;// 订单Id
	private String isSend;// 消息是否已发送
	private String affirm;// 是否接收确认短信 Y/N
	private String issut;// 是否接收出票短信 Y/N
	private String approveMail;// 是否接收收审批邮件 Y/N
	private String tripMail;// 是否接收行程单邮件 Y/N
	private String sendMode;// 邮件发送方式 zs=主送 cc=抄送
	private String memberCd;//会籍编号
	private String identity;//身份 member-会员  passenger-乘客   secretary-秘书  linkman-联系人

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getIsSend() {
		return isSend;
	}

	public void setIsSend(String isSend) {
		this.isSend = isSend;
	}

	public String getAffirm() {
		return affirm;
	}

	public void setAffirm(String affirm) {
		this.affirm = affirm;
	}

	public String getIssut() {
		return issut;
	}

	public void setIssut(String issut) {
		this.issut = issut;
	}

	public String getApproveMail() {
		return approveMail;
	}

	public void setApproveMail(String approveMail) {
		this.approveMail = approveMail;
	}

	public String getTripMail() {
		return tripMail;
	}

	public void setTripMail(String tripMail) {
		this.tripMail = tripMail;
	}

	public String getSendMode() {
		return sendMode;
	}

	public void setSendMode(String sendMode) {
		this.sendMode = sendMode;
	}

	public String getMemberCd() {
		return memberCd;
	}

	public void setMemberCd(String memberCd) {
		this.memberCd = memberCd;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	@Override
	public String toString() {
		return "MessageReceiver [id=" + id + ", receiver=" + receiver + ", mobile=" + mobile + ", mail=" + mail
				+ ", createTime=" + createTime + ", orderId=" + orderId + ", isSend=" + isSend + ", affirm=" + affirm
				+ ", issut=" + issut + ", approveMail=" + approveMail + ", tripMail=" + tripMail + ", sendMode="
				+ sendMode + ", memberCd=" + memberCd + ", identity=" + identity + "]";
	}

	
}
