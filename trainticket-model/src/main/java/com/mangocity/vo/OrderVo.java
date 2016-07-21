package com.mangocity.vo;

import java.io.Serializable;
import java.util.List;

import com.mangocity.model.Contact;
import com.mangocity.model.Order;

public class OrderVo implements Serializable {

	private static final long serialVersionUID = -1372593571639376637L;

	private Contact contact;

	private Order order;

	private List<OrderItemVo> orderItemsVos;
	
	private boolean isChange;

	public List<OrderItemVo> getOrderItemsVos() {
		return orderItemsVos;
	}

	public void setOrderItemsVos(List<OrderItemVo> orderItemsVos) {
		this.orderItemsVos = orderItemsVos;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public boolean isChange() {
		return isChange;
	}

	public void setChange(boolean isChange) {
		this.isChange = isChange;
	}

	
}
