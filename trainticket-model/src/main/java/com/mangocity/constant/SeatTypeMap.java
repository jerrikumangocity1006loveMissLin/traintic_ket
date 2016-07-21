package com.mangocity.constant;

import java.util.HashMap;
import java.util.Map;
/**
 * 座位类型Map
 * @author lizhi
 *
 * @2016年6月30日
 */
public class SeatTypeMap {
	
	private static final Map<String,String> seatTypeMap = new HashMap<String,String>();
	private static final Map<String,String> ticketTypeMap = new HashMap<String, String>();
	
	public static Map<String,String> seatTypeProvider(){
		seatTypeMap.put("9", "商务座");
		seatTypeMap.put("P", "特等座");
		seatTypeMap.put("M", "一等座");
		seatTypeMap.put("O", "二等座");
		seatTypeMap.put("6", "高级软卧");
		seatTypeMap.put("4", "软卧");
		seatTypeMap.put("3", "硬卧");
		seatTypeMap.put("2", "软座");
		seatTypeMap.put("1", "硬座");
		return seatTypeMap;
	}
	
	public static Map<String,String> ticketTypeProvider(){
		ticketTypeMap.put("1", "成人");
		ticketTypeMap.put("2", "儿童");
		ticketTypeMap.put("3", "学生票");
		ticketTypeMap.put("4", "残军票");
		return ticketTypeMap;
	}

}
