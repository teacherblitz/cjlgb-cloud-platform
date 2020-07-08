package com.cjlgb.design.common.mybatis.enums;

import com.cjlgb.design.common.mybatis.service.NumberEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author WFT
 * @date 2020/6/27
 * description:审核标记
 */
@Getter
@AllArgsConstructor
public enum AuditFlag implements NumberEnum {

    /**
     * 待提交
     */
    sleep(0),

    /**
     * 待审核
     */
    wait(1),

    /**
     * 审核通过
     */
    success(2),

    /**
     * 审核失败
     */
    fail(3)
    ;

    private final int value;
}
