package com.mangocity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mangocity.model.MessageReceiver;

public interface MessageReceiverMapper extends BaseMapper<MessageReceiver> {
	
	public List<MessageReceiver> queryMessageReceiversByOrderId(@Param("orderId")Long orderId);

}
