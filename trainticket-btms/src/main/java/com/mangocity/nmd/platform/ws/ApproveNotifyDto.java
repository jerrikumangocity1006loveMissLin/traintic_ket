/**
 * ApproveNotifyDto.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.mangocity.nmd.platform.ws;

public class ApproveNotifyDto  implements java.io.Serializable {
    private java.lang.String appCode;

    private java.lang.String appKey;

    private java.lang.String approveDate;

    private java.lang.String approver;

    private java.lang.String LOBcode;

    private java.lang.String reason;

    private java.lang.String returnUrl;

    private java.lang.String sign;

    private java.lang.String status;

    public ApproveNotifyDto() {
    }

    public ApproveNotifyDto(
           java.lang.String appCode,
           java.lang.String appKey,
           java.lang.String approveDate,
           java.lang.String approver,
           java.lang.String LOBcode,
           java.lang.String reason,
           java.lang.String returnUrl,
           java.lang.String sign,
           java.lang.String status) {
           this.appCode = appCode;
           this.appKey = appKey;
           this.approveDate = approveDate;
           this.approver = approver;
           this.LOBcode = LOBcode;
           this.reason = reason;
           this.returnUrl = returnUrl;
           this.sign = sign;
           this.status = status;
    }


    /**
     * Gets the appCode value for this ApproveNotifyDto.
     * 
     * @return appCode
     */
    public java.lang.String getAppCode() {
        return appCode;
    }


    /**
     * Sets the appCode value for this ApproveNotifyDto.
     * 
     * @param appCode
     */
    public void setAppCode(java.lang.String appCode) {
        this.appCode = appCode;
    }


    /**
     * Gets the appKey value for this ApproveNotifyDto.
     * 
     * @return appKey
     */
    public java.lang.String getAppKey() {
        return appKey;
    }


    /**
     * Sets the appKey value for this ApproveNotifyDto.
     * 
     * @param appKey
     */
    public void setAppKey(java.lang.String appKey) {
        this.appKey = appKey;
    }


    /**
     * Gets the approveDate value for this ApproveNotifyDto.
     * 
     * @return approveDate
     */
    public java.lang.String getApproveDate() {
        return approveDate;
    }


    /**
     * Sets the approveDate value for this ApproveNotifyDto.
     * 
     * @param approveDate
     */
    public void setApproveDate(java.lang.String approveDate) {
        this.approveDate = approveDate;
    }


    /**
     * Gets the approver value for this ApproveNotifyDto.
     * 
     * @return approver
     */
    public java.lang.String getApprover() {
        return approver;
    }


    /**
     * Sets the approver value for this ApproveNotifyDto.
     * 
     * @param approver
     */
    public void setApprover(java.lang.String approver) {
        this.approver = approver;
    }


    /**
     * Gets the LOBcode value for this ApproveNotifyDto.
     * 
     * @return LOBcode
     */
    public java.lang.String getLOBcode() {
        return LOBcode;
    }


    /**
     * Sets the LOBcode value for this ApproveNotifyDto.
     * 
     * @param LOBcode
     */
    public void setLOBcode(java.lang.String LOBcode) {
        this.LOBcode = LOBcode;
    }


    /**
     * Gets the reason value for this ApproveNotifyDto.
     * 
     * @return reason
     */
    public java.lang.String getReason() {
        return reason;
    }


    /**
     * Sets the reason value for this ApproveNotifyDto.
     * 
     * @param reason
     */
    public void setReason(java.lang.String reason) {
        this.reason = reason;
    }


    /**
     * Gets the returnUrl value for this ApproveNotifyDto.
     * 
     * @return returnUrl
     */
    public java.lang.String getReturnUrl() {
        return returnUrl;
    }


    /**
     * Sets the returnUrl value for this ApproveNotifyDto.
     * 
     * @param returnUrl
     */
    public void setReturnUrl(java.lang.String returnUrl) {
        this.returnUrl = returnUrl;
    }


    /**
     * Gets the sign value for this ApproveNotifyDto.
     * 
     * @return sign
     */
    public java.lang.String getSign() {
        return sign;
    }


    /**
     * Sets the sign value for this ApproveNotifyDto.
     * 
     * @param sign
     */
    public void setSign(java.lang.String sign) {
        this.sign = sign;
    }


    /**
     * Gets the status value for this ApproveNotifyDto.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this ApproveNotifyDto.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ApproveNotifyDto)) return false;
        ApproveNotifyDto other = (ApproveNotifyDto) obj;
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
            ((this.appKey==null && other.getAppKey()==null) || 
             (this.appKey!=null &&
              this.appKey.equals(other.getAppKey()))) &&
            ((this.approveDate==null && other.getApproveDate()==null) || 
             (this.approveDate!=null &&
              this.approveDate.equals(other.getApproveDate()))) &&
            ((this.approver==null && other.getApprover()==null) || 
             (this.approver!=null &&
              this.approver.equals(other.getApprover()))) &&
            ((this.LOBcode==null && other.getLOBcode()==null) || 
             (this.LOBcode!=null &&
              this.LOBcode.equals(other.getLOBcode()))) &&
            ((this.reason==null && other.getReason()==null) || 
             (this.reason!=null &&
              this.reason.equals(other.getReason()))) &&
            ((this.returnUrl==null && other.getReturnUrl()==null) || 
             (this.returnUrl!=null &&
              this.returnUrl.equals(other.getReturnUrl()))) &&
            ((this.sign==null && other.getSign()==null) || 
             (this.sign!=null &&
              this.sign.equals(other.getSign()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus())));
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
        if (getAppKey() != null) {
            _hashCode += getAppKey().hashCode();
        }
        if (getApproveDate() != null) {
            _hashCode += getApproveDate().hashCode();
        }
        if (getApprover() != null) {
            _hashCode += getApprover().hashCode();
        }
        if (getLOBcode() != null) {
            _hashCode += getLOBcode().hashCode();
        }
        if (getReason() != null) {
            _hashCode += getReason().hashCode();
        }
        if (getReturnUrl() != null) {
            _hashCode += getReturnUrl().hashCode();
        }
        if (getSign() != null) {
            _hashCode += getSign().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ApproveNotifyDto.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.platform.nmd.mangocity.com/", "approveNotifyDto"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "appCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appKey");
        elemField.setXmlName(new javax.xml.namespace.QName("", "appKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("approveDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "approveDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("approver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "approver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LOBcode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LOBcode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reason");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reason"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnUrl");
        elemField.setXmlName(new javax.xml.namespace.QName("", "returnUrl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sign");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sign"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "status"));
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
