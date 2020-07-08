package com.cjlgb.design.developer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author WFT
 * @date 2020/7/2
 * description:开发者服务启动类
 */
@EnableFeignClients(value = "com.cjlgb.design.common.oauth.feign")
@SpringCloudApplication
@MapperScan(value = "com.cjlgb.design.developer.mapper")
public class DeveloperApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeveloperApplication.class,args);
    }
}
