package com.cjlgb.design.system.service.impl;

import com.cjlgb.design.common.mybatis.service.impl.BaseServiceImpl;
import com.cjlgb.design.common.system.entity.SmsTemplate;
import com.cjlgb.design.system.mapper.SmsTemplateMapper;
import com.cjlgb.design.system.service.SmsTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author WFT
 * @date 2020/5/13
 * description:
 */
@Service
@RequiredArgsConstructor
public class SmsTemplateServiceImpl extends BaseServiceImpl<SmsTemplateMapper, SmsTemplate> implements SmsTemplateService {


}
