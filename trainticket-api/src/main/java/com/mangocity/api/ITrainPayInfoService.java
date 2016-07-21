package com.mangocity.api;

import java.util.List;

import com.mangocity.base.IBaseService;
import com.mangocity.model.TrainPayInfo;

public interface ITrainPayInfoService extends IBaseService<TrainPayInfo>{
	
	/**
	 * 根据订单Id获得支付会话
	 * @ITrainPayInfoService
	 * @List<TrainPayInfo>
	 */
	List<TrainPayInfo> findTrainPayInfoByOrderId(Long orderId);
	
	/**
	 * 根据订单号，订单ID，订购项ID查询支付会话
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<TrainPayInfo> findPayInfoByItemIdCn(Long orderId,String payInfoType,List<Long> goodsIds,String goodsType) throws Exception;

}
