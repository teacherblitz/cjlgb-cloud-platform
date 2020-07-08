package com.cjlgb.design.oauth.service.impl;

import com.cjlgb.design.common.oauth.enums.GrantType;
import com.cjlgb.design.common.oauth.dto.OauthParameter;
import com.cjlgb.design.common.security.bean.Authentication;
import com.cjlgb.design.common.oauth.entity.GrantCode;
import com.cjlgb.design.common.oauth.entity.OauthClient;
import com.cjlgb.design.oauth.service.GrantCodeService;
import com.cjlgb.design.oauth.service.GrantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;

/**
 * @author WFT
 * @date 2020/6/14
 * description:授权码模式
 */
@Service
@RequiredArgsConstructor
public class GrantCodeGrantServiceImpl implements GrantService {

    private final GrantCodeService grantCodeService;

    @Override
    public Authentication grant(OauthParameter parameter) {
        //  校验授权码是否为空
        Assert.hasText(parameter.getCode(),"授权码不能为空");
        //  获取授权码详细信息
        GrantCode grantCode = this.grantCodeService.selectByCode(parameter.getCode());
        Assert.notNull(grantCode,"无效的授权码");
        //  校验客户端Id
        OauthClient client = grantCode.getClient();
        Assert.isTrue(client.getId().toString().equals(parameter.getClientId()),"客户端Id与授权码不匹配");
        //  校验客户端密钥
        String cipherText = DigestUtils.md5DigestAsHex((client.getSecret() + parameter.getCode()).getBytes());
        Assert.isTrue(cipherText.equals(parameter.getClientSecret()),"客户端密钥不正确");
        return new Authentication(grantCode.getUserId(), new ArrayList<>(0));
    }

    @Override
    public GrantType getGrantType() {
        return GrantType.authorization_code;
    }
}
