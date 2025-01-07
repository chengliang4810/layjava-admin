package com.layjava.generator.domain.bo;

import com.layjava.common.core.validate.group.AddGroup;
import com.layjava.common.core.validate.group.UpdateGroup;
import com.layjava.common.mybatis.core.entity.BaseEntity;
import com.layjava.generator.domain.GenTemplate;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.noear.solon.validation.annotation.NotBlank;
import org.noear.solon.validation.annotation.NotNull;

/**
 * 代码生成模板业务对象 gen_template
 *
 * @author chengliang4810
 * @date 2025-01-05
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = GenTemplate.class, reverseConvertGenerate = false)
public class GenTemplateBo extends BaseEntity {

    /**
     * 编号
     */
    @NotNull(message = "编号不能为空", groups = { UpdateGroup.class })
    private Long id;

    /**
     * 模板分类，用于批量选择模板
     */
    @NotBlank(message = "模板分类，用于批量选择模板不能为空", groups = { AddGroup.class, UpdateGroup.class })
    private String category;

    /**
     * 模板名称
     */
    @NotBlank(message = "模板名称不能为空", groups = { AddGroup.class, UpdateGroup.class })
    private String name;

    /**
     * 文件路径
     */
//    @NotBlank(message = "文件路径不能为空", groups = { AddGroup.class, UpdateGroup.class })
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


}
