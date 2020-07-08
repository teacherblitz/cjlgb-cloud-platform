package com.cjlgb.design.developer.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cjlgb.design.common.mybatis.enums.AuditFlag;
import com.cjlgb.design.common.mybatis.service.BaseService;
import com.cjlgb.design.common.oauth.entity.Developer;

import java.io.Serializable;

/**
 * @author WFT
 * @date 2020/6/27
 * description:开发者CRUD接口
 */
public interface DeveloperService extends BaseService<Developer> {

    /**
     * 根据申请人Id获取开发者信息
     * @param userId java.io.Serializable
     * @return com.cjlgb.design.common.oauth.entity.Developer
     */
    default Developer selectByApplyId(Serializable userId){
        return this.getOne(Wrappers.<Developer>lambdaQuery().eq(Developer::getApplyId,userId));
    }

    /**
     * 分页查询开发者账号列表
     * @param page 查询条件
     * @param parameter 查询条件
     * @return 分页结果
     */
    default IPage<Developer> pagingQuery(Page<Developer> page, Developer parameter){
        LambdaQueryWrapper<Developer> wrapper = Wrappers.lambdaQuery();
        //  根据身份类型查询
        if (null != parameter.getIdentityFlag()){
            wrapper.eq(Developer::getIdentityFlag,parameter.getIdentityFlag());
        }
        //  根据审核状态查询
        if (null != parameter.getAuditFlag()){
            wrapper.eq(Developer::getAuditFlag,parameter.getAuditFlag());
        }
        //  过滤待提交记录
        wrapper.notIn(Developer::getAuditFlag,AuditFlag.sleep);
        //  分页查询
        return this.page(page,wrapper);
    }
}
