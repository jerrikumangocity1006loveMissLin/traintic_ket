/**
 * Copyright MangoCity Limited (c) 2011. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of MangoCity Limited.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from MangoCity or an authorized sublicensor.
 */
package com.mangocity.btms.adpater.service.impl;

import com.mangocity.btms.adpater.service.ProjectAdapterService;
import com.mangocity.btms.projectmanagement.model.Project;
import com.mangocity.btms.projectmanagement.service.ProjectService;

import java.util.List;

/**
 * Date: 12-7-18
 * Time: 下午7:48
 *
 * @since 1.0
 */

public class ProjectAdapterServiceImpl implements ProjectAdapterService{

    private ProjectService projectService;

    public Project retrieveProjectById(long proId) {
        return projectService.retrieveProjectById(proId);
    }

    public List<Project> retrieveProjectsByCorporationId(long corporationId) {
        return projectService.retrieveProjectsByCorporationId(corporationId);
    }

    public Project retrieveProjectByCode(String proCode, long corporationId) {
        return projectService.retrieveProjectByCode(proCode,corporationId);
    }

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }
}
