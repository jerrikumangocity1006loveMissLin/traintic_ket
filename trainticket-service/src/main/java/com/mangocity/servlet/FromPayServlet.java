package com.mangocity.servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;

import com.mangocity.api.ITrainPayDetailService;
import com.mangocity.api.ITrainPayInfoService;
import com.mangocity.api.ITrainPayService;
import com.mangocity.api.ITrainTicketService;
import com.mangocity.common.ServletSignUtil;
import com.mangocity.model.TrainPay;
import com.mangocity.model.TrainPayDetail;
import com.mangocity.model.TrainPayInfo;
import com.mangocity.vo.PayVo;
import com.mangocity.vo.TicketInfoVo;

/**
 * 信用卡离线支付或现金支付异步返回
 * */
@WebServlet(name="FromPayServlet",urlPatterns={"/servlet/fromPayServlet"})
public class FromPayServlet extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5620477632448569986L;
	
	Logger logger = Logger.getLogger(FromPayServlet.class);
	
	@Autowired
	private ITrainPayDetailService trainPayDetailService;
	
	@Autowired
	private ITrainPayService trainPayService;
	
	@Autowired
	private ITrainPayInfoService trainPayInfoService;
	
	@Autowired
	private ITrainTicketService trainTicketService;
	
	private PayVo payVo;
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("fromPayServlet异步回调开始!");
		String xmlStr=req.getParameter("xmlStr");
		if(StringUtils.isNotBlank(xmlStr)){
			InputStream in = null;
	        InputStreamReader utfreader = null;
	        try {
				xmlStr=URLDecoder.decode(xmlStr, "UTF-8");
				logger.info("fromPayServlet>>>>>xmlStr="+xmlStr);
				//对于返回的字符串，解析为xml格式
				SAXReader reader = new SAXReader();
				in = new ByteArrayInputStream(xmlStr.getBytes());
				utfreader = new InputStreamReader(in, "UTF-8");        
				Document document = reader.read(utfreader);
				Element root = document.getRootElement();    
				Element rspElt=root.element("result").element("response");  
				String success=root.element("result").elementText("success");//生成预授权工单成功失败
				String outTradeNo= rspElt.elementText("outTradeNo");//外部交易号
				String payResult = rspElt.elementText("payResult");//支付结果
				String payMessage = rspElt.elementText("payMessage");//支付信息
				String payTime = rspElt.elementText("payTime");//支付时间                
				String notifyType=rspElt.elementText("notifyType"); //通知模式   1：同步返回，2：异步通知                                    
				String payAmount = rspElt.elementText("payAmount");//支付金额
				String sign=rspElt.elementText("sign");//密文
				String operator=rspElt.elementText("operator");//操作人
				
				Map map = new HashMap();
				map.put("outTradeNo", outTradeNo);
				map.put("payResult", payResult);
				map.put("payMessage", payMessage);
				map.put("payTime", payTime);
				map.put("notifyType", notifyType);
				map.put("payAmount", payAmount);    
				map.put("operator", operator);
				//验证
				if(!ServletSignUtil.verifySign(sign, map, ServletSignUtil.PSD_KEY)){
	                logger.info(">>>>>>outTradeNo="+outTradeNo+"验证密文失败");
	            }else{
	            	List<TrainPayDetail> payDetailList = trainPayDetailService.findByOutTradeNo(outTradeNo);
	            	Date date = new Date();
	            	if("SUCCESS".equalsIgnoreCase(payResult)){//支付成功
						if(null != payDetailList && payDetailList.size() > 0){
							TrainPayDetail trainPayDetail = payDetailList.get(0);
							Long payId = trainPayDetail.getTrainPayId();
							TrainPay trainPay = trainPayService.find(payId);
							Long integralNumAll = 0L;
							BigDecimal payAmountAll = new BigDecimal("0");
							if(null != trainPay){
								//查询该支付订单的所有详情,已支付
								List<TrainPayDetail> list = trainPayDetailService.findByPayId(payId,"4");
								for(TrainPayDetail d : list){
									integralNumAll += d.getIntegralNum();
									payAmountAll = payAmountAll.add(d.getPayAmount());
								}
							}
							//积分数,100积分=1元
							BigDecimal integralPrice = new BigDecimal(integralNumAll);
							integralPrice = integralPrice.divide(new BigDecimal("100"));
							if(StringUtils.isNotEmpty(payAmount)){
								BigDecimal amount = null;
								try {
									amount = new BigDecimal(payAmount);
								} catch (Exception e) {
									e.printStackTrace();
									amount = new BigDecimal("0");
								}
								if(null != payDetailList && payDetailList.size() > 0){
			            			//更新支付明细
									for(TrainPayDetail payDetail : payDetailList){
										TrainPayDetail d = new TrainPayDetail();
										d.setId(payDetail.getId());
										d.setStatus("4");//已支付
										d.setModifyTime(date);
										//d.setPayAmount(amount);
										trainPayDetailService.update(d);
									}
			            		}
								BigDecimal price = integralPrice.add(payAmountAll).add(amount);
								if(null != trainPay){
									if((price.compareTo(trainPay.getAmount()) != -1)){//应该支付价格 <= （实际支付价格+积分支付价格）
										//更新支付
										if(payId != null){
											TrainPay p = new TrainPay();
											p.setId(payId);
											p.setStatus("4");//支付成功
											p.setModifyTime(date);
											trainPayService.update(p);
										}
										if(null != trainPay){
											Long payInfoId = trainPay.getPayInfoId();
											if(payInfoId != null){
												//更新支付会话
												TrainPayInfo payInfo = new TrainPayInfo();
												payInfo.setId(payInfoId);
												payInfo.setStatus("4");//支付成功
												payInfo.setModifyTime(date);
												trainPayInfoService.update(payInfo);
											}
										}
										//异步通知号百支付完成
										Long payInfoId =  trainPay.getPayInfoId();
										//火车票类型： 0-订票 1-改签 2-退票
										List<TicketInfoVo> ticketList = trainTicketService.findTicketByPayInfoId(payInfoId, "0");
										if(null != ticketList && ticketList.size() > 0){
											logger.info("异步通知号百支付完成ticketList="+ ticketList.toString());
											Map<String,String> mapOrderId = new HashMap<String,String>();//用于判断号百订单号是否重复
											for(TicketInfoVo ticketInfoVo : ticketList){
												String hbOrderId = ticketInfoVo.getHbOrderId();
												String ticketNo = ticketInfoVo.getTicketNo();
												if(StringUtils.isNotBlank(hbOrderId) && StringUtils.isNotBlank(ticketNo)){
													if(StringUtils.isBlank(mapOrderId.get(hbOrderId))){
														payVo = new PayVo();
														payVo.setType("2");//业务类型：2 - 火车票订单；5 - 火车票改签
														payVo.setHbOrderCn(hbOrderId);
														payVo.setAmount(ticketInfoVo.getHbPrice());
														new Thread(
															new Runnable(){
																@Override
																public void run() {
																	try {
																		logger.info("通知号百出票线程开始payVo="+payVo.toString());
																		trainPayService.createHBPay(payVo);
																		logger.info("通知号百出票线程结束payVo="+payVo.toString());
																	} catch (Exception e) {
																		e.printStackTrace();
																	}
																}
															}
														).start();
													}
													mapOrderId.put(hbOrderId, hbOrderId);
												}else{
													logger.info("无号百订单号ticketInfoVo="+ ticketInfoVo.toString());
												}
											}
										}
									}else{
										//更新支付
										if(payId != null){
											TrainPay p = new TrainPay();
											p.setId(payId);
											p.setStatus("3");//部分支付
											p.setModifyTime(date);
											trainPayService.update(p);
										}
										if(null != trainPay){
											Long payInfoId = trainPay.getPayInfoId();
											if(payInfoId != null){
												//更新支付会话
												TrainPayInfo payInfo = new TrainPayInfo();
												payInfo.setId(payInfoId);
												payInfo.setStatus("3");//部分支付
												payInfo.setModifyTime(date);
												trainPayInfoService.update(payInfo);
											}
										}
									}
								}else{
									logger.info("outTradeNo=" +outTradeNo+ ",fromPayServlet异步,trainPay为空!");
								}
							}else{
								logger.info("outTradeNo=" +outTradeNo+ ",fromPayServlet异步,参数payAmount为空!");
							}
						}
	            	}else{//支付平台支付失败
	            		if(null != payDetailList && payDetailList.size() > 0){
	            			//更新支付明细
							for(TrainPayDetail payDetail : payDetailList){
								TrainPayDetail d = new TrainPayDetail();
								d.setId(payDetail.getId());
								d.setStatus("2");//支付失败
								d.setModifyTime(date);
								//d.setPayAmount(amount);
								trainPayDetailService.update(d);
							}
	            		}
	            	}
	            }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			logger.info("fromPayServlet异步回调参数xmlStr为空!");
		}
		logger.info("fromPayServlet异步回调结束!");
	}

}
