package com.mangocity.btms.api;

import java.util.List;

import com.mangocity.mbr.corporation.model.Corporation;
import com.mangocity.mbr.corporation.model.CorporationVo;

/**
 * 获取公司信息服务类
 * @author hongxiaodong
 *
 */
public interface ICorporationManageService {
	
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
