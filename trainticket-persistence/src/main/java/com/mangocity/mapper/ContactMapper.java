package com.mangocity.mapper;

import org.apache.ibatis.annotations.Param;

import com.mangocity.model.Contact;

/**
 * 联系人接口
 * @author hongxiaodong
 *
 */
public interface ContactMapper extends BaseMapper<Contact> {
	
	/**
	 * 根据订单Id获得联系人
	 * @ContactMapper
	 * @Contact
	 */
	public Contact findContactByOrderId(@Param("orderId")Long orderId);

}
