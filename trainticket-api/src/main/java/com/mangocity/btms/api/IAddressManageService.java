package com.mangocity.btms.api;

import java.util.List;

import com.mangocity.model.address.AddressInfo;

public interface IAddressManageService {
	
	public List<AddressInfo> queryAddressByCd(String memberCd) throws Exception;
}
