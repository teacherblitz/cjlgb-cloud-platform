package com.cjlgb.design.developer.service.impl;

import com.cjlgb.design.common.mybatis.service.impl.BaseServiceImpl;
import com.cjlgb.design.common.oauth.entity.Developer;
import com.cjlgb.design.developer.mapper.DeveloperMapper;
import com.cjlgb.design.developer.service.DeveloperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author WFT
 * @date 2020/6/27
 * description:DeveloperServiceImpl
 */
@Service
@RequiredArgsConstructor
public class DeveloperServiceImpl extends BaseServiceImpl<DeveloperMapper,Developer> implements DeveloperService {

}
