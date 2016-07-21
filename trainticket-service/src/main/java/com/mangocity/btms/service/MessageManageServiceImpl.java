package com.mangocity.btms.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.mangocity.api.IContactService;
import com.mangocity.api.IMessageTemplateService;
import com.mangocity.api.ITrainOrderService;
import com.mangocity.api.ITrainTicketService;
import com.mangocity.btms.adpater.service.HierarchyArchitectureAdapterService;
import com.mangocity.btms.adpater.vo.ApprovalManVO;
import com.mangocity.btms.api.ICorporationManageService;
import com.mangocity.btms.api.IMemberManageService;
import com.mangocity.btms.api.IMessageManageService;
import com.mangocity.btms.api.IOrganizationConfigurationManageService;
import com.mangocity.btms.core.manager.HierarchyArchitectureManager;
import com.mangocity.btms.core.model.hierarchy.HierarchyArchitecture;
import com.mangocity.btms.organization.configuration.model.CustomDisplayService;
import com.mangocity.btms.organization.configuration.model.MessageConfiguration;
import com.mangocity.btms.organization.configuration.model.ServiceType;
import com.mangocity.btms.vo.RoleTypes;
import com.mangocity.enums.MessageType;
import com.mangocity.mbr.corporation.model.Corporation;
import com.mangocity.member.adapter.model.Member;
import com.mangocity.member.adapter.model.MemberShipInfo;
import com.mangocity.member.adapter.model.NaturalPerson;
import com.mangocity.member.adapter.model.PersonEmail;
import com.mangocity.member.adapter.model.SecretaryType;
import com.mangocity.member.adapter.service.MemberAdapterService;
import com.mangocity.model.Contact;
import com.mangocity.model.MessageTemplate;
import com.mangocity.model.Passenger;
import com.mangocity.util.StringUtil;
import com.mangocity.vo.MailMessage;
import com.mangocity.vo.MailTicket;
import com.mangocity.vo.OrderTicketPaInfoVo;
import com.mangocity.vo.TicketInfoVo;
import com.mangoctiy.communicateservice.CommunicaterService;
import com.mangoctiy.communicateservice.domain.Fax;
import com.mangoctiy.communicateservice.domain.Mail;
import com.mangoctiy.communicateservice.domain.Sms;

/**
 * 消息管理服务类
 * 
 * @author hongxiaodong
 *
 */
public class MessageManageServiceImpl implements IMessageManageService {
	Logger log = Logger.getLogger(MessageManageServiceImpl.class);

	@Autowired
	private ICorporationManageService corporationManageService;

	@Autowired
	private IOrganizationConfigurationManageService organizationConfigurationManageService;

	@Autowired
	private IMessageTemplateService messageTemplateService;

	@Autowired
	private CommunicaterService communicaterService;

	@Autowired
	private TaskExecutor taskExecutor;
	
	@Autowired
	private IMemberManageService memberManageService;
	
	@Autowired
	private HierarchyArchitectureAdapterService hierarchyArchitectureAdapterService;
	
	@Autowired
	private ITrainTicketService trainTicketService;
	
	@Autowired
	private IContactService contactService;
	
	@Autowired
	private MemberAdapterService dumemberAdapterService;
	
	@Autowired
	private ITrainOrderService trainOrderService;
	
	@Autowired
	private HierarchyArchitectureManager duhierarchyArchitectureManager;
	
	@Override
	public boolean sendissuedSMS(TicketInfoVo ticket, String membercd, String phone) {
		if (null == ticket || null == membercd || null == phone) {
			log.error("发送的内容消息为空");
			return false;
		}
		/*log.info("获取公司信息.........................membercd:" + membercd);
		Corporation corporation = corporationManageService.retrieveCorporationByMbrShipCd(membercd);
		// 找出该会员所在公司在TMC中配置的出票短信模版编码
		List<MessageConfiguration> messageConfigurationList = organizationConfigurationManageService
				.retrieveMessageConfigurationsByCorporationId(corporation.getCorporationId());
		Integer templateId = null;
		for (MessageConfiguration mc : messageConfigurationList) {
			log.info("循环打印公司配置的消息模版-------------------------getMsgType4Str:" + mc.getMsgType4Str());
			log.info("循环打印公司配置的消息模版-------------------------getTemplateId:" + mc.getTemplateId());
			log.info("公司配置的出票短信模版......................templteId:" + mc.getTemplateId());
			templateId = (int) mc.getTemplateId();
		}*/

		MessageTemplate messageTemplate = getMessageTemplateByTemplateId(10, MessageType.SMS);

		String smsCount = messageTemplate.createIssuedSMSTemplate(ticket);// 获取发送短信内容
		
		log.info(">>>>>>>>>>>>>出票短信内容："+smsCount+">>>>>phone:"+phone);

		Sms sms = new Sms();
		sms.setTo(new String[] { phone });
		sms.setMessage(smsCount);

		try {
			sendAsyncSMS(sms);
		} catch (Exception e) {
			log.error(">>>>>>>>>>>发送出票短信失败,发送手机号为:" + phone + "发送内容为:" + smsCount, e);
			return false;
		}
		
		log.info(">>>>>>>>>>>>出票短信发送成功,发送手机号为:" + phone + "发送内容为:" + smsCount);

		return true;
	}
	
	/**
     * 发送邮件
     *
     * @return
     */
	@Override
    public boolean sendEmail(String membercd,MailMessage mailMessage, String[] mailTo,int templateNum,String messageTypeCode) throws Exception {
    	//messageTypeCode=trainTicketConfirm火车票审批邮件（有电子链接）
    	//messageTypeCode=trainTicketOrder火车票审批邮件
    	if(null != mailMessage){
    		String orderCn = mailMessage.getOrderCn();
            try {
                Corporation corporation = null;
				try {
					corporation = corporationManageService.retrieveCorporationByMbrShipCd(membercd);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
                String serviceEmail = "";
                if (corporation != null) {
                    CustomDisplayService customDisplayService = null;
					try {
						customDisplayService = (CustomDisplayService) organizationConfigurationManageService.retrieveServicesByCorporationIdAndType(corporation.getCorporationId(), ServiceType.CUSTOM_ORDER);
					} catch (Exception e) {
						e.printStackTrace();
					}
                    if (customDisplayService != null) {
                        serviceEmail = customDisplayService.getMgServiceEmail();
                        log.info("sendEmail serviceEmail :" + serviceEmail);
                    }
                    if (serviceEmail != null && !"".equals(serviceEmail)) {
                        serviceEmail = customDisplayService.getMgServiceEmail();
                    } else {
                        serviceEmail = "corporate@mangocity.com";
                    }
                }else{
                	serviceEmail = "corporate@mangocity.com";
                }
                log.info("sendEmail serviceEmail :" + serviceEmail);
                // 找出该会员所在公司在TMC中配置的出票短信模版编码
                /*List<MessageConfiguration> messageConfigurationList = organizationConfigurationManageService
        				.retrieveMessageConfigurationsByCorporationId(corporation.getCorporationId());
        		Integer templateId = null;
        		for (MessageConfiguration mc : messageConfigurationList) {
        			log.info("循环打印公司配置的消息模版-------------------------getMsgType4Str:" + mc.getMsgType4Str());
        			log.info("循环打印公司配置的消息模版-------------------------getTemplateId:" + mc.getTemplateId());
        			templateId = (int) mc.getTemplateId();
        		}
        		*/
        		//corporation.getZhName();
                MessageTemplate messageTemplate = getMessageTemplateByTemplateId(templateNum, MessageType.MAIL);
            	//查询订单type=0
				//订单状态为出票status=8
				List<TicketInfoVo> ticketList = trainTicketService.findTicketInfoByOrderCd(orderCn, "0","8");
        		List<MailTicket> list = new ArrayList<MailTicket>();
        		BigDecimal allPrice = new BigDecimal("0.0");
        		Integer num = 1;
        		for(TicketInfoVo ticket :ticketList){
        			MailTicket itm = new MailTicket();
        			itm.setNo(num.toString());
        			itm.setOrigStationName(ticket.getOrigStationName());
        			itm.setDestStationName(ticket.getDestStationName());
        	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        	        itm.setStartDate(ticket.getStartTime() != null ? format.format(ticket.getStartTime()) : "");
        	        itm.setStartTime(ticket.getCcsj());
        	        itm.setEndTime(ticket.getDdsj());
        	        itm.setTrainCn(ticket.getTrainCn());
        	        itm.setTrainType(ticket.getTrainType());
        	        itm.setSeatType(ticket.getSeatType());
        	        itm.setCxin(ticket.getCxin());
        	        BigDecimal price = ticket.getSalePrice() != null ? ticket.getSalePrice():new BigDecimal("0.0");
        	        itm.setPrice(price.toString());
        	        itm.setName(ticket.getPassenger() != null ? ticket.getPassenger().getName() : "");
        	        BigDecimal fee = ticket.getFee() != null ? ticket.getFee():new BigDecimal("0.0");
        	        itm.setFee(fee.toString());
        	        list.add(itm);
        	        allPrice = allPrice.add(price).add(fee);
        	        num++;
        		}
        		mailMessage.setTicketList(list);
        		mailMessage.setAllPrice(allPrice.toString());
        		String result = messageTemplate.createMailTemplate(mailMessage);
        		
                log.info("sendEmail result :" + result);
               
                Mail mail = new Mail();
                Map<String, String> inlines = new HashMap<String, String>();
                inlines.put("mangoLogo", "mango.jpg");
                //mail.setInlines(inlines);
                mail.setTo(mailTo);
                mail.setApplicationName("tour");
                // 发件人(必需)
                // 此邮箱从company取
                mail.setFrom(serviceEmail);
                // 调用模板(必需)，业务系统根据情况，确定使用不同的模板
                mail.setTemplateFileName(messageTypeCode);
                // 邮件主题（必需）
                mail.setSubject(orderCn);
                mail.setXml(result);
                mail.setUserLoginId(membercd);
                try {
    				sendAsyncMail(mail);
    			} catch (Exception e) {
    				e.printStackTrace();
    				return false;
    			}
            } catch (Exception e) {
                log.error("sendEmail error", e);
                return false;
            }
            return true;
    	}else{
    		return false;
    	}
    }

	/**
	 * 根据管控制ID获取相应的模板，如果没有找到就获取一条默认 如果模板ID为空就默认取第一条
	 * 
	 * @param templateId
	 * @return
	 */
	public MessageTemplate getMessageTemplateByTemplateId(Integer templateId, MessageType type) {
		if (null == templateId) {
			return messageTemplateService.findByType(type).get(0);
		}

		MessageTemplate messageTem = messageTemplateService.findBytemplateNum(templateId);

		if (null == messageTem) {
			return messageTemplateService.findByType(type).get(0);
		}

		return messageTem;

	}

	@Override
	public Long sendSMS(Sms sms) throws Exception {
		if (null == sms || null == sms.getMessage() || sms.getTo().length == 0) {
			log.error("发送手机号及内容不能为空");
			throw new Exception("发送手机号及内容不能为空");
		}
		for (String phone : sms.getTo()) {
			if (!StringUtil.isPhone(phone)) {
				log.error("发送手机号不符合规则,手机号:" + phone);
				throw new Exception("发送手机号不符合规则,手机号:" + phone);
			}
		}
		long response = communicaterService.sendSms(sms);
		return response;
	}

	@Override
	public void sendAsyncSMS(final Sms sms) throws Exception {
		if (null == sms || null == sms.getMessage() || sms.getTo().length == 0) {
			log.error("发送手机号及内容不能为空");
			throw new Exception("发送手机号及内容不能为空");
		}
		for (String phone : sms.getTo()) {
			if (!StringUtil.isPhone(phone)) {
				log.error("发送手机号不符合规则,手机号:" + phone);
				throw new Exception("发送手机号不符合规则,手机号:" + phone);
			}
		}
		taskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				communicaterService.sendSms(sms);
			}
		});
	}

	@Override
	public Long sendMail(Mail mail) throws Exception {
		if (null == mail || null == mail.getTo() || mail.getTo().length == 0) {
			log.error("发送邮箱及内容不能为空");
			throw new Exception("发送邮箱及内容不能为空");
		}
		for (String mailStr : mail.getTo()) {
			if (!StringUtil.isMail(mailStr)) {
				log.error("发送邮箱不符合规则,手机号:" + mailStr);
				throw new Exception("发送邮箱不符合规则,手机号:" + mailStr);
			}
		}
		return communicaterService.sendEmail(mail);
	}

	@Override
	public void sendAsyncMail(final Mail mail) throws Exception {
		if (null == mail || null == mail.getTo() || mail.getTo().length == 0) {
			log.error("发送邮箱及内容不能为空");
			throw new Exception("发送邮箱及内容不能为空");
		}
		for (String mailStr : mail.getTo()) {
			if (!StringUtil.isMail(mailStr)) {
				log.error("发送邮箱不符合规则,手机号:" + mailStr);
				throw new Exception("发送邮箱不符合规则,手机号:" + mailStr);
			}
		}

		taskExecutor.execute(new Runnable() {

			@Override
			public void run() {
				communicaterService.sendEmail(mail);
			}
		});

	}

	@Override
	public Long sendFax(Fax fax) throws Exception {
		if (null == fax || null == fax.getTo() || fax.getTo().length == 0) {
			log.error("发送传真及内容不能为空");
			throw new Exception("发送传真及内容不能为空");
		}
		for (String faxStr : fax.getTo()) {
			if (!StringUtil.isFax(faxStr)) {
				log.error("发送传真不符合规则,传真号:" + faxStr);
				throw new Exception("发送传真不符合规则,传真号:" + faxStr);
			}
		}
		return communicaterService.sendFax(fax);
	}

	@Override
	public void sendAsyncFax(final Fax fax) throws Exception {
		if (null == fax || null == fax.getTo() || fax.getTo().length == 0) {
			log.error("发送传真及内容不能为空");
			throw new Exception("发送传真及内容不能为空");
		}
		for (String faxStr : fax.getTo()) {
			if (!StringUtil.isFax(faxStr)) {
				log.error("发送传真不符合规则,传真号:" + faxStr);
				throw new Exception("发送传真不符合规则,传真号:" + faxStr);
			}
		}
		taskExecutor.execute(new Runnable() {
			@Override
			public void run() {

				communicaterService.sendFax(fax);
			}
		});
	}
	
	/**
     * 取得信息接收人
     */
    public List<ApprovalManVO> getMessageReceivers(String memberCd,Long orderId) throws Exception{
        List<ApprovalManVO> messageReceivers = new ArrayList<ApprovalManVO>();
            // 取会员
        Member member = dumemberAdapterService.retrieveMemberByMbrShipCd(memberCd);
        if (member == null) {
            return messageReceivers;
        }
        MemberShipInfo mbrShipInfo = member.getMemberShipInfoList().get(0);
        NaturalPerson person = member.getPerson();
        ApprovalManVO approvalManVO = new ApprovalManVO();
        approvalManVO.setIdentityType(RoleTypes.MEMBER);
        approvalManVO.setName(getMemberFullName(person));
        approvalManVO.setMobile(StringUtil.changMobileAndEmail(mbrShipInfo.getMemberShipCode(), person.getMobileNo()));
        approvalManVO.setEmail(retrieveMemberValidEmail(member));
        approvalManVO.setFax(person.getFax());
        messageReceivers.add(approvalManVO);
        List<Member> secMemberList;

        //先找会员商旅秘书
        secMemberList = dumemberAdapterService.retrieveMemberSecretary(SecretaryType.MEMBER, mbrShipInfo.getMemberShipCode());

        //会员商旅秘书找不到时找部门秘书
        if (secMemberList == null || secMemberList.isEmpty()) {
            secMemberList = dumemberAdapterService.retrieveMemberSecretary(SecretaryType.DEPARTMENT, String.valueOf(mbrShipInfo.getDeptId()));
        }

        //部门秘书找不到时找上级部门
        if (secMemberList == null || secMemberList.isEmpty()) {
            List<Long> deptIds = duhierarchyArchitectureManager.retrieveHirDeptIdsByIdAndType(mbrShipInfo.getDeptId(), HierarchyArchitecture.HierarchyType.DEPARTMENT);
            log.debug("根据部门ID=" + mbrShipInfo.getDeptId() + "查到其父部门个数为:" + deptIds.size());
            for (long parentDeptId : deptIds) {
                log.debug("-----父部门ID-----" + parentDeptId);
                secMemberList = dumemberAdapterService.retrieveMemberSecretary(SecretaryType.DEPARTMENT, String.valueOf(parentDeptId));
                if (secMemberList != null && !secMemberList.isEmpty()) break;
            }
        }
        //找公司商旅秘书
        if (secMemberList == null || secMemberList.isEmpty()) {
            secMemberList = dumemberAdapterService.retrieveMemberSecretary(SecretaryType.CORPORATION, String.valueOf(mbrShipInfo.getCorporationId()));
        }


        if (secMemberList != null && !secMemberList.isEmpty()) {
            for (Member secMember : secMemberList) {
                ApprovalManVO secApprovalManVO = new ApprovalManVO();
                secApprovalManVO.setIdentityType(RoleTypes.SECRETARY);
                secApprovalManVO.setName(getMemberFullName(secMember.getPerson()));
                secApprovalManVO.setMobile(StringUtil.changMobileAndEmail(secMember.getMemberShipInfoList().get(0).getMemberShipCode(), secMember.getPerson().getMobileNo()));
                secApprovalManVO.setEmail(retrieveMemberValidEmail(secMember));
                secApprovalManVO.setFax(secMember.getPerson().getFax());
                messageReceivers.add(secApprovalManVO);
            }
        }
        // 取联系人
        Contact contact = contactService.findContactByOrderId(orderId);
        if(contact != null){
        	 ApprovalManVO linkManApprovalManVo = new ApprovalManVO();
             linkManApprovalManVo.setIdentityType(RoleTypes.LINKMAN);
             linkManApprovalManVo.setName(contact.getName());
             linkManApprovalManVo.setMobile(contact.getForeignMobile());
             linkManApprovalManVo.setEmail(contact.getMail());
             messageReceivers.add(linkManApprovalManVo);
        }

        // 取乘机人
        List<TicketInfoVo> ticketList = trainTicketService.findTicketByOrderIdOrCd(orderId, null, null,null);
        if(null != ticketList && ticketList.size() > 0){
        	for(TicketInfoVo vo : ticketList){
        		Passenger passenger = vo.getPassenger();
        		 ApprovalManVO passman = new ApprovalManVO();
                 passman.setIdentityType(RoleTypes.PASSENGER);
                 passman.setName(passenger.getName());
                 passman.setMobile(passenger.getMobile());
                 if (!messageReceivers.contains(passman)) {
                     messageReceivers.add(passman);
                 }
        	}
        }
        return messageReceivers;
    }
    
    private String retrieveMemberValidEmail(Member member) {
        if (member == null || member.getPerson() == null
                || member.getPerson().getPersonEmailList() == null
                || member.getPerson().getPersonEmailList().size() == 0) {
            return null;
        }

        for (PersonEmail personEmail : member.getPerson().getPersonEmailList()) {
            if (PersonEmail.EmailStatus.VALID.equals(personEmail.getEmailStatus())) {
                return personEmail.getEmail();
            }
        }
        return null;
    }
    
    private String getMemberFullName(NaturalPerson person) {
        String fullName = person.getFamilyName() + person.getName();
        if (!StringUtils.isEmpty(fullName)) return fullName;
        String engName = person.getLastName();
        String engmidname = person.getMiddleName();
        String engsurname = person.getFirstName();
        if (engName != null && !"".equals(engName)) {
            fullName = engName;
        }
        if (engsurname != null && !"".equals(engsurname)) {
            if ("".equals(fullName)) {
                fullName = engsurname;
            } else {
                fullName = fullName + "/" + engsurname;
            }
        }
        if (engmidname != null && !"".equals(engmidname)) {
            if ("".equals(fullName)) {
                fullName = engmidname;
            } else {
                fullName = fullName + " " + engmidname;
            }
        }

        return fullName;
    }

	@Override
	public String viewIssuedSMSInfo(String orderCn) throws Exception {
		
		//根据订单查询出票票号信息
		List<TicketInfoVo> ticketInfos = trainTicketService.findTicketInfoByOrderCd(orderCn, "0", "8");
		
		MessageTemplate messageTemplate = messageTemplateService.findBytemplateNum(13);
		
		if(null == ticketInfos || ticketInfos.isEmpty()){
			
			return null;
		}else if(1 == ticketInfos.size()){
			
			return  messageTemplate.createIssuedSMSTemplate(ticketInfos.get(0));// 获取发送短信内容
		}else{
			
			return messageTemplate.createIssuedSMSTemplate(ticketInfos);
		}
		
	}
	

	@Override
	public boolean sendissuedSMS(String orderCn, String phone) throws Exception {
		
		String smsCount = viewIssuedSMSInfo(orderCn);// 获取发送短信内容
		
		log.info(">>>>>>>>>>>>>出票订单短信内容："+smsCount+">>>>>phone:"+phone+">>>>>orderCn:"+orderCn);

		Sms sms = new Sms();
		sms.setTo(new String[] { phone });
		sms.setMessage(smsCount);
		try {
			sendAsyncSMS(sms);
		} catch (Exception e) {
			log.error(">>>>>>>>>>>发送出票短信失败,发送手机号为:" + phone + "发送内容为:" + smsCount, e);
			return false;
		}
		
		log.info(">>>>>>>>>>>>出票短信发送成功,发送手机号为:" + phone + "发送内容为:" + smsCount);
		return true;
	}

	@Override
	public boolean sendSingleSMS(String smsCount, String phone) {
		Sms sms = new Sms();
		sms.setTo(new String[] { phone });
		sms.setMessage(smsCount);
		try {
			sendAsyncSMS(sms);
		} catch (Exception e) {
			log.error(">>>>>>>>>>>发送出票短信失败,发送手机号为:" + phone + "发送内容为:" + smsCount, e);
			return false;
		}
		
		log.info(">>>>>>>>>>>>出票短信发送成功,发送手机号为:" + phone + "发送内容为:" + smsCount);
		return true;
		
	}

	@Override
	public String viewApprovalSMSInfo(String orderCn, long approveId) throws Exception {
		
		OrderTicketPaInfoVo orderTicketPaInfoVo = trainOrderService.queryOrderTicketByOrderCn(orderCn);
		
		if(null == orderTicketPaInfoVo || null == orderTicketPaInfoVo.getOrderCn()){
			return "";
		}
		
		List<MessageTemplate> messageTemplates = messageTemplateService.findByType(MessageType.APPROVAL_SMS);
		
		if(null == messageTemplates || messageTemplates.isEmpty()){
			return "";
		}
		
		MessageTemplate messageTemplate = messageTemplates.get(0);
		
		return messageTemplate.createApprovalSMSTemplate(orderTicketPaInfoVo, approveId);
	}

}
