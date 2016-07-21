package com.mangocity.btms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mangocity.btms.adpater.service.ReportAuthorityAdapterService;
import com.mangocity.btms.api.IDepartmentManageService;
import com.mangocity.btms.organization.model.Department;
import com.mangocity.btms.organization.model.Department.DeptType;
import com.mangocity.btms.organization.service.DepartmentService;

/**
 * 获取部门信息服务类
 * @author hongxiaodong
 *
 */
public class DepartmentManageServiceImpl implements IDepartmentManageService {
	
	@Autowired
	private DepartmentService dudepartmentService;
	
	@Autowired
	private ReportAuthorityAdapterService reportAuthorityAdapterService;

	@Override
	public Department retrieveDepartmentById(long deptId) {
		
		return dudepartmentService.retrieveDepartmentById(deptId);
	}

	@Override
	public Department retrieveDepartmentBySn(String departmentSn) {
		
		return dudepartmentService.retrieveDepartmentBySn(departmentSn);
	}

	@Override
	public Department retrieveDepartmentByOldDeptId(long oldId, DeptType deptType) {
		
		return dudepartmentService.retrieveDepartmentByOldDeptId(oldId, deptType);
	}

	@Override
	public Department retrieveDepartmentByMbrShipCd(String mbrShipCd) {
		
		return dudepartmentService.retrieveDepartmentByMbrShipCd(mbrShipCd);
	}

	@Override
	public List<Department> retrieveDepartmentByCorporationId(long corporationId) {
		
		return dudepartmentService.retrieveDepartmentByCorporationId(corporationId);
	}

	@Override
	public List<Department> getDepartmentsByMembercd(String membercd) {
		
		return reportAuthorityAdapterService.getDepartmentsByMembercd(membercd);
	}

}
