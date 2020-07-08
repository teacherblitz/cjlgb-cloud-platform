package com.cjlgb.design.common.security.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

/**
 * @author WFT
 * @date 2020/5/6
 * description: 账号的认证信息
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Authentication implements java.io.Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 权限列表
     */
    private Collection<String> authorities;

}
