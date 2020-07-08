package com.cjlgb.design.common.system.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.cjlgb.design.common.core.bean.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author WFT
 * @date 2020/5/13
 * description:短信模板
 */
@Getter
@Setter
@NoArgsConstructor
public class SmsTemplate extends BaseEntity {

    /**
     * 短信签名
     */
    private String sign;

    /**
     * 短信模板
     */
    private String template;

    /**
     * 模板类型：{ 1：会员注册验证码,2：短信登录验证码,3：找回密码验证码,4：异地登陆验证码,5：订单支付验证码 ... }
     */
    private Integer type;

    /**
     * 创建人
     */
    private String createId;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

}
