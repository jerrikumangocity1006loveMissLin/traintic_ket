package com.mangocity.btms.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.mangocity.btms.adpater.service.PolicyAdapterService;
import com.mangocity.btms.adpater.vo.FlightPolicyQueryParam4Biz;
import com.mangocity.btms.adpater.vo.OrderSuggestion;
import com.mangocity.btms.api.IPolicyManageService;
import com.mangocity.btms.policy.model.TravelPolicy;
import com.mangocity.btms.policy.service.TravelPolicyService;

/**
 * 差旅管控系统
 * @author hongxiaodong
 *
 */
public class PolicyManageServiceImpl implements IPolicyManageService {
	
	@Autowired
	private PolicyAdapterService policyAdapterService;
	
	@Autowired
	private TravelPolicyService dutravelPolicyService;

	@Override
	public List<OrderSuggestion> retrievePolicySuggest(FlightPolicyQueryParam4Biz queryParam) {
		
		return policyAdapterService.retrievePolicySuggest(queryParam);
	}

	@Override
	public Set<TravelPolicy> retrieveTravelPoliciesByShipCd(String memberCd) {
		
		return policyAdapterService.retrieveTravelPoliciesByShipCd(memberCd);
	}
	
	@Override
	public String isMonthlyPay(String shipCd) {
		//1 得到会员所有政策
        Set<TravelPolicy> travelPolicies = dutravelPolicyService.retrieveTravelPoliciesByShipCd(shipCd);
        for(TravelPolicy policy : travelPolicies){
            if(policy.getPolicyDefinitionId().equals("monthlyPay")){
                return policy.getPolicyDataList().get(0).getParamValue();
            }
        }
        return null;

	}

}
