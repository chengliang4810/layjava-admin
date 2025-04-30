package com.jimuqu.system.domain;

import cn.xbatis.db.annotations.Table;
import cn.xbatis.db.annotations.TableId;
import lombok.Data;

/**
 * 角色和部门关联 sys_role_dept
 *
 * @author Lion Li,chengliang4810
 */

@Data
@Table("sys_role_dept")
public class SysRoleDept {

    /**
     * 角色ID
     */
    @TableId
    private Long roleId;

    /**
     * 部门ID
     */
    private Long deptId;

}
