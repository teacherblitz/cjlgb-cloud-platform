package com.cjlgb.design.common.security.exception;

import com.cjlgb.design.common.core.constant.HttpStatus;

/**
 * @author WFT
 * @date 2019/11/7
 * description: 401异常
 */
public class UnauthorizedException extends SecurityException {

    private UnauthorizedException(){
        super(HttpStatus.UNAUTHORIZED,"Full authentication is required to access this resource.");
    }

    private final static UnauthorizedException UNAUTHORIZED_EXCEPTION = new UnauthorizedException();

    public static UnauthorizedException getInstance(){
        return UNAUTHORIZED_EXCEPTION;
    }

}
