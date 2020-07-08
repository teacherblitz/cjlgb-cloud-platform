package com.cjlgb.design.common.security.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cjlgb.design.common.core.util.JsonUtils;
import com.cjlgb.design.common.security.bean.AccessToken;
import com.cjlgb.design.common.security.bean.Authentication;
import com.cjlgb.design.common.security.service.SecurityService;
import com.cjlgb.design.common.security.exception.AuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * @author WFT
 * @date 2020/6/14
 * description:
 */
@Service
@RequiredArgsConstructor
public class SecurityServiceByJwtImpl implements SecurityService {

    /**
     * Jwt Token 颁发者
     */
    private final static String ISSUER = "https://www.cjlgb.com";

    /**
     * 算法
     */
    private final static Algorithm ALGORITHM = Algorithm.HMAC256(ISSUER);

    /**
     * 凭证信息
     */
    private final static String CERT = "cert";

    /**
     * 访问令牌有效期为1天
     */
    private final static long ACCESS_TOKEN_VALIDITY = 1000 * 60 * 60 * 24;

    @Override
    public Authentication getAuthentication(String token) throws AuthenticationException {
        try {
            //  验证 Jwt Token 的颁发者,有效期
            DecodedJWT jwt = JWT.require(ALGORITHM).withIssuer(ISSUER).build().verify(token);
            //  将凭证信息序列化成对象
            return JsonUtils.toBean(jwt.getClaim(CERT).asString(),Authentication.class);
        }catch (JWTVerificationException e){
            throw new AuthenticationException(e.getMessage());
        }
    }

    @Override
    public AccessToken generateToken(Authentication authentication) {
        //  生成JwtToken
        String token = JWT.create().withIssuer(ISSUER)
                //  唯一标识
                .withJWTId(UUID.randomUUID().toString())
                //  凭证信息
                .withClaim(CERT, JsonUtils.toJson(authentication))
                //  到期时间（毫秒值）
                .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY))
                .sign(ALGORITHM);
        return new AccessToken(token,this.getFlag());
    }

    @Override
    public String getFlag() {
        return "jwt";
    }
}
