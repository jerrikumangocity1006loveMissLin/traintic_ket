package com.mangocity.vo;

import java.io.Serializable;
import java.util.List;

public class PageQueryResult<T> implements Serializable {

	private static final long serialVersionUID = -2016979618597334247L;
	private List<T> entityList;
	private int totalNum;// 总记录条数
	private int pageNo;// 当前页数
	private int pageSize;

	public List<T> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<T> entityList) {
		this.entityList = entityList;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
