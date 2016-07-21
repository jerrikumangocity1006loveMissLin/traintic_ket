package com.mangocity.btms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mangocity.btms.adpater.service.CorporationAdapterService;
import com.mangocity.btms.api.ICorporationManageService;
import com.mangocity.mbr.corporation.model.Corporation;
import com.mangocity.mbr.corporation.model.CorporationVo;

/**
 * 获取公司信息服务类
 * @author hongxiaodong
 *
 */
public class CorporationManageServiceImpl implements ICorporationManageService {
	
	@Autowired
	private CorporationAdapterService corporationAdapterService;

	@Override
	public Corporation retrieveCorporationById(long corporationId) {
		
		return corporationAdapterService.retrieveCorporationById(corporationId);
	}

	@Override
	public Corporation retrieveCorporationBySn(String corporationSn) {
		
		return corporationAdapterService.retrieveCorporationBySn(corporationSn);
	}

	@Override
	public Corporation retrieveCorporationByNum(String corporationNum) {
		
		return corporationAdapterService.retrieveCorporationByNum(corporationNum);
	}

	@Override
	public Corporation retrieveCorporationByCode(String corporationCode) {
		
		return corporationAdapterService.retrieveCorporationByCode(corporationCode);
	}

	@Override
	public Corporation retrieveCorporationByMbrShipCd(String shipCd) {
		
		return corporationAdapterService.retrieveCorporationByMbrShipCd(shipCd);
	}

	@Override
	public List<Corporation> retrieveAllCorporation() {
		
		return corporationAdapterService.retrieveAllCorporation();
	}

	@Override
	public List<CorporationVo> retrieveAllCorporationVo() {
		
		return corporationAdapterService.retrieveAllCorporationVo();
	}

}
