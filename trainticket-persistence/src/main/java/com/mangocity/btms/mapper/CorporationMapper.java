package com.mangocity.btms.mapper;

import org.apache.ibatis.annotations.Param;

import com.mangocity.btms.model.Corporation;

/**
 * 公司信息查询
 * @author hongxiaodong
 *
 */
public interface CorporationMapper {
	
	
	/**
	 * 根据会员查询公司信息
	 * @param mbrcd
	 * @return
	 */
	public Corporation findCorporationByMbrCd(@Param("mbrcd")String mbrcd);
	
	
	
	/**
	 * 根据公司ID查询公司信息
	 * @param corporationId
	 * @return
	 */
	public Corporation findCorporationByCorporationId(@Param("corporationId")Long corporationId);
	
	
	/**
	 * 根据公司编号或者code查询公司信息
	 * @param corporationNum
	 * @param corporationCode
	 * @return
	 */
	public Corporation findCorporationByNumOrCode(@Param("corporationNum")String corporationNum,@Param("corporationCode")String corporationCode);
	

}
