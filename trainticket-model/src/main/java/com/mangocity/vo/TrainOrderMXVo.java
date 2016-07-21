package com.mangocity.vo;

import java.io.Serializable;

/**
 * @author lizhi
 *
 * @date 2016年6月1日
 */
public class TrainOrderMXVo implements Serializable {

	private static final long serialVersionUID = -7764869857745390791L;
	private String cxi;// 座位
	private String czr;// 乘坐人
	private String pid;// 乘坐人常旅客ID
	private String crl;// 乘坐人类型
	private String pj;// 票价
	private String tno;// 票号
	private String fee;// 代售服务费
	private String bxj;// 保险金额
	private String ysje;// 应收金额
	private String zjh;// 证件号码
	private String zjl;// 证件类型
	private String zwl;// 座位类型
	private String sgq;// 是否改签（1改签 其他未改签）
	private String stp;// 是否退票

	public String getCxi() {
		return cxi;
	}

	public void setCxi(String cxi) {
		this.cxi = cxi;
	}

	public String getCzr() {
		return czr;
	}

	public void setCzr(String czr) {
		this.czr = czr;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getCrl() {
		return crl;
	}

	public void setCrl(String crl) {
		this.crl = crl;
	}

	public String getPj() {
		return pj;
	}

	public void setPj(String pj) {
		this.pj = pj;
	}

	public String getTno() {
		return tno;
	}

	public void setTno(String tno) {
		this.tno = tno;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getBxj() {
		return bxj;
	}

	public void setBxj(String bxj) {
		this.bxj = bxj;
	}

	public String getYsje() {
		return ysje;
	}

	public void setYsje(String ysje) {
		this.ysje = ysje;
	}

	public String getZjh() {
		return zjh;
	}

	public void setZjh(String zjh) {
		this.zjh = zjh;
	}

	public String getZjl() {
		return zjl;
	}

	public void setZjl(String zjl) {
		this.zjl = zjl;
	}

	public String getZwl() {
		return zwl;
	}

	public void setZwl(String zwl) {
		this.zwl = zwl;
	}

	public String getSgq() {
		return sgq;
	}

	public void setSgq(String sgq) {
		this.sgq = sgq;
	}

	public String getStp() {
		return stp;
	}

	public void setStp(String stp) {
		this.stp = stp;
	}

}
