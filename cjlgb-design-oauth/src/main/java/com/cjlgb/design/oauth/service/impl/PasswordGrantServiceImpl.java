package com.cjlgb.design.oauth.service.impl;

import com.cjlgb.design.common.mybatis.enums.LockFlag;
import com.cjlgb.design.common.oauth.enums.GrantType;
import com.cjlgb.design.common.oauth.dto.OauthParameter;
import com.cjlgb.design.common.oauth.entity.OauthClient;
import com.cjlgb.design.common.oauth.entity.OauthUser;
import com.cjlgb.design.common.security.bean.Authentication;
import com.cjlgb.design.oauth.service.ClientService;
import com.cjlgb.design.oauth.service.GrantService;
import com.cjlgb.design.oauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * @author WFT
 * @date 2020/6/14
 * description:密码模式
 */
@Service
@RequiredArgsConstructor
public class PasswordGrantServiceImpl implements GrantService {

    private final UserService userService;
    private final ClientService clientService;

    /**
     * 手机号码正则表达式
     */
    private final static Pattern MOBILE_REGEX =
            Pattern.compile("^((13[0-9])|(15[^4])|(18[0-9])|(17[0-9])|(147))\\d{8}$");

    @Override
    public Authentication grant(OauthParameter parameter) {
        Assert.hasText(parameter.getUsername(),"请输入用户名或手机号码");
        Assert.hasText(parameter.getPassword(),"密码不能为空");
        Assert.hasText(parameter.getSalt(),"随机盐不能为空");
        OauthUser user;
        //  判断是否为手机号码
        if (MOBILE_REGEX.matcher(parameter.getUsername()).matches()){
            user = this.userService.selectByMobile(parameter.getUsername());
            Assert.notNull(user,"手机号码有误");
        } else {
            user = this.userService.selectByUsername(parameter.getUsername());
            Assert.notNull(user,"用户名不存在");
        }
        Assert.isTrue(user.getLockFlag() == LockFlag.enable,"账号被锁定,请与管理员联系");
        //  密码校验
        String cipherText = this.userService.encryption(user.getPassword(),parameter.getSalt());
        Assert.isTrue(cipherText.equals(parameter.getPassword()),"用户名或密码错误");
        //  校验客户端Id与密钥
        OauthClient client = this.clientService.getById(parameter.getClientId());
        Assert.isTrue(this.getGrantType().equals(client.getGrantType()),"当前客户端不支持密码模式");
        Assert.isTrue(client.getSecret().equals(parameter.getClientSecret()),"客户端密钥不正确");
        //  返回凭证信息
        return new Authentication(user.getId(),new ArrayList<>(0));
    }

    @Override
    public GrantType getGrantType() {
        return GrantType.password;
    }
}
