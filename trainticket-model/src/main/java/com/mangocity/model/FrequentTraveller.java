package com.mangocity.model;

import java.io.Serializable;

public class FrequentTraveller implements Serializable {

	private static final long serialVersionUID = 4339535110759887741L;
	private Long id;
	private Long accountId;
	private String name;
	private String phone;
	private String certificateType;
	private String certificate;
	private String sex;
	private String passengerType;

	public FrequentTraveller() {
		super();
	}

	public FrequentTraveller(Long id, Long accountId, String name, String phone, String certificateType,
			String cerfiticate, String sex, String passengerType) {
		this.id = id;
		this.accountId = accountId;
		this.name = name;
		this.phone = phone;
		this.certificateType = certificateType;
		this.certificate = cerfiticate;
		this.sex = sex;
		this.passengerType = passengerType;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPassengerType() {
		return passengerType;
	}

	public void setPassengerType(String passengerType) {
		this.passengerType = passengerType;
	}

	@Override
	public String toString() {
		return "FrequentTraveller [id=" + id + ", accountId=" + accountId + ", name=" + name + ", phone=" + phone
				+ ", certificateType=" + certificateType + ", certificate=" + certificate + ", sex=" + sex
				+ ", passengerType=" + passengerType + "]";
	}

}
