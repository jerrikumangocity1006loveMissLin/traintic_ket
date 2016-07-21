package com.mangocity.btms.adpater.service.handler.impl;

import com.mangocity.btms.adpater.service.handler.FlightTravelPolicyHandler;
import com.mangocity.btms.adpater.vo.CurrentOrderClassInfo;
import com.mangocity.btms.adpater.vo.FlightPolicyQueryParam4Biz;
import com.mangocity.btms.adpater.vo.OrderSuggestion;
import com.mangocity.btms.policy.model.TravelPolicy;
import com.mangocity.btms.policy.model.TravelPolicyData;
import com.mangocity.gds.domain.entity.AvItem;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 前后n小时最低价
 * Date: 12-2-22
 * Time: 上午10:25
 *
 * @since the version
 */
public class BeforeAndAfterLowestPriceHandler implements FlightTravelPolicyHandler {
    private static final String LOWEST_PRICE = "lowestPrice";
    /**
     * 实际点击的舱位价格与最低价舱位价格的差值
     */
    private static final String THRESHOLD = "threshold";
    /**
     * 是否首次点击管控
     */
    private static final String IS_FIRST_CLICK_CONTROL = "isFirstClickCtrl";
    /**
     * 不推荐航司
     */
    private static final String IGNORE_AIRWAYS = "exAirways";
    /**
     * 经停航班是否推荐
     */
    private static final String IGNORE_STOPOVER = "exStopover";
    /**
     * 与首次点击航班起始机场不一致的航班是否推荐
     */
    private static final String IGNORE_OTHER_AIRPORT = "ignoreOtherAirport";

    private static final Log logger = LogFactory.getLog(BeforeAndAfterLowestPriceHandler.class);
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public OrderSuggestion parsePolicy(TravelPolicy policy, FlightPolicyQueryParam4Biz queryParam) throws ParseException {
        logger.debug("得到前后n小时中最低价格,n為" + policy.getPolicyName());
        //1、得到前后n小时中最低价格
        long beforeAndAfterMinPrice;
        if (queryParam.isFirstClicking()) {//第一次点击"订票"按钮
            if ("Y".equalsIgnoreCase(getPolicyParamValueByParamName(policy, IS_FIRST_CLICK_CONTROL))) {//若配置了首点管控，则获取推荐航班价格
                logger.info("当前会员所在公司或者部门配置了首点管控");
                beforeAndAfterMinPrice = getRecommendedPrice(policy, queryParam);
            } else {//未配置首点管控，获取前后n小时内的最低价
                beforeAndAfterMinPrice = getBeforeAndAfterMinPrice(policy, queryParam);
            }
        } else {//第二次点击"订票"按钮，直接取第一次计算出来的值
            beforeAndAfterMinPrice = queryParam.getRecommendedPrice();
        }
        logger.info("得到前后n小时中最低价格" + beforeAndAfterMinPrice);
        if (-1 == beforeAndAfterMinPrice) {//未配置前后n小时政策
            logger.info("当前航班符合差旅政策");
            return null;
        }
        String threshold = getPolicyParamValueByParamName(policy, THRESHOLD);
        logger.info("公司配置的最低价差额是:" + threshold);
        long currentPrice = queryParam.getCurrentClassInfo().getPrice();
        if ((currentPrice - beforeAndAfterMinPrice) > Integer.parseInt(threshold)) {
            logger.info("当前选择的舱位价格违反了公司配置的前后n小时最低价差旅政策");
            OrderSuggestion orderSuggestion = new OrderSuggestion();
            orderSuggestion.setSuggestContinueOrder(true);
            orderSuggestion.setPolicyId(policy.getPolicyId());
            orderSuggestion.setPolicyName(policy.getPolicyName());

            if ("Y".equalsIgnoreCase(getPolicyParamValueByParamName(policy, IS_FIRST_CLICK_CONTROL))) {
                Map<String, String> travelPolicyParamValues = new HashMap<String, String>();
                travelPolicyParamValues.put(IS_FIRST_CLICK_CONTROL, getPolicyParamValueByParamName(policy, IS_FIRST_CLICK_CONTROL));
                travelPolicyParamValues.put(LOWEST_PRICE, getPolicyParamValueByParamName(policy, LOWEST_PRICE));
                travelPolicyParamValues.put(IGNORE_AIRWAYS, getPolicyParamValueByParamName(policy, IGNORE_AIRWAYS));
                travelPolicyParamValues.put(IGNORE_STOPOVER, getPolicyParamValueByParamName(policy, IGNORE_STOPOVER));
                travelPolicyParamValues.put(IGNORE_OTHER_AIRPORT, getPolicyParamValueByParamName(policy, IGNORE_OTHER_AIRPORT));
                orderSuggestion.setTravelPolicyParamValues(travelPolicyParamValues);
                orderSuggestion.setRecommendedPrice(beforeAndAfterMinPrice);
            }

//            orderSuggestion.setPolicyParamValue(getPolicyParamValueByParamName(policy, LOWEST_PRICE));
//            orderSuggestion.setFirstClickCtrl(getPolicyParamValueByParamName(policy, IS_FIRST_CLICK_CONTROL));
//            orderSuggestion.setIgnoreAirways(getPolicyParamValueByParamName(policy, IGNORE_AIRWAYS));
//            orderSuggestion.setIgnoreStopover(getPolicyParamValueByParamName(policy, IGNORE_STOPOVER));
//            orderSuggestion.setIgnoreOtherAirport(getPolicyParamValueByParamName(policy, IGNORE_OTHER_AIRPORT));

            orderSuggestion.setMessageType(OrderSuggestion.MessageType.REASON_MESSAGE);
            orderSuggestion.setMessageList(policy.getPolicyReasonCodeList());
            orderSuggestion.setDefId(policy.getPolicyDefinitionId());
            logger.debug("違反前后n小时中最低价格原因代码条数" + policy.getPolicyReasonCodeList().size());
            return orderSuggestion;
        }
        //2、遍历是否前后n小时的最低价信息
        return null;
    }

    /**
     * 根据政策参数名称获取对应的参数值，若未配置指定的参数名称，则返回字符串"undefined"
     *
     * @param policy    差旅政策
     * @param paramName 政策参数名称
     * @return 政策参数值，如果未配置，则返回字符串"undefined"
     */
    private String getPolicyParamValueByParamName(TravelPolicy policy, String paramName) {
        List<TravelPolicyData> policyDataList = policy.getPolicyDataList();
        for (TravelPolicyData policyData : policyDataList) {
            if (paramName.equalsIgnoreCase(policyData.getParamName())) {
                logger.info("参数名称" + paramName + "的政策参数值为：" + policyData.getParamValue());
                return policyData.getParamValue();
            }
        }
        return null;
    }

    /**
     * Added by zhangdeng,2013-08-28 14:02
     *
     * @param policy     差旅政策
     * @param queryParam 查询参数
     * @return 推荐价格
     * @throws ParseException
     */
    private long getRecommendedPrice(TravelPolicy policy, FlightPolicyQueryParam4Biz queryParam) throws ParseException {
        logger.info("开始计算经过预订前后n小时最低价差旅政策所有过滤条件后所得到的的推荐价格");
        //获取预订前后n小时内最低价航班政策参数值
        int beforeAndAfterHours = Integer.parseInt(getPolicyParamValueByParamName(policy, LOWEST_PRICE));
        CurrentOrderClassInfo currentClassInfo = queryParam.getCurrentClassInfo();
        Date currentDepartTime = currentClassInfo.getDepDateDetailTime();
        Date beginDepartTime = DateUtils.addHours(currentDepartTime, -beforeAndAfterHours);
        Date endDepartTime = DateUtils.addHours(currentDepartTime, beforeAndAfterHours);
        List<AvItem> flights = new ArrayList<AvItem>();
        for (AvItem avItem : queryParam.getAvItemList()) {//过滤前后n小时之外的航班
            Date departTime = SDF.parse(avItem.getDepDate() + " " + avItem.getDepTime().substring(0, 2) + ":" + avItem.getDepTime().substring(2));
            if (departTime.compareTo(beginDepartTime) >= 0 && departTime.compareTo(endDepartTime) <= 0) {
                flights.add(avItem);
            }
        }
        long recommendedPrice = -1;
        logger.info("当前点击航班的起始机场：" + currentClassInfo.getDepartAirport());
        logger.info("当前点击航班的抵达机场：" + currentClassInfo.getArriveAirport());
        logger.info("当前点击航班是否是经停：" + currentClassInfo.getStopover());
        if (flights.size() > 0) {
            recommendedPrice = flights.get(0).getLeastClassInfo().getPrice();
            String ignoreAirwaysStr = getPolicyParamValueByParamName(policy, IGNORE_AIRWAYS);
            String[] ignoreAirways = ignoreAirwaysStr != null && !"null".equals(ignoreAirwaysStr.trim()) && !"".equals(ignoreAirwaysStr.trim()) ? ignoreAirwaysStr.split("/") : new String[0];
            String ignoreStopover = getPolicyParamValueByParamName(policy, IGNORE_STOPOVER);
            String ignoreOtherAirport = getPolicyParamValueByParamName(policy, IGNORE_OTHER_AIRPORT);
            for (AvItem avItem : flights) {
                logger.debug("循环航班的起抵机场：" + avItem.getDepAirdrome() + "/" + avItem.getArrAirdrome());
                if (ignoreAirways.length > 0) {
                    boolean ignore = false;
                    for (String ignoreAirway : ignoreAirways) {
                        if (avItem.getAirways().equalsIgnoreCase(ignoreAirway)) {
                            ignore = true;
                            break;
                        }
                    }
                    if (ignore) {//当前航班属于免控航班，当前的价格不作考虑，即使为最低价
                        continue;
                    }
                    if ("N".equalsIgnoreCase(ignoreStopover)) {//经停航班可推荐
                        if ("N".equalsIgnoreCase(ignoreOtherAirport)) {//起始机场与首次点击航班不一致可推荐
                            if (recommendedPrice > avItem.getLeastClassInfo().getPrice()) {
                                recommendedPrice = avItem.getLeastClassInfo().getPrice();
                            }
                        } else {//起始机场需与首次点击航班一致方可推荐
                            if (currentClassInfo.getDepartAirport().equals(avItem.getDepAirdrome())
                                    && currentClassInfo.getArriveAirport().equals(avItem.getArrAirdrome())) {
                                if (recommendedPrice > avItem.getLeastClassInfo().getPrice()) {
                                    recommendedPrice = avItem.getLeastClassInfo().getPrice();
                                }
                            }
                        }
                    } else {//忽略经停航班
                        if ("0".equals(currentClassInfo.getStopover().trim())) {//当前航班不是经停航班
                            if ("N".equalsIgnoreCase(ignoreOtherAirport)) {//起始机场与首次点击航班不一致可推荐
                                if (recommendedPrice > avItem.getLeastClassInfo().getPrice()) {
                                    recommendedPrice = avItem.getLeastClassInfo().getPrice();
                                }
                            } else {//起始机场需与首次点击航班一致方可推荐
                                logger.debug("首点管控，经停免控，机场免控");
                                if (currentClassInfo.getDepartAirport().equals(avItem.getDepAirdrome())
                                        && currentClassInfo.getArriveAirport().equals(avItem.getArrAirdrome())) {
                                    logger.debug("起抵机场一致！");
                                    if (recommendedPrice > avItem.getLeastClassInfo().getPrice()) {
                                        recommendedPrice = avItem.getLeastClassInfo().getPrice();
                                    }
                                }
                            }
                        }
                    }
                } else {//没有配置需要忽略的航司
                    if ("N".equalsIgnoreCase(ignoreStopover)) {//经停航班可推荐
                        if ("N".equalsIgnoreCase(ignoreOtherAirport)) {//起始机场与首次点击航班不一致可推荐
                            if (recommendedPrice > avItem.getLeastClassInfo().getPrice()) {
                                recommendedPrice = avItem.getLeastClassInfo().getPrice();
                            }
                        } else {//起始机场需与首次点击航班一致方可推荐
                            if (currentClassInfo.getDepartAirport().equals(avItem.getDepAirdrome())
                                    && currentClassInfo.getArriveAirport().equals(avItem.getArrAirdrome())) {
                                if (recommendedPrice > avItem.getLeastClassInfo().getPrice()) {
                                    recommendedPrice = avItem.getLeastClassInfo().getPrice();
                                }
                            }
                        }
                    } else {//忽略经停航班
                        if ("0".equals(currentClassInfo.getStopover())) {//当前航班不是经停航班
                            if ("N".equalsIgnoreCase(ignoreOtherAirport)) {//起始机场与首次点击航班不一致可推荐
                                if (recommendedPrice > avItem.getLeastClassInfo().getPrice()) {
                                    recommendedPrice = avItem.getLeastClassInfo().getPrice();
                                }
                            } else {//起始机场需与首次点击航班一致方可推荐
                                if (currentClassInfo.getDepartAirport().equals(avItem.getDepAirdrome())
                                        && currentClassInfo.getArriveAirport().equals(avItem.getArrAirdrome())) {
                                    if (recommendedPrice > avItem.getLeastClassInfo().getPrice()) {
                                        recommendedPrice = avItem.getLeastClassInfo().getPrice();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        logger.info("经过预订前后n小时最低价差旅政策所有过滤条件后所得到的的推荐价格是：" + recommendedPrice);
        return recommendedPrice;
    }

    private long getBeforeAndAfterMinPrice(TravelPolicy policy, FlightPolicyQueryParam4Biz queryParam) throws ParseException {
        //1、得到前后几小时参数
        int beforeAndAfterHours = Integer.parseInt(getPolicyParamValueByParamName(policy, LOWEST_PRICE));
        Date date = queryParam.getCurrentClassInfo().getDepDateDetailTime();
        Date startTime = DateUtils.addHours(date, -beforeAndAfterHours);
        Date endTime = DateUtils.addHours(date, beforeAndAfterHours);
        List<Long> list = new ArrayList<Long>();
        for (AvItem avItem : queryParam.getAvItemList()) {
            String depTime = avItem.getDepDate() + " " + avItem.getDepTime().substring(0, 2) + ":" + avItem.getDepTime().substring(2);
            Date depTime4Date = SDF.parse(depTime);
            if (depTime4Date.compareTo(startTime) > 0 && depTime4Date.compareTo(endTime) < 0) {
                list.add(avItem.getLeastClassInfo().getPrice());
            }
        }
        Collections.sort(list, new Comparator<Long>() {
            public int compare(Long o1, Long o2) {
                return o1 <= o2 ? 0 : 1;
            }
        });
        return list.get(0);
    }
}
