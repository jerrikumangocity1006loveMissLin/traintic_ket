package com.mangocity.api;

import java.util.List;

import com.mangocity.base.IBaseService;
import com.mangocity.model.FrequentTraveller;
import com.mangocity.response.ResponseMessage;
import com.mangocity.vo.FrequentTravellerVo;

public interface IFrequentTravellerService extends IBaseService<FrequentTraveller> {

	/**
	 * 根据证件号带出对应的12306账号和密码
	 * @IFrequentTravellerService
	 * @FrequentTravellerVo
	 */
	public FrequentTravellerVo fretchAccountInfoByCertificate(String certificate);
	
	/**
	 * 查出前5个可以添加常旅客的12306账号
	 * @IFrequentTravellerService
	 * @List<FrequentTravellerVo>
	 */
	public List<FrequentTravellerVo> queryTop5CandidateAccounts();
	
	/**
	 * 删除12306账号下的常联系人
	 * @IFrequentTravellerService
	 * @ResponseMessage
	 */
	public ResponseMessage deleteFrequent(FrequentTravellerVo frequentTravellervo);
}
