package com.mangocity.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mangocity.api.IApplicantService;
import com.mangocity.api.IPurchaseService;
import com.mangocity.api.ITrainPayDetailService;
import com.mangocity.api.ITrainPayService;
import com.mangocity.api.ITrainTicketService;
import com.mangocity.btms.api.ICorporationManageService;
import com.mangocity.common.HttpClientUtil;
import com.mangocity.common.ServletSignUtil;
import com.mangocity.enums.GoodsType;
import com.mangocity.mapper.ChargeMapper;
import com.mangocity.mapper.DeliveryMapper;
import com.mangocity.mapper.TrainOrderItemMapper;
import com.mangocity.mapper.TrainOrderMapper;
import com.mangocity.mapper.TrainPayDetailMapper;
import com.mangocity.mapper.TrainPayInfoMapper;
import com.mangocity.mapper.TrainPayMapper;
import com.mangocity.mapper.TrainPayOrderItemMapper;
import com.mangocity.mapper.TrainTicketMapper;
import com.mangocity.model.Applicant;
import com.mangocity.model.Charge;
import com.mangocity.model.Delivery;
import com.mangocity.model.Order;
import com.mangocity.model.OrderItem;
import com.mangocity.model.Ticket;
import com.mangocity.model.TrainPay;
import com.mangocity.model.TrainPayDetail;
import com.mangocity.model.TrainPayInfo;
import com.mangocity.model.TrainPayOrderItem;
import com.mangocity.response.ResponseMessage;
import com.mangocity.service.base.BaseServiceImpl;
import com.mangocity.util.DateTimeUtils;
import com.mangocity.util.JsonUtil;
import com.mangocity.vo.ChangeTicketVo;
import com.mangocity.vo.HBTicketVo;
import com.mangocity.vo.InsuranceVo;
import com.mangocity.vo.OrderDetailVo;
import com.mangocity.vo.PassStationVo;
import com.mangocity.vo.PayParamsVo;
import com.mangocity.vo.RefundFeeVo;
import com.mangocity.vo.RefundParamVo;
import com.mangocity.vo.RefundTicketReturnVo;
import com.mangocity.vo.RefundTicketVo;
import com.mangocity.vo.TicketInfoVo;
import com.mangocity.vo.TrainChangeNotifyVo;
import com.mangocity.vo.TrainChangeTicketVo;
import com.mangocity.vo.TrainDataVo;
import com.mangocity.vo.TrainGqDetailVo;
import com.mangocity.vo.TrainGqMxVo;
import com.mangocity.vo.TrainGqOrderMxVo;
import com.mangocity.vo.TrainGqVo;
import com.mangocity.vo.TrainReOrderMxVo;
import com.mangocity.vo.TrainRefundNotifyVo;
import com.mangocity.vo.TrainReturnMxVo;
import com.mangocity.vo.TrainReturnTicketDetailVo;
import com.mangocity.vo.TrainReturnVo;

public class TrainTicketServiceImpl extends BaseServiceImpl<Ticket> implements ITrainTicketService {
	
	Logger logger = Logger.getLogger(TrainTicketServiceImpl.class);
	
	@Autowired
	private ICorporationManageService corporationManageService;

	@Autowired
	private TrainTicketMapper trainTicketMapper;
	
	@Autowired
	private ChargeMapper chargeMapper;
	
	@Autowired
	private DeliveryMapper deliveryMapper;

	@Resource
	private MessageSource messageSource;
	
	@Autowired
	private TrainPayInfoMapper trainPayInfoMapper;
	
	@Autowired
	private TrainOrderItemMapper trainOrderItemMapper;
	
	@Autowired
	private TrainOrderMapper trainOrderMapper;
	
	@Autowired
	private TrainPayMapper trainPayMapper;
	
	@Autowired
	private TrainPayOrderItemMapper trainPayOrderItemMapper;
	
	@Autowired
	private TrainPayDetailMapper trainPayDetailMapper;
	
	@Autowired
	private ITrainPayService trainPayService;
	
	@Autowired
	private IApplicantService applicantService;
	
	@Autowired
	private IPurchaseService purchaseService;
	
	@Autowired
	private ITrainPayDetailService trainPayDetailService;
	
	/**
	 * 车票改签申请
	 */
	public ResponseMessage changeTrainTicket(HBTicketVo hticket) {
		logger.info("===申请车票改签开始====");
		ResponseMessage responseMessage = new ResponseMessage();
		try {
			JSONObject jsonObj = JsonUtil.objectToJosnObject(hticket);
			String parentTicketId = jsonObj.getString("parentTicketId");
			String url = messageSource.getMessage("HB_BOOK_CHANGE_TICKET_URL", new Object[] {},
					LocaleContextHolder.getLocale());
			logger.info("======开始请求号百：params=" + jsonObj.toString());
			long startTime = System.currentTimeMillis();
			String resp = HttpClientUtil.httpPostBody(url, jsonObj.toString());
			long endTime = System.currentTimeMillis();
			logger.info("======请求号百结束耗时:" + (endTime - startTime) + "==返回结果：" + resp);
			if (StringUtils.isNotEmpty(resp) && resp.startsWith("{")) {
				JSONObject json = JSONObject.parseObject(resp);
				String code = json.getString("code");
				if ("0".equals(code) && json.containsKey("result")) {
					BigDecimal gapPrice = json.getJSONObject("result").getBigDecimal("pdc");
					BigDecimal salePrice = json.getJSONObject("result").getJSONArray("nlt").getJSONObject(0)
							.getBigDecimal("pj");
					BigDecimal fee = json.getJSONObject("result").getBigDecimal("fwf");
					String changCode = json.getString("gid");
					if (StringUtils.isNotEmpty(parentTicketId)) {
						Ticket ticket = trainTicketMapper.find(Long.parseLong(parentTicketId));
						Ticket newTicket = new Ticket(null, ticket.getOrderItemId(), ticket.getPassengerId(),
								ticket.getRouteType(), jsonObj.getString("newCch"), ticket.getOrigStation(),
								ticket.getOrigStationName(), ticket.getDestStation(), ticket.getDestStationName(),
								DateTimeUtils.parseDate(
										jsonObj.getString("cfDateNew") + " " + jsonObj.getString("cfTimeNew"),
										"yyyy-MM-dd HH:mm:ss"),
								DateTimeUtils.parseDate(
										jsonObj.getString("ddDateNew") + " " + jsonObj.getString("ddTimeNew"),
										"yyyy-MM-dd HH:mm:ss"),
								ticket.getTrainType(), jsonObj.getString("zwlx"), Long.parseLong(parentTicketId),
								salePrice, fee, "status", new Date(), new Date(), changCode, "tickeNo", "cxin", "type",
								jsonObj.getString("cfTimeNew"), jsonObj.getString("ddTimeNew"), gapPrice);
						// newTicket.setHbOrderId(changCode);
						trainTicketMapper.save(newTicket);
						if (ticket.getId() != null && ticket.getId() > 0) {
							responseMessage.setCode(ResponseMessage.SUCCESS);
						} else {
							buildResponnse(responseMessage, ResponseMessage.ERROR, "保存火车票失败");
						}
					}
				} else {
					buildResponnse(responseMessage, ResponseMessage.ERROR, json.getString("message"));
				}
			} else {
				buildResponnse(responseMessage, ResponseMessage.ERROR, "数据格式不正确");
			}
		} catch (Exception e) {
			logger.error("火车票改签异常changeTrainTicket",  e);
			buildResponnse(responseMessage, ResponseMessage.ERROR, "火车票改签异常");
		}
		logger.info("===车票改签结束====状态码:" + responseMessage.getCode());
		return responseMessage;
	}

	/**
	 * 订单明细查询
	 */
	public ResponseMessage searchTrainOrderDetail(String orderNo) {
		logger.info("====订单明细查询开始===");
		ResponseMessage responseMessage = new ResponseMessage();
		String url = messageSource.getMessage("HB_SEARCH_ORDER_DETAIL", new Object[] {},LocaleContextHolder.getLocale());
		Map<String,String> map = new HashMap<>();
		long bussinessId = System.currentTimeMillis();
		map.put("businessId", bussinessId+"1W");
		map.put("requestNo", bussinessId+"1W");
		map.put("orderNo", orderNo);
		JSONObject jsonObj = JsonUtil.objectToJosnObject(map);
		String params = jsonObj.toString();
		logger.info("开始请求---请求参数---params:" + params);
		long startTime = System.currentTimeMillis();
		String resp = HttpClientUtil.httpPostBody(url, params);
		long endTime = System.currentTimeMillis();
		logger.info("请求结束---耗时：" + (endTime - startTime) + "返回结果：" + resp);
		responseMessage=handleResponse(resp,orderNo);
		logger.info("====订单明细查询结束===");
		return responseMessage;
	}
	
	
	/**
	 * 2、接口：查询车次、余票及票价信息
	 * @param fromStation 出发站三字码
	 * @param toStation 到达站三字码
	 * @param trainDate 乘车日期（yyyy-MM-dd）
	 * @param routeType 行程类型：单程，往返
	 * @param channel 采购渠道
	 */
	public ResponseMessage queryTrainTicket(Map<String,Object> map) throws Exception{
		ResponseMessage responseMessage = new ResponseMessage();
		if(null != map){
			logger.info("map参数：" + map);
			String fromStation = map.get("fromStation") != null ? map.get("fromStation").toString():"";
			String toStation = map.get("toStation") != null? map.get("toStation").toString():"";
			//Date trainDate = map.get("trainDate") != null ? (Date)map.get("trainDate"):null;
			String trainDate = map.get("trainDate") != null ? map.get("trainDate").toString():null;
			String routeType = map.get("routeType") != null ? map.get("routeType").toString():"";
			//此参数用于系统第三方变更时使用
			String channel = map.get("channel") != null ? map.get("channel").toString() : "";
			
			if(StringUtils.isNotEmpty(channel)){
				
			}else{
				try {
					//号百
					String url =  messageSource.getMessage("HB_TRAIN_QUERY_URL", new Object[]{}, LocaleContextHolder.getLocale());  
					JSONObject params = new JSONObject();
					params.put("fromStation", fromStation);
					params.put("toStation", toStation);
					params.put("trainDate", trainDate);
					
					long startTime = System.currentTimeMillis();
					String body = HttpClientUtil.httpPostBody(url,params.toString());
					long endTime = System.currentTimeMillis();
					logger.info("接口调用时间：" + (endTime - startTime) + "ms，url=" + url);
					
					if(StringUtils.isNotEmpty(body)){
						JSONObject jsonBody = null;
						try {
							jsonBody = JSONObject.parseObject(body);
						} catch (Exception e) {
							e.printStackTrace();
						}
						if(null != jsonBody){
							String code = jsonBody.getString("code");//0 成功，-1 失败
							String message = jsonBody.getString("message");
							String maintainFlag = jsonBody.getString("mtf");//系统维护中：1是，0否
							if("0".equals(code)){
								JSONObject result = jsonBody.getJSONObject("result");
								if(null != result){
									List<InsuranceVo> insuranceVoList = null;
									JSONArray insuranceJson = result.getJSONArray("bxl");//保险
									if(null != insuranceJson && insuranceJson.size() > 0){
										insuranceVoList = new ArrayList<InsuranceVo>();
										for(int i=0; i < insuranceJson.size(); i++){
											JSONObject obj = insuranceJson.getJSONObject(i);
											InsuranceVo vo = new InsuranceVo();
											vo = JSONObject.toJavaObject(obj, InsuranceVo.class);
											insuranceVoList.add(vo);
										}
									}
									List<TrainDataVo> trainDataVoList = null;
									JSONArray trainDataJson = result.getJSONArray("tds");//车次信息
									if(null != trainDataJson && trainDataJson.size() > 0){
										trainDataVoList = new ArrayList<TrainDataVo>();
										for(int i=0; i < trainDataJson.size(); i++){
											JSONObject obj = trainDataJson.getJSONObject(i);
											TrainDataVo vo = new TrainDataVo();
											vo = JSONObject.toJavaObject(obj, TrainDataVo.class);
											trainDataVoList.add(vo);
										}
									}
									Map<String,Object> data = new HashMap<String,Object>();
									data.put("trainDataVoList", trainDataVoList);
									data.put("insuranceVoList", insuranceVoList);
									responseMessage.setCode(ResponseMessage.SUCCESS);
									responseMessage.setData(data);
								}else{
									responseMessage.setCode(code);
						    		responseMessage.setMsg(message + ",调用url=" + url + ",参数params=" + params + "返回值result为空！");
						    		logger.info("调用url=" + url + ",参数params=" + params + "返回值result为空！");
								}
							}else{
								responseMessage.setCode(code);
								responseMessage.setMsg(message + ",调用url=" + url + ",参数params=" + params + "返回值异常！");
								logger.info("调用url=" + url + ",参数params=" + params + "返回值异常！");
							}
						}else{
							responseMessage.setCode(ResponseMessage.ERROR);
							responseMessage.setMsg("url=" + url + ",参数params=" + params + ",body" + body + "返回json格式body错误！");
							logger.info("url=" + url + ",参数params=" + params + ",body" + body + "返回json格式body错误！");
						}
					}else{
						responseMessage.setCode(ResponseMessage.ERROR);
						responseMessage.setMsg("url=" + url + ",参数params=" + params + ",返回body错误！");
						logger.info("url=" + url + ",参数params=" + params + ",返回body错误！");
					}
				} catch (NoSuchMessageException e) {
					responseMessage.setCode(ResponseMessage.ERROR);
		        	responseMessage.setMsg("queryTrainTicket接口错误！");
					logger.error("queryTrainTicket接口错误！",e);
				}
			}
		}else{
			responseMessage.setCode(ResponseMessage.ERROR);
        	responseMessage.setMsg("queryTrainTicket接口map参数为空！");
			logger.info("queryTrainTicket接口map参数为空！");
		}
		return responseMessage;
	}
	
	//获取途经站点信息
	@Override
	public ResponseMessage queryPassStations(PassStationVo passStationVo) throws Exception{
		ResponseMessage responseMessage = new ResponseMessage();
		if(null != passStationVo){
			logger.info("map参数：" + passStationVo.toString());
			String trainCode = passStationVo.getTrainCode();
			String trainNo = passStationVo.getTrainNo();
			String startTime = passStationVo.getStartTime();
			String fromStationCode = passStationVo.getFromStationCode();
			String toStationCode = passStationVo.getToStationCode();
			JSONObject params = new JSONObject();
			params.put("trainCode", trainCode);
			params.put("trainNo", trainNo);
			params.put("startTime", startTime);
			params.put("fromStationCode", fromStationCode);
			params.put("toStationCode", toStationCode);
			String url =  messageSource.getMessage("HB_PASS_STATION_URL", new Object[]{}, LocaleContextHolder.getLocale());
			long start = System.currentTimeMillis();
			String body = HttpClientUtil.httpPostBody(url,params.toString());
			long endTime = System.currentTimeMillis();
			logger.info("接口调用时间：" + (endTime - start) + "ms，url=" + url);
			if(StringUtils.isNotEmpty(body)){
				JSONObject jsonBody = null;
				try {
					jsonBody = JSONObject.parseObject(body);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(null != jsonBody){
					String code = jsonBody.getString("code");//0 成功，-1 失败
					String message = jsonBody.getString("message");
					String maintainFlag = jsonBody.getString("mtf");//系统维护中：1是，0否
					if("0".equals(code)){
						JSONObject result = jsonBody.getJSONObject("result");
						if(null != result){
							Map<String,Object> data = new HashMap<String,Object>();
							data.put("passStation", result);
							responseMessage.setCode(ResponseMessage.SUCCESS);
							responseMessage.setData(data);
						}else{
							responseMessage.setCode(code);
				    		responseMessage.setMsg(message + ",调用url=" + url + ",参数params=" + params + "返回值result为空！");
				    		logger.info("调用url=" + url + ",参数params=" + params + "返回值result为空！");
						}
					}else{
						responseMessage.setCode(code);
						responseMessage.setMsg(message + ",调用url=" + url + ",参数params=" + params + "返回值异常！");
						logger.info("调用url=" + url + ",参数params=" + params + "返回值异常！");
					}
				}else{
					responseMessage.setCode(ResponseMessage.ERROR);
					responseMessage.setMsg("url=" + url + ",参数params=" + params + ",body" + body + "返回json格式body错误！");
					logger.info("url=" + url + ",参数params=" + params + ",body" + body + "返回json格式body错误！");
				}
			}else{
				responseMessage.setCode(ResponseMessage.ERROR);
				responseMessage.setMsg("url=" + url + ",参数params=" + params + ",返回body错误！");
				logger.info("url=" + url + ",参数params=" + params + ",返回body错误！");
			}
		}else{
			responseMessage.setCode(ResponseMessage.ERROR);
        	responseMessage.setMsg("queryPassStations接口map参数为空！");
			logger.info("queryPassStations接口map参数为空！");
		}
		return responseMessage;
	}
	
	/**
	 * 火车票退票结果通知
	 * @param trainRefundNotifyVo
	 * @throws Exception
	 */
	public ResponseMessage checkTrainRefundNotify(String params) throws Exception{
		ResponseMessage responseMessage = new ResponseMessage();
		logger.info("火车票退票结果通知接收参数params:" + params);
		if(StringUtils.isNotEmpty(params)){
			JSONObject json = null;
			try {
				json = JSONObject.parseObject(params);
			} catch (Exception e) {
				responseMessage.setCode(ResponseMessage.ERROR);
				responseMessage.setMsg("火车票退票结果通知接口参数错误！params=" + params);
				logger.error("火车票退票结果通知异常---",e);
			}
			if(null != json){
				try {
					TrainRefundNotifyVo trainRefundNotifyVo = new TrainRefundNotifyVo();
					trainRefundNotifyVo = JSONObject.toJavaObject(json, TrainRefundNotifyVo.class);
					List<TrainReOrderMxVo> trainReOrderMxVoList = trainRefundNotifyVo.getTrainReOrderMx();
					if(null != trainReOrderMxVoList && trainReOrderMxVoList.size() > 0){
						String hbOrderId = trainRefundNotifyVo.getRefundOrderNo();//号百的退票订单号
						//火车票类型： 0-订票 1-改签 2-退票
						List<TicketInfoVo> ticketList = trainTicketMapper.findTicketByHbOrderId(hbOrderId, "2");
						if(null != ticketList && ticketList.size() > 0){
							for(TrainReOrderMxVo order : trainReOrderMxVoList){
								for(TicketInfoVo info : ticketList){
									if(info.getTrainCn().equals(order.getTrainNo()) && info.getPassenger().getCertCn().equals(order.getIdNo())){
										Ticket ticket = new Ticket();
										ticket.setId(info.getId());
										ticket.setStatus("6");//已退票
										ticket.setUpdateTime(new Date());
										trainTicketMapper.updateTicket(ticket);
										//加操作日志之后再加操作人
										ticket.setStatus("2");//待审批
										ticket.setOperator("SYSTEM");
										trainTicketMapper.updateTicket(ticket);
										break;
									}else{
										buildResponnse(responseMessage, ResponseMessage.ERROR, "退款信息异常！");
									}
								}
							}
						}else{
							buildResponnse(responseMessage, ResponseMessage.ERROR, "火车票退票结果通知接口异常ticketList为空！");
						}
					}
				} catch (Exception e) {
					buildResponnse(responseMessage, ResponseMessage.ERROR, "火车票退票结果通知接口异常！");
					logger.error("火车票退票结果通知接口异常--",e);
				}
			}
		}else{
			buildResponnse(responseMessage, ResponseMessage.ERROR, "火车票退票结果通知接口参数错误！params=" + params);
		}
		return responseMessage;
	}
	
	//退款审核通过
	@Override
	public ResponseMessage checkRefundTicket(Long orderId,List<Long> ticketIds) throws Exception{
		ResponseMessage responseMessage = new ResponseMessage();
		if(null != orderId && null != ticketIds && ticketIds.size() > 0){
			//调用支付平台申请退款
			String payInfoType = "1";//1=退票
			List<TrainPayInfo> list = trainPayInfoMapper.findPayInfoByItemIdCn(orderId, payInfoType, ticketIds, GoodsType.TICKET.toString());
			if(null != list && list.size() > 0){
				TrainPayInfo trainPayInfo = list.get(0);
				TrainPay trainPay = trainPayMapper.findPayByInfoId(trainPayInfo.getId());
				if(null != trainPay){
					List<TrainPayDetail> detailList = trainPayDetailMapper.findByPayId(trainPay.getId(), null);
					if(null != detailList && detailList.size() > 0){
						TrainPayDetail trainPayDetail = detailList.get(0);
						String payModel = trainPayDetail.getPayModel();
						String status = "2";//支付失败
						String ticketStatus = "9";//退款失败
						try {
							if("TMON".equals(payModel)){//月结
								status = "4";//已退款
								ticketStatus = "8";//已退款
							}else if("DIVR".equals(payModel)){//信用卡
								PayParamsVo paramsVO = new PayParamsVo();
								paramsVO.setOrderCn(trainPay.getOrderCd());
								paramsVO.setOutTradeNo(trainPayDetail.getPayNumber());
								paramsVO.setPrice(trainPayDetail.getPayAmount());
								paramsVO.setRefundModeCode(trainPayDetail.getPayModel());
								responseMessage = trainPayService.createMpmRefundProcess(paramsVO);
								if(ResponseMessage.SUCCESS.equals(responseMessage.getCode())){
									status = "1";//在线支付中
									ticketStatus = "7";//退款中
									responseMessage.setCode(ResponseMessage.SUCCESS);
									responseMessage.setMsg("通知支付平台退款成功！");
								}
							}
							Date date = new Date();
							//支付详情
							TrainPayDetail detail = new TrainPayDetail();
							detail.setId(trainPayDetail.getId());
							detail.setStatus(status);
							detail.setModifyTime(date);
							trainPayDetailMapper.update(detail);
							//支付
							TrainPay pay = new TrainPay();
							pay.setId(trainPay.getId());
							pay.setStatus(status);
							pay.setModifyTime(date);
							trainPayMapper.update(pay);
							//支付会话
							TrainPay tPay = trainPayService.find(trainPay.getId());
							if(null != tPay){
								TrainPayInfo payInfo = new TrainPayInfo();
								payInfo.setId(tPay.getPayInfoId());
								payInfo.setStatus(status);
								payInfo.setModifyTime(date);
								trainPayInfoMapper.update(payInfo);
							}
							//更新火车票状态
							for(Long ticketId : ticketIds){
								Ticket t = new Ticket();
								t.setId(ticketId);
								t.setStatus(ticketStatus);
								t.setUpdateTime(date);
								trainTicketMapper.updateTicket(t);
							}
							
						} catch (Exception e) {
							e.printStackTrace();
							buildResponnse(responseMessage, ResponseMessage.ERROR, "调用支付平台申请退款接口异常！");
						}
					}else{
						buildResponnse(responseMessage, ResponseMessage.ERROR, "调用支付平台申请退款异常,detailList为空！");
					}
				}else{
					buildResponnse(responseMessage, ResponseMessage.ERROR, "调用支付平台申请退款异常,trainPay为空！");
				}
			}else{
				buildResponnse(responseMessage, ResponseMessage.ERROR, "调用支付平台申请退款异常,TrainPayInfoList为空！");
			}
		}
		return responseMessage;
	}
	
	/**
	* @Title: 订单改签确认 
	* @param changOrderNo
	* @return 
	* ResponseMessage
	 */
	public ResponseMessage changeTrainTicketConfirm(String changOrderNo){
		ResponseMessage responseMessage = new ResponseMessage();
		Ticket t= new Ticket();
		t.setStatus("status");
		trainTicketMapper.update(t);
		return responseMessage;
	}
	
	/**
	 * 火车票改签结果通知
	 * @param trainChangeNotifyVo
	 * @throws Exception
	 */
	public ResponseMessage checkTrainChangeNotify(String params) throws Exception{
		logger.info("火车票改签结果通知接收参数params:" + params);
		ResponseMessage responseMessage = new ResponseMessage();
		if(StringUtils.isNotEmpty(params)){
			JSONObject json = null;
			try {
				json = JSONObject.parseObject(params);
			} catch (Exception e) {
				buildResponnse(responseMessage, ResponseMessage.ERROR, "火车票改签结果通知接口参数错误！params=" + params);
				logger.error("火车票改签结果通知接口参数错误=",e);
			}
			if(null != json){
				try {
					TrainChangeNotifyVo trainChangeNotifyVo = new TrainChangeNotifyVo();
					trainChangeNotifyVo = JSONObject.toJavaObject(json, TrainChangeNotifyVo.class);
					List<TrainGqOrderMxVo> trainGqOrderMxVoList = trainChangeNotifyVo.getTrainGqOrderMx();
					if(null != trainGqOrderMxVoList && trainGqOrderMxVoList.size() > 0){
						String hbOrderId = trainChangeNotifyVo.getChangeOrderNo();//号百的改签订单号
						//火车票类型： 0-订票 1-改签 2-退票
						List<TicketInfoVo> ticketList = trainTicketMapper.findTicketByHbOrderId(hbOrderId, "1");
						if(null != ticketList && ticketList.size() > 0){
							for(TrainGqOrderMxVo order : trainGqOrderMxVoList){
								for(TicketInfoVo info : ticketList){
									if(info.getTicketNo().equals(order.getNewTkno()) && info.getPassenger().getCertCn().equals(order.getIdNo())){
										Ticket ticket = new Ticket();
										ticket.setId(info.getId());
										ticket.setStatus(trainChangeNotifyVo.getChangeStatus());
										ticket.setUpdateTime(new Date());
										trainTicketMapper.update(ticket);
										break;
									}
								}
							}
							buildResponnse(responseMessage, ResponseMessage.SUCCESS,"成功！");
						}
					}
				} catch (Exception e) {
					buildResponnse(responseMessage, ResponseMessage.ERROR, "火车票改签结果通知接口异常！");
					logger.error("火车票改签结果通知接口异常----",e);
				}
			}
		}else{
			buildResponnse(responseMessage, ResponseMessage.ERROR, "火车票改签结果通知接口参数错误！params=" + params);
		}
		return responseMessage;
	}
	
	/**
	 * 火车票改签
	 * @throws Exception
	 */
	public void createChangeTicket(Long orderItemId,String goodsType,ChangeTicketVo changeTicketVo,String ticketNo) throws Exception{
		if(null != orderItemId && StringUtils.isNotEmpty(goodsType)){
			if(goodsType.equalsIgnoreCase(GoodsType.CHARGE.toString())){//服务费
				List<Charge> charge = chargeMapper.findByOrderItemId(orderItemId);
				if(null != charge && charge.size() > 0){
					
				}
			}else if(goodsType.equalsIgnoreCase(GoodsType.DELIVERY.toString())){//配送
				List<Delivery> delivery = deliveryMapper.findDeliveryByOrderItemId(orderItemId);
				if(null != delivery && delivery.size() > 0){
					
				}
			}else if(goodsType.equalsIgnoreCase(GoodsType.TICKET.toString())){
				//获取该订购项的所有火车票
				List<TicketInfoVo> ticketList = trainTicketMapper.findTicketInfoVoByOrderItemId(orderItemId,ticketNo);
				if(null != ticketList && ticketList.size() > 0){
					for(TicketInfoVo ticket : ticketList){
						String hbOrderId = ticket.getHbOrderId();
						Ticket newTicket = ticket;
						newTicket.setId(null);
						newTicket.setHbOrderId(null);
						newTicket.setPassengerId(ticket.getId());
						//火车票类型： 0-订票 1-改签 2-退票
						newTicket.setType("1");
						//退票状态：1申请中，2已审核，3已调度，4已配送，5已完成，6已作废
						newTicket.setStatus("1");
						newTicket.setCreateTime(new Date());
						trainTicketMapper.save(newTicket);
						
						changeTicketVo.setOrderNo(hbOrderId);
						List<TrainChangeTicketVo> list = new ArrayList<TrainChangeTicketVo>();
						TrainChangeTicketVo trainChangeTicketVo = new TrainChangeTicketVo();
						trainChangeTicketVo.setTicketNo(ticket.getTicketNo());
						list.add(trainChangeTicketVo);
						changeTicketVo.setTicketList(list);
						
					}
				}
			}
		}
	}
	
	/**
	 * 申请改签
	 * @throws Exception
	 */
	public void checkChangeTicket(ChangeTicketVo changeTicketVo) throws Exception{
		if(null != changeTicketVo){
			if(StringUtils.isNotEmpty(changeTicketVo.getOrderNo()) && StringUtils.isNotEmpty(changeTicketVo.getNewTrainCn()) && StringUtils.isNotEmpty(changeTicketVo.getSeatType()) &&
					null != changeTicketVo.getStartDate() && StringUtils.isNotEmpty(changeTicketVo.getStartTime()) && null != changeTicketVo.getArriveDate() && 
					StringUtils.isNotEmpty(changeTicketVo.getArriveTime()) && StringUtils.isNotEmpty(changeTicketVo.getQueryFrom()) && StringUtils.isNotEmpty(changeTicketVo.getMemberId()) &&
					StringUtils.isNotEmpty(changeTicketVo.getLoginUserId()) && null != changeTicketVo.getTicketList() && changeTicketVo.getTicketList().size() > 0){
				String changeApplyUrl = messageSource.getMessage("HB_TRAIN_CHANGE_APPLY", new Object[]{}, LocaleContextHolder.getLocale());
				
				String jsonStr = JSONObject.toJSONString(changeTicketVo);
				JSONObject json = JSONObject.parseObject(jsonStr);
				
			}
		}
	}
	
	/**
	 * 火车票退票
	 * @throws Exception
	 */
	@Override
	public ResponseMessage createRefundTicket(PayParamsVo paramsVo,RefundFeeVo refundFeeVo) throws Exception{
		ResponseMessage responseMessage = new ResponseMessage();
		if(null != paramsVo && refundFeeVo != null){
			Long orderItemId = paramsVo.getOrderItemId();//订购项ID
			Long orderId = null;
			//BigDecimal price = paramsVo.getPrice();//价格
			//String payModel = paramsVo.getPayModel();//支付方式
			
			OrderItem orderItem = trainOrderItemMapper.find(orderItemId);
			if(null != orderItem && null != orderItemId){
				String goodsType = orderItem.getGoodsType();//产品类型
				if(goodsType.equalsIgnoreCase(GoodsType.CHARGE.toString())){//服务费
					List<Charge> charge = chargeMapper.findByOrderItemId(orderItemId);
					if(null != charge && charge.size() > 0){
						
					}
				}else if(goodsType.equalsIgnoreCase(GoodsType.DELIVERY.toString())){//配送
					List<Delivery> delivery = deliveryMapper.findDeliveryByOrderItemId(orderItemId);
					if(null != delivery && delivery.size() > 0){
						
					}
				}else if(goodsType.equalsIgnoreCase(GoodsType.TICKET.toString())){
					//获取该订购项的所有火车票
					try {
						//插入申请人
						Applicant applicant = paramsVo.getApplicant();
						if(null != applicant){
							applicantService.save(applicant);
						}
						List<RefundParamVo> list = refundFeeVo.getRefundParamVoList();
						if(null != list && list.size() > 0){
							String orderCn = "";
							orderId = orderItem.getOrderId();
							Order order = trainOrderMapper.find(orderId);
							if(null != order){
								orderCn = order.getOrderCn();
							}
							//创建订购项
							OrderItem newOrderItem = new OrderItem();
							newOrderItem.setGoodsType(goodsType);
							newOrderItem.setOrderId(orderId);
							trainOrderItemMapper.save(newOrderItem);
							
							long currentTime = System.currentTimeMillis();
							Date date = new Date();
							Date endDate = new Date(currentTime + 30 * 60 * 1000);//半个小时
							List<Long> goodsIds = new ArrayList<Long>();
							for(RefundParamVo refundParamVo : list){
								TicketInfoVo ticket = trainTicketMapper.findTicketInfoVoByTicketId(refundParamVo.getTicketId());
								if(ticket != null){
									if(!"2".equals(ticket.getType())){//火车票类型： 0-订票 1-改签 2-退票
										String hbOrderId = ticket.getHbOrderId();
										Long id = ticket.getId();
										Long passengerId = ticket.getPassengerId();
										Ticket newTicket = ticket;
										newTicket.setOrderItemId(newOrderItem.getId());
										newTicket.setId(null);
										newTicket.setHbOrderId(null);
										//火车票类型： 0-订票 1-改签 2-退票
										newTicket.setType("2");
										newTicket.setStatus("0");//退票申请中
										newTicket.setCreateTime(date);
										newTicket.setTicketParentId(id);
										newTicket.setPassengerId(passengerId);
										if(null != applicant){
											newTicket.setApplicantId(applicant.getId());
										}
										newTicket.setSalePrice(refundParamVo.getReturnAmount());
										newTicket.setFee(refundParamVo.getFee());
										newTicket.setRefundReason(paramsVo.getRefundReason());
										newTicket.setRefundDesc(paramsVo.getRefundDesc());
										newTicket.setVerifyDesc(paramsVo.getVerifyDesc());
										trainTicketMapper.save(newTicket);
										goodsIds.add(newTicket.getId());
										RefundTicketVo refundTicketVo = new RefundTicketVo();
										refundTicketVo.setTicketNo(ticket.getTicketNo());
										if(null != ticket.getPassenger()){
											refundTicketVo.setPassengerName(ticket.getPassenger().getName());
											refundTicketVo.setPassportTypeSeId(ticket.getPassenger().getCertType());
											refundTicketVo.setPassportSeNo(ticket.getPassenger().getCertCn());
										}
										//调用号百申请退票(这里的退票是号百生成退票，还没有从12306退票成功)
										RefundTicketReturnVo refundTicketReturnVo = checkRefundTicket(hbOrderId,orderId,ticket.getRequestNo(),refundTicketVo);
										if(null != refundTicketReturnVo){
											logger.info("refundTicketReturnVo=" + refundTicketReturnVo.toString());
											Ticket updateTicket = new Ticket();
											updateTicket.setId(newTicket.getId());
											updateTicket.setUpdateTime(new Date());
											updateTicket.setStatus("2");//退票中
											updateTicket.setHbOrderId(refundTicketReturnVo.getReturnOrderNo());
											trainTicketMapper.updateTicket(updateTicket);
											responseMessage.setCode(ResponseMessage.SUCCESS);
								        	responseMessage.setMsg("号百退票成功,hbOrderId="+hbOrderId);
										}else{
											Ticket updateTicket = new Ticket();
											updateTicket.setId(newTicket.getId());
											updateTicket.setUpdateTime(new Date());
											updateTicket.setStatus("1");//退票申失败
											trainTicketMapper.updateTicket(updateTicket);
											responseMessage.setCode(ResponseMessage.ERROR);
								        	responseMessage.setMsg("调用号百申请退票异常,hbOrderId="+hbOrderId);
											logger.info("调用号百申请退票异常,hbOrderId="+hbOrderId);
										}
								    }
								}
							}
							//支付会话
							TrainPayInfo payInfo = new TrainPayInfo();
							payInfo.setOrderId(orderId);
							payInfo.setOrderCd(orderCn);
							payInfo.setStatus("0");//0：支付中
							payInfo.setType("1");//支付类型：0付款，1退款
							payInfo.setDateTime(date);
							payInfo.setEndTime(endDate);
							trainPayInfoMapper.save(payInfo);
							//支付订购项
							logger.info("goodsIds==" + goodsIds);
							for(Long goodsId : goodsIds){
								TrainPayOrderItem payOrderItem = new TrainPayOrderItem();
								payOrderItem.setPayInfoId(payInfo.getId());
								payOrderItem.setGoodsId(goodsId);
								payOrderItem.setGoodsType(goodsType);
								payOrderItem.setCreateTime(date);
								trainPayOrderItemMapper.save(payOrderItem);
							}
							//支付表
							TrainPay pay = new TrainPay();
							pay.setPayInfoId(payInfo.getId());
							pay.setOrderCd(orderCn);
							pay.setAmount(refundFeeVo.getTotalPrice());
							pay.setStatus("0");//0：支付中
							pay.setCreateTime(date);
							trainPayMapper.save(pay);
							//查看预订票支付详情,4=已支付
							Map<String,String> map = new HashMap<String,String>();
							List<TrainPayDetail> detailList = trainPayDetailService.findPayDetailByOrderId(orderId, "0","4");
							if(null != detailList && detailList.size() > 0){
								for(TrainPayDetail t : detailList){
									map.put(t.getPayModel(), t.getPayModel());
								}
							}
							String payModel = map.get("TMON");
							if(StringUtils.isBlank(payModel)){
								payModel = "DIVR";//默认信用卡
							}
							//支付详情
							TrainPayDetail detail = new TrainPayDetail();
							detail.setTrainPayId(pay.getId());
							detail.setPayModel(payModel);
							detail.setPayAmount(refundFeeVo.getTotalPrice());
							detail.setStatus("0");//0：支付中
							detail.setCreateTime(date);
							String outTradeNo = ServletSignUtil.CUSTOMER_ID + orderCn + trainPayService.getOutTradeNo(orderCn);
							detail.setPayNumber(outTradeNo);
							trainPayDetailMapper.save(detail);
							
							//插入采购
							List<Long> orderItemIds = new ArrayList<Long>();
							orderItemIds.add(newOrderItem.getId());
							purchaseService.createPurchase(orderCn, orderItemIds,"2");//火车票类型： 0-订票 1-改签 2-退票
						}
					} catch (Exception e) {
						responseMessage.setCode(ResponseMessage.ERROR);
			        	responseMessage.setMsg("createRefundTicket接口异常！");
						e.printStackTrace();
						logger.error("退票接口异常!",e);
					}
				}
			}else{
				responseMessage.setCode(ResponseMessage.ERROR);
	        	responseMessage.setMsg("createRefundTicket接口参数为空！");
				logger.info("createRefundTicket接口参数为空！");
			}
		}else{
			responseMessage.setCode(ResponseMessage.ERROR);
        	responseMessage.setMsg("createRefundTicket接口参数为空！");
		}
		return responseMessage;
	}
	
	/**
	 * 通知号百申请退票
	 * @param orderItemId
	 * @param ticketNo
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResponseMessage checkRefundTicketTMON(Long orderItemId,String ticketNo) throws Exception{
		ResponseMessage responseMessage = new ResponseMessage();
		List<TicketInfoVo> ticketList = trainTicketMapper.findTicketInfoVoByOrderItemId(orderItemId,ticketNo);
		if(null != ticketList && ticketList.size() > 0){
			for(TicketInfoVo ticket : ticketList){
				RefundTicketVo refundTicketVo = new RefundTicketVo();
				refundTicketVo.setTicketNo(ticket.getTicketNo());
				if(null != ticket.getPassenger()){
					refundTicketVo.setPassengerName(ticket.getPassenger().getName());
					refundTicketVo.setPassportTypeSeId(ticket.getPassenger().getCertType());
					refundTicketVo.setPassportSeNo(ticket.getPassenger().getCertCn());
				}
				String hbOrderId = ticket.getHbOrderId();
				OrderItem orderItem = trainOrderItemMapper.find(ticket.getOrderItemId());
				if(orderItem != null){
					RefundTicketReturnVo refundTicketReturnVo = checkRefundTicket(hbOrderId,orderItem.getOrderId(),ticket.getRequestNo(),refundTicketVo);
					if(null != refundTicketReturnVo){
						logger.info("refundTicketReturnVo=" + refundTicketReturnVo.toString());
						Ticket updateTicket = new Ticket();
						updateTicket.setId(ticket.getId());
						updateTicket.setUpdateTime(new Date());
						updateTicket.setStatus("5");//退票中
						updateTicket.setHbOrderId(refundTicketReturnVo.getReturnOrderNo());
						trainTicketMapper.updateTicket(updateTicket);
						responseMessage.setCode(ResponseMessage.SUCCESS);
			        	responseMessage.setMsg("号百退票成功,hbOrderId="+hbOrderId);
					}else{
						Ticket updateTicket = new Ticket();
						updateTicket.setId(ticket.getId());
						updateTicket.setUpdateTime(new Date());
						updateTicket.setStatus("1");//退票申失败
						trainTicketMapper.updateTicket(updateTicket);
						responseMessage.setCode(ResponseMessage.ERROR);
			        	responseMessage.setMsg("调用号百申请退票异常,hbOrderId="+hbOrderId);
						logger.info("调用号百申请退票异常,hbOrderId="+hbOrderId);
					}
				}else{
					responseMessage.setCode(ResponseMessage.ERROR);
		        	responseMessage.setMsg("checkRefundTicketTMON接口orderItem数据异常!");
				}
			}
		}else{
			responseMessage.setCode(ResponseMessage.ERROR);
        	responseMessage.setMsg("checkRefundTicketTMON接口ticketList数据异常!");
		}
		return responseMessage;
	}
	
	/**
	 * 通知号百申请退票
	 * @throws Exception
	 */
	public RefundTicketReturnVo checkRefundTicket(String orderNo,Long orderId,String requestNo, RefundTicketVo refundTicketVo) throws Exception{
		RefundTicketReturnVo vo = null;
		logger.info("通知号百申请退票orderNo="+orderNo + ",refundTicketVo="+refundTicketVo);
		if(StringUtils.isNotEmpty(orderNo) && null != orderId && StringUtils.isNotEmpty(requestNo) && null != refundTicketVo){
			long start = System.currentTimeMillis();
			String refundApplyUrl = messageSource.getMessage("HB_TRAIN_REFUND_APPLY", new Object[]{}, LocaleContextHolder.getLocale());
			String businessId = orderId.toString();
			JSONObject params = new JSONObject();
			params.put("businessId", businessId);//调用方订单唯一标识
			params.put("requestNo", requestNo);//请求流水号
			params.put("orderNo", orderNo);//号百订单编号
			List<RefundTicketVo> list = new ArrayList<RefundTicketVo>();//退票信息列表，目前只支持单张退票
			list.add(refundTicketVo);
			params.put("tickets", list);
			String body = null;
			try {
				body = HttpClientUtil.httpPostBody(refundApplyUrl,params.toString());
			} catch (Exception e1) {
				logger.info("申请退票异常---"+e1.getMessage());
			}
			long end = System.currentTimeMillis();
			logger.info("耗时："+(end-start)+"ms,refundApplyUrl"+refundApplyUrl+",params="+params+",接口返回=" + body);
			if(StringUtils.isNotEmpty(body)){
				JSONObject obj = null;
				try {
					obj = JSONObject.parseObject(body);
				} catch (Exception e) {
					logger.error("申请退票异常---",e);
				}
				if(null != obj){
					String code = obj.getString("code");
					if(code.equals("0")){
						JSONObject result = obj.getJSONObject("result");
						logger.info("申请退票result=" + result);
						if(null != result){
							JSONArray array = result.getJSONArray("rts");
							if(null != array && array.size() > 0){
								vo = JSONObject.toJavaObject(array.getJSONObject(0), RefundTicketReturnVo.class);
							}
						}
					}
				}
			}
		}
		return vo;
	}
	
	//取消座位
	//hbOrderNo号百订单号
	@Override
	public ResponseMessage checkTrainCancel(String hbOrderNo) throws Exception{
		ResponseMessage responseMessage = new ResponseMessage();
		responseMessage.setCode(ResponseMessage.ERROR);
    	responseMessage.setMsg("取消座位失败！");
		long start = System.currentTimeMillis();
		//火车票类型： 0-订票 1-改签 2-退票
		List<TicketInfoVo> list = trainTicketMapper.findTicketByHbOrderId(hbOrderNo, "0");
		if(null != list && list.size() > 0){
			TicketInfoVo vo = list.get(0);
			OrderItem orderItem = trainOrderItemMapper.find(vo.getOrderItemId());
			if(null != orderItem){
				String url =  messageSource.getMessage("HB_TRAIN_CANCEL_URL", new Object[]{}, LocaleContextHolder.getLocale());
				String businessId = orderItem.getOrderId().toString();
				JSONObject params = new JSONObject();
				params.put("businessId", businessId);//调用方订单唯一标识
				params.put("requestNo", vo.getRequestNo());//请求流水号
				params.put("orderNo", hbOrderNo);//号百订单编号
				String body = null;
				try {
					body = HttpClientUtil.httpPostBody(url,params.toString());
				} catch (Exception e1) {
					logger.info("取消座位异常---"+e1.getMessage());
				}
				long end = System.currentTimeMillis();
				logger.info("耗时："+(end-start)+"ms,取消座位url"+url+",params=" +params+ ",接口返回=" + body);
				if(StringUtils.isNotEmpty(body)){
					JSONObject obj = null;
					try {
						obj = JSONObject.parseObject(body);
					} catch (Exception e) {
						logger.error("取消座位异常---",e);
					}
					if(null != obj){
						String code = obj.getString("code");
						String message = obj.getString("message");
						responseMessage.setMsg(message);
						if(code.equals("0")){
							Date date = new Date();
							for(TicketInfoVo ticketInfoVo : list){
								Ticket ticket = new Ticket();
								ticket.setId(ticketInfoVo.getId());
								ticket.setStatus("12");//12=已取消
								ticket.setUpdateTime(date);
								trainTicketMapper.updateTicket(ticket);
							}
							responseMessage.setCode(ResponseMessage.SUCCESS);
				        	responseMessage.setMsg("取消座位成功！");
						}
					}
				}
			}else{
				responseMessage.setCode(ResponseMessage.ERROR);
	        	responseMessage.setMsg("orderItem数据异常！");
			}
		}else{
			responseMessage.setCode(ResponseMessage.ERROR);
        	responseMessage.setMsg("list数据异常！");
		}
		return responseMessage;
	}
	
	
	/**
	  * 改签订单详情
	  * @return 
	  * ResponseMessage
	 */
	public ResponseMessage queryChangeTicketDetail(String changeOrderNo){
		logger.info("开始查询改签订单明细====订单号："+changeOrderNo);
		ResponseMessage responseMessage = new ResponseMessage();
		try{
			String url =  messageSource.getMessage("HB_", new Object[]{}, LocaleContextHolder.getLocale());
			long startTime = System.currentTimeMillis();
			String resp = HttpClientUtil.httpPostBody(url, changeOrderNo);
			long endTime = System.currentTimeMillis();
			logger.info("改签订单明细接口查询耗时："+(endTime-startTime)+"===返回结果:"+resp);
			//处理改签明细查询返回的结果
		}catch(Exception e){
			buildResponnse(responseMessage, ResponseMessage.ERROR, "查询改签单明细异常");
			logger.error("===查询改签单明细异常:",e);
		}
		logger.info("====查询改签订单明细结束====");
		return responseMessage;
	}
	
	
	private ResponseMessage handleResponse(String response, String orderNo) {
		ResponseMessage responseMessage = new ResponseMessage();
		if (StringUtils.isNotEmpty(response) && response.startsWith("{")) {
			JSONObject jsonObj = JsonUtil.stringToJsonObject(response);
			String code = jsonObj.getString("code");
			if (StringUtils.isEquals("0", code)) {
				JSONObject result = jsonObj.getJSONObject("result");
				if (result != null) {
					OrderDetailVo orderDetail = (OrderDetailVo) JsonUtil.JsonString2Object(result.toJSONString(),
							OrderDetailVo.class);
				}
				responseMessage.setCode(ResponseMessage.SUCCESS);
			} else {
				buildResponnse(responseMessage, ResponseMessage.ERROR, jsonObj.getString("message"));
			}
		} else {
			buildResponnse(responseMessage,ResponseMessage.ERROR,"数据格式不正确");
		}
		return responseMessage;
	}
	
	private void buildResponnse(ResponseMessage responseMessage,String code,String message){
		responseMessage.setCode(code);
		responseMessage.setMsg(message);
	}
	
	/**
	 * 火车票退单详情
	 *
	 */
	@Override
	public ResponseMessage updateReturnTicketStatus(String refundOrderNo) throws Exception{
		ResponseMessage responseMessage = new ResponseMessage();
		String returnDetailUrl = messageSource.getMessage("HB_TRAIN_RETURN_TICKET_DETAIL", new Object[]{}, LocaleContextHolder.getLocale());
		//String returnDetailUrl = "http://10.10.39.103/router/service/cr/searchTrainReturnTicketDetail?appKey=2011";
		//查询退票单
		List<TicketInfoVo> ticketList = trainTicketMapper.findTicketByHbOrderId(refundOrderNo, "2");
		logger.info("查询退票单ticketList=" + ticketList);
		if(null != ticketList && ticketList.size() > 0){
			TicketInfoVo vo = ticketList.get(0);
			OrderItem orderItem = trainOrderItemMapper.find(vo.getOrderItemId());
			if(null != orderItem){
				String businessId = orderItem.getOrderId().toString();
				JSONObject params = new JSONObject();
				params.put("businessId", businessId);
				params.put("requestNo", vo.getRequestNo());
				params.put("refundOrderNo", refundOrderNo);//号百退票单号
				long start = System.currentTimeMillis();
				String body = null;
				try {
					body = HttpClientUtil.httpPostBody(returnDetailUrl,params.toString());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				long end = System.currentTimeMillis();
				logger.info("耗时："+(end-start)+"ms,updateReturnTicketStatus="+returnDetailUrl+",params="+params+",接口返回=" + body);
				//System.out.println(body);
				logger.info("火车票退单详情updateReturnTicketStatus返回值body=" + body);
				if(StringUtils.isNotEmpty(body)){
					JSONObject obj = null;
					try {
						obj = JSONObject.parseObject(body);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if(null != obj){
						String code = obj.getString("code");
						if(code.equals("0")){
							JSONObject result = obj.getJSONObject("result");
							if(null != result){
								logger.info("火车票退单详情result=" + result);
								TrainReturnTicketDetailVo tTrainReturnTicketDetailVo = null;
								try {
									tTrainReturnTicketDetailVo = JSONObject.toJavaObject(result, TrainReturnTicketDetailVo.class);
								} catch (Exception e) {
									e.printStackTrace();
								}
								Map<String,Object> data = new HashMap<String,Object>();
								data.put("tTrainReturnTicketDetailVo", tTrainReturnTicketDetailVo);
								responseMessage.setData(data);
								if(null != tTrainReturnTicketDetailVo){
									//退票单信息
									TrainReturnVo trainReturnVo = tTrainReturnTicketDetailVo.getTrainReturnVo();
									//退单明细
									List<TrainReturnMxVo> trainReturnMxVoList = tTrainReturnTicketDetailVo.getTrainReturnMxVoList();
									//火车票类型： 0-订票 1-改签 2-退票
									if(null != trainReturnVo && null != trainReturnMxVoList && trainReturnMxVoList.size() > 0){
										
										if(null != ticketList && ticketList.size() > 0){
											for(TrainReturnMxVo trainReturnMxVo : trainReturnMxVoList){
												for(TicketInfoVo ticketInfoVo : ticketList){
													//如果票号相同
													if(ticketInfoVo.getTicketNo().equals(trainReturnMxVo.getTicketNo())){
														/*Ticket ticket = new Ticket();
														ticket.setId(ticketInfoVo.getId());
														ticket.setStatus(trainReturnVo.getRefundState());
														ticket.setUpdateTime(new Date());
														trainTicketMapper.update(ticket);*/
														break;
													}
												}
											}
											responseMessage.setCode(ResponseMessage.SUCCESS);
											responseMessage.setMsg("火车票退单状态！refundOrderNo=" + refundOrderNo);
										}
									}
								}
							}
						}else{
							String message = obj.getString("message");
							responseMessage.setCode(ResponseMessage.ERROR);
							responseMessage.setMsg("错误信息,查询火车票退单详情" + returnDetailUrl + ",params=" + params + ",message=" + message);
							logger.info("错误信息,查询火车票退单详情" + returnDetailUrl + ",params=" + params + ",message=" + message);
						}
					}else{
						responseMessage.setCode(ResponseMessage.ERROR);
						responseMessage.setMsg("火车票退单详情updateReturnTicketStatus返回值body错误！body=" + body);
					}
					
				}else{
					responseMessage.setCode(ResponseMessage.ERROR);
					responseMessage.setMsg("火车票退单详情updateReturnTicketStatus返回值body为空！");
				}
			}else{
				responseMessage.setCode(ResponseMessage.ERROR);
				responseMessage.setMsg("火车票退单详情orderItem为空！");
			}
		}else{
			responseMessage.setCode(ResponseMessage.ERROR);
			responseMessage.setMsg("火车票退单详情updateReturnTicketStatus查询退票单ticketList为空！");
		}
		return responseMessage;
	}
	
	/**
	 * 火车票改签单详情
	 */
	@Override
	public ResponseMessage updateChangeTicketStatus(String changeOrderNo) throws Exception{
		ResponseMessage responseMessage = new ResponseMessage();
		String changeDetailUrl = messageSource.getMessage("HB_TRAIN_CHANGE_TICKET_DETAIL", new Object[]{}, LocaleContextHolder.getLocale());
		//String changeDetailUrl = "http://10.10.39.103/router/service/cr/searchTrainGqDetail?appKey=2011";
		List<TicketInfoVo> ticketList = trainTicketMapper.findTicketByHbOrderId(changeOrderNo, "1");
		logger.info("查询改签单ticketList=" + ticketList);
		if(null != ticketList && ticketList.size() > 0){
			TicketInfoVo vo = ticketList.get(0);
			OrderItem orderItem = trainOrderItemMapper.find(vo.getOrderItemId());
			if(null != orderItem){
				String businessId = orderItem.getOrderId().toString();
				JSONObject params = new JSONObject();
				params.put("businessId", businessId);
				params.put("requestNo", vo.getRequestNo());
				params.put("changeOrderNo", changeOrderNo);
				String body = null;
				long start = System.currentTimeMillis();
				try {
					body = HttpClientUtil.httpPostBody(changeDetailUrl,params.toString());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				long end = System.currentTimeMillis();
				logger.info("耗时："+(end-start)+"ms,updateChangeTicketStatus="+changeDetailUrl+",params="+params+",接口返回=" + body);
				if(StringUtils.isNotEmpty(body)){
					JSONObject obj = null;
					try {
						obj = JSONObject.parseObject(body);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if(null != obj){
						String code = obj.getString("code");
						if(code.equals("0")){
							JSONObject result = obj.getJSONObject("result");
							if(null != result){
								logger.info("火车票改签单详情result=" + result);
								TrainGqDetailVo trainGqDetailVo = null;
								try {
									trainGqDetailVo = JSONObject.toJavaObject(result, TrainGqDetailVo.class);
								} catch (Exception e) {
									e.printStackTrace();
								}
								Map<String,Object> data = new HashMap<String,Object>();
								data.put("trainGqDetailVo", trainGqDetailVo);
								responseMessage.setData(data);
								if(null != trainGqDetailVo){
									//改签单信息
									TrainGqVo trainGqVo = trainGqDetailVo.getTrainGqVo();
									//改签单明细
									List<TrainGqMxVo> trainGqMxVoList = trainGqDetailVo.getTrainGqMxVo();
									if(null != trainGqVo && null != trainGqMxVoList && trainGqMxVoList.size() > 0){
										//火车票类型： 0-订票 1-改签 2-退票
										//查询改签单
										for(TrainGqMxVo trainGqMxVo : trainGqMxVoList){
											for(TicketInfoVo ticketInfoVo : ticketList){
												//如果身份证和票号相同
												if(ticketInfoVo.getTicketNo().equals(trainGqMxVo.getNewTicketNo()) && ticketInfoVo.getPassenger().getCertCn().equals(trainGqMxVo.getCertNo())){
													/*Ticket ticket = new Ticket();
													ticket.setId(ticketInfoVo.getId());
													ticket.setStatus(trainGqVo.getStatus());
													ticket.setUpdateTime(new Date());
													trainTicketMapper.update(ticket);*/
													break;
												}
											}
										}
										responseMessage.setCode(ResponseMessage.SUCCESS);
										responseMessage.setMsg("火车票退单状态！changeOrderNo=" + changeOrderNo);
									}
								}
							}
						}else{
							String message = obj.getString("message");
							logger.info("错误信息,查询火车票改签单详情" + changeDetailUrl + ",params=" + params + ",message=" + message);
						}
					}else{
						responseMessage.setCode(ResponseMessage.ERROR);
						responseMessage.setMsg("火车票改签单详情updateChangeTicketStatus返回值body错误！body=" + body);
					}
				}else{
					responseMessage.setCode(ResponseMessage.ERROR);
					responseMessage.setMsg("火车票改签单详情updateChangeTicketStatus返回值body为空！");
				}
			}else{
				responseMessage.setCode(ResponseMessage.ERROR);
				responseMessage.setMsg("火车票改签单详情orderItem为空！");
			}
		}else{
			responseMessage.setCode(ResponseMessage.ERROR);
			responseMessage.setMsg("火车票改签单详情updateChangeTicketStatus的ticketList为空！");
		}
		return responseMessage;
	}
	
	public TicketInfoVo findTicketInfoVoByid(Long id){
		
		return trainTicketMapper.findTicketInfoById(id);
	}
	
	/**
	 * 根据支付会话ID查询预订火车票
	 * @param payInfoId
	 * @param type
	 * @return
	 */
	public List<TicketInfoVo> findTicketByPayInfoId(Long payInfoId,String type){
		return trainTicketMapper.findTicketByPayInfoId(payInfoId, type);
	}
	
	/**
	 * 根据订购项ID查询火车票
	 * @param orderItemId
	 * @return
	 * @throws Exception
	 */
	public List<TicketInfoVo> findTicketInfoVoByOrderItemId(@Param("orderItemId") Long orderItemId,@Param("ticketNo")String ticketNo) throws Exception{
		return trainTicketMapper.findTicketInfoVoByOrderItemId(orderItemId, ticketNo);
	}
	
	@Override
	public List<TicketInfoVo> findTicketByOrderIdOrCd(Long orderId, String orderCd,String type,Long ordetItemId) throws Exception{
		return trainTicketMapper.findTicketByOrderIdOrCd(orderId, orderCd,type,ordetItemId);
	}
	
	/**
	 * 根据号百订单号查询火车票
	 * 
	 * @param hbOrderId
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<TicketInfoVo> findTicketByHbOrderId(String hbOrderId, String type) throws Exception{
		return trainTicketMapper.findTicketByHbOrderId(hbOrderId, type);
	}
	
	/**
	 * 根据ID更新火车票
	 * @param ticket
	 * @return
	 * @throws Exception
	 */
	public int updateTicket(Ticket ticket) throws Exception{
		return trainTicketMapper.updateTicket(ticket);
	}
	
	/**
	 * 根据订单ID查询可以退票的火车票
	 * @param orderId
	 * @param type
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<TicketInfoVo> findCanRefundTicketByOrderId(Long orderId,
			String type) throws Exception {
		return trainTicketMapper.findCanRefundTicketByOrderId(orderId, type);
	}
	
	/**
	 * 查询票信息
	 * @param orderCn 订单号
	 * @param type 0-订票 1-改签 2-退票
	 * @param status 票状状态信息
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<TicketInfoVo> findTicketInfoByOrderCd(String orderCn, String type, String status) throws Exception {
		
		return trainTicketMapper.findTicketInfoByOrderCd(orderCn, type, status);
	}

	@Override
	public Ticket findTicketByHbIdAndPassengerId(Ticket ticket) throws Exception {
		return trainTicketMapper.findTicketByHbIdAndPassengerId(ticket);
	}
	
	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/**
	 * 查询退票火车票
	 * @param orderId
	 * @param orderCd
	 * @param type
	 * @param orderItemId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<TicketInfoVo> findRefundTicketByOrderIdOrCd(Long orderId,String orderCd, String type, Long orderItemId) throws Exception {
		return trainTicketMapper.findRefundTicketByOrderIdOrCd(orderId, orderCd, type, orderItemId);
	}

	

}
