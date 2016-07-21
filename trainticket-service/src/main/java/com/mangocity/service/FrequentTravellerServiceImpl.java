package com.mangocity.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.alibaba.fastjson.JSONObject;
import com.mangocity.api.IFrequentTravellerService;
import com.mangocity.common.HttpClientUtil;
import com.mangocity.mapper.AccountMapper;
import com.mangocity.mapper.FrequentTravellerMapper;
import com.mangocity.model.FrequentTraveller;
import com.mangocity.response.ResponseMessage;
import com.mangocity.service.base.BaseServiceImpl;
import com.mangocity.util.JsonUtil;
import com.mangocity.vo.DeleteAccountTravellerVo;
import com.mangocity.vo.FrequentTravellerVo;
import com.mangocity.vo.FrequentTravels;

public class FrequentTravellerServiceImpl extends BaseServiceImpl<FrequentTraveller>
		implements IFrequentTravellerService {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private FrequentTravellerMapper frequentTravellerMapper;
	@Autowired
	private AccountMapper accountMapper;
	@Resource
	private MessageSource messageReource;

	@Override
	public Long save(FrequentTraveller t) {
		frequentTravellerMapper.save(t);
		return t.getId();
	}

	@Override
	public int update(FrequentTraveller t) {
		return frequentTravellerMapper.update(t);
	}

	@Override
	public FrequentTraveller find(Long id) {
		FrequentTraveller traveller = frequentTravellerMapper.find(id);
		return traveller;
	}

	@Override
	public int delete(Long id) {
		return frequentTravellerMapper.delete(id);
	}

	@Override
	public List<FrequentTraveller> pageBySql(int pageNum, int pageCount) {
		List<FrequentTraveller> frequentTravellers = frequentTravellerMapper.pageBySql(pageNum, pageCount);
		return frequentTravellers;
	}

	/**
	 * 根据旅客证件信息携带出对应的12306账号密码
	 */
	@Override
	public FrequentTravellerVo fretchAccountInfoByCertificate(String certificate) {
		FrequentTravellerVo travellerVo = frequentTravellerMapper.queryFrequentTravellerByCertificate(certificate);
		return travellerVo;
	}

	/**
	 * 选出5个对应的备选12306账号给下单使用
	 */
	@Override
	public List<FrequentTravellerVo> queryTop5CandidateAccounts() {
		List<FrequentTravellerVo> frequentTravellerVos = frequentTravellerMapper.queryTop5CandidateAccounts();
		return frequentTravellerVos;
	}

	/**
	 * 删除对应12306账号下的常联系人
	 */
	@Override
	public ResponseMessage deleteFrequent(FrequentTravellerVo frequentTravellervo) {
		ResponseMessage responseMessage = new ResponseMessage();
		try{
		this.delete(frequentTravellervo.getId());	
		String url = messageReource.getMessage("HB_DELETE_ACCOUNT_TRAVELLERS", new Object[]{}, LocaleContextHolder.getLocale());
		FrequentTravels frequentTravels = new FrequentTravels();
		frequentTravels.setUserName(frequentTravellervo.getUserName());
		frequentTravels.setPassword(frequentTravellervo.getPassword());
		DeleteAccountTravellerVo deleteAccountTravellerVo = new DeleteAccountTravellerVo();
		deleteAccountTravellerVo.setPassengerName(frequentTravellervo.getName());
		deleteAccountTravellerVo.setPassportId(frequentTravellervo.getCertificate());
		deleteAccountTravellerVo.setPassportType(frequentTravellervo.getCertificateType());
		List<DeleteAccountTravellerVo> passengers = new ArrayList<DeleteAccountTravellerVo>();
		passengers.add(deleteAccountTravellerVo);
		frequentTravels.setTrainDeletePassengers(passengers);
		JSONObject params = JsonUtil.objectToJosnObject(frequentTravellervo);
		logger.info("===开始请求号百的删除12306账号下的常联系人===params:"+params);
		long start = System.currentTimeMillis();
		String result = HttpClientUtil.httpPostBody(url, params.toString());
		logger.info("====删除12306账号下的常联系人结束，耗时:"+(System.currentTimeMillis()-start)+"返回结果："+result);
		this.delete(frequentTravellervo.getId());
		responseMessage.setCode(ResponseMessage.SUCCESS);
		}catch(Exception e){
			responseMessage.setCode(ResponseMessage.ERROR);
			logger.info("====删除12306账号下的常联系人异常===", e);
		}	
		return responseMessage;
	}
	
	

}
