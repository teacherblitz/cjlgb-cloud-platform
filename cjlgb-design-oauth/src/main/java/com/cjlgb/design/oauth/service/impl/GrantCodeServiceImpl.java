package com.cjlgb.design.oauth.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.cjlgb.design.common.oauth.entity.GrantCode;
import com.cjlgb.design.common.oauth.entity.OauthClient;
import com.cjlgb.design.oauth.service.GrantCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author WFT
 * @date 2020/6/14
 * description:
 */
@Service
@RequiredArgsConstructor
public class GrantCodeServiceImpl implements GrantCodeService {

    private final RedisTemplate<String, GrantCode> redisTemplate;

    /**
     * 缓存名称
     */
    private final static String CACHE_NAME = "oauth:grant:code:%s";

    /**
     * 授权码有效期为15分钟
     */
    private final static long VALIDITY = 1000 * 60 * 15;

    @Override
    public GrantCode selectByCode(String code) {
        String cacheKey = String.format(CACHE_NAME,code);
        //  获取授权码
        GrantCode grantCode = this.redisTemplate.opsForValue().get(cacheKey);
        //  删除缓存
        this.redisTemplate.delete(cacheKey);
        return grantCode;
    }

    @Override
    public GrantCode generateCode(Long userId, OauthClient client) {
        //  生成授权码
        String code = IdWorker.getIdStr();
        //  构建授权码对象
        GrantCode grantCode = new GrantCode(code, userId, client);
        String cacheKey = String.format(CACHE_NAME,code);
        //  保存到redis中,并设置有效期
        this.redisTemplate.opsForValue().set(cacheKey,grantCode,VALIDITY, TimeUnit.MILLISECONDS);
        return grantCode;
    }
}
