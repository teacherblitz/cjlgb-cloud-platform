package com.cjlgb.design.common.security.service;

import com.cjlgb.design.common.security.bean.AccessToken;
import com.cjlgb.design.common.security.bean.Authentication;
import com.cjlgb.design.common.security.exception.AuthenticationException;

/**
 * @author WFT
 * @date 2020/5/6
 * description:
 */
public interface SecurityService {

    /**
     * 根据访问令牌获取凭证信息
     * @param token 访问令牌
     * @return com.cjlgb.design.common.security.bean.Authentication
     * @throws AuthenticationException 407异常
     */
    Authentication getAuthentication(String token) throws AuthenticationException;

    /**
     * 生成访问令牌
     * @param authentication com.cjlgb.design.common.security.bean.Authentication
     * @return com.cjlgb.design.common.security.bean.AccessToken
     */
    AccessToken generateToken(Authentication authentication);

    /**
     * 移除访问令牌
     * @param token java.lang.String
     */
    default void removeToken(String token){

    }

    /**
     * 实现方式的唯一标识
     * @return java.lang.String
     */
    String getFlag();

}
