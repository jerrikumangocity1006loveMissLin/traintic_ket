package com.mangocity.btms.adpater.service;

import com.mangocity.btms.costcenter.model.CostCenter;

import java.util.List;

/**
 *
 * Date: 12-7-19
 * Time: 下午3:22
 * <p>
 * </p>
 *
 * @since 1.0
 */
public interface CostCenterAdapterService {
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
