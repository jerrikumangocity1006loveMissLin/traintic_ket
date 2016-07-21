package com.mangocity.btms.adpater.service.impl;

import com.mangocity.btms.adpater.service.ContractAdapterService;
import com.mangocity.btms.adpater.service.CorporationAdapterService;
import com.mangocity.btms.contract.model.Contract;
import com.mangocity.btms.contract.service.ContractService;
import com.mangocity.mbr.corporation.model.Corporation;

import java.util.List;

/**
 * Date: 12-7-18
 * Time: 下午4:42
 *
 * @since: the version
 */
public class ContractAdapterServiceImpl implements ContractAdapterService{

    private ContractService contractService;

    private CorporationAdapterService corporationAdapterService;

    public Contract retrieveContractById(long contractId) {
        return contractService.retrieveContractById(contractId);
    }

    public Contract retrieveContractBySn(String contractSn) {
        return contractService.retrieveContractBySn(contractSn);
    }

    public List<Contract> retrieveContractsByCorporationId(long corporationId) {
        return contractService.retrieveContractsByCorporationId(corporationId);
    }

    public List<Contract> retrieveContractsByCorporationId(long corporationId, Contract.ContractStatus status) {
        return contractService.retrieveContractsByCorporationId(corporationId,status);
    }

    public Contract retrieveActiveContractByCorporationId(long corporationId) {
        List<Contract> contracts = this.retrieveContractsByCorporationId(corporationId, Contract.ContractStatus.ACTIVE);
        if(!contracts.isEmpty())
            return contracts.get(0);
        return null;
    }

    public Contract retrieveContractByMemberCD(String membercd) {
        Corporation corporation = corporationAdapterService.retrieveCorporationByMbrShipCd(membercd);
        return retrieveActiveContractByCorporationId(corporation.getCorporationId());

    }

    public void setContractService(ContractService contractService) {
        this.contractService = contractService;
    }

    public void setCorporationAdapterService(CorporationAdapterService corporationAdapterService) {
        this.corporationAdapterService = corporationAdapterService;
    }
}