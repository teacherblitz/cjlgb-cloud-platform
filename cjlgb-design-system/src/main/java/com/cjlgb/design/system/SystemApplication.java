package com.cjlgb.design.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author WFT
 * @date 2020/5/13
 * description:系统服务启动类
 */
@SpringCloudApplication
@MapperScan(value = "com.cjlgb.design.system.mapper")
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class,args);
    }

}
