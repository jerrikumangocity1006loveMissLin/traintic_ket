package com.mangocity.btms.adpater.service;

import java.util.List;

import com.mangocity.model.address.AddressInfo;

public interface AddressAdapterService {
	
	public List<AddressInfo> queryAddressByCd(String memberCd) throws Exception;
}
