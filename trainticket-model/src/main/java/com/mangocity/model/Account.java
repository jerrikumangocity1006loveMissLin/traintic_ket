package com.mangocity.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 12306账号实体
 * 
 * @author lizhi
 *
 * @2016年6月14日
 */
public class Account implements Serializable {

	private static final long serialVersionUID = -7703494723656276062L;
	private Long id;
	private String userName;// 12306账号
	private String password;// 12306密码
	private String type;// 类型 0-公有 1-私有 2-统一
	private String accountStatus;// 在12306的状态
	private Date createTime;// 创建时间
	private Date updateTime;// 更新时间

	public Account() {
		super();
	}

	public Account(Long id, String userName, String password, String type, String accountStatus, Date createTime) {
		this.userName = userName;
		this.accountStatus = accountStatus;
		this.createTime = createTime;
		this.id = id;
		this.password = password;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", userName=" + userName + ", password=" + password + ", type=" + type
				+ ", accountStatus=" + accountStatus + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "]";
	}

}
