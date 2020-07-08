package com.cjlgb.design.common.core.cfgbean;

import com.cjlgb.design.common.core.support.InterceptorSupport;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collection;

/**
 * @author WFT
 * @date 2020/7/2
 * description:Web配置
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer, ApplicationContextAware {

    /**
     * 拦截器列表
     */
    private Collection<InterceptorSupport> interceptors = null;

    /**
     * 注册拦截器
     * @param registry org.springframework.web.servlet.config.annotation.InterceptorRegistry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        interceptors.forEach(interceptor -> registry.addInterceptor(interceptor).addPathPatterns(interceptor.getPathPatterns()));
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext context) throws BeansException {
        //  从容器中获取拦截器
        this.interceptors = context.getBeansOfType(InterceptorSupport.class).values();
    }
}
