/**
 * Copyright Mangocity Limited (c) 2010. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of Mangocity Limited.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from Mangocity or an authorized sublicensor.
 */

package com.mangocity.btms.adpater.service;

import com.mangocity.btms.adpater.vo.DeliveryAddressVO;
import com.mangocity.tmcdelivery.model.DeliveryAddress;
import com.mangocity.tmcdelivery.model.DeliveryConfiguration;
import com.mangocity.tmcdelivery.model.DeliveryDetailInfo;
import com.mangocity.tmcdelivery.model.DeliveryItem;
import com.mangocity.tmcdelivery.service.DeliveryServiceException;

import java.util.Date;
import java.util.List;

/**
 * Class description.
 *
 * @version <pre>
 *                   Revision History
 *                   Author		Version	Date			Changes
 *                   WuZhen	    1.0		12-3-6		    Created
 *
 *                   </pre>
 * @since $
 */
public interface DeliveryAdapterService {

	/**
	 * 取得某公司下某分支的配送地址
	 * @param companyCode 公司编码
	 * @param branchCode 分支编码
	 * @return 配送地址表
	 */
	public List<DeliveryAddressVO> getDeliveryAddressByCode(String companyCode, String branchCode) throws DeliveryServiceException;

    public Date getDeliveryDate(long addressId) throws DeliveryServiceException;

	/**
	 * 取得某分支的统一配送配置
	 * @param companyCode 公司编码
	 * @param branchCode 分支编码
	 * @return 统一配送配置
	 */
	public DeliveryConfiguration getDeliveryConfigurationByCode(String companyCode, String branchCode) throws DeliveryServiceException;

	/**
	 * 添加一条统一配送记录
	 * @param deliveryItem 统一配送记录
	 */
	public void addDeliveryItem(DeliveryItem deliveryItem) throws DeliveryServiceException;

    /**
	 * 根据订单cd获取deliveryItem
	 * @param orderCd
	 */
	public DeliveryItem getDeliveryItemByOrderCd(String orderCd) throws DeliveryServiceException;

    /**
	 * 根据订单cd获取deliveryDetailInfo
	 * @param orderCd
	 */
	public List<DeliveryDetailInfo> getDeliveryDetailInfoByOrderCd(String orderCd) throws DeliveryServiceException;

    public DeliveryAddress getDeliveryAddressById(long id)throws DeliveryServiceException;

    public void modifyDeliveryItem(DeliveryItem deliveryItem)throws DeliveryServiceException;
}
