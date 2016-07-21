package com.mangocity.vo;

import java.io.Serializable;
import java.util.List;

import com.mangocity.model.Order;

/**
 * 订单机票信息
 * 
 * @author hongxiaodong
 *
 */
public class OrderTicketPaInfoVo extends Order implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<TicketInfoVo> ticketInfos;

	public List<TicketInfoVo> getTicketInfos() {
		return ticketInfos;
	}

	public void setTicketInfos(List<TicketInfoVo> ticketInfos) {
		this.ticketInfos = ticketInfos;
	}
	
	
	
	public static OrderTicketPaInfoVo convertOrderTicket(Order order){
		OrderTicketPaInfoVo orderTicket = new OrderTicketPaInfoVo();
		orderTicket.setBookMember(order.getBookMember());
		orderTicket.setCompanyCode(order.getCompanyCode());
		orderTicket.setCostCenter(order.getCostCenter());
		orderTicket.setCreateTime(order.getCreateTime());
		orderTicket.setDepartment(order.getDepartment());
		orderTicket.setFrontRemark(order.getFrontRemark());
		orderTicket.setId(order.getId());
		orderTicket.setMemberCn(order.getMemberCn());
		orderTicket.setMiddRemark(order.getMiddRemark());
		orderTicket.setOrderAudit(order.getOrderAudit());
		orderTicket.setOrderChannel(order.getOrderChannel());
		orderTicket.setOrderCn(order.getOrderCn());
		orderTicket.setOrderFrom(order.getOrderFrom());
		orderTicket.setOrderMethod(order.getOrderMethod());
		orderTicket.setOutMethod(order.getOutMethod());
		orderTicket.setPaymentMethod(order.getPaymentMethod());
		orderTicket.setPaymentStatus(order.getPaymentStatus());
		orderTicket.setProjectCode(order.getProjectCode());
		orderTicket.setRouteType(order.getRouteType());
		orderTicket.setSettlement(order.getSettlement());
		orderTicket.setStatus(order.getStatus());
		orderTicket.setTravelType(order.getTravelType());
		return orderTicket;
	}

}
