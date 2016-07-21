package com.mangocity.btms.api;

import com.mangocity.tmcportal.point.service.PointsServiceException;

public interface IPointsService {

	 /**
     * 获取可用积分
     * @param mbrMemberShipCode 会籍编码
     * @return 可用积分
     */
	 public long getPointsByMbrShipCode(String mbrMemberShipCode) throws PointsServiceException;
}
