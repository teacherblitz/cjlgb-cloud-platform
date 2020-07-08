package com.cjlgb.design.oauth.service;

import com.cjlgb.design.common.oauth.enums.GrantType;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WFT
 * @date 2020/6/14
 * description:认证工厂
 */
@Component
public class GrantFactory implements ApplicationContextAware {

    private static Map<GrantType,GrantService> GRANT_SERVICE_MAP;

    /**
     * 根据认证方式获取相应的认证接口
     * @param grantType 认证方式
     * @return com.cjlgb.design.oauth.service.GrantService
     */
    public GrantService getInterface(GrantType grantType){
        GrantService service = GRANT_SERVICE_MAP.get(grantType);
        Assert.notNull(service,"不支持此认证方式");
        return service;
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext context) throws BeansException {
        Map<String, GrantService> beanMap = context.getBeansOfType(GrantService.class);
        GRANT_SERVICE_MAP = new HashMap<>(beanMap.size());
        beanMap.forEach((beanName,bean) -> GRANT_SERVICE_MAP.put(bean.getGrantType(),bean));
    }
}
