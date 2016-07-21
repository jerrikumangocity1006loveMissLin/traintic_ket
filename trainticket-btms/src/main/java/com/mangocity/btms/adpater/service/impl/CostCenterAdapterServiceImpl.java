package com.mangocity.btms.adpater.service.impl;

import com.mangocity.btms.adpater.service.CostCenterAdapterService;
import com.mangocity.btms.costcenter.model.CostCenter;
import com.mangocity.btms.costcenter.service.CostCenterService;

import java.util.List;

/**
 *
 * Date: 12-7-19
 * Time: 下午3:29
 * <p>
 * </p>
 *
 * @since 1.0
 */
public class CostCenterAdapterServiceImpl implements CostCenterAdapterService {
    private CostCenterService costCenterService;
    /**
     * Queries cost center data by costCenterId
     *
     * @param costCenterId id of cost center
     * @return Cost Center data
     */
    public CostCenter retrieveCostCenterById(long costCenterId) {
        return costCenterService.retrieveCostCenterById(costCenterId);
    }
    /**
     * Queries costCenter by organizationId
     *
     * @param corporationId id of corporation
     * @return costCenters which belong to corporation
     */
    public List<CostCenter> retrieveCostCentersByCorporationId(long corporationId) {
        return costCenterService.retrieveCostCentersByCorporationId(corporationId);
    }

    public void setCostCenterService(CostCenterService costCenterService) {
        this.costCenterService = costCenterService;
    }
}
