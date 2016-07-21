package com.mangocity.btms.adpater.service.impl;

import com.mangocity.btms.adpater.service.ReportAuthorityAdapterService;
import com.mangocity.btms.organization.model.Department;
import com.mangocity.btms.reportauthority.manager.ReportAuthorityRoleManagerException;
import com.mangocity.btms.reportauthority.service.ReportAuthorityRoleService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: leiqunqiong
 * Date: 12-10-11
 * Time: 上午10:44
 * To change this template use File | Settings | File Templates.
 */
public class ReportAuthorityAdapterServiceImpl implements ReportAuthorityAdapterService{
    private ReportAuthorityRoleService reportAuthorityRoleService;
    private static Log log = LogFactory.getLog(ApprovalAdapterServiceImpl.class);

    public List<Department> getDepartmentsByMembercd(String membercd) {
        try {
            return reportAuthorityRoleService.getDepartmentsByMembercd(membercd);
        } catch (ReportAuthorityRoleManagerException e) {
            log.error(e.getErrorCode(),e);
        }
        return null;
    }

    public void setReportAuthorityRoleService(ReportAuthorityRoleService reportAuthorityRoleService) {
        this.reportAuthorityRoleService = reportAuthorityRoleService;
    }

}
