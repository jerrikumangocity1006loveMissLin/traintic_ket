package com.mangocity.nmd.platform.ws.impl;

import com.mangocity.nmd.platform.ws.ApproveDto;
import com.mangocity.nmd.platform.ws.ApproveNotifyDto;
import com.mangocity.nmd.platform.ws.ApproveResult;
import com.mangocity.nmd.platform.ws.SMSApproveService;

import javax.xml.rpc.ServiceException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 短信审批类
 * @author hongxiaodong
 *
 */
public class SMSApproveServiceUtil {
	private static final Log logger = LogFactory.getLog(SMSApproveServiceUtil.class);
	
	private static SMSApproveService smsApproveService;
	
	// private static String url = ParamServiceImpl.getInstance().getConfValue("{NSP_SMS_APPROVAL_URL}");

	//private static String url = "http://10.10.39.5:9082/nsp/service/SmsApproveService?wsdl";
	
	private static String url = "http://ro.mangocity.com/nsp/service/SmsApproveService";

	public static SMSApproveService getService() throws ServiceException {
		if (smsApproveService != null) {
			return smsApproveService;
		} else {
			NspAPISMSApproveServiceLocator nspSMS = new NspAPISMSApproveServiceLocator();
			java.net.URL endpoint;
			try {
				int index = url.indexOf("?");
				if (url != null && index > 0) {
					url = url.substring(0, index);
				}
				endpoint = new java.net.URL(url);
			} catch (java.net.MalformedURLException e) {
				throw new javax.xml.rpc.ServiceException(e);
			}

			smsApproveService = nspSMS.getSMSApproveServiceImplPort(endpoint);

			return smsApproveService;
		}
	}

	public static ApproveResult approve(ApproveDto approveDto) throws Exception {
		try {
			return getService().approve(approveDto);
		} catch (Exception e) {
			logger.error("调用短信审批异常：" + e);
			throw new Exception(e);
		}
	}

	public static String approveNotify(ApproveNotifyDto approveNotifyDto) throws Exception {
		try {
			return getService().notifyApprove(approveNotifyDto);
		} catch (Exception e) {
			logger.error("调用短信审批异常：" + e);
			throw new Exception(e);
		}
	}

	public static void main(String[] args) throws Exception {

		ApproveDto approveDto = new ApproveDto();
		
		approveDto.setAppKey("13258888");

		ApproveResult re = SMSApproveServiceUtil.approve(approveDto);

		System.out.println("=====re====" + re.getResult());
	}
}
