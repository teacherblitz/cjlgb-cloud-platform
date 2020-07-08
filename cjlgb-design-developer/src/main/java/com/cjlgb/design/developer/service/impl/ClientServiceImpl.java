package com.cjlgb.design.developer.service.impl;

import com.cjlgb.design.common.mybatis.service.impl.BaseServiceImpl;
import com.cjlgb.design.common.oauth.entity.OauthClient;
import com.cjlgb.design.developer.mapper.OauthClientMapper;
import com.cjlgb.design.developer.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author WFT
 * @date 2020/6/14
 * description:
 */
@Service
@RequiredArgsConstructor
public class ClientServiceImpl extends BaseServiceImpl<OauthClientMapper, OauthClient> implements ClientService {


}
