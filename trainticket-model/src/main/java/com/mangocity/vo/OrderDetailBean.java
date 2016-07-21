package com.mangocity.vo;

import java.io.Serializable;
import java.util.List;

import com.mangocity.model.Applicant;
import com.mangocity.model.Charge;
import com.mangocity.model.Contact;
import com.mangocity.model.Delivery;
import com.mangocity.model.Order;
import com.mangocity.model.TrainPayDetail;

public class OrderDetailBean implements Serializable {

	private static final long serialVersionUID = -7350641193335378371L;
	private Order orderBasic;//
	private List<TicketInfoVo> tickets;// 票
	private PayDetailBean sumDetails;// 价格合计
	private Contact contact;// 联系人
	private List<Delivery> deliveries;// 配送
	private List<Charge> charges;//
	private List<TrainPayDetail> paymentDetails;//支付明细
	private String paymentMethod;
	private RefundFeeVo refundFeeVo;//退款费
	private Applicant applicant;//申请人
    private String refundDesc;//退改备注
	private String verifyDesc;//审核备注

	public Order getOrderBasic() {
		return orderBasic;
	}

	public void setOrderBasic(Order orderBasic) {
		this.orderBasic = orderBasic;
	}

	public List<TicketInfoVo> getTickets() {
		return tickets;
	}

	public void setTickets(List<TicketInfoVo> tickets) {
		this.tickets = tickets;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public List<Charge> getCharges() {
		return charges;
	}

	public void setCharges(List<Charge> charges) {
		this.charges = charges;
	}

	public PayDetailBean getSumDetails() {
		return sumDetails;
	}

	public void setSumDetails(PayDetailBean sumDetails) {
		this.sumDetails = sumDetails;
	}

	public List<TrainPayDetail> getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(List<TrainPayDetail> paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public List<Delivery> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(List<Delivery> deliveries) {
		this.deliveries = deliveries;
	}

	public RefundFeeVo getRefundFeeVo() {
		return refundFeeVo;
	}

	public void setRefundFeeVo(RefundFeeVo refundFeeVo) {
		this.refundFeeVo = refundFeeVo;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
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
	
	

}
