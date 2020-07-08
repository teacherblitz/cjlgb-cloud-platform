package com.cjlgb.design.common.core.util;

import org.springframework.util.Base64Utils;
import org.springframework.util.DigestUtils;

/**
 * @author WFT
 * @date 2020/7/2
 * description:字符串工具类
 */
public final class StrUtils {

    private StrUtils(){

    }

    private static class Holder {
        private final static StrUtils UTILS = new StrUtils();

        private final static String BASIC = "Basic ";

        private final static String COLON = ":";
    }

    public static StrUtils getInstance(){
        return Holder.UTILS;
    }

    /**
     * 编码
     * @param username 用户名
     * @param password 密码
     * @return java.lang.String
     */
    private String encodeBasicAuthStr(String username,String password){
        return Holder.BASIC + Base64Utils.encodeToString((username + Holder.COLON + password).getBytes());
    }

    /**
     * 编码
     * @param username 用户名
     * @param password 密码
     * @param salt 随机盐
     * @return java.lang.String
     */
    public String encodeBasicAuthStr(String username,String password,String salt){
        return this.encodeBasicAuthStr(username, DigestUtils.md5DigestAsHex((password + salt).getBytes()));
    }

    /**
     * 解码
     * @param basicAuth 字符串
     * @return [0]:用户名,[1]:密码
     */
    public String[] decodeBasicAuthStr(String basicAuth){
        basicAuth = basicAuth.replace(Holder.BASIC,"").trim();
        return new String(Base64Utils.decodeFromString(basicAuth)).split(Holder.COLON);
    }
}
