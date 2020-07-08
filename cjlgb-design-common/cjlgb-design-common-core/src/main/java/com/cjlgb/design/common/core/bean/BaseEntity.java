package com.cjlgb.design.common.core.bean;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author WFT
 * @date 2020/5/9
 * description: 所有实体类的公共父类
 */
@Getter
@Setter
public class BaseEntity implements java.io.Serializable{

    @NotNull(message = "Id不能为空",groups = Validation.Edit.class)
    private Long id;

}
