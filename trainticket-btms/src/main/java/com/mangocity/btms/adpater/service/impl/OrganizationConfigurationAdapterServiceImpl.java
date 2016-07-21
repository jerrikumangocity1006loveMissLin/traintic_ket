package com.mangocity.btms.adpater.service.impl;

import com.mangocity.btms.adpater.service.OrganizationConfigurationAdapterService;
import com.mangocity.btms.organization.configuration.OrganizationConfigException;
import com.mangocity.btms.organization.configuration.model.MessageConfigQryCriteria;
import com.mangocity.btms.organization.configuration.model.MessageConfiguration;
import com.mangocity.btms.organization.configuration.model.Personalisation;
import com.mangocity.btms.organization.configuration.model.ServiceType;
import com.mangocity.btms.organization.configuration.service.CorporationConfigService;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: dongxiaohui
 * Date: 12-7-24
 * Time: 下午2:38
 * To change this template use File | Settings | File Templates.
 */
public class OrganizationConfigurationAdapterServiceImpl implements OrganizationConfigurationAdapterService {

    private CorporationConfigService corporationConfigService;

    public Personalisation retrieveServicesByCorporationIdAndType(long corporationId, ServiceType serviceType) {
        return corporationConfigService.retrieveServicesByCorporationIdAndType(corporationId,serviceType);
    }

    public List<MessageConfiguration> retrieveMessageConfigurationsByCorporationId(Long corporationId) {
        return corporationConfigService.retrieveMessageConfigurationsByCorporationId(corporationId);
    }

    public MessageConfiguration retrieveMessageConfigurationById(Long configId) {
        return corporationConfigService.retrieveMessageConfigurationById(configId);
    }

    public MessageConfiguration retrieveMessageConfigurationByQryCriteria(MessageConfigQryCriteria messageConfigQryCriteria) {
        try {
            return corporationConfigService.retrieveMessageConfigurationByQryCriteria(messageConfigQryCriteria);
        } catch (OrganizationConfigException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setCorporationConfigService(CorporationConfigService corporationConfigService) {
        this.corporationConfigService = corporationConfigService;
    }
}
