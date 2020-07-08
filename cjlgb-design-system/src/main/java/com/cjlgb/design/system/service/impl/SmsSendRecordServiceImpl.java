package com.cjlgb.design.system.service.impl;

import com.cjlgb.design.common.mybatis.service.impl.BaseServiceImpl;
import com.cjlgb.design.common.system.entity.SmsSendRecord;
import com.cjlgb.design.system.mapper.SmsSendRecordMapper;
import com.cjlgb.design.system.service.SmsSendRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author WFT
 * @date 2020/5/12
 * description:
 */
@Service
@RequiredArgsConstructor
public class SmsSendRecordServiceImpl extends BaseServiceImpl<SmsSendRecordMapper, SmsSendRecord>
        implements SmsSendRecordService {



}
