package com.mangocity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mangocity.api.ITrainPayInfoService;
import com.mangocity.mapper.TrainPayInfoMapper;
import com.mangocity.model.TrainPayInfo;
import com.mangocity.service.base.BaseServiceImpl;

public class TrainPayInfoServiceImpl extends BaseServiceImpl<TrainPayInfo> implements ITrainPayInfoService{

	@Autowired
	private TrainPayInfoMapper trainInfoMapper;
	
	@Override
	public List<TrainPayInfo> findTrainPayInfoByOrderId(Long orderId) {
		List<TrainPayInfo> trainPayInfos = trainInfoMapper.findPayInfoByOrderId(orderId);
		return trainPayInfos;
	}

	/**
	 * 根据订单号，订单ID，订购项ID查询支付会话
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<TrainPayInfo> findPayInfoByItemIdCn(Long orderId,
			String payInfoType, List<Long> goodsIds,String goodsType) throws Exception {
		return trainInfoMapper.findPayInfoByItemIdCn(orderId, payInfoType, goodsIds,goodsType);
	}

}
