package com.mangocity.btms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mangocity.btms.adpater.service.ContractAdapterService;
import com.mangocity.btms.api.IContractManageService;
import com.mangocity.btms.contract.model.Contract;
import com.mangocity.btms.contract.model.Contract.ContractStatus;

/**
 * 公司全同服务类
 * @author hongxiaodong
 *
 */
public class ContractManageServiceImpl implements IContractManageService {
	
	@Autowired
	private ContractAdapterService contractAdapterService;

	@Override
	public Contract retrieveContractById(long contractId) {
		
		return contractAdapterService.retrieveContractById(contractId);
	}

	@Override
	public Contract retrieveContractBySn(String contractSn) {
		
		return contractAdapterService.retrieveContractBySn(contractSn);
	}

	@Override
	public List<Contract> retrieveContractsByCorporationId(long corporationId) {
		
		return contractAdapterService.retrieveContractsByCorporationId(corporationId);
	}

	@Override
	public List<Contract> retrieveContractsByCorporationId(long corporationId, ContractStatus status) {
		
		return contractAdapterService.retrieveContractsByCorporationId(corporationId, status);
	}

	@Override
	public Contract retrieveActiveContractByCorporationId(long corporationId) {
		
		return contractAdapterService.retrieveActiveContractByCorporationId(corporationId);
	}

	@Override
	public Contract retrieveContractByMemberCD(String membercd) {
		
		return contractAdapterService.retrieveContractByMemberCD(membercd);
	}

}
