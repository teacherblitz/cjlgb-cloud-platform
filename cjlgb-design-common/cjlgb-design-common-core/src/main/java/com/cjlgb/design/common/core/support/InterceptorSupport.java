package com.cjlgb.design.common.core.support;

import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;

/**
 * @author WFT
 * @date 2020/7/2
 * description:
 */
public interface InterceptorSupport extends HandlerInterceptor {

    /**
     * 路径匹配规则
     * @return java.util.List
     */
    List<String> getPathPatterns();

}
