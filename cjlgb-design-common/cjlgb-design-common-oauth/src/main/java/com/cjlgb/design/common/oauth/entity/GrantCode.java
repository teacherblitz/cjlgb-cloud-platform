package com.cjlgb.design.common.oauth.entity;

import com.cjlgb.design.common.oauth.entity.OauthClient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author WFT
 * @date 2020/6/14
 * description:授权码
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GrantCode implements java.io.Serializable {

    /**
     * 授权码,必须全局唯一,避免出现重复
     */
    private String code;

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * Oauth客户端信息
     */
    private OauthClient client;

}
