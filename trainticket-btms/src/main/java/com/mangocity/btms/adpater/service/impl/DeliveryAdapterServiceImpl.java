/**
 * Copyright Mangocity Limited (c) 2010. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of Mangocity Limited.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from Mangocity or an authorized sublicensor.
 */

package com.mangocity.btms.adpater.service.impl;

import com.mangocity.btms.adpater.service.DeliveryAdapterService;
import com.mangocity.btms.adpater.vo.DeliveryAddressVO;
import com.mangocity.tmcdelivery.model.*;
import com.mangocity.tmcdelivery.service.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * Class description.
 *
 * @version <pre>
 *                                      Revision History
 *                                      Author		Version	Date			Changes
 *                                      WuZhen	    1.0		12-3-6		    Created
 *
 *                                      </pre>
 * @since
 */

public class DeliveryAdapterServiceImpl implements DeliveryAdapterService {
    private static final Log Logger = LogFactory.getLog(DeliveryAdapterServiceImpl.class);
    private DeliveryAddressService deliveryAddressService;

    private DeliveryConfigurationService deliveryConfigurationService;

    private DeliveryItemService deliveryItemService;

    private DeliveryDetailInfoService deliveryDetailInfoService;

    private DeliveryCycleService deliveryCycleService;

    /**
     * 取得某公司下某分支的配送地址
     *
     * @param companyCode 公司编码
     * @param branchCode  分支编码
     * @return 配送地址表
     */
    public List<DeliveryAddressVO> getDeliveryAddressByCode(String companyCode, String branchCode) throws DeliveryServiceException {
        List<DeliveryAddress> deliveryAddresses = deliveryAddressService.getDeliveryAddressByDeptSn(companyCode, branchCode);

        List<DeliveryAddressVO> deliveryAddressVOList = new ArrayList<DeliveryAddressVO>();
        for (DeliveryAddress deliveryAddress : deliveryAddresses) {
            DeliveryAddressVO vo = new DeliveryAddressVO(deliveryAddress);
            Date deliveryDate = getDeliveryDate(deliveryAddress.getAddressId());
            vo.setDeliveryTime(getDate(deliveryDate, "yyyy-MM-dd"));
            if (null == vo.getMobile()) {
                vo.setMobile("");
            }
            if (null == vo.getContactTelephone()) {
                vo.setContactTelephone("");
            }
            deliveryAddressVOList.add(vo);
        }
        return deliveryAddressVOList;
    }

    public Date getDeliveryDate(long addressId) throws DeliveryServiceException {
        List<DeliveryCycle> cycle = deliveryCycleService.getDeliveryCyclesByAddressId(addressId);
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        String todayStr = df.format(new Date());
        String deliveryDateStr = deliveryCycleService.getDeliveryDate(todayStr, cycle);
        Date deliveryDate = null;
        try {
            deliveryDate = df.parse(deliveryDateStr);
        } catch (ParseException e) {
             Logger.error("统一配送时间转换失败",e);
        }
        return deliveryDate;
    }

    /**
     * 取得某分支的统一配送配置
     *
     * @param companyCode 公司编码
     * @param branchCode  分支编码
     * @return 统一配送配置  若没有查到，则返回空
     */
    public DeliveryConfiguration getDeliveryConfigurationByCode(String companyCode, String branchCode) throws DeliveryServiceException {
        DeliveryConfiguration deliveryConfiguration = deliveryConfigurationService.getDeliveryConfigurationBySn(companyCode, branchCode);
        if (null != deliveryConfiguration && deliveryConfiguration.getStatus().name().equals(DeliveryAddress.Status.valid.name())) {
            return deliveryConfiguration;
        }
        return null;
    }
    
    private String getDate(Date date, String pattern) {
        if (date == null)
            return "";

        SimpleDateFormat sd = new SimpleDateFormat(pattern);
        return sd.format(date);

        // 返回结果
        // return yInt + "-" + mStr + "-" + dStr;
    }

    /**
     * 添加一条统一配送记录
     *
     * @param deliveryItem 统一配送记录
     */
    public void addDeliveryItem(DeliveryItem deliveryItem) throws DeliveryServiceException {
        deliveryItemService.addDeliveryItem(deliveryItem);
    }

    public DeliveryItem getDeliveryItemByOrderCd(String orderCd) throws DeliveryServiceException {
        return deliveryItemService.getDeliveryItemByTicketOrderCode(orderCd);
    }

    public List<DeliveryDetailInfo> getDeliveryDetailInfoByOrderCd(String orderCd) throws DeliveryServiceException {
        return deliveryDetailInfoService.getDeliveryDetailInfoByOrderCd(orderCd);
    }

    public DeliveryAddress getDeliveryAddressById(long id) throws DeliveryServiceException {
        return deliveryAddressService.getDeliveryAddressById(id);
    }

    public void modifyDeliveryItem(DeliveryItem deliveryItem) throws DeliveryServiceException {
        deliveryItemService.modifyDeliveryItem(deliveryItem);
    }

    public void setDeliveryAddressService(DeliveryAddressService deliveryAddressService) {
        this.deliveryAddressService = deliveryAddressService;
    }

    public void setDeliveryConfigurationService(DeliveryConfigurationService deliveryConfigurationService) {
        this.deliveryConfigurationService = deliveryConfigurationService;
    }

    public void setDeliveryItemService(DeliveryItemService deliveryItemService) {
        this.deliveryItemService = deliveryItemService;
    }


    public void setDeliveryDetailInfoService(DeliveryDetailInfoService deliveryDetailInfoService) {
        this.deliveryDetailInfoService = deliveryDetailInfoService;
    }

    public void setDeliveryCycleService(DeliveryCycleService deliveryCycleService) {
        this.deliveryCycleService = deliveryCycleService;
    }
}
