package com.mangocity.btms.api;

import java.util.List;

import com.mangocity.btms.costcenter.model.CostCenter;

/**
 * 获取成本中心服务
 * @author hongxiaodong
 *
 */
public interface ICostCenterManageService {
	
	/**
     * Queries cost center data by costCenterId
     *
     * @param costCenterId id of cost center
     * @return Cost Center data
     */
    public CostCenter retrieveCostCenterById(long costCenterId);

    /**
     * Queries costCenter by organizationId
     *
     * @param corporationId id of corporation
     * @return costCenters which belong to corporation
     */
    public List<CostCenter> retrieveCostCentersByCorporationId(long corporationId);

}
