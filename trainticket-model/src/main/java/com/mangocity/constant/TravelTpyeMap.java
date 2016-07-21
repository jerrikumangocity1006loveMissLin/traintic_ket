package com.mangocity.constant;

import java.util.HashMap;
import java.util.Map;

public class TravelTpyeMap {
	
	private static final Map<String,String> travelTypeMap = new HashMap<String,String>();
	private static final Map<String,String> trainTypeMap = new HashMap<String,String>();
	
	public static Map<String,String> TravelTypeMapProvider(){
		travelTypeMap.put("1", "因公");
		travelTypeMap.put("2", "因私");
		return travelTypeMap;
	}
	
	public static Map<String,String> trainTypeMapProvider(){
		travelTypeMap.put("1", "单程");
		travelTypeMap.put("2", "往返");
		return trainTypeMap;
	}

}
