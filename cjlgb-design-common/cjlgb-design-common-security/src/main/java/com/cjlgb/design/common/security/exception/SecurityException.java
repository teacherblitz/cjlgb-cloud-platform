package com.cjlgb.design.common.security.exception;

import com.cjlgb.design.common.core.exception.BizException;

/**
 * @author WFT
 * @date 2020/6/17
 * description:权限异常
 */
public class SecurityException extends BizException {

    SecurityException(int code, String message) {
        super(code, message);
    }
}
