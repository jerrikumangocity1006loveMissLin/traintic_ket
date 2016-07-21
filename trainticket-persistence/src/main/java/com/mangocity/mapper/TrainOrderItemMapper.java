package com.mangocity.mapper;

import java.util.List;
import java.util.Map;

import com.mangocity.model.OrderItem;

public interface TrainOrderItemMapper extends BaseMapper<OrderItem> {
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<OrderItem> findOrderItemListById(List<Long> ids);
	
	
	/**
	 * 根据orderId查找订购项
	 * @param orderId
	 * @return
	 */
	public List<OrderItem> findOrderItemByOrderId(Long orderId);
	
	/**
	  * @Title: 获得订单订购项 
	  * @param orderId
	  * @param type
	  * @return 
	  * OrderItem
	 */
	public OrderItem findOrderItemByOrderIdAndGoodsType(Map map);
}
