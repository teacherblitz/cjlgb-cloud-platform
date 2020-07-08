package com.cjlgb.design.common.mybatis.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cjlgb.design.common.core.bean.BaseEntity;
import com.cjlgb.design.common.mybatis.service.BaseService;

/**
 * @author WFT
 * @date 2020/5/9
 * description:公共服务层接口实现类
 */
public class BaseServiceImpl<Mapper extends BaseMapper<Entity>,Entity extends BaseEntity>
        extends ServiceImpl<Mapper,Entity>
        implements BaseService<Entity> {

}
