package com.mangocity.btms.api;

import java.util.List;
import java.util.Map;

import com.mangocity.btms.vo.HistoryPassengerVO;
import com.mangocity.model.passenger.PassengerInfo;

/**
 * 乘机人信息服务类
 * 
 * @author hongxiaodong
 *
 */
public interface IPassengerManageService {
	
	
	/**
	 * 返回登录会员的常用历史乘机人MAP
	 * @param membercd
	 * @return
	 */
	public Map<HistoryPassengerVO, String> getHistoryPassengerMapByMemberCd(String membercd);
	
	/**
	 * "MAN" 为"ADT"
	 * "CHILD" 为 "CHD"
	 * "BABY" 为 "INF"
	 * 根据会员信息查询所有乘机人信息服务类
	 * @param membercd
	 * @return
	 */
	public List<PassengerInfo> getMemberPassengerByMemberCd(String membercd);
	
	/**
	 * 根据ID查询所有的乘机人信息
	 * @param paramLong
	 * @return
	 */
	public List<PassengerInfo> queryAllPassengerById(long paramLong);
	
	/**
	 * 所有有的乘机人信息
	 * @param paramString
	 * @return
	 */
	public List<PassengerInfo> queryAllPassengerByCd(String paramString);
	
	/**
	 * 乘机人信息
	 * @param paramLong
	 * @return
	 */
	public PassengerInfo getPassengerByPassId(long paramLong);
	
	/**
	 * 查询有的乘机人信息
	 * @param paramLong
	 * @param paramInt
	 * @return
	 */
	public List<PassengerInfo> queryAllPassengerById(long paramLong, int paramInt);
	
	/**
	 * 查询有的乘机人信息
	 * @param paramString
	 * @param paramInt
	 * @return
	 */
	public List<PassengerInfo> queryAllPassengerByCd(String paramString, int paramInt);

	/**
	 * 添加常旅客
	 * @IPassengerManageService
	 * @void
	 */
	public void addPassengerInfo(PassengerInfo passengerInfo) throws Exception;

}
