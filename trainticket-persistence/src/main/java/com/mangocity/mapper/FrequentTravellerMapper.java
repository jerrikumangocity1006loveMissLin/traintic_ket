package com.mangocity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mangocity.model.FrequentTraveller;
import com.mangocity.vo.FrequentTravellerVo;

public interface FrequentTravellerMapper extends BaseMapper<FrequentTraveller> {

	/**
	 * 根据证件号带出对应的12306账号和密码
	 * @FrequentTravellerMapper
	 * @FrequentTravellerVo
	 */
	public FrequentTravellerVo queryFrequentTravellerByCertificate(String certificate);
	
	/**
	 * 查出前5个可以添加常旅客的12306账号
	 * @FrequentTravellerMapper
	 * @List<FrequentTravellerVo>
	 */
	public List<FrequentTravellerVo> queryTop5CandidateAccounts();
	
	/**
	 * 删除所有
	 * @FrequentTravellerMapper
	 * @void
	 */
	public void deleteTravellerByAccountId(@Param("accountId")Long id);
	
}
