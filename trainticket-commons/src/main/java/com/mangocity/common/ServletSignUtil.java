package com.mangocity.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * MD5加密以及验证加密信息工具类
 *
 */
public class ServletSignUtil {
	
	public static final int CONNECTION_TIME = 10 * 1000;// 连接超时时间
	
	public static final int SEVICE_TIME = 80 * 1000;// 服务器返回超时时间(毫秒)

	/**
	 *  机票：1000
		度假：1002
		商旅：1004
		邮轮：1006
		酒店: 1001
		火车票：1927

	 */
	public static final String CUSTOMER_ID = "1927";// 客户id
	
	public static final String PSD_KEY = "123456";// 密钥
	
	public static final String CURRENCY_RMB = "RMB";// 人民币
	
	public static final String GATHERING_UNITCODE = "CENTER";//收款单位
	
	public static final String PRODUCT_TYPE_TRAIN = "TRAINTICKET";
	
	public static final String SIGN_TYPE = "MD5";
	/**
	 * 加密数据之前，对参数进行字母排序和拼装。
	 * 
	 * @param params
	 *            进行签名的数据
	 * @param privateKey
	 *            key密钥
	 * 
	 * @return 加密后的密文
	 */
	public static String buildSign(Map<String, String> params, String privateKey) {
		// 如果map为空，则不进行任何操作
		if (null == params) {
			return "";
		}
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		StringBuffer paramsStrBuf = new StringBuffer();
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			if (null == key || key.equalsIgnoreCase("sign") || key.equalsIgnoreCase("signType")) {
				continue;
			}
			String value = params.get(key);
			if (null == value || 0 == value.trim().length() || "null".equals(value)) {
				continue;
			}
			paramsStrBuf.append((0 == i ? "" : "&"));
			paramsStrBuf.append(key);
			paramsStrBuf.append("=");
			paramsStrBuf.append(value);

		}
		return Md5Encrypt.md5(paramsStrBuf.append(privateKey).toString());
	}
	
	/**
     * 加密信息验证
     * 
     * @param sign
     *            密文
     * @param Params
     *            进行签名的数据
     * @param key
     *            密钥
     * @return 验签结果
     */
    public static boolean verifySign(String sign, Map<String, String> Params, String key) {
        boolean verifyResult = false;
        String verifySign = buildSign(Params, key);
        if (sign.toLowerCase().equals(verifySign.toLowerCase())) {
            verifyResult = true;
        }
        return verifyResult;
    }
}
