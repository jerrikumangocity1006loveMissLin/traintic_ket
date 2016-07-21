package com.mangocity.btms.adpater.service;

import com.mangocity.btms.adpater.vo.FlightPolicyQueryParam4Biz;
import com.mangocity.btms.adpater.vo.OrderSuggestion;
import com.mangocity.btms.policy.model.TravelPolicy;

import java.util.List;
import java.util.Set;

/**
 * Date: 12-7-24
 * Time: 下午2:28
 *
 * @since the version
 */
public interface PolicyAdapterService {
    /**
     * 预定政策查询
     * @param queryParam
     * @return
     */
    public List<OrderSuggestion> retrievePolicySuggest(FlightPolicyQueryParam4Biz queryParam);

    /**
     * 查询所有差旅政策
     * @param memberCd
     * @return
     */
    public Set<TravelPolicy> retrieveTravelPoliciesByShipCd(String memberCd);

    /**
     * 是否可以公司支付
     * @param shipCd
     * @return
     */
    public String isMonthlyPay(String shipCd);


}
