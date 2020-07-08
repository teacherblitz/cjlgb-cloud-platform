package com.cjlgb.design.developer.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cjlgb.design.common.oauth.entity.OauthClient;
import com.cjlgb.design.common.oauth.enums.GrantType;
import com.cjlgb.design.common.security.annotation.PreAuthorize;
import com.cjlgb.design.common.security.context.SecurityContextHolder;
import com.cjlgb.design.developer.service.ClientService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author WFT
 * @date 2020/6/15
 * description:
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/client")
public class ClientController {

    private final ClientService clientService;

    /**
     * 分页查询客户端列表
     * @param page 分页条件
     * @param parameter 查询条件
     * @return 分页结果
     */
    @PreAuthorize
    @GetMapping(value = "/page")
    public IPage<OauthClient> pagingQuery(Page<OauthClient> page, OauthClient parameter){
        parameter = null == parameter ? new OauthClient() : parameter;
        //  获取当前用户Id
        Long userId = SecurityContextHolder.getAuthentication().getId();
        parameter.setDeveloperId(userId);
        //  分页查询
        return this.clientService.pagingQuery(page,parameter);
    }

    /**
     * 创建Oauth客户端
     * @param parameter 请求参数
     */
    @PreAuthorize
    @PostMapping
    public void add(@RequestBody @Validated OauthClient parameter){
        //  判断应用名称是否已存在
        boolean isExist = this.clientService.isExistByAppName(parameter.getAppName());
        Assert.isTrue(!isExist,"应用名称已存在");
        //  获取当前用户Id
        Long userId = SecurityContextHolder.getAuthentication().getId();
        parameter.setDeveloperId(userId);
        //  生成密钥
        parameter.setSecret(UUID.randomUUID().toString());
        //  设为授权码模式
        parameter.setGrantType(GrantType.authorization_code);
        this.clientService.save(parameter);
    }

    /**
     * 获取某客户端信息
     * @param clientId 客户端Id
     * @return com.cjlgb.design.common.oauth.entity.OauthClient
     */
    @PreAuthorize
    @GetMapping(value = "/{clientId}")
    @JsonIgnoreProperties(value = "secret")
    public OauthClient get(@PathVariable(value = "clientId") Long clientId){
        return this.clientService.selectById(clientId);
    }

}
