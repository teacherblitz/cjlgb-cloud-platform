package com.cjlgb.design.common.security.cfgbean;

import com.cjlgb.design.common.security.bean.Authentication;
import com.cjlgb.design.common.security.service.TokenStorage;
import com.cjlgb.design.common.security.exception.UnauthorizedException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

/**
 * @author WFT
 * @date 2020/5/6
 * description: 权限自动配置类
 */
@Configuration
@ComponentScan(basePackages = {
        "com.cjlgb.design.common.security.context",
        "com.cjlgb.design.common.security.service",
        "com.cjlgb.design.common.security.handler"
})
public class SecurityAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public TokenStorage tokenStorage(){
        return (request) -> {
            //  从请求头中获取令牌
            String token = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (StringUtils.isEmpty(token)){
                //  未获取到令牌,则直接报401错误
                throw UnauthorizedException.getInstance();
            }
            //  截取访问令牌
            return token.replace(TokenStorage.BEARER,"").trim();
        };
    }


    @Bean
    public RedisTemplate<String, Authentication> authenticationRedisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String, Authentication> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        return template;
    }

}
