package com.cjlgb.design.common.mybatis.enums;

import com.cjlgb.design.common.mybatis.service.NumberEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author WFT
 * @date 2020/6/15
 * description: 锁定标记
 */
@Getter
@AllArgsConstructor
public enum LockFlag implements NumberEnum {

    /**
     * 启用
     */
    enable(0),

    /**
     * 锁定中
     */
    locking(-1),
    ;

    private final int value;

}
