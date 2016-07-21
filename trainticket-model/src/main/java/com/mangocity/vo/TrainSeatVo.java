package com.mangocity.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 座位类型信息
 * @author lanlonghui
 *
 */

public class TrainSeatVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5203091814254292157L;
	
	private String seatCode;//座位编码。与座位名称对应关系：9:商务座，P:特等座，M:一等座，O:二等座，6:高级软卧，4:软卧，3:硬卧，2:软座，1:硬座注意：当最低的一种座位，无票时，购买选择该座位种类，买下的就是无座(也就说买无座的席别编码就是该车次的最低席别的编码)，另外，当最低席别的票卖完了的时候才可以卖无座的票。
	private String seatName;//座位类型名称
	private String num;//余票数量
	private BigDecimal price;//票价
	private String violationItem;//违背事项（预留）
	private String violation;//违背标准说明（预留）
	private String orderNumber;//座位类型展示顺序号， 排序规则为：首先按价格由低到高，无价格的不显示，有座位的排在前面，无座位的排后面；如果最低席别还有座位就不展示无座的
	public String getSeatCode() {
		return seatCode;
	}
	@JSONField(name="zwc")
	public void setSeatCode(String seatCode) {
		this.seatCode = seatCode;
	}
	public String getSeatName() {
		return seatName;
	}
	@JSONField(name="zlm")
	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}
	public String getNum() {
		return num;
	}
	@JSONField(name="gwn")
	public void setNum(String num) {
		this.num = num;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	@JSONField(name="gwp")
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getViolationItem() {
		return violationItem;
	}
	@JSONField(name="vtm")
	public void setViolationItem(String violationItem) {
		this.violationItem = violationItem;
	}
	public String getViolation() {
		return violation;
	}
	@JSONField(name="vio")
	public void setViolation(String violation) {
		this.violation = violation;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	@JSONField(name="ord")
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	
}
