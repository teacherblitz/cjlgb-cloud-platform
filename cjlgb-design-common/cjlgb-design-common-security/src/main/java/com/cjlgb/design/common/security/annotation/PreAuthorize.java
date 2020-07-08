package com.cjlgb.design.common.security.annotation;

import java.lang.annotation.*;

/**
 * @author WFT
 * @date 2020/5/6
 * description: 授权注解
 */
@Inherited
@Documented
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface PreAuthorize {

    /**
     * @return 权限标识列表
     */
    String[] value() default {};

}
