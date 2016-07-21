/**
 * Copyright MangoCity Limited (c) 2011. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of MangoCity Limited.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from MangoCity or an authorized sublicensor.
 */
package com.mangocity.btms.adpater.service.impl;

import com.mangocity.btms.adpater.service.CorporationAdapterService;
import com.mangocity.mbr.corporation.model.Corporation;
import com.mangocity.mbr.corporation.model.CorporationVo;
import com.mangocity.mbr.corporation.service.CorporationService;

import java.util.List;

/**
 * Date: 12-7-16
 * Time: 下午3:19
 *
 * @since 1.0
 */

public class CorporationAdapterServiceImpl implements CorporationAdapterService {

    private CorporationService corporationService;

    public Corporation retrieveCorporationById(long corporationId) {
    	
        return corporationService.retrieveCorporationById(corporationId);
    }

    public Corporation retrieveCorporationBySn(String corporationSn) {
        return corporationService.retrieveCorporationBySn(corporationSn);
    }

    public Corporation retrieveCorporationByNum(String corporationNum) {
        return corporationService.retrieveCorporationByNum(corporationNum);
    }

    public Corporation retrieveCorporationByCode(String corporationCode) {
        return corporationService.retrieveCorporationByCode(corporationCode);
    }

    public Corporation retrieveCorporationByMbrShipCd(String shipCd) {
        return corporationService.retrieveCorporationByMbrShipCd(shipCd);
    }

    public List<Corporation> retrieveAllCorporation() {
        return corporationService.retrieveAllCorporation();
    }

    public List<CorporationVo> retrieveAllCorporationVo() {
        return corporationService.retrieveAllCorporationVo();
    }

    public void setCorporationService(CorporationService corporationService) {
        this.corporationService = corporationService;
    }
}
