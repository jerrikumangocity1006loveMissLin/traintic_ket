package com.mangocity.btms.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.mangocity.btms.api.IPassengerManageService;
import com.mangocity.btms.vo.CertVO;
import com.mangocity.btms.vo.HistoryPassengerVO;
import com.mangocity.btms.vo.TravelCard;
import com.mangocity.model.passenger.CertificateInfo;
import com.mangocity.model.passenger.PassengerCard;
import com.mangocity.model.passenger.PassengerInfo;
import com.mangocity.services.passenger.IPassengerService;
import com.mangocity.services.passenger.PassengerServiceException;
import com.mangocity.util.CnToSpellUtil;

/**
 * 查询所有乘机人信息服务类
 * 
 * @author hongxiaodong
 *
 */
public class PassengerManageServiceImpl implements IPassengerManageService {

	Logger logger = Logger.getLogger(PassengerManageServiceImpl.class);
	
	private IPassengerService passengerService;

	@Override
	public List<PassengerInfo> queryAllPassengerById(long paramLong) {

		try {
			return passengerService.queryAllPassengerById(paramLong);
		} catch (PassengerServiceException e) {
			logger.error("获取乘机人信息失败", e);
			return null;
		}
	}

	@Override
	public List<PassengerInfo> queryAllPassengerByCd(String paramString) {
		try {
			return passengerService.queryAllPassengerByCd(paramString);
		} catch (PassengerServiceException e) {
			logger.error("获取乘机人信息失败", e);
			return null;
		}
	}

	@Override
	public PassengerInfo getPassengerByPassId(long paramLong) {
		try {
			return passengerService.getPassengerByPassId(paramLong);
		} catch (PassengerServiceException e) {
			logger.error("获取乘机人信息失败", e);
			return null;
		}
	}

	@Override
	public List<PassengerInfo> queryAllPassengerById(long paramLong, int paramInt) {
		try {
			return passengerService.queryAllPassengerById(paramLong, paramInt);
		} catch (PassengerServiceException e) {
			logger.error("获取乘机人信息失败", e);
			return null;
		}
	}

	@Override
	public List<PassengerInfo> queryAllPassengerByCd(String paramString, int paramInt) {
		try {

			return passengerService.queryAllPassengerByCd(paramString, paramInt);
		} catch (PassengerServiceException e) {
			logger.error("获取乘机人信息失败", e);
			return null;
		}
	}

	@Override
	public List<PassengerInfo> getMemberPassengerByMemberCd(String membercd) {
		List<PassengerInfo> passengerInfoList = null;
		try {
			int limit = 3000;
			logger.info("查询历史乘机人限制条数上限：" + limit);
			passengerInfoList = passengerService.queryAllPassengerByCd(membercd, limit);
			if (passengerInfoList == null) {
				logger.info("查询不到历史乘机人，则new ArrayList()");
				passengerInfoList = new ArrayList<PassengerInfo>(0);
			}
			logger.info("ttop passenger maxResult : " + passengerInfoList.size());
		} catch (PassengerServiceException e) {
			logger.error("ttop根据membercd查询历史乘机人失败", e);
		}
		
		
		return passengerInfoList;
	}

	@Override
	public Map<HistoryPassengerVO, String> getHistoryPassengerMapByMemberCd(String membercd) {

		Map<HistoryPassengerVO, String> commonHistoryMap = new TreeMap<HistoryPassengerVO, String>();
		List<PassengerInfo> combined = getMemberPassengerByMemberCd(membercd);
		for (PassengerInfo passengerInfo : combined) {
			HistoryPassengerVO passengerVO = new HistoryPassengerVO();
			passengerVO = toHistoryPassenger(passengerInfo);
			passengerVO.setPsgnamepy(CnToSpellUtil.getFullSpell(passengerInfo.getChiName()));
			commonHistoryMap.put(passengerVO, String.valueOf(passengerInfo.getPassId()));
		}

		logger.debug("commonHistoryMap.size() = " + commonHistoryMap.size());

		return commonHistoryMap;

	}
	
	@Override
	public void addPassengerInfo(PassengerInfo passengerInfo) throws Exception {
		passengerService.createPassenger(passengerInfo);
	}

	private HistoryPassengerVO toHistoryPassenger(PassengerInfo mp) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		HistoryPassengerVO historyPassengerVO = new HistoryPassengerVO();
		historyPassengerVO.setId(mp.getPassId());
		historyPassengerVO.setPsgname(mp.getChiName());
		historyPassengerVO.setPsgengname(mp.getFirstName() + "/" + mp.getMiddleName() + "/" + mp.getLastName());
		historyPassengerVO.setPsgsex(mp.getGender());
		historyPassengerVO.setPsgtype(mp.getPassType());
		historyPassengerVO.setPsgnationality(mp.getCountry());
		historyPassengerVO.setPsgtel(mp.getMobileNo());
		if (mp.getBirthday() != null)
			historyPassengerVO.setPsgbirthdate(df.format(mp.getBirthday()));
		historyPassengerVO.setIdentity(mp.getIdentity());
		historyPassengerVO.setPsgfax(mp.getFaxNo());
		historyPassengerVO.setCreateDate(mp.getCreateTime());
		for (CertificateInfo certificateInfo : mp.getCertificateInfoList()) {
			CertVO certvo = new CertVO();
			certvo.setId(certificateInfo.getCerId());
			certvo.setCertNo(certificateInfo.getCerNo());
			certvo.setType(certificateInfo.getCerType());
			if (certificateInfo.getCerEffDate() != null) {
				certvo.setExpiryDate(df.format(certificateInfo.getCerEffDate()));
			}
			historyPassengerVO.getCert().add(certvo);
		}
		for (PassengerCard passengerCard : mp.getPassCardInfoList()) {
			TravelCard travelCard = new TravelCard();
			travelCard.setCardNo(passengerCard.getCardNo());
			travelCard.setType(passengerCard.getAirlineSn());
			historyPassengerVO.getCard().add(travelCard);
		}
		logger.warn("PassengerInfo转换成HistoryPassengerVO对象");
		return historyPassengerVO;
	}
	


	public IPassengerService getPassengerService() {
		return passengerService;
	}

	public void setPassengerService(IPassengerService passengerService) {
		this.passengerService = passengerService;
	}

}
