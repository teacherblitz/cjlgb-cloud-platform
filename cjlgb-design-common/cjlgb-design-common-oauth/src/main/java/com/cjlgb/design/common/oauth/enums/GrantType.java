package com.cjlgb.design.common.oauth.enums;

import com.cjlgb.design.common.mybatis.service.NumberEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author WFT
 * @date 2020/6/14
 * description:认证模式
 */
@Getter
@AllArgsConstructor
public enum GrantType implements NumberEnum {

    /**
     * 密码模式
     */
    password(1),

    /**
     * 授权码模式
     */
    authorization_code(2),
    ;

    private final int value;
}
