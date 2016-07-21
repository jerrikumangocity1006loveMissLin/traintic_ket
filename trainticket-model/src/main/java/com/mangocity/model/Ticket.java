package com.mangocity.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 火车票
 * 
 * @author lanlonghui
 *
 */
public class Ticket implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4320135124024335639L;

	private Long id;// 火车票ID

	private Long orderItemId;// 订购项ID

	private Long passengerId;// 乘客ID

	private String routeType;// 行程类型

	private String trainCn;// 车次

	private String origStation;// 出发站

	private String origStationName;// 出发站名称

	private String destStation;// 到达站

	private String destStationName;// 到达站名称

	private Date startTime;// 乘车日期

	private Date endTime;// 到达日期

	private String trainType;// 票种  1-成人票 2-儿童票  3-学生票  4-残军票

	private String seatType;// 席别类型

	private Long ticketParentId;// 父行程ID

	private BigDecimal salePrice;// 销售价

	private BigDecimal fee;// 服务费

	private String status;// 状态

	private Date createTime;

	private Date updateTime;

	private String hbOrderId;// 号百订单编号

	private String ticketNo;// 票号

	private String cxin;// 车厢座位

	private String type;// 类型

	private String ddsj;// 到达时间

	private String ccsj;// 乘车时间

	private BigDecimal gapPrice;// 差价，小于0表示退款，大于0表示扣款

	private String bookAccount;// 预订时所绑定的12306账号

	private String requestNo;// 请求流水号

	private Long applicantId;// 申请人ID

	private String refundReason;// 退票原因

	private String refundDesc;// 退改备注

	private String verifyDesc;// 审核备注

	private String transactionId;// 号百交易编号

	private String orderNumber;// 12306票号

	private BigDecimal hbPrice;// 号百返回的价格

	private BigDecimal tmcPrice;// 商旅价

	private String hbSeatType;
	
	private String operator;//操作者

	public Ticket() {
		super();
	}

	public Ticket(Long id, Long orderItemId, Long passengerId, String routeType, String trainCn, String origStation,
			String origStationName, String destStation, String destStationName, Date startTime, Date endTime,
			String trainType, String seatType, Long ticketParentId, BigDecimal salePrice, BigDecimal fee, String status,
			Date createTime, Date updateTime, String hbOrderId, String ticketNo, String cxin, String type, String ddsj,
			String ccsj, BigDecimal gapPrice) {
		super();
		this.id = id;
		this.orderItemId = orderItemId;
		this.passengerId = passengerId;
		this.routeType = routeType;
		this.trainCn = trainCn;
		this.origStation = origStation;
		this.origStationName = origStationName;
		this.destStation = destStation;
		this.destStationName = destStationName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.trainType = trainType;
		this.seatType = seatType;
		this.ticketParentId = ticketParentId;
		this.salePrice = salePrice;
		this.fee = fee;
		this.status = status;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.hbOrderId = hbOrderId;
		this.ticketNo = ticketNo;
		this.cxin = cxin;
		this.type = type;
		this.ddsj = ddsj;
		this.ccsj = ccsj;
		this.gapPrice = gapPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Long getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(Long passengerId) {
		this.passengerId = passengerId;
	}

	public String getRouteType() {
		return routeType;
	}

	public void setRouteType(String routeType) {
		this.routeType = routeType;
	}

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

	public String getDestStation() {
		return destStation;
	}

	public void setDestStation(String destStation) {
		this.destStation = destStation;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getTrainType() {
		return trainType;
	}

	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public Long getTicketParentId() {
		return ticketParentId;
	}

	public void setTicketParentId(Long ticketParentId) {
		this.ticketParentId = ticketParentId;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getHbOrderId() {
		return hbOrderId;
	}

	public void setHbOrderId(String hbOrderId) {
		this.hbOrderId = hbOrderId;
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

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public String getCxin() {
		return cxin;
	}

	public void setCxin(String cxin) {
		this.cxin = cxin;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public BigDecimal getGapPrice() {
		return gapPrice;
	}

	public void setGapPrice(BigDecimal gapPrice) {
		this.gapPrice = gapPrice;
	}

	public String getBookAccount() {
		return bookAccount;
	}

	public void setBookAccount(String bookAccount) {
		this.bookAccount = bookAccount;
	}

	public String getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}

	public Long getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(Long applicantId) {
		this.applicantId = applicantId;
	}

	public String getRefundReason() {
		return refundReason;
	}

	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}

	public String getRefundDesc() {
		return refundDesc;
	}

	public void setRefundDesc(String refundDesc) {
		this.refundDesc = refundDesc;
	}

	public String getVerifyDesc() {
		return verifyDesc;
	}

	public void setVerifyDesc(String verifyDesc) {
		this.verifyDesc = verifyDesc;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public BigDecimal getHbPrice() {
		return hbPrice;
	}

	public void setHbPrice(BigDecimal hbPrice) {
		this.hbPrice = hbPrice;
	}

	public BigDecimal getTmcPrice() {
		return tmcPrice;
	}

	public void setTmcPrice(BigDecimal tmcPrice) {
		this.tmcPrice = tmcPrice;
	}

	public String getHbSeatType() {
		return hbSeatType;
	}

	public void setHbSeatType(String hbSeatType) {
		this.hbSeatType = hbSeatType;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", orderItemId=" + orderItemId
				+ ", passengerId=" + passengerId + ", routeType=" + routeType
				+ ", trainCn=" + trainCn + ", origStation=" + origStation
				+ ", origStationName=" + origStationName + ", destStation="
				+ destStation + ", destStationName=" + destStationName
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", trainType=" + trainType + ", seatType=" + seatType
				+ ", ticketParentId=" + ticketParentId + ", salePrice="
				+ salePrice + ", fee=" + fee + ", status=" + status
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", hbOrderId=" + hbOrderId + ", ticketNo=" + ticketNo
				+ ", cxin=" + cxin + ", type=" + type + ", ddsj=" + ddsj
				+ ", ccsj=" + ccsj + ", gapPrice=" + gapPrice
				+ ", bookAccount=" + bookAccount + ", requestNo=" + requestNo
				+ ", applicantId=" + applicantId + ", refundReason="
				+ refundReason + ", refundDesc=" + refundDesc + ", verifyDesc="
				+ verifyDesc + ", transactionId=" + transactionId
				+ ", orderNumber=" + orderNumber + ", hbPrice=" + hbPrice
				+ ", tmcPrice=" + tmcPrice + ", hbSeatType=" + hbSeatType
				+ ", operator=" + operator + "]";
	}

}
