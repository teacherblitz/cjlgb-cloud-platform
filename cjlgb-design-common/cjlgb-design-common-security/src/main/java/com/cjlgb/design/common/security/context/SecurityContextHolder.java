package com.cjlgb.design.common.security.context;

import com.cjlgb.design.common.security.bean.Authentication;
import com.cjlgb.design.common.security.bean.SecurityContext;
import com.cjlgb.design.common.security.exception.SecurityException;

/**
 * @author WFT
 * @date 2020/5/6
 * description:
 */
public class SecurityContextHolder {

    private static final ThreadLocal<SecurityContext> THREAD_LOCAL = new ThreadLocal<>();

    public static void clear(){
        THREAD_LOCAL.remove();
    }

    public static void set(Authentication authentication,SecurityException e){
        THREAD_LOCAL.set(new SecurityContext(authentication,e));
    }

    /**
     * 获取当前线程中的认证信息
     * @return com.cjlgb.design.common.security.bean.Authentication
     */
    public static Authentication getAuthentication(){
        return THREAD_LOCAL.get().getAuthentication();
    }

    /**
     * 获取当前线程中权限信息上下文
     * @return com.cjlgb.design.common.security.bean.SecurityContext
     * @throws SecurityException 权限异常
     */
    public static SecurityContext getContext() throws SecurityException{
        SecurityContext context = THREAD_LOCAL.get();
        if (null == context.getAuthentication()){
            throw context.getException();
        }
        return context;
    }

}
