package com.mangocity.vo;

import java.io.Serializable;
import java.util.List;

public class FrequentTravels implements Serializable {
	private static final long serialVersionUID = 4394387300272894244L;
	private String userName;// 12306账号
	private String password;// 12306密码
	private List<DeleteAccountTravellerVo> trainDeletePassengers;

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

	public List<DeleteAccountTravellerVo> getTrainDeletePassengers() {
		return trainDeletePassengers;
	}

	public void setTrainDeletePassengers(List<DeleteAccountTravellerVo> trainDeletePassengers) {
		this.trainDeletePassengers = trainDeletePassengers;
	}

}
