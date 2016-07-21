package com.mangocity.btms.vo;

/**
 * 证件VO
 * 
 * @author fuhoujun
 * 
 */
public class CertVO {

    private long id;

    private String type;// 证件类型

    private String certNo;// 证件号

    private String expiryDate;// 证件有效期

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
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
