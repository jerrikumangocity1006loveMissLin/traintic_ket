package com.mangocity.btms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mangocity.btms.api.ICorporationService;
import com.mangocity.btms.mapper.CorporationMapper;
import com.mangocity.btms.model.Corporation;

/**
 * 公司服务接口类
 * @author hongxiaodong
 *
 */
@Transactional(value="btms")
public class CorporationServiceImpl implements ICorporationService {
	
	@Autowired
	private CorporationMapper corporationMapper;

	@Override
	public Corporation retrieveCorporationById(long corporationId) {
		
		return corporationMapper.findCorporationByCorporationId(corporationId);
	}

	@Override
	public Corporation retrieveCorporationByNumOrCode(String corporationNum, String corporationCode) {
		
		return corporationMapper.findCorporationByNumOrCode(corporationNum, corporationCode);
	}

	@Override
	public Corporation retrieveCorporationByMbrShipCd(String shipCd) {
	
		return corporationMapper.findCorporationByMbrCd(shipCd);
	}

}
