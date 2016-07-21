package com.mangocity.btms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mangocity.btms.adpater.service.ProjectAdapterService;
import com.mangocity.btms.api.IProjectManageService;
import com.mangocity.btms.projectmanagement.model.Project;


/**
 * 获取项目代码服务类
 * @author hongxiaodong
 *
 */
public class ProjectManageServiceImpl implements IProjectManageService {
	
	@Autowired
	private ProjectAdapterService projectAdapterService;

	@Override
	public Project retrieveProjectById(long proId) {
		
		return projectAdapterService.retrieveProjectById(proId);
	}

	@Override
	public List<Project> retrieveProjectsByCorporationId(long corporationId) {
		
		return projectAdapterService.retrieveProjectsByCorporationId(corporationId);
	}

	@Override
	public Project retrieveProjectByCode(String proCode, long corporationId) {
	
		return projectAdapterService.retrieveProjectByCode(proCode, corporationId);
	}

}
