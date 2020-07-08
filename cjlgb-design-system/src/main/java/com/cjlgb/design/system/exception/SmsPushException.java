package com.cjlgb.design.system.exception;

import com.cjlgb.design.common.core.constant.HttpStatus;
import com.cjlgb.design.common.core.exception.BizException;

/**
 * @author WFT
 * @date 2020/5/13
 * description:短息推送异常
 */
public class SmsPushException extends BizException {

    public SmsPushException(String message) {
        super(HttpStatus.SERVER_ERROR, message);
    }

}
