package com.cjlgb.design.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author WFT
 * @date 2020/5/14
 * description:网关服务启动类
 */
@SpringCloudApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class,args);
    }

}
