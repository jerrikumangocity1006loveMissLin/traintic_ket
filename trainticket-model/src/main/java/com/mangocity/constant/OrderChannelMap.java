package com.mangocity.constant;

import java.util.HashMap;
import java.util.Map;

public class OrderChannelMap {
	
	private static final Map<String,String> channelMap = new HashMap<String,String>();
	
	public static Map<String,String> channelMapProvider(){
		channelMap.put("TMCMAIL", "邮件");
		channelMap.put("TMCFAX", "传真");
		channelMap.put("TMCAPP", "APP");
		channelMap.put("TMCWEB", "网站");
		channelMap.put("TMCCC", "CC");
		return channelMap;
	}

}
