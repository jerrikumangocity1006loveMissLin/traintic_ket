package com.mangocity.btms.api;

import java.util.List;

import com.mangocity.btms.projectmanagement.model.Project;

/**
 * 项目代码信息
 * 
 * @author hongxiaodong
 *
 */
public interface IProjectManageService {

	/**
	 * find project object with given id
	 *
	 * @param proId
	 *            id of project object
	 * @return project object
	 */

	public Project retrieveProjectById(long proId);

	/**
	 * find projects with organization id
	 *
	 * @param corporationId
	 *            the id of company which projects belong to
	 * @return projects
	 */

	public List<Project> retrieveProjectsByCorporationId(long corporationId);

	/**
	 * find project object by code
	 *
	 * @param proCode
	 *            code of project object
	 * @param corporationId
	 *            the id of company which the project belongs to
	 * @return project object
	 */

	public Project retrieveProjectByCode(String proCode, long corporationId);

}
