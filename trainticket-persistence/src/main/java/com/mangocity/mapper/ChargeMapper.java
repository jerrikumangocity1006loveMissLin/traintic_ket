package com.mangocity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import com.mangocity.model.Charge;

/**
 * 服务费mapper
 * @author hongxiaodong
 *
 */
public interface ChargeMapper extends BaseMapper<Charge> {
	
	/**
	 * 根据姓名查询
	 * @param name
	 * @return
	 */
	public Charge findByName(@Param("name")String name);
	
	public void batchSaveCharge(@Param("list")List<Charge> charges);
	
	/**
	 * 根据订购项ID查询服务费
	 * @param orderItemId
	 * @return
	 * @throws Exception
	 */
	public List<Charge> findByOrderItemId(@Param("orderItemId")Long orderItemId) throws Exception;

}
