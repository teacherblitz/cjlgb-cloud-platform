package com.cjlgb.design.system.service.impl;

import com.cjlgb.design.common.mybatis.service.impl.BaseServiceImpl;
import com.cjlgb.design.common.system.entity.SmsProvider;
import com.cjlgb.design.system.mapper.SmsProviderMapper;
import com.cjlgb.design.system.service.SmsProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author WFT
 * @date 2020/5/13
 * description:
 */
@Service
@RequiredArgsConstructor
public class SmsProviderServiceImpl extends BaseServiceImpl<SmsProviderMapper,SmsProvider> implements SmsProviderService {


}
