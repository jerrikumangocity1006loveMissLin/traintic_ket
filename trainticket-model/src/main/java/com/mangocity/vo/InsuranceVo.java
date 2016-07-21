package com.mangocity.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 保险
 * @author lanlonghui
 *
 */
public class InsuranceVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3651880752526095075L;
	
	private String insuranceTypeId;//保险类型ID
	private String insuranceName;//保险名称
	private BigDecimal salePrice;//销售价
	private String insuranceDesc;//保险说明
	private BigDecimal startPrice;//价格始
	private BigDecimal endPrice;//价格止
	private BigDecimal fee;//服务费
	private BigDecimal amount;//保险额度
	private Integer num;//保险份数
	public String getInsuranceTypeId() {
		return insuranceTypeId;
	}
	@JSONField(name="blx")
	public void setInsuranceTypeId(String insuranceTypeId) {
		this.insuranceTypeId = insuranceTypeId;
	}
	public String getInsuranceName() {
		return insuranceName;
	}
	@JSONField(name="bmc")
	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}
	public BigDecimal getSalePrice() {
		return salePrice;
	}
	@JSONField(name="xsj")
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	public String getInsuranceDesc() {
		return insuranceDesc;
	}
	@JSONField(name="bsm")
	public void setInsuranceDesc(String insuranceDesc) {
		this.insuranceDesc = insuranceDesc;
	}
	public BigDecimal getStartPrice() {
		return startPrice;
	}
	@JSONField(name="jgs")
	public void setStartPrice(BigDecimal startPrice) {
		this.startPrice = startPrice;
	}
	public BigDecimal getEndPrice() {
		return endPrice;
	}
	@JSONField(name="jgz")
	public void setEndPrice(BigDecimal endPrice) {
		this.endPrice = endPrice;
	}
	public BigDecimal getFee() {
		return fee;
	}
	@JSONField(name="fwf")
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	@JSONField(name="bed")
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Integer getNum() {
		return num;
	}
	@JSONField(name="bfs")
	public void setNum(Integer num) {
		this.num = num;
	}
	
}
