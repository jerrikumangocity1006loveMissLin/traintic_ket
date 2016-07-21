package com.mangocity.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class BookTrainInfoVo implements Serializable {

	private static final long serialVersionUID = -6550804342882843957L;

	private String routeType;// 行程类型

	private String memberCd;// 会籍编号

	private String travelType;// 出行性质

	private String trainCn;// 车次

	private String origStation;// 出发站

	private String origStationName;// 出发站名称

	private String destStation;// 到达站

	private String destStationName;// 到达站名称

	private Date startTime;// 乘车日期

	private Date endTime;// 到达日期

	private String ddsj;// 到达时间

	private String ccsj;// 乘车时间

	private List<TrainSeatVo> trainSeats;// 预定车次座位信息

	public String getTrainCn() {
		return trainCn;
	}

	public void setTrainCn(String trainCn) {
		this.trainCn = trainCn;
	}

	public String getOrigStation() {
		return origStation;
	}

	public void setOrigStation(String origStation) {
		this.origStation = origStation;
	}

	public String getOrigStationName() {
		return origStationName;
	}

	public void setOrigStationName(String origStationName) {
		this.origStationName = origStationName;
	}

	public String getDestStation() {
		return destStation;
	}

	public void setDestStation(String destStation) {
		this.destStation = destStation;
	}

	public String getDestStationName() {
		return destStationName;
	}

	public void setDestStationName(String destStationName) {
		this.destStationName = destStationName;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getDdsj() {
		return ddsj;
	}

	public void setDdsj(String ddsj) {
		this.ddsj = ddsj;
	}

	public String getCcsj() {
		return ccsj;
	}

	public void setCcsj(String ccsj) {
		this.ccsj = ccsj;
	}

	public String getRouteType() {
		return routeType;
	}

	public void setRouteType(String routeType) {
		this.routeType = routeType;
	}

	public String getMemberCd() {
		return memberCd;
	}

	public void setMemberCd(String memberCd) {
		this.memberCd = memberCd;
	}

	public String getTravelType() {
		return travelType;
	}

	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}

	public List<TrainSeatVo> getTrainSeats() {
		return trainSeats;
	}

	public void setTrainSeats(List<TrainSeatVo> trainSeats) {
		this.trainSeats = trainSeats;
	}

}
