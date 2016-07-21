package com.mangocity.vo;

import java.io.Serializable;
import java.util.List;

import com.mangocity.model.Charge;
import com.mangocity.model.Delivery;
import com.mangocity.model.OrderItem;
import com.mangocity.model.TrainInvoice;

public class OrderItemVo implements Serializable {

	private static final long serialVersionUID = 5977578402563340886L;

	//private TicketVo ticketVo;
	private String chargeId;
	private String deliveryId;
	private List<TrainInvoice> trainInvoices;
	private OrderItem orderItem;
	private List<TicketVo> trainTicketVos;
	private List<Charge> charges;
	private List<Delivery> deliverys;

	public String getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(String deliveryId) {
		this.deliveryId = deliveryId;
	}

	public String getChargeId() {
		return chargeId;
	}

	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}

/*	public TicketVo getTicketVo() {
		return ticketVo;
	}

	public void setTicketVo(TicketVo ticketVo) {
		this.ticketVo = ticketVo;
	}*/

	public List<TrainInvoice> getTrainInvoices() {
		return trainInvoices;
	}

	public void setTrainInvoices(List<TrainInvoice> trainInvoices) {
		this.trainInvoices = trainInvoices;
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}

	public List<TicketVo> getTrainTicketVos() {
		return trainTicketVos;
	}

	public void setTrainTicketVos(List<TicketVo> trainTicketVos) {
		this.trainTicketVos = trainTicketVos;
	}

	public List<Charge> getCharges() {
		return charges;
	}

	public void setCharges(List<Charge> charges) {
		this.charges = charges;
	}

	public List<Delivery> getDeliverys() {
		return deliverys;
	}

	public void setDeliverys(List<Delivery> deliverys) {
		this.deliverys = deliverys;
	}

}
