package com.mangocity.api;

import java.util.List;

import com.mangocity.base.IBaseService;
import com.mangocity.model.Purchase;
import com.mangocity.response.ResponseMessage;

/**
 * @author lizhi
 *
 * @date 2016年6月1日
 */
public interface IPurchaseService extends IBaseService<Purchase>{
	
	/**
	 * 创建采购信息
	 * @param orderCn订单号
	 * @param orderItemIds订购项ID
	 * @param hbOrderId号百订单号
	 * @throws Exception
	 */
	public ResponseMessage createPurchase(String orderCn,List<Long> orderItemIds,String type) throws Exception;

}
