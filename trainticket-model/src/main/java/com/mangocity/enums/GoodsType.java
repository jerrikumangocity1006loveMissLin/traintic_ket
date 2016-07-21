/**
 * 
 */
package com.mangocity.enums;

/**
 * @author lizhi
 *
 */
public enum GoodsType {
	/**
	 * 火车票
	 */
	TICKET,
	/**
	 * 服务费
	 */
	CHARGE,
	/**
	 * 配送费
	 */
	DELIVERY;
	
	public static void main(String[] args){
	System.out.println(GoodsType.CHARGE.toString());	
	}
	

}
