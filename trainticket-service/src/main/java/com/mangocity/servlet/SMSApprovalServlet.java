package com.mangocity.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mangocity.btms.api.IApprovalManageService;
import com.mangocity.btms.api.IApprovalMessageManageService;
import com.mangocity.btms.approval.model.ApprovalDetail;
import com.mangocity.btms.approval.model.ApprovalMan;
import com.mangocity.btms.vo.ApprovalVO;
import com.mangocity.common.ServletSignUtil;


/**
 * 短信回调审批
 * 
 * @author hongxiaodong
 *
 */
@WebServlet(name = "SMSApprovalServlet", urlPatterns = {"/service/smsApproval"})
public class SMSApprovalServlet extends BaseServlet {
	
	Logger log = Logger.getLogger(SMSApprovalServlet.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -3511818793863589982L;
	
	@Autowired
	private IApprovalManageService approvalManageService;
	
	@Autowired
	private IApprovalMessageManageService approvalMessageManageService;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info(">>>>>>>>>>>>SMSApprovalServlet>>>>>>>>>>>start");
		String ip = null;
		PrintWriter writer = null;
    	try{
    		ip = getRemoteIP(req);
    	}catch(Exception e){
    		 e.printStackTrace();  
    	}
    	String appKey = req.getParameter("appKey");
    	String mobile = req.getParameter("mobile");
    	String approveCode = req.getParameter("approveCode");
    	String result = req.getParameter("result");
    	String reason = req.getParameter("reason");
    	String sign = req.getParameter("sign");
    	log.info("获取到审批结果："+"id:"+appKey+",手机："+mobile+",审批单号："+approveCode+",结果："+result+",原因："+reason+",密文:"+sign+" ip:"+ip);
    	
    	if(!checkSign(approveCode,appKey,mobile,result,reason,sign)){
    		log.info(">>>>>>>>>>审核批单号验证失败，审批单号为:"+approveCode);
    		writer = resp.getWriter();
    		writer.write("UN_CERT");
    		writer.flush();
    		writer.close();
    		return ;
    	}
    	try {
    		result = result.equals("Y")?"pass":"reject";
    		
			ApprovalVO approvalVO = approvalManageService.getApprovalOrder(appKey, mobile);
			
			List<ApprovalDetail> approvalDetailList = new ArrayList<ApprovalDetail>();
			Iterator i = approvalVO.getOrderSet().iterator();
			while(i.hasNext()){//遍历  
				ApprovalDetail detail = new ApprovalDetail();
	            detail.setOrderCd((String) i.next());
	            detail.setResult(ApprovalDetail.Result.getInstance(result));
	            detail.setChannel(ApprovalDetail.Channel.getInstance("SMS"));
	            detail.setRemark(reason);
	            approvalDetailList.add(detail); 
	        } 
			 approvalVO.getApprovalHuman().setApprovalMan(new ApprovalMan());
			 
			 approvalMessageManageService.saveCustomerApproval(Integer.parseInt(appKey),approvalVO.getApprovalCd(),approvalDetailList,approvalVO.getApprovalHuman());
		
    	} catch (Exception e) {
			log.error("审批异常,审批单号为:"+approveCode+""+e);
			writer = resp.getWriter();
    		writer.write("error");
    		writer.flush();
    		writer.close();
    		return ;
		}
    	log.info(">>>>>>>>>>>>审批单号成功，审批单号成功:"+approveCode);
		writer = resp.getWriter();
		writer.write("success");
		writer.flush();
		writer.close();
		return ;
	}
	
	/**
	 * 签名验证
	 * 
	 * @return
	 */
	private boolean checkSign(String approveCode,String appKey,String mobile,String result,String reason,String sign) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("approveCode", approveCode);
			map.put("appKey", appKey);
			map.put("mobile", mobile);
			map.put("result", result);
			map.put("reason", reason);
			return sign.equals(ServletSignUtil.buildSign(map, "85D2B8EC0CA91DB1E0D6AD486C169959")) ? true : false;
		} catch (Exception e) {
			log.error("验证错误" + e);
			return false;
		}

	}

	/**
	 * 获取去客户端IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getRemoteIP(HttpServletRequest request) {
		String ip = request.getHeader("clientip");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("x-forwarded-for");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;

	}

}
