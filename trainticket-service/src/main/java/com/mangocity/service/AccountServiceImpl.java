package com.mangocity.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mangocity.api.IAccountService;
import com.mangocity.common.HttpClientUtil;
import com.mangocity.mapper.AccountMapper;
import com.mangocity.mapper.FrequentTravellerMapper;
import com.mangocity.model.Account;
import com.mangocity.model.FrequentTraveller;
import com.mangocity.response.ResponseMessage;
import com.mangocity.service.base.BaseServiceImpl;
import com.mangocity.util.JsonUtil;
import com.mangocity.vo.AccountVo;
import com.mangocity.vo.FrequentTravellerVo;

public class AccountServiceImpl extends BaseServiceImpl<Account> implements IAccountService {

	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private FrequentTravellerMapper frequentTravellerMapper;
	@Resource
	private MessageSource messageReource;
	
	@Override
	public Long save(Account t) {
		Long id = accountMapper.save(t);
		this.syschroAccountTravellers(t);
		return id;
	}

	@Override
	public int update(Account t) {	
		return accountMapper.update(t);
	}

	@Override
	public Account find(Long id) {
		Account account = accountMapper.find(id);
		return account;
	}

	@Override
	public int delete(Long id) {
		frequentTravellerMapper.deleteTravellerByAccountId(id);
		accountMapper.delete(id);
		return 0;
	}

	@Override
	public List<AccountVo> queryAllInfo(int startIndex, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseMessage deleteAccount(Account account) {
		ResponseMessage responseMessage = new ResponseMessage();
		String url = messageReource.getMessage("", new Object[]{}, LocaleContextHolder.getLocale());
		String result =  "";
		this.delete(account.getId());
		return responseMessage;
	}

	/**
	 *同步12306账号下的常联系人
	 */
	@Override
	public ResponseMessage syschroAccountTravellers(Account account) {
		ResponseMessage responseMessage = new ResponseMessage();
		String url = messageReource.getMessage("HB_SEARCH_ACCOUNT_TRAVELLERS", new Object[]{}, LocaleContextHolder.getLocale());
		String params = JsonUtil.Object2JsonString(account);
		logger.info("=====开始请求号百获得12306账号下的联系人====params："+params);
		long start = System.currentTimeMillis();
		String result = HttpClientUtil.httpPostBody(url, params);
		logger.info("请求号百获得12306结束耗时："+(System.currentTimeMillis()-start)+"==返回结果=="+result);
		if(StringUtils.isNotEmpty(result)&&result.startsWith("{")){
			JSONObject jsonObj = JsonUtil.stringToJsonObject(result);
			String code = jsonObj.getString("code");
			if(StringUtils.isEquals("0", code)){
				JSONArray jsonArray = jsonObj.getJSONArray("pas");
				for(Object obj : jsonArray){
					JSONObject json = JsonUtil.stringToJsonObject(obj.toString());
					FrequentTravellerVo frequentTravellerVo =frequentTravellerMapper.queryFrequentTravellerByCertificate("certificate");
					if(frequentTravellerVo == null){
						Map map = new HashMap();
				    	map.put("account", account.getUserName());
				    	map.put("password", account.getPassword());
				    	Account ac = accountMapper.queryAccount(map);
			        	if(ac!=null){
			        	FrequentTraveller t = new FrequentTraveller(null, ac.getId(), json.getString("pna"),json.getString("pml"), 
			        		json.getString("pty"),json.getString("pid"), "", json.getString("pst")); 
			        	frequentTravellerMapper.save(t);
			        	}
			        }
				}
			}
		}
		return responseMessage;
	}

}
