package com.layjava.system.domain;

import com.easy.query.core.annotation.Column;
import com.easy.query.core.annotation.EasyAlias;
import com.easy.query.core.annotation.EntityProxy;
import com.easy.query.core.annotation.Table;
import com.easy.query.core.proxy.ProxyEntityAvailable;
import com.layjava.system.domain.proxy.SysRoleMenuProxy;
import lombok.Data;

/**
 * 角色和菜单关联 sys_role_menu
 *
 * @author chengliang
 * @date 2024/06/13
 */

@Data
@EntityProxy
@Table("sys_role_menu")
@EasyAlias("sysRoleMenu")
public class SysRoleMenu implements ProxyEntityAvailable<SysRoleMenu , SysRoleMenuProxy> {

    /**
     * 角色ID
     */
    @Column(primaryKey = true)
    private Long roleId;

    /**
     * 菜单ID
     */
    @Column(primaryKey = true)
    private Long menuId;

}
