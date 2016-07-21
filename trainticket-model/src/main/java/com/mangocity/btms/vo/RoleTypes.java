package com.mangocity.btms.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息接收者 类型
 * 
 * @author suxiaoming
 * 
 */
public class RoleTypes {
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
    
    /**
     * 审批人
     */
    public static final String AUDITMAN="auditman";
    
    /**
     * 订单审核的一级审批人 add by yingnan.zeng 2011.3.4
     */
    public static final String FIRST_LEVEL_AUDIT_MAN=ApprovalLevels.FIRST_LEVEL;

    public static final Map ROLETYPES = new HashMap();

    static {
        ROLETYPES.put(MEMBER, "会员");
        ROLETYPES.put(PASSENGER, "乘机人");
        ROLETYPES.put(SECRETARY, "秘书");
        ROLETYPES.put(LINKMAN, "联系人");
        ROLETYPES.put(AUDITMAN, "审批人");
        ROLETYPES.put(FIRST_LEVEL_AUDIT_MAN,"一级审批人");
    }

}
