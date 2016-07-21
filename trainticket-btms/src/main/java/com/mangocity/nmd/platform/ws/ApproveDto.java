/**
 * ApproveDto.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.mangocity.nmd.platform.ws;

public class ApproveDto  implements java.io.Serializable {
    private java.lang.String appKey;

    private java.lang.String approveType;

    private java.lang.String content;

    private java.lang.String LOBcode;

    private java.lang.String[] mobile;

    private java.lang.String returnUrl;

    private java.lang.String sign;

    public ApproveDto() {
    }

    public ApproveDto(
           java.lang.String appKey,
           java.lang.String approveType,
           java.lang.String content,
           java.lang.String LOBcode,
           java.lang.String[] mobile,
           java.lang.String returnUrl,
           java.lang.String sign) {
           this.appKey = appKey;
           this.approveType = approveType;
           this.content = content;
           this.LOBcode = LOBcode;
           this.mobile = mobile;
           this.returnUrl = returnUrl;
           this.sign = sign;
    }


    /**
     * Gets the appKey value for this ApproveDto.
     * 
     * @return appKey
     */
    public java.lang.String getAppKey() {
        return appKey;
    }


    /**
     * Sets the appKey value for this ApproveDto.
     * 
     * @param appKey
     */
    public void setAppKey(java.lang.String appKey) {
        this.appKey = appKey;
    }


    /**
     * Gets the approveType value for this ApproveDto.
     * 
     * @return approveType
     */
    public java.lang.String getApproveType() {
        return approveType;
    }


    /**
     * Sets the approveType value for this ApproveDto.
     * 
     * @param approveType
     */
    public void setApproveType(java.lang.String approveType) {
        this.approveType = approveType;
    }


    /**
     * Gets the content value for this ApproveDto.
     * 
     * @return content
     */
    public java.lang.String getContent() {
        return content;
    }


    /**
     * Sets the content value for this ApproveDto.
     * 
     * @param content
     */
    public void setContent(java.lang.String content) {
        this.content = content;
    }


    /**
     * Gets the LOBcode value for this ApproveDto.
     * 
     * @return LOBcode
     */
    public java.lang.String getLOBcode() {
        return LOBcode;
    }


    /**
     * Sets the LOBcode value for this ApproveDto.
     * 
     * @param LOBcode
     */
    public void setLOBcode(java.lang.String LOBcode) {
        this.LOBcode = LOBcode;
    }


    /**
     * Gets the mobile value for this ApproveDto.
     * 
     * @return mobile
     */
    public java.lang.String[] getMobile() {
        return mobile;
    }


    /**
     * Sets the mobile value for this ApproveDto.
     * 
     * @param mobile
     */
    public void setMobile(java.lang.String[] mobile) {
        this.mobile = mobile;
    }

    public java.lang.String getMobile(int i) {
        return this.mobile[i];
    }

    public void setMobile(int i, java.lang.String _value) {
        this.mobile[i] = _value;
    }


    /**
     * Gets the returnUrl value for this ApproveDto.
     * 
     * @return returnUrl
     */
    public java.lang.String getReturnUrl() {
        return returnUrl;
    }


    /**
     * Sets the returnUrl value for this ApproveDto.
     * 
     * @param returnUrl
     */
    public void setReturnUrl(java.lang.String returnUrl) {
        this.returnUrl = returnUrl;
    }


    /**
     * Gets the sign value for this ApproveDto.
     * 
     * @return sign
     */
    public java.lang.String getSign() {
        return sign;
    }


    /**
     * Sets the sign value for this ApproveDto.
     * 
     * @param sign
     */
    public void setSign(java.lang.String sign) {
        this.sign = sign;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ApproveDto)) return false;
        ApproveDto other = (ApproveDto) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.appKey==null && other.getAppKey()==null) || 
             (this.appKey!=null &&
              this.appKey.equals(other.getAppKey()))) &&
            ((this.approveType==null && other.getApproveType()==null) || 
             (this.approveType!=null &&
              this.approveType.equals(other.getApproveType()))) &&
            ((this.content==null && other.getContent()==null) || 
             (this.content!=null &&
              this.content.equals(other.getContent()))) &&
            ((this.LOBcode==null && other.getLOBcode()==null) || 
             (this.LOBcode!=null &&
              this.LOBcode.equals(other.getLOBcode()))) &&
            ((this.mobile==null && other.getMobile()==null) || 
             (this.mobile!=null &&
              java.util.Arrays.equals(this.mobile, other.getMobile()))) &&
            ((this.returnUrl==null && other.getReturnUrl()==null) || 
             (this.returnUrl!=null &&
              this.returnUrl.equals(other.getReturnUrl()))) &&
            ((this.sign==null && other.getSign()==null) || 
             (this.sign!=null &&
              this.sign.equals(other.getSign())));
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
        if (getAppKey() != null) {
            _hashCode += getAppKey().hashCode();
        }
        if (getApproveType() != null) {
            _hashCode += getApproveType().hashCode();
        }
        if (getContent() != null) {
            _hashCode += getContent().hashCode();
        }
        if (getLOBcode() != null) {
            _hashCode += getLOBcode().hashCode();
        }
        if (getMobile() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMobile());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMobile(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getReturnUrl() != null) {
            _hashCode += getReturnUrl().hashCode();
        }
        if (getSign() != null) {
            _hashCode += getSign().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ApproveDto.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.platform.nmd.mangocity.com/", "approveDto"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appKey");
        elemField.setXmlName(new javax.xml.namespace.QName("", "appKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("approveType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "approveType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("content");
        elemField.setXmlName(new javax.xml.namespace.QName("", "content"));
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
        elemField.setFieldName("mobile");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mobile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
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
