package com.mangocity.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.mangocity.api.IContactService;
import com.mangocity.mapper.ContactMapper;
import com.mangocity.model.Contact;
import com.mangocity.service.base.BaseServiceImpl;

/**
 * 联系人
 * 
 * @author hongxiaodong
 *
 */
public class ContactServiceImpl extends BaseServiceImpl<Contact> implements IContactService {

	@Autowired
	private ContactMapper contactMapper;
	
	@Override
	public Contact findContactByOrderId(Long OrderId) {
		  return contactMapper.findContactByOrderId(OrderId);
	}

}
