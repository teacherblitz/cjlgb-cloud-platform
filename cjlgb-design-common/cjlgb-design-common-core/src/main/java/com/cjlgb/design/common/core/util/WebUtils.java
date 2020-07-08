package com.cjlgb.design.common.core.util;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author WFT
 * @date 2020/5/6
 * description: Web工具类
 */
public final class WebUtils {

    private WebUtils(){

    }

    /**
     * 获取HttpServletRequest对象
     * @return request
     */
    public HttpServletRequest getHttpServletRequest(){
        ServletRequestAttributes requestAttributes =
                Optional.ofNullable((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .orElseThrow(() -> new RuntimeException("request is null"));
        return requestAttributes.getRequest();
    }

    private static class Holder {
        private final static WebUtils UTILS = new WebUtils();

        /**
         * 请求头：可获取到Ip信息
         */
        private final static String[] IP_HEADER_ARRAY = {
                "X-Requested-For",
                "X-Forwarded-For",
                "Proxy-Client-IP",
                "WL-Proxy-Client-IP",
                "HTTP_CLIENT_IP",
                "HTTP_X_FORWARDED_FOR"
        };
    }

    /**
     * 从请求中获取Ip地址
     * @param request javax.servlet.http.HttpServletRequest
     * @return java.lang.String
     */
    public String getRemoteAddr(HttpServletRequest request){
        String ip = request.getRemoteAddr();
        for (String header : Holder.IP_HEADER_ARRAY) {
            String value = request.getHeader(header);
            if (StringUtils.hasText(value) && !"unknown".equalsIgnoreCase(value)){
                ip = value;
                break;
            }
        }
        return ip;
    }

    /**
     * 从当前请求中获取Ip地址
     * @return java.lang.String
     */
    public String getRemoteAddr(){
        return this.getRemoteAddr(this.getHttpServletRequest());
    }

    public static WebUtils getInstance(){
        return Holder.UTILS;
    }

}
