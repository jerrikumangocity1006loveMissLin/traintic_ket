package com.mangocity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mangocity.api.ITrainPayDetailService;
import com.mangocity.mapper.TrainPayDetailMapper;
import com.mangocity.model.TrainPayDetail;
import com.mangocity.service.base.BaseServiceImpl;

public class TrainPayDetailServiceImpl extends BaseServiceImpl<TrainPayDetail> implements ITrainPayDetailService{
	
	@Autowired
	private TrainPayDetailMapper trainPayDetailMapper;
	
	/**
	 * 根据支付流水号查询支付详情
	 * @param outTradeNo
	 * @return
	 */
	@Override
	public List<TrainPayDetail> findByOutTradeNo(String outTradeNo) throws Exception{
		return trainPayDetailMapper.findByOutTradeNo(outTradeNo);
	}

	/**
	 * 根据支付ID查询支付详情
	 * @param payId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<TrainPayDetail> findByPayId(Long payId,String type) throws Exception {
		return trainPayDetailMapper.findByPayId(payId,type);
	}
	
	/**
	 * 根据订单ID，支付类型：（0付款，1退款），查询支付详情
	 * @param orderId
	 * @param type
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<TrainPayDetail> findPayDetailByOrderId(Long orderId,String type,String status) throws Exception{
		return trainPayDetailMapper.findPayDetailByOrderId(orderId, type,status);
	}

}
