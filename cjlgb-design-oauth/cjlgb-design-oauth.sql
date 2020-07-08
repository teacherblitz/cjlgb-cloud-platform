/* 初始化数据库 */
DROP DATABASE IF EXISTS `cjlgb_design_oauth`;
CREATE DATABASE  `cjlgb_design_oauth` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;
USE `cjlgb_design_oauth`;
SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

/* 初始化表格 */
DROP TABLE IF EXISTS `oauth_user`;
CREATE TABLE `oauth_user` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `user_mobile` varchar(11) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机号码',
  `username` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登陆密码：md5(明文 + 随机盐)',
  `portrait` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户头像',
  `lock_flag` tinyint(1) DEFAULT NULL COMMENT '锁定标记',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户表';

INSERT INTO `cjlgb_design_oauth`.`oauth_user` (`id`, `user_mobile`, `username`, `password`, `portrait`, `lock_flag`, `create_time`) VALUES ('10001', '13250248984', 'cjlgb', '44cff287b069d49bb073760d8935ae1e', 'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1898470484,687743959&fm=26&gp=0.jpg', '0', '2020-06-14 17:18:16');

DROP TABLE IF EXISTS `oauth_client`;
CREATE TABLE `oauth_client` (
  `id` bigint(20) NOT NULL COMMENT '客户端Id',
  `secret` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '客户端密钥',
  `grant_type` tinyint(1) DEFAULT NULL COMMENT '授权方式：{ 1：授权码模式, 2：密码模式  }',
  `app_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '应用名称',
  `app_logo` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '应用Logo',
  `app_desc` varchar(300) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '应用描述',
  `callback` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '回调地址',
  `developer_id` bigint(20) DEFAULT NULL COMMENT '创建人Id',
  `lock_flag` tinyint(1) DEFAULT NULL COMMENT '锁定标记',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='Oauth客户端';

INSERT INTO `cjlgb_design_oauth`.`oauth_client` (`id`, `secret`, `grant_type`, `app_name`, `app_logo`, `app_desc`, `callback`, `developer_id`, `lock_flag`, `create_time`) VALUES ('10001', 'c1940b54-288d-11ea-b6bb-0242ac120004', '1', '单点登录平台', 'https://cjlgb-design-oauth.cdn.bcebos.com/favicon.png', '....', '', '10001', '0', '2020-06-14 17:29:02');
INSERT INTO `cjlgb_design_oauth`.`oauth_client` (`id`, `secret`, `grant_type`, `app_name`, `app_logo`, `app_desc`, `callback`, `developer_id`, `lock_flag`, `create_time`) VALUES ('10002', 'c1940b54-288d-11ea-b6bb-0242ac120004', '2', '吃惊开放平台', 'https://cjlgb-design-oauth.cdn.bcebos.com/favicon.png', NULL, 'http://open.cjlgb.com/#/oauth', '10001', '0', '2020-06-27 21:43:29');

DROP TABLE IF EXISTS `developer`;
CREATE TABLE `developer` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `identity_flag` tinyint(1) DEFAULT NULL COMMENT '身份标识',
  `contact_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系人姓名',
  `contact_mobile` varchar(11) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系人电话',
  `contact_email` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系人邮箱',
  `identity` varchar(18) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '个人身份证',
  `company_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '企业名称',
  `company_code` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '统一社会信用代码',
  `apply_time` datetime DEFAULT NULL COMMENT '申请时间',
  `apply_ip` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '申请人Ip',
  `apply_id` bigint(20) DEFAULT NULL COMMENT '申请人Id',
  `operating_time` datetime DEFAULT NULL COMMENT '操作时间',
  `operating_ip` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作人Ip',
  `operating_id` bigint(20) DEFAULT NULL COMMENT '操作人Id',
  `audit_flag` tinyint(1) DEFAULT NULL COMMENT '审核标识',
  `remarks` varchar(300) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='开发者信息表';
