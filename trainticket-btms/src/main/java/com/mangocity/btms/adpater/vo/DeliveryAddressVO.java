/**
 * Copyright Mangocity Limited (c) 2010. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of Mangocity Limited.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from Mangocity or an authorized sublicensor.
 */

package com.mangocity.btms.adpater.vo;

import com.mangocity.tmcdelivery.model.DeliveryAddress;

/**
 * Class description.
 *
 * @version <pre>
 *                                                       Revision History
 *                                                       Author		Version	Date			Changes
 *                                                       WuZhen	    1.0		12-3-6		    Created
 *
 *                                                       </pre>
 * @since
 */

public class DeliveryAddressVO extends DeliveryAddress {

    public DeliveryAddressVO(DeliveryAddress deliveryAddress) {
        setAddressId(deliveryAddress.getAddressId());
        setAddress(deliveryAddress.getAddress());
        setAddressee(deliveryAddress.getAddressee());
        setCity(deliveryAddress.getCity());
        setCorporationSn(deliveryAddress.getCorporationSn());
        setContactTelephone(deliveryAddress.getContactTelephone());
        setDeliveryType(deliveryAddress.getDeliveryType());
        setMobile(deliveryAddress.getMobile());
        setCreateBy(deliveryAddress.getCreateBy());
        setCreateTime(deliveryAddress.getCreateTime());
        setLastModifyBy(deliveryAddress.getLastModifyBy());
        setLastModifyTime(deliveryAddress.getLastModifyTime());
        setRegion(deliveryAddress.getRegion());
        setPostCode(deliveryAddress.getPostCode());
        setStatus(Status.valid);
    }

    /**
     * 预计配送时间
     */
    private String deliveryTime;

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
	}
}