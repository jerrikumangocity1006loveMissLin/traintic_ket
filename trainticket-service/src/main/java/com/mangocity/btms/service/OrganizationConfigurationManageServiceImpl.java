package com.mangocity.btms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mangocity.btms.adpater.service.OrganizationConfigurationAdapterService;
import com.mangocity.btms.api.IOrganizationConfigurationManageService;
import com.mangocity.btms.organization.configuration.model.MessageConfigQryCriteria;
import com.mangocity.btms.organization.configuration.model.MessageConfiguration;
import com.mangocity.btms.organization.configuration.model.Personalisation;
import com.mangocity.btms.organization.configuration.model.ServiceType;

/**
 * 获取公司配置信息
 * @author hongxiaodong
 *
 */
public class OrganizationConfigurationManageServiceImpl implements IOrganizationConfigurationManageService {
	
	@Autowired
	private OrganizationConfigurationAdapterService organizationConfigurationAdapterService;

	@Override
	public Personalisation retrieveServicesByCorporationIdAndType(long corporationId, ServiceType serviceType) {
		
		return organizationConfigurationAdapterService.retrieveServicesByCorporationIdAndType(corporationId, serviceType);
	}

	@Override
	public List<MessageConfiguration> retrieveMessageConfigurationsByCorporationId(Long corporationId) {
		
		return organizationConfigurationAdapterService.retrieveMessageConfigurationsByCorporationId(corporationId);
	}

	@Override
	public MessageConfiguration retrieveMessageConfigurationById(Long configId) {
		
		return organizationConfigurationAdapterService.retrieveMessageConfigurationById(configId);
	}

	@Override
	public MessageConfiguration retrieveMessageConfigurationByQryCriteria(
			MessageConfigQryCriteria messageConfigQryCriteria) {
		
		return organizationConfigurationAdapterService.retrieveMessageConfigurationByQryCriteria(messageConfigQryCriteria);
	}

}
