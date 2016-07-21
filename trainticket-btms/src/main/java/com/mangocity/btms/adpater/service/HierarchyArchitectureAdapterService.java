package com.mangocity.btms.adpater.service;

import com.mangocity.btms.core.model.hierarchy.HierarchyArchitecture;

import java.util.List;

/**
 *
 * Date: 12-7-24
 * Time: 下午3:30
 * <p>
 * </p>
 *
 * @since 1.0
 */
public interface HierarchyArchitectureAdapterService {
    /**
     * Queries hierarchy architecture data by architectureId
     * @param architectureId
     * @return If successful,return hierarchy architecture data
     * @throws com.mangocity.btms.core.manager.HierarchyArchitectureManagerException
     */
    public HierarchyArchitecture retrieveHierarchyArchitectureById(Long architectureId);

    /**
     * 根据层级ID查询其所有的子层级
     * @param parentId
     * @param hierarchyType
     * @return
     */
    public List<HierarchyArchitecture> retrieveHierarchyArchitectureChildren(long parentId,HierarchyArchitecture.HierarchyType hierarchyType);

    /**
     * 根据实体ID及类型查询一级层级结构
     * @param assocId
     * @param hierarchyType
     * @return
     */
    public List<HierarchyArchitecture> retrieveTopHierarchyArchitectureByAssocIdAndType(long assocId, HierarchyArchitecture.HierarchyType hierarchyType);

    public HierarchyArchitecture retrieveHierarchyArchitectureByAssocIdAndType(long assocId, HierarchyArchitecture.HierarchyType hierarchyType);

    /**
     * 根据实体关联ID查询(暂时只查法人机构)
     * @param assocId
     * @return
     */
    public HierarchyArchitecture retrieveHierarchyArchitectureByAssocId(long assocId);

    /**
     * 递归查询树
     * @param corporationID
     * @param hierarchyType
     * @return
     */
    public  HierarchyArchitecture retrieveTreeByArchitectureID(long corporationID,HierarchyArchitecture.HierarchyType hierarchyType);

    /**
     * 递归查询该实体下所有子层级
     * @param entityId
     * @param hierarchyType
     * @return
     */
    public List<HierarchyArchitecture> retrieveAllHierarchyArchitectureChildren(long entityId,HierarchyArchitecture.HierarchyType hierarchyType);


    /**
     * 根据关联ID和类型向上查询树形结构
     * @param assocId
     * @param type
     * @return
     */
    public List<Long> retrieveHirDeptIdsByIdAndType(long assocId,HierarchyArchitecture.HierarchyType type);



}
