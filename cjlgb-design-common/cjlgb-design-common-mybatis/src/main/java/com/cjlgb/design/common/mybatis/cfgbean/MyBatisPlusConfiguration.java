package com.cjlgb.design.common.mybatis.cfgbean;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.cjlgb.design.common.mybatis.enums.LockFlag;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.LocalDateTime;

/**
 * @author WFT
 * @date 2020/5/9
 * description: MybatisPlus配置类
 */
@Configuration
@EnableTransactionManagement
public class MyBatisPlusConfiguration {

    @Bean
    public ISqlInjector sqlInjector() {
        return new DefaultSqlInjector();
    }

    /**
     * 分页插件，自动识别数据库类型
     * 多租户，请参考官网【插件扩展】
     * @return PaginationInterceptor
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor().setOverflow(true);
    }

    /**
     * 自动填充插件
     * @return MetaObjectHandler
     */
    @Bean
    public MetaObjectHandler metaObjectHandler(){
        return new MetaObjectHandler(){
            @Override
            public void insertFill(MetaObject metaObject) {
                //  创建/最后修改时间
                LocalDateTime now = LocalDateTime.now();
                this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, now);
                this.strictInsertFill(metaObject, "lastModifyTime", LocalDateTime.class, now);
                //  锁定标记
                this.strictInsertFill(metaObject, "lockFlag", LockFlag.class, LockFlag.enable);
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                //  最后修改时间
                this.strictInsertFill(metaObject, "lastModifyTime", LocalDateTime.class, LocalDateTime.now());
            }
        };
    }

}
