/**
 * 
 */
package com.mangocity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mangocity.model.Purchase;

/**
 * @author lizhi
 *
 * @date 2016年5月31日
 */
public interface PurchaseMapper extends BaseMapper<Purchase> {
	
	/**
	 * 根据订单号查询采购
	 * @param orderCn
	 * @return
	 * @throws Exception
	 */
	public List<Purchase> findPurchaseByOrderCn(@Param("orderCn")String orderCn) throws Exception;

}
