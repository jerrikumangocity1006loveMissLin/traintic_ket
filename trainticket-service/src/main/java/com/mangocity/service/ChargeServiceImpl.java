package com.mangocity.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.mangocity.api.IChargeService;
import com.mangocity.mapper.ChargeMapper;
import com.mangocity.model.Charge;
import com.mangocity.service.base.BaseServiceImpl;

/**
 * 服务类
 * @author hongxiaodong
 *
 */
public class ChargeServiceImpl extends BaseServiceImpl<Charge> implements IChargeService {
	
	@Autowired
	private ChargeMapper chargeMapper;

	
	public Charge findByName(String name) {
		
		return chargeMapper.findByName(name);
	}

}
