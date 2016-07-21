package com.mangocity.api;

import java.util.List;

import com.mangocity.base.IBaseService;
import com.mangocity.model.MessageReceiver;

public interface IMessageReveiverService extends IBaseService<MessageReceiver> {
	//根据订单Id获得要发送消息的联系人
   public List<MessageReceiver> queryMessageReceiversByOrderId(Long orderId) throws Exception;
}
