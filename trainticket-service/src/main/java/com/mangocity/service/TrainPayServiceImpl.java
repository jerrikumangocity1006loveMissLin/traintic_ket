package com.mangocity.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mangocity.api.ITrainOrderService;
import com.mangocity.api.ITrainPayService;
import com.mangocity.common.HttpClientUtil;
import com.mangocity.common.ServletSignUtil;
import com.mangocity.enums.GoodsType;
import com.mangocity.mapper.TrainOrderItemMapper;
import com.mangocity.mapper.TrainPayDetailMapper;
import com.mangocity.mapper.TrainPayInfoMapper;
import com.mangocity.mapper.TrainPayMapper;
import com.mangocity.mapper.TrainPayOrderItemMapper;
import com.mangocity.mapper.TrainTicketMapper;
import com.mangocity.model.Delivery;
import com.mangocity.model.OrderItem;
import com.mangocity.model.Ticket;
import com.mangocity.model.TrainPay;
import com.mangocity.model.TrainPayDetail;
import com.mangocity.model.TrainPayInfo;
import com.mangocity.model.TrainPayOrderItem;
import com.mangocity.response.ResponseMessage;
import com.mangocity.service.base.BaseServiceImpl;
import com.mangocity.vo.PayParamsVo;
import com.mangocity.vo.PayVo;
import com.mangocity.vo.TicketInfoVo;

public class TrainPayServiceImpl extends BaseServiceImpl<TrainPay> implements ITrainPayService{
	
	Logger logger = Logger.getLogger(TrainPayServiceImpl.class);
	
	@Autowired
	private TrainPayInfoMapper trainPayInfoMapper;
	
	@Autowired
	private TrainPayOrderItemMapper trainPayOrderItemMapper;
	
	@Autowired
	private TrainPayDetailMapper trainPayDetailMapper;
	
	@Autowired
	private TrainOrderItemMapper trainOrderItemMapper;
	
	@Autowired
	private TrainPayMapper trainPayMapper;
	
	@Resource
	private MessageSource messageSource;
	
	@Autowired
	private ITrainOrderService trainOrderService;
	
	@Autowired
	private TrainTicketMapper trainTicketMapper;
	
	/*
	 *  在线支付,包括了网站在线支付、3G在线支付、网站快捷支付
	 */
	
	public ResponseMessage createPayProcess(PayParamsVo params) throws Exception{
		ResponseMessage responseMessage = new ResponseMessage();
		if(params != null){
			Long orderId = params.getOrderId();
			String orderCn = params.getOrderCn();
			List<Long> orderItems = params.getOrderItems();//目前只能传一个值
			List<Long> goodsIds = params.getGoodsIds();//产品ID
			BigDecimal price = params.getPrice();
			String payModel = params.getPayModel();
			String membercd = params.getMembercd();
			String passenger = params.getPassenger();
			String payInfoType = params.getPayInfoType();//支付类型：0付款，1退款
			Long integralNum = params.getIntegralNum();//积分兑换数
			String goodsType = params.getGoodsType();
			if(orderId != null && StringUtils.isNotEmpty(orderCn) && null != goodsIds && goodsIds.size() > 0 && StringUtils.isNotEmpty(goodsType)
					&& StringUtils.isNotEmpty(payInfoType) && StringUtils.isNotEmpty(payModel) &&(price != null || integralNum != null)){
				try {
					//添加配送项
					Delivery delivery = params.getDelivery();
					if(null != delivery){
						try {
							logger.info("开始添加配送项,delivery=" + delivery.toString() + ",orderId=" + orderId);
							trainOrderService.addDeliveryItem(delivery,orderId,null);
						} catch (Exception e) {
							e.printStackTrace();
							logger.info("添加配送项异常,delivery=" + delivery.toString() + ",orderId=" + orderId);
						}
					}
					
					TrainPay pay = new TrainPay();
					String outTradeNo = createPay(params,pay);
					//生成支付工单（在线支付）
					if(StringUtils.isNotEmpty(outTradeNo)){
						if(price != null){//现金支付
							logger.info("开始调用支付平台（在线支付）生成支付工单：orderCn" + orderCn + ",outTradeNo=" + outTradeNo);
							String result = createMPmOrderOnline(orderCn,outTradeNo,price,payModel,membercd,passenger);
							logger.info("调用支付平台（在线支付）生成支付工单：orderCn" + orderCn + ",outTradeNo=" + outTradeNo + ",result=" + result);
							boolean flagResult = checkResult(result);
							String status = "2";//支付失败
							if(flagResult){
								status = "1";//在线支付中
								responseMessage.setCode(ResponseMessage.SUCCESS);
								responseMessage.setMsg("支付创建成功！");
								Map<String,Object> data = new HashMap<String,Object>();
								data.put("pay", pay);
								responseMessage.setData(data);
							}else{
								responseMessage.setCode(ResponseMessage.ERROR);
								responseMessage.setMsg("生成支付工单问题！result=" + result);
							}
							//查询为支付的详细
							List<TrainPayDetail> list = trainPayDetailMapper.findByPayId(pay.getId(),"0");
							Date date = new Date();
							for(TrainPayDetail trainPayDetail : list){
								TrainPayDetail detail = new TrainPayDetail();
								detail.setId(trainPayDetail.getId());
								detail.setModifyTime(date);
								detail.setStatus(status);//支付状态
								trainPayDetailMapper.update(detail);
							}
						}
						if(integralNum != null){//积分支付
							
						}
					}else{
						responseMessage.setCode(ResponseMessage.ERROR);
						responseMessage.setMsg("outTradeNo为空！");
					}
					
				} catch (Exception e) {
					responseMessage.setCode(ResponseMessage.ERROR);
					responseMessage.setMsg("创建支付会话表、支付表createPay接口异常！");
					logger.info(e.getMessage());
				}
			}else{
				responseMessage.setCode(ResponseMessage.ERROR);
				responseMessage.setMsg("params"+params +"参数错误！");
			}
		}else{
			responseMessage.setCode(ResponseMessage.ERROR);
			responseMessage.setMsg("params"+params +"参数错误！");
			logger.info("params="+ params + "参数错误！");
		}
		return responseMessage;
	}
	
	//创建支付会话表、支付表、支付详情
	//支付状态：0=待支付，1=在线支付中，2=支付失败，3=部分支付，4=已支付
	public String createPay(PayParamsVo params,TrainPay pay) throws Exception{
		String outTradeNo = null;
		if(null != params){
			try {
				Long orderId = params.getOrderId();
				String orderCn = params.getOrderCn();
				List<Long> orderItems = params.getOrderItems();
				List<Long> goodsIds = params.getGoodsIds();
				BigDecimal price = params.getPrice() != null ? params.getPrice() : new BigDecimal("0.0");
				Long integralNum = params.getIntegralNum() != null ? params.getIntegralNum() : 0L;//积分兑换数
				String payModel = params.getPayModel();
				String payInfoType = params.getPayInfoType();//支付类型：0付款，1退款
				String goodsType = params.getGoodsType();
				long currentTime = System.currentTimeMillis();
				Date date = new Date(currentTime);
				Date endDate = new Date(currentTime + 30 * 60 * 1000);//半个小时
				logger.info("创建支付会话params=" +params.toString());
				BigDecimal integralPrice = new BigDecimal(integralNum);
				integralPrice = integralPrice.divide(new BigDecimal("100"));//积分价格
				List<TrainPayInfo> payInfoList = trainPayInfoMapper.findPayInfoByItemIdCn(orderId,payInfoType,goodsIds,goodsType);
				if(null != payInfoList && payInfoList.size() > 0){
					TrainPayInfo payInfo = payInfoList.get(0);
					pay = trainPayMapper.findPayByInfoId(payInfo.getId());
					if(null != pay){
						//先检查是否是有支付失败的详情
						List<TrainPayDetail> trainPayDetailList = trainPayDetailMapper.findByPayId(pay.getId(), "2");//2=支付失败
						if(null == trainPayDetailList || trainPayDetailList.size() < 1){
							//不存在支付失败的则更新支付价格
							TrainPay trainPay = new TrainPay();
							trainPay.setId(pay.getId());
							trainPay.setAmount(price.add(integralPrice));
							trainPay.setModifyTime(date);
							trainPayMapper.update(trainPay);
						}
						
						//支付详情
						TrainPayDetail detail = new TrainPayDetail();
						detail.setTrainPayId(pay.getId());
						detail.setPayModel(payModel);//支付方式
						if(StringUtils.isNotEmpty(params.getVoucherCd())){
							detail.setVoucherCd(params.getVoucherCd());//代金劵编号
						}
						detail.setIntegralNum(integralNum);//积分兑换数
						detail.setPayAmount(price);
						detail.setStatus("0");//0：支付中
						detail.setCreateTime(date);
						outTradeNo = ServletSignUtil.CUSTOMER_ID + orderCn + getOutTradeNo(orderCn);
						detail.setPayNumber(outTradeNo);
						trainPayDetailMapper.save(detail);
					}
				}else{
					//支付会话
					TrainPayInfo payInfo = new TrainPayInfo();
					payInfo.setOrderId(orderId);
					payInfo.setOrderCd(orderCn);
					payInfo.setStatus("0");//0：支付中
					payInfo.setType(params.getPayInfoType());//支付类型：0付款，1退款
					payInfo.setDateTime(date);
					payInfo.setEndTime(endDate);
					trainPayInfoMapper.save(payInfo);
					//支付表
					
					pay.setPayInfoId(payInfo.getId());
					pay.setOrderCd(orderCn);
					pay.setAmount(price.add(integralPrice));
					pay.setStatus("0");//0：支付中
					pay.setCreateTime(date);
					this.save(pay);
					
					//支付订购项
					Map<Long,Long> itemMap = new HashMap<Long,Long>();
					for(Long goodsId : goodsIds){
						if(itemMap.get(goodsId) == null){
							TrainPayOrderItem orderItem = new TrainPayOrderItem();
							orderItem.setPayInfoId(payInfo.getId());
							orderItem.setGoodsId(goodsId);
							orderItem.setGoodsType(goodsType);
							orderItem.setCreateTime(date);
							trainPayOrderItemMapper.save(orderItem);
						}
						itemMap.put(goodsId, goodsId);
					}
					
					//支付详情
					TrainPayDetail detail = new TrainPayDetail();
					detail.setTrainPayId(pay.getId());
					detail.setPayModel(payModel);//支付方式
					if(StringUtils.isNotEmpty(params.getVoucherCd())){
						detail.setVoucherCd(params.getVoucherCd());//代金劵编号
					}
					detail.setIntegralNum(integralNum);//积分兑换数
					detail.setPayAmount(price);
					detail.setStatus("0");//0：支付中
					detail.setCreateTime(date);
					outTradeNo = ServletSignUtil.CUSTOMER_ID + orderCn + getOutTradeNo(orderCn);
					detail.setPayNumber(outTradeNo);
					trainPayDetailMapper.save(detail);
				}
				
				long time = System.currentTimeMillis();
				logger.info("时间：" + (time - currentTime)  + "ms,完成支付会话params=" +params);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return outTradeNo;
	}
	
	
	/*
	 * 支付平台回调接口，更新支付状态
	 * 支付状态：0=待支付，1=在线支付中，2=支付失败，3=部分支付，4=已支付
	 */
	public ResponseMessage createPayDetail(PayParamsVo params) throws Exception{
		ResponseMessage responseMessage = new ResponseMessage();
		if(null != params){
			logger.info("createPayDetail参数params=" +params.toString());
			String xmlStr = params.getXmlStr();//支付平台调用参数
			//String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?><mpm><result><success>T</success><message></message><response><payMessage></payMessage><sign>078f6984c244e4e28a6693cfe9f0b656</sign><signType>MD5</signType><outTradeNo>1004160526240333169</outTradeNo><payTime>2016-05-26 18:10:39</payTime><payResult>SUCCESS</payResult><operator>system</operator><notifyType>2</notifyType><payAmount>11.00</payAmount></response></result></mpm>";
			if(StringUtils.isNotEmpty(xmlStr)){
				try {
					TrainPayDetail detail = new TrainPayDetail();
					logger.info("支付返回xmlStr=" + xmlStr);
					InputStream in = null;
			        InputStreamReader utfreader = null;
					SAXReader reader = new SAXReader();
		            in = new ByteArrayInputStream(xmlStr.getBytes());
		            utfreader = new InputStreamReader(in, "UTF-8");        
		            Document document = reader.read(utfreader);
		            Element root = document.getRootElement();
					String success = root.element("result").elementText("success");
					if("T".equalsIgnoreCase(success)){//T：请求成功，F：请求失败
						String payResult = root.element("result").element("response").elementText("payResult");
						if("SUCCESS".equalsIgnoreCase(payResult)){//支付成功
							String outTradeNo = root.element("result").element("response").elementText("outTradeNo");
							String payAmount = root.element("result").element("response").elementText("payAmount");
							if(StringUtils.isNotEmpty(outTradeNo)){
								detail.setPayNumber(outTradeNo);
								List<TrainPayDetail> payDetailList = trainPayDetailMapper.findByOutTradeNo(outTradeNo);
								if(null != payDetailList && payDetailList.size() > 0){
									TrainPayDetail payDetail = payDetailList.get(0);
									Long payId = payDetail.getTrainPayId();
									List<TrainPayDetail> list = trainPayDetailMapper.findByPayId(payId,"4");//根据支付ID得到所有已支付的支付详情
									if(null != list && list.size() > 0){
										TrainPay trainPay = trainPayMapper.find(payId);
										String payStatus = "0";//支付中
										Long integralNumAll = 0L;
										BigDecimal payAmountAll = new BigDecimal("0");
										for(TrainPayDetail d : list){
											integralNumAll += d.getIntegralNum();
											payAmountAll = payAmountAll.add(d.getPayAmount());
										}
										//积分数,100积分=1元
										BigDecimal integralPrice = new BigDecimal(integralNumAll);
										integralPrice = integralPrice.divide(new BigDecimal(100));
										if(StringUtils.isNotEmpty(payAmount)){
											BigDecimal price = integralPrice.add(payAmountAll).add(new BigDecimal(payAmount));
											if(null != trainPay){
												if((price.compareTo(trainPay.getAmount()) != -1)){//应该支付价格 <= （实际支付价格+积分支付价格）
													payStatus = "1";//支付成功
													//更新支付详情
													Date date = new Date();
													detail.setId(payDetail.getId());
													detail.setStatus(payStatus);
													detail.setModifyTime(date);
													detail.setPayAmount(new BigDecimal(payAmount));
													trainPayDetailMapper.update(detail);
													for(TrainPayDetail d : list){
														if(d.getPayNumber() != outTradeNo){
															TrainPayDetail detail_ = new TrainPayDetail();
															detail_.setId(d.getId());
															detail_.setStatus(payStatus);
															detail_.setModifyTime(date);
															trainPayDetailMapper.update(detail_);
														}
													}
													logger.info("payDetailId" + payDetail.getId() +",支付详情表更新成功！");
													
													TrainPay pay = new TrainPay();
													pay.setId(payId);
													pay.setStatus(payStatus);//支付成功
													pay.setModifyTime(date);
													trainPayMapper.update(pay);
													logger.info("payId" + payId +",支付表更新成功！");
													
													Long payInfoId = trainPay.getPayInfoId();
													TrainPayInfo trainPayInfo = trainPayInfoMapper.find(payInfoId);
													if(null != trainPayInfo){
														TrainPayInfo payInfo = new TrainPayInfo();
														payInfo.setId(trainPayInfo.getId());
														payInfo.setStatus(payStatus);
														payInfo.setModifyTime(date);
														trainPayInfoMapper.update(payInfo);
														logger.info("payInfoId" + trainPayInfo.getId() +",支付会话更新成功！");
														responseMessage.setCode(ResponseMessage.SUCCESS);
														responseMessage.setMsg("支付状态更新成功！");
													}else{
														logger.info("payInfoId" + trainPayInfo.getId() +",支付会话不存在！");
														responseMessage.setCode(ResponseMessage.ERROR);
														responseMessage.setMsg("payInfoId" + trainPayInfo.getId() +",支付会话不存在！");
													}
												}
											}else{
												responseMessage.setCode(ResponseMessage.ERROR);
												responseMessage.setMsg("payId" + payId +",支付不存在！");
												logger.info("payId" + payId +",支付不存在！");
											}
										}
								}
							}
						}
					}else{
						responseMessage.setCode(ResponseMessage.ERROR);
						responseMessage.setMsg("支付平台支付失败！");
					}
				}else{
					responseMessage.setCode(ResponseMessage.ERROR);
					responseMessage.setMsg("支付平台支付请求失败！");
				}
			  } catch (Exception e) {
				responseMessage.setCode(ResponseMessage.ERROR);
				responseMessage.setMsg("支付平台回调接口，更新支付状态createPayDetail接口异常！");
				logger.info(e.getMessage());;
			  } 
			}else{
				responseMessage.setCode(ResponseMessage.ERROR);
				responseMessage.setMsg("params"+params +"参数错误！");
			}
		}else{
			responseMessage.setCode(ResponseMessage.ERROR);
			responseMessage.setMsg("params"+params +"参数错误！");
		}
		return responseMessage;
	}
	
	/**
	 * 调用支付平台（在线支付）生成支付工单
	 * @param orderCd
	 * @param outTradeNo
	 * @param amount
	 * @param payMode
	 * @param membercd
	 * @param passenger
	 * @throws Exception
	 */
	public String createMPmOrderOnline(String orderCd, String outTradeNo, BigDecimal amount,String payMode, String membercd, String passenger) throws Exception{
		long startTime = System.currentTimeMillis();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String requestDate = dateFormat.format(new Date());
		//（在线支付）生成支付工单回调地址
		String returnURL = messageSource.getMessage("ONLINE_RETURN_URL", new Object[]{}, LocaleContextHolder.getLocale());
		//String returnURL = "http://10.10.39.103/router/";
		String amountString = amount.setScale(2,BigDecimal.ROUND_UP).toString();
		String remark = payMode;
		Map<String, String> params = new HashMap<String, String>();
		params.put("orderCd", orderCd);
		params.put("outTradeNo", outTradeNo);
		params.put("customerId", ServletSignUtil.CUSTOMER_ID);
		params.put("gatheringUnitCode", ServletSignUtil.GATHERING_UNITCODE);
		params.put("currencyType", ServletSignUtil.CURRENCY_RMB);
		params.put("requestDate", requestDate);
		params.put("productType", ServletSignUtil.PRODUCT_TYPE_TRAIN);
		params.put("payMode", payMode);
		params.put("amount", amountString);
		params.put("returnURL", returnURL);
		params.put("remark", remark);
		params.put("memberCD", membercd);
		params.put("passenger", passenger);
		String sign = ServletSignUtil.buildSign(params, ServletSignUtil.PSD_KEY);
		// 将参数用UTF-8编码格式encode
		NameValuePair orderCdPair = new NameValuePair("orderCd", orderCd);
		NameValuePair outTradeNoPair = new NameValuePair("outTradeNo", outTradeNo);
		NameValuePair customerIdPair = new NameValuePair("customerId", ServletSignUtil.CUSTOMER_ID);
		NameValuePair gatheringUnitCodePair = new NameValuePair("gatheringUnitCode", ServletSignUtil.GATHERING_UNITCODE);
		NameValuePair currencyTypePair = new NameValuePair("currencyType", ServletSignUtil.CURRENCY_RMB);
		NameValuePair requestDatePair = new NameValuePair("requestDate", requestDate);
		NameValuePair productTypePair = new NameValuePair("productType", ServletSignUtil.PRODUCT_TYPE_TRAIN);
		NameValuePair payModePair = new NameValuePair("payMode", payMode);
		NameValuePair amountPair = new NameValuePair("amount", amountString);
		NameValuePair returnURLPair = new NameValuePair("returnURL", returnURL);
		NameValuePair memberCDPair = new NameValuePair("memberCD", membercd);
		NameValuePair remarkPair = new NameValuePair("remark", remark);
		NameValuePair signTypePair = new NameValuePair("signType", ServletSignUtil.SIGN_TYPE);
		NameValuePair signPair = new NameValuePair("sign", sign);
		NameValuePair passengerPair = new NameValuePair("passenger", passenger);

		NameValuePair[] nameValuePair = new NameValuePair[] { orderCdPair, outTradeNoPair, customerIdPair, gatheringUnitCodePair,
				currencyTypePair, requestDatePair, productTypePair, payModePair, amountPair, returnURLPair,
				memberCDPair, remarkPair, signTypePair, signPair, passengerPair };
		HttpClient httpclient = new HttpClient();
		// 创建post对象,指定远程服务地址
		//（在线支付）生成支付工单请求地址
		String requestUrl = messageSource.getMessage("ONLINE_REQUEST_URL", new Object[]{}, LocaleContextHolder.getLocale());
		//String requestUrl = "http://test39.mangocity.com/MpmWeb/paygateway/paymentOnline_service.action";
		logger.info("payment request url : " + requestUrl + ",params=" + params);
		PostMethod post = new PostMethod(requestUrl);
		//post.addRequestHeader("Content-type" , "text/html; charset=utf-8");
		post.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		post.setRequestBody(nameValuePair);
		// 设置链接超时
		httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(ServletSignUtil.CONNECTION_TIME);
		// 设置读取远程服务返回数据超时时间
		post.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, ServletSignUtil.SEVICE_TIME);
		try {
			int index = httpclient.executeMethod(post);
			logger.info(">>>>>parseXml（同步返回） index=" + index);
			// 对远程服务返回数据用UTF-8进行encoding
			String result = EncodingUtil.getString(post.getResponseBody(), "UTF-8");
			long endTime = System.currentTimeMillis();
			logger.info(">>>>>耗时：" +(endTime - startTime)+ "ms,parseXml（同步返回） result=" + result);
			return result;
		} catch (HttpException he) {
			logger.error(he.getMessage(), he);
		} catch (IOException ie) {
			logger.error(ie.getMessage(), ie);
		} finally {
			post.releaseConnection();
		}
		return "";
	}
	/**
	 * 在线支付验证
	 * @param result
	 * @return
	 */
	public boolean checkResult(String result) {
		if(StringUtils.isNotEmpty(result)){
			if (result.equalsIgnoreCase("SUCCESS")) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	/**
	 * 离线支付验证
	 * @param result
	 * @return
	 */
	public boolean checkOffResult(String result) {
		if(StringUtils.isNotEmpty(result)){
			if (result.equalsIgnoreCase("T")) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	/**
	 * 根据订单号统计支付
	 * @param orderCn
	 * @return
	 * @throws Exception
	 */
	public String getOutTradeNo(String orderCn) throws Exception{
		String tradeNo = "";
		String outTradeNo = trainPayDetailMapper.findPayDetailCountByOrderCn(orderCn);
		if(StringUtils.isBlank(outTradeNo)){
			tradeNo = "01";
		}else{
			String num = outTradeNo.substring(outTradeNo.length()-2, outTradeNo.length());
			int count = 0;
			try {
				count = Integer.parseInt(num);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(count < 9){
				tradeNo = "0" + (count+1);
			}else{
				tradeNo = String.valueOf((count+1));
			}
		}
		return tradeNo;
	}
	
	//离线支付,包括了信用卡支付，上门收款（包括上门POS）、现金、3G信用卡支付、CC信用卡支付
	@Override
	public ResponseMessage createOffPayProcess(PayParamsVo params) throws Exception{
		ResponseMessage responseMessage = new ResponseMessage();
		if(params != null){
			Long orderId = params.getOrderId();
			String orderCn = params.getOrderCn();
			List<Long> orderItems = params.getOrderItems();
			List<Long> goodsIds = params.getGoodsIds();
			String goodsType = params.getGoodsType();
			BigDecimal price = params.getPrice();
			String payModel = params.getPayModel();
			String membercd = params.getMembercd();
			//String passenger = params.getPassenger();
			String payInfoType = params.getPayInfoType();//支付类型：0付款，1退款
			String creditCardId = params.getCreditCardId();//信用卡ID
			String cardPayType = params.getCardPayType();//离线信用卡支付类别,全额=ALL，担保=ASS，分期=AFQ，信用卡积分=CJF
			Long integralNum = params.getIntegralNum();//积分兑换数
			if(orderId != null && StringUtils.isNotEmpty(orderCn) && null != goodsIds && goodsIds.size() > 0 && StringUtils.isNotEmpty(goodsType) && StringUtils.isNotEmpty(payModel) && StringUtils.isNotEmpty(payInfoType) 
					&& StringUtils.isNotEmpty(creditCardId) && StringUtils.isNotEmpty(cardPayType) && (price != null || integralNum != null)){
				try {
					//添加配送项
					Delivery delivery = params.getDelivery();
					if(null != delivery){
						try {
							logger.info("开始添加配送项,delivery=" + delivery.toString() + ",orderId=" + orderId);
							trainOrderService.addDeliveryItem(delivery,orderId,null);
						} catch (Exception e) {
							e.printStackTrace();
							logger.info("添加配送项异常,delivery=" + delivery.toString() + ",orderId=" + orderId);
						}
					}
					TrainPay pay = new TrainPay();
					String outTradeNo = createPay(params,pay);
					if(StringUtils.isNotEmpty(outTradeNo)){
						if(price != null){//--现金支付
							//生成支付工单（在线支付）
							logger.info("开始调用支付平台（离线支付）生成支付工单：orderCn" + orderCn + ",outTradeNo=" + outTradeNo);
							String result = createMPmOrderOffline(orderCn,outTradeNo,payModel,ServletSignUtil.CURRENCY_RMB,price,creditCardId,cardPayType,payModel,membercd,"");
							logger.info("调用支付平台（离线支付）生成支付工单：orderCn" + orderCn + ",outTradeNo=" + outTradeNo + ",result=" + result);
							boolean flagResult = checkOffResult(result);
							String status = "2";//支付失败
							if(flagResult){
								status = "1";//在线支付中
								responseMessage.setCode(ResponseMessage.SUCCESS);
								responseMessage.setMsg("支付创建成功！");
								Map<String,Object> data = new HashMap<String,Object>();
								data.put("pay", pay);
								responseMessage.setData(data);
							}else{
								responseMessage.setCode(ResponseMessage.ERROR);
								responseMessage.setMsg("生成支付工单问题！result=" + result);
							}
							//查询为支付的详细
							List<TrainPayDetail> list = trainPayDetailMapper.findByPayId(pay.getId(),"0");
							Date date = new Date();
							for(TrainPayDetail trainPayDetail : list){
								TrainPayDetail detail = new TrainPayDetail();
								detail.setId(trainPayDetail.getId());
								detail.setModifyTime(date);
								detail.setStatus(status);//支付状态
								trainPayDetailMapper.update(detail);
							}
						}
						if(integralNum != null){//积分支付
							
						}
					}else{
						responseMessage.setCode(ResponseMessage.ERROR);
						responseMessage.setMsg("outTradeNo为空！");
					}
				} catch (Exception e) {
					responseMessage.setCode(ResponseMessage.ERROR);
					responseMessage.setMsg("创建支付会话表、支付表createPay接口异常！");
					e.printStackTrace();
					logger.info(e.getMessage());
				}
			}else{
				responseMessage.setCode(ResponseMessage.ERROR);
				responseMessage.setMsg("params"+params +"参数错误！");
			}
		}else{
			responseMessage.setCode(ResponseMessage.ERROR);
			responseMessage.setMsg("params"+params +"参数错误！");
			logger.info("params="+ params + "参数错误！");
		}
		return responseMessage;
	}
	
	/**
	 * 调用支付平台（离线支付）生成支付工单
	 * @param orderCd
	 * @param outTradeNo
	 * @param payMode
	 * @param currencyType
	 * @param amount
	 * @param creditCardId
	 * @param cardPayType
	 * @param remark
	 * @param memberCD
	 * @param sid
	 * @return
	 */
	public String createMPmOrderOffline(String orderCd,String  outTradeNo,String payMode,String currencyType,
            BigDecimal amount, String creditCardId, String cardPayType,String remark,String memberCD,String sid){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String requestDate = dateFormat.format(new Date());
		Map map = new HashMap();
        map.put("orderCd", orderCd);
        map.put("outTradeNo", outTradeNo);
        map.put("customerId", ServletSignUtil.CUSTOMER_ID);
        map.put("gatheringUnitCode", ServletSignUtil.GATHERING_UNITCODE);
        map.put("currencyType", currencyType);
        map.put("requestDate", requestDate);
        map.put("productType", ServletSignUtil.PRODUCT_TYPE_TRAIN);
        map.put("payMode", payMode);
        map.put("amount", String.valueOf(amount));
        map.put("creditCardId", creditCardId);//信用卡ID
        map.put("remark", remark);
        map.put("cardPayType", cardPayType);//离线信用卡支付类别,全额=ALL，担保=ASS，分期=AFQ，信用卡积分=CJF
        map.put("stageCount", "0");//离线信用卡分期支付的分期数，如果没有则为0
        map.put("memberCD", memberCD);
        map.put("sid", sid);
        String sign = ServletSignUtil.buildSign(map, ServletSignUtil.PSD_KEY);
        
        //请求的xml格式的字符串
        StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>");
        xml.append("<mpm><service>PAYMENT</service><request>");
        xml.append("<orderCd>"+ orderCd+ "</orderCd>");
        xml.append("<outTradeNo>"+ outTradeNo+ "</outTradeNo>");
        xml.append("<customerId>"+ServletSignUtil.CUSTOMER_ID+"</customerId>");
        xml.append("<gatheringUnitCode>"+ServletSignUtil.GATHERING_UNITCODE+"</gatheringUnitCode>");
        xml.append("<currencyType>"+currencyType+"</currencyType>");
        xml.append("<requestDate>"+requestDate+"</requestDate>");
        xml.append("<productType>"+ServletSignUtil.PRODUCT_TYPE_TRAIN+"</productType>");
        xml.append("<payMode>"+payMode+"</payMode>");
        xml.append("<amount>"+String.valueOf(amount)+"</amount>");
        xml.append("<creditCardId>"+creditCardId+"</creditCardId>");
        xml.append("<remark>"+remark+"</remark>");
        xml.append("<signType>MD5</signType>");
        xml.append("<sign>"+sign+"</sign>");
        xml.append("<cardPayType>"+cardPayType+"</cardPayType>");
        xml.append("<stageCount>0</stageCount>");
        xml.append("<memberCD>"+memberCD+"</memberCD>");
        xml.append("<sid>"+sid+"</sid>");
        xml.append("</request>" + "</mpm>");
		String xmlStr = xml.toString();
        //创建post对象,指定远程服务地址
        String offPayURL = messageSource.getMessage("OFFLINE_REQUEST_URL", new Object[]{}, LocaleContextHolder.getLocale());
        String result=this.parseXml(xmlStr,offPayURL);
        logger.info("createMPmOrderOffline xmlStr=="+xmlStr + ",offPayURL=" + offPayURL + ",result=" + result);
        InputStream in = null;
        InputStreamReader utfreader = null;
        try {
            //对于返回的字符串，解析为xml格式
            SAXReader reader = new SAXReader();
            in = new ByteArrayInputStream(result.getBytes());
            utfreader = new InputStreamReader(in, "UTF-8");        
            org.dom4j.Document document = reader.read(utfreader);
            org.dom4j.Element root = document.getRootElement();                
            String retValue=root.element("result").elementText("success");
            logger.info("createMPmOrderOffline>>>>>outTradeNo=" + outTradeNo+" 支付结果="+retValue);
            return retValue; 
        } catch (DocumentException e) {
        	logger.error(e.getMessage());   
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        }finally{
            try {
                utfreader.close();
                 in.close();
            } catch (IOException e) {
            	logger.error(e.getMessage());   
            }
           
        }
        return null;
	}
	
	
    private String parseXml(String xmlStr,String url){
        HttpClient httpclient = new HttpClient();
        PostMethod post = new PostMethod(url);
        try{
            //将参数用UTF-8编码格式encode
            NameValuePair nvReqData = new NameValuePair("xmlStr", URLEncoder.encode(xmlStr, "UTF-8"));
            NameValuePair [] nameValuePair = new NameValuePair []{nvReqData};
            post.setRequestBody(nameValuePair);
            //设置链接超时
            httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(ServletSignUtil.CONNECTION_TIME);
            //设置读取远程服务返回数据超时时间
            post.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,ServletSignUtil.SEVICE_TIME);
            int index = httpclient.executeMethod(post);
            logger.info(">>>>>parseXml（同步返回） index="+index);
            //对远程服务返回数据用UTF-8进行encoding
            String result = EncodingUtil.getString(post.getResponseBody(),"UTF-8");   
            logger.info(">>>>>parseXml（同步返回） result="+result);       
            return result;
        }catch(HttpException he){
        	logger.error(he.getMessage());   
        }catch(IOException ie){
        	logger.error(ie.getMessage());   
        }finally{
            post.releaseConnection();
        }
        return null;
    }
    
    /**
     * 向支付平台申请退款(在线，离线)
     */
    @Override
    public ResponseMessage createMpmRefundProcess(PayParamsVo params) throws Exception{
    	ResponseMessage responseMessage = new ResponseMessage();
    	if(null != params){
    		logger.info("createMpmRefundProcess参数params=" + params);
    		String orderCd = params.getOrderCn();//订单号
        	String outTradeNo = params.getOutTradeNo();//外部交易号
        	BigDecimal amount = params.getPrice();
        	String refundModeCode = params.getRefundModeCode();//退款方式
        	String refundReasonCode = params.getRefundReasonCode();//退款原因
        	String operator = params.getOperator();//操作员
        	String accountName = params.getAccountName();//收款人名称
        	String accountNo = params.getAccountNo();//帐号
        	String bankName = params.getBankName();//开户银行名称
        	String createBill = params.getCreateBill();//已创行程单
        	String unCreateBill = params.getUnCreateBill();//未创行程单
        	String remark = params.getRemark();//备注
        	if(StringUtils.isEmpty(refundModeCode)){
        		refundModeCode = "CREDITCARD";//信用卡收款(这里先写死)
			}
        	if(StringUtils.isEmpty(refundReasonCode)){
        		refundReasonCode = "dishonourRefund";//退票退款(这里先写死)
        	}
        	if(StringUtils.isNotEmpty(orderCd) && StringUtils.isNotEmpty(outTradeNo) && null != amount && StringUtils.isNotEmpty(refundModeCode) && StringUtils.isNotEmpty(refundReasonCode)){
        		//参数
        		try {
					String xmlStr = createMPmRefund(orderCd,outTradeNo,amount,refundModeCode,refundReasonCode,operator,accountName,accountNo,bankName,createBill,unCreateBill,remark);
					//创建post对象,指定远程服务地址
					String refundURL = messageSource.getMessage("OFFLINE_REQUEST_URL", new Object[]{}, LocaleContextHolder.getLocale());
					String result=this.parseXml(xmlStr,refundURL);
					logger.info("createMPmOrderOffline xmlStr=="+xmlStr + ",refundURL=" + refundURL + ",result=" + result);
					//解析
					InputStream in = null;
					InputStreamReader utfreader = null;
					SAXReader reader = new SAXReader();
					in = new ByteArrayInputStream(result.getBytes());
					utfreader = new InputStreamReader(in, "UTF-8");        
					Document document = reader.read(utfreader);
					Element root = document.getRootElement();
					String success = root.element("result").elementText("success");
					if("T".equalsIgnoreCase(success)){
						responseMessage.setCode(ResponseMessage.SUCCESS);
						responseMessage.setMsg("退款成功！");
					}else{
						String message = root.element("result").elementText("message");
						responseMessage.setCode(ResponseMessage.ERROR);
						responseMessage.setMsg("退款失败！message=" + message);
					}
				} catch (Exception e) {
					responseMessage.setCode(ResponseMessage.ERROR);
	    			responseMessage.setMsg("createMpmRefundProcess接口异常！");
					e.printStackTrace();
				}
        	}else{
        		responseMessage.setCode(ResponseMessage.ERROR);
    			responseMessage.setMsg("params"+params +"参数错误！");
        	}
    	}else{
    		responseMessage.setCode(ResponseMessage.ERROR);
			responseMessage.setMsg("params"+params +"参数错误！");
			logger.info("params="+ params + "参数错误！");
    	}
    	return responseMessage;
    }
	
    //在线退款、离线退款(调用支付平台接口和离线支付是同一接口只是参数不一样)
    public String createMPmRefund(String orderCd,String outTradeNo,BigDecimal amount,String refundModeCode,String refundReasonCode,
    		String operator,String accountName,String accountNo,String bankName,String createBill,String unCreateBill,String remark) throws Exception{
    	String amountString = amount.setScale(2,BigDecimal.ROUND_UP).toString();
    	operator = StringUtils.isBlank(operator) ? "" : operator;
    	accountName = StringUtils.isBlank(accountName) ? "" : accountName;
    	accountNo= StringUtils.isBlank(accountNo) ? "" : accountNo;
    	bankName = StringUtils.isBlank(bankName) ? "" : bankName;
    	createBill = StringUtils.isBlank(createBill) ? "" : createBill;
    	unCreateBill = StringUtils.isBlank(unCreateBill) ? "" : unCreateBill;
    	remark = StringUtils.isBlank(remark) ? "" : remark;
    	Map<String, String> params = new HashMap<String, String>();
		params.put("customerId", ServletSignUtil.CUSTOMER_ID);
		params.put("outTradeNo", outTradeNo); 
	    params.put("orderCd", orderCd);
	    params.put("amount", amountString);
	    params.put("currencyType", ServletSignUtil.CURRENCY_RMB);
	    params.put("refundModeCode", refundModeCode);
	    params.put("refundReasonCode", refundReasonCode);
	    params.put("operator", operator);
	    params.put("accountName", accountName);
	    params.put("accountNo", accountNo);
	    params.put("bankName", bankName);
	    params.put("created", createBill);
	    params.put("unCreated", unCreateBill);
		String signStr = ServletSignUtil.buildSign(params, ServletSignUtil.PSD_KEY);
		
    	StringBuffer strBuffer = new StringBuffer();
    	strBuffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>");
		strBuffer.append("<mpm>");
		strBuffer.append("<service>REFUNDINFOAPPLY</service>");
		strBuffer.append("<request>");
		//客户号
		strBuffer.append("<customerId>" + ServletSignUtil.CUSTOMER_ID + "</customerId>");
		//外部交易号
		strBuffer.append("<outTradeNo>" + outTradeNo + "</outTradeNo>");
		//订单编号
		strBuffer.append("<orderCd>" + orderCd + "</orderCd>");
		//退款金额
		strBuffer.append("<amount>" + amountString + "</amount>");
		//币种
		strBuffer.append("<currencyType>" + ServletSignUtil.CURRENCY_RMB + "</currencyType>");
		//退款方式
		strBuffer.append("<refundModeCode>" + refundModeCode + "</refundModeCode>");
		//退款原因
		strBuffer.append("<refundReasonCode>" + refundReasonCode + "</refundReasonCode>");
		//操作员
		strBuffer.append("<operator>" + operator + "</operator>");
		//收款人名称
		strBuffer.append("<accountName>" + accountName + "</accountName>");
		//帐号
		strBuffer.append("<accountNo>"+ accountNo + "</accountNo>");
		//开户银行名称
		strBuffer.append("<bankName>" + bankName + "</bankName>");
		//已创行程单
		strBuffer.append("<created>" + createBill + "</created>");
		//未创行程单
		strBuffer.append("<unCreated>" + unCreateBill + "</unCreated>");
		//备注
		strBuffer.append("<remark>" + remark + "</remark>");
		strBuffer.append("<signType>MD5</signType>");
		strBuffer.append("<sign>" + signStr + "</sign>");
		strBuffer.append("</request>");
		strBuffer.append("</mpm>");
		return strBuffer.toString();
		
    }
    
    //个人支付成功之后调用号百支付接口，通知其出票
    //公司月结是审批通过之后调用号百支付接口，通知其出票
    @Override
    public ResponseMessage createHBPay(PayVo payVo) throws Exception{
    	ResponseMessage responseMessage = new ResponseMessage();
    	if(null != payVo){
    		String businessId = payVo.getOrderId() != null ? payVo.getOrderId().toString() : "";
    		String requestNo = "";
    		List<TicketInfoVo> ticketList = trainTicketMapper.findTicketByHbOrderId(payVo.getHbOrderCn(),"0");
    		if(null != ticketList && ticketList.size() > 0){
    			TicketInfoVo ticketInfoVo = ticketList.get(0);
    			requestNo = ticketInfoVo.getRequestNo();
    			OrderItem orderItem = trainOrderItemMapper.find(ticketInfoVo.getOrderItemId());
    			if(null != orderItem){
    				businessId = orderItem.getOrderId() != null ? String.valueOf(orderItem.getOrderId()) : "";
    			}
    			//号百
        		String url =  messageSource.getMessage("HB_TRAIN_PAY_URL", new Object[]{}, LocaleContextHolder.getLocale());  
        		JSONObject params = new JSONObject();
        		params.put("businessId", businessId);
        		params.put("requestNo", requestNo);
        		params.put("ywType", payVo.getType());//业务类型：2 - 火车票订单；5 - 火车票改签
        		params.put("cldh", payVo.getHbOrderCn());
        		params.put("transAmt", payVo.getAmount());
        		params.put("by1", payVo.getBy1());
        		JSONArray array = new JSONArray();
        		JSONObject obj = new JSONObject();
        		obj.put("cldh", payVo.getHbOrderCn());
        		obj.put("transAmt", payVo.getAmount());
        		array.add(obj);
        		params.put("dds", array);
        		
        		long startTime = System.currentTimeMillis();
    			String body = HttpClientUtil.httpPostBody(url,params.toString());
    			long endTime = System.currentTimeMillis();
    			logger.info("接口调用时间：" + (endTime - startTime) + "ms，url=" + url + ",params=" + params);
    			String status = "7";//出票失败
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
    						status = "6";//出票中
    						JSONObject result = jsonBody.getJSONObject("result");
    						if(null != result){
    							String totalFee = result.getString("total_fee");
    							Map<String,Object> data = new HashMap<String,Object>();
    							data.put("totalFee", totalFee);
    							responseMessage.setData(data);
    							responseMessage.setCode(ResponseMessage.SUCCESS);
    						}
    					}else{
    						responseMessage.setCode(ResponseMessage.ERROR);
    						responseMessage.setMsg(message + ",调用url=" + url + ",参数params=" + params + "返回值异常！");
    						logger.info("调用url=" + url + ",参数params=" + params + "返回值异常！");
    					}
    				}
    			}
    			//更新票状态为出票中
				Date date = new Date();
				for(TicketInfoVo vo : ticketList){
					Ticket ticket = new Ticket();
					ticket.setId(vo.getId());
					ticket.setStatus(status);//出票中
					ticket.setUpdateTime(date);
					trainTicketMapper.updateTicket(ticket);
				}
    		}else{
    			responseMessage.setCode(ResponseMessage.ERROR);
    			responseMessage.setMsg("ticketList值异常！");
    		}
    	}else{
    		responseMessage.setCode(ResponseMessage.ERROR);
			responseMessage.setMsg("payVo值异常！");
    	}
    	
		return responseMessage;
    }
    
    //公司月结
    @Override
    public ResponseMessage createCompanyPay(PayParamsVo params) throws Exception{
    	ResponseMessage responseMessage = new ResponseMessage();
		if(params != null){
			Long orderId = params.getOrderId();
			String orderCn = params.getOrderCn();
			List<Long> orderItems = params.getOrderItems();//目前只能传一个值
			List<Long> goodsIds = params.getGoodsIds();
			String goodsType = params.getGoodsType();
			BigDecimal price = params.getPrice();
			String payModel = params.getPayModel();
			String payInfoType = params.getPayInfoType();//支付类型：0付款，1退款
			long currentTime = System.currentTimeMillis();
			Date date = new Date(currentTime);
			Date endDate = new Date(currentTime + 30 * 60 * 1000);//半个小时
			logger.info("companyPay接口创建支付会话params=" +params.toString());
			if(orderId != null && StringUtils.isNotEmpty(orderCn) && null != goodsIds && goodsIds.size() > 0 && price != null && StringUtils.isNotEmpty(payInfoType) && StringUtils.isNotEmpty(payModel)){
				//支付会话
				try {
					List<TrainPayInfo> payInfoList = trainPayInfoMapper.findPayInfoByItemIdCn(orderId,payInfoType,goodsIds,goodsType);
					if(null != payInfoList && payInfoList.size() > 0){
						TrainPayInfo payInfo = payInfoList.get(0);
						TrainPay pay = trainPayMapper.findPayByInfoId(payInfo.getId());
						if(null != pay){
							//先检查是否是有支付失败的详情
							List<TrainPayDetail> trainPayDetailList = trainPayDetailMapper.findByPayId(pay.getId(), "2");//2=支付失败
							if(null == trainPayDetailList || trainPayDetailList.size() < 1){
								//不存在支付失败的则更新支付价格
								TrainPay trainPay = new TrainPay();
								trainPay.setId(pay.getId());
								trainPay.setAmount(price);
								trainPay.setModifyTime(date);
								trainPayMapper.update(trainPay);
							}
							
							//支付详情
							TrainPayDetail detail = new TrainPayDetail();
							detail.setTrainPayId(pay.getId());
							detail.setPayModel(payModel);//支付方式
							if(StringUtils.isNotEmpty(params.getVoucherCd())){
								detail.setVoucherCd(params.getVoucherCd());//代金劵编号
							}
							detail.setIntegralNum(params.getIntegralNum() != null ? params.getIntegralNum() : 0L);//积分兑换数
							detail.setPayAmount(price);
							detail.setStatus("4");//4：已支付
							detail.setCreateTime(date);
							trainPayDetailMapper.save(detail);
						}
					}else{
						TrainPayInfo payInfo = new TrainPayInfo();
						payInfo.setOrderId(orderId);
						payInfo.setOrderCd(orderCn);
						payInfo.setStatus("4");//4：已支付
						payInfo.setType(payInfoType);//支付类型：0付款，1退款
						payInfo.setDateTime(date);
						payInfo.setEndTime(endDate);
						trainPayInfoMapper.save(payInfo);
						//支付表
						TrainPay pay = new TrainPay();
						pay.setPayInfoId(payInfo.getId());
						pay.setOrderCd(orderCn);
						pay.setAmount(price);
						pay.setStatus("4");//4：已支付
						pay.setCreateTime(date);
						this.save(pay);
						
						//支付订购项
						for(Long goodsId : goodsIds){
							TrainPayOrderItem orderItem = new TrainPayOrderItem();
							orderItem.setPayInfoId(payInfo.getId());
							orderItem.setGoodsId(goodsId);
							orderItem.setGoodsType(goodsType);
							orderItem.setCreateTime(date);
							trainPayOrderItemMapper.save(orderItem);
						}
						
						//支付详情
						TrainPayDetail detail = new TrainPayDetail();
						detail.setTrainPayId(pay.getId());
						if(StringUtils.isNotEmpty(payModel)){//支付方式
							detail.setPayModel(payModel);
						}
						if(StringUtils.isNotEmpty(params.getVoucherCd())){
							detail.setVoucherCd(params.getVoucherCd());//代金劵编号
						}
						if(params.getIntegralNum() != null){
							detail.setIntegralNum(params.getIntegralNum());//积分兑换数
						}else{
							detail.setIntegralNum(0L);//默认为0
						}
						detail.setPayAmount(price);
						detail.setStatus("4");//4：已支付
						detail.setCreateTime(date);
						trainPayDetailMapper.save(detail);
					}
					responseMessage.setCode(ResponseMessage.SUCCESS);
					responseMessage.setMsg("成功！");
				} catch (Exception e) {
					e.printStackTrace();
					responseMessage.setCode(ResponseMessage.ERROR);
					responseMessage.setMsg("companyPay接口参数错误！");
				}
			}else{
				responseMessage.setCode(ResponseMessage.ERROR);
				responseMessage.setMsg("params"+params +"参数错误！");
			}
		}else{
			responseMessage.setCode(ResponseMessage.ERROR);
			responseMessage.setMsg("params"+params +"参数错误！");
		}
    	return responseMessage;
    }
    
	public static void main(String args[]){
		long currentTime = System.currentTimeMillis() + 30 * 60 * 1000;
		Date date = new Date(currentTime);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String nowTime=""; 
		nowTime= df.format(date);
		System.out.println(nowTime);
		
		PayParamsVo vo = new PayParamsVo();
		vo.setOrderId(3L);
		vo.setOrderCn("3");
		List<Long> list = new ArrayList<Long>();
		list.add(3l);
		list.add(1l);
		vo.setOrderItems(list);
		vo.setPayModel("TMCCC");
		vo.setPayChannel("TMCCC");
		vo.setPayId(10L);
		TrainPayServiceImpl i = new TrainPayServiceImpl();
		try {
			i.createPayDetail(vo);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(GoodsType.CHARGE.toString().equals("CHARGE")){
			System.out.println("123");
		}
		String s = "";
		try {
			s = i.createMPmOrderOnline("200702080306531T","1000200702080306531T21",new BigDecimal("0.1"),"RMBESTPAY","1003015728","11");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(s);
	}

	@Override
	public TrainPay findTrainPayByInfoId(Long payInfoId) throws Exception {
		TrainPay trainPay = trainPayMapper.findPayByInfoId(payInfoId);
		return trainPay;
	}

}
