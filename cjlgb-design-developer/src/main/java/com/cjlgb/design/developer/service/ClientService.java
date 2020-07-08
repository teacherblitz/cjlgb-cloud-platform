package com.cjlgb.design.developer.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cjlgb.design.common.mybatis.enums.LockFlag;
import com.cjlgb.design.common.mybatis.service.BaseService;
import com.cjlgb.design.common.oauth.entity.OauthClient;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * @author WFT
 * @date 2020/6/14
 * description:
 */
public interface ClientService extends BaseService<OauthClient> {

    /**
     * 根据Id获取客户端信息
     * @param clientId java.io.Serializable
     * @return com.cjlgb.design.common.oauth.entity.OauthClient
     */
    default OauthClient selectById(Serializable clientId){
        OauthClient client = this.getById(clientId);
        Assert.notNull(client,"客户端Id不存在");
        Assert.isTrue(client.getLockFlag() == LockFlag.enable,"客户端被锁定,请与管理员联系");
        return client;
    }

    /**
     * 分页查询客户端列表
     * @param page 分页条件
     * @param parameter 查询条件
     * @return 分页结果
     */
    default IPage<OauthClient> pagingQuery(Page<OauthClient> page, OauthClient parameter){
        LambdaQueryWrapper<OauthClient> wrapper = Wrappers.lambdaQuery();
        //  根据开发者Id查询
        if (null != parameter.getDeveloperId()){
            wrapper.eq(OauthClient::getDeveloperId,parameter.getDeveloperId());
        }
        //  根据应用名称查询
        if (StringUtils.hasText(parameter.getAppName())){
            wrapper.likeRight(OauthClient::getAppName,parameter.getAppName());
        }
        //  分页查询
        return this.page(page,wrapper);
    }

    /**
     * 根据应用名称判单是否已存在
     * @param appName 应用名称
     * @return true:存在
     */
    default boolean isExistByAppName(String appName){
        return this.count(Wrappers.<OauthClient>lambdaQuery().eq(OauthClient::getAppName,appName)) > 0;
    }
}
