package com.mangocity.btms.vo;

/**
 * 常旅卡
 * 
 * @author fuhoujun
 * 
 */
public class TravelCard {

    private long id;

    private String type;// 类型

    private String cardNo;// 常旅卡号

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
