package com.cjlgb.design.common.oauth.dto;

import com.cjlgb.design.common.oauth.enums.GrantType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author WFT
 * @date 2020/6/14
 * description:
 */
@Setter
@Getter
@NoArgsConstructor
public class OauthParameter implements java.io.Serializable {

    /**
     * 认证方式
     */
    private GrantType grantType;

    /**
     * 用户名,密码模式时必填
     */
    private String username;

    /**
     * 密码,密码模式时必填
     */
    private String password;

    /**
     * 加密盐,密码模式时必填
     */
    private String salt;

    /**
     * 客户端Id
     */
    @JsonProperty(value = "client_id")
    private String clientId;

    /**
     * 客户端密钥
     */
    @JsonProperty(value = "client_secret")
    private String clientSecret;

    /**
     * 授权码,授权码模式必填
     */
    private String code;
}
