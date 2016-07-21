package com.mangocity.btms.adpater.service.impl;

import com.mangocity.btms.adpater.service.PolicyAdapterService;
import com.mangocity.btms.adpater.service.handler.FlightTravelPolicyHandler;
import com.mangocity.btms.adpater.vo.FlightPolicyQueryParam4Biz;
import com.mangocity.btms.adpater.vo.OrderSuggestion;
import com.mangocity.btms.policy.model.PolicyApplicableFilter;
import com.mangocity.btms.policy.model.TravelPolicy;
import com.mangocity.btms.policy.service.TravelPolicyService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Date: 12-7-24
 * Time: 下午4:50
 *
 * @since: the version
 */
public class PolicyAdapterServiceImpl implements PolicyAdapterService{
    private static final Log logger = LogFactory.getLog(PolicyAdapterServiceImpl.class);
    private TravelPolicyService travelPolicyService;
    private static final List EMPTY_SET = Collections.unmodifiableList(new ArrayList(0));
    private static final String MONTHLY_PAY = "monthlyPay";
    private static final String ONLY_ORDER_SELF = "orderYourself";
    private static ApplicationContext ctx = null;
    static {
         ctx = new ClassPathXmlApplicationContext(new String[]{"config/spring/btms-adapter-config.xml","config/spring/btms-client-config.xml"});
    }
    public List<OrderSuggestion> retrievePolicySuggest(FlightPolicyQueryParam4Biz queryParam) {
        logger.debug("会员所受管控的政策");
        if(queryParam == null || StringUtils.isEmpty(queryParam.getShipCd())){
            logger.debug("查询参数错误!queryParam = " +queryParam);
            return EMPTY_SET;
        }
        //1 得到会员所有政策
        Set<TravelPolicy> travelPolicies = travelPolicyService.retrieveTravelPoliciesByShipCd(queryParam.getShipCd());
        logger.debug("得到会员所受管控的政策条数"+travelPolicies.size());
        OrderSuggestion suggestion = null;
        List<OrderSuggestion> suggestions = new ArrayList<OrderSuggestion>();
        for(TravelPolicy policy : travelPolicies){
            if(isApplicableFilter(queryParam.getShipCd(),policy.getApplicableFilterList())){
                continue;
            }
            String beanName = policy.getPolicyDefinition().getBeanName();
            if(beanName == null || StringUtils.isEmpty(beanName)) continue;
            //根据不同的定义去调用不同的处理类
            logger.debug("得到会员所受管控的政策"+beanName);
            FlightTravelPolicyHandler policyIntegrationFlight = (FlightTravelPolicyHandler)ctx.getBean(beanName);
            try {
                suggestion = policyIntegrationFlight.parsePolicy(policy,queryParam);
            } catch (ParseException e) {
                logger.error("parse time error!",e);
            }
            logger.debug("----suggestion--------"+suggestion);
            if(suggestion!=null) {
             logger.debug("得到会员所违反的政策"+suggestion.getPolicyName()+"----------"+suggestion.getMessageType());
             suggestions.add(suggestion);
            }
        }
         return suggestions;
    }

    /**
     * 判断 shipCd是否在 applicableFilterList里面 如果return true则 过滤生效、不受管控，
     * @param shipCd
     * @param applicableFilterList
     * @return
     */
    private boolean isApplicableFilter(String shipCd,List<PolicyApplicableFilter> applicableFilterList) {
        for(PolicyApplicableFilter applicableFilter:applicableFilterList){
            if(shipCd.equals(applicableFilter.getExpression())){
                return  true;
            }
        }
        return false;
    }

    public Set<TravelPolicy> retrieveTravelPoliciesByShipCd(String memberCd){
          return travelPolicyService.retrieveTravelPoliciesByShipCd(memberCd);
    }

    public String isMonthlyPay(String shipCd) {
        //1 得到会员所有政策
        Set<TravelPolicy> travelPolicies = travelPolicyService.retrieveTravelPoliciesByShipCd(shipCd);
        for(TravelPolicy policy : travelPolicies){
            if(policy.getPolicyDefinitionId().equals(MONTHLY_PAY)){
                return policy.getPolicyDataList().get(0).getParamValue();
            }
        }
        return null;
    }

    public void setTravelPolicyService(TravelPolicyService travelPolicyService) {
        this.travelPolicyService = travelPolicyService;
    }
}
