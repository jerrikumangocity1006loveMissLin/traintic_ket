/**
 * 
 */
package com.mangtocity.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mangocity.api.ITrainTicketService;
import com.mangocity.response.ResponseMessage;
import com.mangocity.vo.HBTicketVo;
import com.mangtocity.base.JunitTestBase;

/**
 * @author lizhi
 *
 * @date 2016年5月31日
 */
public class TrainTickerServiceTest extends JunitTestBase {
	@Autowired
	private ITrainTicketService ticketService;
	
	/*@Test
	public void changeTrainTicket(){
		HBTicketVo hticket = new HBTicketVo();
		hticket.setBusinessId("201605031201501W");
		hticket.setCfDateNew("2016-06-27");
		hticket.setCfTimeNew("07:37");
		hticket.setLxr("李智");
		hticket.setMemberId("1");
		hticket.setDdTimeNew("08:06");
		hticket.setDdDateNew("2016-06-27");
		hticket.setLoginUserId("1");
		hticket.setLxrsj("13823361603");
		hticket.setNewCch("G1312");
		hticket.setOrderNo("TR16053102678");
		hticket.setQqly("2002209 ");
		hticket.setZwlx("0");
		hticket.setParentTicketId("96");
		hticket.setRequestNo("201605031201501W");
		//hticket
		ticketService.changeTrainTicket(hticket);		
	}*/
	
	@Test
	public void SearchOrderDetail(){
		ResponseMessage responseMessage = ticketService.searchTrainOrderDetail("TR16060602753");
		System.out.println(responseMessage.getCode());
	}

}
