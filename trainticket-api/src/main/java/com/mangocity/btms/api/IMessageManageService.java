package com.mangocity.btms.api;


import com.mangocity.vo.MailMessage;
import com.mangocity.vo.TicketInfoVo;

import java.util.List;

import com.mangocity.btms.adpater.vo.ApprovalManVO;
import com.mangoctiy.communicateservice.domain.Fax;
import com.mangoctiy.communicateservice.domain.Mail;
import com.mangoctiy.communicateservice.domain.Sms;

/**
 * 消息管理服务类
 * 
 * @author hongxiaodong
 *
 */
public interface IMessageManageService {
	
	/**
	 * 发送出票短信
	 * @param ticket
	 * @param membercd
	 * @param phone
	 * @return
	 */
	public boolean sendissuedSMS(TicketInfoVo ticket,String membercd,String phone);

	/**
	 * 同步发送短信
	 * 
	 * @param sms
	 * @return
	 * @throws Exception
	 */
	public Long sendSMS(Sms sms) throws Exception;

	/**
	 * 异步发送短信
	 * 
	 * @param sms
	 * @return
	 * @throws Exception
	 */
	public void sendAsyncSMS(Sms sms) throws Exception;

	/**
	 * 同步发送邮件
	 * 
	 * @param mail
	 * @return
	 * @throws Exception
	 */
	public Long sendMail(Mail mail) throws Exception;

	/**
	 * 异步发送邮件
	 * 
	 * @param mail
	 * @throws Exception
	 */
	public void sendAsyncMail(Mail mail) throws Exception;

	/**
	 * 同步发送传真
	 * 
	 * @param fax
	 * @return
	 * @throws Exception
	 */
	public Long sendFax(Fax fax) throws Exception;

	/**
	 * 异步发送传真
	 * 
	 * @param fax
	 * @throws Exception
	 */
	public void sendAsyncFax(Fax fax) throws Exception;
	
	/**
     * 取得信息接收人
     */
    public List<ApprovalManVO> getMessageReceivers(String memberCd,Long orderId) throws Exception;
    
    
    /**
     * 出票短信模版
     * @param orderCn
     * @return
     * @throws Exception
     */
    public String viewIssuedSMSInfo(String orderCn)throws Exception;
    
    
    /**
     * 根据订单发送统一已出票票号短信
     * @param orderCn
     * @param phone
     * @return
     * @throws Exception
     */
    public boolean sendissuedSMS(String orderCn,String phone)throws Exception;
    
    
    /**
     * 发送模板邮件
     * @param membercd
     * @param mailMessage
     * @param mailTo
     * @param templateNum
     * @param messageTypeCode
     * @return
     * @throws Exception
     */
    public boolean sendEmail(String membercd,MailMessage mailMessage, String[] mailTo,int templateNum,String messageTypeCode) throws Exception;
    
    
    /**
     * 发送异步单个短信内容
     * @param smsCount
     * @param phone
     * @throws Exception
     */
    public boolean sendSingleSMS(String smsCount,String phone);
    
    
    
    /**
     * 审批短信内容
     * @param orderCn
     * @return
     * @throws Exception
     */
    public String viewApprovalSMSInfo(String orderCn,long approveId)throws Exception;
    
    

}
