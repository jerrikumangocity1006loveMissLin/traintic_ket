package com.mangocity.btms.adpater.vo;

import com.mangocity.btms.approval.model.ApprovalMan;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: fanliding
 * Date: 12-8-2
 * Time: 上午11:22
 * To change this template use File | Settings | File Templates.
 */
public class ApprovalManVO implements Serializable {

    /*
     * 会员
     */
    public static final String MEMBER = "member";

    /*
     * 乘机人
     */
    public static final String PASSENGER = "passenger";

    /*
     * 秘书
     */
    public static final String SECRETARY = "secretary";

    /*
     * 联系人
     */
    public static final String LINKMAN = "linkman";

    private ApprovalMan approvalMan;       //审批人的基本信息

    private String email;

    private String mobile;

    private String fax;

    private String identityType;    //TTOP中 用于识别 是审批人 还是 联系人

    private String name; // TTOP 当此VO用于联系人显示时，表示联系人姓名

    //add by tmc 3.31 自动发送短信
    private String affirm;//是否接收确认短信 Y/N
    private String affirmTemplet;//确认短信模板

    private String issut;//是否接收出票短信 Y/N
    private String issutTemplet;//出票短信模板

    private String approve_mail;//是否接收收审批邮件Y/N
    private String approvemailTemplet;//审批邮件模板
    private String approvemailType;//审批邮件类型content:正文 contentAndAttachment 正文加附件 attachment 附件

    private String trip_mail;//是否接收行程单邮件
    private String tripmailTemplet;//行程单邮件模板
    private String tripmailType;//行程单邮件类型content:正文 contentAndAttachment 正文加附件 attachment 附件

    private String sendMode;//邮件发送方式 zs=主送 cc=抄送


    private boolean valid=true;//是否为有效审批人
    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }


    public ApprovalMan getApprovalMan() {
        return approvalMan;
    }

    public void setApprovalMan(ApprovalMan approvalMan) {
        this.approvalMan = approvalMan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getIdentityType() {
        return identityType;
    }

    public String getIdentityTypeName() {
        if(identityType == null) return null;
        if(MEMBER.equals(identityType)){
            return "会员";
        }else if(LINKMAN.equals(identityType)){
            return "联系人";
        }else if(PASSENGER.equals(identityType)){
            return "乘机人";
        }else if(SECRETARY.equals(identityType)){
            return "秘书";
        }
        return null;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSendMode() {
        return sendMode;
    }

    public void setSendMode(String sendMode) {
        this.sendMode = sendMode;
    }

    public String getAffirm() {
        return affirm;
    }

    public void setAffirm(String affirm) {
        this.affirm = affirm;
    }

    public String getAffirmTemplet() {
        return affirmTemplet;
    }

    public void setAffirmTemplet(String affirmTemplet) {
        this.affirmTemplet = affirmTemplet;
    }

    public String getIssut() {
        return issut;
    }

    public void setIssut(String issut) {
        this.issut = issut;
    }

    public String getIssutTemplet() {
        return issutTemplet;
    }

    public void setIssutTemplet(String issutTemplet) {
        this.issutTemplet = issutTemplet;
    }

    public String getApprove_mail() {
        return approve_mail;
    }

    public void setApprove_mail(String approve_mail) {
        this.approve_mail = approve_mail;
    }

    public String getApprovemailTemplet() {
        return approvemailTemplet;
    }

    public void setApprovemailTemplet(String approvemailTemplet) {
        this.approvemailTemplet = approvemailTemplet;
    }

    public String getApprovemailType() {
        return approvemailType;
    }

    public void setApprovemailType(String approvemailType) {
        this.approvemailType = approvemailType;
    }

    public String getTrip_mail() {
        return trip_mail;
    }

    public void setTrip_mail(String trip_mail) {
        this.trip_mail = trip_mail;
    }

    public String getTripmailTemplet() {
        return tripmailTemplet;
    }

    public void setTripmailTemplet(String tripmailTemplet) {
        this.tripmailTemplet = tripmailTemplet;
    }

    public String getTripmailType() {
        return tripmailType;
    }

    public void setTripmailType(String tripmailType) {
        this.tripmailType = tripmailType;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof ApprovalManVO) {
            ApprovalManVO vo = (ApprovalManVO) obj;
            if (isEqual(this.getName(), vo.getName()) && isEqual(this.getFax(), vo.getFax())
                    && isEqual(this.getEmail(), vo.getEmail())
                    && isEqual(this.getMobile(), vo.getMobile())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean isEqual(String str1, String str2) {
        if (str1 == null) {
            str1 = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        return str1.equals(str2);
    }
}
