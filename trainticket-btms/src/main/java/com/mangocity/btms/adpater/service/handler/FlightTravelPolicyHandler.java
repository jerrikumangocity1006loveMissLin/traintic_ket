/**
 * Copyright MangoCity Limited (c) 2011. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of MangoCity Limited.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from MangoCity or an authorized sublicensor.
 */
package com.mangocity.btms.adpater.service.handler;

import com.mangocity.btms.adpater.vo.FlightPolicyQueryParam4Biz;
import com.mangocity.btms.adpater.vo.OrderSuggestion;
import com.mangocity.btms.policy.model.TravelPolicy;

import java.text.ParseException;


/**
 * Date: 11-12-30
 * Time: 上午9:53
 * 主要用于政策的处理
 * @since 1.0
 */
public interface FlightTravelPolicyHandler {
    /**
     * 预定处理政策
     * @param policy
     * @param queryParam
     * @return
     */
    public OrderSuggestion parsePolicy(TravelPolicy policy, FlightPolicyQueryParam4Biz queryParam) throws  ParseException;

}
