package com.mangocity.btms.adpater.service;

import com.mangocity.btms.organization.configuration.model.MessageConfigQryCriteria;
import com.mangocity.btms.organization.configuration.model.MessageConfiguration;
import com.mangocity.btms.organization.configuration.model.Personalisation;
import com.mangocity.btms.organization.configuration.model.ServiceType;

import java.util.List;

public interface OrganizationConfigurationAdapterService {
    /**
     * 根据法人机构ID及类型查询相应的服务配置
     * @param corporationId
     * @param serviceType
     * @return
     */
    public Personalisation retrieveServicesByCorporationIdAndType(long corporationId,ServiceType serviceType);
    /**
     *
     * @param corporationId
     * @return
     */
    public List<MessageConfiguration> retrieveMessageConfigurationsByCorporationId(Long corporationId);

    /**
     *
     * @param configId
     * @return
     */
    public MessageConfiguration retrieveMessageConfigurationById(Long configId);

    /**
     * Find MessageConfiguration with query criteria
     * @param messageConfigQryCriteria
     * @return
     */
    public MessageConfiguration retrieveMessageConfigurationByQryCriteria(MessageConfigQryCriteria messageConfigQryCriteria);
}
