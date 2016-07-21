/**
 * @projectName:dhtserver123
 * @packageName:com.dht.base.util
 * @className:FastJsonUtil.java
 *
 * @createTime:2014年4月10日-下午4:13:33
 *
 *
 */
package com.mangocity.common;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

/**
 * JSON处理类
 * 
 * @author hongxiaodong
 *
 */
public class JsonUtil {
	private Logger logger = Logger.getLogger(this.getClass());
	private static SerializeConfig serializeConfig = new SerializeConfig();

	public static String objectToString(Object o) {
		String str = "";
		if (o != null) {
			str = JSON.toJSONString(o);
		}
		return str;
	}

	public static <T> Object JsonString2Object(String text, Class<T> clazz) throws Exception{
		try {
			if (StringUtils.isEmpty(text))return null;
			return JSON.parseObject(text, clazz);
		} catch (Exception e) {
			return null;
		}
	}

	public static String objectContainsDateToFormatString(Object o, String dateFormatString) {

		try {
			serializeConfig.put(Date.class, new SimpleDateFormatSerializer(dateFormatString));

		} catch (Exception e) {
			try {
				throw e;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		return JSON.toJSONString(o, serializeConfig);
	}

}
