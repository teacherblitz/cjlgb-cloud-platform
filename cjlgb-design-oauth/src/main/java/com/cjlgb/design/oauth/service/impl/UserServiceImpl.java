package com.cjlgb.design.oauth.service.impl;

import com.cjlgb.design.common.mybatis.service.impl.BaseServiceImpl;
import com.cjlgb.design.common.oauth.entity.OauthUser;
import com.cjlgb.design.oauth.mapper.OauthUserMapper;
import com.cjlgb.design.oauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author WFT
 * @date 2020/5/9
 * description:
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends BaseServiceImpl<OauthUserMapper, OauthUser> implements UserService {

}
