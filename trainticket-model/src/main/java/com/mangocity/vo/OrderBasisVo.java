package com.mangocity.vo;

import java.io.Serializable;

import com.mangocity.model.Order;

/**
 * 订单基本信息
 * 
 * @author hongxiaodong
 *
 */
public class OrderBasisVo extends Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2997676210540094235L;

	private String payModel;// 支付方式

	private String paymetStatus;// 支付状态

	private String ccrq;// 乘车日期

	private String ccsj;//
	private String origStationName;
	private String destStationName;
	
	private Long orderItemId;

	public OrderBasisVo() {
		super();
	}

	public String getPayModel() {
		return payModel;
	}

	public void setPayModel(String payModel) {
		this.payModel = payModel;
	}

	public String getPaymetStatus() {
		return paymetStatus;
	}

	public void setPaymetStatus(String paymetStatus) {
		this.paymetStatus = paymetStatus;
	}

	public String getCcrq() {
		return ccrq;
	}

	public void setCcrq(String ccrq) {
		this.ccrq = ccrq;
	}

	public String getCcsj() {
		return ccsj;
	}

	public void setCcsj(String ccsj) {
		this.ccsj = ccsj;
	}

	public String getOrigStationName() {
		return origStationName;
	}

	public void setOrigStationName(String origStationName) {
		this.origStationName = origStationName;
	}

	public String getDestStationName() {
		return destStationName;
	}

	public void setDestStationName(String destStationName) {
		this.destStationName = destStationName;
	}

	public Long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}

	@Override
	public String toString() {
		return "OrderBasisVo [payModel=" + payModel + ", paymetStatus=" + paymetStatus + ", ccrq=" + ccrq + ", ccsj="
				+ ccsj + ", origStationName=" + origStationName + ", destStationName=" + destStationName + "]";
	}

}
