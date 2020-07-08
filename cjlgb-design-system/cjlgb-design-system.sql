/* 初始化数据库 */
DROP DATABASE IF EXISTS `cjlgb_design_system`;
CREATE DATABASE  `cjlgb_design_system` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;
USE `cjlgb_design_system`;
SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

/* 初始化表格 */
DROP TABLE IF EXISTS `sms_provider`;
CREATE TABLE `sms_provider` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `provider_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '短信服务商名称',
  `app_private_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'AppPrivateKey',
  `lock_flag` tinyint(1) DEFAULT NULL COMMENT '锁定标记：{ -1:锁定,0:正常 }',
  `remarks` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `create_id` bigint(20) DEFAULT NULL COMMENT '创建人Id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='短信服务商';

DROP TABLE IF EXISTS `sms_send_record`;
CREATE TABLE `sms_send_record` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机号码',
  `context` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '短信内容',
  `provider_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '短信服务商名称',
  `type` tinyint(1) DEFAULT NULL COMMENT '模板类型：{ 1：会员注册验证码,2：短信登录验证码,3：找回密码验证码,4：异地登陆验证码,5：订单支付验证码 ... }',
  `create_id` bigint(20) DEFAULT NULL COMMENT '创建人Id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='短息发送记录';

DROP TABLE IF EXISTS `sms_template`;
CREATE TABLE `sms_template` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `sign` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '短信签名',
  `template` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '短信模板',
  `type` tinyint(1) DEFAULT NULL COMMENT '模板类型：{ 1：会员注册验证码,2：短信登录验证码,3：找回密码验证码,4：异地登陆验证码,5：订单支付验证码 ... }',
  `create_id` bigint(20) DEFAULT NULL COMMENT '创建人Id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='短信模板';