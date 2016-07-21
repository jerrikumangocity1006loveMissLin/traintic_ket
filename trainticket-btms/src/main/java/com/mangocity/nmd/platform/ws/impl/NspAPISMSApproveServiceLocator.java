/**
 * NspAPISMSApproveServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.mangocity.nmd.platform.ws.impl;

import java.rmi.RemoteException;

import com.mangocity.nmd.platform.ws.ApproveDto;
import com.mangocity.nmd.platform.ws.ApproveResult;
import com.mangocity.nmd.platform.ws.SMSApproveService;

public class NspAPISMSApproveServiceLocator extends org.apache.axis.client.Service implements com.mangocity.nmd.platform.ws.impl.NspAPISMSApproveService {

    public NspAPISMSApproveServiceLocator() {
    }


    public NspAPISMSApproveServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public NspAPISMSApproveServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SMSApproveServiceImplPort
    private java.lang.String SMSApproveServiceImplPort_address = "http://10.10.39.5:9082/nsp/service/SmsApproveService";

    public java.lang.String getSMSApproveServiceImplPortAddress() {
        return SMSApproveServiceImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SMSApproveServiceImplPortWSDDServiceName = "SMSApproveServiceImplPort";

    public java.lang.String getSMSApproveServiceImplPortWSDDServiceName() {
        return SMSApproveServiceImplPortWSDDServiceName;
    }

    public void setSMSApproveServiceImplPortWSDDServiceName(java.lang.String name) {
        SMSApproveServiceImplPortWSDDServiceName = name;
    }

    public com.mangocity.nmd.platform.ws.SMSApproveService getSMSApproveServiceImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SMSApproveServiceImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSMSApproveServiceImplPort(endpoint);
    }
    
    /**
     * 测试
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
    	
    	NspAPISMSApproveServiceLocator nspSMS = new NspAPISMSApproveServiceLocator();
		
		 ApproveDto approveDto = new ApproveDto();
	     approveDto.setAppKey("13258888");
		SMSApproveService spa = nspSMS.getSMSApproveServiceImplPort();
		
		ApproveResult re = spa.approve(approveDto);
		
		System.out.println("=====re===="+re);
	}

    public com.mangocity.nmd.platform.ws.SMSApproveService getSMSApproveServiceImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.mangocity.nmd.platform.ws.impl.NspAPISMSApproveServiceSoapBindingStub _stub = new com.mangocity.nmd.platform.ws.impl.NspAPISMSApproveServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getSMSApproveServiceImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSMSApproveServiceImplPortEndpointAddress(java.lang.String address) {
        SMSApproveServiceImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.mangocity.nmd.platform.ws.SMSApproveService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.mangocity.nmd.platform.ws.impl.NspAPISMSApproveServiceSoapBindingStub _stub = new com.mangocity.nmd.platform.ws.impl.NspAPISMSApproveServiceSoapBindingStub(new java.net.URL(SMSApproveServiceImplPort_address), this);
                _stub.setPortName(getSMSApproveServiceImplPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("SMSApproveServiceImplPort".equals(inputPortName)) {
            return getSMSApproveServiceImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://impl.ws.platform.nmd.mangocity.com/", "NspAPISMSApproveService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://impl.ws.platform.nmd.mangocity.com/", "SMSApproveServiceImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SMSApproveServiceImplPort".equals(portName)) {
            setSMSApproveServiceImplPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
