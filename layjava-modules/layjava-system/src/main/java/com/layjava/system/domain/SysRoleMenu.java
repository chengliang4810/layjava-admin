package com.layjava.system.domain;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * 角色和菜单关联 sys_role_menu
 *
 * @author Lion Li
 */

@Data
@Table("sys_role_menu")
public class SysRoleMenu {

    /**
     * 角色ID
     */
    @Id(keyType = KeyType.None)
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;

}
