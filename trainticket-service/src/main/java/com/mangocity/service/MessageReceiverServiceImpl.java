package com.mangocity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mangocity.api.IMessageReveiverService;
import com.mangocity.mapper.MessageReceiverMapper;
import com.mangocity.model.MessageReceiver;
import com.mangocity.service.base.BaseServiceImpl;

public class MessageReceiverServiceImpl  extends BaseServiceImpl<MessageReceiver> implements IMessageReveiverService {

	@Autowired
	private MessageReceiverMapper messageReceiverMapper;
	
	@Override
	public List<MessageReceiver> queryMessageReceiversByOrderId(Long orderId) {
		List<MessageReceiver> messageReceivers = messageReceiverMapper.queryMessageReceiversByOrderId(orderId);
		return messageReceivers;
	}

}
