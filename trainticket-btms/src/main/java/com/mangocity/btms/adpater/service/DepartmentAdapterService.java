/**
 * Copyright MangoCity Limited (c) 2011. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of MangoCity Limited.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from MangoCity or an authorized sublicensor.
 */
package com.mangocity.btms.adpater.service;

import com.mangocity.btms.organization.model.Department;

import java.util.List;

/**
 * Date: 12-7-16
 * Time: 下午5:13
 *
 * @since 1.0
 */
public interface DepartmentAdapterService {


        /**
     * Finds department with given department id
     * @param deptId the given department id
     * @return Department from database with special department id
     */
    public Department retrieveDepartmentById(long deptId);

    /**
     * Finds department with given department sn
     * @param departmentSn  the given department sn
     * @return  Department from database with special department sn
     */
    public Department retrieveDepartmentBySn(String departmentSn);

    /**
     * 由于导数据出现分支ID与部门ID相同，增加此方法以区分
     * @param oldId
     * @param deptType
     * @return
     */
    public Department retrieveDepartmentByOldDeptId(long oldId,Department.DeptType deptType);

    /**
     * Queries department data by member ship cd
     * @param mbrShipCd
     * @return
     */
    public Department retrieveDepartmentByMbrShipCd(String mbrShipCd);

    /**
     * 根据法人机构ID查部门
     * @param corporationId
     * @return
     */
    public List<Department> retrieveDepartmentByCorporationId(long corporationId);

}
