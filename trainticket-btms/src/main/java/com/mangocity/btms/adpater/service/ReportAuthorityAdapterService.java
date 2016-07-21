package com.mangocity.btms.adpater.service;

import com.mangocity.btms.organization.model.Department;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: leiqunqiong
 * Date: 12-10-11
 * Time: 上午10:32
 * To change this template use File | Settings | File Templates.
 */
public interface ReportAuthorityAdapterService {
    public List<Department> getDepartmentsByMembercd(String membercd);
}
