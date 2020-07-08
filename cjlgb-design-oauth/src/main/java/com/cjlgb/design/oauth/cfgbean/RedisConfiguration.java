package com.cjlgb.design.oauth.cfgbean;

import com.cjlgb.design.common.oauth.entity.GrantCode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author WFT
 * @date 2020/6/14
 * description:
 */
@Configuration
public class RedisConfiguration {

    @Bean
    public RedisTemplate<String, GrantCode> grantCodeRedisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String, GrantCode> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        return template;
    }

}
