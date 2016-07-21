package com.mangocity.vo;

import java.io.Serializable;

import com.mangocity.model.FrequentTraveller;

public class FrequentTravellerVo extends FrequentTraveller implements Serializable {

	private static final long serialVersionUID = -2736731217343691501L;
	private String userName;// 12306账号
	private String password;// 12306密码

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

}
