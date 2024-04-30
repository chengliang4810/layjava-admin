package com.layjava.system.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.layjava.common.mybatis.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * OSS对象存储表
 *
 * @author chengliang4810
 * @since 2024-04-30
 */
@Data
@Accessors(chain = true)
@TableName("sys_oss")
@EqualsAndHashCode(callSuper = true)
public class SysOss extends BaseEntity {

    /**
     * 对象存储主键
     */
    @TableId("oss_id")
    private Long ossId;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 原名
     */
    private String originalName;

    /**
     * 文件后缀名
     */
    private String fileSuffix;

    /**
     * URL地址
     */
    private String url;

    /**
     * 文档大小
     */
    private Long size;

    /**
     * 服务商
     */
    private String service;
}
