package com.mangocity.vo;

import java.io.Serializable;

public class PassStationVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3142845163081589538L;

	private String trainCode;//车次
	
	private String trainNo;//列车号
	
	private String startTime;//	发车时间
	
	private String fromStationCode;//始发站编号
	
	private String toStationCode;//到达站编号

	public String getTrainCode() {
		return trainCode;
	}

	public void setTrainCode(String trainCode) {
		this.trainCode = trainCode;
	}

	public String getTrainNo() {
		return trainNo;
	}

	public void setTrainNo(String trainNo) {
		this.trainNo = trainNo;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getFromStationCode() {
		return fromStationCode;
	}

	public void setFromStationCode(String fromStationCode) {
		this.fromStationCode = fromStationCode;
	}

	public String getToStationCode() {
		return toStationCode;
	}

	public void setToStationCode(String toStationCode) {
		this.toStationCode = toStationCode;
	}

	@Override
	public String toString() {
		return "PassStationVo [trainCode=" + trainCode + ", trainNo=" + trainNo
				+ ", startTime=" + startTime + ", fromStationCode="
				+ fromStationCode + ", toStationCode=" + toStationCode + "]";
	}
	
	
}
