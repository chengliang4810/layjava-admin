package com.layjava.system.domain.vo;

import com.layjava.system.domain.SysOss;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.layjava.common.mybatis.core.entity.BaseEntity;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
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
@AutoMapper(target = SysOss.class, convertGenerate = false)
public class SysOssVo implements Serializable {

    /**
     * 对象存储主键
     */
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
