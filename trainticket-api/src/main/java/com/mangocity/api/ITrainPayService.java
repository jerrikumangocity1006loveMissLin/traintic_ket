package com.mangocity.api;

import com.mangocity.base.IBaseService;
import com.mangocity.model.TrainPay;
import com.mangocity.response.ResponseMessage;
import com.mangocity.vo.PayParamsVo;
import com.mangocity.vo.PayVo;

public interface ITrainPayService extends IBaseService<TrainPay>{
	
	/**
	 * 在线支付
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public ResponseMessage createPayProcess(PayParamsVo params) throws Exception;
	
	
	/*
	 * 支付平台回调接口，更新支付状态
	 */
	public ResponseMessage createPayDetail(PayParamsVo params) throws Exception;
	
	
	/**
	 * 离线支付
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public ResponseMessage createOffPayProcess(PayParamsVo params) throws Exception;
	
	/**
	 * 在线离线退款
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public ResponseMessage createMpmRefundProcess(PayParamsVo params) throws Exception;
	
	/**
	 * 根据支付会话Id获得支付
	 * @ITrainPayService
	 * @TrainPay
	 */
	public TrainPay findTrainPayByInfoId(Long payInfoId)throws Exception;
	
	//支付成功之后调用号百支付接口，通知其出票
	//公司月结是审批通过之后调用号百支付接口，通知其出票
    public ResponseMessage createHBPay(PayVo payVo) throws Exception;
    
    /**
	 * 根据订单号统计支付
	 * @param orderCn
	 * @return
	 * @throws Exception
	 */
	public String getOutTradeNo(String orderCn) throws Exception;
	
	//公司月结
    public ResponseMessage createCompanyPay(PayParamsVo params) throws Exception;

}
