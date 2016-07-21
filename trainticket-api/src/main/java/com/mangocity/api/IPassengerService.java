package com.mangocity.api;

import com.mangocity.base.IBaseService;
import com.mangocity.model.Passenger;

/**
 * @author lizhi
 *
 * @date 2016年5月30日
 */
public interface IPassengerService extends IBaseService<Passenger> {
	public Passenger findPassengerByzjhm(String zjhm);// 通过证件号码获得乘客信息
}
