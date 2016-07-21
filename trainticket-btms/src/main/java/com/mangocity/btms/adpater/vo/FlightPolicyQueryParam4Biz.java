package com.mangocity.btms.adpater.vo;

import com.mangocity.gds.domain.entity.AvItem;

import java.util.List;

/**
 * Date: 12-2-21
 * Time: 下午3:46
 *
 * @since: the version
 */
public class FlightPolicyQueryParam4Biz{
    /**
     * 会籍CD
     */
    private List<AvItem> avItemList;
    private String shipCd;
    private CurrentOrderClassInfo currentClassInfo;
    /**
     * 是否首次点击，用于首点管控判断逻辑
     */
    private boolean isFirstClicking = true;

    private long recommendedPrice = -1;

    public FlightPolicyQueryParam4Biz(List<AvItem> avItemList, String shipCd, CurrentOrderClassInfo currentClassInfo) {
        this.avItemList = avItemList;
        this.shipCd = shipCd;
        this.currentClassInfo = currentClassInfo;
    }


    public List<AvItem> getAvItemList() {
        return avItemList;
    }

    public void setAvItemList(List<AvItem> avItemList) {
        this.avItemList = avItemList;
    }

    public String getShipCd() {
        return shipCd;
    }

    public void setShipCd(String shipCd) {
        this.shipCd = shipCd;
    }

    public CurrentOrderClassInfo getCurrentClassInfo() {
        return currentClassInfo;
    }

    public void setCurrentClassInfo(CurrentOrderClassInfo currentClassInfo) {
        this.currentClassInfo = currentClassInfo;
    }

    public boolean isFirstClicking() {
        return isFirstClicking;
    }

    public void setFirstClicking(boolean firstClicking) {
        isFirstClicking = firstClicking;
    }

    public long getRecommendedPrice() {
        return recommendedPrice;
    }

    public void setRecommendedPrice(long recommendedPrice) {
        this.recommendedPrice = recommendedPrice;
    }
}
