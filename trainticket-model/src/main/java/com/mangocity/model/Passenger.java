package com.mangocity.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 乘客
 * 
 * @author lanlonghui
 *
 */
public class Passenger implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4592094253403509268L;

	private Long id;// 乘客ID

	private String name;// 乘客姓名

	private String certType;// 证件类型

	private String certCn;// 证件号

	private Date createTime;// 创建时间

	private String mobile;// 乘客手机

	public Passenger() {
		super();
	}

	public Passenger(Long id, String name, String certType, String certCn, String mobile, Date createTime) {
		super();
		this.id = id;
		this.name = name;
		this.certType = certType;
		this.certCn = certCn;
		this.mobile = mobile;
		this.createTime = createTime;
	}

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

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertCn() {
		return certCn;
	}

	public void setCertCn(String certCn) {
		this.certCn = certCn;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "Passenger [id=" + id + ", name=" + name + ", certType=" + certType + ", certCn=" + certCn
				+ ", createTime=" + createTime + ", mobile=" + mobile + "]";
	}

}
