package com.mangocity.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.alibaba.fastjson.annotation.JSONField;

public class TrainGqOrderMxVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4000282238798277655L;

	private String passenger;//乘坐人姓名
	private String idNo;//证件号码
	private String departStation;//出发站
	private String arriveStation;//到达站
	private String oldTrainNo;//旧车次号
	private String oldDepartTime;//旧出发时间
	private String oldArriveTime;//	旧到达时间
	private String oldSeatType;//座位类型：9:商务座，P:特等座，M:一等座，O:二等座，6:高级软卧，4:软卧，3:硬卧，2:软座，1:硬座
	private String oldTkno;//旧票号
	private BigDecimal oldPrice;//旧票价
	private String oldSeatNo;//旧座位号
	private String newTrainNo;//新车次号
	private String newDepartTime;//新出发时间
	private String newArriveTime;//新到达时间
	private String newSeatType;//座位类型：9:商务座，P:特等座，M:一等座，O:二等座，6:高级软卧，4:软卧，3:硬卧，2:软座，1:硬座
	private String newTkno;//新票号
	private BigDecimal newPrice;//新价格
	private String newSeatNo;//新座位号
	private BigDecimal changeFee;//改签费
	public String getPassenger() {
		return passenger;
	}
	@JSONField
	public void setPassenger(String passenger) {
		this.passenger = passenger;
	}
	public String getIdNo() {
		return idNo;
	}
	@JSONField
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getDepartStation() {
		return departStation;
	}
	@JSONField
	public void setDepartStation(String departStation) {
		this.departStation = departStation;
	}
	public String getArriveStation() {
		return arriveStation;
	}
	@JSONField
	public void setArriveStation(String arriveStation) {
		this.arriveStation = arriveStation;
	}
	public String getOldTrainNo() {
		return oldTrainNo;
	}
	@JSONField
	public void setOldTrainNo(String oldTrainNo) {
		this.oldTrainNo = oldTrainNo;
	}
	public String getOldDepartTime() {
		return oldDepartTime;
	}
	@JSONField
	public void setOldDepartTime(String oldDepartTime) {
		this.oldDepartTime = oldDepartTime;
	}
	public String getOldArriveTime() {
		return oldArriveTime;
	}
	@JSONField
	public void setOldArriveTime(String oldArriveTime) {
		this.oldArriveTime = oldArriveTime;
	}
	public String getOldSeatType() {
		return oldSeatType;
	}
	@JSONField
	public void setOldSeatType(String oldSeatType) {
		this.oldSeatType = oldSeatType;
	}
	public String getOldTkno() {
		return oldTkno;
	}
	@JSONField
	public void setOldTkno(String oldTkno) {
		this.oldTkno = oldTkno;
	}
	public BigDecimal getOldPrice() {
		return oldPrice;
	}
	@JSONField
	public void setOldPrice(BigDecimal oldPrice) {
		this.oldPrice = oldPrice;
	}
	public String getOldSeatNo() {
		return oldSeatNo;
	}
	@JSONField
	public void setOldSeatNo(String oldSeatNo) {
		this.oldSeatNo = oldSeatNo;
	}
	public String getNewTrainNo() {
		return newTrainNo;
	}
	@JSONField
	public void setNewTrainNo(String newTrainNo) {
		this.newTrainNo = newTrainNo;
	}
	public String getNewDepartTime() {
		return newDepartTime;
	}
	@JSONField
	public void setNewDepartTime(String newDepartTime) {
		this.newDepartTime = newDepartTime;
	}
	public String getNewArriveTime() {
		return newArriveTime;
	}
	@JSONField
	public void setNewArriveTime(String newArriveTime) {
		this.newArriveTime = newArriveTime;
	}
	public String getNewSeatType() {
		return newSeatType;
	}
	@JSONField
	public void setNewSeatType(String newSeatType) {
		this.newSeatType = newSeatType;
	}
	public String getNewTkno() {
		return newTkno;
	}
	@JSONField
	public void setNewTkno(String newTkno) {
		this.newTkno = newTkno;
	}
	public BigDecimal getNewPrice() {
		return newPrice;
	}
	@JSONField
	public void setNewPrice(BigDecimal newPrice) {
		this.newPrice = newPrice;
	}
	public String getNewSeatNo() {
		return newSeatNo;
	}
	@JSONField
	public void setNewSeatNo(String newSeatNo) {
		this.newSeatNo = newSeatNo;
	}
	public BigDecimal getChangeFee() {
		return changeFee;
	}
	@JSONField
	public void setChangeFee(BigDecimal changeFee) {
		this.changeFee = changeFee;
	}
	
}
