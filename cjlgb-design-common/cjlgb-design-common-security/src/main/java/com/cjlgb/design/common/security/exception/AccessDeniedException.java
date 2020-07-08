package com.cjlgb.design.common.security.exception;

import com.cjlgb.design.common.core.constant.HttpStatus;

/**
 * @author WFT
 * @date 2019/11/7
 * description: 403异常
 */
public class AccessDeniedException extends SecurityException {

    private AccessDeniedException(){
        super(HttpStatus.FORBIDDEN,"You don't have permission to access on this server.");
    }

    private final static AccessDeniedException ACCESS_DENIED_EXCEPTION = new AccessDeniedException();

    public static AccessDeniedException getInstance(){
        return ACCESS_DENIED_EXCEPTION;
    }

}
