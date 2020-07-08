package com.cjlgb.design.common.security.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author WFT
 * @date 2020/5/6
 * description: 令牌存储策略
 */
public interface TokenStorage {

    String BEARER = "Bearer";

    /**
     * 从请求中获取访问令牌,默认从请求头中获取
     * @param request 请求对象
     * @return String
     */
    String getAccessToken(HttpServletRequest request);

}
