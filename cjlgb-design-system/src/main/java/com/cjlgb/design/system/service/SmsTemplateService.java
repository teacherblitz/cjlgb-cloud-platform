package com.cjlgb.design.system.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cjlgb.design.common.mybatis.service.BaseService;
import com.cjlgb.design.common.system.entity.SmsTemplate;

/**
 * @author WFT
 * @date 2020/5/13
 * description:
 */
public interface SmsTemplateService extends BaseService<SmsTemplate> {

    /**
     * 根据模板类型获取短信模板信息
     * @param type 模板类型
     * @return com.cjlgb.design.common.system.entity.SmsTemplate
     */
    default SmsTemplate getTemplateByType(Integer type){
        return this.getOne(Wrappers.<SmsTemplate>lambdaQuery().eq(SmsTemplate::getType,type));
    }

}
