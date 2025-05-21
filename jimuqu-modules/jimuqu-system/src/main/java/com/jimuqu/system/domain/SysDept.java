package com.jimuqu.system.domain;

import cn.xbatis.db.annotations.Table;
import com.jimuqu.common.mybatis.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 部门表 sys_dept
 *
 * @author Lion Li,chengliang4810
 */

@Data
@Table("sys_dept")
@EqualsAndHashCode(callSuper = true)
public class SysDept extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 父部门ID
     */
    private Long parentId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 显示顺序
     */
    private Integer orderNum;

    /**
     * 负责人
     */
    private Long leader;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 部门状态:0正常,1停用
     */
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    /**
     * 祖级列表
     */
    private String ancestors;

}
