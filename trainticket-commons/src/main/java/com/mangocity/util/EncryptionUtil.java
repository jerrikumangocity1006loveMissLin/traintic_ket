package com.mangocity.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.apache.log4j.Logger;

public class EncryptionUtil {

	Logger logger = Logger.getLogger(this.getClass());
	private static final String APPSECRET="202cb962ac59075b964b07152d234b70";
	private static final String APPKEY="2433279";
	private static final String FORMATE="json";
	private static final String VERSION="1.0";
	private static final String SIGNMETHOD="md5";
	
	public static String signParams(String parameters,String method,Date date){
		
	    StringBuffer query = new StringBuffer(EncryptionUtil.APPSECRET);
	    query.append("appKey").append(EncryptionUtil.APPKEY);
	    query.append("format").append(EncryptionUtil.FORMATE);
	    query.append("method").append(method);
	    query.append("signMethod").append(EncryptionUtil.SIGNMETHOD);
	    query.append("timestamp").append(DateTimeUtils.formatDate(date, "yyyy-MM-dd HH:mm:ss"));
	    query.append("version").append(EncryptionUtil.VERSION);
	    query.append(parameters);
	    query.append(EncryptionUtil.APPSECRET);
	    byte[] bytes = parseStrToMd5(query.toString());
	    return byte2hex(bytes);
	}

	public static byte[] parseStrToMd5(String text) {

	    MessageDigest msgDigest = null;

	    try {
	        msgDigest = MessageDigest.getInstance("MD5");
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }

	    try {
	        msgDigest.update(text.getBytes("utf-8"));
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    }

	    return msgDigest.digest();
	}

	public static String byte2hex(byte[] bytes){

	    StringBuilder sign = new StringBuilder();
	    for(int i = 0; i < bytes.length; i++){
	        String hex = Integer.toHexString(bytes[i] & 255);
	        if(hex.length() == 1)
	            sign.append("0");
	        sign.append(hex.toUpperCase());
	    }

	    return sign.toString();
	}
	
	public static String urlEnCode(String url,String method,Date date,String sign){
		String enCodeUrl="";
		StringBuffer sbf = new StringBuffer(url);
		//sbf.append("appKey").append(EncryptionUtil.APPKEY);
		sbf.append("format").append(EncryptionUtil.FORMATE);
		sbf.append("method").append(method);
		sbf.append("signMethod").append(EncryptionUtil.SIGNMETHOD);
		sbf.append("timestamp").append(DateTimeUtils.formatDate(date, "yyyy-MM-dd HH:mm:ss"));
		sbf.append("version").append(EncryptionUtil.VERSION);
		sbf.append("sign").append(sign);
		try {
			enCodeUrl = java.net.URLEncoder.encode(sbf.toString(),"utf-8");
		} catch (UnsupportedEncodingException e) {
		   e.printStackTrace();
		}  
		return enCodeUrl;
	}
	
	public static void main(String[] args){
		String parameters = "{\"businessId\":\"550\",\"bxfs\":\"0\",\"checi\":\"K438\",\"cllx\":\"N\",\"ddDate\":\"2016-08-17\",\"ddTime\":\"06:20\",\"fromStationCode\":\"DMQ\",\"fromStationName\":\"东莞东\",\"lxr\":\"胡育新\",\"lxrsj\":\"13794478081\",\"passengers\":[{\"cxin\":\"\",\"fee\":\"15\",\"passengerName\":\"胡育新\",\"passportNo\":\"360421198803252618\",\"passportTypeId\":\"1\",\"password\":\"gaohu188258cs\",\"phone\":\"13794478081\",\"piaoType\":\"1\",\"price\":\"17.5\",\"username\":\"gaohu2013\",\"zwCode\":\"1\"}],\"qqly\":\"2002209\",\"requestNo\":\"1468824356663\",\"toStationCode\":\"HCQ\",\"toStationName\":\"惠州\",\"trainDate\":\"2016-08-17\",\"trainTime\":\"05:37\"}";
		String method = "cr.bookTrain";
		Date date = new Date(); 
		System.out.println(EncryptionUtil.signParams(parameters, method, date));
	}
}
