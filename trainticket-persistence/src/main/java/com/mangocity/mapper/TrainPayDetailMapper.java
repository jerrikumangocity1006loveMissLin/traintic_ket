package com.mangocity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mangocity.model.TrainPayDetail;

public interface TrainPayDetailMapper extends BaseMapper<TrainPayDetail>{
	
	/**
	 * 根据支付ID查询支付详情
	 * @param payId
	 * @return
	 */
	public List<TrainPayDetail> findByPayId(@Param("payId")Long payId,@Param("status")String status) throws Exception;
	
	/**
	 * 根据支付流水号查询支付详情
	 * @param outTradeNo
	 * @return
	 */
	public List<TrainPayDetail> findByOutTradeNo(@Param("outTradeNo")String outTradeNo) throws Exception;
	
	/**
	 * 根据订单号统计支付
	 * @param orderCn
	 * @return
	 * @throws Exception
	 */
	public String findPayDetailCountByOrderCn(@Param("orderCn")String orderCn) throws Exception;
	
	/**
	 * 根据订单ID，支付类型：（0付款，1退款），查询支付详情
	 * @param orderId
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<TrainPayDetail> findPayDetailByOrderId(@Param("orderId")Long orderId,@Param("type")String type,@Param("status")String status) throws Exception;
	
	/**
	 * 根据号百订单ID查询支付详情
	 * @param hbOrderId
	 * @return
	 * @throws Exception
	 */
	public List<TrainPayDetail> findPayDetailByHbOrderId(@Param("hbOrderId")String hbOrderId) throws Exception;
}
