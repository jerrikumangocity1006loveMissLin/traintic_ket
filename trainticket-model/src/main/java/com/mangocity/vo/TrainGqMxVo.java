package com.mangocity.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.alibaba.fastjson.annotation.JSONField;
/**
 * 改签单明细
 * @author lanlonghui
 *
 */
public class TrainGqMxVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7869380700332184759L;
	
	private String oldSeatType;//原座位类型名称
	private String newSeatType;//新座位类型名称
	private String passenger;//乘坐人
	private String passengerId;//乘坐人常旅客ID
	private String passengerType;//乘坐人类型名称
	private String certType;//证件类型名称
	private String certNo;//证件号码
	private String oldTicketNo;//原票号
	private String newTicketNo;//新票号
	private BigDecimal oldTicketPrice;//原票价
	private BigDecimal newTicketPrice;//新票价
	private String oldSeatNo;//原座位号
	private String newSeatNo;//新座位号
	private String flag;//是否退票
	public String getOldSeatType() {
		return oldSeatType;
	}
	@JSONField(name="ozl")
	public void setOldSeatType(String oldSeatType) {
		this.oldSeatType = oldSeatType;
	}
	public String getNewSeatType() {
		return newSeatType;
	}
	@JSONField(name="nzl")
	public void setNewSeatType(String newSeatType) {
		this.newSeatType = newSeatType;
	}
	public String getPassenger() {
		return passenger;
	}
	@JSONField(name="czr")
	public void setPassenger(String passenger) {
		this.passenger = passenger;
	}
	public String getPassengerId() {
		return passengerId;
	}
	@JSONField(name="pid")
	public void setPassengerId(String passengerId) {
		this.passengerId = passengerId;
	}
	public String getPassengerType() {
		return passengerType;
	}
	@JSONField(name="czm")
	public void setPassengerType(String passengerType) {
		this.passengerType = passengerType;
	}
	public String getCertType() {
		return certType;
	}
	@JSONField(name="zjm")
	public void setCertType(String certType) {
		this.certType = certType;
	}
	public String getCertNo() {
		return certNo;
	}
	@JSONField(name="zjh")
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	public String getOldTicketNo() {
		return oldTicketNo;
	}
	@JSONField(name="otn")
	public void setOldTicketNo(String oldTicketNo) {
		this.oldTicketNo = oldTicketNo;
	}
	public String getNewTicketNo() {
		return newTicketNo;
	}
	@JSONField(name="ntn")
	public void setNewTicketNo(String newTicketNo) {
		this.newTicketNo = newTicketNo;
	}
	public BigDecimal getOldTicketPrice() {
		return oldTicketPrice;
	}
	@JSONField(name="opj")
	public void setOldTicketPrice(BigDecimal oldTicketPrice) {
		this.oldTicketPrice = oldTicketPrice;
	}
	public BigDecimal getNewTicketPrice() {
		return newTicketPrice;
	}
	@JSONField(name="npj")
	public void setNewTicketPrice(BigDecimal newTicketPrice) {
		this.newTicketPrice = newTicketPrice;
	}
	public String getOldSeatNo() {
		return oldSeatNo;
	}
	@JSONField(name="oci")
	public void setOldSeatNo(String oldSeatNo) {
		this.oldSeatNo = oldSeatNo;
	}
	public String getNewSeatNo() {
		return newSeatNo;
	}
	@JSONField(name="nci")
	public void setNewSeatNo(String newSeatNo) {
		this.newSeatNo = newSeatNo;
	}
	public String getFlag() {
		return flag;
	}
	@JSONField(name="stp")
	public void setFlag(String flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "TrainGqMxVo [oldSeatType=" + oldSeatType + ", newSeatType="
				+ newSeatType + ", passenger=" + passenger + ", passengerId="
				+ passengerId + ", passengerType=" + passengerType
				+ ", certType=" + certType + ", certNo=" + certNo
				+ ", oldTicketNo=" + oldTicketNo + ", newTicketNo="
				+ newTicketNo + ", oldTicketPrice=" + oldTicketPrice
				+ ", newTicketPrice=" + newTicketPrice + ", oldSeatNo="
				+ oldSeatNo + ", newSeatNo=" + newSeatNo + ", flag=" + flag
				+ "]";
	}
	
	

}
