/**
 * 
 */
package com.mangocity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mangocity.model.Delivery;

/**
 * @author lizhi
 *
 */
public interface DeliveryMapper extends BaseMapper<Delivery> {
	
	public void saveBatchDelevery(@Param("list")List<Delivery> deliverys);
	
	/**
	 * 根据订购项ID查询配送
	 * @param orderItemId
	 * @return
	 * @throws Exception
	 */
	public List<Delivery> findDeliveryByOrderItemId(@Param("orderItemId")Long orderItemId) throws Exception;

}
