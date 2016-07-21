package com.mangocity.btms.adpater.service;

import org.mangocube.corenut.commons.exception.CheckedException;

/**
 * Created by IntelliJ IDEA.
 * User: heyunhao
 * Date: 11-10-7
 * Time: 上午7:40
 *
 * @since 1.0
 */
public class ApprovalServiceException extends CheckedException{
    public ApprovalServiceException(Enum errorCode) {
        super(errorCode);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public ApprovalServiceException(Enum errorCode, Object... para) {
        super(errorCode, para);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public ApprovalServiceException(Enum errorCode, Throwable throwable) {
        super(errorCode, throwable);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public ApprovalServiceException(Enum errorCode, Throwable throwable, Object... para) {
        super(errorCode, throwable, para);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
