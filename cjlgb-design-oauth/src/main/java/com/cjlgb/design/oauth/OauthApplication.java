package com.cjlgb.design.oauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author WFT
 * @date 2020/5/6
 * description: Oauth2服务启动类
 */
@SpringCloudApplication
@MapperScan(value = "com.cjlgb.design.oauth.mapper")
public class OauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthApplication.class,args);
    }

}
