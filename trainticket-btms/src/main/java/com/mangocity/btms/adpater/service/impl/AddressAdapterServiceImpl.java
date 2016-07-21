package com.mangocity.btms.adpater.service.impl;

import java.util.List;

import com.mangocity.btms.adpater.service.AddressAdapterService;
import com.mangocity.model.address.AddressInfo;
import com.mangocity.services.address.IAddressService;

public class AddressAdapterServiceImpl implements AddressAdapterService{

	private IAddressService addressService;
	
	public List<AddressInfo> queryAddressByCd(String memberCd) throws Exception {
		return addressService.queryAddressByCd(memberCd);
	}

	public IAddressService getAddressService() {
		return addressService;
	}

	public void setAddressService(IAddressService addressService) {
		this.addressService = addressService;
	}
	
	

}
