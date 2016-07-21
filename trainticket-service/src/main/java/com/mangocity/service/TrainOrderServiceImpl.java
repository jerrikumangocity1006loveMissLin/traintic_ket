package com.mangocity.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mangocity.api.IMessageReveiverService;
import com.mangocity.api.IPurchaseItemService;
import com.mangocity.api.IPurchaseService;
import com.mangocity.api.ITrainOrderService;
import com.mangocity.api.ITrainPayDetailService;
import com.mangocity.api.ITrainPayInfoService;
import com.mangocity.api.ITrainPayService;
import com.mangocity.api.ITrainTicketService;
import com.mangocity.btms.api.IMessageManageService;
import com.mangocity.common.HttpClientUtil;
import com.mangocity.constant.CredentialsMap;
import com.mangocity.constant.OrderStatusConstantMap;
import com.mangocity.constant.RequestResourceMap;
import com.mangocity.enums.GoodsType;
import com.mangocity.mapper.AccountMapper;
import com.mangocity.mapper.ChargeMapper;
import com.mangocity.mapper.ContactMapper;
import com.mangocity.mapper.DeliveryMapper;
import com.mangocity.mapper.FrequentTravellerMapper;
import com.mangocity.mapper.PassengerMapper;
import com.mangocity.mapper.TrainOrderItemMapper;
import com.mangocity.mapper.TrainOrderMapper;
import com.mangocity.mapper.TrainPayInfoMapper;
import com.mangocity.mapper.TrainTicketMapper;
import com.mangocity.model.Account;
import com.mangocity.model.Charge;
import com.mangocity.model.Contact;
import com.mangocity.model.Delivery;
import com.mangocity.model.FrequentTraveller;
import com.mangocity.model.MessageReceiver;
import com.mangocity.model.Order;
import com.mangocity.model.OrderItem;
import com.mangocity.model.Passenger;
import com.mangocity.model.PurchaseItem;
import com.mangocity.model.Ticket;
import com.mangocity.model.TrainPay;
import com.mangocity.model.TrainPayDetail;
import com.mangocity.model.TrainPayInfo;
import com.mangocity.response.ResponseMessage;
import com.mangocity.service.base.BaseServiceImpl;
import com.mangocity.util.DateTimeUtils;
import com.mangocity.util.JsonUtil;
import com.mangocity.vo.HBOrderVo;
import com.mangocity.vo.OrderBasisVo;
import com.mangocity.vo.OrderDetailBean;
import com.mangocity.vo.OrderItemVo;
import com.mangocity.vo.OrderTicketPaInfoVo;
import com.mangocity.vo.OrderTicketQuery;
import com.mangocity.vo.OrderVo;
import com.mangocity.vo.PageOrderParameter;
import com.mangocity.vo.PageQueryResult;
import com.mangocity.vo.PassengerVo;
import com.mangocity.vo.PayDetailBean;
import com.mangocity.vo.PayVo;
import com.mangocity.vo.TicketInfoVo;
import com.mangocity.vo.TicketVo;

public class TrainOrderServiceImpl extends BaseServiceImpl<Order> implements ITrainOrderService {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private TrainOrderMapper trainOrderMapper;
	@Autowired
	private TrainTicketMapper trainTicketMapper;
	@Autowired
	private TrainOrderItemMapper trainOrderItemMapper;
	@Autowired
	private ContactMapper contactMapper;
	@Autowired
	private ChargeMapper chargeMapper;
	@Autowired
	private DeliveryMapper deliveryMapper;
	@Autowired
	private PassengerMapper passengerMapper;
	@Autowired
	private ITrainPayInfoService trainPayInfoService;
	@Autowired
	private ITrainPayService trainPayService;
	@Autowired
	private IPurchaseService purchaseService;	
	@Autowired
	private ITrainPayDetailService payDetailService;
	@Autowired
	private FrequentTravellerMapper frequentTravellerMapper;
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private ITrainTicketService trainTicketService;
/*	@Autowired
	private IPassengerManageService passengerManagerService;*/
	@Autowired
	private IPurchaseItemService purchaseItemService;
	@Autowired
	private IMessageReveiverService messageReveiverService;
	@Autowired
	private TrainPayInfoMapper trainPayInfoMapper;
	@Autowired
	private IMessageManageService messageService;
	@Resource
	private MessageSource messageSource;

	@Override
	public ResponseMessage createTrainOrder(OrderVo orderVo) {
		long start = System.currentTimeMillis();
		ResponseMessage responseMessage = new ResponseMessage();
		try {
			if (checkIsNewOrder(orderVo)) {
				Date orderDate = new Date(); // 定义全局下单时间
				Order order = saveOrder(orderVo.getOrder(), orderDate);// 保存订单
				if (order != null && order.getId() != null) {
					Contact contact = orderVo.getContact();
					if(null==contact.getIsForeign()){//因前端checbox的原因
						contact.setIsForeign("0");
					}
					StringBuffer stBuffer = new StringBuffer();
					List<Long> succeedTicketId = new ArrayList<>();
					PayDetailBean payDetail = new PayDetailBean(); 
					List<PassengerVo> succeedPassengers = new ArrayList<>();
					contact.setOrderId(order.getId());
					contactMapper.save(contact);// 保存联系人
					List<OrderItemVo> orderItemVos = orderVo.getOrderItemsVos();
					List<Long> orderItemIds = new LinkedList<>();
					String qqly = RequestResourceMap.buildMap().get(order.getOrderFrom());//请求来源
					String cllx = order.getTravelType();//差旅类型
					for (OrderItemVo itemVo : orderItemVos) {
						Long orderItemId = saveOrderItems(itemVo, order.getId(), orderDate);// 保存订单项
						orderItemIds.add(orderItemId);
						// saveTrainInvoices(itemVo.getTrainInvoices(),orderItemId);//保存发票
						for (TicketVo ticketVo : itemVo.getTrainTicketVos()) {
							for (PassengerVo passengerVo : ticketVo.getPassengerVos()) {
								ticketVo.setCllx(cllx);
								ticketVo.setQqly(qqly);
								Long passengerId = savePassenger(passengerVo, orderDate);// 保存乘客信息
								if (passengerId == null) {
									logger.info("-------保存乘客时异常-----");
									continue;
								} else {
									String saveResult = saveTicket(ticketVo, order.getId(), passengerId, orderItemId, passengerVo, contact,succeedTicketId,payDetail,orderDate);// 保存火车票
									stBuffer.append(saveResult);
									if(StringUtils.isBlank(saveResult)){
										succeedPassengers.add(passengerVo);
									}
									saveAccount(passengerVo, passengerVo.getAccountType(), orderDate);
								}
							}
						}
						purchaseService.createPurchase(order.getOrderCn(), orderItemIds, "0");// 保存订购项
						String message = stBuffer.toString();
						Map<String, Object> data = new Hashtable<>();
						data.put("orderId", order.getId());
						data.put("orderCn", order.getOrderCn());
						data.put("message",message);
						data.put("succeedTicketId", succeedTicketId);
						data.put("payDetail", payDetail);
						data.put("succeedTicketInfo", succeedPassengers);
						responseMessage.setData(data);
						responseMessage.setCode(ResponseMessage.SUCCESS);
						logger.info("=====预定车票成功====订单Id==" + order.getId());
					}
				} else {
					logger.info("==========预定车票失败=====");
					responseMessage.setCode(ResponseMessage.ERROR);
					responseMessage.setMsg("预定车票失败");
				}
			} else {
				responseMessage.setCode(ResponseMessage.ERROR);
				responseMessage.setMsg("预定车票失败");
			}
		} catch (Exception e) {
			logger.error("createTrainOrder预定车票异常=====", e);
			responseMessage.setCode(ResponseMessage.ERROR);
			responseMessage.setMsg("预定车票异常");
		}
		logger.info("=====预定车票结束=========用时:" + (System.currentTimeMillis() - start));
		return responseMessage;
	}

	/**
	 * 车票预定
	 */
	public ResponseMessage createTrainOrder(String orderJson) {
		OrderVo orderVo = (OrderVo) JsonUtil.JsonString2Object(orderJson, OrderVo.class);
		ResponseMessage responseMessage = createTrainOrder(orderVo);
		return responseMessage;
	}
	
	/**
	 * @Title: 添加费用项
	 */
	/*public ResponseMessage addChargerItem(String chargeId, long orderId, Date date) {
		ResponseMessage responseMessage = new ResponseMessage();
		if (date == null) {
			date = new Date();
		}
		if (StringUtils.isNotEmpty(chargeId)) {// 保存服务费项
			Charge charge = chargeMapper.find(Long.parseLong(chargeId));
			OrderItem item = new OrderItem(null, orderId, GoodsType.CHARGE.toString(),charge.getPrice(), date);
			trainOrderItemMapper.save(item);
		}
		return responseMessage;
	}*/
	
		/**
		 * @Title:预定通知接口 
		 * @return 
		 * ResponseMessage
		 */
	public ResponseMessage bookTicketNotify(String bookResult) {
		ResponseMessage responseMessage = new ResponseMessage();
			logger.info("======预定通知接口返回的数据:" + bookResult);
			JSONObject jsonObject = JsonUtil.stringToJsonObject(bookResult);
			if (jsonObject == null) {
				responseMessage.setCode(ResponseMessage.ERROR);
				responseMessage.setMsg("预定返回结果为空");
				return responseMessage;
			}
			if (!jsonObject.containsKey("trainOrderMx")) {
				responseMessage.setCode(ResponseMessage.ERROR);
				responseMessage.setMsg("预订返回结果缺少重要信息");
				return responseMessage;
			}
			responseMessage = notifyHandler(jsonObject,responseMessage);
		return responseMessage;
	}

	/**
	 * @Title: 保存订单主体  
	 */
	private Order saveOrder(Order order, Date orderDate) throws Exception {
		order.setOrderCn(getOrderCd());
		order.setCreateTime(orderDate);
		order.setStatus("0");
		order.setPaymentStatus("0");
		order.setOrderChannel(order.getOrderFrom());
		trainOrderMapper.save(order);
		logger.info("订单主体保存成功====" + order.getId() + "orderinfo==" + order.toString());
		return order;
	}

	/**
	 * @Title: 保存订单项  
	 */
	private Long saveOrderItems(OrderItemVo itemVo, long orderId, Date orderDate) {
		Long orderItemId = null;
		if (itemVo != null) {
			OrderItem item = new OrderItem(null, orderId, GoodsType.TICKET.toString(), new BigDecimal(0), orderDate);
			trainOrderItemMapper.save(item);
			orderItemId = item.getId();
		}
	/*	for (Delivery delivery : itemVo.getDeliverys()) {
				addDeliveryItem(delivery, orderId, orderDate);
		}*/
		return orderItemId;
	}
	
	
	private String bookTicketByHB(String url, TicketVo ticketVo, PassengerVo passengerVo, Contact contact, Long orderId,
			String requestNo) {
		Ticket ticket = ticketVo.getTicket();
		List<PassengerVo> passengerVos = new ArrayList<PassengerVo>();
		passengerVo.setPassportTypeId(CredentialsMap.credentialsRelationShipMapProvider().get(passengerVo.getPassportTypeId()));
		passengerVos.add(passengerVo);
		HBOrderVo hbOrderVo = new HBOrderVo(ticket.getTrainCn(), ticket.getOrigStation(), ticket.getOrigStationName(),
				ticket.getDestStation(), ticket.getDestStationName(),DateTimeUtils.formatDate(ticket.getEndTime(),"yyyy-MM-dd"), ticket.getDdsj(),
				DateTimeUtils.formatDate(ticket.getStartTime(),"yyyy-MM-dd"),ticket.getCcsj(), contact.getName(), contact.getForeignMobile(), passengerVos,
				ticketVo.getQqly());
		hbOrderVo.setBxfs("0");
		hbOrderVo.setCllx(ticketVo.getCllx());
		hbOrderVo.setBusinessId(orderId.toString());
		hbOrderVo.setRequestNo(requestNo);
		JSONObject jsonObj = JsonUtil.objectToJosnObject(hbOrderVo);
		logger.info("---------开始请求号百：params:" + jsonObj.toString());
		long startTime = System.currentTimeMillis();
		String resp = HttpClientUtil.httpPostBody(url, jsonObj.toString());
		long endTime = System.currentTimeMillis();
		logger.info("---------请求号百结束==耗时:" + (endTime - startTime) + "==返回结果：" + resp);
		if (StringUtils.isNotEmpty(resp) && resp.startsWith("{")) {
			JSONObject json = JSONObject.parseObject(resp);
			String code = json.getString("code");// 0 成功，-1 失败
			if (!StringUtils.isEquals("-1", code)) {
				if (!json.containsKey("message")&&StringUtils.isEquals("2", code)) {
					JSONObject result = json.getJSONObject("result");
					String oId = result.getString("oid");
					return oId;
				} else {
					return json.getString("message");
				}
			} else {
				logger.info("号百请求返回结果:code" + code + "===msg:" + json.getString("message"));
				return ResponseMessage.ERROR;
			}
		}
		return ResponseMessage.ERROR;
	}
	
	/**
	 * @Title: 保存火车票
	 */
	private String saveTicket(TicketVo ticketVo,Long orderId, Long passengerId,long orderItemId,PassengerVo pvo,Contact contact,
			List<Long> succeedTicketId,PayDetailBean payDetail, Date date)
			throws Exception {
		logger.info("保存订单项下的所有车票===start===订单项Id" + orderItemId);
		String result="";
		try {
			BigDecimal fee = null;
			BigDecimal salePrice = null;
			if(StringUtils.isNotEmpty(pvo.getFee())){
			     fee = new BigDecimal(pvo.getFee());
			}else{
				 fee = new BigDecimal(0);
			}
			if(StringUtils.isNotEmpty(pvo.getPrice())){
				salePrice = new BigDecimal(pvo.getPrice());
			}else{
				salePrice = new BigDecimal(0);
			}
			Ticket t = new Ticket(null, orderItemId, passengerId, ticketVo.getTicket().getRouteType(),
					ticketVo.getTicket().getTrainCn(), ticketVo.getTicket().getOrigStation(),
					ticketVo.getTicket().getOrigStationName(), ticketVo.getTicket().getDestStation(),
					ticketVo.getTicket().getDestStationName(), ticketVo.getTicket().getStartTime(),
					ticketVo.getTicket().getEndTime(), pvo.getPiaoType(), pvo.getZwCode(), null, salePrice, fee, "0",
					date, null, "", "", "", "0", ticketVo.getTicket().getDdsj(), ticketVo.getTicket().getCcsj(),
					new BigDecimal(0));
			t.setType("0");
			t.setTmcPrice(salePrice);
			t.setBookAccount(pvo.getUsername());
			String requestNo = String.valueOf(System.currentTimeMillis());
			t.setRequestNo(requestNo);
			logger.info("票数据信息" + t.toString());
			trainTicketMapper.save(t);
			String url = messageSource.getMessage("HB_BOOK_TICKET_URL", new Object[] {},
					LocaleContextHolder.getLocale());
			result = bookTicketByHB(url, ticketVo, pvo, contact, orderId, requestNo);// 号百预定火车票
			if (!StringUtils.isEquals(result, ResponseMessage.ERROR) && result.startsWith("TR")) {
				calculatePayDetail(payDetail,fee,salePrice);
				succeedTicketId.add(t.getId());
				t.setHbOrderId(result);
				t.setStatus("1");
				t.setUpdateTime(new Date());
				trainTicketMapper.updateTicketById(t);
			}else{
			   return result;
			}
			// batchSaveInsurance(ticketVo, t.getId());// 批量保存保险
		} catch (Exception e) {
			logger.error("SaveTicket保存订单时异常" ,e);
			result="";
		}
		logger.info("保存订单项下的所有车票===end===");
		return "";
	}
	
	/**
	  * @Title: 批量保存保险  
	  */
/*	private void batchSaveInsurance(TicketVo tv, long ticketId) throws Exception {
		logger.info("批量保存车票保险===== start" + "=======orig====" + tv.getTicket().getOrigStation() + "====dest===="
				+ tv.getTicket().getDestStation());
		for (Insurance is : tv.getInsurances()) {
			is.setTicketId(ticketId);
		}
		if (tv.getInsurances().size() > 0) {
			trainInsuranceMapper.savebatchInsurance(tv.getInsurances());
		}
		logger.info("批量保存车票保险===== end");
	}
*/
	/*private void saveTrainInvoices(List<TrainInvoice> trainInvoices, long orderItemId) throws Exception {
		logger.info("批量保存订单项===== start" + "=====orderItemId:" + orderItemId + "===size====" + trainInvoices.size());
		for (TrainInvoice trainInvoice : trainInvoices) {
			trainInvoice.setOrderItemId(orderItemId);
		}
		if (trainInvoices.size() > 0) {
			trainInvoiceMapper.savebatchTrainInvoice(trainInvoices);
		}
		logger.info("批量保存订单项===== end" + "=====orderItemId:" + orderItemId);
	}
*/
	  /**
	    * 根据订单号查询订单详情
	    * @param orderCn
	    * @return
	    * @throws Exception
	    */
	@Override
	public OrderDetailBean queryOrderDeatilByOrderCn(String orderCn) throws Exception {
		OrderDetailBean orderDetail = new OrderDetailBean();
		Order order=trainOrderMapper.findOrderByOrderCn(orderCn);
		logger.info("订单基本信息:"+order.toString());
		if(order!=null){
			List<TrainPayInfo> payInfos = trainPayInfoService.findTrainPayInfoByOrderId(order.getId());
			if(payInfos!=null && payInfos.size()>0){
				order.setPaymentStatus(payInfos.get(0).getStatus());
			}
			for(TrainPayInfo payInfo:payInfos){
				TrainPay trainPay = trainPayService.findTrainPayByInfoId(payInfo.getId());
				if(trainPay!=null){
					List<TrainPayDetail> payDetails =  payDetailService.findByPayId(trainPay.getId(),"");	
					orderDetail.setPaymentDetails(payDetails);
				}		
			}		
			Contact contact = contactMapper.findContactByOrderId(order.getId());
			buildOrderItems(orderDetail,order.getId());
		    orderDetail.setContact(contact);
		}
		orderDetail.setOrderBasic(order);	
		return orderDetail;
	}
	
	/**
	    * 根据订单ID查询订单详情
	    * @param orderId
	    * @return
	    * @throws Exception
	    */
	@Override
	public OrderDetailBean queryOrderDeatilByOrderId(Long orderId) throws Exception {
		OrderDetailBean orderDetail = new OrderDetailBean();
		Order order=trainOrderMapper.find(orderId);
		if(order!=null){
			trainPayInfoService.findTrainPayInfoByOrderId(orderId);
		}
		orderDetail.setOrderBasic(order);
		buildOrderItems(orderDetail,orderId);
		return orderDetail;
	}
	
	//获取订购项信息
		private void buildOrderItems(OrderDetailBean orderDetail,Long orderId) throws Exception{
		if (null != orderDetail) {
			List<OrderItem> orderItemList = trainOrderItemMapper.findOrderItemByOrderId(orderId);// 订单项
			if (null != orderItemList && orderItemList.size() > 0) {
				BigDecimal total = new BigDecimal(0);
		        PayDetailBean payDetail = new PayDetailBean();
				for (OrderItem item : orderItemList) {
					String goodsType = item.getGoodsType();
					Long orderItemId = item.getId();
					if (null != orderItemId) {
						if (GoodsType.CHARGE.toString().equalsIgnoreCase(goodsType)) {// 服务费
							List<Charge> charges = chargeMapper.findByOrderItemId(orderItemId);
							if (null != charges && charges.size() > 0) {
								orderDetail.setCharges(charges);
							}
						} else if (GoodsType.DELIVERY.toString().equalsIgnoreCase(goodsType)) {// 配送
							List<Delivery> deliveries = deliveryMapper.findDeliveryByOrderItemId(orderItemId);
							if (null != deliveries && deliveries.size() > 0) {
								orderDetail.setDeliveries(deliveries);
								BigDecimal sumDelivery = new BigDecimal(0);
								for(Delivery delivery:deliveries){
									sumDelivery=sumDelivery.add(delivery.getFee());
								}
								payDetail.setSumDelivery(sumDelivery);
								total = total.add(sumDelivery);	
							}
						} else if (GoodsType.TICKET.toString().equalsIgnoreCase(goodsType)) {// 火车票和保险
							List<TicketInfoVo> ticketList = trainTicketMapper.findTicketInfoVoByOrderItemId(orderItemId,"");
							if (null != ticketList && ticketList.size() > 0) {
								BigDecimal sumTicketPrice = new BigDecimal(0);
								BigDecimal sumFee = new BigDecimal(0);
								BigDecimal sumTmcPrice = new BigDecimal(0);
								orderDetail.setTickets(ticketList);
								for(TicketInfoVo ticketInfoVo:ticketList){
									sumTicketPrice = sumTicketPrice.add(ticketInfoVo.getSalePrice()==null ? new BigDecimal(0):ticketInfoVo.getSalePrice());
									sumFee = sumFee.add(ticketInfoVo.getFee()==null?new BigDecimal(0):ticketInfoVo.getFee());
									sumTmcPrice = sumTmcPrice.add(ticketInfoVo.getTmcPrice()==null?new BigDecimal(0):ticketInfoVo.getTmcPrice());
								}
								total = total.add(sumTicketPrice);
								total = total.add(sumFee);
								payDetail.setSumTicketPrice(sumTicketPrice);
								payDetail.setSumTmcPrice(sumTmcPrice);
								payDetail.setSumFee(sumFee);
								payDetail.setTotal(total);
								logger.info("sumTicketPrice==="+sumTicketPrice+"sumFee==="+sumFee+"sumTmcPrice==="+sumTmcPrice+"==");
							}
						}
					}
				}
				orderDetail.setSumDetails(payDetail);;
			}
		}
		}

	@Override
	public List<OrderBasisVo> queryOrderBasisList() {
		return trainOrderMapper.queryOrderBasisList();
	}

	@Override
	public List<OrderBasisVo> queryOrderBasisListByOrderTicket(OrderTicketQuery orderTicketQuery,int pageNum, int pageCount) {
		int startNum = 0;
		if(pageNum>0){
			startNum = (pageNum-1)*pageCount;
		}
		if(null == orderTicketQuery){
			return trainOrderMapper.queryOrderBasisListByPage(null,startNum, pageCount);
		}
		return trainOrderMapper.queryOrderBasisListByOrderTicket(orderTicketQuery, startNum, pageCount);
	}

	@Override
	public List<TicketInfoVo> queryTicketInfoByOrderCn(String orderCn) throws Exception {
		List<TicketInfoVo> list = null;
		try {
			list = trainTicketMapper.findTicketByOrderIdOrCd(null, orderCn,null,null);
		} catch (Exception e) {
			logger.error("===queryTicketInfoByOrderCn异常======",e);
		}
		return list;
	}

	@Override
	public List<TicketInfoVo> queryTicketInfoByOrderId(Long orderId) throws Exception {
		List<TicketInfoVo> list = null;
		try {
			list = trainTicketMapper.findTicketByOrderIdOrCd(orderId, null,null,null);
		} catch (Exception e) {
			logger.error("=====queryTicketInfoByOrderId异常======",e);
		}
		return list;
	}
	
	private Long savePassenger(PassengerVo passengerVo,Date date){
		Passenger passenger = passengerMapper.findPassengerByzjhm(passengerVo.getPassportNo());
		if(passenger!=null){
			return passenger.getId();
		}
		Passenger psg = new Passenger(null, passengerVo.getPassengerName(), passengerVo.getPassportTypeId(),
				passengerVo.getPassportNo(), passengerVo.getPhone(), date);
		passengerMapper.save(psg);
		Long passengerId = psg.getId();
		/*if(passengerVo.getPassengerId()==null){
			PassengerInfo passengerInfo = new PassengerInfo();
			passengerInfo.setCreateTime(date);
			passengerInfo.setLinkPhoneNo(passengerVo.getPhone());
			passengerInfo.setMobileNo(passengerVo.getPhone());
			passengerInfo.setChiName(passengerVo.getPassengerName());
			List<CertificateInfo> certificateInfoList = new ArrayList<>();
			CertificateInfo ceInfo = new CertificateInfo();
			ceInfo.setCreateTime(date);
			ceInfo.setCerNo(passengerVo.getPassportNo());
			ceInfo.setCerType(passengerVo.getPassportTypeId());
			certificateInfoList.add(ceInfo);
			passengerInfo.setCertificateInfoList(certificateInfoList);
			try {
			//	passengerManagerService.addPassengerInfo(passengerInfo);
			} catch (Exception e) {
			  logger.error("passengerManagerService.addPassengerInfo异常",e);
			}
		}*/
		return passengerId;
	}

	/**
	 * @Title: 添加配送项
	 */
	public ResponseMessage addDeliveryItem(Delivery delivery, long orderId,Date date) {	
		ResponseMessage responseMessage = new ResponseMessage();
	try {
		if (date == null) {
			date = new Date();
		}
		if (delivery != null) {// 保存配送项
			List orderItemIds = new LinkedList<>();
			Hashtable param = new Hashtable();
			param.put("orderId", orderId);
			param.put("goodsType", GoodsType.DELIVERY.toString());
			OrderItem orderItem = trainOrderItemMapper.findOrderItemByOrderIdAndGoodsType(param);
			delivery.setCreateTime(date);
			if (orderItem != null) {
				delivery.setId(orderItem.getId());
				deliveryMapper.save(delivery);
				orderItemIds.add(orderItem.getId());
			} else {
				OrderItem item = new OrderItem(null, orderId, GoodsType.DELIVERY.toString(), delivery.getFee(),date);
				trainOrderItemMapper.save(item);
				delivery.setOrderItemId(item.getId());
				deliveryMapper.save(delivery);
				orderItemIds.add(item.getId());
			}	
			Order order = trainOrderMapper.find(orderId);
			purchaseService.createPurchase(order.getOrderCn(), orderItemIds,"0");//火车票类型： 0-订票 1-改签 2-退票
			responseMessage.setCode(ResponseMessage.SUCCESS);
		}
	} catch (Exception e) {
		logger.error("----添加配送异常--" , e);
		responseMessage.setCode(ResponseMessage.ERROR);
	}
	return responseMessage;
	}
	
	/**
	 * 保存12306账号
	 * @TrainOrderServiceImpl
	 * @void
	 */
	private void saveAccount(PassengerVo passengerVo, String accountType, Date date) {
		if (StringUtils.isNotEmpty(passengerVo.getUsername()) && StringUtils.isNotEmpty(passengerVo.getPassword())) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("account", passengerVo.getUsername());
			map.put("password", passengerVo.getPassword());
			Account account = accountMapper.queryAccount(map);
			if (account == null) {
				account = new Account(null, passengerVo.getUsername(), passengerVo.getPassword(), accountType, "1",
						date);
				accountMapper.save(account);
				FrequentTraveller t = new FrequentTraveller(null, account.getId(), passengerVo.getPassengerName(),
						passengerVo.getPhone(), passengerVo.getPassportTypeId(), passengerVo.getPassportNo(), "",
						passengerVo.getPiaoType());
				frequentTravellerMapper.save(t);
			}
		}
	}

	@Override
	public PageQueryResult<OrderBasisVo> queryOrderBasisListByPage(PageOrderParameter pageOrderParameter) {
		logger.info("分页参数："+pageOrderParameter.toString());
		PageQueryResult<OrderBasisVo> pageResult = new PageQueryResult<OrderBasisVo>();
		pageResult.setPageNo(pageOrderParameter.getPageNo());
		pageResult.setPageSize(pageOrderParameter.getPageSize());
		Integer orderNumber = trainOrderMapper.queryOrderNumber(pageOrderParameter);
		pageResult.setTotalNum(orderNumber);
		int startIndex = 0;
		if(pageOrderParameter.getPageNo()>0){
			startIndex = (pageOrderParameter.getPageNo()-1)*pageOrderParameter.getPageSize();
		}
		List<OrderBasisVo> queryResult = trainOrderMapper.queryOrderBasisListByPage(pageOrderParameter, startIndex,
				pageOrderParameter.getPageSize());
		pageResult.setEntityList(queryResult);
		return pageResult;
	}

	@Override
	public Map<String, Integer> queryDifferentStatusOrderNum(String orderStatus,String payStatus) throws Exception {
		PageOrderParameter pageOrderParameter = new PageOrderParameter();
		Map<String, Integer> result = new HashMap<String, Integer>();
		pageOrderParameter.setOrderStatus(orderStatus);
		pageOrderParameter.setPayStatus(payStatus);
		Integer number = trainOrderMapper.queryOrderNumber(pageOrderParameter);
		result.put("orderCount", number);
		return result;
	}
	
	private boolean checkIsNewOrder(OrderVo orderVo) throws Exception {// 检查是否需重复下单
		if (orderVo != null) {
			Order order = orderVo.getOrder();
			if (order != null) {
				Long orderId = order.getId();
				if (orderId != null) {
					if (orderVo.isChange()) {// 改变了乘车人重要信息，需重新下单
						// 取消旧订单
						Order tempOrder = new Order();
						tempOrder.setId(orderId);
						tempOrder.setStatus("12");// 设置取消
						trainOrderMapper.update(tempOrder);
						List<OrderItem> orderItemList = trainOrderItemMapper.findOrderItemByOrderId(orderId);// 订单项
						if (null != orderItemList && orderItemList.size() > 0) {
							for (OrderItem item : orderItemList) {
								String goodsType = item.getGoodsType();
								Long orderItemId = item.getId();
								if (goodsType.equalsIgnoreCase(GoodsType.TICKET.toString())) {// 火车票和保险
									List<TicketInfoVo> ticketList = trainTicketMapper
											.findTicketInfoVoByOrderItemId(orderItemId, "");
									for (TicketInfoVo tv : ticketList) {
										trainTicketService.checkTrainCancel(tv.getHbOrderId());
									}
								}
							}
						}
						return Boolean.TRUE;
					} else {
						List<OrderItemVo> itemvos = orderVo.getOrderItemsVos();
						for (OrderItemVo vo : itemvos) {
							List<TicketVo> tvs = vo.getTrainTicketVos();
							for (TicketVo tv : tvs) {
								List<PassengerVo> passengerVos = tv.getPassengerVos();
								for (PassengerVo passengerVo : passengerVos) {
									Passenger psg = new Passenger(null, passengerVo.getPassengerName(),
											passengerVo.getPassportTypeId(), passengerVo.getPassportNo(),
											passengerVo.getPhone(), null);
									passengerMapper.updatePassengerByzjhm(psg);
								}
							}
						}
						return Boolean.FALSE;
					}
				} else {// 没有改变乘车人关键信息，不用重复下单
					return Boolean.TRUE;
				}
			} else {
				return Boolean.FALSE;
			}
		} else {
			return Boolean.FALSE;
		}
	}
	
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	private String getOrderCd() {
		String orderCd = "";
		String url = messageSource.getMessage("ORDER_PROVIDER_URL", new Object[] {}, LocaleContextHolder.getLocale());
		logger.info("====获取订单编号请求地址及参数===" + url);
		CloseableHttpClient httpClient = HttpClients.createDefault();
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000).build();//设置请求和传输超时时间
		HttpPost post = new HttpPost(url);
		post.setConfig(requestConfig);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		List formparams = new ArrayList();
		formparams.add(new BasicNameValuePair("OrderType", "3"));
		CloseableHttpResponse response = null;
		try {
			post.setEntity(new UrlEncodedFormEntity(formparams, "UTF-8"));
			response = httpClient.execute(post);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity);
			logger.info("====获取订单编号content===="+content);
			if (StringUtils.isNotEmpty(content) && content.startsWith("{")) {
				JSONObject jsonObject = JsonUtil.stringToJsonObject(content);
				String code = jsonObject.getString("code");
				if (StringUtils.isEquals(ResponseMessage.SUCCESS, code)) {
					orderCd = jsonObject.getJSONObject("Result").getString("ordercd");
				}
			}
		} catch (ClientProtocolException e) {
			logger.error("========获取订单编号请求异常======", e);
		} catch (IOException e) {
			logger.error("获取订单编号请求异常======", e);
		} try {
			if(response!=null){
			 EntityUtils.consume(response.getEntity());	
			}
		   } catch (IOException e) {
			   logger.error("========释放链接异常======", e);
		   }
		return orderCd;
	}

	@Override
	public void updateOrderBasicInfo(OrderDetailBean orderDetail) throws Exception {
		orderDetail.getOrderBasic().setUpdateTime(new Date());
		logger.info("updateOrderBasic info==="+orderDetail.getOrderBasic().toString());
		trainOrderMapper.updateOrderBasic(orderDetail.getOrderBasic());
		logger.info("updateOrderBasic info==="+orderDetail.getContact().toString());
		contactMapper.update(orderDetail.getContact());
	}

	@Override
	public void updateOrderPaymethod(Order order) {
		trainOrderMapper.updateOrderPaymethod(order);	
	}

	@Override
	public void updateOrderStatus(Order order)  {
		
		trainOrderMapper.updateOrderStatus(order);
		
	}

	@Override
	public OrderTicketPaInfoVo queryOrderTicketByOrderCn(String orderCn) throws Exception {
		
		Order order = trainOrderMapper.findOrderByOrderCn(orderCn);
		
		OrderTicketPaInfoVo orderTicketPaInfoVo = OrderTicketPaInfoVo.convertOrderTicket(order);
		
		List<TicketInfoVo> ticketInfos = trainTicketMapper.findTicketInfoByOrderCd(orderCn, "0", "");
		
		orderTicketPaInfoVo.setTicketInfos(ticketInfos);

		return orderTicketPaInfoVo;
	}

	@Override
	public Order findOrderByOrderCn(String orderCn) {
		
		return trainOrderMapper.findOrderByOrderCn(orderCn);
	}
	
	@Override
	public List<OrderItem> queryOrderIetmsByOrderId(Long orderId) {
		
		return trainOrderItemMapper.findOrderItemByOrderId(orderId);
	}
	
	/**
	 * 查询退票订单列表
	 * @param pageOrderParameter
	 * @return
	 */
	@Override
	public PageQueryResult<OrderBasisVo> queryRefundOrderBasisListByPage(PageOrderParameter pageOrderParameter) throws Exception {
		logger.info("分页参数："+pageOrderParameter.toString());
		PageQueryResult<OrderBasisVo> pageResult = new PageQueryResult<OrderBasisVo>();
		pageResult.setPageNo(pageOrderParameter.getPageNo());
		pageResult.setPageSize(pageOrderParameter.getPageSize());
		Integer orderNumber = trainOrderMapper.queryRefundOrderNumber(pageOrderParameter);
		pageResult.setTotalNum(orderNumber);
		int startIndex = 0;
		if(pageOrderParameter.getPageNo()>0){
			startIndex = (pageOrderParameter.getPageNo()-1)*pageOrderParameter.getPageSize();
		}
		List<OrderBasisVo> queryResult = trainOrderMapper.queryRefundOrderBasisListByPage(pageOrderParameter, startIndex,
				pageOrderParameter.getPageSize());
		pageResult.setEntityList(queryResult);
		return pageResult;
	}
	
	/**
	 * 获取退票订单状态数
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Integer> queryDifferentStatusRefundOrderNum(String status) throws Exception {
		PageOrderParameter pageOrderParameter = new PageOrderParameter();
		Map<String, Integer> result = new HashMap<String, Integer>();
		pageOrderParameter.setOrderStatus(status);//待审核
		Integer noAuditOrderNumber = trainOrderMapper.queryRefundOrderNumber(pageOrderParameter);
		result.put("orderCount", noAuditOrderNumber);
		return result;
	}
	
	private void calculatePayDetail(PayDetailBean payDetail, BigDecimal fee, BigDecimal ticketPrice) {
		if (payDetail == null) {
			payDetail = new PayDetailBean();
		}
		BigDecimal sumFee = payDetail.getSumFee()==null?new BigDecimal(0):payDetail.getSumFee();
		BigDecimal sumTmcPrice = payDetail.getSumTmcPrice()==null?new BigDecimal(0):payDetail.getSumTmcPrice();
		BigDecimal sumTicketPrice = payDetail.getSumTicketPrice()==null?new BigDecimal(0):payDetail.getSumTicketPrice();
		BigDecimal total = payDetail.getTotal()==null?new BigDecimal(0):payDetail.getTotal();
		sumFee = sumFee.add(fee);
		sumTicketPrice = sumTicketPrice.add(ticketPrice);
		sumTmcPrice = sumTmcPrice.add(ticketPrice);
		total = total.add(fee);
		total = total.add(ticketPrice);
		payDetail.setSumTicketPrice(sumTicketPrice);
		payDetail.setSumFee(sumFee);
		payDetail.setSumTmcPrice(sumTmcPrice);
		payDetail.setTotal(total);
	}
	
	private ResponseMessage notifyHandler(JSONObject jsonObject, ResponseMessage responseMessage) {
		try {
			String ddbh = jsonObject.getString("ddbh");
			String orderNumber = jsonObject.getString("orderNumber");
			JSONArray jsonArray = jsonObject.getJSONArray("trainOrderMx");
			String ddzt = jsonObject.getString("ddzt");// 获取异步回调通知状态
			for (Object obj : jsonArray) {
				JSONObject json = JsonUtil.stringToJsonObject(obj.toString());
				String zjhm = json.getString("zjh");
				Passenger passenger = passengerMapper.findPassengerByzjhm(zjhm);
				Long passengerId = null;
				if (passenger != null) {
					passengerId = passenger.getId();
				}
				Ticket t = new Ticket();
				PurchaseItem purchaseItem = new PurchaseItem();
				String tno = json.getString("tno");
				BigDecimal hbPrice = json.getBigDecimal("pj");
				t.setTicketNo(tno);
				if (StringUtils.isBlank(json.getString("cxi")) || StringUtils.isEquals("null", json.getString("cxi"))
						|| StringUtils.isEquals("NULL", json.getString("cxi"))) {
					t.setCxin("");
				} else {
					t.setCxin(json.getString("cxi"));
				}
				t.setOrderNumber(orderNumber);
				t.setHbPrice(hbPrice);
				t.setHbSeatType(json.getString("zwl"));
				if (StringUtils.isNotEmpty(ddzt)) {
					if (OrderStatusConstantMap.orderStatusMapFactory().containsKey(ddzt)) {
						t.setStatus(OrderStatusConstantMap.orderStatusMapFactory().get(ddzt));
						purchaseItem.setStatus(OrderStatusConstantMap.orderStatusMapFactory().get(ddzt));
					} else {
						t.setStatus("7");
						purchaseItem.setStatus("7");
					}
				}
				t.setPassengerId(passengerId);
				t.setHbOrderId(ddbh);
				t.setUpdateTime(new Date());
				logger.info("更新的票数据信息----" + t.toString());
				trainTicketMapper.updateByHbIdAndPassengerId(t);
				Ticket ticket = trainTicketMapper.findTicketByHbIdAndPassengerId(t);
				if (ticket == null) {
					logger.info("----未找到passengerId:"+passengerId+"号百号为:"+ddbh+"对应的车票---");
					responseMessage.setCode(ResponseMessage.ERROR);
					return responseMessage;
				}
				logger.info("-------是否找到票-" +(ticket == null) );
				purchaseItem.setGoodsId(ticket.getId());
				purchaseItem.setOrderNo(orderNumber);
				purchaseItemService.updatePurchaseItemByGoodsId(purchaseItem);
				// 发起号百出票请求
				if (StringUtils.isEquals("1", ddzt)) {
					OrderItem orderItem = trainOrderItemMapper.find(ticket.getOrderItemId());
					if (orderItem != null) {
						Order order = trainOrderMapper.find(orderItem.getOrderId());
						if (order != null) {
							List<Long> ticketIds = new ArrayList<>();
							ticketIds.add(ticket.getId());
							List<TrainPayInfo> trainPayInfos = trainPayInfoMapper.findPayInfoByItemIdCn(order.getId(),
									"0", ticketIds, GoodsType.TICKET.toString());
							if (trainPayInfos != null && trainPayInfos.size() > 0) {
								TrainPayInfo trainPayInfo = trainPayInfos.get(0);
								String status = order.getStatus();
								if (checkAbledPrintTicket(status, trainPayInfo.getStatus())) {// 检查是否可出票
									PayVo payVo = new PayVo();
									payVo.setAmount(hbPrice);
									payVo.setHbOrderCn(ddbh);
									payVo.setOrderId(order.getId());
									payVo.setType("2");
									payVo.setBy1("");
									trainPayService.createHBPay(payVo);
								}
							}
						}
					}
				}
				if (StringUtils.isEquals("2", ddzt)) {// 已出票时发送邮件 2-已出票状态
					List<TicketInfoVo> ticketInfoVos = trainTicketMapper
							.findTicketInfoVoByOrderItemId(ticket.getOrderItemId(), ticket.getTicketNo());
					OrderItem orderItem = trainOrderItemMapper.find(ticket.getOrderItemId());
					Order order = new Order();
					order.setId(orderItem.getOrderId());
					order.setStatus("8");
					order.setUpdateTime(new Date());
					logger.info("订单更新信息----" + order.toString());
					trainOrderMapper.updateOrderStatus(order);
					List<MessageReceiver> messageReceivers = messageReveiverService.queryMessageReceiversByOrderId(orderItem.getOrderId());
					logger.info("接收信息人数----" + messageReceivers.size());
					for (MessageReceiver mReiceiver : messageReceivers) {
						logger.info("mReiceiver----" + mReiceiver.toString()+"---"+StringUtils.isEquals(mReiceiver.getMobile(), passenger.getMobile())+"---"+StringUtils.isEquals(passenger.getName(), mReiceiver.getReceiver()));
						if (StringUtils.isEquals(mReiceiver.getMobile(), passenger.getMobile())
								|| StringUtils.isEquals(passenger.getName(), mReiceiver.getReceiver())) {
							messageService.sendissuedSMS(ticketInfoVos.get(0), mReiceiver.getMemberCd(),mReiceiver.getMobile());
						} else if (!StringUtils.isEquals(mReiceiver.getIdentity(), "passenger")) {
							messageService.sendissuedSMS(ticketInfoVos.get(0), mReiceiver.getMemberCd(),mReiceiver.getMobile());
						}
					}
				}
				responseMessage.setCode(ResponseMessage.SUCCESS);
			}
		} catch (Exception e) {
			responseMessage.setCode(ResponseMessage.ERROR);
			responseMessage.setMsg("系统异常");
			logger.error("bookTicketNotify预定通知接口异常 :", e);
		}
		return responseMessage;
	}
	
	/**
	 * 判断通知号百出票条件检查
	 * @TrainOrderServiceImpl
	 * @param status 订单状态 3 -待审批  4 -审批失败   8-已出票 
	 * @param paymentStatus 支付状态 4-已支付
	 * @boolean
	 */
	private boolean checkAbledPrintTicket(String status, String paymentStatus) {
		return (!StringUtils.isEquals(status, "3")) && (!StringUtils.isEquals(status, "4"))
				&& (!StringUtils.isEquals(status, "8")) && (StringUtils.isEquals(paymentStatus, "4"));
	}
}
