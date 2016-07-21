/**
 * Copyright MangoCity Limited (c) 2011. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of MangoCity Limited.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from MangoCity or an authorized sublicensor.
 */
package com.mangocity.btms.adpater.service.impl;

import com.mangocity.btms.adpater.service.DepartmentAdapterService;
import com.mangocity.btms.organization.model.Department;
import com.mangocity.btms.organization.service.DepartmentService;

import java.util.List;

/**
 * Date: 12-7-16
 * Time: 下午5:19
 *
 * @since 1.0
 */

public class DepartmentAdapterServiceImpl implements DepartmentAdapterService{

    private DepartmentService departmentService;


    public Department retrieveDepartmentById(long deptId) {
        return departmentService.retrieveDepartmentById(deptId);
    }

    public Department retrieveDepartmentBySn(String departmentSn) {
        return departmentService.retrieveDepartmentBySn(departmentSn);
    }

    public Department retrieveDepartmentByOldDeptId(long oldId,Department.DeptType deptType){
        return departmentService.retrieveDepartmentByOldDeptId(oldId,deptType);
    }

    public Department retrieveDepartmentByMbrShipCd(String mbrShipCd) {
        return departmentService.retrieveDepartmentByMbrShipCd(mbrShipCd);
    }

    public List<Department> retrieveDepartmentByCorporationId(long corporationId) {
        return departmentService.retrieveDepartmentByCorporationId(corporationId);
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
}