package com.mangocity.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.mangocity.btms.adpater.vo.ApprovalManVO;
import com.mangocity.model.Applicant;
import com.mangocity.model.Delivery;

public class PayParamsVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -287658244292015141L;

	private Long orderId;// 订单Id

	private String orderCn;// 订单号

	private List<Long> orderItems;// 订购项ID
	
	private List<Long> goodsIds;//产品ID

	private String payModel;// 支付方式

	private String voucherCd;// 代金劵编号

	private Long integralNum;// 积分兑换数

	private String payInfoType;// 支付类型：0付款，1退款

	private String payChannel;// 支付渠道：CC

	private Long payId;// 支付ID

	private String xmlStr;// 支付平台调用参数

	private BigDecimal price;// 支付金额

	private String membercd;// 会员

	private String passenger;// 乘客

	private String creditCardId;// 信用卡ID

	private String cardPayType;// 离线信用卡支付类别,全额=ALL，担保=ASS，分期=AFQ，信用卡积分=CJF

	private String outTradeNo;// 外部交易号

	private String refundModeCode;// 退款方式

	private String refundReasonCode;// 退款原因

	private String operator;// 操作员

	private String accountName;// 收款人名称

	private String accountNo;// 帐号

	private String bankName;// 开户银行名称

	private String createBill;// 已创行程单

	private String unCreateBill;// 未创行程单

	private String remark;// 备注

	private Delivery delivery;// 配送信息

	private Long orderItemId;// 订购项ID

	private String goodsType;// 产品类型

	private String ticketNo;// 票号

	private Applicant applicant;// 申请人

	private String refundReason;// 退票原因

	private String refundDesc;// 退改备注

	private String verifyDesc;// 审核备注

	private List<String> ticketNoList;// 票号

	private String customerRemark;// 客人要求

	private String frontRemark;// 前台备注

	private List<ApprovalManVO> messageReceiver;// 消息配送人员

	private String projectCode;// 项目代码
	
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderCn() {
		return orderCn;
	}

	public void setOrderCn(String orderCn) {
		this.orderCn = orderCn;
	}

	public List<Long> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<Long> orderItems) {
		this.orderItems = orderItems;
	}

	public String getPayModel() {
		return payModel;
	}

	public void setPayModel(String payModel) {
		this.payModel = payModel;
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

	public String getPayInfoType() {
		return payInfoType;
	}

	public void setPayInfoType(String payInfoType) {
		this.payInfoType = payInfoType;
	}

	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}

	public Long getPayId() {
		return payId;
	}

	public void setPayId(Long payId) {
		this.payId = payId;
	}

	public String getXmlStr() {
		return xmlStr;
	}

	public void setXmlStr(String xmlStr) {
		this.xmlStr = xmlStr;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getMembercd() {
		return membercd;
	}

	public void setMembercd(String membercd) {
		this.membercd = membercd;
	}

	public String getPassenger() {
		return passenger;
	}

	public void setPassenger(String passenger) {
		this.passenger = passenger;
	}

	public String getCreditCardId() {
		return creditCardId;
	}

	public void setCreditCardId(String creditCardId) {
		this.creditCardId = creditCardId;
	}

	public String getCardPayType() {
		return cardPayType;
	}

	public void setCardPayType(String cardPayType) {
		this.cardPayType = cardPayType;
	}

	public String getRefundModeCode() {
		return refundModeCode;
	}

	public void setRefundModeCode(String refundModeCode) {
		this.refundModeCode = refundModeCode;
	}

	public String getRefundReasonCode() {
		return refundReasonCode;
	}

	public void setRefundReasonCode(String refundReasonCode) {
		this.refundReasonCode = refundReasonCode;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCreateBill() {
		return createBill;
	}

	public void setCreateBill(String createBill) {
		this.createBill = createBill;
	}

	public String getUnCreateBill() {
		return unCreateBill;
	}

	public void setUnCreateBill(String unCreateBill) {
		this.unCreateBill = unCreateBill;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public Long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
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

	public List<String> getTicketNoList() {
		return ticketNoList;
	}

	public void setTicketNoList(List<String> ticketNoList) {
		this.ticketNoList = ticketNoList;
	}

	public String getCustomerRemark() {
		return customerRemark;
	}

	public void setCustomerRemark(String customerRemark) {
		this.customerRemark = customerRemark;
	}

	public String getFrontRemark() {
		return frontRemark;
	}

	public void setFrontRemark(String frontRemark) {
		this.frontRemark = frontRemark;
	}

	public List<ApprovalManVO> getMessageReceiver() {
		return messageReceiver;
	}

	public void setMessageReceiver(List<ApprovalManVO> messageReceiver) {
		this.messageReceiver = messageReceiver;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public List<Long> getGoodsIds() {
		return goodsIds;
	}

	public void setGoodsIds(List<Long> goodsIds) {
		this.goodsIds = goodsIds;
	}

	@Override
	public String toString() {
		return "PayParamsVo [orderId=" + orderId + ", orderCn=" + orderCn + ", orderItems=" + orderItems + ", payModel="
				+ payModel + ", voucherCd=" + voucherCd + ", integralNum=" + integralNum + ", payInfoType="
				+ payInfoType + ", payChannel=" + payChannel + ", payId=" + payId + ", xmlStr=" + xmlStr + ", price="
				+ price + ", membercd=" + membercd + ", passenger=" + passenger + ", creditCardId=" + creditCardId
				+ ", cardPayType=" + cardPayType + ", outTradeNo=" + outTradeNo + ", refundModeCode=" + refundModeCode
				+ ", refundReasonCode=" + refundReasonCode + ", operator=" + operator + ", accountName=" + accountName
				+ ", accountNo=" + accountNo + ", bankName=" + bankName + ", createBill=" + createBill
				+ ", unCreateBill=" + unCreateBill + ", remark=" + remark + ", delivery=" + delivery + ", orderItemId="
				+ orderItemId + ", goodsType=" + goodsType + ", ticketNo=" + ticketNo + ", applicant=" + applicant
				+ ", refundReason=" + refundReason + ", refundDesc=" + refundDesc + ", verifyDesc=" + verifyDesc + "]";
	}

}
