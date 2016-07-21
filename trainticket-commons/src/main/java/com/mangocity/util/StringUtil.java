package com.mangocity.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class StringUtil {

	public static String upperCase(String code) {
		if (null == code) {
			return "";
		} else {
			return StringUtils.upperCase(code);
		}
	}

	public static String null2Space(String str) {
		if (null == str) {
			return "";
		} else {
			return str;
		}
	}

	public static int null2Zero(Object obj) {
		if (null == obj) {
			return 0;
		} else {
			return str2Int(obj.toString());
		}
	}

	public static int str2Int(String str) {
		int result = 0;
		if (null == str) {
			return 0;
		} else {
			try {
				result = Integer.parseInt(str);
			} catch (Exception e) {
				e.printStackTrace();
				result = 0;
			}

		}
		return result;
	}

	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 验证传真
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isFax(String str) {
		Pattern pattern = Pattern.compile("^[+]{0,1}(\\d){1,3}[ ]?([-]?((\\d)|[ ]){1,12})+$");

		Matcher matcher = pattern.matcher(str);

		if (matcher.matches()) {
			return true;
		}

		return false;

	}

	/**
	 * 验证手机号码
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isPhone(String str) {
		Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

		Matcher matcher = pattern.matcher(str);

		if (matcher.matches()) {
			return true;
		}

		return false;

	}

	/**
	 * 验证邮箱
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isMail(String str) {

		Pattern pattern = Pattern
				.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");

		Matcher matcher = pattern.matcher(str);

		if (matcher.matches()) {
			return true;
		}

		return false;
	}

	/**
	 * 验证身份证
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isCard(String str) {

		Pattern pattern = Pattern.compile("(^\\d{18}$)|(^\\d{15}$)");

		Matcher matcher = pattern.matcher(str);

		if (matcher.matches()) {
			return true;
		}

		return false;
	}
	
	/**
     * 
     * @param membercd
     * @param str
     * @return
     */
    public static String changMobileAndEmail(String membercd,String str){
		String result = str;
		 if(result!=null && result.indexOf("$")>0 && result.split("\\$").length==2){
		    	if(result.startsWith(membercd+"$")){
		    		result = result.substring(str.indexOf("$")+1);
		    	}
		    }
		 return result;
    }

}
