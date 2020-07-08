package com.cjlgb.design.common.security.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author WFT
 * @date 2020/6/13
 * description:
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccessToken implements java.io.Serializable {

    /**
     * 访问令牌
     */
    private String token;

    /**
     * 标记
     */
    private String flag;

}
