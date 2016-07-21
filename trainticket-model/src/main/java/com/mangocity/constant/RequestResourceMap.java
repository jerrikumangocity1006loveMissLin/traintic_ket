package com.mangocity.constant;

import java.util.HashMap;
import java.util.Map;

public class RequestResourceMap {
	
	private static final Map<String,String> requestResourceMap = new HashMap<String,String>();
	private static final Map<String,String> requestResourceCnMap = new HashMap<String,String>();
	
	public static  Map<String,String> buildMap(){
		requestResourceMap.put("TMCMAIL", "2002209");
		requestResourceMap.put("TMCFAX", "2002209");
		requestResourceMap.put("TMCAPP", "2002209");
		requestResourceMap.put("TMCWEB", "2002209");
		requestResourceMap.put("TMCCC", "2002209");
		return requestResourceMap;
	}
	
	public static Map<String,String> buildResourceCnMap(){
		requestResourceCnMap.put("TMCMAIL", "邮件");
		requestResourceCnMap.put("TMCAPP", "APP");
		requestResourceCnMap.put("TMCFAX", "传真");
		requestResourceCnMap.put("TMCWEB", "WEB");
		requestResourceCnMap.put("TMCCC", "CC");
		return requestResourceCnMap;
	}

}
