package com.mangocity.api;

import java.util.List;

import com.mangocity.base.IBaseService;
import com.mangocity.enums.MessageType;
import com.mangocity.model.MessageTemplate;
import com.mangocity.response.ResponseMessage;

/**
 * 消息模板
 * @author hongxiaodong
 *
 */
public interface IMessageTemplateService extends IBaseService<MessageTemplate> {
	
	/**
	 * 根据消息内容获取所有的消息模板
	 * @param type
	 * @return
	 */
	public List<MessageTemplate> findByType(MessageType type);
	
	/**
	 * 根据模版ID获取模板
	 * @param templateNum
	 * @return
	 */
	public MessageTemplate findBytemplateNum(Integer templateNum);
	
	/**
	 * 预览邮件模板
	 * @param orderItemId
	 * @param templateNum
	 * @return
	 * @throws Exception
	 */
	public String showEmailTemplat(String orderCn,int templateNum,String messageType) throws Exception;

}
