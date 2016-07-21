package com.mangocity.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import com.mangocity.constant.SeatTypeMap;
import com.mangocity.constant.TravelTpyeMap;
import com.mangocity.enums.MessageType;
import com.mangocity.util.DateTimeUtils;
import com.mangocity.vo.IssuedTicketMessage;
import com.mangocity.vo.MailMessage;
import com.mangocity.vo.OrderTicketPaInfoVo;
import com.mangocity.vo.TicketInfoVo;

/**
 * 消息模板
 * 
 * @author hongxiaodong
 *
 */
public class MessageTemplate implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private MessageType type;

	private Integer templateNum;

	private String subject;

	private String messageContent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public Integer getTemplateNum() {
		return templateNum;
	}

	public void setTemplateNum(Integer templateNum) {
		this.templateNum = templateNum;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	/**
	 * 创建票号模板
	 * 
	 * @param ticket
	 * @return
	 */
	public String createIssuedSMSTemplate(TicketInfoVo ticket) {
		if (null == ticket) {
			return "";
		}

		String startDate = DateTimeUtils.getDate(ticket.getStartTime());
		String endDate = DateTimeUtils.getDate(ticket.getEndTime());

		IssuedTicketMessage itm = new IssuedTicketMessage();
		itm.setCxin(ticket.getCxin());
		itm.setDestStationName(ticket.getDestStationName());
		itm.setEndDate(endDate);
		itm.setEndTime(ticket.getDdsj());
		itm.setName(ticket.getPassenger().getName());
		itm.setOrigStationName(ticket.getOrigStationName());
		itm.setStartDate(startDate);
		itm.setStartTime(ticket.getCcsj());
		itm.setTicketNo(ticket.getOrderNumber());
		itm.setTrainCn(ticket.getTrainCn());
		itm.setTrainType(SeatTypeMap.ticketTypeProvider().get(ticket.getTrainType()));

		StringTemplate st = new StringTemplate(messageContent);
		st.setAttribute("itm", itm);

		return st.toString();
	}
	
	/**
	 * 创建票号模板
	 * 
	 * @param ticket
	 * @return
	 */
	public String createIssuedSMSTemplate(List<TicketInfoVo> TicketInfos) {
		if (null == TicketInfos) {
			return "";
		}

		IssuedTicketMessage itm = new IssuedTicketMessage();
		itm.setStartDate(DateTimeUtils.getDate(TicketInfos.get(0).getStartTime()));
		itm.setOrigStationName(TicketInfos.get(0).getOrigStationName());
		itm.setStartTime(TicketInfos.get(0).getCcsj());
		itm.setTrainCn(TicketInfos.get(0).getTrainCn());
		itm.setDestStationName(TicketInfos.get(0).getDestStationName());
		String endDate = DateTimeUtils.getDate(TicketInfos.get(0).getEndTime());
		if(!endDate.equals(itm.getStartDate())){
			itm.setEndDate(endDate);
		}
		itm.setEndTime(TicketInfos.get(0).getDdsj());		
		List<String> trainInfo = new ArrayList<String>();
		for (TicketInfoVo ticketInfoVo : TicketInfos) {
			StringBuilder tempteStr = new StringBuilder();
			tempteStr.append(ticketInfoVo.getPassenger().getName());
			tempteStr.append("("+SeatTypeMap.ticketTypeProvider().get(ticketInfoVo.getTrainType())+"票)");
			tempteStr.append(ticketInfoVo.getCxin());
			tempteStr.append(" 取票订单号为:"+ticketInfoVo.getOrderNumber());
			trainInfo.add(tempteStr.toString());			
		}
        
		StringTemplate st = new StringTemplate(messageContent);
		st.setAttribute("itm", itm);
		st.setAttribute("ticketlist", trainInfo);
		return st.toString();
	}
	
	
	public String createMailTemplate(MailMessage mailMessage){
		StringTemplate st = new StringTemplate(messageContent);
		st.setAttribute("itm", mailMessage);
		String content = st.toString();
		StringTemplateGroup group = new StringTemplateGroup("Train");
		String xml = "<ticket><no>$it.no$</no><name>$it.name$</name><train>$it.trainCn$</train><date>$it.startDate$</date><origStationName>$it.origStationName$</origStationName><startTime>$it.startTime$</startTime><destStationName>$it.destStationName$</destStationName><endTime>$it.endTime$</endTime><seat>$it.seatType$</seat><price>$it.price$</price><serviceCharge>$it.fee$</serviceCharge></ticket>";
		group.defineTemplate("trianXml", xml);
		StringTemplate xmlTem = new StringTemplate(group, "<ticketList>$itm.ticketList:trianXml()$</ticketList>");
		xmlTem.setAttribute("itm", mailMessage);
		content = content.replace("</ticketList>", "");
		content = content.replace("<ticketList>", xmlTem.toString());
		return content;
	}
	
	/**
	 * 创建审批短信
	 * @param orderTicket
	 * @param approveId
	 * @return
	 */
	public String createApprovalSMSTemplate(OrderTicketPaInfoVo orderTicket,long approveId){
		IssuedTicketMessage itm = new IssuedTicketMessage();
		String bookMember = orderTicket.getBookMember();
		String orderCn = orderTicket.getOrderCn();
		String seatType = null;
		String salePrice = null;
		BigDecimal fee = null;
		BigDecimal totalPrice = new BigDecimal(0);
		List<TicketInfoVo> TicketInfos = orderTicket.getTicketInfos();
		boolean isRep = TicketInfos.size()== 1 ? true:false;
		if(!isRep){
			BigDecimal price = TicketInfos.get(0).getSalePrice();
			for (TicketInfoVo ticketInfoVo : TicketInfos) {
				if(!price.equals(ticketInfoVo.getSalePrice())){
					isRep = false;
				}else{
					isRep = true;
				}
			}
		}
		List<String> trainInfo = new ArrayList<String>();
		for (int i = 0; i < TicketInfos.size(); i++) {
			TicketInfoVo ticketInfoVo = TicketInfos.get(i);
			StringBuilder tempteStr = new StringBuilder();
			tempteStr.append(ticketInfoVo.getPassenger().getName());
			tempteStr.append(ticketInfoVo.getPassenger().getCertCn()+" ");
			if(!isRep){
				tempteStr.append(SeatTypeMap.seatTypeProvider().get(ticketInfoVo.getSeatType())+" ");
				tempteStr.append(ticketInfoVo.getSalePrice()+"元 ");
			}
			if(i != TicketInfos.size()-1 && 1 != TicketInfos.size()){
				tempteStr.append("/");
			}
			totalPrice = totalPrice.add(ticketInfoVo.getSalePrice());
			trainInfo.add(tempteStr.toString());
		}
		itm.setStartDate(DateTimeUtils.getDate(TicketInfos.get(0).getStartTime()));
		itm.setStartTime(TicketInfos.get(0).getCcsj());
		itm.setOrigStationName(TicketInfos.get(0).getOrigStationName());
		itm.setDestStationName(TicketInfos.get(0).getDestStationName());
		if(isRep){
			seatType = SeatTypeMap.seatTypeProvider().get(TicketInfos.get(0).getSeatType())+" ";
			salePrice = "("+TicketInfos.get(0).getSalePrice().doubleValue()+"元/人) ";
		}
		fee = TicketInfos.get(0).getFee();
		
		StringTemplate iterator = new StringTemplate(messageContent);
		
        iterator.setAttribute("itm", itm);
        iterator.setAttribute("ticketlist", trainInfo);
        iterator.setAttribute("bookMember", bookMember);
        iterator.setAttribute("orderCn", orderCn);
        iterator.setAttribute("fee", fee.doubleValue());
        iterator.setAttribute("seatType", seatType);
        iterator.setAttribute("tmPrice", salePrice);
        iterator.setAttribute("totalPrice", totalPrice.doubleValue());
        iterator.setAttribute("approvalId", approveId);

		return iterator.toString();
	}
	
	/** 
     * @param args 
     */  
    public static void main(String[] args) {
    	
    	OrderTicketPaInfoVo orderTicket = new OrderTicketPaInfoVo();
    	orderTicket.setBookMember("长耕周 ");
    	orderTicket.setOrderCn("201606299929240V");
    	
        List<TicketInfoVo> TicketInfos = new ArrayList<TicketInfoVo>();
        TicketInfoVo ticket = new TicketInfoVo();
		ticket.setTicketNo("EC53112148");
		ticket.setStartTime(new Date());
		ticket.setOrigStationName("合肥");
		ticket.setDestStationName("北京");
		ticket.setTrainCn("Z227");
		ticket.setEndTime(new Date());
		ticket.setTrainType("成人");
		ticket.setCxin("硬卧01车厢19号中铺");
		ticket.setTmcPrice(new BigDecimal(110));
		ticket.setFee(new BigDecimal(15));
		ticket.setSeatType("1");
		Passenger ps = new Passenger();
		ps.setName("谢咏梅");
		ps.setCertCn("123456789");
		ticket.setPassenger(ps);
		TicketInfoVo ticket01 = new TicketInfoVo();
		ticket01.setTicketNo("EC53112148");
		ticket01.setStartTime(new Date());
		ticket01.setOrigStationName("合肥");
		ticket01.setDestStationName("北京");
		ticket01.setTrainCn("Z227");
		ticket01.setEndTime(new Date());
		ticket01.setTrainType("儿童");
		ticket01.setCxin("硬卧01车厢19号中铺");
		ticket01.setSeatType("M");
		ticket01.setTmcPrice(new BigDecimal(110.55));
		ticket01.setFee(new BigDecimal(15));
		Passenger ps01 = new Passenger();
		ps01.setName("谢咏梅");
		ps01.setCertCn("987654321");
		ticket01.setPassenger(ps01);
        
		TicketInfos.add(ticket);
		TicketInfos.add(ticket01);
		
		orderTicket.setTicketInfos(TicketInfos);
		
		/*单人： 尊敬的审批确认人，长耕周 预订的谢咏梅654009898767898765,2016-06-04 21:50  合肥-北京 硬卧（234.0元/人）服务费
		15.0元/人的差旅行程（审批单216085），订单2401736应付金额249.0元。如有问题请致电4006620088，请在20分钟内完成审批，同意回复y1,不同意回复n1@驳回原因。


		多人： 尊敬的审批确认人，长耕周 预订的谢咏梅654009898767898765/王浩787654321234543212,2016-06-04 21:50  合肥-北京 
		硬卧（234.0元/人）服务费15.0元/人的差旅行程（审批单216085），订单2401736应付金额498.0元。如有问题请致4006620088，请在20分钟内完成审批，同意回复y1,不同意回复n1@驳回原因*/
		
		//StringTemplate iterator = new StringTemplate(" 尊敬的审批确认人,$bookMember$预订的乘客 $ticketlist:{arg1|$arg1$}$    $itm.startDate$ tmPrice $itm.origStationName$$itm.startTime$ $itm.trainCn$ $itm.endDate$ $itm.destStationName$ $itm.endTime$,已出票成功! 如有疑问请致电400662008");
        
		StringTemplate iterator = new StringTemplate("尊敬的审批确认人，$bookMember$预订的 $ticketlist:{arg1|$arg1$}$,$itm.startDate$ $itm.startTime$ $itm.origStationName$-$itm.destStationName$ $seatType$ $tmPrice$服务费$fee$元/人的差旅行程(审批单$approvalId$),订单$orderCn$应付金额$totalPrice$元。如有问题请致4006620088，请在20分钟内完成审批");
		
		IssuedTicketMessage itm = new IssuedTicketMessage();
		String bookMember = orderTicket.getBookMember();
		String orderCn = orderTicket.getOrderCn();
		String seatType = null;
		String tmPrice = null;
		BigDecimal fee = null;
		BigDecimal totalPrice = new BigDecimal(0);
		boolean isRep = TicketInfos.size()== 1 ? true:false;
		if(!isRep){
			BigDecimal price = TicketInfos.get(0).getTmcPrice();
			for (TicketInfoVo ticketInfoVo : TicketInfos) {
				if(!price.equals(ticketInfoVo.getTmcPrice())){
					isRep = false;
				}else{
					isRep = true;
				}
			}
		}
		List<String> trainInfo = new ArrayList<String>();
		for (int i = 0; i < TicketInfos.size(); i++) {
			TicketInfoVo ticketInfoVo = TicketInfos.get(i);
			StringBuilder tempteStr = new StringBuilder();
			tempteStr.append(ticketInfoVo.getPassenger().getName());
			tempteStr.append(ticketInfoVo.getPassenger().getCertCn()+" ");
			if(!isRep){
				tempteStr.append(SeatTypeMap.seatTypeProvider().get(ticketInfoVo.getSeatType())+" ");
				tempteStr.append(ticketInfoVo.getTmcPrice()+"元 ");
			}
			if(i != TicketInfos.size()-1 && 1 != TicketInfos.size()){
				tempteStr.append("/");
			}
			
			
			
			totalPrice = totalPrice.add(ticketInfoVo.getTmcPrice());
			
			System.out.println(">>>>>>>>>>>"+totalPrice);
			
			trainInfo.add(tempteStr.toString());
		}
		itm.setStartDate(DateTimeUtils.getDate(TicketInfos.get(0).getStartTime()));
		itm.setStartTime(DateTimeUtils.formatDate(TicketInfos.get(0).getStartTime(),"HH:mm"));
		itm.setOrigStationName(TicketInfos.get(0).getOrigStationName());
		itm.setDestStationName(TicketInfos.get(0).getDestStationName());
		if(isRep){
			seatType = SeatTypeMap.seatTypeProvider().get(TicketInfos.get(0).getSeatType())+" ";
			tmPrice = "("+TicketInfos.get(0).getTmcPrice().doubleValue()+"元/人) ";
		}
		fee = TicketInfos.get(0).getFee();
        iterator.setAttribute("itm", itm);
        iterator.setAttribute("ticketlist", trainInfo);
        iterator.setAttribute("bookMember", bookMember);
        iterator.setAttribute("orderCn", orderCn);
        iterator.setAttribute("fee", fee);
        iterator.setAttribute("seatType", seatType);
        iterator.setAttribute("tmPrice", tmPrice);
        iterator.setAttribute("totalPrice", totalPrice.doubleValue());
        iterator.setAttribute("approvalId", "12312");
       
        System.out.println(iterator);  
    }  
  
	

	@Override
	public String toString() {

		return "MessageTemplate [id=" + id + ", type=" + type + ", templateNum=" + templateNum + ", subject=" + subject
				+ ", messageContent=" + messageContent + "]";
	}

}
