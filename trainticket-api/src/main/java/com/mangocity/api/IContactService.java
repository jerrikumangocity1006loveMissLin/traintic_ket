package com.mangocity.api;

import com.mangocity.base.IBaseService;
import com.mangocity.model.Contact;


/**
 * 联系人服务类
 * @author hongxiaodong
 *
 */
public interface IContactService extends IBaseService<Contact> {
	/**
	 * 根据订单Id获得联系人
	 * @IContactService
	 * @Contact
	 */
	public Contact findContactByOrderId(Long OrderId);

}
