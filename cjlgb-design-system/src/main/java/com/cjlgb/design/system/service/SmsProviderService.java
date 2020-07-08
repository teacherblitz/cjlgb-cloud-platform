package com.cjlgb.design.system.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cjlgb.design.common.mybatis.enums.LockFlag;
import com.cjlgb.design.common.mybatis.service.BaseService;
import com.cjlgb.design.common.system.entity.SmsProvider;
import com.cjlgb.design.system.exception.SmsPushException;

import java.util.Calendar;
import java.util.List;

/**
 * @author WFT
 * @date 2020/5/13
 * description:
 */
public interface SmsProviderService extends BaseService<SmsProvider> {

    /**
     * 获取可用的短信服务商
     * @return 短信服务商信息
     */
    default SmsProvider getUsableProvider(){
        List<SmsProvider> list = this.list(Wrappers.<SmsProvider>lambdaQuery().eq(SmsProvider::getLockFlag, LockFlag.enable));
        if (list.size() <= 0){
            throw new SmsPushException("暂无可用的短信服务商,请联系管理员!");
        }
        //  随机选一个
        return list.get(Calendar.getInstance().get(Calendar.SECOND) % list.size());
    }

}
