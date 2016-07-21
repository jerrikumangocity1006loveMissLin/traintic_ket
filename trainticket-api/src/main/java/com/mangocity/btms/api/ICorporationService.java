package com.mangocity.btms.api;

import com.mangocity.btms.model.Corporation;

/**
 * 获取公司信息服务类
 * @author hongxiaodong
 *
 */
public interface ICorporationService {
	
	/**
     * Queries corporation by corporationId
     *
     * @param corporationId the specified corporationId
     * @return If successful ,return the detail corporation info,include address,email and phone
     */
    public Corporation retrieveCorporationById(long corporationId);

    /**
     * Queries corporation by corporationNum or corporationCode
     *
     * @param corporationNum the specified corporationNum
     * @param corporationCode the specified corporationCode
     * @return If successful ,return the detail corporation info,include address,email and phone
     */
    
    public Corporation retrieveCorporationByNumOrCode(String corporationNum,String corporationCode);

    /**
     * Queries Corporation by member shipCd
     *
     * @param shipCd the member shipCd
     * @return corporation the detail corporation info,include address,email and phone
     */
    
    public Corporation retrieveCorporationByMbrShipCd(String shipCd);

}
