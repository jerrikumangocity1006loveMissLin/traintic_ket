package com.mangocity.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mangocity.api.IMessageTemplateService;
import com.mangocity.enums.MessageType;
import com.mangocity.mapper.MessageTemplateMapper;
import com.mangocity.mapper.TrainTicketMapper;
import com.mangocity.model.MessageTemplate;
import com.mangocity.service.base.BaseServiceImpl;
import com.mangocity.vo.MailMessage;
import com.mangocity.vo.MailTicket;
import com.mangocity.vo.TicketInfoVo;

/**
 * 消息模板管理页
 * @author hongxiaodong
 *
 */
public class MessageTemplateServiceImpl extends BaseServiceImpl<MessageTemplate> implements IMessageTemplateService {
	
	Logger logger = Logger.getLogger(MessageTemplateServiceImpl.class);
	
	@Autowired
	private MessageTemplateMapper messageTemplateMapper;
	
	@Autowired
	private TrainTicketMapper trainTicketMapper;

	@Override
	public List<MessageTemplate> findByType(MessageType type) {
	
		return messageTemplateMapper.findByType(type);
	}

	@Override
	public MessageTemplate findBytemplateNum(Integer templateNum) {
		
		return messageTemplateMapper.findBytemplateNum(templateNum);
	}
	
	
	/**
	 * 预览邮件模板
	 * @param orderItemId
	 * @param templateNum
	 * @return
	 * @throws Exception
	 */
	@Override
	public String showEmailTemplat(String orderCn,int templateNum,String messageType) throws Exception{
		String html = "";
		try {
			MessageTemplate messageTem = messageTemplateMapper.findBytemplateNum(templateNum);
			if(null != messageTem){
				MailMessage mailMessage = new MailMessage();
				//查询订单type=0
				//订单状态为出票status=8
				List<TicketInfoVo> ticketList = trainTicketMapper.findTicketInfoByOrderCd(orderCn, "0","8");
				List<MailTicket> list = new ArrayList<MailTicket>();
				BigDecimal allPrice = new BigDecimal("0.0");
				Integer num = 1;
				for(TicketInfoVo ticket :ticketList){
					MailTicket itm = new MailTicket();
					itm.setNo(num.toString());
					itm.setOrigStationName(ticket.getOrigStationName());
					itm.setDestStationName(ticket.getDestStationName());
			        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			        itm.setStartDate(ticket.getStartTime() != null ? format.format(ticket.getStartTime()) : "");
			        itm.setStartTime(ticket.getCcsj());
			        itm.setEndTime(ticket.getDdsj());
			        itm.setTrainCn(ticket.getTrainCn());
			        itm.setTrainType(ticket.getTrainType());
			        itm.setSeatType(ticket.getSeatType());
			        itm.setCxin(ticket.getCxin());
			        BigDecimal price = ticket.getSalePrice() != null ? ticket.getSalePrice():new BigDecimal("0.0");
			        itm.setPrice(price.toString());
			        itm.setName(ticket.getPassenger() != null ? ticket.getPassenger().getName() : "");
			        BigDecimal fee = ticket.getFee() != null ? ticket.getFee():new BigDecimal("0.0");
			        itm.setFee(fee.toString());
			        list.add(itm);
			        allPrice = allPrice.add(price).add(fee);
			        num++;
				}
				mailMessage.setTicketList(list);
				mailMessage.setAllPrice(allPrice.toString());
				String result = messageTem.createMailTemplate(mailMessage);
				
				InputStream inputStream = new ByteArrayInputStream(result.getBytes("UTF-8"));
				String path = Thread.currentThread().getContextClassLoader().getResource("../../").getPath();
				FileInputStream fIn = null;
				InputStreamReader xslFile = null;
				ByteArrayOutputStream output = null;
				try {
					fIn = new FileInputStream(path + File.separator +"templates" + File.separator + messageType + ".xsl");
					xslFile = new InputStreamReader(fIn, "UTF-8"); 
					TransformerFactory tFac = TransformerFactory.newInstance();
					Source xslSource = new StreamSource(xslFile);
					Transformer t = tFac.newTransformer(xslSource);
					Source source = new StreamSource(inputStream);
					output = new ByteArrayOutputStream();
					Result result1 = new StreamResult(output);
					t.transform(source, result1);
					html = new String(output.toByteArray(),"UTF-8");
					if(html.indexOf("<html") > 0){
						html = html.substring(html.indexOf("<html"), html.length());
					}
					//logger.info(html);
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					if(fIn != null){
						fIn.close();
					}
					if(xslFile != null){
						xslFile.close();
					}
					if(output != null){
						output.close();
					}
				}
			}
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}
		return html;
	}

}
