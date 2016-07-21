package com.mangocity.response;

import java.io.Serializable;
import java.util.Map;

public class ResponseMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1945702421543048236L;
	
	public static final String SUCCESS = "0";
	
	public static final String ERROR = "-1";
	
	private String code;//0 成功，-1 失败
	private String msg;//失败时返回错误信息
	private String maintainFlag;//系统维护中：1是，0否
	private Map<String,Object> data;//其他数据
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMaintainFlag() {
		return maintainFlag;
	}
	public void setMaintainFlag(String maintainFlag) {
		this.maintainFlag = maintainFlag;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	
}
