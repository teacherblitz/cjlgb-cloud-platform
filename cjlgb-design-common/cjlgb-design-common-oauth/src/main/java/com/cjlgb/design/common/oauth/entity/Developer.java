package com.cjlgb.design.common.oauth.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cjlgb.design.common.core.bean.BaseEntity;
import com.cjlgb.design.common.mybatis.enums.AuditFlag;
import com.cjlgb.design.common.mybatis.enums.IdentityFlag;
import com.cjlgb.design.common.mybatis.handler.NumberEnumTypeHandler;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author WFT
 * @date 2020/6/27
 * description:开发者账号
 */
@Getter
@Setter
@NoArgsConstructor
@TableName(autoResultMap = true)
public class Developer extends BaseEntity {

    /**
     * 身份标识
     */
    @NotNull(message = "身份类型不能为空")
    @TableField(typeHandler = NumberEnumTypeHandler.class)
    private IdentityFlag identityFlag;

    /**
     * 联系人姓名
     */
    @NotBlank(message = "联系人姓名不能为空")
    private String contactName;

    /**
     * 联系人电话
     */
    @NotBlank(message = "联系人电话不能为空")
    private String contactMobile;

    /**
     * 联系人邮箱
     */
    @NotBlank(message = "联系人邮箱不能为空")
    private String contactEmail;

    /**
     * 个人身份证
     */
    private String identity;

    /**
     * 企业名称
     */
    private String companyName;

    /**
     * 统一社会信用代码
     */
    private String companyCode;

    /**
     * 申请时间
     */
    private LocalDateTime applyTime;

    /**
     * 申请人Ip
     */
    private String applyIp;

    /**
     * 申请人Id
     */
    private Long applyId;

    /**
     * 操作时间
     */
    private LocalDateTime operatingTime;

    /**
     * 操作人Ip
     */
    private String operatingIp;

    /**
     * 操作人Id
     */
    private Long operatingId;

    /**
     * 审核标识
     */
    @TableField(typeHandler = NumberEnumTypeHandler.class)
    private AuditFlag auditFlag;

    /**
     * 备注
     */
    private String remarks;

}
