package com.cjlgb.design.common.oauth.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cjlgb.design.common.core.bean.BaseEntity;
import com.cjlgb.design.common.core.bean.Validation;
import com.cjlgb.design.common.mybatis.enums.LockFlag;
import com.cjlgb.design.common.mybatis.handler.NumberEnumTypeHandler;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @author WFT
 * @date 2020/5/9
 * description:用户
 */
@Getter
@Setter
@NoArgsConstructor
@TableName(autoResultMap = true)
public class OauthUser extends BaseEntity {

    /**
     * 手机号码
     */
    @NotBlank(message = "手机号码不能为空",groups = { Validation.Add.class })
    private String userMobile;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空",groups = { Validation.Add.class })
    private String username;

    /**
     * 登陆密码：md5(明文 + 随机盐)
     */
    @NotBlank(message = "登陆密码不能为空",groups = { Validation.Add.class })
    private String password;

    /**
     * 用户头像
     */
    private String portrait;

    /**
     * 锁定标记：{ -1:锁定,0:正常 }
     */
    @TableField(fill = FieldFill.INSERT,typeHandler = NumberEnumTypeHandler.class)
    private LockFlag lockFlag;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

}
