package com.layjava.system.domain;

import com.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * 角色和菜单关联 sys_role_menu
 *
 * @author chengliang
 * @date 2024/06/13
 */

@Data
@Table("sys_role_menu")
public class SysRoleMenu {

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;

}
