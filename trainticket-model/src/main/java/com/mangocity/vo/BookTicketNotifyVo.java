package com.mangocity.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author lizhi
 *
 * @date 2016年6月1日
 */
public class BookTicketNotifyVo implements Serializable {

	private static final long serialVersionUID = -8027850929922898409L;
	private String cch;// 车次号
	private String cps_ddbh;// CPS订单编号
	private String ddbh;// 订单编号
	private String ddrq;// 到达日期
	private String ddsj;// 到达时间
	private String ddz;// 到达站
	private String ddzmc;// 到达站名称
	private String ddzt;// 订单状态
	private String jy_sxf;// 手续费
	private String lx;// 类型
	private String lxr;// 联系人
	private String lxr_mobile;// 联系人手机
	private String pj;// 票价
	private String sfrq;// 始发日期
	private String sfsj;// 始发时间
	private String sfz;// 始发站
	private String sfzmc;// 始发站名称
	private String transactionid;// 票号
	private String zf_sxf;// 支付手续费
	private String zs;
	private String orderNumber;
	private List<TrainOrderNotifyMXVo> trainOrderMx;

	public String getCch() {
		return cch;
	}

	public void setCch(String cch) {
		this.cch = cch;
	}

	public String getCps_ddbh() {
		return cps_ddbh;
	}

	public void setCps_ddbh(String cps_ddbh) {
		this.cps_ddbh = cps_ddbh;
	}

	public String getDdbh() {
		return ddbh;
	}

	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}

	public String getDdrq() {
		return ddrq;
	}

	public void setDdrq(String ddrq) {
		this.ddrq = ddrq;
	}

	public String getDdsj() {
		return ddsj;
	}

	public void setDdsj(String ddsj) {
		this.ddsj = ddsj;
	}

	public String getDdz() {
		return ddz;
	}

	public void setDdz(String ddz) {
		this.ddz = ddz;
	}

	public String getDdzmc() {
		return ddzmc;
	}

	public void setDdzmc(String ddzmc) {
		this.ddzmc = ddzmc;
	}

	public String getDdzt() {
		return ddzt;
	}

	public void setDdzt(String ddzt) {
		this.ddzt = ddzt;
	}

	public String getJy_sxf() {
		return jy_sxf;
	}

	public void setJy_sxf(String jy_sxf) {
		this.jy_sxf = jy_sxf;
	}

	public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}

	public String getLxr() {
		return lxr;
	}

	public void setLxr(String lxr) {
		this.lxr = lxr;
	}

	public String getLxr_mobile() {
		return lxr_mobile;
	}

	public void setLxr_mobile(String lxr_mobile) {
		this.lxr_mobile = lxr_mobile;
	}

	public String getPj() {
		return pj;
	}

	public void setPj(String pj) {
		this.pj = pj;
	}

	public String getSfrq() {
		return sfrq;
	}

	public void setSfrq(String sfrq) {
		this.sfrq = sfrq;
	}

	public String getSfsj() {
		return sfsj;
	}

	public void setSfsj(String sfsj) {
		this.sfsj = sfsj;
	}

	public String getSfz() {
		return sfz;
	}

	public void setSfz(String sfz) {
		this.sfz = sfz;
	}

	public String getSfzmc() {
		return sfzmc;
	}

	public void setSfzmc(String sfzmc) {
		this.sfzmc = sfzmc;
	}

	public String getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}

	public String getZf_sxf() {
		return zf_sxf;
	}

	public void setZf_sxf(String zf_sxf) {
		this.zf_sxf = zf_sxf;
	}

	public String getZs() {
		return zs;
	}

	public void setZs(String zs) {
		this.zs = zs;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public List<TrainOrderNotifyMXVo> getTrainOrderMx() {
		return trainOrderMx;
	}

	public void setTrainOrderMx(List<TrainOrderNotifyMXVo> trainOrderMx) {
		this.trainOrderMx = trainOrderMx;
	}

}
