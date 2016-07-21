package com.mangocity.btms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mangocity.btms.adpater.service.CostCenterAdapterService;
import com.mangocity.btms.api.ICostCenterManageService;
import com.mangocity.btms.costcenter.model.CostCenter;

public class CostCenterManageServiceImpl implements ICostCenterManageService {
	
	@Autowired
	private CostCenterAdapterService costCenterAdapterService;

	@Override
	public CostCenter retrieveCostCenterById(long costCenterId) {
	
		return costCenterAdapterService.retrieveCostCenterById(costCenterId);
	}

	@Override
	public List<CostCenter> retrieveCostCentersByCorporationId(long corporationId) {
		
		return costCenterAdapterService.retrieveCostCentersByCorporationId(corporationId);
	}

}
