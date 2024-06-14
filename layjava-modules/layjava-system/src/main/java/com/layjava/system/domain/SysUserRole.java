package com.layjava.system.domain;

import com.easy.query.core.annotation.Column;
import com.easy.query.core.annotation.EasyAlias;
import com.easy.query.core.annotation.EntityProxy;
import com.easy.query.core.annotation.Table;
import com.easy.query.core.proxy.ProxyEntityAvailable;
import com.layjava.system.domain.proxy.SysUserRoleProxy;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

/**
 * 用户和角色关联 sys_user_role
 *
 * @author chengliang
 * @date 2024/06/13
 */
@Data
@EntityProxy
@FieldNameConstants
@Table("sys_user_role")
@EasyAlias("sysUserRole")
public class SysUserRole implements ProxyEntityAvailable<SysUserRole , SysUserRoleProxy> {

    /**
     * 用户ID
     */
    @Column(primaryKey = true)
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

}
