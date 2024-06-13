package com.layjava.system.domain;

import com.easy.query.core.annotation.Column;
import com.easy.query.core.annotation.EasyAlias;
import com.easy.query.core.annotation.EntityProxy;
import com.easy.query.core.annotation.Table;
import com.easy.query.core.proxy.ProxyEntityAvailable;
import com.layjava.system.domain.proxy.SysRoleDeptProxy;
import lombok.Data;

/**
 * 角色和部门关联 sys_role_dept
 *
 * @author chengliang
 * @date 2024/06/13
 */

@Data
@EntityProxy
@Table("sys_role_dept")
@EasyAlias("sysRoleDept")
public class SysRoleDept implements ProxyEntityAvailable<SysRoleDept , SysRoleDeptProxy> {

    /**
     * 角色ID
     */
    @Column(primaryKey = true)
    private Long roleId;

    /**
     * 部门ID
     */
    @Column(primaryKey = true)
    private Long deptId;

}
