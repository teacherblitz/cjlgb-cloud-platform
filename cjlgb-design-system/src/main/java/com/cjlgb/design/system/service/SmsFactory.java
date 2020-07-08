package com.cjlgb.design.system.service;

import com.cjlgb.design.system.exception.SmsPushException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WFT
 * @date 2020/5/13
 * description:短信工厂
 */
@Component
public class SmsFactory implements ApplicationContextAware{

    private static Map<String, SmsService> SERVICE_MAP;

    /**
     * 根据短信服务商名称获取相应的短信服务接口
     * @param providerName 服务商名称
     * @return com.cjlgb.design.system.service.SmsService
     */
    public SmsService getInterface(String providerName){
        SmsService service = SERVICE_MAP.get(providerName);
        if (null == service){
            throw new SmsPushException(String.format("系统尚未与[%s]短信服务商进行对接",providerName));
        }
        return service;
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext context) throws BeansException {
        Map<String, SmsService> beanMap = context.getBeansOfType(SmsService.class);
        SERVICE_MAP = new HashMap<>(beanMap.size());
        beanMap.forEach((beanName,bean) -> SERVICE_MAP.put(bean.getProviderName(),bean));
    }
}
