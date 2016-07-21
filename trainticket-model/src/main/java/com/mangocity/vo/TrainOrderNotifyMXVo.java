package com.mangocity.vo;

import java.io.Serializable;

public class TrainOrderNotifyMXVo implements Serializable {

	private static final long serialVersionUID = -599385671071977721L;
	private String cxin;// 座位
	private String czr;// 乘坐人
	private String czrlx;// 乘坐人类型：1成人票 2儿童票 4残军票
	private String tid;// 票号
	private String ysje;// 应收金额
	private String zjhm;// 证件号码
	private String zjlx;// 证件类型：1二代身份证 C港澳通行证 G台湾通行证 B护照
	private String zwlx;// 座位类型：9:商务座，P:特等座，M:一等座，O:二等座，6:高级软卧，4:软卧，3:硬卧，2:软座，1:硬座

	public String getCxin() {
		return cxin;
	}

	public void setCxin(String cxin) {
		this.cxin = cxin;
	}

	public String getCzr() {
		return czr;
	}

	public void setCzr(String czr) {
		this.czr = czr;
	}

	public String getCzrlx() {
		return czrlx;
	}

	public void setCzrlx(String czrlx) {
		this.czrlx = czrlx;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getYsje() {
		return ysje;
	}

	public void setYsje(String ysje) {
		this.ysje = ysje;
	}

	public String getZjhm() {
		return zjhm;
	}

	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}

	public String getZjlx() {
		return zjlx;
	}

	public void setZjlx(String zjlx) {
		this.zjlx = zjlx;
	}

	public String getZwlx() {
		return zwlx;
	}

	public void setZwlx(String zwlx) {
		this.zwlx = zwlx;
	}

}
