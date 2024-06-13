package com.layjava.system.domain;

import com.easy.query.core.annotation.*;
import com.easy.query.core.basic.extension.logicdel.LogicDeleteStrategyEnum;
import com.easy.query.core.proxy.ProxyEntityAvailable;
import com.layjava.common.dao.core.entity.BaseEntity;
import com.layjava.system.domain.proxy.SysDeptProxy;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 部门表 sys_dept
 *
 * @author chengliang
 * @since 2024/06/13
 */

@Data
@EntityProxy
@Table("sys_dept")
@EasyAlias("sysDept")
@EqualsAndHashCode(callSuper = true)
public class SysDept extends BaseEntity implements ProxyEntityAvailable<SysDept , SysDeptProxy> {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 部门ID
     */
    @Column(primaryKey = true)
    private Long deptId;

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
    @LogicDelete(strategy = LogicDeleteStrategyEnum.DELETE_LONG_TIMESTAMP)
    private String delFlag;

    /**
     * 祖级列表
     */
    private String ancestors;

}
