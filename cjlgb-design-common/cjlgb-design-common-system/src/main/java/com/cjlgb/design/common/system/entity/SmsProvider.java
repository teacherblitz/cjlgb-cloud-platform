package com.cjlgb.design.common.system.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cjlgb.design.common.core.bean.BaseEntity;
import com.cjlgb.design.common.mybatis.enums.LockFlag;
import com.cjlgb.design.common.mybatis.handler.NumberEnumTypeHandler;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author WFT
 * @date 2020/5/13
 * description:短信服务商
 */
@Getter
@Setter
@NoArgsConstructor
@TableName(autoResultMap = true)
public class SmsProvider extends BaseEntity {

    /**
     * 短信服务商名称
     */
    private String providerName;

    /**
     * AppPrivateKey
     */
    private String appPrivateKey;

    /**
     * 锁定标记:{ -1 : 锁定, 0 : 正常 }
     */
    @TableField(fill = FieldFill.INSERT,typeHandler = NumberEnumTypeHandler.class)
    private LockFlag lockFlag;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 创建人
     */
    private Long createId;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

}
