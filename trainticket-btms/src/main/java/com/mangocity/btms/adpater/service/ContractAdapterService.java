package com.mangocity.btms.adpater.service;

import com.mangocity.btms.contract.model.Contract;

import java.util.List;

/**
 * Date: 12-7-18
 * Time: 下午4:40
 *
 * @since: the version
 */
public interface ContractAdapterService {
    /**
     * 根据合同ID查询合同
     * @param contractId
     * @return
     */

    public Contract retrieveContractById(long contractId);

    /**
     * 根据合同SN查询合同
     * @param contractSn
     * @return
     */

    public Contract retrieveContractBySn(String contractSn);

    /**
     * 根据法人机构ID查询其所有的合同
     * @param corporationId
     * @return
     */

    public List<Contract> retrieveContractsByCorporationId(long corporationId);

    /**
     * 根据法人机构ID及其状态查询所有合同
     * @param corporationId
     * @param status
     * @return
     */

    public List<Contract> retrieveContractsByCorporationId(long corporationId,Contract.ContractStatus status);

    /**
     * 根据法人机构ID及有效合同
     * @param corporationId
     * @return
     */

    public Contract retrieveActiveContractByCorporationId(long corporationId) ;

    /**
     * 取会员的有效合同
     * @param membercd
     * @return
     */

    public Contract retrieveContractByMemberCD(String membercd);
}
