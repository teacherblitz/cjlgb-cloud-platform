package com.cjlgb.design.common.core.cfgbean;

import com.cjlgb.design.common.core.handler.GlobalExceptionHandler;
import com.cjlgb.design.common.core.handler.GlobalReturnValueHandler;
import com.cjlgb.design.common.core.util.JsonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WFT
 * @date 2020/5/6
 * description: 基础配置类
 */
@Configuration
@EnableAsync
public class BasicConfiguration {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    /**
     * Json序列化配置
     * @return com.fasterxml.jackson.databind.ObjectMapper
     */
    @Bean
    public ObjectMapper serializingObjectMapper() {
        return JsonUtils.getInstance();
    }


    /**
     * 配置全局异常处理器
     * @return com.cjlgb.design.common.core.handler.GlobalExceptionHandler
     */
    @Bean
    public GlobalExceptionHandler exceptionHandler(){
        return new GlobalExceptionHandler();
    }


    /**
     * 应用程序上下文初始化配置
     * @return org.springframework.context.ApplicationContextAware
     */
    @Bean
    @ConditionalOnClass(name = "org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter")
    public ApplicationContextAware applicationContextAware(){
        return context -> {
            //  配置全局返回值类型处理器
            RequestMappingHandlerAdapter adapter = context.getBean(RequestMappingHandlerAdapter.class);
            List<HandlerMethodReturnValueHandler> handlers = new ArrayList<>();
            handlers.add(new GlobalReturnValueHandler());
            List<HandlerMethodReturnValueHandler> handlerList = adapter.getReturnValueHandlers();
            if (null != handlerList){
                handlers.addAll(handlerList);
            }
            adapter.setReturnValueHandlers(handlers);

        };
    }
}
