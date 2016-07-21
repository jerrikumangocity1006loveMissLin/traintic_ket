/**
 * 
 */
package com.mangtocity.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mangocity.api.ITrainOrderService;
import com.mangocity.model.Charge;
import com.mangocity.model.Contact;
import com.mangocity.model.Delivery;
import com.mangocity.model.Insurance;
import com.mangocity.model.Order;
import com.mangocity.model.Ticket;
import com.mangocity.model.TrainInvoice;
import com.mangocity.util.JsonUtil;
import com.mangocity.vo.OrderItemVo;
import com.mangocity.vo.OrderVo;
import com.mangocity.vo.PassengerVo;
import com.mangocity.vo.TicketVo;
import com.mangtocity.base.JunitTestBase;

/**
 * @author lizhi
 *
 * @date 2016年5月31日
 */
public class TrainOrderServiceTest extends JunitTestBase {

	@Autowired
	public ITrainOrderService orderService; 
	
	@Test
	public void saveOrderTest(){
		OrderVo orderVo = new OrderVo();	
		Order order = new Order();
		List<OrderItemVo> orderItemsVos = new ArrayList<OrderItemVo>();
		OrderItemVo orderItemVo1 = new OrderItemVo();
	    
	    order.setBookMember("lizhi1230625");
		order.setCompanyCode("mangocity0625");
		order.setCostCenter("test0625");
		order.setMemberCn("lizhi0625");
		order.setOrderCn("1111120160625");
		order.setOrderFrom("mangocc(fax)0625");
        order.setStatus("0");
		order.setPaymentStatus("未支付");
		order.setTravelType("因公出差");
		order.setFrontRemark("aaaaaaa");
		order.setOrderAudit("bbbbbbbb");
		order.setOrderChannel("mangocity-cc");
		order.setOrderMethod("mangocity-cc");
		order.setOutMethod("auto-take");
		order.setProjectCode("11110625");
		
	    List<TicketVo> ticketsv1 = new ArrayList<TicketVo>();
	    //List<TicketVo> ticketsv2 = new ArrayList<TicketVo>();
	    
		List<TrainInvoice> trainInvoices = new ArrayList<TrainInvoice>();
		TrainInvoice invoice = new TrainInvoice();
		invoice.setAddress("芒果网大厦0625");
		invoice.setAmount(new BigDecimal(12306));
		invoice.setDrawer("ABCDEFG0625");
		invoice.setInvoiceHead("szx0625");
		invoice.setInvoiceUnit("mango0625");
		trainInvoices.add(invoice);
		orderItemVo1.setTrainInvoices(trainInvoices);
	      
		List<Delivery> deliveries = new ArrayList<Delivery>();
		Delivery delivery = new Delivery();
		delivery.setId(3L);
		delivery.setAddress("芒果网大厦0625");
		delivery.setDeliveryCn("顺丰速递0625");
		delivery.setType("B");
		deliveries.add(delivery);
		orderItemVo1.setDeliverys(deliveries);
		
		List<Charge> charges = new ArrayList<Charge>();
		Charge charge = new Charge();
		charge.setId(5L);
		charges.add(charge);
		orderItemVo1.setCharges(charges);
	    //orderItemVo1.setChargeId("2");
	    //orderItemVo1.setDeliveryId("4");
	    
	    TicketVo ticketVo1 = new TicketVo();
	    ticketVo1.setLxr("李智");
	    ticketVo1.setLxrsj("13823361603");
	    ticketVo1.setQqly("20022090625");
	    ticketVo1.setCllx("2");
	    ticketVo1.setCcrq("2016-07-26");
	    ticketVo1.setDdrq("2016-07-26");
	    
	    Ticket ticket1 = new Ticket();
	    //ticket1.setCreateTime(new Date());
	    ticket1.setDestStation("IZQ");
	    ticket1.setDestStationName("广州南");
	    ticket1.setEndTime(new Date());
	    ticket1.setFee(new BigDecimal(99.5));
	    ticket1.setOrigStation("IOQ");
	    ticket1.setOrigStationName("深圳北");
	    ticket1.setPassengerId(10L);
	    ticket1.setRouteType("multip");
	    ticket1.setSalePrice(new BigDecimal(99.5));
	    ticket1.setSeatType("一等");
	    ticket1.setStartTime(new Date());
	    ticket1.setStatus("0");
	    ticket1.setTicketParentId(0L);
	    ticket1.setTrainCn("G1002");
	    ticket1.setTrainType("1"); 
	    ticket1.setCcsj("07:00");
	    ticket1.setDdsj("07:29");
	    ticketVo1.setTicket(ticket1);
	    
	    Insurance insurance = new Insurance();
	    insurance.setDescp("papi0625");
	    insurance.setNum(1);
	    insurance.setPrice(new BigDecimal(10));
	    insurance.setType("A");
	    List<Insurance> insurances = new ArrayList<Insurance>();
	    insurances.add(insurance);
	    ticketVo1.setInsurances(insurances);
	    
	    PassengerVo passengerVo = new PassengerVo();
	    passengerVo.setCxin("");
	    passengerVo.setFee("0");
	    passengerVo.setPassengerId(1L);
	    passengerVo.setPassengerName("李智");
	    passengerVo.setPassportNo("440224198907030735");
	    passengerVo.setPassportTypeId("1");
	    passengerVo.setPiaoType("1");
	    passengerVo.setPrice("99.5");
	    passengerVo.setZwCode("1");
	    passengerVo.setPhone("13823361603");;
	    //passengerVo.setQqly("2002209");
	    
	    PassengerVo passengerVo1 = new PassengerVo();
	    passengerVo1.setCxin("");
	    passengerVo1.setFee("0");
	    passengerVo1.setPassengerId(2L);
	    passengerVo1.setPassengerName("刘金娥");
	    passengerVo1.setPassportNo("152127196211307529");
	    passengerVo1.setPassportTypeId("1");
	    passengerVo1.setPiaoType("1");
	    passengerVo1.setPrice("99.5");
	    passengerVo1.setZwCode("1");
	    passengerVo1.setPhone("13999999999");
	    
	    Contact contact = new Contact();
	    contact.setMail("zhi.li@mangocity.com");
	    contact.setName("lizhi0625");
	    contact.setTelephone("12345678900");
	    
	    List<PassengerVo> passengerVos = new ArrayList<PassengerVo>();
	    passengerVos.add(passengerVo);
	    passengerVos.add(passengerVo1);
	    
	    ticketVo1.setPassengerVos(passengerVos);
	    //List<TicketVo> list = new ArrayList<>();
	    ticketsv1.add(ticketVo1);
	    //orderItemVo1.setTrainTicketVos(list);
	    
	    ticketsv1.add(ticketVo1);
	    orderItemVo1.setTrainTicketVos(ticketsv1);
	    orderItemsVos.add(orderItemVo1);
		orderVo.setOrder(order);
		orderVo.setOrderItemsVos(orderItemsVos);
		orderVo.setContact(contact);
		String jsonString = JsonUtil.Object2JsonString(orderVo);
		orderService.createTrainOrder(jsonString);	
		
	}
	
/*	@Test
	public void addDeliveryItemTest(){
		Delivery delivery = new Delivery();
		delivery.setAddress("后海大道123号芒果网大厦");
		delivery.setCity("深圳");
		delivery.setCreateTime(new Date());
		delivery.setFee(new BigDecimal(0));
        delivery.setStatus("A");
        delivery.setType("BB");
		orderService.addDeliveryItem(delivery, 133, "TR16061102844", new Date());
	}*/
	
	/*@Test
	public void bookTicketNotifyTest(){
		PropertyConfigurator.configure(TrainOrderServiceTest.class.getClassLoader().getResource("log4j.properties"));
		
		BookTicketNotifyVo btntv = new BookTicketNotifyVo();
		List<TrainOrderNotifyMXVo> trainOrderMx = new ArrayList<>();
		TrainOrderNotifyMXVo trainOrderNotifyMXVo1 = new TrainOrderNotifyMXVo();
		TrainOrderNotifyMXVo trainOrderNotifyMXVo2 = new TrainOrderNotifyMXVo();
		btntv.setCch("G6224");
		btntv.setCps_ddbh("1408051200437123");
		btntv.setDdbh("TR16060302716");
		btntv.setDdrq("2016-07-3");
		btntv.setDdsj("09:02");
		btntv.setDdz("IZQ");
		btntv.setDdzmc("广州南");
		btntv.setDdzt("预订成功");
		btntv.setJy_sxf("4.5");
		btntv.setLx("lizhi");
		btntv.setLxr_mobile("13823361603");
		btntv.setOrderNumber("2");
		btntv.setPj("99.5");
		btntv.setSfrq("2016-07-3");
		btntv.setSfsj("08:26");
		btntv.setSfz("IOQ");
		btntv.setSfzmc("深圳北");
		btntv.setTransactionid("6F4K789H2C00");
		btntv.setZf_sxf("0.0");
        btntv.setZs("2");
        
        trainOrderNotifyMXVo1.setCxin("01车厢,03F号");
        trainOrderNotifyMXVo1.setCzr("李智");
        trainOrderNotifyMXVo1.setCzrlx("1");
        trainOrderNotifyMXVo1.setTid("E225759905101003F");
        trainOrderNotifyMXVo1.setYsje("99.5");
        trainOrderNotifyMXVo1.setZjhm("440224198907030735");
        trainOrderNotifyMXVo1.setZjlx("1");
        trainOrderNotifyMXVo1.setZwlx("M");
        
        trainOrderNotifyMXVo1.setCxin("01车厢,04F号");
        trainOrderNotifyMXVo1.setCzr("刘金娥");
        trainOrderNotifyMXVo1.setCzrlx("1");
        trainOrderNotifyMXVo1.setTid("E225759905101004F");
        trainOrderNotifyMXVo1.setYsje("99.5");
        trainOrderNotifyMXVo1.setZjhm("152127196211307529");
        trainOrderNotifyMXVo1.setZjlx("1");
        trainOrderNotifyMXVo1.setZwlx("M");
        trainOrderMx.add(trainOrderNotifyMXVo1);
        btntv.setTrainOrderMx(trainOrderMx);
        
        orderService.bookTicketNotify(JsonUtil.Object2JsonString(btntv));
        
	}
*/}
