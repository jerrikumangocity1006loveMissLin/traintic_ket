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
import com.mangocity.model.Ticket;
import com.mangocity.model.TrainPay;
import com.mangocity.model.TrainPayDetail;
import com.mangocity.model.TrainPayInfo;
import com.mangocity.vo.TicketInfoVo;

/**
 * 退款回调
 */
@WebServlet(name="BackfillRefundStatusServlet",urlPatterns={"/servlet/backfillRefundStatusServlet"})
public class BackfillRefundStatusServlet extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5076176055124087317L;
	
	private Logger logger = Logger.getLogger(BackfillRefundStatusServlet.class);
	
	@Autowired
	private ITrainPayDetailService trainPayDetailService;
	
	@Autowired
	private ITrainPayService trainPayService;
	
	@Autowired
	private ITrainPayInfoService trainPayInfoService;
	
	@Autowired
	private ITrainTicketService trainTicketService;

	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("backfillRefundStatusServlet异步回调开始");
		String xmlStr = req.getParameter("xmlStr");
		if(StringUtils.isNotBlank(xmlStr)){
			InputStream in = null;
			InputStreamReader utfreader = null;
			try {
				xmlStr = URLDecoder.decode(xmlStr, "UTF-8");
				logger.info(">>>>>xmlStr=" + xmlStr);
				SAXReader reader = new SAXReader();
				in = new ByteArrayInputStream(xmlStr.getBytes());
				utfreader = new InputStreamReader(in, "UTF-8");
				Document document = reader.read(utfreader);
				Element root = document.getRootElement();

				String title = root.elementText("service");
				if (!"REFUNDSTATUS".equals(title)) {
					logger.info("xml格式错误,service节点校验失败,xmlStr="+ xmlStr);
				}else{
					Element rElt = root.element("request");
					String outTradeNo = rElt.elementText("outTradeNo"); // 外部交易号
					String orderCd = rElt.elementText("orderCd"); // 订单编号 
					String amount = rElt.elementText("amount"); // 退款金额
					String origin = rElt.elementText("origin"); // 信息来源：结算或支付
					String refundStatus = rElt.elementText("refundStatus"); // 退款状态																	
					String reasonCode = rElt.elementText("reasonCode"); // 驳回原因代码(目前留空)
					String reason = rElt.elementText("reason"); // 驳回原因
					String remark = rElt.elementText("remark"); // 备注
					String operator = rElt.elementText("operator"); // 操作员
					String sign = rElt.elementText("sign"); // 密文
					
					Map<String,String> map = new HashMap<String,String>();
					map.put("outTradeNo", outTradeNo);
					map.put("orderCd", orderCd);
					map.put("amount", amount);
					map.put("origin", origin);
					map.put("refundStatus", refundStatus);
					//map.put("reasonCode", reasonCode);
					//map.put("reason", reason);
					//map.put("remark", remark);
					map.put("operator", operator);
					
					// 验证密文
					if(!ServletSignUtil.verifySign(sign, map, ServletSignUtil.PSD_KEY)){
						logger.info(">>>>>>outTradeNo=" + outTradeNo + "验证密文失败");
					}else{
		            	try {
							List<TrainPayDetail> payDetailList = trainPayDetailService.findByOutTradeNo(outTradeNo);
							if(null != payDetailList && payDetailList.size() > 0){
								TrainPayDetail trainPayDetail = payDetailList.get(0);
								Long payId = trainPayDetail.getTrainPayId();
								TrainPay trainPay = trainPayService.find(payId);
								Long integralNumAll = 0L;
								BigDecimal payAmountAll = new BigDecimal("0");
								if(null != trainPay){
									//查询该支付订单的所有详情--已支付
									List<TrainPayDetail> list = trainPayDetailService.findByPayId(payId,"4");
									for(TrainPayDetail d : list){
										integralNumAll += d.getIntegralNum();
										payAmountAll = payAmountAll.add(d.getPayAmount());
									}
								}
								//积分数,100积分=1元
								BigDecimal integralPrice = new BigDecimal(integralNumAll);
								integralPrice = integralPrice.divide(new BigDecimal("100"));
								if(StringUtils.isNotEmpty(amount)){
									BigDecimal payAmount = null;
									try {
										payAmount = new BigDecimal(amount);
									} catch (Exception e) {
										e.printStackTrace();
										payAmount = new BigDecimal("0");
									}
									BigDecimal price = integralPrice.add(payAmountAll).add(payAmount);
									if(null != trainPay){
										Date date = new Date();
										if((price.compareTo(trainPay.getAmount()) != -1)){//应该支付价格 <= （实际支付价格+积分支付价格）
											//更新支付明细
											for(TrainPayDetail payDetail : payDetailList){
												TrainPayDetail d = new TrainPayDetail();
												d.setId(payDetail.getId());
												d.setStatus("4");//退款成功
												d.setModifyTime(date);
												d.setPayAmount(payAmount);
												trainPayDetailService.update(d);
											}
											//更新支付
											if(payId != null){
												TrainPay p = new TrainPay();
												p.setId(payId);
												p.setStatus("4");//退款成功
												p.setModifyTime(date);
												trainPayService.update(p);
											}
											if(null != trainPay){
												Long payInfoId = trainPay.getPayInfoId();
												if(payInfoId != null){
													//更新支付会话
													TrainPayInfo payInfo = new TrainPayInfo();
													payInfo.setId(payInfoId);
													payInfo.setStatus("4");//退款成功
													payInfo.setModifyTime(date);
													trainPayInfoService.update(payInfo);
												}
											}
											//火车票类型： 0-订票 1-改签 2-退票
											List<TicketInfoVo> ticketList = trainTicketService.findTicketByHbOrderId(trainPayDetail.getHbOrderId(), "2");
											for(TicketInfoVo ticketInfoVo : ticketList){
												Ticket ticket = new Ticket();
												ticket.setId(ticketInfoVo.getId());
												ticket.setStatus("8");//已退款
												ticket.setUpdateTime(new Date());
												trainTicketService.updateTicket(ticket);
											}
											
										}else{
											//更新支付
											if(payId != null){
												TrainPay p = new TrainPay();
												p.setId(payId);
												p.setStatus("3");//部分退款成功
												p.setModifyTime(date);
												trainPayService.update(p);
											}
											if(null != trainPay){
												Long payInfoId = trainPay.getPayInfoId();
												if(payInfoId != null){
													//更新支付会话
													TrainPayInfo payInfo = new TrainPayInfo();
													payInfo.setId(payInfoId);
													payInfo.setStatus("3");//部分退款成功
													payInfo.setModifyTime(date);
													trainPayInfoService.update(payInfo);
												}
											}
										}
									}
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
		            }
				}
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}else{
			logger.info("backfillRefundStatusServlet异步回调参数xmlStr为空");
		}
		logger.info("backfillRefundStatusServlet异步回调结束");
		
	}
}
