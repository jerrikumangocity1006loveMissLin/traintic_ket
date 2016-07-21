package com.mangocity.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mangocity.common.HttpClientUtil;
import com.mangocity.mapper.PassengerMapper;
import com.mangocity.mapper.TrainTicketMapper;
import com.mangocity.model.Passenger;
import com.mangocity.model.Ticket;
import com.mangocity.response.ResponseMessage;
import com.mangocity.util.JsonUtil;

/**
 * @author lizhi
 *
 * @date 2016年6月2日
 */
@Service
public class ScheduledService {
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private TrainTicketMapper trainTicketMapper;
	@Autowired
	private PassengerMapper passengerMapper;
	@Resource
	private MessageSource messageSource;

	/**
	 * 定时处理未及时通知的票
	 * 
	 * @Title: jobTest void
	 */
	public void jobTask() {
		logger.info("===定时任务开始===");
		try {
			Date date = new Date();
			String url = messageSource.getMessage("HB_SEARCH_ORDER_DETAIL", new Object[] {},
					LocaleContextHolder.getLocale());
			List<Ticket> tickets = trainTicketMapper.findTicketByStatus(date);
			for (Ticket ticket : tickets) {
				String orderNo = ticket.getHbOrderId();
				if(StringUtils.isNotEmpty(orderNo)&&!StringUtils.isEquals("-1", orderNo)){
				Map<String,String> map = new HashMap<String,String>();
				long time = System.currentTimeMillis();
				map.put("businessId", time+"W");
				map.put("requestNo", time+"W");
				map.put("orderNo", orderNo);
				JSONObject json = JsonUtil.objectToJosnObject(map);
				logger.info("开始请求---请求号百参数---params:" + json.toString());
				long startTime = System.currentTimeMillis();
				String response = HttpClientUtil.httpPostBody(url, json.toString());
				long endTime = System.currentTimeMillis();
				logger.info("请求结束---耗时：" + (endTime - startTime) + "---返回结果：" + response);
				handleResponse(response, orderNo);
				}
			}
		} catch (Exception e) {
			logger.error("jobTask定时任务异常:", e);
		}
		logger.info("===定时任务结束===");
	}

	private ResponseMessage handleResponse(String response, String orderNo) {
		ResponseMessage responseMessage = new ResponseMessage();
		if (StringUtils.isNotEmpty(response) && response.startsWith("{")) {
			JSONObject jsonObj = JsonUtil.stringToJsonObject(response);
			String code = jsonObj.getString("code");
			if (StringUtils.isEquals("0", code)) {
				JSONObject result = jsonObj.getJSONObject("result");
				if (result.containsKey("tox")) {
					JSONArray jArray = result.getJSONArray("tox");
					for (Object obj : jArray) {
						JSONObject json = JsonUtil.stringToJsonObject(obj.toString());
						String zjhm = json.getString("zjh");
						Passenger passenger = passengerMapper.findPassengerByzjhm(zjhm);
						Long passengerId = null;
						if (passenger != null) {
							passengerId = passenger.getId();
						}else{
							passengerId=-1L;
						}
						Ticket t = new Ticket();
						t.setTicketNo(json.getString("tid"));
						t.setCxin(json.getString("cxi"));
						t.setSeatType(json.getString("zwl"));
						t.setStatus("Success");
						t.setPassengerId(passengerId);
						t.setHbOrderId(orderNo);
						t.setUpdateTime(new Date());
						trainTicketMapper.update(t);
					}
				}
				responseMessage.setCode(ResponseMessage.SUCCESS);
			} else {
				responseMessage.setCode(ResponseMessage.ERROR);
				responseMessage.setMsg(jsonObj.getString("message"));
			}
		} else {
			responseMessage.setCode(ResponseMessage.ERROR);
			responseMessage.setMsg("数据格式不正确");
		}
		return responseMessage;
	}
}
