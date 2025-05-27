/*
 Navicat Premium Dump SQL

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80040 (8.0.40)
 Source Host           : localhost:3306
 Source Schema         : jimuqu_db

 Target Server Type    : MySQL
 Target Server Version : 80040 (8.0.40)
 File Encoding         : 65001

 Date: 27/05/2025 14:52:35
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table`
(
    `id`                bigint NOT NULL COMMENT '主键',
    `data_name`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '数据源名称',
    `table_name`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '表名称',
    `table_comment`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '表描述',
    `sub_table_name`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '关联父表的表名',
    `sub_table_fk_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '本表关联父表的外键名',
    `class_name`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '实体类名称(首字母大写)',
    `tpl_category`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '使用的模板（crud单表操作 tree树表操作 sub主子表操作）',
    `package_name`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生成包路径',
    `module_name`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生成模块名',
    `business_name`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生成业务名',
    `function_name`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生成功能名',
    `function_author`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生成作者',
    `gen_type`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
    `gen_path`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生成路径（不填默认项目路径）',
    `options`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '其它生成选项',
    `remark`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
    `create_dept`       bigint NULL DEFAULT NULL COMMENT '创建部门',
    `create_by`         bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time`       datetime NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`         bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time`       datetime NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '业务表 gen_table' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column`
(
    `id`             bigint NOT NULL COMMENT '主键',
    `table_id`       bigint NULL DEFAULT NULL COMMENT '归属表编号',
    `column_name`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '列名称',
    `column_comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '列描述',
    `column_type`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '列类型',
    `java_type`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'JAVA类型',
    `java_field`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'JAVA字段名',
    `is_pk`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否主键（1是）',
    `is_increment`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否自增（1是）',
    `is_required`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否必填（1是）',
    `is_insert`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否为插入字段（1是）',
    `is_edit`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否编辑字段（1是）',
    `is_list`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否列表字段（1是）',
    `is_query`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否查询字段（1是）',
    `query_type`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '查询方式（EQ等于、NE不等于、GT大于、LT小于、LIKE模糊、BETWEEN范围）',
    `html_type`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '显示类型（input文本框、textarea文本域、select下拉框、checkbox复选框、radio单选框、datetime日期控件、image图片上传控件、upload文件上传控件、editor富文本控件）',
    `dict_type`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '字典类型',
    `sort`           int NULL DEFAULT NULL COMMENT '排序',
    `create_dept`    bigint NULL DEFAULT NULL COMMENT '创建部门',
    `create_by`      bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time`    datetime NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`      bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time`    datetime NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '代码生成业务字段表 gen_table_column' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for gen_template
-- ----------------------------
DROP TABLE IF EXISTS `gen_template`;
CREATE TABLE `gen_template`
(
    `id`          bigint NOT NULL COMMENT '主键',
    `category`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '模板分类，用于批量选择模板',
    `name`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '模板名称',
    `path`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '文件路径',
    `db_type`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '数据库类型',
    `content`     text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '模板内容',
    `enable`      int NULL DEFAULT 1 COMMENT '是否启用',
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
    `create_dept` bigint NULL DEFAULT NULL COMMENT '创建部门',
    `create_by`   bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '代码模板' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_client
-- ----------------------------
DROP TABLE IF EXISTS `sys_client`;
CREATE TABLE `sys_client`
(
    `id`             bigint NOT NULL COMMENT '主键',
    `client_id`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户端id',
    `client_key`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户端key',
    `client_secret`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户端秘钥',
    `grant_type`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '授权类型',
    `device_type`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '设备类型',
    `active_timeout` bigint NULL DEFAULT NULL COMMENT 'token活跃超时时间',
    `timeout`        bigint NULL DEFAULT NULL COMMENT 'token固定超时时间',
    `status`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '状态（0正常 1停用）',
    `del_flag`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '删除标志（0代表存在 2代表删除）',
    `create_dept`    bigint NULL DEFAULT NULL COMMENT '创建部门',
    `create_by`      bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time`    datetime NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`      bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time`    datetime NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '授权管理对象 sys_client' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_client
-- ----------------------------
INSERT INTO `sys_client`
VALUES (1, 'e5cd7e4891bf95d1d19206ce24a7b32e', 'pc', 'pc123', 'password,social', 'pc', 1800, 604800, '0', '0', 103, 1,
        '2024-07-31 18:46:52', 1, '2024-07-31 18:46:52');
INSERT INTO `sys_client`
VALUES (2, '428a8310cd442757ae699df5d894f051', 'app', 'app123', 'password,sms,social', 'android', 1800, 604800, '0',
        '0', 103, 1, '2024-07-31 18:46:52', 1, '2024-07-31 18:46:52');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`
(
    `id`           bigint NOT NULL COMMENT '参数主键',
    `tenant_id`    varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '000000' COMMENT '租户编号',
    `config_name`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '参数名称',
    `config_key`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '参数键名',
    `config_value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '参数键值',
    `config_type`  char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
    `create_dept`  bigint NULL DEFAULT NULL COMMENT '创建部门',
    `create_by`    bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time`  datetime NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`    bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time`  datetime NULL DEFAULT NULL COMMENT '更新时间',
    `remark`       varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '参数配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config`
VALUES (1, '000000', '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 103, 1, '2024-07-31 18:46:52',
        NULL, NULL, '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config`
VALUES (2, '000000', '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 103, 1, '2024-07-31 18:46:52',
        NULL, NULL, '初始化密码 123456');
INSERT INTO `sys_config`
VALUES (3, '000000', '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 103, 1, '2024-07-31 18:46:52',
        NULL, NULL, '深色主题theme-dark，浅色主题theme-light');
INSERT INTO `sys_config`
VALUES (5, '000000', '账号自助-是否开启用户注册功能', 'sys.account.registerUser', 'false', 'Y', 103, 1,
        '2024-07-31 18:46:52', NULL, NULL, '是否开启注册用户功能（true开启，false关闭）');
INSERT INTO `sys_config`
VALUES (11, '000000', 'OSS预览列表资源开关', 'sys.oss.previewListResource', 'true', 'Y', 103, 1, '2024-07-31 18:46:52',
        NULL, NULL, 'true:开启, false:关闭');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`
(
    `id`          bigint NOT NULL COMMENT '主键',
    `dept_id`     bigint NULL DEFAULT NULL COMMENT '部门id',
    `parent_id`   bigint NULL DEFAULT NULL COMMENT '父部门id',
    `ancestors`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '祖级列表',
    `dept_name`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '部门名称',
    `order_num`   int NULL DEFAULT NULL COMMENT '显示顺序',
    `leader`      bigint NULL DEFAULT NULL COMMENT '负责人',
    `phone`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '联系电话',
    `email`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '邮箱',
    `status`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '部门状态（0正常 1停用）',
    `del_flag`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '删除标志（0代表存在 2代表删除）',
    `tenant_id`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '租户Id',
    `create_dept` bigint NULL DEFAULT NULL COMMENT '创建部门',
    `create_by`   bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '<p>\n 部门\n </p>' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept`
VALUES (100, NULL, 0, '0', 'XXX科技', 0, NULL, '15888888888', 'xxx@qq.com', '0', '0', NULL, 103, 1,
        '2024-07-31 18:46:50', NULL, NULL);
INSERT INTO `sys_dept`
VALUES (101, NULL, 100, '0,100', '深圳总公司', 1, NULL, '15888888888', 'xxx@qq.com', '0', '0', NULL, 103, 1,
        '2024-07-31 18:46:50', NULL, NULL);
INSERT INTO `sys_dept`
VALUES (102, NULL, 100, '0,100', '长沙分公司', 2, NULL, '15888888888', 'xxx@qq.com', '0', '0', NULL, 103, 1,
        '2024-07-31 18:46:50', NULL, NULL);
INSERT INTO `sys_dept`
VALUES (103, NULL, 101, '0,100,101', '研发部门', 1, 1, '15888888888', 'xxx@qq.com', '0', '0', NULL, 103, 1,
        '2024-07-31 18:46:50', NULL, NULL);
INSERT INTO `sys_dept`
VALUES (104, NULL, 101, '0,100,101', '市场部门', 2, NULL, '15888888888', 'xxx@qq.com', '0', '0', NULL, 103, 1,
        '2024-07-31 18:46:50', NULL, NULL);
INSERT INTO `sys_dept`
VALUES (105, NULL, 101, '0,100,101', '测试部门', 3, NULL, '15888888888', 'xxx@qq.com', '0', '0', NULL, 103, 1,
        '2024-07-31 18:46:50', NULL, NULL);
INSERT INTO `sys_dept`
VALUES (106, NULL, 101, '0,100,101', '财务部门', 4, NULL, '15888888888', 'xxx@qq.com', '0', '0', NULL, 103, 1,
        '2024-07-31 18:46:50', NULL, NULL);
INSERT INTO `sys_dept`
VALUES (107, NULL, 101, '0,100,101', '运维部门', 5, NULL, '15888888888', 'xxx@qq.com', '0', '0', NULL, 103, 1,
        '2024-07-31 18:46:50', NULL, NULL);
INSERT INTO `sys_dept`
VALUES (108, NULL, 102, '0,100,102', '市场部门', 1, NULL, '15888888888', 'xxx@qq.com', '0', '0', NULL, 103, 1,
        '2024-07-31 18:46:50', NULL, NULL);
INSERT INTO `sys_dept`
VALUES (109, NULL, 102, '0,100,102', '财务部门', 2, NULL, '15888888888', 'xxx@qq.com', '0', '0', NULL, 103, 1,
        '2024-07-31 18:46:50', NULL, NULL);

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`
(
    `dict_code`   bigint NOT NULL COMMENT '字典编码',
    `tenant_id`   varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '000000' COMMENT '租户编号',
    `dict_sort`   int NULL DEFAULT 0 COMMENT '字典排序',
    `dict_label`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '字典标签',
    `dict_value`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '字典键值',
    `dict_type`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '字典类型',
    `css_class`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
    `list_class`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '表格回显样式',
    `is_default`  char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
    `create_dept` bigint NULL DEFAULT NULL COMMENT '创建部门',
    `create_by`   bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
    `remark`      varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '字典数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data`
VALUES (1, '000000', 1, '男', '0', 'sys_user_sex', '', '', 'Y', 103, 1, '2024-07-31 18:46:52', NULL, NULL, '性别男');
INSERT INTO `sys_dict_data`
VALUES (2, '000000', 2, '女', '1', 'sys_user_sex', '', '', 'N', 103, 1, '2024-07-31 18:46:52', NULL, NULL, '性别女');
INSERT INTO `sys_dict_data`
VALUES (3, '000000', 3, '未知', '2', 'sys_user_sex', '', '', 'N', 103, 1, '2024-07-31 18:46:52', NULL, NULL,
        '性别未知');
INSERT INTO `sys_dict_data`
VALUES (4, '000000', 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', 103, 1, '2024-07-31 18:46:52', NULL, NULL,
        '显示菜单');
INSERT INTO `sys_dict_data`
VALUES (5, '000000', 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', 103, 1, '2024-07-31 18:46:52', NULL, NULL,
        '隐藏菜单');
INSERT INTO `sys_dict_data`
VALUES (6, '000000', 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', 103, 1, '2024-07-31 18:46:52', NULL,
        NULL, '正常状态');
INSERT INTO `sys_dict_data`
VALUES (7, '000000', 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', 103, 1, '2024-07-31 18:46:52', NULL, NULL,
        '停用状态');
INSERT INTO `sys_dict_data`
VALUES (12, '000000', 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', 103, 1, '2024-07-31 18:46:52', NULL, NULL,
        '系统默认是');
INSERT INTO `sys_dict_data`
VALUES (13, '000000', 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', 103, 1, '2024-07-31 18:46:52', NULL, NULL,
        '系统默认否');
INSERT INTO `sys_dict_data`
VALUES (14, '000000', 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', 103, 1, '2024-07-31 18:46:52', NULL, NULL,
        '通知');
INSERT INTO `sys_dict_data`
VALUES (15, '000000', 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', 103, 1, '2024-07-31 18:46:52', NULL, NULL,
        '公告');
INSERT INTO `sys_dict_data`
VALUES (16, '000000', 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', 103, 1, '2024-07-31 18:46:52', NULL,
        NULL, '正常状态');
INSERT INTO `sys_dict_data`
VALUES (17, '000000', 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', 103, 1, '2024-07-31 18:46:52', NULL, NULL,
        '关闭状态');
INSERT INTO `sys_dict_data`
VALUES (18, '000000', 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', 103, 1, '2024-07-31 18:46:52', NULL, NULL,
        '新增操作');
INSERT INTO `sys_dict_data`
VALUES (19, '000000', 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', 103, 1, '2024-07-31 18:46:52', NULL, NULL,
        '修改操作');
INSERT INTO `sys_dict_data`
VALUES (20, '000000', 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', 103, 1, '2024-07-31 18:46:52', NULL, NULL,
        '删除操作');
INSERT INTO `sys_dict_data`
VALUES (21, '000000', 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', 103, 1, '2024-07-31 18:46:52', NULL, NULL,
        '授权操作');
INSERT INTO `sys_dict_data`
VALUES (22, '000000', 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', 103, 1, '2024-07-31 18:46:52', NULL, NULL,
        '导出操作');
INSERT INTO `sys_dict_data`
VALUES (23, '000000', 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', 103, 1, '2024-07-31 18:46:52', NULL, NULL,
        '导入操作');
INSERT INTO `sys_dict_data`
VALUES (24, '000000', 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', 103, 1, '2024-07-31 18:46:52', NULL, NULL,
        '强退操作');
INSERT INTO `sys_dict_data`
VALUES (25, '000000', 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', 103, 1, '2024-07-31 18:46:52', NULL,
        NULL, '生成操作');
INSERT INTO `sys_dict_data`
VALUES (26, '000000', 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', 103, 1, '2024-07-31 18:46:52', NULL, NULL,
        '清空操作');
INSERT INTO `sys_dict_data`
VALUES (27, '000000', 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', 103, 1, '2024-07-31 18:46:52', NULL,
        NULL, '正常状态');
INSERT INTO `sys_dict_data`
VALUES (28, '000000', 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', 103, 1, '2024-07-31 18:46:52', NULL, NULL,
        '停用状态');
INSERT INTO `sys_dict_data`
VALUES (29, '000000', 99, '其他', '0', 'sys_oper_type', '', 'info', 'N', 103, 1, '2024-07-31 18:46:52', NULL, NULL,
        '其他操作');
INSERT INTO `sys_dict_data`
VALUES (30, '000000', 0, '密码认证', 'password', 'sys_grant_type', 'el-check-tag', 'default', 'N', 103, 1,
        '2024-07-31 18:46:52', NULL, NULL, '密码认证');
INSERT INTO `sys_dict_data`
VALUES (31, '000000', 0, '短信认证', 'sms', 'sys_grant_type', 'el-check-tag', 'default', 'N', 103, 1,
        '2024-07-31 18:46:52', NULL, NULL, '短信认证');
INSERT INTO `sys_dict_data`
VALUES (32, '000000', 0, '邮件认证', 'email', 'sys_grant_type', 'el-check-tag', 'default', 'N', 103, 1,
        '2024-07-31 18:46:52', NULL, NULL, '邮件认证');
INSERT INTO `sys_dict_data`
VALUES (33, '000000', 0, '小程序认证', 'xcx', 'sys_grant_type', 'el-check-tag', 'default', 'N', 103, 1,
        '2024-07-31 18:46:52', NULL, NULL, '小程序认证');
INSERT INTO `sys_dict_data`
VALUES (34, '000000', 0, '三方登录认证', 'social', 'sys_grant_type', 'el-check-tag', 'default', 'N', 103, 1,
        '2024-07-31 18:46:52', NULL, NULL, '三方登录认证');
INSERT INTO `sys_dict_data`
VALUES (35, '000000', 0, 'PC', 'pc', 'sys_device_type', '', 'default', 'N', 103, 1, '2024-07-31 18:46:52', NULL, NULL,
        'PC');
INSERT INTO `sys_dict_data`
VALUES (36, '000000', 0, '安卓', 'android', 'sys_device_type', '', 'default', 'N', 103, 1, '2024-07-31 18:46:52', NULL,
        NULL, '安卓');
INSERT INTO `sys_dict_data`
VALUES (37, '000000', 0, 'iOS', 'ios', 'sys_device_type', '', 'default', 'N', 103, 1, '2024-07-31 18:46:52', NULL, NULL,
        'iOS');
INSERT INTO `sys_dict_data`
VALUES (38, '000000', 0, '小程序', 'xcx', 'sys_device_type', '', 'default', 'N', 103, 1, '2024-07-31 18:46:52', NULL,
        NULL, '小程序');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`
(
    `dict_id`     bigint NOT NULL COMMENT '字典主键',
    `tenant_id`   varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '000000' COMMENT '租户编号',
    `dict_name`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '字典名称',
    `dict_type`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '字典类型',
    `create_dept` bigint NULL DEFAULT NULL COMMENT '创建部门',
    `create_by`   bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
    `remark`      varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`dict_id`) USING BTREE,
    UNIQUE INDEX `tenant_id`(`tenant_id` ASC, `dict_type` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type`
VALUES (1, '000000', '用户性别', 'sys_user_sex', 103, 1, '2024-07-31 18:46:52', NULL, NULL, '用户性别列表');
INSERT INTO `sys_dict_type`
VALUES (2, '000000', '菜单状态', 'sys_show_hide', 103, 1, '2024-07-31 18:46:52', NULL, NULL, '菜单状态列表');
INSERT INTO `sys_dict_type`
VALUES (3, '000000', '系统开关', 'sys_normal_disable', 103, 1, '2024-07-31 18:46:52', NULL, NULL, '系统开关列表');
INSERT INTO `sys_dict_type`
VALUES (6, '000000', '系统是否', 'sys_yes_no', 103, 1, '2024-07-31 18:46:52', NULL, NULL, '系统是否列表');
INSERT INTO `sys_dict_type`
VALUES (7, '000000', '通知类型', 'sys_notice_type', 103, 1, '2024-07-31 18:46:52', NULL, NULL, '通知类型列表');
INSERT INTO `sys_dict_type`
VALUES (8, '000000', '通知状态', 'sys_notice_status', 103, 1, '2024-07-31 18:46:52', NULL, NULL, '通知状态列表');
INSERT INTO `sys_dict_type`
VALUES (9, '000000', '操作类型', 'sys_oper_type', 103, 1, '2024-07-31 18:46:52', NULL, NULL, '操作类型列表');
INSERT INTO `sys_dict_type`
VALUES (10, '000000', '系统状态', 'sys_common_status', 103, 1, '2024-07-31 18:46:52', NULL, NULL, '登录状态列表');
INSERT INTO `sys_dict_type`
VALUES (11, '000000', '授权类型', 'sys_grant_type', 103, 1, '2024-07-31 18:46:52', NULL, NULL, '认证授权类型');
INSERT INTO `sys_dict_type`
VALUES (12, '000000', '设备类型', 'sys_device_type', 103, 1, '2024-07-31 18:46:52', NULL, NULL, '客户端设备类型');

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`
(
    `info_id`        bigint NOT NULL COMMENT '访问ID',
    `tenant_id`      varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '000000' COMMENT '租户编号',
    `user_name`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '用户账号',
    `client_key`     varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '客户端',
    `device_type`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '设备类型',
    `ipaddr`         varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '登录IP地址',
    `login_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '登录地点',
    `browser`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '浏览器类型',
    `os`             varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '操作系统',
    `status`         char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
    `msg`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '提示消息',
    `login_time`     datetime NULL DEFAULT NULL COMMENT '访问时间',
    PRIMARY KEY (`info_id`) USING BTREE,
    INDEX            `idx_sys_logininfor_s`(`status` ASC) USING BTREE,
    INDEX            `idx_sys_logininfor_lt`(`login_time` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '系统访问记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `id`          bigint NOT NULL COMMENT '主键',
    `parent_id`   bigint NULL DEFAULT NULL COMMENT '父菜单ID',
    `menu_name`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '菜单名称',
    `order_num`   int NULL DEFAULT NULL COMMENT '显示顺序',
    `path`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '路由地址',
    `component`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '组件路径',
    `query_param` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '路由参数',
    `is_frame`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否为外链（0是 1否）',
    `is_cache`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否缓存（0缓存 1不缓存）',
    `menu_type`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '类型（M目录 C菜单 F按钮）',
    `visible`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '显示状态（0显示 1隐藏）',
    `status`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '菜单状态（0正常 1停用）',
    `perms`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '权限字符串',
    `icon`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '菜单图标',
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
    `create_dept` bigint NULL DEFAULT NULL COMMENT '创建部门',
    `create_by`   bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '菜单权限表 sys_menu' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu`
VALUES (1, 0, '系统管理', 1, 'system', NULL, '', '1', '0', 'M', '0', '0', '', 'eos-icons:system-group', '系统管理目录',
        103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (2, 0, '系统监控', 3, 'monitor', NULL, '', '1', '0', 'M', '0', '0', '', 'solar:monitor-camera-outline',
        '系统监控目录', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (3, 0, '系统工具', 4, 'tool', NULL, '', '1', '0', 'M', '0', '0', '', 'ant-design:tool-outlined', '系统工具目录',
        103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (4, 0, 'PLUS官网', 5, 'https://gitee.com/dromara/RuoYi-Vue-Plus', NULL, '', '0', '0', 'M', '0', '0', '',
        'flat-color-icons:plus', 'RuoYi-Vue-Plus官网地址', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (6, 0, '租户管理', 2, 'tenant', NULL, '', '1', '0', 'M', '0', '0', '', 'ph:users-light', '租户管理目录', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (100, 1, '用户管理', 1, 'user', 'system/user/index', '', '1', '0', 'C', '0', '0', 'system:user:list',
        'ant-design:user-outlined', '用户管理菜单', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (101, 1, '角色管理', 2, 'role', 'system/role/index', '', '1', '0', 'C', '0', '0', 'system:role:list',
        'eos-icons:role-binding-outlined', '角色管理菜单', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (102, 1, '菜单管理', 3, 'menu', 'system/menu/index', '', '1', '0', 'C', '0', '0', 'system:menu:list',
        'ic:sharp-menu', '菜单管理菜单', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (103, 1, '部门管理', 4, 'dept', 'system/dept/index', '', '1', '0', 'C', '0', '0', 'system:dept:list',
        'mingcute:department-line', '部门管理菜单', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (104, 1, '岗位管理', 5, 'post', 'system/post/index', '', '1', '0', 'C', '0', '0', 'system:post:list',
        'icon-park-outline:appointment', '岗位管理菜单', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (105, 1, '字典管理', 6, 'dict', 'system/dict/index', '', '1', '0', 'C', '0', '0', 'system:dict:list',
        'fluent-mdl2:dictionary', '字典管理菜单', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (106, 1, '参数设置', 7, 'config', 'system/config/index', '', '1', '0', 'C', '0', '0', 'system:config:list',
        'ant-design:setting-outlined', '参数设置菜单', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (107, 1, '通知公告', 8, 'notice', 'system/notice/index', '', '1', '0', 'C', '0', '0', 'system:notice:list',
        'fe:notice-push', '通知公告菜单', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (108, 1, '日志管理', 9, 'log', '', '', '1', '0', 'M', '0', '0', '', 'material-symbols:logo-dev-outline',
        '日志管理菜单', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (109, 2, '在线用户', 1, 'online', 'monitor/online/index', '', '1', '0', 'C', '0', '0', 'monitor:online:list',
        'material-symbols:generating-tokens-outline', '在线用户菜单', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (113, 2, '缓存监控', 5, 'cache', 'monitor/cache/index', '', '1', '0', 'C', '0', '0', 'monitor:cache:list',
        'devicon:redis-wordmark', '缓存监控菜单', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (115, 3, '代码生成', 2, 'gen', 'tool/gen-code/index', '', '1', '0', 'C', '0', '0', 'tool:gen:list',
        'tabler:code', '代码生成菜单', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (117, 2, 'Admin监控', 5, 'Admin', 'monitor/admin/index', '', '1', '0', 'C', '0', '0', 'monitor:admin:list',
        'devicon:spring-wordmark', 'Admin监控菜单', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (118, 1, '文件管理', 10, 'oss', 'system/oss/index', '', '1', '0', 'C', '0', '0', 'system:oss:list',
        'solar:folder-with-files-outline', '文件管理菜单', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (120, 2, '任务调度中心', 6, 'snailjob', 'monitor/snailjob/index', '', '1', '0', 'C', '0', '0',
        'monitor:snailjob:list', 'svg:snail-job', 'SnailJob控制台菜单', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (121, 6, '租户管理', 1, 'tenantManager', 'system/tenant/index', '', '1', '0', 'C', '0', '0',
        'system:tenant:list', 'ph:user-list', '租户管理菜单', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (122, 6, '租户套餐管理', 2, 'tenantPackage', 'system/tenantPackage/index', '', '1', '0', 'C', '0', '0',
        'system:tenantPackage:list', 'bx:package', '租户套餐管理菜单', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (123, 1, '客户端管理', 11, 'client', 'system/client/index', '', '1', '0', 'C', '0', '0', 'system:client:list',
        'solar:monitor-smartphone-outline', '客户端管理菜单', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (500, 108, '操作日志', 1, 'operlog', 'monitor/operlog/index', '', '1', '0', 'C', '0', '0',
        'monitor:operlog:list', 'arcticons:one-hand-operation', '操作日志菜单', 103, 1, '2025-05-27 13:19:44', NULL,
        NULL);
INSERT INTO `sys_menu`
VALUES (501, 108, '登录日志', 2, 'logininfor', 'monitor/logininfor/index', '', '1', '0', 'C', '0', '0',
        'monitor:logininfor:list', 'streamline:interface-login-dial-pad-finger-password-dial-pad-dot-finger',
        '登录日志菜单', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1001, 100, '用户查询', 1, '', '', '', '1', '0', 'F', '0', '0', 'system:user:query', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1002, 100, '用户新增', 2, '', '', '', '1', '0', 'F', '0', '0', 'system:user:add', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1003, 100, '用户修改', 3, '', '', '', '1', '0', 'F', '0', '0', 'system:user:edit', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1004, 100, '用户删除', 4, '', '', '', '1', '0', 'F', '0', '0', 'system:user:remove', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1005, 100, '用户导出', 5, '', '', '', '1', '0', 'F', '0', '0', 'system:user:export', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1006, 100, '用户导入', 6, '', '', '', '1', '0', 'F', '0', '0', 'system:user:import', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1007, 100, '重置密码', 7, '', '', '', '1', '0', 'F', '0', '0', 'system:user:resetPwd', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1008, 101, '角色查询', 1, '', '', '', '1', '0', 'F', '0', '0', 'system:role:query', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1009, 101, '角色新增', 2, '', '', '', '1', '0', 'F', '0', '0', 'system:role:add', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1010, 101, '角色修改', 3, '', '', '', '1', '0', 'F', '0', '0', 'system:role:edit', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1011, 101, '角色删除', 4, '', '', '', '1', '0', 'F', '0', '0', 'system:role:remove', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1012, 101, '角色导出', 5, '', '', '', '1', '0', 'F', '0', '0', 'system:role:export', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1013, 102, '菜单查询', 1, '', '', '', '1', '0', 'F', '0', '0', 'system:menu:query', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1014, 102, '菜单新增', 2, '', '', '', '1', '0', 'F', '0', '0', 'system:menu:add', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1015, 102, '菜单修改', 3, '', '', '', '1', '0', 'F', '0', '0', 'system:menu:edit', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1016, 102, '菜单删除', 4, '', '', '', '1', '0', 'F', '0', '0', 'system:menu:remove', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1017, 103, '部门查询', 1, '', '', '', '1', '0', 'F', '0', '0', 'system:dept:query', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1018, 103, '部门新增', 2, '', '', '', '1', '0', 'F', '0', '0', 'system:dept:add', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1019, 103, '部门修改', 3, '', '', '', '1', '0', 'F', '0', '0', 'system:dept:edit', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1020, 103, '部门删除', 4, '', '', '', '1', '0', 'F', '0', '0', 'system:dept:remove', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1021, 104, '岗位查询', 1, '', '', '', '1', '0', 'F', '0', '0', 'system:post:query', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1022, 104, '岗位新增', 2, '', '', '', '1', '0', 'F', '0', '0', 'system:post:add', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1023, 104, '岗位修改', 3, '', '', '', '1', '0', 'F', '0', '0', 'system:post:edit', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1024, 104, '岗位删除', 4, '', '', '', '1', '0', 'F', '0', '0', 'system:post:remove', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1025, 104, '岗位导出', 5, '', '', '', '1', '0', 'F', '0', '0', 'system:post:export', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1026, 105, '字典查询', 1, '#', '', '', '1', '0', 'F', '0', '0', 'system:dict:query', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1027, 105, '字典新增', 2, '#', '', '', '1', '0', 'F', '0', '0', 'system:dict:add', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1028, 105, '字典修改', 3, '#', '', '', '1', '0', 'F', '0', '0', 'system:dict:edit', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1029, 105, '字典删除', 4, '#', '', '', '1', '0', 'F', '0', '0', 'system:dict:remove', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1030, 105, '字典导出', 5, '#', '', '', '1', '0', 'F', '0', '0', 'system:dict:export', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1031, 106, '参数查询', 1, '#', '', '', '1', '0', 'F', '0', '0', 'system:config:query', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1032, 106, '参数新增', 2, '#', '', '', '1', '0', 'F', '0', '0', 'system:config:add', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1033, 106, '参数修改', 3, '#', '', '', '1', '0', 'F', '0', '0', 'system:config:edit', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1034, 106, '参数删除', 4, '#', '', '', '1', '0', 'F', '0', '0', 'system:config:remove', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1035, 106, '参数导出', 5, '#', '', '', '1', '0', 'F', '0', '0', 'system:config:export', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1036, 107, '公告查询', 1, '#', '', '', '1', '0', 'F', '0', '0', 'system:notice:query', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1037, 107, '公告新增', 2, '#', '', '', '1', '0', 'F', '0', '0', 'system:notice:add', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1038, 107, '公告修改', 3, '#', '', '', '1', '0', 'F', '0', '0', 'system:notice:edit', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1039, 107, '公告删除', 4, '#', '', '', '1', '0', 'F', '0', '0', 'system:notice:remove', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1040, 500, '操作查询', 1, '#', '', '', '1', '0', 'F', '0', '0', 'monitor:operlog:query', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1041, 500, '操作删除', 2, '#', '', '', '1', '0', 'F', '0', '0', 'monitor:operlog:remove', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1042, 500, '日志导出', 4, '#', '', '', '1', '0', 'F', '0', '0', 'monitor:operlog:export', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1043, 501, '登录查询', 1, '#', '', '', '1', '0', 'F', '0', '0', 'monitor:logininfor:query', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1044, 501, '登录删除', 2, '#', '', '', '1', '0', 'F', '0', '0', 'monitor:logininfor:remove', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1045, 501, '日志导出', 3, '#', '', '', '1', '0', 'F', '0', '0', 'monitor:logininfor:export', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1046, 109, '在线查询', 1, '#', '', '', '1', '0', 'F', '0', '0', 'monitor:online:query', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1047, 109, '批量强退', 2, '#', '', '', '1', '0', 'F', '0', '0', 'monitor:online:batchLogout', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1048, 109, '单条强退', 3, '#', '', '', '1', '0', 'F', '0', '0', 'monitor:online:forceLogout', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1050, 501, '账户解锁', 4, '#', '', '', '1', '0', 'F', '0', '0', 'monitor:logininfor:unlock', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1055, 115, '生成查询', 1, '#', '', '', '1', '0', 'F', '0', '0', 'tool:gen:query', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1056, 115, '生成修改', 2, '#', '', '', '1', '0', 'F', '0', '0', 'tool:gen:edit', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1057, 115, '生成删除', 3, '#', '', '', '1', '0', 'F', '0', '0', 'tool:gen:remove', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1058, 115, '导入代码', 2, '#', '', '', '1', '0', 'F', '0', '0', 'tool:gen:import', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1059, 115, '预览代码', 4, '#', '', '', '1', '0', 'F', '0', '0', 'tool:gen:preview', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1060, 115, '生成代码', 5, '#', '', '', '1', '0', 'F', '0', '0', 'tool:gen:code', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1061, 123, '客户端管理查询', 1, '#', '', '', '1', '0', 'F', '0', '0', 'system:client:query', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1062, 123, '客户端管理新增', 2, '#', '', '', '1', '0', 'F', '0', '0', 'system:client:add', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1063, 123, '客户端管理修改', 3, '#', '', '', '1', '0', 'F', '0', '0', 'system:client:edit', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1064, 123, '客户端管理删除', 4, '#', '', '', '1', '0', 'F', '0', '0', 'system:client:remove', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1065, 123, '客户端管理导出', 5, '#', '', '', '1', '0', 'F', '0', '0', 'system:client:export', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1500, 5, '测试单表', 1, 'demo', 'demo/demo/index', '', '1', '0', 'C', '0', '0', 'demo:demo:list',
        'lucide:table', '测试单表菜单', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1501, 1500, '测试单表查询', 1, '#', '', '', '1', '0', 'F', '0', '0', 'demo:demo:query', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1502, 1500, '测试单表新增', 2, '#', '', '', '1', '0', 'F', '0', '0', 'demo:demo:add', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1503, 1500, '测试单表修改', 3, '#', '', '', '1', '0', 'F', '0', '0', 'demo:demo:edit', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1504, 1500, '测试单表删除', 4, '#', '', '', '1', '0', 'F', '0', '0', 'demo:demo:remove', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1505, 1500, '测试单表导出', 5, '#', '', '', '1', '0', 'F', '0', '0', 'demo:demo:export', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1506, 5, '测试树表', 1, 'tree', 'demo/tree/index', '', '1', '0', 'C', '0', '0', 'demo:tree:list',
        'emojione:evergreen-tree', '测试树表菜单', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1507, 1506, '测试树表查询', 1, '#', '', '', '1', '0', 'F', '0', '0', 'demo:tree:query', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1508, 1506, '测试树表新增', 2, '#', '', '', '1', '0', 'F', '0', '0', 'demo:tree:add', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1509, 1506, '测试树表修改', 3, '#', '', '', '1', '0', 'F', '0', '0', 'demo:tree:edit', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1510, 1506, '测试树表删除', 4, '#', '', '', '1', '0', 'F', '0', '0', 'demo:tree:remove', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1511, 1506, '测试树表导出', 5, '#', '', '', '1', '0', 'F', '0', '0', 'demo:tree:export', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1600, 118, '文件查询', 1, '#', '', '', '1', '0', 'F', '0', '0', 'system:oss:query', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1601, 118, '文件上传', 2, '#', '', '', '1', '0', 'F', '0', '0', 'system:oss:upload', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1602, 118, '文件下载', 3, '#', '', '', '1', '0', 'F', '0', '0', 'system:oss:download', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1603, 118, '文件删除', 4, '#', '', '', '1', '0', 'F', '0', '0', 'system:oss:remove', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1606, 121, '租户查询', 1, '#', '', '', '1', '0', 'F', '0', '0', 'system:tenant:query', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1607, 121, '租户新增', 2, '#', '', '', '1', '0', 'F', '0', '0', 'system:tenant:add', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1608, 121, '租户修改', 3, '#', '', '', '1', '0', 'F', '0', '0', 'system:tenant:edit', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1609, 121, '租户删除', 4, '#', '', '', '1', '0', 'F', '0', '0', 'system:tenant:remove', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1610, 121, '租户导出', 5, '#', '', '', '1', '0', 'F', '0', '0', 'system:tenant:export', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1611, 122, '租户套餐查询', 1, '#', '', '', '1', '0', 'F', '0', '0', 'system:tenantPackage:query', '#', '', 103,
        1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1612, 122, '租户套餐新增', 2, '#', '', '', '1', '0', 'F', '0', '0', 'system:tenantPackage:add', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1613, 122, '租户套餐修改', 3, '#', '', '', '1', '0', 'F', '0', '0', 'system:tenantPackage:edit', '#', '', 103,
        1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1614, 122, '租户套餐删除', 4, '#', '', '', '1', '0', 'F', '0', '0', 'system:tenantPackage:remove', '#', '', 103,
        1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1615, 122, '租户套餐导出', 5, '#', '', '', '1', '0', 'F', '0', '0', 'system:tenantPackage:export', '#', '', 103,
        1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1620, 118, '配置列表', 5, '#', '', '', '1', '0', 'F', '0', '0', 'system:ossConfig:list', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1621, 118, '配置添加', 6, '#', '', '', '1', '0', 'F', '0', '0', 'system:ossConfig:add', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1622, 118, '配置编辑', 6, '#', '', '', '1', '0', 'F', '0', '0', 'system:ossConfig:edit', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (1623, 118, '配置删除', 6, '#', '', '', '1', '0', 'F', '0', '0', 'system:ossConfig:remove', '#', '', 103, 1,
        '2025-05-27 13:19:44', NULL, NULL);

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`
(
    `notice_id`      bigint                                                NOT NULL COMMENT '公告ID',
    `tenant_id`      varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '000000' COMMENT '租户编号',
    `notice_title`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '公告标题',
    `notice_type`    char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin     NOT NULL COMMENT '公告类型（1通知 2公告）',
    `notice_content` longblob NULL COMMENT '公告内容',
    `status`         char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
    `create_dept`    bigint NULL DEFAULT NULL COMMENT '创建部门',
    `create_by`      bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time`    datetime NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`      bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time`    datetime NULL DEFAULT NULL COMMENT '更新时间',
    `remark`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '通知公告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice`
VALUES (1, '000000', '温馨提醒：2018-07-01 新版本发布啦', '2', 0xE696B0E78988E69CACE58685E5AEB9, '0', 103, 1,
        '2024-07-31 18:46:52', NULL, NULL, '管理员');
INSERT INTO `sys_notice`
VALUES (2, '000000', '维护通知：2018-07-01 系统凌晨维护', '1', 0xE7BBB4E68AA4E58685E5AEB9, '0', 103, 1,
        '2024-07-31 18:46:52', NULL, NULL, '管理员');

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`
(
    `oper_id`        bigint NOT NULL COMMENT '日志主键',
    `tenant_id`      varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '000000' COMMENT '租户编号',
    `title`          varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '模块标题',
    `business_type`  int NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
    `method`         varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '方法名称',
    `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '请求方式',
    `operator_type`  int NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
    `oper_name`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '操作人员',
    `dept_name`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '部门名称',
    `oper_url`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '请求URL',
    `oper_ip`        varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '主机地址',
    `oper_location`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '操作地点',
    `oper_param`     varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '请求参数',
    `json_result`    varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '返回参数',
    `status`         int NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
    `error_msg`      varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '错误消息',
    `oper_time`      datetime NULL DEFAULT NULL COMMENT '操作时间',
    `cost_time`      bigint NULL DEFAULT 0 COMMENT '消耗时间',
    PRIMARY KEY (`oper_id`) USING BTREE,
    INDEX            `idx_sys_oper_log_bt`(`business_type` ASC) USING BTREE,
    INDEX            `idx_sys_oper_log_s`(`status` ASC) USING BTREE,
    INDEX            `idx_sys_oper_log_ot`(`oper_time` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '操作日志记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss`
(
    `oss_id`        bigint                                                 NOT NULL COMMENT '对象存储主键',
    `tenant_id`     varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '000000' COMMENT '租户编号',
    `file_name`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '文件名',
    `original_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '原名',
    `file_suffix`   varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NOT NULL DEFAULT '' COMMENT '文件后缀名',
    `url`           varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT 'URL地址',
    `create_dept`   bigint NULL DEFAULT NULL COMMENT '创建部门',
    `create_time`   datetime NULL DEFAULT NULL COMMENT '创建时间',
    `create_by`     bigint NULL DEFAULT NULL COMMENT '上传人',
    `update_time`   datetime NULL DEFAULT NULL COMMENT '更新时间',
    `update_by`     bigint NULL DEFAULT NULL COMMENT '更新人',
    `service`       varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NOT NULL DEFAULT 'minio' COMMENT '服务商',
    PRIMARY KEY (`oss_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = 'OSS对象存储表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_oss
-- ----------------------------

-- ----------------------------
-- Table structure for sys_oss_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss_config`;
CREATE TABLE `sys_oss_config`
(
    `oss_config_id` bigint                                                NOT NULL COMMENT '主建',
    `tenant_id`     varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '000000' COMMENT '租户编号',
    `config_key`    varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '配置key',
    `access_key`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT 'accessKey',
    `secret_key`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '秘钥',
    `bucket_name`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '桶名称',
    `prefix`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '前缀',
    `endpoint`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '访问站点',
    `domain`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '自定义域名',
    `is_https`      char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT 'N' COMMENT '是否https（Y=是,N=否）',
    `region`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '域',
    `access_policy` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin     NOT NULL DEFAULT '1' COMMENT '桶权限类型(0=private 1=public 2=custom)',
    `status`        char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '1' COMMENT '是否默认（0=是,1=否）',
    `ext1`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '扩展字段',
    `create_dept`   bigint NULL DEFAULT NULL COMMENT '创建部门',
    `create_by`     bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time`   datetime NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`     bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time`   datetime NULL DEFAULT NULL COMMENT '更新时间',
    `remark`        varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`oss_config_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '对象存储配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_oss_config
-- ----------------------------
INSERT INTO `sys_oss_config`
VALUES (1, '000000', 'minio', 'ruoyi', 'ruoyi123', 'ruoyi', '', '127.0.0.1:9000', '', 'N', '', '1', '0', '', 103, 1,
        '2024-07-31 18:46:52', 1, '2024-07-31 18:46:52', NULL);
INSERT INTO `sys_oss_config`
VALUES (2, '000000', 'qiniu', 'XXXXXXXXXXXXXXX', 'XXXXXXXXXXXXXXX', 'ruoyi', '', 's3-cn-north-1.qiniucs.com', '', 'N',
        '', '1', '1', '', 103, 1, '2024-07-31 18:46:52', 1, '2024-07-31 18:46:52', NULL);
INSERT INTO `sys_oss_config`
VALUES (3, '000000', 'aliyun', 'XXXXXXXXXXXXXXX', 'XXXXXXXXXXXXXXX', 'ruoyi', '', 'oss-cn-beijing.aliyuncs.com', '',
        'N', '', '1', '1', '', 103, 1, '2024-07-31 18:46:52', 1, '2024-07-31 18:46:52', NULL);
INSERT INTO `sys_oss_config`
VALUES (4, '000000', 'qcloud', 'XXXXXXXXXXXXXXX', 'XXXXXXXXXXXXXXX', 'ruoyi-1250000000', '',
        'cos.ap-beijing.myqcloud.com', '', 'N', 'ap-beijing', '1', '1', '', 103, 1, '2024-07-31 18:46:52', 1,
        '2024-07-31 18:46:52', NULL);
INSERT INTO `sys_oss_config`
VALUES (5, '000000', 'image', 'ruoyi', 'ruoyi123', 'ruoyi', 'image', '127.0.0.1:9000', '', 'N', '', '1', '1', '', 103,
        1, '2024-07-31 18:46:52', 1, '2024-07-31 18:46:52', NULL);

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`
(
    `id`          bigint NOT NULL COMMENT '主键',
    `post_code`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '岗位编码',
    `post_name`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '岗位名称',
    `post_sort`   int NULL DEFAULT NULL COMMENT '岗位排序',
    `status`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '状态（0正常 1停用）',
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
    `create_dept` bigint NULL DEFAULT NULL COMMENT '创建部门',
    `create_by`   bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '岗位表 sys_post' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post`
VALUES (1, NULL, '董事长', 1, '0', '', 103, 1, '2024-07-31 18:46:50', NULL, NULL);
INSERT INTO `sys_post`
VALUES (2, NULL, '项目经理', 2, '0', '', 103, 1, '2024-07-31 18:46:50', NULL, NULL);
INSERT INTO `sys_post`
VALUES (3, NULL, '人力资源', 3, '0', '', 103, 1, '2024-07-31 18:46:50', NULL, NULL);
INSERT INTO `sys_post`
VALUES (4, NULL, '普通员工', 4, '0', '', 103, 1, '2024-07-31 18:46:50', NULL, NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`                  bigint NOT NULL COMMENT '主键',
    `role_name`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '角色名称',
    `role_key`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '角色权限',
    `role_sort`           int NULL DEFAULT NULL COMMENT '角色排序',
    `data_scope`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '数据范围（1：所有数据权限；2：自定义数据权限；3：本部门数据权限；4：本部门及以下数据权限；5：仅本人数据权限）',
    `menu_check_strictly` bit(1) NULL DEFAULT NULL COMMENT '菜单树选择项是否关联显示（ 0：父子不互相关联显示 1：父子互相关联显示）',
    `dept_check_strictly` bit(1) NULL DEFAULT NULL COMMENT '部门树选择项是否关联显示（0：父子不互相关联显示 1：父子互相关联显示 ）',
    `status`              varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '角色状态（0正常 1停用）',
    `del_flag`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '删除标志（0代表存在 2代表删除）',
    `remark`              varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
    `create_dept`         bigint NULL DEFAULT NULL COMMENT '创建部门',
    `create_by`           bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time`         datetime NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`           bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time`         datetime NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '角色表 sys_role' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role`
VALUES (1, '超级管理员', 'superadmin', 1, '1', b'1', b'1', '0', '0', '超级管理员', 103, 1, '2024-07-31 18:46:50', NULL,
        NULL);
INSERT INTO `sys_role`
VALUES (2, '普通角色', 'common', 2, '2', b'1', b'1', '0', '0', '普通角色', 103, 1, '2024-07-31 18:46:50', NULL, NULL);

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`
(
    `role_id` bigint NULL DEFAULT NULL COMMENT '角色ID',
    `dept_id` bigint NULL DEFAULT NULL COMMENT '部门ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '角色和部门关联 sys_role_dept' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept`
VALUES (2, 100);
INSERT INTO `sys_role_dept`
VALUES (2, 101);
INSERT INTO `sys_role_dept`
VALUES (2, 105);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`
(
    `role_id` bigint NULL DEFAULT NULL COMMENT '角色ID',
    `menu_id` bigint NULL DEFAULT NULL COMMENT '菜单ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '角色和菜单关联 sys_role_menu' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu`
VALUES (2, 1);
INSERT INTO `sys_role_menu`
VALUES (2, 2);
INSERT INTO `sys_role_menu`
VALUES (2, 3);
INSERT INTO `sys_role_menu`
VALUES (2, 4);
INSERT INTO `sys_role_menu`
VALUES (2, 100);
INSERT INTO `sys_role_menu`
VALUES (2, 101);
INSERT INTO `sys_role_menu`
VALUES (2, 102);
INSERT INTO `sys_role_menu`
VALUES (2, 103);
INSERT INTO `sys_role_menu`
VALUES (2, 104);
INSERT INTO `sys_role_menu`
VALUES (2, 105);
INSERT INTO `sys_role_menu`
VALUES (2, 106);
INSERT INTO `sys_role_menu`
VALUES (2, 107);
INSERT INTO `sys_role_menu`
VALUES (2, 108);
INSERT INTO `sys_role_menu`
VALUES (2, 109);
INSERT INTO `sys_role_menu`
VALUES (2, 110);
INSERT INTO `sys_role_menu`
VALUES (2, 111);
INSERT INTO `sys_role_menu`
VALUES (2, 112);
INSERT INTO `sys_role_menu`
VALUES (2, 113);
INSERT INTO `sys_role_menu`
VALUES (2, 114);
INSERT INTO `sys_role_menu`
VALUES (2, 115);
INSERT INTO `sys_role_menu`
VALUES (2, 116);
INSERT INTO `sys_role_menu`
VALUES (2, 500);
INSERT INTO `sys_role_menu`
VALUES (2, 501);
INSERT INTO `sys_role_menu`
VALUES (2, 1000);
INSERT INTO `sys_role_menu`
VALUES (2, 1001);
INSERT INTO `sys_role_menu`
VALUES (2, 1002);
INSERT INTO `sys_role_menu`
VALUES (2, 1003);
INSERT INTO `sys_role_menu`
VALUES (2, 1004);
INSERT INTO `sys_role_menu`
VALUES (2, 1005);
INSERT INTO `sys_role_menu`
VALUES (2, 1006);
INSERT INTO `sys_role_menu`
VALUES (2, 1007);
INSERT INTO `sys_role_menu`
VALUES (2, 1008);
INSERT INTO `sys_role_menu`
VALUES (2, 1009);
INSERT INTO `sys_role_menu`
VALUES (2, 1010);
INSERT INTO `sys_role_menu`
VALUES (2, 1011);
INSERT INTO `sys_role_menu`
VALUES (2, 1012);
INSERT INTO `sys_role_menu`
VALUES (2, 1013);
INSERT INTO `sys_role_menu`
VALUES (2, 1014);
INSERT INTO `sys_role_menu`
VALUES (2, 1015);
INSERT INTO `sys_role_menu`
VALUES (2, 1016);
INSERT INTO `sys_role_menu`
VALUES (2, 1017);
INSERT INTO `sys_role_menu`
VALUES (2, 1018);
INSERT INTO `sys_role_menu`
VALUES (2, 1019);
INSERT INTO `sys_role_menu`
VALUES (2, 1020);
INSERT INTO `sys_role_menu`
VALUES (2, 1021);
INSERT INTO `sys_role_menu`
VALUES (2, 1022);
INSERT INTO `sys_role_menu`
VALUES (2, 1023);
INSERT INTO `sys_role_menu`
VALUES (2, 1024);
INSERT INTO `sys_role_menu`
VALUES (2, 1025);
INSERT INTO `sys_role_menu`
VALUES (2, 1026);
INSERT INTO `sys_role_menu`
VALUES (2, 1027);
INSERT INTO `sys_role_menu`
VALUES (2, 1028);
INSERT INTO `sys_role_menu`
VALUES (2, 1029);
INSERT INTO `sys_role_menu`
VALUES (2, 1030);
INSERT INTO `sys_role_menu`
VALUES (2, 1031);
INSERT INTO `sys_role_menu`
VALUES (2, 1032);
INSERT INTO `sys_role_menu`
VALUES (2, 1033);
INSERT INTO `sys_role_menu`
VALUES (2, 1034);
INSERT INTO `sys_role_menu`
VALUES (2, 1035);
INSERT INTO `sys_role_menu`
VALUES (2, 1036);
INSERT INTO `sys_role_menu`
VALUES (2, 1037);
INSERT INTO `sys_role_menu`
VALUES (2, 1038);
INSERT INTO `sys_role_menu`
VALUES (2, 1039);
INSERT INTO `sys_role_menu`
VALUES (2, 1040);
INSERT INTO `sys_role_menu`
VALUES (2, 1041);
INSERT INTO `sys_role_menu`
VALUES (2, 1042);
INSERT INTO `sys_role_menu`
VALUES (2, 1043);
INSERT INTO `sys_role_menu`
VALUES (2, 1044);
INSERT INTO `sys_role_menu`
VALUES (2, 1045);
INSERT INTO `sys_role_menu`
VALUES (2, 1046);
INSERT INTO `sys_role_menu`
VALUES (2, 1047);
INSERT INTO `sys_role_menu`
VALUES (2, 1048);
INSERT INTO `sys_role_menu`
VALUES (2, 1050);
INSERT INTO `sys_role_menu`
VALUES (2, 1055);
INSERT INTO `sys_role_menu`
VALUES (2, 1056);
INSERT INTO `sys_role_menu`
VALUES (2, 1057);
INSERT INTO `sys_role_menu`
VALUES (2, 1058);
INSERT INTO `sys_role_menu`
VALUES (2, 1059);
INSERT INTO `sys_role_menu`
VALUES (2, 1060);
INSERT INTO `sys_role_menu`
VALUES (2, 1061);
INSERT INTO `sys_role_menu`
VALUES (2, 1062);
INSERT INTO `sys_role_menu`
VALUES (2, 1063);
INSERT INTO `sys_role_menu`
VALUES (2, 1064);
INSERT INTO `sys_role_menu`
VALUES (2, 1065);

-- ----------------------------
-- Table structure for sys_social
-- ----------------------------
DROP TABLE IF EXISTS `sys_social`;
CREATE TABLE `sys_social`
(
    `id`                 bigint                                                 NOT NULL COMMENT '主键',
    `user_id`            bigint                                                 NOT NULL COMMENT '用户ID',
    `tenant_id`          varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '租户id',
    `auth_id`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '平台+平台唯一id',
    `source`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户来源',
    `open_id`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '平台编号唯一id',
    `user_name`          varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NOT NULL COMMENT '登录账号',
    `nick_name`          varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '用户昵称',
    `email`              varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '用户邮箱',
    `avatar`             varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '头像地址',
    `access_token`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户的授权令牌',
    `expire_in`          int NULL DEFAULT NULL COMMENT '用户的授权令牌的有效期，部分平台可能没有',
    `refresh_token`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '刷新令牌，部分平台可能没有',
    `access_code`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '平台的授权信息，部分平台可能没有',
    `union_id`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户的 unionid',
    `scope`              varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '授予的权限，部分平台可能没有',
    `token_type`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '个别平台的授权信息，部分平台可能没有',
    `id_token`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'id token，部分平台可能没有',
    `mac_algorithm`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '小米平台用户的附带属性，部分平台可能没有',
    `mac_key`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '小米平台用户的附带属性，部分平台可能没有',
    `code`               varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户的授权code，部分平台可能没有',
    `oauth_token`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'Twitter平台用户的附带属性，部分平台可能没有',
    `oauth_token_secret` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'Twitter平台用户的附带属性，部分平台可能没有',
    `create_dept`        bigint NULL DEFAULT NULL COMMENT '创建部门',
    `create_by`          bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time`        datetime NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`          bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time`        datetime NULL DEFAULT NULL COMMENT '更新时间',
    `del_flag`           char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '社会化关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_social
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`          bigint NOT NULL COMMENT '主键',
    `dept_id`     bigint NULL DEFAULT NULL COMMENT '部门ID',
    `user_name`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户账号',
    `nick_name`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户昵称',
    `user_type`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户类型（sys_user系统用户）',
    `email`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户邮箱',
    `phonenumber` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '手机号码',
    `sex`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户性别',
    `avatar`      bigint NULL DEFAULT NULL COMMENT '用户头像',
    `password`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '密码',
    `status`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '帐号状态（0正常 1停用）',
    `del_flag`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '删除标志（0代表存在 2代表删除）',
    `login_ip`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '最后登录IP',
    `login_date`  datetime NULL DEFAULT NULL COMMENT '最后登录时间',
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
    `tenant_id`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '租户Id',
    `create_dept` bigint NULL DEFAULT NULL COMMENT '创建部门',
    `create_by`   bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户对象 sys_user' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user`
VALUES (1, 103, 'admin', '疯狂的狮子Li', 'pc_user', 'crazyLionLi@163.com', '15888888888', '1', NULL,
        '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2024-08-07 19:16:57',
        '管理员', '0', 103, 1, '2024-08-07 19:16:57', NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`
(
    `user_id` bigint NULL DEFAULT NULL COMMENT '用户ID',
    `post_id` bigint NULL DEFAULT NULL COMMENT '部门ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户和角色关联 sys_user_role' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post`
VALUES (1, 1);
INSERT INTO `sys_user_post`
VALUES (2, 2);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `user_id` bigint NULL DEFAULT NULL COMMENT '用户ID',
    `role_id` bigint NULL DEFAULT NULL COMMENT '角色ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户和角色关联 sys_user_role' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role`
VALUES (1, 1);
INSERT INTO `sys_user_role`
VALUES (2, 2);

-- ----------------------------
-- Table structure for user_test
-- ----------------------------
DROP TABLE IF EXISTS `user_test`;
CREATE TABLE `user_test`
(
    `user_id` bigint NOT NULL AUTO_INCREMENT,
    `name`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
    PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_test
-- ----------------------------

SET
FOREIGN_KEY_CHECKS = 1;
