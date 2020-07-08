package com.cjlgb.design.common.security.service.impl;

import com.cjlgb.design.common.security.bean.AccessToken;
import com.cjlgb.design.common.security.bean.Authentication;
import com.cjlgb.design.common.security.service.SecurityService;
import com.cjlgb.design.common.security.exception.AuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author WFT
 * @date 2020/5/6
 * description:
 */
@Service
@RequiredArgsConstructor
public class SecurityServiceByRedisImpl implements SecurityService {

    /**
     * 缓存名称
     */
    private final static String CACHE_NAME = "online:user:%s";

    /**
     * 缓存有效期为30分钟
     */
    private final static int VALIDITY = 1000 * 60 * 30;

    private final RedisTemplate<String,Authentication> redisTemplate;

    @Override
    public Authentication getAuthentication(String token) throws AuthenticationException {
        String cacheKey = String.format(CACHE_NAME,token);
        //  从缓存中获取
        Authentication authentication = this.redisTemplate.opsForValue().get(cacheKey);
        if (null == authentication){
            //  抛出407异常
            throw new AuthenticationException("Please replace with a new certificate. Current certificate has expired.");
        }
        //  延长缓存的有效期
        this.redisTemplate.expire(cacheKey,VALIDITY, TimeUnit.MILLISECONDS);
        return authentication;
    }

    @Override
    public AccessToken generateToken(Authentication authentication) {
        //  生成访问令牌
        String token = UUID.randomUUID().toString();
        String cacheKey = String.format(CACHE_NAME,token);
        //  保存到Redis中
        this.redisTemplate.opsForValue().set(cacheKey,authentication,VALIDITY,TimeUnit.MILLISECONDS);
        return new AccessToken(token,this.getFlag());
    }

    @Override
    public void removeToken(String token) {
        String cacheKey = String.format(CACHE_NAME,token);
        this.redisTemplate.delete(cacheKey);
    }

    @Override
    public String getFlag() {
        return "redis";
    }
}
