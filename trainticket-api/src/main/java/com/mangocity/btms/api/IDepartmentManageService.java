package com.mangocity.btms.api;

import java.util.List;

import com.mangocity.btms.organization.model.Department;

/**
 * 获取部门信息服务类
 * @author hongxiaodong
 *
 */
public interface IDepartmentManageService {
	
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
    
    /**
     * 根据会员信息获取部门信息集
     * @param membercd
     * @return
     */
    public List<Department> getDepartmentsByMembercd(String membercd);

}
