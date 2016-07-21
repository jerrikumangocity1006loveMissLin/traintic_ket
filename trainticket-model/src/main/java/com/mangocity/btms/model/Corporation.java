/*
 * Copyright MangoCity Limited (c) 2011. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of MangoCity Limited.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from MangoCity or an authorized sublicensor.
 */
package com.mangocity.btms.model;


import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

/**
 * Date: 11-9-20
 * Time: 上午9:27
 *
 * @since 1.0
 */
public class Corporation implements Serializable {
  
	private static final long serialVersionUID = 4890554126737749441L;


    private long corporationId;
    private String corporationSn;
    /**
     * Chinese name of corporation
     * The corporation's english name and chinese name,only one must be chose.
     */
    private String zhName;
    /**
     * English name of corporation
     */
    private String enName;
    /**
     * Number of code,mainly used for exterior line incoming call
     */
    private String corporationNum;
    /**
     * Three character code of corporation agency,
     * Such as the Agricultural Bank of China is ABC,
     * It can't be null,
     * The only and may not be repeated in all the system
     */
    private String corporationCode;
    /**
     * Corporation representative
     */
    private String corporation;
    /**
     * The name of general manager
     */
    private String generalMgrName;
    /**
     * The webSite of corporation
     */
    private String webSite;
    /**
     * The logo url of corporation
     */
    private String logoUrl;
    /**
     * Registered capital
     */
    private String regCapital;
    /**
     * Currency of registered capital
     */
    private String currency;
    /**
     * Certificate type of corporation
     */
    private String certType;
    /**
     * Certificate number of corporation
     */
    private String certNO;
    /**
     * The type of corporation,
     * It may be limited liability company (wholly State-owned) , collective - owned ,
     * individual - owned and privately - owned
     */
    private String corporationType;
    private String remark;
    /**
     * Code of industry of corporation
     */
    private String industryCode;

    /**
     * The contact of corporation
     */
    private String contacts;

    public long getCorporationId() {
        return corporationId;
    }

    public void setCorporationId(long corporationId) {
        this.corporationId = corporationId;
    }

    public String getCorporationSn() {
        return corporationSn;
    }

    public void setCorporationSn(String corporationSn) {
        this.corporationSn = corporationSn;
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getCorporationNum() {
        return corporationNum;
    }

    public void setCorporationNum(String corporationNum) {
        this.corporationNum = corporationNum;
    }

    public String getCorporationCode() {
        return corporationCode;
    }

    public void setCorporationCode(String corporationCode) {
        this.corporationCode = corporationCode;
    }

    public String getCorporation() {
        return corporation;
    }

    public void setCorporation(String corporation) {
        this.corporation = corporation;
    }

    public String getGeneralMgrName() {
        return generalMgrName;
    }

    public void setGeneralMgrName(String generalMgrName) {
        this.generalMgrName = generalMgrName;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(String regCapital) {
        this.regCapital = regCapital;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getCertNO() {
        return certNO;
    }

    public void setCertNO(String certNO) {
        this.certNO = certNO;
    }

    public String getCorporationType() {
        return corporationType;
    }

    public void setCorporationType(String corporationType) {
        this.corporationType = corporationType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIndustryCode() {
        return industryCode;
    }

    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }


    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    /**
     * 判断法人机构中英文是否合法，
     * @return 合法则返回true,不合法返回false
     */
    public boolean validateName(){
        return !(StringUtils.isEmpty(zhName) && StringUtils.isEmpty(enName));
    }
    
    @Override
  	public String toString() {
  		return "Corporation [corporationId=" + corporationId + ", corporationSn=" + corporationSn + ", zhName=" + zhName
  				+ ", enName=" + enName + ", corporationNum=" + corporationNum + ", corporationCode=" + corporationCode
  				+ ", corporation=" + corporation + ", generalMgrName=" + generalMgrName + ", webSite=" + webSite
  				+ ", logoUrl=" + logoUrl + ", regCapital=" + regCapital + ", currency=" + currency + ", certType="
  				+ certType + ", certNO=" + certNO + ", corporationType=" + corporationType + ", remark=" + remark
  				+ ", industryCode=" + industryCode + ", contacts=" + contacts + "]";
  	}

}
