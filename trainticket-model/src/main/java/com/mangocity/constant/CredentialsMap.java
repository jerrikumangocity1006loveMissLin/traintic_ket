package com.mangocity.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 证件类型Map
 * 
 * @author lizhi
 *
 * @2016年6月30日
 */
public class CredentialsMap {

	private static final Map<String, String> credentialsMap = new HashMap<String, String>();
	private static final Map<String,String> credentialsRelationShipMap = new HashMap<String,String>();
	private static final Map<String,String> credentialsRelationConvert = new HashMap<String,String>();

	public static Map<String, String> credentialsMapProvider() {
		credentialsMap.put("1", "二代身份证");
		credentialsMap.put("2", "一代身份证");
		credentialsMap.put("C", "港澳通行证");
		credentialsMap.put("G", "台湾通行证");
		credentialsMap.put("B", "护照");
		return credentialsMap;
	}
	
	public static  Map<String, String> credentialsRelationShipMapProvider(){
		credentialsRelationShipMap.put("PSP", "B");
		credentialsRelationShipMap.put("IDC", "1");
		credentialsRelationShipMap.put("TW1", "G");
		credentialsRelationShipMap.put("TW2", "G");
		credentialsRelationShipMap.put("HKM", "C");
		return credentialsRelationShipMap;
	}
	
	public static  Map<String, String> credentialsRelationShipMapConvert(){
		credentialsRelationConvert.put("B","PSP");
		credentialsRelationConvert.put("1","IDC");
		credentialsRelationConvert.put("G","TW1");
		credentialsRelationConvert.put("G","TW2");
		credentialsRelationConvert.put("C","HKM");
		return credentialsRelationConvert;
	}
	

}
