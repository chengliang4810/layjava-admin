package com.layjava.system.domain;

import cn.xbatis.db.annotations.Table;
import cn.xbatis.db.annotations.TableId;
import lombok.Data;

/**
 * 角色和菜单关联 sys_role_menu
 *
 * @author Lion Li,chengliang4810
 */

@Data
@Table("sys_role_menu")
public class SysRoleMenu {

    /**
     * 角色ID
     */
    @TableId
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;

}
