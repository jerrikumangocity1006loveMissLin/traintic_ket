/**
 * SMSApproveService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.mangocity.nmd.platform.ws;

public interface SMSApproveService extends java.rmi.Remote {
    public java.lang.String notifyApprove(com.mangocity.nmd.platform.ws.ApproveNotifyDto arg0) throws java.rmi.RemoteException;
    public com.mangocity.nmd.platform.ws.ApproveResult approve(com.mangocity.nmd.platform.ws.ApproveDto arg0) throws java.rmi.RemoteException;
    public com.mangocity.nmd.platform.ws.ApproveNotifyDto queryApprove(java.lang.String arg0) throws java.rmi.RemoteException;
}
