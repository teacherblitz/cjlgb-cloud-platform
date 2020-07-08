package com.cjlgb.design.common.security.handler;

import com.cjlgb.design.common.core.support.InterceptorSupport;
import com.cjlgb.design.common.security.bean.Authentication;
import com.cjlgb.design.common.security.exception.SecurityException;
import com.cjlgb.design.common.security.service.SecurityService;
import com.cjlgb.design.common.security.context.SecurityContextHolder;
import com.cjlgb.design.common.security.service.TokenStorage;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author WFT
 * @date 2020/6/17
 * description:认证拦截器
 */
@Component
@AllArgsConstructor
public class AuthenticationInterceptor implements InterceptorSupport, ApplicationContextAware {

    private final TokenStorage tokenStorage;

    private static Map<String, SecurityService> SECURITY_CONTEXT_MAP;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        try {
            //  获取访问令牌
            String accessToken = this.tokenStorage.getAccessToken(request);
            //  不同系统可能采用不同的方式存储用户信息,根据标识得到相应的实现类
            SecurityService context = SECURITY_CONTEXT_MAP.get(request.getHeader("flag"));
            Assert.notNull(context, "非法参数");
            //  获取当前用户认证信息
            Authentication authentication = context.getAuthentication(accessToken);
            //  将用户认证信息存放在当前线程中
            SecurityContextHolder.set(authentication,null);
        } catch (SecurityException e){
            //  将异常信息存放在当前线程中
            SecurityContextHolder.set(null,e);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        //  将当前线程中的用户信息清空
        SecurityContextHolder.clear();
    }

    @Override
    public List<String> getPathPatterns() {
        return new ArrayList<>(Arrays.asList("","/**"));
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext context) throws BeansException {
        Map<String, SecurityService> beanMap = context.getBeansOfType(SecurityService.class);
        SECURITY_CONTEXT_MAP = new HashMap<>(beanMap.size());
        beanMap.forEach((beanName,bean) -> SECURITY_CONTEXT_MAP.put(bean.getFlag(),bean));
    }
}
