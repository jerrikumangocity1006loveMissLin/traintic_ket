package com.mangocity.btms.adpater.vo;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: fanliding
 * Date: 12-8-6
 * Time: 上午11:14
 *
 */
public class ApprovalRecordQueryDto implements Serializable {

    private String membercd;

    private String approvalStatus;

    private String orderCd;

    // 查询的审批时间段
    private String fromDate;

    private String toDate;

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

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getOrderCd() {
        return orderCd;
    }

    public void setOrderCd(String orderCd) {
        this.orderCd = orderCd;
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
        return startRecordNum;
    }

    public void setStartRecordNum(int startRecordNum) {
        this.startRecordNum = startRecordNum;
    }

    public int getEndRecordNum() {
        return endRecordNum;
    }

    public void setEndRecordNum(int endRecordNum) {
        this.endRecordNum = endRecordNum;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }
}
