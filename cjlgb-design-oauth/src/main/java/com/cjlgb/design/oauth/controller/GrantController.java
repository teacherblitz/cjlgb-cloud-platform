package com.cjlgb.design.oauth.controller;

import com.cjlgb.design.common.core.util.StrUtils;
import com.cjlgb.design.common.mybatis.enums.LockFlag;
import com.cjlgb.design.common.oauth.enums.GrantType;
import com.cjlgb.design.common.oauth.dto.OauthParameter;
import com.cjlgb.design.common.security.annotation.PreAuthorize;
import com.cjlgb.design.common.security.bean.AccessToken;
import com.cjlgb.design.common.security.bean.Authentication;
import com.cjlgb.design.common.security.service.SecurityService;
import com.cjlgb.design.common.security.context.SecurityContextHolder;
import com.cjlgb.design.common.oauth.entity.OauthClient;
import com.cjlgb.design.oauth.service.ClientService;
import com.cjlgb.design.oauth.service.GrantCodeService;
import com.cjlgb.design.oauth.service.GrantFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author WFT
 * @date 2020/6/14
 * description:认证控制器
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/grant")
public class GrantController {

    private final GrantFactory grantFactory;
    private final GrantCodeService grantCodeService;
    private final ClientService clientService;

    @Resource
    @Qualifier(value = "securityServiceByJwtImpl")
    private SecurityService securityService;

    private final static String CLIENT_ID = "10001";
    private final static String CLIENT_SECRET = "c1940b54-288d-11ea-b6bb-0242ac120004";

    /**
     * 账号登陆
     * @param parameter 请求参数
     * @return com.cjlgb.design.common.security.bean.AccessToken
     */
    @PostMapping(value = "/login")
    public AccessToken login(@RequestBody OauthParameter parameter){
        parameter.setClientId(CLIENT_ID);
        parameter.setClientSecret(CLIENT_SECRET);
        //  认证登录,返回AccessToken
        return this.getToken(GrantType.password,parameter);
    }

    private AccessToken getToken(GrantType grantType,OauthParameter parameter){
        //  根据认证方式获取相应的实现,并进行认证
        Authentication authentication = this.grantFactory.getInterface(grantType).grant(parameter);
        //  返回AccessToken
        return this.securityService.generateToken(authentication);
    }

    /**
     * 获取访问令牌
     * @param parameter 请求参数
     * @param basicAuth base编码（客户端Id:客户端密钥）
     * @return com.cjlgb.design.common.core.bean.ResultBean<AccessToken>
     */
    @PostMapping(value = "/token")
    public AccessToken getToken(
            @RequestBody @Validated OauthParameter parameter,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String basicAuth){
        Assert.notNull(parameter.getGrantType(),"认证方式不能为空");
        //  解码
        String[] fields = StrUtils.getInstance().decodeBasicAuthStr(basicAuth);
        Assert.isTrue(fields.length == 2,"客户端信息有误");
        //  填充客户端Id和密钥
        parameter.setClientId(fields[0]);
        parameter.setClientSecret(fields[1]);
        //  根据认证方式获取相应的实现,并进行认证,返回AccessToken
        return this.getToken(parameter.getGrantType(),parameter);
    }

    /**
     * 申请授权码
     * @param parameter 请求参数
     * @return 授权码
     */
    @PreAuthorize
    @PostMapping(value = "/apply/code")
    public String applyCode(@RequestBody OauthParameter parameter){
        Assert.notNull(parameter.getClientId(),"客户端Id不能为空");
        //  获取客户端信息
        OauthClient client = this.clientService.getById(parameter.getClientId());
        Assert.notNull(client,"客户端Id不存在");
        Assert.isTrue(client.getLockFlag() == LockFlag.enable,"客户端被锁定,请与管理员联系");
        Assert.isTrue(client.getGrantType().equals(GrantType.authorization_code),"此客户端不支持授权码模式");
        //  获取当前用户Id
        Long userId = SecurityContextHolder.getAuthentication().getId();
        //  生成授权码对象,并返回授权码
        return this.grantCodeService.generateCode(userId,client).getCode();
    }

}
