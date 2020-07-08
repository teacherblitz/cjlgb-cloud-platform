package com.cjlgb.design.common.system.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author WFT
 * @date 2020/5/14
 * description:短信推送数据传输对象
 */
@Getter
@Setter
@NoArgsConstructor
public class SmsPushDto implements java.io.Serializable {

    /**
     * 手机号码
     */
    @NotBlank(message = "手机号码不能为空")
    private String mobile;

    /**
     * 模板类型：{ 1：会员注册验证码,2：短信登录验证码,3：找回密码验证码,4：异地登陆验证码,5：订单支付验证码 ... }
     */
    @NotNull(message = "模板类型不能为空")
    private Integer type;

}
