package com.mangocity.vo;

import java.io.Serializable;
import java.util.List;

public class QueryOrderVo extends OrderBasisVo implements Serializable {

	private static final long serialVersionUID = -4386227911633883715L;
	private List<TicketInfoVo> tickets;

	public List<TicketInfoVo> getTickets() {
		return tickets;
	}

	public void setTickets(List<TicketInfoVo> tickets) {
		this.tickets = tickets;
	}

}
