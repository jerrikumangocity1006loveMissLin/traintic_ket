package com.mangocity.api;

import java.util.List;

import com.mangocity.base.IBaseService;
import com.mangocity.model.TrainPayDetail;

public interface ITrainPayDetailService extends IBaseService<TrainPayDetail>{
	
	/**
	 * 根据支付流水号查询支付详情
	 * @param outTradeNo
	 * @return
	 */
	public List<TrainPayDetail> findByOutTradeNo(String outTradeNo) throws Exception;
	
	/**
	 * 根据支付ID查询支付详情
	 * @param payId
	 * @return
	 * @throws Exception
	 */
	public List<TrainPayDetail> findByPayId(Long payId,String type) throws Exception;
	
	/**
	 * 根据订单ID，支付类型：（0付款，1退款），查询支付详情
	 * @param orderId
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<TrainPayDetail> findPayDetailByOrderId(Long orderId,String type,String status) throws Exception;

}
