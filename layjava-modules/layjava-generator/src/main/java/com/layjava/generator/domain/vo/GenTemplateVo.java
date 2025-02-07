package com.layjava.generator.domain.vo;

import com.layjava.generator.domain.GenTemplate;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 代码生成模板视图对象 gen_template
 *
 * @author chengliang4810
 * @date 2025-01-05
 */
@Data
@AutoMapper(target = GenTemplate.class)
public class GenTemplateVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private Long id;

    /**
     * 模板分类，用于批量选择模板
     */
    private String category;

    /**
     * 模板名称
     */
    private String name;

    /**
     * 文件路径
     */
    private String path;

    /**
     * 数据库类型
     */
    private String dbType;

    /**
     * 模板内容
     */
    private String content;

    /**
     * 备注
     */
    private String remark;

    /**
     * 最后更新时间
     */
    private LocalDateTime updateTime;

}
