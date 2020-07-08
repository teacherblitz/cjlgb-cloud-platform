package com.cjlgb.design.common.security.bean;

import com.cjlgb.design.common.security.exception.SecurityException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author WFT
 * @date 2020/6/17
 * description:
 */
@Getter
@AllArgsConstructor
public class SecurityContext implements java.io.Serializable {

    /**
     * 凭证信息
     */
    private Authentication authentication;

    /**
     * 权限异常
     */
    private SecurityException exception;

}
