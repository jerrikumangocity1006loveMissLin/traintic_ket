/**
 * 
 */
package com.mangocity.vo;

import java.util.List;

/**
 * @author lizhi
 *
 * @date 2016年5月30日
 */
public class HBTicketVo {
	private String businessId;//
	private String requestNo;// 请求流水号
	private String newCch;// 新车次
	private String cfDateNew;// 出发日期
	private String cfTimeNew;// 出发时间
	private String ddDateNew;// 达到日期
	private String ddTimeNew;// 到达时间
	private String qqly;// 请求来源
	private String memberId;// 会员id
	private String loginUserId;// 常旅客id
	private String lxrEmail;// 联系人邮箱
	private String lxr;// 联系人
	private String lxrsj;// 联系人电话
	private String parentTicketId;// 被改签车票Id
	// private String trainType;//
	private String zwlx;// 座位类型
	private String orderNo;
	private List orig_tickets;

	/*
	 * public String getTrainType() { return trainType; }
	 * 
	 * public void setTrainType(String trainType) { this.trainType = trainType;
	 * }
	 */

	public String getZwlx() {
		return zwlx;
	}

	public void setZwlx(String zwlx) {
		this.zwlx = zwlx;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}

	public String getCfDateNew() {
		return cfDateNew;
	}

	public void setCfDateNew(String cfDateNew) {
		this.cfDateNew = cfDateNew;
	}

	public String getCfTimeNew() {
		return cfTimeNew;
	}

	public void setCfTimeNew(String cfTimeNew) {
		this.cfTimeNew = cfTimeNew;
	}

	public String getDdDateNew() {
		return ddDateNew;
	}

	public void setDdDateNew(String ddDateNew) {
		this.ddDateNew = ddDateNew;
	}

	public String getDdTimeNew() {
		return ddTimeNew;
	}

	public void setDdTimeNew(String ddTimeNew) {
		this.ddTimeNew = ddTimeNew;
	}

	public String getQqly() {
		return qqly;
	}

	public void setQqly(String qqly) {
		this.qqly = qqly;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}

	public String getLxrEmail() {
		return lxrEmail;
	}

	public void setLxrEmail(String lxrEmail) {
		this.lxrEmail = lxrEmail;
	}

	public String getLxr() {
		return lxr;
	}

	public void setLxr(String lxr) {
		this.lxr = lxr;
	}

	public String getLxrsj() {
		return lxrsj;
	}

	public void setLxrsj(String lxrsj) {
		this.lxrsj = lxrsj;
	}

	public String getParentTicketId() {
		return parentTicketId;
	}

	public void setParentTicketId(String parentTicketId) {
		this.parentTicketId = parentTicketId;
	}

	public String getNewCch() {
		return newCch;
	}

	public void setNewCch(String newCch) {
		this.newCch = newCch;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

}
