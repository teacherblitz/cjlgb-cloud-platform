package com.cjlgb.design.oauth.service;

import com.cjlgb.design.common.oauth.enums.GrantType;
import com.cjlgb.design.common.oauth.dto.OauthParameter;
import com.cjlgb.design.common.security.bean.Authentication;

/**
 * @author WFT
 * @date 2020/6/14
 * description: 认证接口
 */
public interface GrantService {

    /**
     * 用户认证,认证通过则返回用户的凭证信息
     * @param parameter com.cjlgb.design.common.oauth.dto.OauthParameter
     * @return com.cjlgb.design.common.security.bean.Authentication
     */
    Authentication grant(OauthParameter parameter);

    /**
     * 获取认证方式
     * @return java.lang.String
     */
    GrantType getGrantType();

}
