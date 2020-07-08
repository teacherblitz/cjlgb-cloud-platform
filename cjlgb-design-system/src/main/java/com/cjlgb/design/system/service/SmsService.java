package com.cjlgb.design.system.service;

import com.cjlgb.design.common.system.entity.SmsProvider;
import com.cjlgb.design.system.exception.SmsPushException;

/**
 * @author WFT
 * @date 2020/5/13
 * description:短信服务
 */
public interface SmsService {

    /**
     * 获取短信服务商名称
     * @return java.lang.String
     */
    String getProviderName();

    /**
     * 短信推送
     * @param provider 短信服务商信息
     * @param mobile 手机号码
     * @param context 短信内容
     * @throws SmsPushException 短信推送异常
     */
    void push(SmsProvider provider,String mobile,String context) throws SmsPushException;

}
