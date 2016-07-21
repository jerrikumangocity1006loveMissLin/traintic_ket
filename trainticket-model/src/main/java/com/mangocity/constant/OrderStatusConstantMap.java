package com.mangocity.constant;

import java.util.HashMap;
import java.util.Map;

public class OrderStatusConstantMap {
	
	private static final Map<String,String> orderStatusMap = new HashMap<String, String>(); 
	
	//订票状态
	private static final Map<String,String> ticketStatusMap = new HashMap<String, String>();
	//退票状态
	private static final Map<String,String> refundStatusMap = new HashMap<String, String>();
	//改签状态
	private static final Map<String,String> changeStatusMap = new HashMap<String, String>();
	
	public static Map<String,String> orderStatusMapFactory(){
		orderStatusMap.put("1", "2");
		orderStatusMap.put("2", "8");
		orderStatusMap.put("1B", "-1");
		orderStatusMap.put("3", "12");
		orderStatusMap.put("2A", "6");
		return orderStatusMap;
	}
	
	//订票状态
	public static Map<String,String> getTicketStatus(){
		ticketStatusMap.put("-1", "订座失败");
		ticketStatusMap.put("0", "预订中");
		ticketStatusMap.put("1", "占位中");
		ticketStatusMap.put("2", "已预订");
		ticketStatusMap.put("3", "待审批");
		ticketStatusMap.put("4", "审批失败");
		ticketStatusMap.put("5", "审批成功");
		ticketStatusMap.put("6", "出票中");
		ticketStatusMap.put("7", "出票失败");
		ticketStatusMap.put("8", "已出票");
		ticketStatusMap.put("9", "待配送");
		ticketStatusMap.put("10", "已配送");
		ticketStatusMap.put("11", "已完成");
		ticketStatusMap.put("12", "已取消");
		return ticketStatusMap;
	}
	
	//退票状态
	public static Map<String,String> getRefundStatus(){
		refundStatusMap.put("0", "退票申请中");
		refundStatusMap.put("1", "退票申失败");
		refundStatusMap.put("2", "待审核");
		refundStatusMap.put("3", "审核失败");
		refundStatusMap.put("4", "已审核");
		refundStatusMap.put("5", "退票中");
		refundStatusMap.put("6", "已退票");
		refundStatusMap.put("7", "退款中");
		refundStatusMap.put("8", "已退款");
		refundStatusMap.put("9", "退款失败");
		//refundStatusMap.put("10", "已取消");
		return refundStatusMap;
	}
	
	//改签状态
	public static Map<String,String> getChangeStatus(){
		changeStatusMap.put("", "");
		return changeStatusMap;
	}

}
