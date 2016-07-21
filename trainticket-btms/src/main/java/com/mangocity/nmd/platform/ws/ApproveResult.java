/**
 * ApproveResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.mangocity.nmd.platform.ws;

public class ApproveResult  implements java.io.Serializable {
    private java.lang.String appCode;

    private java.lang.String errorMsg;

    private java.lang.String result;

    public ApproveResult() {
    }

    public ApproveResult(
           java.lang.String appCode,
           java.lang.String errorMsg,
           java.lang.String result) {
           this.appCode = appCode;
           this.errorMsg = errorMsg;
           this.result = result;
    }


    /**
     * Gets the appCode value for this ApproveResult.
     * 
     * @return appCode
     */
    public java.lang.String getAppCode() {
        return appCode;
    }


    /**
     * Sets the appCode value for this ApproveResult.
     * 
     * @param appCode
     */
    public void setAppCode(java.lang.String appCode) {
        this.appCode = appCode;
    }


    /**
     * Gets the errorMsg value for this ApproveResult.
     * 
     * @return errorMsg
     */
    public java.lang.String getErrorMsg() {
        return errorMsg;
    }


    /**
     * Sets the errorMsg value for this ApproveResult.
     * 
     * @param errorMsg
     */
    public void setErrorMsg(java.lang.String errorMsg) {
        this.errorMsg = errorMsg;
    }


    /**
     * Gets the result value for this ApproveResult.
     * 
     * @return result
     */
    public java.lang.String getResult() {
        return result;
    }


    /**
     * Sets the result value for this ApproveResult.
     * 
     * @param result
     */
    public void setResult(java.lang.String result) {
        this.result = result;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ApproveResult)) return false;
        ApproveResult other = (ApproveResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.appCode==null && other.getAppCode()==null) || 
             (this.appCode!=null &&
              this.appCode.equals(other.getAppCode()))) &&
            ((this.errorMsg==null && other.getErrorMsg()==null) || 
             (this.errorMsg!=null &&
              this.errorMsg.equals(other.getErrorMsg()))) &&
            ((this.result==null && other.getResult()==null) || 
             (this.result!=null &&
              this.result.equals(other.getResult())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getAppCode() != null) {
            _hashCode += getAppCode().hashCode();
        }
        if (getErrorMsg() != null) {
            _hashCode += getErrorMsg().hashCode();
        }
        if (getResult() != null) {
            _hashCode += getResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ApproveResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.platform.nmd.mangocity.com/", "approveResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "appCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorMsg");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errorMsg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("result");
        elemField.setXmlName(new javax.xml.namespace.QName("", "result"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
