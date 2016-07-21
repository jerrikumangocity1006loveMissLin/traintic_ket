package com.mangocity.btms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mangocity.btms.adpater.service.HierarchyArchitectureAdapterService;
import com.mangocity.btms.api.IHierarchyArchitectureManageService;
import com.mangocity.btms.core.model.hierarchy.HierarchyArchitecture;
import com.mangocity.btms.core.model.hierarchy.HierarchyArchitecture.HierarchyType;

public class HierarchyArchitectureManageServiceImpl implements IHierarchyArchitectureManageService {
	
	@Autowired
	private HierarchyArchitectureAdapterService hierarchyArchitectureAdapterService;

	@Override
	public HierarchyArchitecture retrieveHierarchyArchitectureById(Long architectureId) {
		
		return hierarchyArchitectureAdapterService.retrieveHierarchyArchitectureById(architectureId);
	}

	@Override
	public List<HierarchyArchitecture> retrieveHierarchyArchitectureChildren(long parentId,
			HierarchyType hierarchyType) {
		
		return hierarchyArchitectureAdapterService.retrieveHierarchyArchitectureChildren(parentId, hierarchyType);
	}

	@Override
	public List<HierarchyArchitecture> retrieveTopHierarchyArchitectureByAssocIdAndType(long assocId,
			HierarchyType hierarchyType) {
		
		return hierarchyArchitectureAdapterService.retrieveTopHierarchyArchitectureByAssocIdAndType(assocId, hierarchyType);
	}

	@Override
	public HierarchyArchitecture retrieveHierarchyArchitectureByAssocIdAndType(long assocId,
			HierarchyType hierarchyType) {
		
		return hierarchyArchitectureAdapterService.retrieveHierarchyArchitectureByAssocIdAndType(assocId, hierarchyType);
	}

	@Override
	public HierarchyArchitecture retrieveHierarchyArchitectureByAssocId(long assocId) {
		
		return hierarchyArchitectureAdapterService.retrieveHierarchyArchitectureByAssocId(assocId);
	}

	@Override
	public HierarchyArchitecture retrieveTreeByArchitectureID(long corporationID, HierarchyType hierarchyType) {
		
		return hierarchyArchitectureAdapterService.retrieveTreeByArchitectureID(corporationID, hierarchyType);
	}

	@Override
	public List<HierarchyArchitecture> retrieveAllHierarchyArchitectureChildren(long entityId,
			HierarchyType hierarchyType) {
		
		return hierarchyArchitectureAdapterService.retrieveAllHierarchyArchitectureChildren(entityId, hierarchyType);
	}

	@Override
	public List<Long> retrieveHirDeptIdsByIdAndType(long assocId, HierarchyType type) {
		
		return hierarchyArchitectureAdapterService.retrieveHirDeptIdsByIdAndType(assocId, type);
	}

}
