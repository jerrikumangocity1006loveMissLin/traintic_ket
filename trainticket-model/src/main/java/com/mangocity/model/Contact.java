package com.mangocity.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 联系人
 * 
 * @author lanlonghui
 *
 */
public class Contact implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1491782724545354205L;

	private Long id;// 联系人ID

	private Long orderId;// 订单ID

	private String name;// 姓名

	private String telephone;// 手机号

	private String mail;// 邮箱

	private Date createTime;// 创建时间

	private String fax;// 传真

	private String foreignMobile;

	private String isForeign;// 是否是境外手机 0-否 1-是

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getForeignMobile() {
		return foreignMobile;
	}

	public void setForeignMobile(String foreignMobile) {
		this.foreignMobile = foreignMobile;
	}

	public String getIsForeign() {
		return isForeign;
	}

	public void setIsForeign(String isForeign) {
		this.isForeign = isForeign;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", orderId=" + orderId + ", name=" + name + ", telephone=" + telephone + ", mail="
				+ mail + ", createTime=" + createTime + ", fax=" + fax + ", foreignMobile=" + foreignMobile
				+ ", isForeign=" + isForeign + "]";
	}

}
