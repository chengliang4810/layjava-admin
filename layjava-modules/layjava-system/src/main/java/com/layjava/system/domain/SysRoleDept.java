package com.layjava.system.domain;

import com.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * 角色和部门关联 sys_role_dept
 *
 * @author chengliang
 * @date 2024/06/13
 */

@Data
@Table("sys_role_dept")
public class SysRoleDept {

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 部门ID
     */
    private Long deptId;

}
