package com.mangocity.btms.adpater.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Date: 12-2-21
 * Time: 下午3:30
 *
 * @since 1.0
 */
public class OrderSuggestion<T> implements Serializable{
    public enum MessageType{
        /**提示信息**/
        ALERT_MESSAGE,
        /**原因代码**/
        REASON_MESSAGE
    }
    /**
     * 建议是否继续预定
     */
    private boolean suggestContinueOrder;
    /**
     * 消息类型，原因代码还是提示信息
     */
    private MessageType messageType;

    public List<T> messageList = new ArrayList<T>();

    /**
     * 政策定义ID
     */
    private String defId;

    /**政策ID**/
    private long policyId;

    /**政策名称**/
    private String policyName;

    /**
     * 政策参数值
     */
    private String policyParamValue;
    /**
     * 是否需要首点管控
     */
    private String isFirstClickCtrl = "N";
    /**
     * 不作推荐的航司，如:HO/KN，以"/"分隔
     */
    private String ignoreAirways = "";
    /**
     * 经停航班是否推荐，Y表示不推荐，N表示推荐
     */
    private String ignoreStopover = "N";
    /**
     * 与首次点击的航班起始机场不一致的航班是否推荐，Y表示不推荐，N表示推荐
     */
    private String ignoreOtherAirport = "N";

    private long recommendedPrice;
    /**
     * 政策参数值Map
     */
    private Map<String,String> travelPolicyParamValues = new HashMap<String, String>();


    public String getPolicyParamValue() {
        return policyParamValue;
    }

    public void setPolicyParamValue(String policyParamValue) {
        this.policyParamValue = policyParamValue;
    }

    public boolean isSuggestContinueOrder() {
        return suggestContinueOrder;
    }

    public void setSuggestContinueOrder(boolean suggestContinueOrder) {
        this.suggestContinueOrder = suggestContinueOrder;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public List<T> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<T> messageList) {
        this.messageList = messageList;
    }

    public long getPolicyId() {
        return policyId;
    }

    public void setPolicyId(long policyId) {
        this.policyId = policyId;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public String getDefId() {
        return defId;
    }

    public void setDefId(String defId) {
        this.defId = defId;
    }

    public String getFirstClickCtrl() {
        return isFirstClickCtrl;
    }

    public void setFirstClickCtrl(String firstClickCtrl) {
        isFirstClickCtrl = null != firstClickCtrl ? firstClickCtrl : "N";
    }

    public String getIgnoreAirways() {
        return ignoreAirways;
    }

    public void setIgnoreAirways(String ignoreAirways) {
        this.ignoreAirways = null != ignoreAirways && !"null".equals(ignoreAirways) ? ignoreAirways : "";
    }

    public String getIgnoreStopover() {
        return ignoreStopover;
    }

    public void setIgnoreStopover(String ignoreStopover) {
        this.ignoreStopover = null != ignoreStopover?ignoreStopover:"N";
    }

    public String getIgnoreOtherAirport() {
        return ignoreOtherAirport;
    }

    public void setIgnoreOtherAirport(String ignoreOtherAirport) {
        this.ignoreOtherAirport = null != ignoreOtherAirport ? ignoreOtherAirport : "N";
    }

    public Map<String, String> getTravelPolicyParamValues() {
        return travelPolicyParamValues;
    }

    public void setTravelPolicyParamValues(Map<String, String> travelPolicyParamValues) {
        this.travelPolicyParamValues = travelPolicyParamValues != null ? travelPolicyParamValues : new HashMap<String, String>();
    }

    public long getRecommendedPrice() {
        return recommendedPrice;
    }

    public void setRecommendedPrice(long recommendedPrice) {
        this.recommendedPrice = recommendedPrice;
    }
}
