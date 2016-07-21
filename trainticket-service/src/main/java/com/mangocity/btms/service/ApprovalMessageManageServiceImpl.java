package com.mangocity.btms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mangocube.corenut.commons.exception.ErrorCode;
import org.mangocube.corenut.commons.exception.ErrorRecordUtil;
import org.mangocube.corenut.scm.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.mangocity.api.ITrainOrderService;
import com.mangocity.api.ITrainPayInfoService;
import com.mangocity.api.ITrainPayService;
import com.mangocity.api.ITrainTicketService;
import com.mangocity.btms.api.IApprovalMessageManageService;
import com.mangocity.btms.api.ICorporationService;
import com.mangocity.btms.api.IMessageManageService;
import com.mangocity.btms.approval.ApprovalException;
import com.mangocity.btms.approval.adapter.common.MessageTypes;
import com.mangocity.btms.approval.adapter.model.SendApprovalParameter;
import com.mangocity.btms.approval.model.Approval;
import com.mangocity.btms.approval.model.ApprovalDetail;
import com.mangocity.btms.approval.model.ApprovalMan;
import com.mangocity.btms.approval.model.ApprovalTrainTicketOrder;
import com.mangocity.btms.approval.model.SelectedApprovalMan;
import com.mangocity.btms.approval.service.TrainTicketApprovalMessageService;
import com.mangocity.btms.approval.service.TrainTicketApprovalService;
import com.mangocity.btms.model.Corporation;
import com.mangocity.btms.organization.configuration.model.CustomDisplayService;
import com.mangocity.btms.organization.configuration.model.MessageConfiguration.ContentType;
import com.mangocity.btms.organization.configuration.model.ServiceType;
import com.mangocity.btms.organization.configuration.service.CorporationConfigService;
import com.mangocity.btms.projectmanagement.model.Project;
import com.mangocity.btms.projectmanagement.service.ProjectService;
import com.mangocity.btms.vo.TravelInfoVO;
import com.mangocity.easy.workflow.model.FlowNode;
import com.mangocity.enums.ApprovalType;
import com.mangocity.enums.GoodsType;
import com.mangocity.model.Order;
import com.mangocity.model.TrainPayInfo;
import com.mangocity.response.ResponseMessage;
import com.mangocity.vo.PayVo;
import com.mangocity.vo.TicketInfoVo;

/**
 * 消息管理类
 * 
 * @author hongxiaodong
 *
 */
public class ApprovalMessageManageServiceImpl implements IApprovalMessageManageService {

	Logger log = Logger.getLogger(ApprovalMessageManageServiceImpl.class);
	
    public enum ApprovalError {
	        @ErrorCode(comment = "Single the flow error!the parameter is ${1}")
	        SINGLE_FLOW_ERROR,
	        @ErrorCode(comment = "The parameter ${1} can't be null!the value is ${2}")
	        PARAM_NULL_ERROR
	}

	@Autowired
	private CorporationConfigService ducorporationConfigService;

	@Autowired
	private ProjectService duprojectService;

	@Autowired
	private ICorporationService ducorporationService;

	@Autowired
	private ITrainOrderService trainOrderService;

	@Autowired
	private TrainTicketApprovalService trainTicketApprovalService;

	@Autowired
	private TrainTicketApprovalMessageService trainTicketApprovalMessageService;

	@Autowired
	private IMessageManageService messageManageService;
	
	@Autowired
	private ITrainTicketService trainTicketService;
	
	@Autowired
	private ITrainPayInfoService trainPayInfoService;
	
	@Autowired
	private ITrainPayService trainPayService;

	public ResponseMessage sendApprovalMessage(Long orderId, TravelInfoVO travelInfoVO) throws Exception {
		
		System.out.println(">>>>>>>>>>>>>sendApprovalorderId>>>>>>>>>>>>" + orderId);

		boolean isNeedApproval;

		Corporation corporation = null;

		Project project;

		ResponseMessage resMessage = new ResponseMessage();

		Order order = trainOrderService.find(orderId);

		if (null == order) {

			resMessage.setCode(ResponseMessage.ERROR);

			resMessage.setMsg("订单不存在");

			return resMessage;
		}

		ApprovalTrainTicketOrder trainTicketOrders = createApprovalTrainTicketOrderByOrder(order);

		Map<FlowNode, List<ApprovalMan>> approvalManMap;

		corporation = ducorporationService.retrieveCorporationByMbrShipCd(order.getMemberCn());

		System.out.println(">>>>>>>>>>>>>corporation>>>>>>>>>>>>" + corporation.getCorporationId());

		project = duprojectService.retrieveProjectByCode(travelInfoVO.getProjectCode(), corporation.getCorporationId());

		if (project != null) {
			// 根据会籍号，项目ID判断该会员是否需要审批
			isNeedApproval = trainTicketApprovalService.isNeedApproval(order.getMemberCn(), project.getProId());
			// 获取审批人集合
			approvalManMap = trainTicketApprovalService.retrieveApprovalMan(order.getMemberCn(), project.getProId());
		} else {
			// 根据会籍号，判断该会员是否需要审批
			isNeedApproval = trainTicketApprovalService.isNeedApproval(order.getMemberCn(), 0);
			// 根据会籍号，获取审批人集合
			approvalManMap = trainTicketApprovalService.retrieveApprovalMan(order.getMemberCn(), 0);
		}

		log.info("会籍号【" + order.getMemberCn() + "】是否需要审批：" + isNeedApproval + " 出行性质：" + travelInfoVO.getTravelType());

		if (!isNeedApproval && !travelInfoVO.getTravelType().equals("N")) {

			resMessage.setCode(ResponseMessage.ERROR);

			resMessage.setMsg("订单无须审批");

			return resMessage;
		}

		log.info(">>>>>>>>>>>>>>>approvalManMap:" + approvalManMap.size() + ">>>>>>>>>>>>>>"
				+ corporation.getCorporationId());

		CustomDisplayService customDisplayService = (CustomDisplayService) ducorporationConfigService
				.retrieveServicesByCorporationIdAndType(corporation.getCorporationId(), ServiceType.CUSTOM_ORDER);

		String result = saveAssembleOrder(isNeedApproval, customDisplayService, approvalManMap, corporation, project,
				travelInfoVO, trainTicketOrders);

		log.info(">>>>>>>>>>>>>>>rest:" + result);

		return resMessage;

	}

	private ApprovalTrainTicketOrder createApprovalTrainTicketOrderByOrder(Order orderTicket) {

		ApprovalTrainTicketOrder trainTicketOrders = new ApprovalTrainTicketOrder();

		trainTicketOrders.setBookMember(orderTicket.getBookMember());

		trainTicketOrders.setCompanyCode(orderTicket.getCompanyCode());

		trainTicketOrders.setCostCenter(orderTicket.getCostCenter());

		trainTicketOrders.setCreateTime(orderTicket.getCreateTime());

		trainTicketOrders.setId(orderTicket.getId());

		trainTicketOrders.setMemberCn(orderTicket.getMemberCn());

		trainTicketOrders.setOrderCn(orderTicket.getOrderCn());

		trainTicketOrders.setOrderFrom(orderTicket.getOrderFrom());

		trainTicketOrders.setPaymentStatus(orderTicket.getPaymentMethod());

		trainTicketOrders.setStatus(orderTicket.getStatus());

		trainTicketOrders.setTravelType(orderTicket.getTravelType());

		// 暂测试用
		//trainTicketOrders.setsMScontent(
				//"尊敬的审批确认人，长耕周 预订的谢咏梅654009898767898765,2016-06-04 21:50  合肥-北京 硬卧（234.0元/人）服务费15.0元/人的差旅行程（审批单216085），订单2401736应付金额249.0元。如有问题请致电4006620088，请在20分钟内完成审批");

		trainTicketOrders.setEmailTitle("test");

		trainTicketOrders.setEmailTemplateCode("test");

		trainTicketOrders.setEmailResult("test");

		return trainTicketOrders;
	}

	private String getSMSContent(String orderCn, Long approvalId) {
		String smsMessage = "";
		try {
			smsMessage = messageManageService.viewApprovalSMSInfo(orderCn, approvalId);
		} catch (Exception e) {
			e.printStackTrace();
			smsMessage = "";
		}
		return smsMessage;
	}

	/**
	 * 组合审批单保存进数据库,后再更新订单状态
	 * 
	 * @param ticketOrder
	 * @throws ApprovalException
	 */
	public String saveAssembleOrder(boolean isNeedApproval, CustomDisplayService customDisplayService,
			Map<FlowNode, List<ApprovalMan>> approvalManMap, Corporation corporation, Project project,
			TravelInfoVO travelInfoVO, ApprovalTrainTicketOrder trainTicketOrders) throws ApprovalException {
		String saveApprovalResult = "success";

		String orderCd = trainTicketOrders.getOrderCn();// 订单号

		boolean isSaveAssembleOrder = isNeedApproval;

		log.info("会籍号【" + trainTicketOrders.getMemberCn() + "】是否需要保存审批单：" + isSaveAssembleOrder + "……");

		if (isSaveAssembleOrder) {
			boolean isEXEMPT_APPROVAL = (approvalManMap.size() == 1 && (approvalManMap.get("EXEMPT_APPROVAL") != null));
			try {
				// 开始创建审批单
				Approval approval = new Approval();
				approval.setMemberCd(trainTicketOrders.getMemberCn());
				if (project != null) {
					approval.setProjectId(project.getProId());
				} else {
					approval.setProjectId(0);
				}
				
				String smsApproveFlag = "";
				if(null != customDisplayService){
					smsApproveFlag = customDisplayService.getCheckNoteApproval();
				}
				if ("1".equals(smsApproveFlag)) {
					approval.setSendType(Approval.SendType.SM_EMAIL);
				} else {
					approval.setSendType(Approval.SendType.EMAIL);
				}
				approval.setCorporationId(corporation.getCorporationId());

				// 创建审批单详情
				ApprovalDetail approvalDetail = new ApprovalDetail();
				approvalDetail.setOrderCd(orderCd);

				approvalDetail.setBelongSystem(ApprovalDetail.System.TICKET);

				List<ApprovalDetail> approvalDetailList = new ArrayList<ApprovalDetail>();
				if (isEXEMPT_APPROVAL) {// 如果是免审
					approvalDetail.setResult(ApprovalDetail.Result.PASS);
				}
				approvalDetailList.add(approvalDetail);

				approval.setApprovalDetailList(approvalDetailList);// 加入审批单详情

				if (travelInfoVO.getApprovalManSet() != null && travelInfoVO.getApprovalManSet().size() != 0) {
					// 页面传进来的审批人个数必须和管控系统中配置的审批人个数相等
					log.info(">>>>>>>>>approval>>>>>>" + approvalManMap.size() + ">>>>>>>>>>"+ travelInfoVO.getApprovalManSet().size());
					if (travelInfoVO.getApprovalManSet().size() != approvalManMap.size()) {
						log.info("页面传进来的审批人个数和管控系统中配置的审批人个数不相等！");
						saveApprovalResult = "页面传进来的审批人个数和管控系统中配置的审批人个数不相等！";
						return saveApprovalResult;
					}
					List<SelectedApprovalMan> selectedApprovalManList = new ArrayList<SelectedApprovalMan>(
							travelInfoVO.getApprovalManSet());
					approval.setSelectedApprovalMan(selectedApprovalManList);
				}
				log.info(">>>>>>>>>approval>>>>>>" + approval);
				// 保存创建审批单记录
				try {
					approval = trainTicketApprovalService.createApproval(approval);
				} catch (Exception e) {
					log.info("创建审批人异常！！！" + e.getStackTrace());
					e.printStackTrace();
				}
				if (approval != null)
					log.info("保存审批人成功！");
				else
					log.info("保存审批人失败！");

				// 发送审批邮件。系统对审批，默认发的是送邮件审批。如果需要发送短信审批，则需要从页面传来标识进行判断
				SendApprovalParameter para = new SendApprovalParameter();
				para.setSendType(Approval.SendType.EMAIL.name());
				log.info("是否同时发送邮件审批和短信审批：" + travelInfoVO.getSmsAndEmailApprovalFlag());
				if ("Y".equals(travelInfoVO.getSmsAndEmailApprovalFlag())) {
					para.setSendType(Approval.SendType.SM_EMAIL.name());
				}
				//只发短信
				para.setSendType(Approval.SendType.SMS.name());

				para.setOperater("系统自动发送");

				System.out.println(">>>>>>>>>>>>approval.getRunningFlowInstanceId()>>>>>" + approval.getRunningFlowInstanceId());

				FlowNode nextNode = trainTicketApprovalService.retrieveCurrentNodeId(approval.getRunningFlowInstanceId());

				if (isEXEMPT_APPROVAL) {
					SelectedApprovalMan approvalHuman = new SelectedApprovalMan();
					ApprovalMan man = new ApprovalMan();
					man.setReferenceTypeCode("EXEMPT_APPROVAL");
					man.setMemberName("系统免审");
					approvalHuman.setApprovalMan(man);
					Map<String, ApprovalDetail> approvalMap = new HashMap<String, ApprovalDetail>();
					for (ApprovalDetail order : approvalDetailList) {
						approvalMap.put(order.getOrderCd(), order);
					}

					List<ApprovalDetail> approvalDetailResult = trainTicketApprovalService.saveCustomerApproval(
							(long) approval.getApproveId(), "", approvalDetailList, approvalHuman, trainTicketOrders);

					log.info(">>>>>>>>>>>>>>>>保存审批信息" + approvalDetailResult);

				} else {
					try {
						long nodeid = nextNode.getNodeId();
						trainTicketOrders.setsMScontent(getSMSContent(trainTicketOrders.getOrderCn(), approval.getApproveId()));
						log.info(">>>>>>>>>>>>>>>>短信审批信息" + trainTicketOrders.getsMScontent()+">>>>>>>>>getOrderCn:"+trainTicketOrders.getOrderCn());
						trainTicketApprovalMessageService.sendApprovalMsg(nodeid, approval, para,
								MessageTypes.MAIL_ASSEMBLE_ORDERMESSAGE_HANDEL.getCode(), ContentType.CONTENT,trainTicketOrders);

						log.info("发送邮件审批和短信审批成功！！！");
					} catch (Exception e) {
						log.info("该用户需要发送审批短信，发送审批短信或邮件异常！！！" + e.getStackTrace());
						e.printStackTrace();
					}
					// 修改订单状态为已提交审批
					// ticketOrder.setStatus(_StateConstant.ORDER_STATE_OSUBMITAPPROVING);
				}

			} catch (ServiceException e) {
				log.error("审批邮件发送异常", e);

				Order order = trainOrderService.findOrderByOrderCn(orderCd);
				
				//order.setStatus(status);
				
				order.setFrontRemark("审批邮件发送异常,请手工跟进!!");
				
				   String remark = order.getFrontRemark(); 
				   if (remark != null && !"".equals(remark.trim())) { 
					   order.setFrontRemark(remark+ "审批邮件发送异常,请手工跟进!!");
				   } else {
					   order.setFrontRemark("审批邮件发送异常,请手工跟进!!");
				   }
				   
				  trainOrderService.update(order);
				 
			}
		}
		return saveApprovalResult;
	}

	@Override
	public ResponseMessage humanApprovalOrder(Long orderId, ApprovalType approvalType) {

		ResponseMessage resMess = new ResponseMessage();

		Order order = trainOrderService.find(orderId);
		// 订单处理待审批状态
		/*if (!"3".equals(order.getStatus())) {
			resMess.setCode(ResponseMessage.ERROR);
			resMess.setMsg("订单不处于待审批状态");
			return resMess;
		}*/

		if (ApprovalType.PASS.equals(approvalType)) {// 订单审批通过
			order.setStatus("5");// 审核成功
			trainOrderService.updateOrderStatus(order);
			resMess.setCode(ResponseMessage.SUCCESS);
			resMess.setMsg("订单审核成功");

			return resMess;

		} else {// 订单审批拒绝
			order.setStatus("4");// 审核拒绝
			trainOrderService.updateOrderStatus(order);
			resMess.setCode(ResponseMessage.SUCCESS);
			resMess.setMsg("订单审核拒绝成功");

			return resMess;
		}
	}

	@Override
	public void saveCustomerApproval(long approvalId, String approvalCd, List<ApprovalDetail> approvalDetailList,
			SelectedApprovalMan selectedApprovalMan) throws ApprovalException {

		if (null == approvalDetailList || approvalDetailList.isEmpty()) {
			return;
		}
		String orderCn = "";
		Map<String, ApprovalDetail> approvalMap = new HashMap<String, ApprovalDetail>();
		for (ApprovalDetail order : approvalDetailList) {
			orderCn = order.getOrderCd();
			approvalMap.put(order.getOrderCd(), order);
		}
		Order order = trainOrderService.findOrderByOrderCn(orderCn);

		ApprovalTrainTicketOrder trainTicketOrders = createApprovalTrainTicketOrderByOrder(order);

		trainTicketOrders.setsMScontent(getSMSContent(trainTicketOrders.getOrderCn(), approvalId));

		List<ApprovalDetail> approvalDetailResult = null;
		try {
			approvalDetailResult = trainTicketApprovalService.saveCustomerApproval(approvalId,
					approvalCd, approvalDetailList, selectedApprovalMan, trainTicketOrders);
		} catch (ApprovalException e) {
			List<Object> list = ErrorRecordUtil.getErrorParameters(e.getErrorRec());
			if(null != list && list.size() > 0){
				String value = list.get(0).toString();
				if("pass".equals(value)){
					log.info("全部审批通过！");
					updateTicketOrderStatusByApprovalPass(approvalMap,approvalDetailList,"pass");// 修改票状态
				}else if("reject".equals(value)){
					log.info("全部审批不通过！");
					updateTicketOrderStatusByApprovalPass(approvalMap,approvalDetailList,"reject");// 修改票状态
				}
			}else{
				log.error(">>>>>>审批失败",e);
			}
		}
		updateTicketOrderStatusByApproval(approvalMap, approvalDetailResult);// 修改票状态
	}
	
	
	

	private void updateTicketOrderStatusByApproval(Map<String, ApprovalDetail> orderMap,
			List<ApprovalDetail> approvalOrders) throws ServiceException {
		if (approvalOrders == null || approvalOrders.isEmpty())
			return;
		for (ApprovalDetail approvalDetail : approvalOrders) {
			String orderCd = approvalDetail.getOrderCd();
			ApprovalDetail orderResult = orderMap.get(orderCd);
			if (orderResult == null)
				continue;
			Order order = trainOrderService.findOrderByOrderCn(orderCd);
			// 审批通过
			if (ApprovalDetail.Result.PASS.name().equals(orderResult.getResultCode())
					|| ApprovalDetail.Result.ADMIN_PASS.name().equals(orderResult.getResultCode())) {
				// 当为即时出票(I)时，状态改为已提交审核。当为预约出票(R)时，状态改为审批通过
				order.setStatus("5");// 审批成功
				try {
					ResponseMessage responseMessage = createHBPay(order.getId());
					if(ResponseMessage.ERROR.equals(responseMessage.getCode())){
						order.setStatus("7");
						order.setFrontRemark("出票失败");
					}
				} catch (Exception e) {
					log.error(">>>>>>通知出票失败",e);
					order.setStatus("7");
					order.setFrontRemark("出票失败");
				}
				
			} else if (ApprovalDetail.Result.REJECT.name().equals(orderResult.getResultCode())
					|| ApprovalDetail.Result.ADMIN_REJECT.name().equals(orderResult.getResultCode())) {
				order.setStatus("4");// 审批失败
			}

			trainOrderService.updateOrderStatus(order);// 修改状态
		}
	}
	
	private void updateTicketOrderStatusByApprovalPass(Map<String, ApprovalDetail> orderMap,
			List<ApprovalDetail> approvalDetailList,String value) throws ServiceException {
		if (approvalDetailList == null || approvalDetailList.isEmpty())
			return;
		for (ApprovalDetail approvalDetail : approvalDetailList) {
			String orderCd = approvalDetail.getOrderCd();
			ApprovalDetail orderResult = orderMap.get(orderCd);
			if (orderResult == null)
				continue;
			Order order = trainOrderService.findOrderByOrderCn(orderCd);
			log.info(">>>>>>>>>>>>>>出票订单ID："+order.getId());
			if("pass".equals(value)){
				// 审批通过
				order.setStatus("5");// 审批成功
				try {
					ResponseMessage responseMessage = createHBPay(order.getId());
					if(ResponseMessage.ERROR.equals(responseMessage.getCode())){
						order.setStatus("7");
						order.setFrontRemark("出票失败");
					}
				} catch (Exception e) {
					log.error(">>>>>>通知出票失败",e);
					order.setStatus("7");
					order.setFrontRemark("出票失败");
				}
			}else if("reject".equals(value)){
				order.setStatus("4");// 审批失败
			}
			trainOrderService.updateOrderStatus(order);// 修改状态
		}
	}
	
	
	private ResponseMessage createHBPay(Long orderId) throws Exception{
		ResponseMessage responseMessage = new ResponseMessage();
    	try {
    		List<TicketInfoVo> ticketList = trainTicketService.findTicketByOrderIdOrCd(orderId, null, "0",null);
    		if(null != ticketList && ticketList.size() > 0){
    			TicketInfoVo ticketInfoVo = ticketList.get(0);
    			List<Long> ticketIdList = new ArrayList<Long>();
    			ticketIdList.add(ticketInfoVo.getId());
    			//支付类型：0付款，1退款
    			List<TrainPayInfo> payInfoList = trainPayInfoService.findPayInfoByItemIdCn(orderId,"0",ticketIdList,GoodsType.TICKET.toString());
    			if(null != payInfoList && payInfoList.size() > 0){
    				TrainPayInfo trainPayInfo = payInfoList.get(0);
    				if("4".equals(trainPayInfo.getStatus())){//已支付才可以出票
						Map<String,String> map = new HashMap<String,String>();//map这里用于去除重复的号百号，以免重复请求
			    		for(TicketInfoVo vo : ticketList){
			    			String hbOrderId = vo.getHbOrderId();
			    			String ticketNo = ticketInfoVo.getTicketNo();
			    			if(!StringUtils.isEmpty(hbOrderId) && !StringUtils.isEmpty(ticketNo)){
			    				if(StringUtils.isEmpty(map.get(hbOrderId))){
			    					PayVo pay = new PayVo();
				    				pay.setHbOrderCn(hbOrderId);
				    				pay.setType("2");//业务类型：2 - 火车票订单；5 - 火车票改签
				    				pay.setAmount(vo.getHbPrice());
				    				trainPayService.createHBPay(pay);
			    				}
			    				map.put(hbOrderId, hbOrderId);
			    			}
			    		}
			    		responseMessage.setCode(ResponseMessage.SUCCESS);
						responseMessage.setMsg("成功！");
    				}else{
    					responseMessage.setCode(ResponseMessage.ERROR);
    					responseMessage.setMsg("没有支付不能出票！");
    				}
    			}else{
		    		responseMessage.setCode(ResponseMessage.ERROR);
					responseMessage.setMsg("createHBPay数据异常！");
		    	}
    		}else{
	    		responseMessage.setCode(ResponseMessage.ERROR);
				responseMessage.setMsg("createHBPay数据异常！");
	    	}
		} catch (Exception e) {
			e.printStackTrace();
			responseMessage.setCode(ResponseMessage.ERROR);
			responseMessage.setMsg("createHBPay接口异常！");
		}
    	
    	return responseMessage;
    }

}
