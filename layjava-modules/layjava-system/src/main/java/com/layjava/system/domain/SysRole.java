package com.layjava.system.domain;

import com.easy.query.core.annotation.*;
import com.easy.query.core.enums.RelationTypeEnum;
import com.easy.query.core.proxy.ProxyEntityAvailable;
import com.layjava.common.dao.core.entity.BaseEntity;
import com.layjava.system.domain.proxy.SysRoleProxy;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.util.List;

/**
 * 角色表 sys_role
 *
 * @author chengliang
 * @date 2024/06/13
 */

@Data
@EntityProxy
@Table("sys_role")
@NoArgsConstructor
@FieldNameConstants
@EasyAlias("sysRole")
@EqualsAndHashCode(callSuper = true)
public class SysRole extends BaseEntity implements ProxyEntityAvailable<SysRole , SysRoleProxy> {

    /**
     * 角色ID
     */
    @Column(primaryKey = true)
    private Long roleId;

    @Navigate(value = RelationTypeEnum.ManyToMany,
            mappingClass = SysUserRole.class,
            selfMappingProperty = "roleId",
            targetMappingProperty = "userId")
    private List<SysUser> users;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色权限
     */
    private String roleKey;

    /**
     * 角色排序
     */
    private Integer roleSort;

    /**
     * 数据范围（1：所有数据权限；2：自定义数据权限；3：本部门数据权限；4：本部门及以下数据权限；5：仅本人数据权限）
     */
    // private String dataScope;

    /**
     * 菜单树选择项是否关联显示（ 0：父子不互相关联显示 1：父子互相关联显示）
     */
    private Boolean menuCheckStrictly;

    /**
     * 部门树选择项是否关联显示（0：父子不互相关联显示 1：父子互相关联显示 ）
     */
    private Boolean deptCheckStrictly;

    /**
     * 角色状态（0正常 1停用）
     */
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
//    @LogicDelete(strategy = LogicDeleteStrategyEnum.DELETE_LONG_TIMESTAMP)
    private String delFlag;

    /**
     * 备注
     */
    private String remark;

    public SysRole(Long roleId) {
        this.roleId = roleId;
    }

}
