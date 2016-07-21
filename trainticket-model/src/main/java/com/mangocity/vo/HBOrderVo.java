package com.mangocity.vo;

import java.util.List;

/**
 * @author lizhi
 *
 * @date 2016年5月30日
 */
public class HBOrderVo {

	private String businessId;
	private String requestNo;
	private String bxfs;// 保险份数
	private String bxlx;// 保险类型
	private String checi;//车次
	private String cllx;// 差旅类型1因公2因私s
	private String ddDate;//到达日期
	private String ddTime;//到达时间
	private String fromStationCode;//始发站编码
	private String fromStationName;//始发站名称
	private String lxr;//联系人
	private String lxrMail;//联系人邮箱
	private String lxrsj;//联系人手机
	private String qqly;//请求来源
	private String spgzid;//审批规则id
	private String toStationCode;//到达站编码
	private String toStationName;//到达站名称
	private String trainDate;//出发日期
	private String trainTime;//出发时间
	private List<PassengerVo> passengers;//乘客

	public HBOrderVo(String checi, String fromStationCode, String fromStationName, String toStationCode,
			String toStationName, String ddDate, String ddTime, String trainDate, String trainTime, String lxr,
			String lxrsj, List<PassengerVo> passengers,String qqly) {
		this.checi = checi;
		this.fromStationCode = fromStationCode;
		this.fromStationName = fromStationName;
		this.toStationCode = toStationCode;
		this.toStationName = toStationName;
		this.ddDate = ddDate;
		this.ddTime = ddTime;
		this.trainDate = trainDate;
		this.trainTime = trainTime;
		this.lxr = lxr;
		this.lxrsj = lxrsj;
		this.passengers = passengers;
		this.qqly = qqly;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}

	public String getBxfs() {
		return bxfs;
	}

	public void setBxfs(String bxfs) {
		this.bxfs = bxfs;
	}

	public String getBxlx() {
		return bxlx;
	}

	public void setBxlx(String bxlx) {
		this.bxlx = bxlx;
	}

	public String getCheci() {
		return checi;
	}

	public void setCheci(String checi) {
		this.checi = checi;
	}

	public String getCllx() {
		return cllx;
	}

	public void setCllx(String cllx) {
		this.cllx = cllx;
	}

	public String getDdDate() {
		return ddDate;
	}

	public void setDdDate(String ddDate) {
		this.ddDate = ddDate;
	}

	public String getDdTime() {
		return ddTime;
	}

	public void setDdTime(String ddTime) {
		this.ddTime = ddTime;
	}

	public String getFromStationCode() {
		return fromStationCode;
	}

	public void setFromStationCode(String fromStationCode) {
		this.fromStationCode = fromStationCode;
	}

	public String getFromStationName() {
		return fromStationName;
	}

	public void setFromStationName(String fromStationName) {
		this.fromStationName = fromStationName;
	}

	public String getLxr() {
		return lxr;
	}

	public void setLxr(String lxr) {
		this.lxr = lxr;
	}

	public String getLxrMail() {
		return lxrMail;
	}

	public void setLxrMail(String lxrMail) {
		this.lxrMail = lxrMail;
	}

	public String getLxrsj() {
		return lxrsj;
	}

	public void setLxrsj(String lxrsj) {
		this.lxrsj = lxrsj;
	}

	public String getQqly() {
		return qqly;
	}

	public void setQqly(String qqly) {
		this.qqly = qqly;
	}

	public String getSpgzid() {
		return spgzid;
	}

	public void setSpgzid(String spgzid) {
		this.spgzid = spgzid;
	}

	public String getToStationCode() {
		return toStationCode;
	}

	public void setToStationCode(String toStationCode) {
		this.toStationCode = toStationCode;
	}

	public String getToStationName() {
		return toStationName;
	}

	public void setToStationName(String toStationName) {
		this.toStationName = toStationName;
	}

	public String getTrainDate() {
		return trainDate;
	}

	public void setTrainDate(String trainDate) {
		this.trainDate = trainDate;
	}

	public String getTrainTime() {
		return trainTime;
	}

	public void setTrainTime(String trainTime) {
		this.trainTime = trainTime;
	}

	public List<PassengerVo> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<PassengerVo> passengers) {
		this.passengers = passengers;
	}

	
}
