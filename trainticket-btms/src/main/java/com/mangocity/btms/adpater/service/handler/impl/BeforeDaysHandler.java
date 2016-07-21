package com.mangocity.btms.adpater.service.handler.impl;


import com.mangocity.btms.adpater.service.handler.FlightTravelPolicyHandler;
import com.mangocity.btms.adpater.vo.FlightPolicyQueryParam4Biz;
import com.mangocity.btms.adpater.vo.OrderSuggestion;
import com.mangocity.btms.policy.model.TravelPolicy;
import com.mangocity.btms.policy.model.TravelPolicyData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 提前n天处理
 * Date: 12-2-22
 * Time: 上午10:27
 *
 * @since: the version
 */
public class BeforeDaysHandler implements FlightTravelPolicyHandler {

    private static final Log logger = LogFactory.getLog(BeforeDaysHandler.class);

    public OrderSuggestion parsePolicy(TravelPolicy policy, FlightPolicyQueryParam4Biz queryParam) {
        int day = getParamValue(policy);
        logger.debug("得到提前n天,"+policy.getPolicyName());
        logger.debug("違反前后n小时中最低价格原因代码条数"+policy.getPolicyReasonCodeList().size());
        String selectDate = queryParam.getCurrentClassInfo().getDepDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(selectDate);
            String today = sdf.format(new Date());
            long quot = date.getTime() - sdf.parse(today).getTime();
            quot = quot / 1000 / 60 / 60 / 24;
            //得到建议
            OrderSuggestion orderSuggestion = new OrderSuggestion();
            logger.debug("得到提前n天时，应该提前的天数"+day+"实际提前天数"+quot);
            logger.debug(day >= quot+1);
            if(day >= quot+1){
                logger.debug("違反提前n天");
                orderSuggestion.setSuggestContinueOrder(true);
                orderSuggestion.setPolicyId(policy.getPolicyId());
                orderSuggestion.setPolicyName(policy.getPolicyName());
                orderSuggestion.setMessageType(OrderSuggestion.MessageType.REASON_MESSAGE);
                orderSuggestion.setMessageList(policy.getPolicyReasonCodeList());
                orderSuggestion.setDefId(policy.getPolicyDefinitionId());
                logger.debug("違反提前n天原因代码条数"+policy.getPolicyReasonCodeList().size());
                return orderSuggestion;
            }
        } catch (ParseException e) {
            logger.error("差旅政策提前N天匹配出错",e);
        }
        return null;
    }

    private int getParamValue(TravelPolicy policy) {
        TravelPolicyData policyData = policy.getPolicyDataList().get(0);
        return Integer.parseInt(policyData.getParamValue());
    }
}
