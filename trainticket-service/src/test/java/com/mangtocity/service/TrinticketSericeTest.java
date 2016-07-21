package com.mangtocity.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mangocity.api.IChargeService;
import com.mangocity.api.ITrainOrderService;
import com.mangocity.api.ITrainTicketService;
import com.mangocity.response.ResponseMessage;
import com.mangocity.util.DateTimeUtils;
import com.mangocity.util.JsonUtil;
import com.mangocity.vo.OrderBasisVo;
import com.mangocity.vo.OrderDetailBean;
import com.mangocity.vo.OrderVo;
import com.mangocity.vo.PageOrderParameter;
import com.mangocity.vo.PageQueryResult;
import com.mangocity.vo.TicketInfoVo;
import com.mangtocity.base.JunitTestBase;




/**
 * 生产者测试类
 * @author hongxiaodong
 *
 */
public class TrinticketSericeTest extends JunitTestBase{
	
	@Autowired
	public ITrainTicketService trainTicketService;
	
	@Autowired
	public IChargeService chargeService;
	
	@Autowired
	public ITrainOrderService orderService; 

	
/*	@Test
	public void test(){
		
		System.out.println("======="+trainTicketService.TestDemo("dddddd"));
		
		
	}*/
	
	@Test
	public void getOrderDetailTest() throws Exception{
		//chargeService.OrderDetailBean orderVo = orderService.queryOrderDeatilByOrderId(133l);
	    //List<TicketInfoVo> result = trainTicketService.findTicketByOrderIdOrCd(null, "100020");
/*		OrderDetailBean result = orderService.queryOrderDeatilByOrderCn("3");
		System.out.println("==============");
		System.out.println("==============");
		System.out.println("==============");
		System.out.println("==============");
		System.out.println("==============");
		System.out.println(JsonUtil.ObjectToJsonString(result));*/
	}
	
	
	@Test
	public void pageOrdeTest() throws Exception{/*
		PageOrderParameter pageOrderParameter = new PageOrderParameter();
		pageOrderParameter.setPageNo(0);
		pageOrderParameter.setPageSize(100);
		PageQueryResult<OrderBasisVo> orders = orderService.queryOrderBasisListByPage(pageOrderParameter);
		List<OrderBasisVo> reuslt = orders.getEntityList();
      for(OrderBasisVo vo:reuslt){
    	  System.out.println(JsonUtil.ObjectToJsonString(vo));
      }
	*/}
	
/*	@Test
	public void getOrderCdTest(){
		System.out.println(orderService.getOrderCd());
	}*/
}