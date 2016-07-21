package com.mangocity.vo;

import java.io.Serializable;

/**
 * @author lizhi
 *
 * @date 2016年6月2日
 */
public class OrderDetailBasic implements Serializable {

	private static final long serialVersionUID = 6641784295235142472L;
	private String ord;// 订单编号
	private String pj;// 票价
	private String fee;// 代售服务费
	private String tno;// 票张数
	private String cch;// 车次
	private String sfr;// 始发日期
	private String sfs;// 始发时间
	private String sfz;// 始发站编码
	private String ddr;// 到达日期
	private String dds;// 到达时间
	private String ddz;// 到达站编码
	private String sfm;// 始发名称
	private String ddm;// 到达站名称
	private String czr;// 乘坐人
	private String fkf;// 付款否: 1已付，0未付
	private String fkc;// 付款科目名称
	private String fsj;// 付款时间
	private String sgq;// 是否改签（1改签 其他未改签
	private String kts;// 可以退票票数
	private String dzt;// 订单状态
	private String btm;// 预订时间
	private String bxj;// 保险金额
	private String snb;// 已送审次数

	public String getOrd() {
		return ord;
	}

	public void setOrd(String ord) {
		this.ord = ord;
	}

	public String getPj() {
		return pj;
	}

	public void setPj(String pj) {
		this.pj = pj;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getTno() {
		return tno;
	}

	public void setTno(String tno) {
		this.tno = tno;
	}

	public String getCch() {
		return cch;
	}

	public void setCch(String cch) {
		this.cch = cch;
	}

	public String getSfr() {
		return sfr;
	}

	public void setSfr(String sfr) {
		this.sfr = sfr;
	}

	public String getSfs() {
		return sfs;
	}

	public void setSfs(String sfs) {
		this.sfs = sfs;
	}

	public String getSfz() {
		return sfz;
	}

	public void setSfz(String sfz) {
		this.sfz = sfz;
	}

	public String getDdr() {
		return ddr;
	}

	public void setDdr(String ddr) {
		this.ddr = ddr;
	}

	public String getDds() {
		return dds;
	}

	public void setDds(String dds) {
		this.dds = dds;
	}

	public String getDdz() {
		return ddz;
	}

	public void setDdz(String ddz) {
		this.ddz = ddz;
	}

	public String getSfm() {
		return sfm;
	}

	public void setSfm(String sfm) {
		this.sfm = sfm;
	}

	public String getDdm() {
		return ddm;
	}

	public void setDdm(String ddm) {
		this.ddm = ddm;
	}

	public String getCzr() {
		return czr;
	}

	public void setCzr(String czr) {
		this.czr = czr;
	}

	public String getFkf() {
		return fkf;
	}

	public void setFkf(String fkf) {
		this.fkf = fkf;
	}

	public String getFkc() {
		return fkc;
	}

	public void setFkc(String fkc) {
		this.fkc = fkc;
	}

	public String getFsj() {
		return fsj;
	}

	public void setFsj(String fsj) {
		this.fsj = fsj;
	}

	public String getSgq() {
		return sgq;
	}

	public void setSgq(String sgq) {
		this.sgq = sgq;
	}

	public String getKts() {
		return kts;
	}

	public void setKts(String kts) {
		this.kts = kts;
	}

	public String getDzt() {
		return dzt;
	}

	public void setDzt(String dzt) {
		this.dzt = dzt;
	}

	public String getBtm() {
		return btm;
	}

	public void setBtm(String btm) {
		this.btm = btm;
	}

	public String getBxj() {
		return bxj;
	}

	public void setBxj(String bxj) {
		this.bxj = bxj;
	}

	public String getSnb() {
		return snb;
	}

	public void setSnb(String snb) {
		this.snb = snb;
	}

}
