package com.mangocity.vo;

import java.io.Serializable;
import java.util.List;

import com.mangocity.model.Insurance;
import com.mangocity.model.Ticket;

public class TicketVo implements Serializable {

	private static final long serialVersionUID = -4320135124024335639L;

	private Ticket ticket;
	// private MyTest ticket;

	private List<Insurance> insurances;

	private List<Ticket> ticketList;

	private String lxr;

	private String lxrsj;

	private String lxrEmail;

	private String qqly;

	private String cllx;

	// private String ccrq;// 乘车日期

	// private String ddrq;// 到达日期

	private List<PassengerVo> passengerVos;

	public List<PassengerVo> getPassengerVos() {
		return passengerVos;
	}

	public void setPassengerVos(List<PassengerVo> passengerVos) {
		this.passengerVos = passengerVos;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public List<Insurance> getInsurances() {
		return insurances;
	}

	
	/*  public MyTest getTicket() { return ticket; }
	 
	  public void setTicket(MyTest ticket) { this.ticket = ticket; }*/
	 

	public void setInsurances(List<Insurance> insurances) {
		this.insurances = insurances;
	}

	public String getLxr() {
		return lxr;
	}

	public void setLxr(String lxr) {
		this.lxr = lxr;
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

	public String getCllx() {
		return cllx;
	}

	public void setCllx(String cllx) {
		this.cllx = cllx;
	}

	public String getLxrEmail() {
		return lxrEmail;
	}

	public void setLxrEmail(String lxrEmail) {
		this.lxrEmail = lxrEmail;
	}

	public List<Ticket> getTicketList() {
		return ticketList;
	}

	public void setTicketList(List<Ticket> ticketList) {
		this.ticketList = ticketList;
	}

	/*
	 * public String getCcrq() { return ccrq; }
	 * 
	 * public void setCcrq(String ccrq) { this.ccrq = ccrq; }
	 * 
	 * public String getDdrq() { return ddrq; }
	 * 
	 * public void setDdrq(String ddrq) { this.ddrq = ddrq; }
	 */

}
