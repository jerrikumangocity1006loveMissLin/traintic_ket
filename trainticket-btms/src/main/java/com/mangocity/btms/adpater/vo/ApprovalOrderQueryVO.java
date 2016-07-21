package com.mangocity.btms.adpater.vo;

import java.util.List;

public class ApprovalOrderQueryVO {
	//当前登录会员编号
	private String membercd;
	//审批开始时间
	private String fromDate;
	//审批结束时间
	private String toDate;
	//订单号
	private String orderCd;
	
	//订单审批状态
	private String approvalStatus;
	
	private long corporationId;
	
	private boolean adminQueryFlag;//是否是管理员查询
	
	//要查询的订单编号列表，分页查询时使用
	private List<String> ordercdList;
	
	private String status;
	private String tmcTravelBusiness;
	private String IsHaier;

    // 每页多少条记录. 直接从page对象拿相应的值
    private int pageSize = 10;

    // 第几页. 直接从page对象拿相应的值
    private int startIndex = 1;

    // 根据pageSize和startIndex自动计算出查询数据库,从哪条记录开始
    private int startRecordNum = 1;

    // 根据pageSize和startIndex自动计算出查询数据库,到哪条记录结束
    private int endRecordNum = 10;

    private int totalPages=1;

    //总记录条数
    private int totalRecords=0;

    public String getMembercd() {
        return membercd;
    }

    public void setMembercd(String membercd) {
        this.membercd = membercd;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getOrderCd() {
        return orderCd;
    }

    public void setOrderCd(String orderCd) {
        this.orderCd = orderCd;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public long getCorporationId() {
        return corporationId;
    }

    public void setCorporationId(long corporationId) {
        this.corporationId = corporationId;
    }

    public boolean isAdminQueryFlag() {
        return adminQueryFlag;
    }

    public void setAdminQueryFlag(boolean adminQueryFlag) {
        this.adminQueryFlag = adminQueryFlag;
    }

    public List<String> getOrdercdList() {
        return ordercdList;
    }

    public void setOrdercdList(List<String> ordercdList) {
        this.ordercdList = ordercdList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTmcTravelBusiness() {
        return tmcTravelBusiness;
    }

    public void setTmcTravelBusiness(String tmcTravelBusiness) {
        this.tmcTravelBusiness = tmcTravelBusiness;
    }

    public String getIsHaier() {
        return IsHaier;
    }

    public void setIsHaier(String isHaier) {
        IsHaier = isHaier;
    }

    public int getEndRecordNum() {
		int endRecordNum = getStartIndex() * getPageSize();
		return endRecordNum;
	}

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getStartRecordNum() {
        startRecordNum = (startIndex - 1) * pageSize + 1;
        return startRecordNum;
    }

    public int getHibernateStartRecordNum() {
        startRecordNum = (startIndex - 1) * pageSize;
        return startRecordNum;
    }

    public void setStartRecordNum(int startRecordNum) {
        this.startRecordNum = startRecordNum;
    }

    public void setEndRecordNum(int endRecordNum) {
        this.endRecordNum = endRecordNum;
    }

    public int getTotalPages() {
        return (totalRecords+pageSize - 1)/pageSize;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }
}
