package com.mangocity.btms.api;

import java.util.List;
import java.util.Set;

import com.mangocity.btms.adpater.vo.FlightPolicyQueryParam4Biz;
import com.mangocity.btms.adpater.vo.OrderSuggestion;
import com.mangocity.btms.policy.model.TravelPolicy;

/**
 * 差旅管控服务
 * 
 * @author hongxiaodong
 *
 */
public interface IPolicyManageService {
	/**
	 * 预定政策查询
	 * 
	 * @param queryParam
	 * @return
	 */
	public List<OrderSuggestion> retrievePolicySuggest(FlightPolicyQueryParam4Biz queryParam);

	/**
	 * 查询所有差旅政策
	 * 
	 * @param memberCd
	 * @return
	 */
	public Set<TravelPolicy> retrieveTravelPoliciesByShipCd(String memberCd);

	/**
	 * 是否可以公司月结支付
	 * 
	 * @param shipCd
	 * @return
	 */
	public String isMonthlyPay(String shipCd);

}
