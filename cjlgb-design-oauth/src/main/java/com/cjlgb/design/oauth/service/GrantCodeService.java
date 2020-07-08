package com.cjlgb.design.oauth.service;

import com.cjlgb.design.common.oauth.entity.GrantCode;
import com.cjlgb.design.common.oauth.entity.OauthClient;

/**
 * @author WFT
 * @date 2020/6/14
 * description:授权码CRUD接口
 */
public interface GrantCodeService {

    /**
     * 获取授权码信息
     * @param code java.lang.String
     * @return com.cjlgb.design.common.oauth.entity.GrantCode
     */
    GrantCode selectByCode(String code);

    /**
     * 生成授权码
     * @param userId 用户Id
     * @param client Oauth客户端信息
     * @return com.cjlgb.design.common.oauth.entity.GrantCode
     */
    GrantCode generateCode(Long userId, OauthClient client);

}
