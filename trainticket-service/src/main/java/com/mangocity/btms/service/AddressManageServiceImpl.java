package com.mangocity.btms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mangocity.btms.adpater.service.AddressAdapterService;
import com.mangocity.btms.api.IAddressManageService;
import com.mangocity.model.address.AddressInfo;

public class AddressManageServiceImpl implements IAddressManageService{

	@Autowired
	private AddressAdapterService addressAdapterService;
	
	@Override
	public List<AddressInfo> queryAddressByCd(String memberCd) throws Exception {
		return addressAdapterService.queryAddressByCd(memberCd);
	}

}
