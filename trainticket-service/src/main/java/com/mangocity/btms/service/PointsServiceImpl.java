package com.mangocity.btms.service;

import com.mangocity.btms.api.IPointsService;
import com.mangocity.tmcportal.point.service.PointsService;
import com.mangocity.tmcportal.point.service.PointsServiceException;

public class PointsServiceImpl implements IPointsService{
	
	private PointsService newPointsService;

	@Override
	public long getPointsByMbrShipCode(String mbrMemberShipCode) throws PointsServiceException {
		return newPointsService.getPointsByMbrShipCode(mbrMemberShipCode);
	}

	public PointsService getNewPointsService() {
		return newPointsService;
	}

	public void setNewPointsService(PointsService newPointsService) {
		this.newPointsService = newPointsService;
	}
	
}
