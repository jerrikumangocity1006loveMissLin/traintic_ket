package com.mangocity.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TrainPayDetail implements Serializable {

	private static final long serialVersionUID = -7132236788799060121L;
	private Long id;
	private Long trainPayId;//支付ID
	private String payModel;//支付方式
	private BigDecimal payAmount;//支付金额
	private String status;//支付状态
	private String payNumber;//支付流水号
	private String voucherCd;//代金券编号
	private Long integralNum;//积分兑换数
	private Date createTime;
	private Date modifyTime;
	private String hbOrderId;//号百订单（退单）号
	
	

	public TrainPayDetail() {
		super();
	}

	public TrainPayDetail(Long id, Long trainPayId, String payModel,
			BigDecimal payAmount, String status, String payNumber,
			String voucherCd, Long integralNum, Date createTime,
			Date modifyTime, String hbOrderId) {
		super();
		this.id = id;
		this.trainPayId = trainPayId;
		this.payModel = payModel;
		this.payAmount = payAmount;
		this.status = status;
		this.payNumber = payNumber;
		this.voucherCd = voucherCd;
		this.integralNum = integralNum;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.hbOrderId = hbOrderId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTrainPayId() {
		return trainPayId;
	}

	public void setTrainPayId(Long trainPayId) {
		this.trainPayId = trainPayId;
	}

	public String getPayModel() {
		return payModel;
	}

	public void setPayModel(String payModel) {
		this.payModel = payModel;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public String getPayNumber() {
		return payNumber;
	}

	public void setPayNumber(String payNumber) {
		this.payNumber = payNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVoucherCd() {
		return voucherCd;
	}

	public void setVoucherCd(String voucherCd) {
		this.voucherCd = voucherCd;
	}

	public Long getIntegralNum() {
		return integralNum;
	}

	public void setIntegralNum(Long integralNum) {
		this.integralNum = integralNum;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getHbOrderId() {
		return hbOrderId;
	}

	public void setHbOrderId(String hbOrderId) {
		this.hbOrderId = hbOrderId;
	}

	@Override
	public String toString() {
		return "TrainPayDetail [id=" + id + ", trainPayId=" + trainPayId
				+ ", payModel=" + payModel + ", payAmount=" + payAmount
				+ ", status=" + status + ", payNumber=" + payNumber
				+ ", voucherCd=" + voucherCd + ", integralNum=" + integralNum
				+ ", createTime=" + createTime + ", modifyTime=" + modifyTime
				+ ", hbOrderId=" + hbOrderId + "]";
	}
}
