package com.cjlgb.design.common.security.exception;

import com.cjlgb.design.common.core.constant.HttpStatus;

/**
 * @author WFT
 * @date 2019/11/7
 * description: 407异常
 */
public class AuthenticationException extends SecurityException {

    public AuthenticationException(String message) {
        super(HttpStatus.CERT_OVERDUE, message);
    }
    
}
