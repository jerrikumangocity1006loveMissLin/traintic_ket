package com.mangocity.btms.adpater.service.impl;

import com.mangocity.btms.adpater.service.HierarchyArchitectureAdapterService;
import com.mangocity.btms.core.manager.HierarchyArchitectureManager;
import com.mangocity.btms.core.model.hierarchy.HierarchyArchitecture;

import java.util.List;

/**
 *
 * Date: 12-7-24
 * Time: 下午3:32
 * <p>
 * </p>
 *
 * @since 1.0
 */
public class HierarchyArchitectureAdapterServiceImpl implements HierarchyArchitectureAdapterService{
    private HierarchyArchitectureManager hierarchyArchitectureManager;
    public HierarchyArchitecture retrieveHierarchyArchitectureById(Long architectureId) {
        return hierarchyArchitectureManager.retrieveHierarchyArchitectureById(architectureId);
    }

    public List<HierarchyArchitecture> retrieveHierarchyArchitectureChildren(long parentId, HierarchyArchitecture.HierarchyType hierarchyType) {
        return hierarchyArchitectureManager.retrieveHierarchyArchitectureChildren(parentId,hierarchyType);
    }

    public List<HierarchyArchitecture> retrieveTopHierarchyArchitectureByAssocIdAndType(long assocId, HierarchyArchitecture.HierarchyType hierarchyType) {
        return hierarchyArchitectureManager.retrieveTopHierarchyArchitectureByAssocIdAndType(assocId,hierarchyType);
    }

    public HierarchyArchitecture retrieveHierarchyArchitectureByAssocIdAndType(long assocId, HierarchyArchitecture.HierarchyType hierarchyType) {
        return hierarchyArchitectureManager.retrieveHierarchyArchitectureByAssocIdAndType(assocId,hierarchyType);
    }

    public HierarchyArchitecture retrieveHierarchyArchitectureByAssocId(long assocId) {
        return hierarchyArchitectureManager.retrieveHierarchyArchitectureByAssocId(assocId);
    }

    public HierarchyArchitecture retrieveTreeByArchitectureID(long corporationID, HierarchyArchitecture.HierarchyType hierarchyType) {
        return hierarchyArchitectureManager.retrieveTreeByArchitectureID(corporationID,hierarchyType);
    }

    public List<HierarchyArchitecture> retrieveAllHierarchyArchitectureChildren(long entityId, HierarchyArchitecture.HierarchyType hierarchyType) {
        return hierarchyArchitectureManager.retrieveAllHierarchyArchitectureChildren(entityId, hierarchyType);
    }

    public List<Long> retrieveHirDeptIdsByIdAndType(long assocId, HierarchyArchitecture.HierarchyType type) {
        return hierarchyArchitectureManager.retrieveHirDeptIdsByIdAndType(assocId,type);
    }

    public void setHierarchyArchitectureManager(HierarchyArchitectureManager hierarchyArchitectureManager) {
        this.hierarchyArchitectureManager = hierarchyArchitectureManager;
    }
}
