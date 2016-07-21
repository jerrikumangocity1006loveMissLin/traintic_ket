/**
 * 
 */
package com.mangocity.vo;

import java.io.Serializable;

/**
 * @author lizhi
 *
 * @date 2016年5月30日
 */
public class PassengerVo implements Serializable {

	private static final long serialVersionUID = 2689131610019627210L;
	private Long passengerId;
	private String passengerName;
	private String passportNo;// 证件号码
	private String passportTypeId;// 证件类型
	private String piaoType;// 票种 --1成人，2儿童，3学生，4残军
	private String zwCode;
	private String price;
	private String cxin;
	private String fee;
	private String username;// 12306账号
	private String password;// 12306密码
	private String accountType;// 12306账号类型 0-公有 1-私有 2-统一
	private Long accountId;// 12306表主键
	private String phone;

	public Long getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(Long passengerId) {
		this.passengerId = passengerId;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public String getPassportTypeId() {
		return passportTypeId;
	}

	public void setPassportTypeId(String passportTypeId) {
		this.passportTypeId = passportTypeId;
	}

	public String getPiaoType() {
		return piaoType;
	}

	public void setPiaoType(String piaoType) {
		this.piaoType = piaoType;
	}

	public String getZwCode() {
		return zwCode;
	}

	public void setZwCode(String zwCode) {
		this.zwCode = zwCode;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCxin() {
		return cxin;
	}

	public void setCxin(String cxin) {
		this.cxin = cxin;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
