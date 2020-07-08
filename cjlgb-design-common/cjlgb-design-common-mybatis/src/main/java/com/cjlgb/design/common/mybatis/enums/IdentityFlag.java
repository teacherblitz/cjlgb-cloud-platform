package com.cjlgb.design.common.mybatis.enums;

import com.cjlgb.design.common.mybatis.service.NumberEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author WFT
 * @date 2020/6/27
 * description:身份标识
 */
@Getter
@AllArgsConstructor
public enum  IdentityFlag implements NumberEnum {

    /**
     * 个人
     */
    personal(1),

    /**
     * 企业
     */
    company(2);

    private final int value;

}
