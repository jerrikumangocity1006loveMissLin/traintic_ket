package com.mangocity.vo;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.mangocity.constant.SeatTypeMap;

public class MailTicket implements Serializable{
	
	private static final long serialVersionUID = 123382802582093555L;

	/**
	 * 
	 */

	private String startDate;
	
	private String startTime;
	
	private String origStationName;
	
	private String destStationName;
	
	private String trainCn;
	
	private String endDate;
	
	private String endTime;
	
	private String trainType;
	
	private String cxin;
	
	private String name;
	
	private String price;
	
	private String seatType;
	
	private String fee;//服务费
	
	private String no;//序号

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getOrigStationName() {
		return origStationName;
	}

	public void setOrigStationName(String origStationName) {
		this.origStationName = origStationName;
	}

	public String getDestStationName() {
		return destStationName;
	}

	public void setDestStationName(String destStationName) {
		this.destStationName = destStationName;
	}

	public String getTrainCn() {
		return trainCn;
	}

	public void setTrainCn(String trainCn) {
		this.trainCn = trainCn;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTrainType() {
		return trainType;
	}

	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}

	public String getCxin() {
		return cxin;
	}

	public void setCxin(String cxin) {
		this.cxin = cxin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSeatType() {
		if(StringUtils.isNotBlank(seatType)){
			seatType = SeatTypeMap.seatTypeProvider().get("seatType");
		}
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	
	
}
