package com.mangocity.util;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtil {
	
	public static String Object2JsonString(Object object){
		return JSON.toJSONString(object);
	}
	
	public static <T> Object JsonString2Object(String text,Class<T> clazz){
		if(StringUtils.isEmpty(text))return null;
		return  JSON.parseObject(text, clazz);
	} 
	
	public static JSONObject objectToJosnObject(Object object){
		JSONObject json = null;
		if(object!=null){
		   json = (JSONObject) JSON.toJSON(object);
		}
		return json;
	}
	
	public static JSONObject stringToJsonObject(String text){
		if(StringUtils.isBlank(text)){
			return null;
		}else{
			return JSON.parseObject(text);
		}
	}

	public static String ObjectToJsonString(Object object){
		if(object==null){
			return "";
		}else{
			 ObjectMapper mapper = new ObjectMapper();   
		     String json="";
			try {
			       mapper.configure(SerializationFeature.INDENT_OUTPUT, Boolean.TRUE);
                   json = mapper.writeValueAsString(object);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		     return json;
		}  
		
	}
	
	
	public static void main(String[] args) {
	    
		
	}

}
