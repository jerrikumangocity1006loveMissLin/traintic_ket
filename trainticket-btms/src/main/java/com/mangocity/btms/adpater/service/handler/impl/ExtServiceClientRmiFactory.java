/*
 * Copyright MangoCity Limited (c) 2010. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of MangoCity Limited.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from MangoCity or an authorized sublicensor.
 */

package com.mangocity.btms.adpater.service.handler.impl;

import com.ctol.mango.pge.common.ParamServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mangocube.corenut.scm.stub.JndiContext;
import org.mangocube.corenut.scm.stub.JndiContextProperty;
import org.mangocube.corenut.scm.stub.ServiceClientRmiFactory;

public class ExtServiceClientRmiFactory extends ServiceClientRmiFactory {

    private static final Log logger = LogFactory.getLog(ExtServiceClientRmiFactory.class);
    private static final String PROVIDE_URL = "java.naming.provider.url";

    private String javaNameProviderUrl;
    
    public String getJavaNameProviderUrl() {
		return javaNameProviderUrl;
	}

	public void setJavaNameProviderUrl(String javaNameProviderUrl) {
		this.javaNameProviderUrl = javaNameProviderUrl;
	}



	public ExtServiceClientRmiFactory() {
        super();
        initializePropertiesFromDB();
    }

	public ExtServiceClientRmiFactory(String url, String javaNameProviderUrl){
		super(url);
		
		this.javaNameProviderUrl = javaNameProviderUrl;
        initializePropertiesFromDB();
		
	}
	
    public ExtServiceClientRmiFactory(String url){
        super(url);
        initializePropertiesFromDB();
    }

    private void initializePropertiesFromDB() {
    	assert javaNameProviderUrl != null;
        boolean reset = false;
        String contextId = "";
        logger.info("javaNameProviderUrl---["+this.javaNameProviderUrl+"]");
        for (JndiContext context : super.serviceSkeletonFactory.getJndiContexts()) {
            String value = ParamServiceImpl.getInstance().getConfValue(this.javaNameProviderUrl);
            logger.info("javaNameProviderUrl"+this.javaNameProviderUrl+"----"+value);
            if (value == null || value.trim().length() == 0)
                continue;

            for (JndiContextProperty property : context.getProperties()) {
                if (PROVIDE_URL.equals(property.getName())) {
                    property.setValue(value);
                    reset = true;
                    contextId = context.getId();
                    logger.info("contextId : "+contextId);
                    break;
                }
            }
            if (!reset) {
                JndiContextProperty property = new JndiContextProperty();
                property.setName(PROVIDE_URL);
                property.setValue(value);
                context.addProperty(property);
            }
        }
    }
}
