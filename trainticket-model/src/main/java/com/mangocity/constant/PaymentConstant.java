package com.mangocity.constant;

import java.util.HashMap;
import java.util.Map;

public class PaymentConstant {
	
	//退款方式编码
	private static Map<String,String> refundModeCode = new HashMap<String,String>();
	
	//退款原因编码
	private static Map<String,String> refundReasonCode = new HashMap<String,String>();
	
	//支付方式
	private static Map<String, String> paymentWay = new HashMap<String, String>();
	
	//支付状态
	private static Map<String, String> paymentType = new HashMap<String, String>();
	
	//退款方式编码
	public static Map<String,String> getRefundModeCode(){
		refundModeCode.put("RMIPSO","IPS（国际）");
		refundModeCode.put("RMTENCENTPAY","财付通");
	    refundModeCode.put("RMCITICPAY","中信在线");
	    refundModeCode.put("BBKTA","银行转帐");
	    refundModeCode.put("BCASH","现金 ");
	    refundModeCode.put("BPOS","POS刷卡");
	    refundModeCode.put("RMICBCPAY","工行在线");
	    refundModeCode.put("BOTHER","其他");
	    refundModeCode.put("RMBOCPAY","中行在线");
	    refundModeCode.put("RMEASYPAY","宝易互通");
	    refundModeCode.put("RMCMBPAY","招行在线");
	    refundModeCode.put("RM99BILL","快钱");
	    refundModeCode.put("RMCOMMPAY","交行在线");
	    refundModeCode.put("RMCCBPAY","建行在线");
	    refundModeCode.put("DEBITCARD","借记卡支付");
	    refundModeCode.put("RMBYEEPAY","易宝");
	    refundModeCode.put("RMHKPPS","香港PPS");
	    refundModeCode.put("MORTAGAGE","抵款");
	    refundModeCode.put("CREDITCARDASSURE","信用卡担保");
	    refundModeCode.put("RMHKEPS","香港EPS");
	    refundModeCode.put("CREDITCARD","信用卡收款");
	    refundModeCode.put("NOMPOS","非芒果POS");
	    refundModeCode.put("ASSURETOPAY","担保转支付");
	    refundModeCode.put("RMALIPAY","支付宝");
	    refundModeCode.put("BCHK","支票");
	    refundModeCode.put("RMIPSR","IPS（国内）");
	    refundModeCode.put("RMABCPAY","农行在线");
		return refundModeCode;
	}
	
	//退款原因编码
	public static Map<String,String> getRefundReasonCode(){
		refundReasonCode.put("realWaste","假退实废");
	    refundReasonCode.put("dishonourRefund","退票退款");
	    refundReasonCode.put("complaint","投诉");
	    refundReasonCode.put("returnsPremium","退保险");
	    refundReasonCode.put("cancelRefund","取消退款");
	    refundReasonCode.put("overchargeRefund","多收退款");
	    refundReasonCode.put("other","其他");
		return refundReasonCode;
	}
	public static Map<String,String> getPaymentWay(){
        paymentWay.put("CASH", "现金支付");
        paymentWay.put("DPOS", "POS支付（国内）");
        paymentWay.put("IPOS", "POS支付（国际）");
        paymentWay.put("DIVR", "信用卡支付（国内）");
        paymentWay.put("IIVR", "信用卡支付（国际）");
        paymentWay.put("BKTA", "银行转帐");
        paymentWay.put("8ipso", "在线支付-（国外卡）");
        paymentWay.put("8ipsr", "在线支付-（国内卡）");
        paymentWay.put("TMON", "商旅月结");
        paymentWay.put("POINT", "积分支付");
        return paymentWay;
	}
	
	public static Map<String,String> getPaymentType(){
		paymentType.put("0", "待支付");//或者“待退款”
		paymentType.put("1", "在线支付中");//或者“退款中”
		paymentType.put("2", "支付失败");//或者“退款失败”
		paymentType.put("3", "部分支付");//或者“部分退款”
		paymentType.put("4", "已支付");//或者“已退款”
        return paymentType;
	}
}
