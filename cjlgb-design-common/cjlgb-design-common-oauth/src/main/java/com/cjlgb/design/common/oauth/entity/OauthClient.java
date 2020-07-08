package com.cjlgb.design.common.oauth.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cjlgb.design.common.core.bean.BaseEntity;
import com.cjlgb.design.common.mybatis.enums.LockFlag;
import com.cjlgb.design.common.mybatis.handler.NumberEnumTypeHandler;
import com.cjlgb.design.common.oauth.enums.GrantType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @author WFT
 * @date 2020/6/14
 * description:Oauth客户端
 */
@Getter
@Setter
@NoArgsConstructor
@TableName(autoResultMap = true)
public class OauthClient extends BaseEntity {

    /**
     * 客户端密钥
     */
    private String secret;

    /**
     * 授权方式：{ 1：授权码模式, 2：密码模式  }
     */
    @TableField(typeHandler = NumberEnumTypeHandler.class)
    private GrantType grantType;

    /**
     * 应用名称
     */
    @NotBlank(message = "应用名称必填")
    private String appName;

    /**
     * 应用Logo
     */
    @NotBlank(message = "应用图标必填")
    private String appLogo;

    /**
     * 应用描述
     */
    private String appDesc;

    /**
     * 回调地址
     */
    @NotBlank(message = "回调地址不能为空")
    private String callback;

    /**
     * 开发者Id
     */
    private Long developerId;

    /**
     * 锁定标记:{ -1 : 锁定, 0 : 正常 }
     */
    @TableField(fill = FieldFill.INSERT,typeHandler = NumberEnumTypeHandler.class)
    private LockFlag lockFlag;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

}
