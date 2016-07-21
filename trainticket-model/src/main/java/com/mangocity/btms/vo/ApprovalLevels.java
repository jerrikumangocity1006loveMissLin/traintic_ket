package com.mangocity.btms.vo;

import java.util.*;

/**
 * 审批级别
 * 
 * @author suxiaoming
 * 
 */
public class ApprovalLevels {

    public static String FIRST_LEVEL = "auditF";

    public static String SECOND_LEVEL = "auditS";

    public static String THIRD_LEVEL = "auditT";
    
    public static String FOUR_LEVEL = "audit4";
    
    public static String ADMIN_LEVEL="auditAdmin";
    
    public static String MEMBER_LEVEL = "member";

    public static String PASSENGER_LEVEL = "passenger";
    
    /*
     * 联系人
     */
    public static final String LINKMAN = "linkman";
    /*
     * 秘书
     */
    public static final String SECRETARY = "secretary";


    public static Map approvalLevelsMap = new HashMap();

    static {
    	approvalLevelsMap.put(FOUR_LEVEL, "项目审批人");
        approvalLevelsMap.put(FIRST_LEVEL, "一级审批人");
        approvalLevelsMap.put(SECOND_LEVEL, "二级审批人");
        approvalLevelsMap.put(THIRD_LEVEL, "三级审批人");

        approvalLevelsMap.put(MEMBER_LEVEL, "商旅会员");
        approvalLevelsMap.put(PASSENGER_LEVEL, "预定乘机人");
        approvalLevelsMap.put(SECRETARY, "秘书");
        approvalLevelsMap.put(LINKMAN, "联系人");

    }

}
