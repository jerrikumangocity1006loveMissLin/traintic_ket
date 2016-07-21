package com.mangocity.mapper;

import org.apache.ibatis.annotations.Param;

import com.mangocity.model.Passenger;

/**
 * @author lizhi
 *
 * @date 2016年5月30日
 */
public interface PassengerMapper extends BaseMapper<Passenger> {

	public Passenger findPassengerByzjhm(@Param("zjhm")String zjhm);
	
	public void updatePassengerByzjhm(Passenger passenger);
}
