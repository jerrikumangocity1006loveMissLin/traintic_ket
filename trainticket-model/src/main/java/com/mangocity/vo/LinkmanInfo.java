package com.mangocity.vo;

import java.util.Date;


/**
 * 联系人
 * @author hongxiaodong
 *
 */
public class LinkmanInfo implements java.io.Serializable {

    // Fields

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String name;

    private String mobile;

    private String hideMobile;//隐藏手机号

    private String tele;

    private String email;

    private String fax;

    private String confirmtype;

    private String confirmdetail;

    private String logid;

    private Date logtime;
    
    //海尔领导人手机号码
    private String leaderMobile;
    
    //海尔领导人邮箱
    private String leaderMail;

    public String getLogid() {
        return logid;
    }

    public void setLogid(String logid) {
        this.logid = logid;
    }

    public Date getLogtime() {
        return logtime;
    }

    public void setLogtime(Date logtime) {
        this.logtime = logtime;
    }

    // Constructors

    /** default constructor */
    public LinkmanInfo() {
    }

    // Property accessors

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTele() {
        return this.tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getConfirmtype() {
        return this.confirmtype;
    }

    public void setConfirmtype(String confirmtype) {
        this.confirmtype = confirmtype;
    }

    public String getConfirmdetail() {
        return this.confirmdetail;
    }

    public void setConfirmdetail(String confirmdetail) {
        this.confirmdetail = confirmdetail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getLeaderMobile() {
		return leaderMobile;
	}

	public void setLeaderMobile(String leaderMobile) {
		this.leaderMobile = leaderMobile;
	}

	public String getLeaderMail() {
		return leaderMail;
	}

	public void setLeaderMail(String leaderMail) {
		this.leaderMail = leaderMail;
	}

    public String getHideMobile() {
        return hideMobile;
    }

    public void setHideMobile(String hideMobile) {
        this.hideMobile = hideMobile;
    }

	@Override
	public String toString() {
		return "LinkmanInfo [id=" + id + ", name=" + name + ", mobile=" + mobile + ", hideMobile=" + hideMobile
				+ ", tele=" + tele + ", email=" + email + ", fax=" + fax + ", confirmtype=" + confirmtype
				+ ", confirmdetail=" + confirmdetail + ", logid=" + logid + ", logtime=" + logtime + ", leaderMobile="
				+ leaderMobile + ", leaderMail=" + leaderMail + "]";
	}
    
    
}