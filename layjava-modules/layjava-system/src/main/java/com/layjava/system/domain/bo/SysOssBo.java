package com.layjava.system.domain.bo;

import com.layjava.system.domain.SysOss;
import io.github.linpeilie.annotations.AutoMapper;
import org.noear.solon.validation.annotation.NotNull;
import org.noear.solon.validation.annotation.NotBlank;
import com.layjava.common.core.validate.group.AddGroup;
import com.layjava.common.core.validate.group.UpdateGroup;
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
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SysOss.class, reverseConvertGenerate = false)
public class SysOssBo extends BaseEntity {


    /**
     * 对象存储主键
     */
    @NotNull(message = "对象存储主键不能为空", groups = { UpdateGroup.class })
    private Long ossId;

    /**
     * 文件名
     */
    @NotBlank(message = "文件名不能为空", groups = { AddGroup.class, UpdateGroup.class })
    private String fileName;

    /**
     * 原名
     */
    @NotBlank(message = "原名不能为空", groups = { AddGroup.class, UpdateGroup.class })
    private String originalName;

    /**
     * 文件后缀名
     */
    @NotBlank(message = "文件后缀名不能为空", groups = { AddGroup.class, UpdateGroup.class })
    private String fileSuffix;

    /**
     * URL地址
     */
    @NotBlank(message = "URL地址不能为空", groups = { AddGroup.class, UpdateGroup.class })
    private String url;

    /**
     * 文档大小
     */
    @NotNull(message = "文档大小不能为空", groups = { AddGroup.class, UpdateGroup.class })
    private Long size;

    /**
     * 服务商
     */
    @NotBlank(message = "服务商不能为空", groups = { AddGroup.class, UpdateGroup.class })
    private String service;

}
