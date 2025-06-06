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

 Date: 06/06/2025 15:27:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table`  (
                              `id` bigint NOT NULL COMMENT '主键',
                              `data_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '数据源名称',
                              `table_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '表名称',
                              `table_comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '表描述',
                              `sub_table_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '关联父表的表名',
                              `sub_table_fk_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '本表关联父表的外键名',
                              `class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '实体类名称(首字母大写)',
                              `tpl_category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '使用的模板（crud单表操作 tree树表操作 sub主子表操作）',
                              `package_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生成包路径',
                              `module_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生成模块名',
                              `business_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生成业务名',
                              `function_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生成功能名',
                              `function_author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生成作者',
                              `gen_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
                              `gen_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生成路径（不填默认项目路径）',
                              `options` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '其它生成选项',
                              `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
                              `create_dept` bigint NULL DEFAULT NULL COMMENT '创建部门',
                              `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
                              `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                              `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
                              `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '业务表 gen_table' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column`  (
                                     `id` bigint NOT NULL COMMENT '主键',
                                     `table_id` bigint NULL DEFAULT NULL COMMENT '归属表编号',
                                     `column_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '列名称',
                                     `column_comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '列描述',
                                     `column_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '列类型',
                                     `java_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'JAVA类型',
                                     `default_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '列默认值',
                                     `length` int NULL DEFAULT NULL COMMENT '列长度',
                                     `java_field` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'JAVA字段名',
                                     `is_pk` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否主键（1是）',
                                     `is_increment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否自增（1是）',
                                     `is_required` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否必填（1是）',
                                     `is_insert` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否为插入字段（1是）',
                                     `is_edit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否编辑字段（1是）',
                                     `is_list` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否列表字段（1是）',
                                     `is_query` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否查询字段（1是）',
                                     `query_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '查询方式（EQ等于、NE不等于、GT大于、LT小于、LIKE模糊、BETWEEN范围）',
                                     `html_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '显示类型（input文本框、textarea文本域、select下拉框、checkbox复选框、radio单选框、datetime日期控件、image图片上传控件、upload文件上传控件、editor富文本控件）',
                                     `dict_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '字典类型',
                                     `sort` int NULL DEFAULT NULL COMMENT '排序',
                                     `create_dept` bigint NULL DEFAULT NULL COMMENT '创建部门',
                                     `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
                                     `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                     `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
                                     `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '代码生成业务字段表 gen_table_column' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of gen_table_column
-- ----------------------------
INSERT INTO `gen_table_column` VALUES (202102013532581888, 202102013427724288, 'id', '主键', 'bigint', 'Long', '', -1, 'id', '1', '0', '1', '', '1', '1', '', 'EQ', 'input', '', 1, 103, 1, '2025-05-27 16:41:15', 1, '2025-05-28 20:10:12');
INSERT INTO `gen_table_column` VALUES (202102013536776192, 202102013427724288, 'client_id', '客户端id', 'varchar', 'String', '', 255, 'clientId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 103, 1, '2025-05-27 16:41:15', 1, '2025-05-28 20:10:12');
INSERT INTO `gen_table_column` VALUES (202102013536776193, 202102013427724288, 'client_key', '客户端key', 'varchar', 'String', '', 255, 'clientKey', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 103, 1, '2025-05-27 16:41:15', 1, '2025-05-28 20:10:12');
INSERT INTO `gen_table_column` VALUES (202102013536776194, 202102013427724288, 'client_secret', '客户端秘钥', 'varchar', 'String', '', 255, 'clientSecret', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, 103, 1, '2025-05-27 16:41:15', 1, '2025-05-28 20:10:12');
INSERT INTO `gen_table_column` VALUES (202102013536776195, 202102013427724288, 'grant_type', '授权类型', 'varchar', 'String', '', 255, 'grantType', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 5, 103, 1, '2025-05-27 16:41:15', 1, '2025-05-28 20:10:12');
INSERT INTO `gen_table_column` VALUES (202102013536776196, 202102013427724288, 'device_type', '设备类型', 'varchar', 'String', '', 255, 'deviceType', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 6, 103, 1, '2025-05-27 16:41:15', 1, '2025-05-28 20:10:12');
INSERT INTO `gen_table_column` VALUES (202102013536776197, 202102013427724288, 'active_timeout', 'token活跃超时时间', 'bigint', 'Long', '', -1, 'activeTimeout', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 7, 103, 1, '2025-05-27 16:41:15', 1, '2025-05-28 20:10:12');
INSERT INTO `gen_table_column` VALUES (202102013536776198, 202102013427724288, 'timeout', 'token固定超时时间', 'bigint', 'Long', '', -1, 'timeout', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 8, 103, 1, '2025-05-27 16:41:15', 1, '2025-05-28 20:10:12');
INSERT INTO `gen_table_column` VALUES (202102013536776199, 202102013427724288, 'status', '状态（0正常 1停用）', 'varchar', 'String', '10', 255, 'status', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'radio', '', 9, 103, 1, '2025-05-27 16:41:15', 1, '2025-05-28 20:10:12');
INSERT INTO `gen_table_column` VALUES (202102013536776200, 202102013427724288, 'del_flag', '删除标志（0代表存在 2代表删除）', 'varchar', 'String', '', 255, 'delFlag', '0', '0', '1', '', '', '', '', 'EQ', 'input', '', 10, 103, 1, '2025-05-27 16:41:15', 1, '2025-05-28 20:10:12');
INSERT INTO `gen_table_column` VALUES (202102013536776201, 202102013427724288, 'create_dept', '创建部门', 'bigint', 'Long', '', -1, 'createDept', '0', '0', '1', '', '', '', '', 'EQ', 'input', '', 11, 103, 1, '2025-05-27 16:41:15', 1, '2025-05-28 20:10:12');
INSERT INTO `gen_table_column` VALUES (202102013536776202, 202102013427724288, 'create_by', '创建者', 'bigint', 'Long', '', -1, 'createBy', '0', '0', '1', '', '', '', '', 'EQ', 'input', '', 12, 103, 1, '2025-05-27 16:41:15', 1, '2025-05-28 20:10:12');
INSERT INTO `gen_table_column` VALUES (202102013536776203, 202102013427724288, 'create_time', '创建时间', 'datetime', 'Date', '', -1, 'createTime', '0', '0', '1', '', '', '', '', 'EQ', 'datetime', '', 13, 103, 1, '2025-05-27 16:41:15', 1, '2025-05-28 20:10:12');
INSERT INTO `gen_table_column` VALUES (202102013536776204, 202102013427724288, 'update_by', '更新者', 'bigint', 'Long', '', -1, 'updateBy', '0', '0', '1', '', '', '', '', 'EQ', 'input', '', 14, 103, 1, '2025-05-27 16:41:15', 1, '2025-05-28 20:10:12');
INSERT INTO `gen_table_column` VALUES (202102013536776205, 202102013427724288, 'update_time', '更新时间', 'datetime', 'Date', '', -1, 'updateTime', '0', '0', '1', '', '', '', '', 'EQ', 'datetime', '', 15, 103, 1, '2025-05-27 16:41:15', 1, '2025-05-28 20:10:12');
INSERT INTO `gen_table_column` VALUES (202180247423025152, 202180247162978304, 'dict_id', '字典主键', 'bigint', 'Long', '', -1, 'dictId', '1', '0', '1', NULL, '1', '1', NULL, 'EQ', 'input', NULL, 1, 103, 1, '2025-05-27 21:52:07', 1, '2025-05-27 21:52:07');
INSERT INTO `gen_table_column` VALUES (202180247427219456, 202180247162978304, 'tenant_id', '租户编号', 'varchar', 'String', '000000', 20, 'tenantId', '0', '0', '1', NULL, NULL, NULL, NULL, 'EQ', 'input', NULL, 2, 103, 1, '2025-05-27 21:52:07', 1, '2025-05-27 21:52:07');
INSERT INTO `gen_table_column` VALUES (202180247427219457, 202180247162978304, 'dict_key', '字典key', 'varchar', 'String', '', 100, 'dictKey', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', NULL, 3, 103, 1, '2025-05-27 21:52:07', 1, '2025-05-27 21:52:07');
INSERT INTO `gen_table_column` VALUES (202180247427219458, 202180247162978304, 'dict_name', '字典名称', 'varchar', 'String', '', 100, 'dictName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', NULL, 4, 103, 1, '2025-05-27 21:52:07', 1, '2025-05-27 21:52:07');
INSERT INTO `gen_table_column` VALUES (202180247427219459, 202180247162978304, 'dict_type', '字典类型 L 列表 T 树', 'varchar', 'String', 'L', 100, 'dictType', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', NULL, 5, 103, 1, '2025-05-27 21:52:07', 1, '2025-05-27 21:52:07');
INSERT INTO `gen_table_column` VALUES (202180247427219460, 202180247162978304, 'create_dept', '创建部门', 'bigint', 'Long', '', -1, 'createDept', '0', '0', '1', NULL, NULL, NULL, NULL, 'EQ', 'input', NULL, 6, 103, 1, '2025-05-27 21:52:07', 1, '2025-05-27 21:52:07');
INSERT INTO `gen_table_column` VALUES (202180247427219461, 202180247162978304, 'create_by', '创建者', 'bigint', 'Long', '', -1, 'createBy', '0', '0', '1', NULL, NULL, NULL, NULL, 'EQ', 'input', NULL, 7, 103, 1, '2025-05-27 21:52:07', 1, '2025-05-27 21:52:07');
INSERT INTO `gen_table_column` VALUES (202180247427219462, 202180247162978304, 'create_time', '创建时间', 'datetime', 'Date', '', -1, 'createTime', '0', '0', '1', NULL, NULL, NULL, NULL, 'EQ', 'datetime', NULL, 8, 103, 1, '2025-05-27 21:52:07', 1, '2025-05-27 21:52:07');
INSERT INTO `gen_table_column` VALUES (202180247427219463, 202180247162978304, 'update_by', '更新者', 'bigint', 'Long', '', -1, 'updateBy', '0', '0', '1', NULL, NULL, NULL, NULL, 'EQ', 'input', NULL, 9, 103, 1, '2025-05-27 21:52:07', 1, '2025-05-27 21:52:07');
INSERT INTO `gen_table_column` VALUES (202180247427219464, 202180247162978304, 'update_time', '更新时间', 'datetime', 'Date', '', -1, 'updateTime', '0', '0', '1', NULL, NULL, NULL, NULL, 'EQ', 'datetime', NULL, 10, 103, 1, '2025-05-27 21:52:07', 1, '2025-05-27 21:52:07');
INSERT INTO `gen_table_column` VALUES (202180247427219465, 202180247162978304, 'remark', '备注', 'varchar', 'String', '', 500, 'remark', '0', '0', '1', '1', '1', '1', NULL, 'EQ', 'input', NULL, 11, 103, 1, '2025-05-27 21:52:07', 1, '2025-05-27 21:52:07');
INSERT INTO `gen_table_column` VALUES (202184617573269504, 202184617371942912, 'id', '字典ID', 'bigint', 'Long', '', -1, 'id', '1', '0', '1', NULL, '1', '1', NULL, 'EQ', 'input', NULL, 1, 103, 1, '2025-05-27 22:09:29', 1, '2025-05-27 22:09:29');
INSERT INTO `gen_table_column` VALUES (202184617573269505, 202184617371942912, 'parent_id', '父级ID', 'bigint', 'Long', '0', -1, 'parentId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', NULL, 2, 103, 1, '2025-05-27 22:09:29', 1, '2025-05-27 22:09:29');
INSERT INTO `gen_table_column` VALUES (202184617573269506, 202184617371942912, 'dict_sort', '字典排序', 'bigint', 'Long', '0', -1, 'dictSort', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', NULL, 3, 103, 1, '2025-05-27 22:09:29', 1, '2025-05-27 22:09:29');
INSERT INTO `gen_table_column` VALUES (202184617573269507, 202184617371942912, 'dict_label', '字典标签', 'varchar', 'String', '', 100, 'dictLabel', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', NULL, 4, 103, 1, '2025-05-27 22:09:29', 1, '2025-05-27 22:09:29');
INSERT INTO `gen_table_column` VALUES (202184617573269508, 202184617371942912, 'dict_value', '字典键值', 'varchar', 'String', '', 100, 'dictValue', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', NULL, 5, 103, 1, '2025-05-27 22:09:29', 1, '2025-05-27 22:09:29');
INSERT INTO `gen_table_column` VALUES (202184617577463808, 202184617371942912, 'dict_type_key', '字典类型', 'varchar', 'String', '', 100, 'dictTypeKey', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', NULL, 6, 103, 1, '2025-05-27 22:09:29', 1, '2025-05-27 22:09:29');
INSERT INTO `gen_table_column` VALUES (202184617577463809, 202184617371942912, 'css_class', '样式属性（其他样式扩展）', 'varchar', 'String', '', 100, 'cssClass', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', NULL, 7, 103, 1, '2025-05-27 22:09:29', 1, '2025-05-27 22:09:29');
INSERT INTO `gen_table_column` VALUES (202184617577463810, 202184617371942912, 'list_class', '表格回显样式', 'varchar', 'String', '', 100, 'listClass', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', NULL, 8, 103, 1, '2025-05-27 22:09:29', 1, '2025-05-27 22:09:29');
INSERT INTO `gen_table_column` VALUES (202184617577463811, 202184617371942912, 'is_default', '是否默认（Y是 N否）', 'varchar', 'String', 'N', 1, 'isDefault', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', NULL, 9, 103, 1, '2025-05-27 22:09:29', 1, '2025-05-27 22:09:29');
INSERT INTO `gen_table_column` VALUES (202184617577463812, 202184617371942912, 'remark', '备注', 'varchar', 'String', '', 500, 'remark', '0', '0', '1', '1', '1', '1', NULL, 'EQ', 'input', NULL, 10, 103, 1, '2025-05-27 22:09:29', 1, '2025-05-27 22:09:29');
INSERT INTO `gen_table_column` VALUES (202184617577463813, 202184617371942912, 'tenant_id', '租户Id', 'varchar', 'String', '', 10, 'tenantId', '0', '0', '1', NULL, NULL, NULL, NULL, 'EQ', 'input', NULL, 11, 103, 1, '2025-05-27 22:09:29', 1, '2025-05-27 22:09:29');
INSERT INTO `gen_table_column` VALUES (202184617577463814, 202184617371942912, 'create_dept', '创建部门', 'bigint', 'Long', '', -1, 'createDept', '0', '0', '1', NULL, NULL, NULL, NULL, 'EQ', 'input', NULL, 12, 103, 1, '2025-05-27 22:09:29', 1, '2025-05-27 22:09:29');
INSERT INTO `gen_table_column` VALUES (202184617577463815, 202184617371942912, 'create_by', '创建者', 'bigint', 'Long', '', -1, 'createBy', '0', '0', '1', NULL, NULL, NULL, NULL, 'EQ', 'input', NULL, 13, 103, 1, '2025-05-27 22:09:29', 1, '2025-05-27 22:09:29');
INSERT INTO `gen_table_column` VALUES (202184617577463816, 202184617371942912, 'create_time', '创建时间', 'datetime', 'Date', '', -1, 'createTime', '0', '0', '1', NULL, NULL, NULL, NULL, 'EQ', 'datetime', NULL, 14, 103, 1, '2025-05-27 22:09:29', 1, '2025-05-27 22:09:29');
INSERT INTO `gen_table_column` VALUES (202184617577463817, 202184617371942912, 'update_by', '更新者', 'bigint', 'Long', '', -1, 'updateBy', '0', '0', '1', NULL, NULL, NULL, NULL, 'EQ', 'input', NULL, 15, 103, 1, '2025-05-27 22:09:29', 1, '2025-05-27 22:09:29');
INSERT INTO `gen_table_column` VALUES (202184617577463818, 202184617371942912, 'update_time', '更新时间', 'datetime', 'Date', '', -1, 'updateTime', '0', '0', '1', NULL, NULL, NULL, NULL, 'EQ', 'datetime', NULL, 16, 103, 1, '2025-05-27 22:09:29', 1, '2025-05-27 22:09:29');
INSERT INTO `gen_table_column` VALUES (202194388988825600, 202194388674252800, 'id', '参数主键', 'bigint', 'Long', '', -1, 'id', '1', '0', '1', NULL, '1', '1', NULL, 'EQ', 'input', NULL, 1, 103, 1, '2025-05-27 22:48:19', 1, '2025-05-27 22:48:19');
INSERT INTO `gen_table_column` VALUES (202194388993019904, 202194388674252800, 'tenant_id', '租户编号', 'varchar', 'String', '000000', 20, 'tenantId', '0', '0', '1', NULL, NULL, NULL, NULL, 'EQ', 'input', NULL, 2, 103, 1, '2025-05-27 22:48:19', 1, '2025-05-27 22:48:19');
INSERT INTO `gen_table_column` VALUES (202194388993019905, 202194388674252800, 'config_name', '参数名称', 'varchar', 'String', '', 100, 'configName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', NULL, 3, 103, 1, '2025-05-27 22:48:19', 1, '2025-05-27 22:48:19');
INSERT INTO `gen_table_column` VALUES (202194388993019906, 202194388674252800, 'config_key', '参数键名', 'varchar', 'String', '', 100, 'configKey', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', NULL, 4, 103, 1, '2025-05-27 22:48:19', 1, '2025-05-27 22:48:19');
INSERT INTO `gen_table_column` VALUES (202194388993019907, 202194388674252800, 'config_value', '参数键值', 'varchar', 'String', '', 500, 'configValue', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', NULL, 5, 103, 1, '2025-05-27 22:48:19', 1, '2025-05-27 22:48:19');
INSERT INTO `gen_table_column` VALUES (202194388993019908, 202194388674252800, 'config_type', '系统内置（Y是 N否）', 'char', 'String', 'N', 1, 'configType', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', NULL, 6, 103, 1, '2025-05-27 22:48:19', 1, '2025-05-27 22:48:19');
INSERT INTO `gen_table_column` VALUES (202194388993019909, 202194388674252800, 'create_dept', '创建部门', 'bigint', 'Long', '', -1, 'createDept', '0', '0', '1', NULL, NULL, NULL, NULL, 'EQ', 'input', NULL, 7, 103, 1, '2025-05-27 22:48:19', 1, '2025-05-27 22:48:19');
INSERT INTO `gen_table_column` VALUES (202194388993019910, 202194388674252800, 'create_by', '创建者', 'bigint', 'Long', '', -1, 'createBy', '0', '0', '1', NULL, NULL, NULL, NULL, 'EQ', 'input', NULL, 8, 103, 1, '2025-05-27 22:48:19', 1, '2025-05-27 22:48:19');
INSERT INTO `gen_table_column` VALUES (202194388997214208, 202194388674252800, 'create_time', '创建时间', 'datetime', 'Date', '', -1, 'createTime', '0', '0', '1', NULL, NULL, NULL, NULL, 'EQ', 'datetime', NULL, 9, 103, 1, '2025-05-27 22:48:19', 1, '2025-05-27 22:48:19');
INSERT INTO `gen_table_column` VALUES (202194388997214209, 202194388674252800, 'update_by', '更新者', 'bigint', 'Long', '', -1, 'updateBy', '0', '0', '1', NULL, NULL, NULL, NULL, 'EQ', 'input', NULL, 10, 103, 1, '2025-05-27 22:48:19', 1, '2025-05-27 22:48:19');
INSERT INTO `gen_table_column` VALUES (202194388997214210, 202194388674252800, 'update_time', '更新时间', 'datetime', 'Date', '', -1, 'updateTime', '0', '0', '1', NULL, NULL, NULL, NULL, 'EQ', 'datetime', NULL, 11, 103, 1, '2025-05-27 22:48:19', 1, '2025-05-27 22:48:19');
INSERT INTO `gen_table_column` VALUES (202194388997214211, 202194388674252800, 'remark', '备注', 'varchar', 'String', '', 500, 'remark', '0', '0', '1', '1', '1', '1', NULL, 'EQ', 'input', NULL, 12, 103, 1, '2025-05-27 22:48:19', 1, '2025-05-27 22:48:19');
INSERT INTO `gen_table_column` VALUES (205011739425681408, 205011739299852288, 'post_id', '岗位ID', 'bigint', 'Long', '', -1, 'postId', '1', '0', '1', NULL, '1', '1', NULL, 'EQ', 'input', NULL, 1, 103, 1, '2025-06-04 17:23:28', 1, '2025-06-04 17:23:28');
INSERT INTO `gen_table_column` VALUES (205011739429875712, 205011739299852288, 'tenant_id', '租户编号', 'varchar', 'String', '000000', 20, 'tenantId', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', NULL, 2, 103, 1, '2025-06-04 17:23:28', 1, '2025-06-04 17:23:28');
INSERT INTO `gen_table_column` VALUES (205011739429875713, 205011739299852288, 'dept_id', '部门id', 'bigint', 'Long', '', -1, 'deptId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', NULL, 3, 103, 1, '2025-06-04 17:23:28', 1, '2025-06-04 17:23:28');
INSERT INTO `gen_table_column` VALUES (205011739429875714, 205011739299852288, 'post_code', '岗位编码', 'varchar', 'String', '', 64, 'postCode', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', NULL, 4, 103, 1, '2025-06-04 17:23:28', 1, '2025-06-04 17:23:28');
INSERT INTO `gen_table_column` VALUES (205011739429875715, 205011739299852288, 'post_category', '岗位类别编码', 'varchar', 'String', '', 100, 'postCategory', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', NULL, 5, 103, 1, '2025-06-04 17:23:28', 1, '2025-06-04 17:23:28');
INSERT INTO `gen_table_column` VALUES (205011739429875716, 205011739299852288, 'post_name', '岗位名称', 'varchar', 'String', '', 50, 'postName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', NULL, 6, 103, 1, '2025-06-04 17:23:28', 1, '2025-06-04 17:23:28');
INSERT INTO `gen_table_column` VALUES (205011739429875717, 205011739299852288, 'post_sort', '显示顺序', 'int', 'Long', '', -1, 'postSort', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', NULL, 7, 103, 1, '2025-06-04 17:23:28', 1, '2025-06-04 17:23:28');
INSERT INTO `gen_table_column` VALUES (205011739429875718, 205011739299852288, 'status', '状态（0正常 1停用）', 'char', 'String', '', 1, 'status', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'radio', NULL, 8, 103, 1, '2025-06-04 17:23:28', 1, '2025-06-04 17:23:28');
INSERT INTO `gen_table_column` VALUES (205011739429875719, 205011739299852288, 'create_dept', '创建部门', 'bigint', 'Long', '', -1, 'createDept', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', NULL, 9, 103, 1, '2025-06-04 17:23:28', 1, '2025-06-04 17:23:28');
INSERT INTO `gen_table_column` VALUES (205011739429875720, 205011739299852288, 'create_by', '创建者', 'bigint', 'Long', '', -1, 'createBy', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', NULL, 10, 103, 1, '2025-06-04 17:23:28', 1, '2025-06-04 17:23:28');
INSERT INTO `gen_table_column` VALUES (205011739429875721, 205011739299852288, 'create_time', '创建时间', 'datetime', 'Date', '', -1, 'createTime', '0', '0', '0', NULL, NULL, '1', '1', 'BETWEEN', 'datetime', NULL, 11, 103, 1, '2025-06-04 17:23:28', 1, '2025-06-04 17:23:28');
INSERT INTO `gen_table_column` VALUES (205011739429875722, 205011739299852288, 'update_by', '更新者', 'bigint', 'Long', '', -1, 'updateBy', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', NULL, 12, 103, 1, '2025-06-04 17:23:28', 1, '2025-06-04 17:23:28');
INSERT INTO `gen_table_column` VALUES (205011739429875723, 205011739299852288, 'update_time', '更新时间', 'datetime', 'Date', '', -1, 'updateTime', '0', '0', '0', NULL, NULL, NULL, NULL, 'BETWEEN', 'datetime', NULL, 13, 103, 1, '2025-06-04 17:23:28', 1, '2025-06-04 17:23:28');
INSERT INTO `gen_table_column` VALUES (205011739429875724, 205011739299852288, 'remark', '备注', 'varchar', 'String', '', 500, 'remark', '0', '0', '1', NULL, '1', '1', NULL, 'EQ', 'input', NULL, 14, 103, 1, '2025-06-04 17:23:28', 1, '2025-06-04 17:23:28');
INSERT INTO `gen_table_column` VALUES (205336062195666944, 205336062090809344, 'id', '用户ID', 'bigint', 'Long', '', -1, 'id', '1', '0', '1', NULL, '1', '1', NULL, 'EQ', 'input', NULL, 1, 103, 1, '2025-06-05 14:52:12', 1, '2025-06-05 14:52:12');
INSERT INTO `gen_table_column` VALUES (205336062199861248, 205336062090809344, 'tenant_id', '租户编号', 'varchar', 'String', '000000', 20, 'tenantId', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', NULL, 2, 103, 1, '2025-06-05 14:52:12', 1, '2025-06-05 14:52:12');
INSERT INTO `gen_table_column` VALUES (205336062199861249, 205336062090809344, 'dept_id', '部门ID', 'bigint', 'Long', '', -1, 'deptId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', NULL, 3, 103, 1, '2025-06-05 14:52:12', 1, '2025-06-05 14:52:12');
INSERT INTO `gen_table_column` VALUES (205336062199861250, 205336062090809344, 'user_name', '用户账号', 'varchar', 'String', '', 30, 'userName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', NULL, 4, 103, 1, '2025-06-05 14:52:12', 1, '2025-06-05 14:52:12');
INSERT INTO `gen_table_column` VALUES (205336062199861251, 205336062090809344, 'nick_name', '用户昵称', 'varchar', 'String', '', 30, 'nickName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', NULL, 5, 103, 1, '2025-06-05 14:52:12', 1, '2025-06-05 14:52:12');
INSERT INTO `gen_table_column` VALUES (205336062199861252, 205336062090809344, 'user_type', '用户类型（sys_user系统用户）', 'varchar', 'String', 'sys_user', 10, 'userType', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', NULL, 6, 103, 1, '2025-06-05 14:52:12', 1, '2025-06-05 14:52:12');
INSERT INTO `gen_table_column` VALUES (205336062199861253, 205336062090809344, 'email', '用户邮箱', 'varchar', 'String', '', 50, 'email', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', NULL, 7, 103, 1, '2025-06-05 14:52:12', 1, '2025-06-05 14:52:12');
INSERT INTO `gen_table_column` VALUES (205336062199861254, 205336062090809344, 'phonenumber', '手机号码', 'varchar', 'String', '', 11, 'phonenumber', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', NULL, 8, 103, 1, '2025-06-05 14:52:12', 1, '2025-06-05 14:52:12');
INSERT INTO `gen_table_column` VALUES (205336062199861255, 205336062090809344, 'sex', '用户性别（0男 1女 2未知）', 'char', 'String', '0', 1, 'sex', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', NULL, 9, 103, 1, '2025-06-05 14:52:12', 1, '2025-06-05 14:52:12');
INSERT INTO `gen_table_column` VALUES (205336062199861256, 205336062090809344, 'avatar', '头像地址', 'bigint', 'Long', '', -1, 'avatar', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', NULL, 10, 103, 1, '2025-06-05 14:52:12', 1, '2025-06-05 14:52:12');
INSERT INTO `gen_table_column` VALUES (205336062199861257, 205336062090809344, 'password', '密码', 'varchar', 'String', '', 100, 'password', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', NULL, 11, 103, 1, '2025-06-05 14:52:12', 1, '2025-06-05 14:52:12');
INSERT INTO `gen_table_column` VALUES (205336062199861258, 205336062090809344, 'status', '帐号状态（0正常 1停用）', 'char', 'String', '0', 1, 'status', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'radio', NULL, 12, 103, 1, '2025-06-05 14:52:12', 1, '2025-06-05 14:52:12');
INSERT INTO `gen_table_column` VALUES (205336062199861259, 205336062090809344, 'del_flag', '删除标志（0代表存在 1代表删除）', 'char', 'String', '0', 1, 'delFlag', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', NULL, 13, 103, 1, '2025-06-05 14:52:12', 1, '2025-06-05 14:52:12');
INSERT INTO `gen_table_column` VALUES (205336062199861260, 205336062090809344, 'login_ip', '最后登录IP', 'varchar', 'String', '', 128, 'loginIp', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', NULL, 14, 103, 1, '2025-06-05 14:52:12', 1, '2025-06-05 14:52:12');
INSERT INTO `gen_table_column` VALUES (205336062199861261, 205336062090809344, 'login_date', '最后登录时间', 'datetime', 'Date', '', -1, 'loginDate', '0', '0', '1', '1', '1', '1', '1', 'BETWEEN', 'datetime', NULL, 15, 103, 1, '2025-06-05 14:52:12', 1, '2025-06-05 14:52:12');
INSERT INTO `gen_table_column` VALUES (205336062199861262, 205336062090809344, 'create_dept', '创建部门', 'bigint', 'Long', '', -1, 'createDept', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', NULL, 16, 103, 1, '2025-06-05 14:52:12', 1, '2025-06-05 14:52:12');
INSERT INTO `gen_table_column` VALUES (205336062199861263, 205336062090809344, 'create_by', '创建者', 'bigint', 'Long', '', -1, 'createBy', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', NULL, 17, 103, 1, '2025-06-05 14:52:12', 1, '2025-06-05 14:52:12');
INSERT INTO `gen_table_column` VALUES (205336062199861264, 205336062090809344, 'create_time', '创建时间', 'datetime', 'Date', '', -1, 'createTime', '0', '0', '0', NULL, NULL, '1', '1', 'BETWEEN', 'datetime', NULL, 18, 103, 1, '2025-06-05 14:52:12', 1, '2025-06-05 14:52:12');
INSERT INTO `gen_table_column` VALUES (205336062199861265, 205336062090809344, 'update_by', '更新者', 'bigint', 'Long', '', -1, 'updateBy', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', NULL, 19, 103, 1, '2025-06-05 14:52:12', 1, '2025-06-05 14:52:12');
INSERT INTO `gen_table_column` VALUES (205336062199861266, 205336062090809344, 'update_time', '更新时间', 'datetime', 'Date', '', -1, 'updateTime', '0', '0', '0', NULL, NULL, NULL, NULL, 'BETWEEN', 'datetime', NULL, 20, 103, 1, '2025-06-05 14:52:12', 1, '2025-06-05 14:52:12');
INSERT INTO `gen_table_column` VALUES (205336062199861267, 205336062090809344, 'remark', '备注', 'varchar', 'String', '', 500, 'remark', '0', '0', '1', NULL, '1', '1', NULL, 'EQ', 'input', NULL, 21, 103, 1, '2025-06-05 14:52:12', 1, '2025-06-05 14:52:12');
INSERT INTO `gen_table_column` VALUES (205373899398049792, 205373899377078272, 'role_id', '角色ID', 'bigint', 'Long', '', -1, 'roleId', '1', '0', '1', NULL, '1', '1', NULL, 'EQ', 'input', NULL, 1, 103, 1, '2025-06-05 17:22:33', 1, '2025-06-05 17:22:33');
INSERT INTO `gen_table_column` VALUES (205373899398049793, 205373899377078272, 'tenant_id', '租户编号', 'varchar', 'String', '000000', 20, 'tenantId', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', NULL, 2, 103, 1, '2025-06-05 17:22:33', 1, '2025-06-05 17:22:33');
INSERT INTO `gen_table_column` VALUES (205373899398049794, 205373899377078272, 'role_name', '角色名称', 'varchar', 'String', '', 30, 'roleName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', NULL, 3, 103, 1, '2025-06-05 17:22:33', 1, '2025-06-05 17:22:33');
INSERT INTO `gen_table_column` VALUES (205373899398049795, 205373899377078272, 'role_key', '角色权限字符串', 'varchar', 'String', '', 100, 'roleKey', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', NULL, 4, 103, 1, '2025-06-05 17:22:33', 1, '2025-06-05 17:22:33');
INSERT INTO `gen_table_column` VALUES (205373899398049796, 205373899377078272, 'role_sort', '显示顺序', 'int', 'Long', '', -1, 'roleSort', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', NULL, 5, 103, 1, '2025-06-05 17:22:33', 1, '2025-06-05 17:22:33');
INSERT INTO `gen_table_column` VALUES (205373899398049797, 205373899377078272, 'data_scope', '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限 5：仅本人数据权限 6：部门及以下或本人数据权限）', 'char', 'String', '1', 1, 'dataScope', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', NULL, 6, 103, 1, '2025-06-05 17:22:33', 1, '2025-06-05 17:22:33');
INSERT INTO `gen_table_column` VALUES (205373899398049798, 205373899377078272, 'menu_check_strictly', '菜单树选择项是否关联显示', 'tinyint', 'Long', '1', -1, 'menuCheckStrictly', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', NULL, 7, 103, 1, '2025-06-05 17:22:33', 1, '2025-06-05 17:22:33');
INSERT INTO `gen_table_column` VALUES (205373899398049799, 205373899377078272, 'dept_check_strictly', '部门树选择项是否关联显示', 'tinyint', 'Long', '1', -1, 'deptCheckStrictly', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', NULL, 8, 103, 1, '2025-06-05 17:22:33', 1, '2025-06-05 17:22:33');
INSERT INTO `gen_table_column` VALUES (205373899398049800, 205373899377078272, 'status', '角色状态（0正常 1停用）', 'char', 'String', '', 1, 'status', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'radio', NULL, 9, 103, 1, '2025-06-05 17:22:33', 1, '2025-06-05 17:22:33');
INSERT INTO `gen_table_column` VALUES (205373899402244096, 205373899377078272, 'del_flag', '删除标志（0代表存在 1代表删除）', 'char', 'String', '0', 1, 'delFlag', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', NULL, 10, 103, 1, '2025-06-05 17:22:33', 1, '2025-06-05 17:22:33');
INSERT INTO `gen_table_column` VALUES (205373899402244097, 205373899377078272, 'create_dept', '创建部门', 'bigint', 'Long', '', -1, 'createDept', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', NULL, 11, 103, 1, '2025-06-05 17:22:33', 1, '2025-06-05 17:22:33');
INSERT INTO `gen_table_column` VALUES (205373899402244098, 205373899377078272, 'create_by', '创建者', 'bigint', 'Long', '', -1, 'createBy', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', NULL, 12, 103, 1, '2025-06-05 17:22:33', 1, '2025-06-05 17:22:33');
INSERT INTO `gen_table_column` VALUES (205373899402244099, 205373899377078272, 'create_time', '创建时间', 'datetime', 'Date', '', -1, 'createTime', '0', '0', '0', NULL, NULL, '1', '1', 'BETWEEN', 'datetime', NULL, 13, 103, 1, '2025-06-05 17:22:33', 1, '2025-06-05 17:22:33');
INSERT INTO `gen_table_column` VALUES (205373899402244100, 205373899377078272, 'update_by', '更新者', 'bigint', 'Long', '', -1, 'updateBy', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', NULL, 14, 103, 1, '2025-06-05 17:22:33', 1, '2025-06-05 17:22:33');
INSERT INTO `gen_table_column` VALUES (205373899402244101, 205373899377078272, 'update_time', '更新时间', 'datetime', 'Date', '', -1, 'updateTime', '0', '0', '0', NULL, NULL, NULL, NULL, 'BETWEEN', 'datetime', NULL, 15, 103, 1, '2025-06-05 17:22:33', 1, '2025-06-05 17:22:33');
INSERT INTO `gen_table_column` VALUES (205373899402244102, 205373899377078272, 'remark', '备注', 'varchar', 'String', '', 500, 'remark', '0', '0', '1', NULL, '1', '1', NULL, 'EQ', 'input', NULL, 16, 103, 1, '2025-06-05 17:22:33', 1, '2025-06-05 17:22:33');
INSERT INTO `gen_table_column` VALUES (205625923867320320, 205625923762462720, 'id', '菜单ID', 'bigint', 'Long', '', -1, 'id', '1', '0', '1', '', '1', '1', '', 'EQ', 'input', '', 1, 103, 1, '2025-06-06 10:04:01', 1, '2025-06-06 10:04:10');
INSERT INTO `gen_table_column` VALUES (205625923867320321, 205625923762462720, 'menu_name', '菜单名称', 'varchar', 'String', '', 50, 'menuName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 2, 103, 1, '2025-06-06 10:04:01', 1, '2025-06-06 10:04:10');
INSERT INTO `gen_table_column` VALUES (205625923867320322, 205625923762462720, 'parent_id', '父菜单ID', 'bigint', 'Long', '0', -1, 'parentId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 103, 1, '2025-06-06 10:04:01', 1, '2025-06-06 10:04:10');
INSERT INTO `gen_table_column` VALUES (205625923867320323, 205625923762462720, 'order_num', '显示顺序', 'int', 'Long', '0', -1, 'orderNum', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, 103, 1, '2025-06-06 10:04:01', 1, '2025-06-06 10:04:10');
INSERT INTO `gen_table_column` VALUES (205625923867320324, 205625923762462720, 'path', '路由地址', 'varchar', 'String', '', 200, 'path', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, 103, 1, '2025-06-06 10:04:01', 1, '2025-06-06 10:04:10');
INSERT INTO `gen_table_column` VALUES (205625923867320325, 205625923762462720, 'component', '组件路径', 'varchar', 'String', '', 255, 'component', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 6, 103, 1, '2025-06-06 10:04:01', 1, '2025-06-06 10:04:10');
INSERT INTO `gen_table_column` VALUES (205625923867320326, 205625923762462720, 'query_param', '路由参数', 'varchar', 'String', '', 255, 'queryParam', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 7, 103, 1, '2025-06-06 10:04:01', 1, '2025-06-06 10:04:10');
INSERT INTO `gen_table_column` VALUES (205625923871514624, 205625923762462720, 'is_frame', '是否为外链（0是 1否）', 'int', 'Long', '1', -1, 'isFrame', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 8, 103, 1, '2025-06-06 10:04:01', 1, '2025-06-06 10:04:10');
INSERT INTO `gen_table_column` VALUES (205625923871514625, 205625923762462720, 'is_cache', '是否缓存（0缓存 1不缓存）', 'int', 'Long', '0', -1, 'isCache', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 9, 103, 1, '2025-06-06 10:04:01', 1, '2025-06-06 10:04:10');
INSERT INTO `gen_table_column` VALUES (205625923871514626, 205625923762462720, 'menu_type', '菜单类型（M目录 C菜单 F按钮）', 'char', 'String', '', 1, 'menuType', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 10, 103, 1, '2025-06-06 10:04:01', 1, '2025-06-06 10:04:10');
INSERT INTO `gen_table_column` VALUES (205625923871514627, 205625923762462720, 'visible', '显示状态（0显示 1隐藏）', 'char', 'String', '0', 1, 'visible', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 11, 103, 1, '2025-06-06 10:04:01', 1, '2025-06-06 10:04:10');
INSERT INTO `gen_table_column` VALUES (205625923871514628, 205625923762462720, 'status', '菜单状态（0正常 1停用）', 'char', 'String', '0', 1, 'status', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'radio', '', 12, 103, 1, '2025-06-06 10:04:01', 1, '2025-06-06 10:04:10');
INSERT INTO `gen_table_column` VALUES (205625923871514629, 205625923762462720, 'perms', '权限标识', 'varchar', 'String', '', 100, 'perms', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 13, 103, 1, '2025-06-06 10:04:01', 1, '2025-06-06 10:04:10');
INSERT INTO `gen_table_column` VALUES (205625923871514630, 205625923762462720, 'icon', '菜单图标', 'varchar', 'String', '#', 100, 'icon', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 14, 103, 1, '2025-06-06 10:04:01', 1, '2025-06-06 10:04:10');
INSERT INTO `gen_table_column` VALUES (205625923871514631, 205625923762462720, 'create_dept', '创建部门', 'bigint', 'Long', '', -1, 'createDept', '0', '0', '0', '', '', '', '', 'EQ', 'input', '', 15, 103, 1, '2025-06-06 10:04:01', 1, '2025-06-06 10:04:10');
INSERT INTO `gen_table_column` VALUES (205625923871514632, 205625923762462720, 'create_by', '创建者', 'bigint', 'Long', '', -1, 'createBy', '0', '0', '0', '', '', '', '', 'EQ', 'input', '', 16, 103, 1, '2025-06-06 10:04:01', 1, '2025-06-06 10:04:10');
INSERT INTO `gen_table_column` VALUES (205625923871514633, 205625923762462720, 'create_time', '创建时间', 'datetime', 'Date', '', -1, 'createTime', '0', '0', '0', '', '', '1', '1', 'BETWEEN', 'datetime', '', 17, 103, 1, '2025-06-06 10:04:01', 1, '2025-06-06 10:04:10');
INSERT INTO `gen_table_column` VALUES (205625923871514634, 205625923762462720, 'update_by', '更新者', 'bigint', 'Long', '', -1, 'updateBy', '0', '0', '0', '', '', '', '', 'EQ', 'input', '', 18, 103, 1, '2025-06-06 10:04:01', 1, '2025-06-06 10:04:10');
INSERT INTO `gen_table_column` VALUES (205625923871514635, 205625923762462720, 'update_time', '更新时间', 'datetime', 'Date', '', -1, 'updateTime', '0', '0', '0', '', '', '', '', 'BETWEEN', 'datetime', '', 19, 103, 1, '2025-06-06 10:04:01', 1, '2025-06-06 10:04:10');
INSERT INTO `gen_table_column` VALUES (205625923871514636, 205625923762462720, 'remark', '备注', 'varchar', 'String', '', 500, 'remark', '0', '0', '0', '', '1', '1', '', 'EQ', 'input', '', 20, 103, 1, '2025-06-06 10:04:01', 1, '2025-06-06 10:04:10');

-- ----------------------------
-- Table structure for gen_template
-- ----------------------------
DROP TABLE IF EXISTS `gen_template`;
CREATE TABLE `gen_template`  (
                                 `id` bigint NOT NULL COMMENT '主键',
                                 `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '模板分类，用于批量选择模板',
                                 `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '模板名称',
                                 `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '文件路径',
                                 `db_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '数据库类型',
                                 `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '模板内容',
                                 `enable` int NULL DEFAULT 1 COMMENT '是否启用',
                                 `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
                                 `create_dept` bigint NULL DEFAULT NULL COMMENT '创建部门',
                                 `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
                                 `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                 `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
                                 `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '代码模板' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of gen_template
-- ----------------------------
INSERT INTO `gen_template` VALUES (200038416115593216, 'default', 'domain.java', '${javaPath}/domain/${className}.java', NULL, 'package ${packageName}.domain;\n\nimport cn.xbatis.core.incrementer.IdentifierGeneratorType;\nimport cn.xbatis.db.IdAutoType;\nimport cn.xbatis.db.annotations.*;\n#foreach ($column in $columns)\n#if($column.javaField==\'tenantId\')\n#set($IsTenant=1)\n#end\n#end\n#if($IsTenant==1)\nimport com.jimuqu.common.mybatis.core.entity.TenantEntity;\n#else\nimport com.jimuqu.common.mybatis.core.entity.BaseEntity;\n#end\nimport lombok.Data;\nimport lombok.EqualsAndHashCode;\nimport lombok.NoArgsConstructor;\nimport lombok.ToString;\nimport lombok.experimental.Accessors;\nimport lombok.experimental.FieldNameConstants;\nimport org.dromara.autotable.annotation.AutoColumn;\n#foreach ($import in $importList)\nimport ${import};\n#end\n\nimport java.io.Serial;\n\n/**\n * ${functionName}\n * @author ${author}\n * @since ${datetime}\n */\n#if($IsTenant==1)\n#set($Entity=\"TenantEntity\")\n#else\n#set($Entity=\"BaseEntity\")\n#end\n@Data\n@NoArgsConstructor\n@FieldNameConstants\n@Accessors(chain = true)\n@ToString(callSuper = true)\n@EqualsAndHashCode(callSuper = true)\n@Table(value = \"${tableName}\")\npublic class ${ClassName} extends ${Entity} {\n\n    @Serial\n    private static final long serialVersionUID = 1L;\n\n#foreach ($column in $columns)\n#if(!$table.isSuperColumn($column.javaField))\n    /**\n     * $column.columnComment\n     */\n#if($column.javaField==\'deleted\')\n    @LogicDelete(beforeValue = \"0\", afterValue = \"1\")\n#end\n#if($column.isPk==1)\n    @TableId(value = IdAutoType.GENERATOR, generatorName = IdentifierGeneratorType.DEFAULT)\n#end\n#if($column.javaField==\'version\')\n    @Version\n#end\n    #if($column.htmlType == \'checkbox\')\n    private String $column.javaField;\n    #else\n    @AutoColumn(comment = \"$column.columnComment\"#if($column.length != \'-1\'), length = $column.length#end#if($column.defaultValue != \'\'), defaultValue = \"$column.defaultValue\"#end)\n    private $column.javaType $column.javaField;\n    #end\n#end\n#end\n\n}\n', 1, 'Java 实体类', 103, 1, '2025-05-22 00:01:15', 1, '2025-05-27 16:45:03');
INSERT INTO `gen_template` VALUES (200750781976596480, 'default', 'vo.java', '${javaPath}/domain/vo/${className}Vo.java', NULL, 'package ${packageName}.domain.vo;\n\nimport cn.xbatis.db.annotations.ResultEntity;\nimport ${packageName}.domain.${ClassName};\nimport io.github.linpeilie.annotations.AutoMapper;\nimport lombok.Data;\nimport lombok.experimental.Accessors;\nimport lombok.experimental.FieldNameConstants;\n#foreach ($import in $importList)\nimport ${import};\n#end\n\nimport java.io.Serial;\nimport java.io.Serializable;\n\n/**\n * ${functionName}视图对象\n * @author ${author}\n * @since ${datetime}\n */\n@Data\n@FieldNameConstants\n@Accessors(chain = true)\n@ResultEntity(${ClassName}.class)\n@AutoMapper(target = ${ClassName}.class)\npublic class ${ClassName}Vo implements Serializable {\n\n    @Serial\n    private static final long serialVersionUID = 1L;\n\n#foreach ($column in $columns)\n#if(!$table.isSuperColumn($column.javaField))\n    /**\n     * $column.columnComment\n     */\n    private $column.javaType $column.javaField;\n#end\n#end\n\n}\n', 1, 'Vo 视图类', 103, 1, '2025-05-23 23:11:56', 1, '2025-05-24 02:08:55');
INSERT INTO `gen_template` VALUES (200753415089344512, 'default', 'bo.java', '${javaPath}/domain/bo/${className}Bo.java', NULL, 'package ${packageName}.domain.bo;\n\nimport com.jimuqu.common.core.validate.group.AddGroup;\nimport com.jimuqu.common.core.validate.group.UpdateGroup;\nimport com.jimuqu.common.mybatis.core.entity.BoBaseEntity;\nimport ${packageName}.domain.${ClassName};\nimport io.github.linpeilie.annotations.AutoMapper;\nimport lombok.Data;\nimport lombok.EqualsAndHashCode;\nimport lombok.experimental.Accessors;\nimport org.noear.solon.validation.annotation.*;\n#foreach ($import in $importList)\nimport ${import};\n#end\n\n/**\n * ${functionName}业务对象 ${tableName}\n *\n * @author ${author}\n * @since ${datetime}\n */\n@Data\n@Accessors(chain = true)\n@EqualsAndHashCode(callSuper = true)\n@AutoMapper(target = ${ClassName}.class, reverseConvertGenerate = false)\npublic class ${ClassName}Bo extends BoBaseEntity {\n\n#foreach ($column in $columns)\n#if(!$table.isSuperColumn($column.javaField) && ($column.query || $column.insert || $column.edit))\n    /**\n     * $column.columnComment\n     */\n#if($column.insert && $column.edit)\n#set($Group=\"AddGroup.class, UpdateGroup.class\")\n#elseif($column.insert)\n#set($Group=\"AddGroup.class\")\n#elseif($column.edit)\n#set($Group=\"UpdateGroup.class\")\n#end\n#if($column.required)\n#if($column.javaType == \'String\')\n    @NotBlank(message = \"$column.columnComment不能为空\", groups = { $Group })\n#else\n    @NotNull(message = \"$column.columnComment不能为空\", groups = { $Group })\n#end\n#end\n    private $column.javaType $column.javaField;\n#end\n#end\n\n}\n', 1, 'Bo 业务实体类', 103, 1, '2025-05-23 23:22:24', 1, '2025-05-24 02:26:52');
INSERT INTO `gen_template` VALUES (200761501443215360, 'default', 'query.java', '${javaPath}/domain/query/${className}Query.java', NULL, 'package ${packageName}.domain.query;\n\nimport cn.xbatis.core.sql.ObjectConditionLifeCycle;\nimport cn.xbatis.db.annotations.Condition;\nimport cn.xbatis.db.annotations.ConditionTarget;\nimport ${packageName}.domain.${ClassName};\nimport lombok.Data;\nimport lombok.experimental.FieldNameConstants;\n#foreach ($import in $importList)\nimport ${import};\n#end\n\nimport java.io.Serial;\nimport java.io.Serializable;\n\nimport static cn.xbatis.db.annotations.Condition.Type.*;\n\n/**\n * ${functionName}查询条件对象\n * @author ${author}\n * @since ${datetime}\n */\n@Data\n@FieldNameConstants\n@ConditionTarget(${ClassName}.class)\npublic class ${ClassName}Query implements Serializable, ObjectConditionLifeCycle {\n\n    @Serial\n    private static final long serialVersionUID = 1L;\n\n#foreach ($column in $columns)\n#if(!$table.isSuperColumn($column.javaField))\n    /**\n     * $column.columnComment\n     */\n    @Condition(value = EQ)\n    private $column.javaType $column.javaField;\n#end\n#end\n\n    /**\n     * 条件构建前执行\n     */\n    @Override\n    public void beforeBuildCondition() {\n        \n    }\n\n}\n', 1, '查询条件对象', 103, 1, '2025-05-23 23:54:32', 1, '2025-05-24 02:09:03');
INSERT INTO `gen_template` VALUES (200771511107768320, 'default', 'mapper.java', '${javaPath}/mapper/${className}Mapper.java', NULL, 'package ${packageName}.mapper;\n\nimport com.jimuqu.common.mybatis.core.mapper.BaseMapperPlus;\nimport ${packageName}.domain.${ClassName};\nimport ${packageName}.domain.vo.${ClassName}Vo;\nimport org.apache.ibatis.annotations.Mapper;\n\n/**\n * ${functionName}数据层\n * @author ${author}\n * @since ${datetime}\n */\n@Mapper\npublic interface ${ClassName}Mapper extends BaseMapperPlus<${ClassName}, ${ClassName}Vo> {\n\n}', 1, 'Mapper接口', 103, 1, '2025-05-24 00:34:18', 1, '2025-05-24 01:58:45');
INSERT INTO `gen_template` VALUES (200775763347689472, 'default', 'service.java', '${javaPath}/service/${className}Service.java', NULL, 'package ${packageName}.service;\n\nimport com.jimuqu.common.mybatis.core.Page;\nimport com.jimuqu.common.mybatis.core.page.PageQuery;\nimport ${packageName}.domain.bo.${ClassName}Bo;\nimport ${packageName}.domain.vo.${ClassName}Vo;\nimport ${packageName}.domain.query.${ClassName}Query;\n\nimport java.util.Collection;\nimport java.util.List;\n\n/**\n * ${functionName}Service接口\n *\n * @author ${author}\n * @since ${datetime}\n */\npublic interface ${ClassName}Service {\n\n    /**\n     * 根据主键查询${functionName}\n     *\n     * @param id ${functionName}主键\n     * @return {@link ${ClassName}Vo } ${functionName}视图对象\n     */\n   ${ClassName}Vo queryById(Long id);\n\n    /**\n     * 查询${functionName}分页列表\n     *\n     * @param query 查询条件对象\n     * @param pageQuery 分页条件\n     * @return {@link Page }<{@link ${ClassName}Vo }> ${functionName}分页对象\n     */\n    Page<${ClassName}Vo> queryPageList(${ClassName}Query query, PageQuery pageQuery);\n\n   /**\n     * 查询${functionName}列表\n     *\n     * @param query 查询条件对象\n     * @return {@link List }<{@link ${ClassName}Vo }> ${functionName}列表\n     */\n    List<${ClassName}Vo> queryList(${ClassName}Query query);\n\n    /**\n     * 新增${functionName}\n     *\n     * @param bo ${functionName}业务对象\n     * @return {@link Boolean } 新增是否成功\n     */\n    Boolean insertByBo(${ClassName}Bo bo);\n\n    /**\n     * 更新${functionName}\n     *\n     * @param bo ${functionName}业务对象\n     * @return {@link Boolean } 更新是否成功\n     */\n    Boolean updateByBo(${ClassName}Bo bo);\n\n    /**\n     * 批量删除代码生成模板信息\n     *\n     * @param ids ${functionName}主键列表\n     * @return {@link Integer } 删除成功条数\n     */\n    Integer deleteByIds(Collection<Long> ids);\n}\n', 1, 'service 接口', 103, 1, '2025-05-24 00:51:12', 1, '2025-05-24 02:29:55');
INSERT INTO `gen_template` VALUES (200783937337348096, 'default', 'serviceImpl.java', '${javaPath}/service/impl/${className}ServiceImpl.java', NULL, 'package ${packageName}.service.impl;\n\nimport cn.xbatis.core.sql.executor.chain.QueryChain;\nimport com.jimuqu.common.core.utils.MapstructUtil;\nimport com.jimuqu.common.mybatis.core.Page;\nimport com.jimuqu.common.mybatis.core.page.PageQuery;\nimport ${packageName}.domain.${ClassName};\nimport ${packageName}.domain.bo.${ClassName}Bo;\nimport ${packageName}.domain.vo.${ClassName}Vo;\nimport ${packageName}.domain.query.${ClassName}Query;\nimport ${packageName}.mapper.${ClassName}Mapper;\nimport ${packageName}.service.${ClassName}Service;\nimport lombok.RequiredArgsConstructor;\nimport lombok.extern.slf4j.Slf4j;\nimport org.noear.solon.annotation.Component;\n\nimport java.util.Collection;\nimport java.util.List;\n\n\n/**\n * ${functionName}Service业务层处理\n *\n * @author ${author}\n * @since ${datetime}\n */\n@Slf4j\n@Component\n@RequiredArgsConstructor\npublic class ${ClassName}ServiceImpl implements ${ClassName}Service {\n\n    private final ${ClassName}Mapper ${className}Mapper;\n\n    /**\n     * 查询${functionName}\n     */\n    @Override\n    public ${ClassName}Vo queryById(Long id) {\n        return ${className}Mapper.getVoById(id);\n    }\n\n    /**\n     * 查询${functionName}分页列表\n     */\n    @Override\n    public Page<${ClassName}Vo> queryPageList(${ClassName}Query query, PageQuery pageQuery) {\n        return buildQueryChain(query)\n                .returnType(${ClassName}Vo.class)\n                .paging(pageQuery.build());\n    }\n\n    /**\n     * 查询${functionName}列表\n     */\n    @Override\n    public List<${ClassName}Vo> queryList(${ClassName}Query query) {\n        QueryChain<${ClassName}> queryChain = buildQueryChain(query);\n        return queryChain.returnType(${ClassName}Vo.class).list();\n    }\n\n    /**\n     * 构建查询条件\n     * @param query 查询对象\n     * @return 查询条件对象\n     */\n    private QueryChain<${ClassName}> buildQueryChain(${ClassName}Query query) {\n        return QueryChain.of(${className}Mapper)\n                .forSearch(true)\n                .where(query);\n    }\n\n    /**\n     * 新增${functionName}\n     */\n    @Override\n    public Boolean insertByBo(${ClassName}Bo bo) {\n        ${ClassName} ${className} = MapstructUtil.convert(bo, ${ClassName}.class);\n        boolean flag = ${className}Mapper.save(${className}) > 0;\n        bo.set${pkColumn.getCapJavaField()}(${className}.get${pkColumn.getCapJavaField()}());\n        return flag;\n    }\n\n    /**\n     * 修改${functionName}\n     */\n    @Override\n    public Boolean updateByBo(${ClassName}Bo bo) {\n        ${ClassName} ${className} = MapstructUtil.convert(bo, ${ClassName}.class);\n        return ${className}Mapper.update(${className}) > 0;\n    }\n\n    /**\n     * 批量删除${functionName}\n     */\n    @Override\n    public Integer deleteByIds(Collection<Long> ids) {\n        return ${className}Mapper.deleteByIds(ids);\n    }\n}\n', 1, 'Service 实现类', 103, 1, '2025-05-24 01:23:41', 1, '2025-05-26 00:31:14');
INSERT INTO `gen_template` VALUES (200792488621428736, 'default', 'controller.java', '${javaPath}/controller/${className}Controller.java', NULL, 'package ${packageName}.controller;\n\nimport cn.dev33.satoken.annotation.SaCheckPermission;\nimport com.jimuqu.common.core.checker.Assert;\nimport com.jimuqu.common.core.validate.group.AddGroup;\nimport com.jimuqu.common.core.validate.group.UpdateGroup;\nimport com.jimuqu.common.log.annotation.Log;\nimport com.jimuqu.common.log.enums.BusinessType;\nimport com.jimuqu.common.mybatis.core.Page;\nimport com.jimuqu.common.mybatis.core.page.PageQuery;\nimport com.jimuqu.common.web.core.BaseController;\nimport ${packageName}.domain.bo.${ClassName}Bo;\nimport ${packageName}.domain.vo.${ClassName}Vo;\nimport ${packageName}.domain.query.${ClassName}Query;\nimport ${packageName}.service.${ClassName}Service;\nimport lombok.RequiredArgsConstructor;\nimport org.noear.solon.annotation.Controller;\nimport org.noear.solon.annotation.Get;\nimport org.noear.solon.annotation.Mapping;\nimport org.noear.solon.annotation.Post;\nimport org.noear.solon.validation.annotation.NoRepeatSubmit;\nimport org.noear.solon.validation.annotation.NotEmpty;\nimport org.noear.solon.validation.annotation.NotNull;\nimport org.noear.solon.validation.annotation.Validated;\n\nimport java.util.List;\n\n/**\n * ${functionName} Controller\n *\n * @author ${author}\n * @since ${datetime}\n */\n@Post\n@Controller\n@RequiredArgsConstructor\n@Mapping(\"/${moduleName}/${businessName}\")\npublic class ${ClassName}Controller extends BaseController {\n\n    private final ${ClassName}Service ${className}Service;\n\n    /**\n     * 查询${functionName}列表\n     */\n    @Get\n    @Mapping(\"/list\")\n    @SaCheckPermission(\"${permissionPrefix}:list\")\n    public Page<${ClassName}Vo> list(${ClassName}Query query, PageQuery pageQuery) {\n        return ${className}Service.queryPageList(query, pageQuery);\n    }\n\n    /**\n     * 获取${functionName}详细信息\n     *\n     * @param ${pkColumn.javaField} ${functionName}主键\n     */\n    @Get\n    @Mapping(\"/{${pkColumn.javaField}}\")\n    @SaCheckPermission(\"${permissionPrefix}:query\")\n    public ${ClassName}Vo getInfo(@NotNull(message = \"${functionName}主键不能为空\") ${pkColumn.javaType} ${pkColumn.javaField}) {\n        return ${className}Service.queryById(${pkColumn.javaField});\n    }\n\n    /**\n     * 新增${functionName}\n     */\n    @Mapping(\"/add\")\n    @NoRepeatSubmit\n    @SaCheckPermission(\"${permissionPrefix}:add\")\n    @Log(title = \"新增${functionName}\", businessType = BusinessType.ADD)\n    public Long add(@Validated(AddGroup.class) ${ClassName}Bo bo) {\n        boolean result = ${className}Service.insertByBo(bo);\n        Assert.isTrue(result, \"新增${functionName}失败\");\n        return bo.get${pkColumn.getCapJavaField()}();\n    }\n\n    /**\n     * 更新${functionName}\n     */\n    @NoRepeatSubmit\n    @Mapping(\"/update\")\n    @SaCheckPermission(\"${permissionPrefix}:update\")\n    @Log(title = \"更新${functionName}\", businessType = BusinessType.UPDATE)\n    public void edit(@Validated(UpdateGroup.class) ${ClassName}Bo bo) {\n        boolean result = ${className}Service.updateByBo(bo);\n        Assert.isTrue(result, \"更新${functionName}失败\");\n    }\n\n    /**\n     * 删除${functionName}\n     */\n    @Mapping(\"/delete/{ids}\")\n    @SaCheckPermission(\"${permissionPrefix}:delete\")\n    @Log(title = \"删除${functionName}\", businessType = BusinessType.DELETE)\n    public Integer delete(@NotEmpty(message = \"主键不能为空\") List<${pkColumn.javaType}> ids) {\n        Integer num = ${className}Service.deleteByIds(ids);\n        Assert.gtZero(num, \"删除${functionName}失败\");\n        return num;\n    }\n\n}', 1, 'Controller控制器', 103, 1, '2025-05-24 01:57:40', 1, '2025-05-26 03:07:40');
INSERT INTO `gen_template` VALUES (201522306810417152, 'default', 'index.vue', '${vuePath}/views/${moduleName}/${businessName}/index.vue', NULL, '<script lang=\"ts\" setup>\nimport type { FormType } from \'./form-modal.vue\';\n\nimport { Page, useVbenModal } from \'@vben/common-ui\';\n\nimport { requestClient } from \'#/api/request\';\n\nimport formModal from \'./form-modal.vue\';\n\nconst message = useMessage();\nconst dialog = useDialog();\n\n// 数据类型字段\ninterface ${BusinessName}Vo {\n#foreach ($column in $columns)\n#if($column.list)\n  /** $column.columnComment */\n  $column.javaField:#if($column.javaField.indexOf(\"id\") != -1 || $column.javaField.indexOf(\"Id\") != -1) string#elseif($column.javaType == \'Long\' || $column.javaType == \'Integer\' || $column.javaType == \'Double\' || $column.javaType == \'Float\' || $column.javaType == \'BigDecimal\') number#elseif($column.javaType == \'Boolean\') boolean#else string#end;\n    #if($column.htmlType == \"imageUpload\")\n  /** ${column.columnComment}Url */\n  ${column.javaField}Url: string;\n#end\n#end\n#end\n}\n\n// 查询表单配置\nconst formOptions: VbenFormProps = {\n  // 默认展开\n  collapsed: false,\n  schema: [\n#foreach($column in $columns)\n#if($column.query)\n#set($dictType=$column.dictType)\n#set($AttrName=$column.javaField.substring(0,1).toUpperCase() + ${column.javaField.substring(1)})\n#set($parentheseIndex=$column.columnComment.indexOf(\"（\"))\n#if($parentheseIndex != -1)\n#set($comment=$column.columnComment.substring(0, $parentheseIndex))\n#else\n#set($comment=$column.columnComment)\n#end\n#if($column.htmlType == \"input\" || $column.htmlType == \"textarea\")\n    {\n      component: \'Input\',\n      componentProps: {\n        placeholder: \'请输入${comment}\',\n      },\n      fieldName: \'${column.javaField}\',\n      label: \'${comment}\',\n    },\n#end\n#end\n#end\n  ],\n  // 控制表单是否显示折叠按钮\n  showCollapseButton: true,\n  submitButtonOptions: {\n    content: \'查询\',\n  },\n  // 是否在字段值改变时提交表单进行搜索\n  submitOnChange: false,\n  // 按下回车时是否提交表单进行搜索\n  submitOnEnter: true,\n};\n\n// 表格配置\nconst gridOptions: VxeGridProps<${BusinessName}Vo> = {\n  checkboxConfig: {\n    highlight: true,\n    range: true,\n  },\n  columns: [\n    { align: \'left\', title: \'\', type: \'checkbox\', width: 40 },\n#foreach($column in $columns)\n#set($javaField=$column.javaField)\n#set($parentheseIndex=$column.columnComment.indexOf(\"（\"))\n#if($parentheseIndex != -1)\n#set($comment=$column.columnComment.substring(0, $parentheseIndex))\n#else\n#set($comment=$column.columnComment)\n#end\n#if($column.pk)\n    { field: \'${javaField}\', title: \'${comment}\', visible: ${column.list}},\n#elseif($column.list && $column.htmlType == \"datetime\")\n    { field: \'${javaField}\', formatter: \'formatDateTime\', title: \'${comment}\' },\n#elseif($column.list && \"\" != $javaField)\n    { field: \'${javaField}\', title: \'${comment}\' },\n#end\n#end\n    {\n      field: \'action\',\n      fixed: \'right\',\n      slots: { default: \'action\' },\n      title: \'操作\',\n      width: 150,\n    },\n  ],\n  keepSource: true,\n  pagerConfig: {},\n  height: \'auto\',\n  proxyConfig: {\n    ajax: {\n      query: async ({ page }, formValues) => {\n        const { currentPage, pageSize } = page;\n        return await requestClient.get<${BusinessName}Vo[]>(\'/${moduleName}/${businessName}/list\', {\n          params: {\n            currentPage,\n            pageSize,\n            ...formValues,\n          },\n        });\n      },\n    },\n  },\n  toolbarConfig: {\n    // 是否显示搜索表单控制按钮\n    // @ts-ignore 正式环境时有完整的类型声明\n    custom: true,\n    // import: true,\n    refresh: true,\n    zoom: true,\n  },\n  headerCellConfig: {\n    height: 44,\n  },\n  cellConfig: {\n    height: 48,\n  },\n  rowConfig: {\n    keyField: \'${pkColumn.javaField}\',\n  },\n};\n\nconst [Grid, gridApi] = useVbenVxeGrid({ formOptions, gridOptions });\n\n// ${functionName}表单弹窗\nconst [TemplateFromModal, formModelApi] = useVbenModal({\n  connectedComponent: formModal,\n});\n\nfunction openModal(formType: FormType, row?: ${BusinessName}Vo) {\n  formModelApi\n    .setData({\n      formType,\n      row: row || {},\n    })\n    .open();\n}\n\n/**\n * 删除选中的${functionName}数据\n */\nasync function handleDeleteCheck() {\n  const records = gridApi.grid.getCheckboxRecords();\n  if (records.length <= 0) {\n    message.warning(\'请选择要删除的模板数据\');\n    return;\n  }\n\n  // 确认删除\n  dialog.warning({\n    title: \'删除${functionName}提醒\',\n    content: `你确定要删除${records.length}条数据吗？`,\n    positiveText: \'确定\',\n    negativeText: \'取消\',\n    draggable: true,\n    onPositiveClick: async () => {\n      const ids = records.map((item) => item.${pkColumn.javaField});\n      await handleDelete(ids);\n    },\n  });\n}\n\n/**\n * 删除${functionName}\n * @param id 主键，主键数组\n */\nasync function handleDelete(id: string | string[]) {\n  const data = await requestClient.post(`/${moduleName}/${businessName}/delete/${id}`);\n  message.success(`成功删除${data}条数据`);\n  refreshTable();\n}\n\n/**\n * 刷新${functionName}表格数据\n */\nasync function refreshTable() {\n  gridApi.reload();\n}\n</script>\n\n<template>\n  <Page auto-content-height>\n    <Grid>\n      <template #toolbar-tools>\n        <n-flex class=\"mx-3\" size=\"small\">\n          <!-- <n-button class=\"mr-2\"> 导出 </n-button> -->\n          <n-button class=\"mr-2\" type=\"error\" @click=\"handleDeleteCheck\">\n            删除\n          </n-button>\n          <n-button class=\"mr-2\" type=\"primary\" @click=\"openModal(\'add\')\">\n            新增\n          </n-button>\n        </n-flex>\n      </template>\n      <template #action=\"{ row }\">\n        <n-flex class=\"mx-3\" justify=\"space-around\" size=\"small\">\n          <n-button\n            type=\"info\"\n            size=\"small\"\n            @click=\"openModal(\'update\', row)\"\n            ghost\n          >\n            编辑\n          </n-button>\n          <n-popconfirm @positive-click=\"handleDelete(row.${pkColumn.javaField})\">\n            <template #trigger>\n              <n-button type=\"error\" size=\"small\" ghost>删除</n-button>\n            </template>\n            确认删除该${functionName}吗？\n          </n-popconfirm>\n        </n-flex>\n      </template>\n    </Grid>\n    <TemplateFromModal @reload=\"refreshTable\" />\n  </Page>\n</template>\n', 1, 'Vue3 列表页', 103, 1, '2025-05-26 02:17:42', 1, '2025-06-05 17:17:03');
INSERT INTO `gen_template` VALUES (201527495818776576, 'default', 'form-modal.vue', '${vuePath}/views/${moduleName}/${businessName}/form-modal.vue', NULL, '<script setup lang=\"ts\">\nimport { useVbenModal } from \'@vben/common-ui\';\nimport { cloneDeep } from \'@vben/utils\';\n\nimport { requestClient } from \'#/api/request\';\n\nconst emit = defineEmits<{ reload: [] }>();\n\nconst message = useMessage();\n\n// 表单类型\nexport type FormType = \'add\' | \'update\';\nconst currentType: Ref<FormType> = ref(\'add\');\n\n// 表单不同类型参数\nconst formTypeData: Record<FormType, Record<string, any>> = {\n  add: {\n    title: \'新增${functionName}\',\n    url: \'/${moduleName}/${businessName}/add\',\n  },\n  update: {\n    title: \'编辑${functionName}\',\n    url: \'/${moduleName}/${businessName}/update\',\n  },\n};\n\n// 当前表单类型参数\nconst currentFormTypeData = computed(() => {\n  return formTypeData[currentType.value];\n});\n\n// 表单字段配置\nconst [${BusinessName}Form, formApi] = useVbenForm({\n  schema: [\n    {\n      label: \'${pkColumn.columnComment}\',\n      fieldName: \'${pkColumn.javaField}\',\n      component: \'Input\',\n      dependencies: {\n        // 使用css方式隐藏 但仍然可赋值\n        show: () => false,\n        // 注意这个一定要为[\'\']  否则不能被正常隐藏\n        triggerFields: [\'\'],\n      },\n    },\n#foreach($column in $columns)\n#set($field=$column.javaField)\n#if(($column.insert || $column.edit) && !$column.pk)\n#set($parentheseIndex=$column.columnComment.indexOf(\"（\"))\n#if($parentheseIndex != -1)\n#set($comment=$column.columnComment.substring(0, $parentheseIndex))\n#else\n#set($comment=$column.columnComment)\n#end\n#set($dictType=$column.dictType)\n#if($column.htmlType == \"input\")\n    {\n      fieldName: \'${field}\',\n      label: \'${comment}\',\n      component: \'Input\',\n#if($column.required)\n      rules: \'required\',\n#end\n      componentProps: {\n        placeholder: \'请输入${comment}\',\n      },\n    },\n#elseif($column.htmlType == \"textarea\")\n    {\n      fieldName: \'${field}\',\n      label: \'${comment}\',\n      component: \'Input\',\n      componentProps: {\n        type: \'textarea\',\n        placeholder: \'请输入${comment}\',\n      },\n#if($column.required)\n      rules: \'required\',\n#end\n    },\n#end\n#end\n#end\n  ],\n  showDefaultActions: false,\n});\n\n// ${functionName}表单弹窗\nconst [FormModel, formModelApi] = useVbenModal({\n  onConfirm: handleConfirm,\n  onOpenChange(isOpen: boolean) {\n    if (isOpen) {\n      const { formType, row } = formModelApi.getData<Record<string, any>>();\n      currentType.value = formType;\n      if (row) {\n        formApi.setValues(row);\n      }\n    }\n  },\n});\n\n// 表单提交\nasync function handleConfirm() {\n  const loading = message.loading(`正在${currentFormTypeData.value.title}...`);\n  try {\n    // 锁定表单弹窗禁止操作\n    formModelApi.lock();\n\n    // 校验表单\n    const { valid } = await formApi.validate();\n    if (!valid) {\n      return;\n    }\n\n    // 表单数据\n    const data = cloneDeep(await formApi.getValues());\n    await requestClient.post(currentFormTypeData.value.url, {\n      ...data,\n      category: \'default\',\n    });\n\n    // 提示成功\n    message.success(`${currentFormTypeData.value.title}成功`);\n\n    // 刷新表格\n    emit(\'reload\');\n    // 关闭表单弹窗\n    formModelApi.close();\n  } catch (error) {\n    console.error(error);\n  } finally {\n    // 解锁表单弹窗\n    formModelApi.lock(false);\n    loading.destroy();\n  }\n}\n</script>\n<template>\n  <FormModel :title=\"currentFormTypeData.title\">\n    <${BusinessName}Form />\n  </FormModel>\n</template>\n', 1, 'vue3 表单弹窗页', 103, 1, '2025-05-26 02:38:19', 1, '2025-05-26 03:03:35');

-- ----------------------------
-- Table structure for sys_client
-- ----------------------------
DROP TABLE IF EXISTS `sys_client`;
CREATE TABLE `sys_client`  (
                               `id` bigint NOT NULL COMMENT '主键',
                               `client_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户端id',
                               `client_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户端key',
                               `client_secret` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户端秘钥',
                               `grant_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '授权类型',
                               `device_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '设备类型',
                               `active_timeout` bigint NULL DEFAULT -1 COMMENT 'token活跃超时时间',
                               `timeout` bigint NULL DEFAULT -1 COMMENT 'token固定超时时间',
                               `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
                               `del_flag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
                               `create_dept` bigint NULL DEFAULT NULL COMMENT '创建部门',
                               `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
                               `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                               `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
                               `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '授权管理对象 sys_client' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_client
-- ----------------------------
INSERT INTO `sys_client` VALUES (1, 'e5cd7e4891bf95d1d19206ce24a7b32e', 'pc', 'pc123', 'password,social', 'pc', 1800, 604800, '0', '0', 103, 1, '2024-07-31 18:46:52', 1, '2024-07-31 18:46:52');
INSERT INTO `sys_client` VALUES (2, '428a8310cd442757ae699df5d894f051', 'app', 'app123', 'password,sms,social', 'android', 1800, 604800, '0', '0', 103, 1, '2024-07-31 18:46:52', 1, '2024-07-31 18:46:52');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
                               `id` bigint NOT NULL COMMENT '参数主键',
                               `config_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '参数名称',
                               `config_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '参数键名',
                               `config_value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '参数键值',
                               `config_type` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
                               `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
                               `tenant_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '租户Id',
                               `create_dept` bigint NULL DEFAULT NULL COMMENT '创建部门',
                               `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
                               `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                               `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
                               `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '参数配置' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_config` VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', '初始化密码 123456', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_config` VALUES (3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', '深色主题theme-dark，浅色主题theme-light', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_config` VALUES (5, '账号自助-是否开启用户注册功能', 'sys.account.registerUser', 'false', 'Y', '是否开启注册用户功能（true开启，false关闭）', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_config` VALUES (11, 'OSS预览列表资源开关', 'sys.oss.previewListResource', 'true', 'Y', 'true:开启, false:关闭', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
                             `id` bigint NOT NULL COMMENT '主键',
                             `parent_id` bigint NULL DEFAULT NULL COMMENT '父部门id',
                             `ancestors` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '祖级列表',
                             `dept_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '部门名称',
                             `order_num` int NULL DEFAULT NULL COMMENT '显示顺序',
                             `leader` bigint NULL DEFAULT NULL COMMENT '负责人',
                             `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '联系电话',
                             `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '邮箱',
                             `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
                             `del_flag` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
                             `tenant_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '租户Id',
                             `create_dept` bigint NULL DEFAULT NULL COMMENT '创建部门',
                             `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
                             `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                             `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
                             `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '部门' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (100, 0, '0', 'XXX科技', 0, NULL, '15888888888', 'xxx@qq.com', '0', '0', '000000', 103, 1, '2024-07-31 18:46:50', NULL, NULL);
INSERT INTO `sys_dept` VALUES (101, 100, '0,100', '深圳总公司', 1, NULL, '15888888888', 'xxx@qq.com', '0', '0', '000000', 103, 1, '2024-07-31 18:46:50', NULL, NULL);
INSERT INTO `sys_dept` VALUES (102, 100, '0,100', '长沙分公司', 2, NULL, '15888888888', 'xxx@qq.com', '0', '0', '000000', 103, 1, '2024-07-31 18:46:50', NULL, NULL);
INSERT INTO `sys_dept` VALUES (103, 101, '0,100,101', '研发部门', 1, 1, '15888888888', 'xxx@qq.com', '0', '0', '000000', 103, 1, '2024-07-31 18:46:50', NULL, NULL);
INSERT INTO `sys_dept` VALUES (104, 101, '0,100,101', '市场部门', 2, NULL, '15888888888', 'xxx@qq.com', '0', '0', '000000', 103, 1, '2024-07-31 18:46:50', NULL, NULL);
INSERT INTO `sys_dept` VALUES (105, 101, '0,100,101', '测试部门', 3, NULL, '15888888888', 'xxx@qq.com', '0', '0', '000000', 103, 1, '2024-07-31 18:46:50', NULL, NULL);
INSERT INTO `sys_dept` VALUES (106, 101, '0,100,101', '财务部门', 4, NULL, '15888888888', 'xxx@qq.com', '0', '0', '000000', 103, 1, '2024-07-31 18:46:50', NULL, NULL);
INSERT INTO `sys_dept` VALUES (107, 101, '0,100,101', '运维部门', 5, NULL, '15888888888', 'xxx@qq.com', '0', '0', '000000', 103, 1, '2024-07-31 18:46:50', NULL, NULL);
INSERT INTO `sys_dept` VALUES (108, 102, '0,100,102', '市场部门', 1, NULL, '15888888888', 'xxx@qq.com', '0', '0', '000000', 103, 1, '2024-07-31 18:46:50', NULL, NULL);
INSERT INTO `sys_dept` VALUES (109, 102, '0,100,102', '财务部门', 2, NULL, '15888888888', 'xxx@qq.com', '0', '0', '000000', 103, 1, '2024-07-31 18:46:50', NULL, NULL);

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
                                  `id` bigint NOT NULL COMMENT '字典ID',
                                  `parent_id` bigint NULL DEFAULT 0 COMMENT '父级ID',
                                  `dict_sort` bigint NULL DEFAULT 0 COMMENT '字典排序',
                                  `dict_label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '字典标签',
                                  `dict_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '字典键值',
                                  `dict_type_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '字典类型',
                                  `css_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
                                  `list_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '表格回显样式',
                                  `is_default` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
                                  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
                                  `tenant_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '租户Id',
                                  `create_dept` bigint NULL DEFAULT NULL COMMENT '创建部门',
                                  `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
                                  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                  `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
                                  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '字典数据' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 0, 1, '男', '0', 'sys_user_sex', '', '', '0', '性别男', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (2, 0, 2, '女', '1', 'sys_user_sex', '', '', '0', '性别女', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (3, 0, 3, '未知', '2', 'sys_user_sex', '', '', '0', '性别未知', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (4, 0, 1, '显示', '0', 'sys_show_hide', '', 'primary', '0', '显示菜单', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (5, 0, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', '0', '隐藏菜单', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (6, 0, 1, '正常', '0', 'sys_normal_disable', '', 'primary', '0', '正常状态', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (7, 0, 2, '停用', '1', 'sys_normal_disable', '', 'danger', '0', '停用状态', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (12, 0, 1, '是', 'Y', 'sys_yes_no', '', 'primary', '0', '系统默认是', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (13, 0, 2, '否', 'N', 'sys_yes_no', '', 'danger', '0', '系统默认否', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (14, 0, 1, '通知', '1', 'sys_notice_type', '', 'warning', '0', '通知', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (15, 0, 2, '公告', '2', 'sys_notice_type', '', 'success', '0', '公告', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (16, 0, 1, '正常', '0', 'sys_notice_status', '', 'primary', '0', '正常状态', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (17, 0, 2, '关闭', '1', 'sys_notice_status', '', 'danger', '0', '关闭状态', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (18, 0, 1, '新增', '1', 'sys_oper_type', '', 'info', '0', '新增操作', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (19, 0, 2, '修改', '2', 'sys_oper_type', '', 'info', '0', '修改操作', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (20, 0, 3, '删除', '3', 'sys_oper_type', '', 'danger', '0', '删除操作', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (21, 0, 4, '授权', '4', 'sys_oper_type', '', 'primary', '0', '授权操作', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (22, 0, 5, '导出', '5', 'sys_oper_type', '', 'warning', '0', '导出操作', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (23, 0, 6, '导入', '6', 'sys_oper_type', '', 'warning', '0', '导入操作', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (24, 0, 7, '强退', '7', 'sys_oper_type', '', 'danger', '0', '强退操作', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (25, 0, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', '0', '生成操作', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (26, 0, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', '0', '清空操作', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (27, 0, 1, '成功', '0', 'sys_common_status', '', 'primary', '0', '正常状态', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (28, 0, 2, '失败', '1', 'sys_common_status', '', 'danger', '0', '停用状态', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (29, 0, 99, '其他', '0', 'sys_oper_type', '', 'info', '0', '其他操作', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (30, 0, 0, '密码认证', 'password', 'sys_grant_type', 'el-check-tag', 'default', '0', '密码认证', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (31, 0, 0, '短信认证', 'sms', 'sys_grant_type', 'el-check-tag', 'default', '0', '短信认证', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (32, 0, 0, '邮件认证', 'email', 'sys_grant_type', 'el-check-tag', 'default', '0', '邮件认证', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (33, 0, 0, '小程序认证', 'xcx', 'sys_grant_type', 'el-check-tag', 'default', '0', '小程序认证', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (34, 0, 0, '三方登录认证', 'social', 'sys_grant_type', 'el-check-tag', 'default', '0', '三方登录认证', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (35, 0, 0, 'PC', 'pc', 'sys_device_type', '', 'default', '0', 'PC', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (36, 0, 0, '安卓', 'android', 'sys_device_type', '', 'default', '0', '安卓', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (37, 0, 0, 'iOS', 'ios', 'sys_device_type', '', 'default', '0', 'iOS', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (38, 0, 0, '小程序', 'xcx', 'sys_device_type', '', 'default', '0', '小程序', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
                                  `dict_id` bigint NOT NULL COMMENT '字典主键',
                                  `dict_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '字典key',
                                  `dict_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '字典名称',
                                  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT 'L' COMMENT '字典类型 L 列表 T 树',
                                  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
                                  `tenant_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '租户Id',
                                  `create_dept` bigint NULL DEFAULT NULL COMMENT '创建部门',
                                  `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
                                  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                  `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
                                  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                  PRIMARY KEY (`dict_id`) USING BTREE,
                                  UNIQUE INDEX `tenant_id`(`tenant_id` ASC, `dict_key` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '字典类型' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, 'sys_user_sex', '用户性别', 'L', '用户性别列表', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (2, 'sys_show_hide', '菜单状态', 'L', '菜单状态列表', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (3, 'sys_normal_disable', '系统开关', 'L', '系统开关列表', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (6, 'sys_yes_no', '系统是否', 'L', '系统是否列表', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (7, 'sys_notice_type', '通知类型', 'L', '通知类型列表', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (8, 'sys_notice_status', '通知状态', 'L', '通知状态列表', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (9, 'sys_oper_type', '操作类型', 'L', '操作类型列表', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (10, 'sys_common_status', '系统状态', 'L', '登录状态列表', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (11, 'sys_grant_type', '授权类型', 'L', '认证授权类型', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (12, 'sys_device_type', '设备类型', 'L', '客户端设备类型', '000000', 103, 1, '2024-07-31 18:46:52', NULL, NULL);

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`  (
                                   `info_id` bigint NOT NULL COMMENT '访问ID',
                                   `tenant_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '000000' COMMENT '租户编号',
                                   `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '用户账号',
                                   `client_key` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '客户端',
                                   `device_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '设备类型',
                                   `ipaddr` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '登录IP地址',
                                   `login_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '登录地点',
                                   `browser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '浏览器类型',
                                   `os` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '操作系统',
                                   `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
                                   `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '提示消息',
                                   `login_time` datetime NULL DEFAULT NULL COMMENT '访问时间',
                                   PRIMARY KEY (`info_id`) USING BTREE,
                                   INDEX `idx_sys_logininfor_s`(`status` ASC) USING BTREE,
                                   INDEX `idx_sys_logininfor_lt`(`login_time` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '系统访问记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
                             `id` bigint NOT NULL COMMENT '菜单ID',
                             `parent_id` bigint NULL DEFAULT 0 COMMENT '父菜单ID',
                             `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '菜单名称',
                             `order_num` int NULL DEFAULT 0 COMMENT '显示顺序',
                             `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '路由地址',
                             `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '组件路径',
                             `query_param` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '路由参数',
                             `is_frame` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '1' COMMENT '是否为外链（0是 1否）',
                             `is_cache` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '是否缓存（0缓存 1不缓存）',
                             `menu_type` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '菜单类型（M目录 C菜单 F按钮）',
                             `visible` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '显示状态（0显示 1隐藏）',
                             `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
                             `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '权限标识',
                             `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '#' COMMENT '菜单图标',
                             `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
                             `create_dept` bigint NULL DEFAULT NULL COMMENT '创建部门',
                             `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
                             `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                             `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
                             `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '菜单权限' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '系统管理', 1, 'system', NULL, '', '1', '0', 'M', '0', '0', '', 'eos-icons:system-group', '系统管理目录', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (2, 0, '系统监控', 3, 'monitor', '', '', '1', '0', 'M', '0', '1', '', 'solar:monitor-camera-outline', '系统监控目录', 103, 1, '2025-05-27 13:19:44', 1, '2025-06-06 15:26:31');
INSERT INTO `sys_menu` VALUES (3, 0, '系统工具', 4, 'tool', NULL, '', '1', '0', 'M', '0', '0', '', 'ant-design:tool-outlined', '系统工具目录', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (4, 0, '官网', 5, 'https://doc.jimuqu.com', NULL, '', '0', '0', 'M', '0', '0', '', 'flat-color-icons:plus', 'RuoYi-Vue-Plus官网地址', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (6, 0, '租户管理', 2, 'tenant', NULL, '', '1', '0', 'M', '0', '1', '', 'ph:users-light', '租户管理目录', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (100, 1, '用户管理', 1, 'user', 'system/user/index', '', '1', '0', 'C', '0', '0', 'system:user:list', 'ant-design:user-outlined', '用户管理菜单', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (101, 1, '角色管理', 2, 'role', 'system/role/index', '', '1', '0', 'C', '0', '0', 'system:role:list', 'eos-icons:role-binding-outlined', '角色管理菜单', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (102, 1, '菜单管理', 3, 'menu', 'system/menu/index', '', '1', '0', 'C', '0', '0', 'system:menu:list', 'ic:sharp-menu', '菜单管理菜单', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (103, 1, '部门管理', 4, 'dept', 'system/dept/index', '', '1', '0', 'C', '0', '0', 'system:dept:list', 'mingcute:department-line', '部门管理菜单', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (104, 1, '岗位管理', 5, 'post', 'system/post/index', '', '1', '0', 'C', '0', '0', 'system:post:list', 'icon-park-outline:appointment', '岗位管理菜单', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (105, 1, '字典管理', 6, 'dict', 'system/dict/index', '', '1', '0', 'C', '0', '0', 'system:dict:list', 'fluent-mdl2:dictionary', '字典管理菜单', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (106, 1, '参数设置', 7, 'config', 'system/config/index', '', '1', '0', 'C', '0', '0', 'system:config:list', 'ant-design:setting-outlined', '参数设置菜单', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (107, 1, '通知公告', 8, 'notice', 'system/notice/index', '', '1', '0', 'C', '0', '1', 'system:notice:list', 'fe:notice-push', '通知公告菜单', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (108, 1, '日志管理', 9, 'log', '', '', '1', '0', 'M', '0', '1', '', 'material-symbols:logo-dev-outline', '日志管理菜单', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (109, 2, '在线用户', 1, 'online', 'monitor/online/index', '', '1', '0', 'C', '0', '1', 'monitor:online:list', 'material-symbols:generating-tokens-outline', '在线用户菜单', 103, 1, '2025-05-27 13:19:44', 1, '2025-06-06 15:25:17');
INSERT INTO `sys_menu` VALUES (115, 3, '代码生成', 2, 'gen', 'tool/gen-code/index', '', '1', '0', 'C', '0', '0', 'tool:gen:list', 'tabler:code', '代码生成菜单', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (116, 3, '代码模板', 3, 'genTemplate', 'tool/gen-template/index', '', '1', '0', 'C', '0', '0', 'tool:gen:list', 'tabler:template', '代码生成菜单', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (118, 1, '文件管理', 10, 'oss', 'system/oss/index', '', '1', '0', 'C', '0', '1', 'system:oss:list', 'solar:folder-with-files-outline', '文件管理菜单', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (121, 6, '租户管理', 1, 'tenantManager', 'system/tenant/index', '', '1', '0', 'C', '0', '0', 'system:tenant:list', 'ph:user-list', '租户管理菜单', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (122, 6, '租户套餐管理', 2, 'tenantPackage', 'system/tenantPackage/index', '', '1', '0', 'C', '0', '0', 'system:tenantPackage:list', 'bx:package', '租户套餐管理菜单', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (123, 1, '客户端管理', 11, 'client', 'system/client/index', '', '1', '0', 'C', '0', '0', 'system:client:list', 'solar:monitor-smartphone-outline', '客户端管理菜单', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (500, 108, '操作日志', 1, 'operlog', 'monitor/operlog/index', '', '1', '0', 'C', '0', '0', 'monitor:operlog:list', 'arcticons:one-hand-operation', '操作日志菜单', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (501, 108, '登录日志', 2, 'logininfor', 'monitor/logininfor/index', '', '1', '0', 'C', '0', '0', 'monitor:logininfor:list', 'streamline:interface-login-dial-pad-finger-password-dial-pad-dot-finger', '登录日志菜单', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1001, 100, '用户查询', 1, '', '', '', '1', '0', 'F', '0', '0', 'system:user:query', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1002, 100, '用户新增', 2, '', '', '', '1', '0', 'F', '0', '0', 'system:user:add', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1003, 100, '用户修改', 3, '', '', '', '1', '0', 'F', '0', '0', 'system:user:edit', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1004, 100, '用户删除', 4, '', '', '', '1', '0', 'F', '0', '0', 'system:user:remove', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1005, 100, '用户导出', 5, '', '', '', '1', '0', 'F', '0', '0', 'system:user:export', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1006, 100, '用户导入', 6, '', '', '', '1', '0', 'F', '0', '0', 'system:user:import', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1007, 100, '重置密码', 7, '', '', '', '1', '0', 'F', '0', '0', 'system:user:resetPwd', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1008, 101, '角色查询', 1, '', '', '', '1', '0', 'F', '0', '0', 'system:role:query', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1009, 101, '角色新增', 2, '', '', '', '1', '0', 'F', '0', '0', 'system:role:add', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1010, 101, '角色修改', 3, '', '', '', '1', '0', 'F', '0', '0', 'system:role:edit', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1011, 101, '角色删除', 4, '', '', '', '1', '0', 'F', '0', '0', 'system:role:remove', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1012, 101, '角色导出', 5, '', '', '', '1', '0', 'F', '0', '0', 'system:role:export', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1013, 102, '菜单查询', 1, '', '', '', '1', '0', 'F', '0', '0', 'system:menu:query', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1014, 102, '菜单新增', 2, '', '', '', '1', '0', 'F', '0', '0', 'system:menu:add', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1015, 102, '菜单修改', 3, '', '', '', '1', '0', 'F', '0', '0', 'system:menu:edit', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1016, 102, '菜单删除', 4, '', '', '', '1', '0', 'F', '0', '0', 'system:menu:remove', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1017, 103, '部门查询', 1, '', '', '', '1', '0', 'F', '0', '0', 'system:dept:query', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1018, 103, '部门新增', 2, '', '', '', '1', '0', 'F', '0', '0', 'system:dept:add', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1019, 103, '部门修改', 3, '', '', '', '1', '0', 'F', '0', '0', 'system:dept:edit', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1020, 103, '部门删除', 4, '', '', '', '1', '0', 'F', '0', '0', 'system:dept:remove', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1021, 104, '岗位查询', 1, '', '', '', '1', '0', 'F', '0', '0', 'system:post:query', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1022, 104, '岗位新增', 2, '', '', '', '1', '0', 'F', '0', '0', 'system:post:add', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1023, 104, '岗位修改', 3, '', '', '', '1', '0', 'F', '0', '0', 'system:post:edit', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1024, 104, '岗位删除', 4, '', '', '', '1', '0', 'F', '0', '0', 'system:post:remove', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1025, 104, '岗位导出', 5, '', '', '', '1', '0', 'F', '0', '0', 'system:post:export', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1026, 105, '字典查询', 1, '#', '', '', '1', '0', 'F', '0', '0', 'system:dict:query', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1027, 105, '字典新增', 2, '#', '', '', '1', '0', 'F', '0', '0', 'system:dict:add', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1028, 105, '字典修改', 3, '#', '', '', '1', '0', 'F', '0', '0', 'system:dict:edit', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1029, 105, '字典删除', 4, '#', '', '', '1', '0', 'F', '0', '0', 'system:dict:remove', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1030, 105, '字典导出', 5, '#', '', '', '1', '0', 'F', '0', '0', 'system:dict:export', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1031, 106, '参数查询', 1, '#', '', '', '1', '0', 'F', '0', '0', 'system:config:query', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1032, 106, '参数新增', 2, '#', '', '', '1', '0', 'F', '0', '0', 'system:config:add', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1033, 106, '参数修改', 3, '#', '', '', '1', '0', 'F', '0', '0', 'system:config:edit', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1034, 106, '参数删除', 4, '#', '', '', '1', '0', 'F', '0', '0', 'system:config:remove', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1035, 106, '参数导出', 5, '#', '', '', '1', '0', 'F', '0', '0', 'system:config:export', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1036, 107, '公告查询', 1, '#', '', '', '1', '0', 'F', '0', '0', 'system:notice:query', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1037, 107, '公告新增', 2, '#', '', '', '1', '0', 'F', '0', '0', 'system:notice:add', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1038, 107, '公告修改', 3, '#', '', '', '1', '0', 'F', '0', '0', 'system:notice:edit', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1039, 107, '公告删除', 4, '#', '', '', '1', '0', 'F', '0', '0', 'system:notice:remove', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1040, 500, '操作查询', 1, '#', '', '', '1', '0', 'F', '0', '0', 'monitor:operlog:query', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1041, 500, '操作删除', 2, '#', '', '', '1', '0', 'F', '0', '0', 'monitor:operlog:remove', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1042, 500, '日志导出', 4, '#', '', '', '1', '0', 'F', '0', '0', 'monitor:operlog:export', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1043, 501, '登录查询', 1, '#', '', '', '1', '0', 'F', '0', '0', 'monitor:logininfor:query', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1044, 501, '登录删除', 2, '#', '', '', '1', '0', 'F', '0', '0', 'monitor:logininfor:remove', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1045, 501, '日志导出', 3, '#', '', '', '1', '0', 'F', '0', '0', 'monitor:logininfor:export', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1046, 109, '在线查询', 1, '#', '', '', '1', '0', 'F', '0', '0', 'monitor:online:query', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1047, 109, '批量强退', 2, '#', '', '', '1', '0', 'F', '0', '0', 'monitor:online:batchLogout', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1048, 109, '单条强退', 3, '#', '', '', '1', '0', 'F', '0', '0', 'monitor:online:forceLogout', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1050, 501, '账户解锁', 4, '#', '', '', '1', '0', 'F', '0', '0', 'monitor:logininfor:unlock', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1055, 115, '生成查询', 1, '#', '', '', '1', '0', 'F', '0', '0', 'tool:gen:query', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1056, 115, '生成修改', 2, '#', '', '', '1', '0', 'F', '0', '0', 'tool:gen:edit', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1057, 115, '生成删除', 3, '#', '', '', '1', '0', 'F', '0', '0', 'tool:gen:remove', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1058, 115, '导入代码', 2, '#', '', '', '1', '0', 'F', '0', '0', 'tool:gen:import', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1059, 115, '预览代码', 4, '#', '', '', '1', '0', 'F', '0', '0', 'tool:gen:preview', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1060, 115, '生成代码', 5, '#', '', '', '1', '0', 'F', '0', '0', 'tool:gen:code', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1061, 123, '客户端管理查询', 1, '#', '', '', '1', '0', 'F', '0', '0', 'system:client:query', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1062, 123, '客户端管理新增', 2, '#', '', '', '1', '0', 'F', '0', '0', 'system:client:add', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1063, 123, '客户端管理修改', 3, '#', '', '', '1', '0', 'F', '0', '0', 'system:client:edit', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1064, 123, '客户端管理删除', 4, '#', '', '', '1', '0', 'F', '0', '0', 'system:client:remove', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1065, 123, '客户端管理导出', 5, '#', '', '', '1', '0', 'F', '0', '0', 'system:client:export', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1600, 118, '文件查询', 1, '#', '', '', '1', '0', 'F', '0', '0', 'system:oss:query', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1601, 118, '文件上传', 2, '#', '', '', '1', '0', 'F', '0', '0', 'system:oss:upload', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1602, 118, '文件下载', 3, '#', '', '', '1', '0', 'F', '0', '0', 'system:oss:download', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1603, 118, '文件删除', 4, '#', '', '', '1', '0', 'F', '0', '0', 'system:oss:remove', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1606, 121, '租户查询', 1, '#', '', '', '1', '0', 'F', '0', '0', 'system:tenant:query', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1607, 121, '租户新增', 2, '#', '', '', '1', '0', 'F', '0', '0', 'system:tenant:add', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1608, 121, '租户修改', 3, '#', '', '', '1', '0', 'F', '0', '0', 'system:tenant:edit', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1609, 121, '租户删除', 4, '#', '', '', '1', '0', 'F', '0', '0', 'system:tenant:remove', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1610, 121, '租户导出', 5, '#', '', '', '1', '0', 'F', '0', '0', 'system:tenant:export', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1611, 122, '租户套餐查询', 1, '#', '', '', '1', '0', 'F', '0', '0', 'system:tenantPackage:query', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1612, 122, '租户套餐新增', 2, '#', '', '', '1', '0', 'F', '0', '0', 'system:tenantPackage:add', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1613, 122, '租户套餐修改', 3, '#', '', '', '1', '0', 'F', '0', '0', 'system:tenantPackage:edit', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1614, 122, '租户套餐删除', 4, '#', '', '', '1', '0', 'F', '0', '0', 'system:tenantPackage:remove', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1615, 122, '租户套餐导出', 5, '#', '', '', '1', '0', 'F', '0', '0', 'system:tenantPackage:export', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1620, 118, '配置列表', 5, '#', '', '', '1', '0', 'F', '0', '0', 'system:ossConfig:list', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1621, 118, '配置添加', 6, '#', '', '', '1', '0', 'F', '0', '0', 'system:ossConfig:add', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1622, 118, '配置编辑', 6, '#', '', '', '1', '0', 'F', '0', '0', 'system:ossConfig:edit', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1623, 118, '配置删除', 6, '#', '', '', '1', '0', 'F', '0', '0', 'system:ossConfig:remove', '#', '', 103, 1, '2025-05-27 13:19:44', NULL, NULL);

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
                               `notice_id` bigint NOT NULL COMMENT '公告ID',
                               `tenant_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '000000' COMMENT '租户编号',
                               `notice_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '公告标题',
                               `notice_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '公告类型（1通知 2公告）',
                               `notice_content` longblob NULL COMMENT '公告内容',
                               `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
                               `create_dept` bigint NULL DEFAULT NULL COMMENT '创建部门',
                               `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
                               `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                               `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
                               `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                               `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
                               PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '通知公告表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (1, '000000', '温馨提醒：2018-07-01 新版本发布啦', '2', 0xE696B0E78988E69CACE58685E5AEB9, '0', 103, 1, '2024-07-31 18:46:52', NULL, NULL, '管理员');
INSERT INTO `sys_notice` VALUES (2, '000000', '维护通知：2018-07-01 系统凌晨维护', '1', 0xE7BBB4E68AA4E58685E5AEB9, '0', 103, 1, '2024-07-31 18:46:52', NULL, NULL, '管理员');

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
                                 `oper_id` bigint NOT NULL COMMENT '日志主键',
                                 `tenant_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '000000' COMMENT '租户编号',
                                 `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '模块标题',
                                 `business_type` int NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
                                 `method` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '方法名称',
                                 `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '请求方式',
                                 `operator_type` int NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
                                 `oper_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '操作人员',
                                 `dept_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '部门名称',
                                 `oper_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '请求URL',
                                 `oper_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '主机地址',
                                 `oper_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '操作地点',
                                 `oper_param` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '请求参数',
                                 `json_result` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '返回参数',
                                 `status` int NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
                                 `error_msg` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '错误消息',
                                 `oper_time` datetime NULL DEFAULT NULL COMMENT '操作时间',
                                 `cost_time` bigint NULL DEFAULT 0 COMMENT '消耗时间',
                                 PRIMARY KEY (`oper_id`) USING BTREE,
                                 INDEX `idx_sys_oper_log_bt`(`business_type` ASC) USING BTREE,
                                 INDEX `idx_sys_oper_log_s`(`status` ASC) USING BTREE,
                                 INDEX `idx_sys_oper_log_ot`(`oper_time` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '操作日志记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss`  (
                            `oss_id` bigint NOT NULL COMMENT '对象存储主键',
                            `tenant_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '000000' COMMENT '租户编号',
                            `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '文件名',
                            `original_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '原名',
                            `file_suffix` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '文件后缀名',
                            `url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT 'URL地址',
                            `create_dept` bigint NULL DEFAULT NULL COMMENT '创建部门',
                            `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                            `create_by` bigint NULL DEFAULT NULL COMMENT '上传人',
                            `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                            `update_by` bigint NULL DEFAULT NULL COMMENT '更新人',
                            `service` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT 'minio' COMMENT '服务商',
                            PRIMARY KEY (`oss_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = 'OSS对象存储表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_oss
-- ----------------------------

-- ----------------------------
-- Table structure for sys_oss_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss_config`;
CREATE TABLE `sys_oss_config`  (
                                   `oss_config_id` bigint NOT NULL COMMENT '主建',
                                   `tenant_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '000000' COMMENT '租户编号',
                                   `config_key` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '配置key',
                                   `access_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT 'accessKey',
                                   `secret_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '秘钥',
                                   `bucket_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '桶名称',
                                   `prefix` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '前缀',
                                   `endpoint` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '访问站点',
                                   `domain` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '自定义域名',
                                   `is_https` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT 'N' COMMENT '是否https（Y=是,N=否）',
                                   `region` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '域',
                                   `access_policy` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '1' COMMENT '桶权限类型(0=private 1=public 2=custom)',
                                   `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '1' COMMENT '是否默认（0=是,1=否）',
                                   `ext1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '扩展字段',
                                   `create_dept` bigint NULL DEFAULT NULL COMMENT '创建部门',
                                   `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
                                   `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                   `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
                                   `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                   `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
                                   PRIMARY KEY (`oss_config_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '对象存储配置表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_oss_config
-- ----------------------------
INSERT INTO `sys_oss_config` VALUES (1, '000000', 'minio', 'ruoyi', 'ruoyi123', 'ruoyi', '', '127.0.0.1:9000', '', 'N', '', '1', '0', '', 103, 1, '2024-07-31 18:46:52', 1, '2024-07-31 18:46:52', NULL);
INSERT INTO `sys_oss_config` VALUES (2, '000000', 'qiniu', 'XXXXXXXXXXXXXXX', 'XXXXXXXXXXXXXXX', 'ruoyi', '', 's3-cn-north-1.qiniucs.com', '', 'N', '', '1', '1', '', 103, 1, '2024-07-31 18:46:52', 1, '2024-07-31 18:46:52', NULL);
INSERT INTO `sys_oss_config` VALUES (3, '000000', 'aliyun', 'XXXXXXXXXXXXXXX', 'XXXXXXXXXXXXXXX', 'ruoyi', '', 'oss-cn-beijing.aliyuncs.com', '', 'N', '', '1', '1', '', 103, 1, '2024-07-31 18:46:52', 1, '2024-07-31 18:46:52', NULL);
INSERT INTO `sys_oss_config` VALUES (4, '000000', 'qcloud', 'XXXXXXXXXXXXXXX', 'XXXXXXXXXXXXXXX', 'ruoyi-1250000000', '', 'cos.ap-beijing.myqcloud.com', '', 'N', 'ap-beijing', '1', '1', '', 103, 1, '2024-07-31 18:46:52', 1, '2024-07-31 18:46:52', NULL);
INSERT INTO `sys_oss_config` VALUES (5, '000000', 'image', 'ruoyi', 'ruoyi123', 'ruoyi', 'image', '127.0.0.1:9000', '', 'N', '', '1', '1', '', 103, 1, '2024-07-31 18:46:52', 1, '2024-07-31 18:46:52', NULL);

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
                             `post_id` bigint NOT NULL COMMENT '岗位ID',
                             `dept_id` bigint NULL DEFAULT NULL COMMENT '部门id',
                             `post_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '岗位编码',
                             `post_category` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '岗位类别编码',
                             `post_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '岗位名称',
                             `post_sort` bigint NULL DEFAULT NULL COMMENT '显示顺序',
                             `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '状态（0正常 1停用）',
                             `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
                             `tenant_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '租户Id',
                             `create_dept` bigint NULL DEFAULT NULL COMMENT '创建部门',
                             `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
                             `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                             `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
                             `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                             PRIMARY KEY (`post_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '岗位信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES (1, 103, 'ceo', NULL, '董事长', 1, '0', '', '000000', 103, 1, '2025-06-04 17:22:27', NULL, NULL);
INSERT INTO `sys_post` VALUES (2, 100, 'se', NULL, '项目经理', 2, '0', '', '000000', 103, 1, '2025-06-04 17:22:27', NULL, NULL);
INSERT INTO `sys_post` VALUES (3, 100, 'hr', NULL, '人力资源', 3, '0', '', '000000', 103, 1, '2025-06-04 17:22:27', NULL, NULL);
INSERT INTO `sys_post` VALUES (4, 100, 'user', NULL, '普通员工', 4, '0', '', '000000', 103, 1, '2025-06-04 17:22:27', NULL, NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
                             `id` bigint NOT NULL COMMENT '角色ID',
                             `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '角色名称',
                             `role_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '角色权限字符串',
                             `role_sort` int NULL DEFAULT NULL COMMENT '显示顺序',
                             `data_scope` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限 5：仅本人数据权限 6：部门及以下或本人数据权限）',
                             `menu_check_strictly` bit(1) NULL DEFAULT b'1' COMMENT '菜单树选择项是否关联显示',
                             `dept_check_strictly` bit(1) NULL DEFAULT b'1' COMMENT '部门树选择项是否关联显示',
                             `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '角色状态（0正常 1停用）',
                             `del_flag` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
                             `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
                             `tenant_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '租户Id',
                             `create_dept` bigint NULL DEFAULT NULL COMMENT '创建部门',
                             `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
                             `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                             `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
                             `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '角色信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'superadmin', 1, '1', b'1', b'1', '0', '0', '超级管理员', '000000', 103, 1, '2025-06-05 17:18:06', NULL, NULL);
INSERT INTO `sys_role` VALUES (3, '本部门及以下', 'test1', 3, '4', b'1', b'1', '0', '0', '', '000000', 103, 1, '2025-06-05 17:18:06', NULL, NULL);
INSERT INTO `sys_role` VALUES (4, '仅本人', 'test2', 4, '5', b'1', b'1', '0', '0', '', '000000', 103, 1, '2025-06-05 17:18:06', NULL, NULL);

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
                                  `role_id` bigint NULL DEFAULT NULL COMMENT '角色ID',
                                  `dept_id` bigint NULL DEFAULT NULL COMMENT '部门ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '角色和部门关联 sys_role_dept' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES (2, 100);
INSERT INTO `sys_role_dept` VALUES (2, 101);
INSERT INTO `sys_role_dept` VALUES (2, 105);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
                                  `role_id` bigint NULL DEFAULT NULL COMMENT '角色ID',
                                  `menu_id` bigint NULL DEFAULT NULL COMMENT '菜单ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '角色和菜单关联 sys_role_menu' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2);
INSERT INTO `sys_role_menu` VALUES (2, 3);
INSERT INTO `sys_role_menu` VALUES (2, 4);
INSERT INTO `sys_role_menu` VALUES (2, 100);
INSERT INTO `sys_role_menu` VALUES (2, 101);
INSERT INTO `sys_role_menu` VALUES (2, 102);
INSERT INTO `sys_role_menu` VALUES (2, 103);
INSERT INTO `sys_role_menu` VALUES (2, 104);
INSERT INTO `sys_role_menu` VALUES (2, 105);
INSERT INTO `sys_role_menu` VALUES (2, 106);
INSERT INTO `sys_role_menu` VALUES (2, 107);
INSERT INTO `sys_role_menu` VALUES (2, 108);
INSERT INTO `sys_role_menu` VALUES (2, 109);
INSERT INTO `sys_role_menu` VALUES (2, 110);
INSERT INTO `sys_role_menu` VALUES (2, 111);
INSERT INTO `sys_role_menu` VALUES (2, 112);
INSERT INTO `sys_role_menu` VALUES (2, 114);
INSERT INTO `sys_role_menu` VALUES (2, 115);
INSERT INTO `sys_role_menu` VALUES (2, 116);
INSERT INTO `sys_role_menu` VALUES (2, 500);
INSERT INTO `sys_role_menu` VALUES (2, 501);
INSERT INTO `sys_role_menu` VALUES (2, 1000);
INSERT INTO `sys_role_menu` VALUES (2, 1001);
INSERT INTO `sys_role_menu` VALUES (2, 1002);
INSERT INTO `sys_role_menu` VALUES (2, 1003);
INSERT INTO `sys_role_menu` VALUES (2, 1004);
INSERT INTO `sys_role_menu` VALUES (2, 1005);
INSERT INTO `sys_role_menu` VALUES (2, 1006);
INSERT INTO `sys_role_menu` VALUES (2, 1007);
INSERT INTO `sys_role_menu` VALUES (2, 1008);
INSERT INTO `sys_role_menu` VALUES (2, 1009);
INSERT INTO `sys_role_menu` VALUES (2, 1010);
INSERT INTO `sys_role_menu` VALUES (2, 1011);
INSERT INTO `sys_role_menu` VALUES (2, 1012);
INSERT INTO `sys_role_menu` VALUES (2, 1013);
INSERT INTO `sys_role_menu` VALUES (2, 1014);
INSERT INTO `sys_role_menu` VALUES (2, 1015);
INSERT INTO `sys_role_menu` VALUES (2, 1016);
INSERT INTO `sys_role_menu` VALUES (2, 1017);
INSERT INTO `sys_role_menu` VALUES (2, 1018);
INSERT INTO `sys_role_menu` VALUES (2, 1019);
INSERT INTO `sys_role_menu` VALUES (2, 1020);
INSERT INTO `sys_role_menu` VALUES (2, 1021);
INSERT INTO `sys_role_menu` VALUES (2, 1022);
INSERT INTO `sys_role_menu` VALUES (2, 1023);
INSERT INTO `sys_role_menu` VALUES (2, 1024);
INSERT INTO `sys_role_menu` VALUES (2, 1025);
INSERT INTO `sys_role_menu` VALUES (2, 1026);
INSERT INTO `sys_role_menu` VALUES (2, 1027);
INSERT INTO `sys_role_menu` VALUES (2, 1028);
INSERT INTO `sys_role_menu` VALUES (2, 1029);
INSERT INTO `sys_role_menu` VALUES (2, 1030);
INSERT INTO `sys_role_menu` VALUES (2, 1031);
INSERT INTO `sys_role_menu` VALUES (2, 1032);
INSERT INTO `sys_role_menu` VALUES (2, 1033);
INSERT INTO `sys_role_menu` VALUES (2, 1034);
INSERT INTO `sys_role_menu` VALUES (2, 1035);
INSERT INTO `sys_role_menu` VALUES (2, 1036);
INSERT INTO `sys_role_menu` VALUES (2, 1037);
INSERT INTO `sys_role_menu` VALUES (2, 1038);
INSERT INTO `sys_role_menu` VALUES (2, 1039);
INSERT INTO `sys_role_menu` VALUES (2, 1040);
INSERT INTO `sys_role_menu` VALUES (2, 1041);
INSERT INTO `sys_role_menu` VALUES (2, 1042);
INSERT INTO `sys_role_menu` VALUES (2, 1043);
INSERT INTO `sys_role_menu` VALUES (2, 1044);
INSERT INTO `sys_role_menu` VALUES (2, 1045);
INSERT INTO `sys_role_menu` VALUES (2, 1046);
INSERT INTO `sys_role_menu` VALUES (2, 1047);
INSERT INTO `sys_role_menu` VALUES (2, 1048);
INSERT INTO `sys_role_menu` VALUES (2, 1050);
INSERT INTO `sys_role_menu` VALUES (2, 1055);
INSERT INTO `sys_role_menu` VALUES (2, 1056);
INSERT INTO `sys_role_menu` VALUES (2, 1057);
INSERT INTO `sys_role_menu` VALUES (2, 1058);
INSERT INTO `sys_role_menu` VALUES (2, 1059);
INSERT INTO `sys_role_menu` VALUES (2, 1060);
INSERT INTO `sys_role_menu` VALUES (2, 1061);
INSERT INTO `sys_role_menu` VALUES (2, 1062);
INSERT INTO `sys_role_menu` VALUES (2, 1063);
INSERT INTO `sys_role_menu` VALUES (2, 1064);
INSERT INTO `sys_role_menu` VALUES (2, 1065);

-- ----------------------------
-- Table structure for sys_social
-- ----------------------------
DROP TABLE IF EXISTS `sys_social`;
CREATE TABLE `sys_social`  (
                               `id` bigint NOT NULL COMMENT '主键',
                               `user_id` bigint NOT NULL COMMENT '用户ID',
                               `tenant_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '租户id',
                               `auth_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '平台+平台唯一id',
                               `source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户来源',
                               `open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '平台编号唯一id',
                               `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '登录账号',
                               `nick_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '用户昵称',
                               `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '用户邮箱',
                               `avatar` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '头像地址',
                               `access_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户的授权令牌',
                               `expire_in` int NULL DEFAULT NULL COMMENT '用户的授权令牌的有效期，部分平台可能没有',
                               `refresh_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '刷新令牌，部分平台可能没有',
                               `access_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '平台的授权信息，部分平台可能没有',
                               `union_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户的 unionid',
                               `scope` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '授予的权限，部分平台可能没有',
                               `token_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '个别平台的授权信息，部分平台可能没有',
                               `id_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'id token，部分平台可能没有',
                               `mac_algorithm` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '小米平台用户的附带属性，部分平台可能没有',
                               `mac_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '小米平台用户的附带属性，部分平台可能没有',
                               `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户的授权code，部分平台可能没有',
                               `oauth_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'Twitter平台用户的附带属性，部分平台可能没有',
                               `oauth_token_secret` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'Twitter平台用户的附带属性，部分平台可能没有',
                               `create_dept` bigint NULL DEFAULT NULL COMMENT '创建部门',
                               `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
                               `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                               `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
                               `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                               `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '社会化关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_social
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
                             `id` bigint NOT NULL COMMENT '用户ID',
                             `dept_id` bigint NULL DEFAULT NULL COMMENT '部门ID',
                             `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户账号',
                             `nick_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户昵称',
                             `user_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT 'pc_user' COMMENT '用户类型（pc_user系统用户）',
                             `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户邮箱',
                             `phonenumber` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '手机号码',
                             `sex` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
                             `avatar` bigint NULL DEFAULT NULL COMMENT '头像地址',
                             `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '密码',
                             `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
                             `del_flag` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
                             `login_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '最后登录IP',
                             `login_date` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
                             `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
                             `tenant_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '租户Id',
                             `create_dept` bigint NULL DEFAULT NULL COMMENT '创建部门',
                             `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
                             `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                             `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
                             `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 103, 'admin', '勤奋的Jerry', 'pc_user', 'chengliang4810@163.com', '15888888888', '1', NULL, '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2025-06-05 14:51:43', '管理员', '000000', 103, 1, '2025-06-05 14:51:43', NULL, NULL);
INSERT INTO `sys_user` VALUES (3, 108, 'test', '本部门及以下 密码666666', 'pc_user', '', '', '0', NULL, '$2a$10$b8yUzN0C71sbz.PhNOCgJe.Tu1yWC3RNrTyjSQ8p1W0.aaUXUJ.Ne', '0', '0', '127.0.0.1', '2025-06-05 14:51:43', NULL, '000000', 103, 1, '2025-06-05 14:51:43', 3, '2025-06-05 14:51:43');
INSERT INTO `sys_user` VALUES (4, 102, 'test1', '仅本人 密码666666', 'pc_user', '', '', '0', NULL, '$2a$10$b8yUzN0C71sbz.PhNOCgJe.Tu1yWC3RNrTyjSQ8p1W0.aaUXUJ.Ne', '0', '0', '127.0.0.1', '2025-06-05 14:51:43', NULL, '000000', 103, 1, '2025-06-05 14:51:43', 4, '2025-06-05 14:51:43');
INSERT INTO `sys_user` VALUES (205394081231167488, 101, 'cd', '123123', 'pc_user', NULL, NULL, '0', NULL, '$2a$10$Ae.VnFXv6mgVLH2jdBIor.mueeyxDc8iXWwpu6zIPwrzZKJJHBWEq', '0', '0', NULL, NULL, NULL, '000000', 103, 1, '2025-06-05 18:42:45', 1, '2025-06-05 18:42:45');

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`  (
                                  `user_id` bigint NULL DEFAULT NULL COMMENT '用户ID',
                                  `post_id` bigint NULL DEFAULT NULL COMMENT '部门ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户和角色关联 sys_user_role' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES (1, 1);
INSERT INTO `sys_user_post` VALUES (2, 2);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
                                  `user_id` bigint NULL DEFAULT NULL COMMENT '用户ID',
                                  `role_id` bigint NULL DEFAULT NULL COMMENT '角色ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户和角色关联 sys_user_role' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);

-- ----------------------------
-- Table structure for user_test
-- ----------------------------
DROP TABLE IF EXISTS `user_test`;
CREATE TABLE `user_test`  (
                              `user_id` bigint NOT NULL AUTO_INCREMENT,
                              `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
                              PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_test
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;