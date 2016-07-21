package com.mangocity.btms.adpater.service.handler.impl;


import com.mangocity.btms.adpater.service.handler.FlightTravelPolicyHandler;
import com.mangocity.btms.adpater.vo.CurrentOrderClassInfo;
import com.mangocity.btms.adpater.vo.FlightPolicyQueryParam4Biz;
import com.mangocity.btms.adpater.vo.OrderSuggestion;
import com.mangocity.btms.policy.model.TravelPolicy;
import com.mangocity.gds.domain.entity.AvItem;
import com.mangocity.gds.domain.entity.ClassInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * 当前航班最低价
 * Date: 12-2-22
 * Time: 上午10:26
 *
 * @since the version
 */
public class CurrentFlightLowestPriceHandler implements FlightTravelPolicyHandler {

    private static final Log logger = LogFactory.getLog(CurrentFlightLowestPriceHandler.class);

    public OrderSuggestion parsePolicy(TravelPolicy policy, FlightPolicyQueryParam4Biz queryParam) {
        logger.debug("得到当前航班最低价,"+policy.getPolicyName());
        logger.debug("違反前后n小时中最低价格原因代码条数"+policy.getPolicyReasonCodeList().size());
        CurrentOrderClassInfo currentOrderClassInfo = queryParam.getCurrentClassInfo();
        //获取当前航班所有仓位
        List<ClassInfo> classInfoList = currentFlightClassInfoList(queryParam.getAvItemList(),currentOrderClassInfo.getFlightNO());
        //获取所有仓位最低价
        long lowestPrice = currentFlightLowestPrice(classInfoList);
        logger.debug("得到当前航班最低价,"+lowestPrice+"预定航班价格"+currentOrderClassInfo.getPrice());
        logger.debug(lowestPrice < currentOrderClassInfo.getPrice());
        if (lowestPrice < currentOrderClassInfo.getPrice()) {
            logger.debug("違反当前航班最低价");
            OrderSuggestion orderSuggestion = new OrderSuggestion();
            orderSuggestion.setSuggestContinueOrder(false);
            orderSuggestion.setPolicyId(policy.getPolicyId());
            orderSuggestion.setPolicyName(policy.getPolicyName());
            orderSuggestion.setMessageType(OrderSuggestion.MessageType.ALERT_MESSAGE);
            orderSuggestion.setMessageList(policy.getPolicyReasonCodeList());
            orderSuggestion.setDefId(policy.getPolicyDefinitionId());
            logger.debug("違反当前航班最低价原因代码条数"+policy.getPolicyReasonCodeList().size());
            return orderSuggestion;
        }
        return null;
    }

    private List<ClassInfo> currentFlightClassInfoList(List<AvItem> avItemList,String flightNo) {
        if(avItemList == null || avItemList.isEmpty()) return null;
        List<ClassInfo> classInfoList = null;
        for (AvItem avItem : avItemList) {
            if (avItem.getFlightNo().equals(flightNo)) {
                classInfoList = avItem.getClassInfoList();
                break;
            }
        }
        return classInfoList;
    }

    private long currentFlightLowestPrice(List<ClassInfo> classInfoList){
        if(classInfoList == null || classInfoList.isEmpty()) return 0;
        long lowestPrice = classInfoList.get(0).getPrice();
        if(classInfoList.get(0) != null)   lowestPrice = classInfoList.get(0).getPrice();
        for(ClassInfo classInfo:classInfoList){
            if(classInfo.getPrice()<lowestPrice)
                lowestPrice = classInfo.getPrice();
        }
        return lowestPrice;
    }

}
