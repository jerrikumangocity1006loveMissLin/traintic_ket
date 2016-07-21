package com.mangocity.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.mangocity.vo.TrainSeatVo;
/**
 * 车次信息
 * @author lanlonghui
 *
 */
public class TrainDataVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9171844065620509599L;
	private String saleDateTime;//车票开售时间
	private String canBuyNow;//当前是否可以接受预定（Y:可以，N:不可以）
	private Integer arriveDays;//列车从出发站到达目的站的运行天数      0：当日到达，1：次日到达，2：三日到达，3：四日到达，依此类推
	private String trainStartDate;//列车从始发站出发的日期
	private String trainCode;//车次
	private String trainNo;//列车号
	private String accessByidcard;//是否可凭二代身份证直接进出站
	private String trainType;//列车类型（）
	private String fromStationName;//出发车站名
	private String fromStationCode;//出发车站简码
	private String toStationName;//到达车站名
	private String toStationCode;//到达车站简码
	private String startStationName;//始发站站名
	private String endStationName;//终点站站名
	private String startTime;//出发时间
	private String arriveTime;//到达时刻
	private String runTime;//历时（从出发站到目的站的列车运行时间）
	private Integer runTimeMinute;//历时分钟合计
	private List<TrainSeatVo> trainSeatList;//座位类型列表
	
	public String getSaleDateTime() {
		return saleDateTime;
	}
	@JSONField(name="sdt")
	public void setSaleDateTime(String saleDateTime) {
		this.saleDateTime = saleDateTime;
	}
	public String getCanBuyNow() {
		return canBuyNow;
	}
	@JSONField(name="cbn")
	public void setCanBuyNow(String canBuyNow) {
		this.canBuyNow = canBuyNow;
	}
	public Integer getArriveDays() {
		return arriveDays;
	}
	@JSONField(name="ard")
	public void setArriveDays(Integer arriveDays) {
		this.arriveDays = arriveDays;
	}
	public String getTrainStartDate() {
		return trainStartDate;
	}
	@JSONField(name="tsd")
	public void setTrainStartDate(String trainStartDate) {
		this.trainStartDate = trainStartDate;
	}
	public String getTrainCode() {
		return trainCode;
	}
	@JSONField(name="trc")
	public void setTrainCode(String trainCode) {
		this.trainCode = trainCode;
	}
	public String getTrainNo() {
		return trainNo;
	}
	@JSONField(name="tno")
	public void setTrainNo(String trainNo) {
		this.trainNo = trainNo;
	}
	public String getAccessByidcard() {
		return accessByidcard;
	}
	@JSONField(name="abc")
	public void setAccessByidcard(String accessByidcard) {
		this.accessByidcard = accessByidcard;
	}
	public String getTrainType() {
		return trainType;
	}
	@JSONField(name="ttp")
	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}
	public String getFromStationName() {
		return fromStationName;
	}
	@JSONField(name="fsn")
	public void setFromStationName(String fromStationName) {
		this.fromStationName = fromStationName;
	}
	public String getFromStationCode() {
		return fromStationCode;
	}
	@JSONField(name="fsc")
	public void setFromStationCode(String fromStationCode) {
		this.fromStationCode = fromStationCode;
	}
	public String getToStationName() {
		return toStationName;
	}
	@JSONField(name="tsn")
	public void setToStationName(String toStationName) {
		this.toStationName = toStationName;
	}
	public String getToStationCode() {
		return toStationCode;
	}
	@JSONField(name="tsc")
	public void setToStationCode(String toStationCode) {
		this.toStationCode = toStationCode;
	}
	public String getStartStationName() {
		return startStationName;
	}
	@JSONField(name="ssn")
	public void setStartStationName(String startStationName) {
		this.startStationName = startStationName;
	}
	public String getEndStationName() {
		return endStationName;
	}
	@JSONField(name="esn")
	public void setEndStationName(String endStationName) {
		this.endStationName = endStationName;
	}
	
	public String getStartTime() {
		return startTime;
	}
	@JSONField(name="stt")
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getArriveTime() {
		return arriveTime;
	}
	@JSONField(name="art")
	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}
	public String getRunTime() {
		return runTime;
	}
	@JSONField(name="rt")
	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}
	public Integer getRunTimeMinute() {
		return runTimeMinute;
	}
	@JSONField(name="rtm")
	public void setRunTimeMinute(Integer runTimeMinute) {
		this.runTimeMinute = runTimeMinute;
	}
	public List<TrainSeatVo> getTrainSeatList() {
		return trainSeatList;
	}
	@JSONField(name="zws")
	public void setTrainSeatList(List<TrainSeatVo> trainSeatList) {
		this.trainSeatList = trainSeatList;
	}
}
