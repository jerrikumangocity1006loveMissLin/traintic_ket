package com.mangocity.mapper;

import org.apache.ibatis.annotations.Param;

import com.mangocity.model.TrainPay;

public interface TrainPayMapper extends BaseMapper<TrainPay>{

	/**
	 * 根据支付会话ID查询支付
	 * @param payInfoId
	 * @return
	 */
	public TrainPay findPayByInfoId(@Param("payInfoId")Long payInfoId) throws Exception;
}
