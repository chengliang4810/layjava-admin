package com.jimuqu.system.domain;

import cn.xbatis.core.incrementer.IdentifierGeneratorType;
import cn.xbatis.db.IdAutoType;
import cn.xbatis.db.annotations.Table;
import cn.xbatis.db.annotations.TableId;
import com.jimuqu.common.mybatis.core.entity.TenantEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.dromara.autotable.annotation.AutoColumn;

import java.io.Serial;

/**
 * <p>
 * 部门
 * </p>
 * @author chengliang4810
 * @since 2025-05-23
 */
@Data
@NoArgsConstructor
@FieldNameConstants
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_dept")
public class SysDept extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = IdAutoType.GENERATOR, generatorName = IdentifierGeneratorType.DEFAULT)
    protected Long id;

    /**
     * 部门id
     */
    @AutoColumn(comment = "部门id", length = -1, defaultValue = "", type = "")
    private Long deptId;
    /**
     * 父部门id
     */
    @AutoColumn(comment = "父部门id", length = -1, defaultValue = "", type = "")
    private Long parentId;
    /**
     * 祖级列表
     */
    @AutoColumn(comment = "祖级列表", length = -1, defaultValue = "", type = "")
    private String ancestors;
    /**
     * 部门名称
     */
    @AutoColumn(comment = "部门名称", length = -1, defaultValue = "", type = "")
    private String deptName;
    /**
     * 显示顺序
     */
    @AutoColumn(comment = "显示顺序", length = -1, defaultValue = "", type = "")
    private Integer orderNum;
    /**
     * 负责人
     */
    @AutoColumn(comment = "负责人", length = -1, defaultValue = "", type = "")
    private Long leader;
    /**
     * 联系电话
     */
    @AutoColumn(comment = "联系电话", length = -1, defaultValue = "", type = "")
    private String phone;
    /**
     * 邮箱
     */
    @AutoColumn(comment = "邮箱", length = -1, defaultValue = "", type = "")
    private String email;
    /**
     * 部门状态（0正常 1停用）
     */
    @AutoColumn(comment = "部门状态（0正常 1停用）", length = -1, defaultValue = "", type = "")
    private String status;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @AutoColumn(comment = "删除标志（0代表存在 2代表删除）", length = -1, defaultValue = "", type = "")
    private String delFlag;
}
