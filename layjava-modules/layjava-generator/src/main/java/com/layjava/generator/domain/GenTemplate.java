package com.layjava.generator.domain;

import com.layjava.common.mybatis.core.entity.BaseEntity;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
  import org.dromara.autotable.annotation.AutoColumn;
import org.dromara.autotable.annotation.AutoTable;
import org.dromara.autotable.annotation.PrimaryKey;
import org.dromara.autotable.annotation.mysql.MysqlTypeConstant;

import java.io.Serial;

/**
 * 代码生成模板对象 gen_template
 *
 * @author chengliang4810
 * @date 2025-01-05
 */
@Data
@Table("gen_template")
@AutoTable(value = "gen_template", comment = "代码模板")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class GenTemplate extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @Id
    @PrimaryKey
    @AutoColumn(comment = "编号")
    private Long id;

    /**
     * 模板分类，用于批量选择模板
     */
    @AutoColumn(comment = "模板分类，用于批量选择模板")
    private String category;

    /**
     * 模板名称
     */
    @AutoColumn(comment = "模板名称")
    private String name;

    /**
     * 文件路径
     */
    @AutoColumn(comment = "文件路径")
    private String path;

    /**
     * 数据库类型
     */
    @AutoColumn(comment = "数据库类型")
    private String dbType;

    /**
     * 模板内容
     */
    @AutoColumn(type = MysqlTypeConstant.TEXT, comment = "模板内容")
    private String content;

    /**
     * 是否启用
     */
    @AutoColumn(comment = "是否启用")
    private Boolean enabled;

    /**
     * 备注
     */
    @AutoColumn(comment = "备注")
    private String remark;


}
