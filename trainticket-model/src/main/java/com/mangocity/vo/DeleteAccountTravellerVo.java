package com.mangocity.vo;

import java.io.Serializable;

public class DeleteAccountTravellerVo implements Serializable {

	private static final long serialVersionUID = -1070242246381022018L;
	private String passengerName;// 联系人姓名
	private String passportId;// 证件类型
	private String passportType;// 证件号码

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getPassportId() {
		return passportId;
	}

	public void setPassportId(String passportId) {
		this.passportId = passportId;
	}

	public String getPassportType() {
		return passportType;
	}

	public void setPassportType(String passportType) {
		this.passportType = passportType;
	}

}
