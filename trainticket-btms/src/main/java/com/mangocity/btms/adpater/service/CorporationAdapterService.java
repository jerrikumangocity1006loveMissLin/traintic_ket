/**
 * Copyright MangoCity Limited (c) 2011. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of MangoCity Limited.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from MangoCity or an authorized sublicensor.
 */
package com.mangocity.btms.adpater.service;

import com.mangocity.mbr.corporation.model.Corporation;
import com.mangocity.mbr.corporation.model.CorporationVo;

import java.util.List;

/**
 * Date: 12-7-16
 * Time: 下午3:19
 *
 * @since 1.0
 */
public interface CorporationAdapterService {

    /**
     * Queries corporation by corporationId
     *
     * @param corporationId the specified corporationId
     * @return If successful ,return the detail corporation info,include address,email and phone
     */
    public Corporation retrieveCorporationById(long corporationId);

    /**
     * Queries Corporation by corporationSn
     *
     * @param corporationSn the specified corporationSn
     * @return If successful ,return the detail corporation info,include address,email and phone
     */
    
    public Corporation retrieveCorporationBySn(String corporationSn);

    /**
     * Queries corporation by corporationNum
     *
     * @param corporationNum the specified corporationNum
     * @return If successful ,return the detail corporation info,include address,email and phone
     */
    
    public Corporation retrieveCorporationByNum(String corporationNum);

    /**
     * Queries corporation by corporationCode
     *
     * @param corporationCode the specified corporationCode
     * @return If successful ,return the detail corporation info,include address,email and phone
     */
    
    public Corporation retrieveCorporationByCode(String corporationCode);

    /**
     * Queries Corporation by member shipCd
     *
     * @param shipCd the member shipCd
     * @return corporation the detail corporation info,include address,email and phone
     */
    
    public Corporation retrieveCorporationByMbrShipCd(String shipCd);

    
    public List<Corporation> retrieveAllCorporation();


    public List<CorporationVo> retrieveAllCorporationVo();



}
