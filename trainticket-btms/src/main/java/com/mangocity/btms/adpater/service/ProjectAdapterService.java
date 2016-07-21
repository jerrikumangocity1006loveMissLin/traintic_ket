/**
 * Copyright MangoCity Limited (c) 2011. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of MangoCity Limited.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from MangoCity or an authorized sublicensor.
 */
package com.mangocity.btms.adpater.service;

import com.mangocity.btms.projectmanagement.model.Project;

import java.util.List;

/**
 * Date: 12-7-18
 * Time: 下午7:48
 *
 * @since 1.0
 */

public interface ProjectAdapterService {


      /**
     * find project object with given id
     *
     * @param proId id of project object
     * @return project  object
     */

    public Project retrieveProjectById(long proId);

    /**
     * find projects with organization id
     *
     * @param corporationId the id of company which  projects belong to
     * @return projects
     */

    public List<Project> retrieveProjectsByCorporationId(long corporationId);

    /**
     * find project object by code
     *
     * @param proCode code of project  object
     * @param corporationId   the id of company which the project  belongs to
     * @return project  object
     */

    public Project retrieveProjectByCode(String proCode, long corporationId) ;

}
