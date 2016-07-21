package com.mangocity.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 申请人
 * @author lanlonghui
 *
 */
public class Applicant implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2753975336532100808L;

	private Long id;//联系人ID
	
	private String name;//姓名
	
	private String telephone;//手机号
	
	private String telType;//是否境外手机，0=否，1=是
	
	private String mobile;//电话
	
	private String operator;//操作者
	
	private Date createTime;//创建时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getTelType() {
		return telType;
	}

	public void setTelType(String telType) {
		this.telType = telType;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Applicant [id=" + id + ", name=" + name + ", telephone="
				+ telephone + ", telType=" + telType + ", mobile=" + mobile
				+ ", operator=" + operator + ", createTime=" + createTime + "]";
	}

}
