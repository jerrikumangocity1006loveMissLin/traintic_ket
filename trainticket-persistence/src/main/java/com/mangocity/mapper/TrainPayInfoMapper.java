package com.mangocity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mangocity.model.TrainPayInfo;

public interface TrainPayInfoMapper extends BaseMapper<TrainPayInfo>{
	
	/**
	 * 根据订单号，订单ID，火车票ID查询支付会话
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<TrainPayInfo> findPayInfoByItemIdCn(@Param("orderId")Long orderId, @Param("payInfoType")String payInfoType,@Param("list") List<Long> ticketIds,@Param("goodsType")String goodsType) throws Exception;
	
	/**
	 * 根据订单Id查询支付会话
	 * @TrainPayInfoMapper
	 * @List<TrainPayInfo>
	 */
	public List<TrainPayInfo> findPayInfoByOrderId(@Param("orderId") Long orderId);

}
