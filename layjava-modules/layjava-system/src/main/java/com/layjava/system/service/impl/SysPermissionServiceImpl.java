package com.layjava.system.service.impl;


import com.layjava.common.core.constant.TenantConstants;
import com.layjava.common.satoken.utils.LoginHelper;
import com.layjava.system.service.ISysMenuService;
import com.layjava.system.service.ISysPermissionService;
import com.layjava.system.service.ISysRoleService;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户权限处理
 *
 * @author ruoyi
 */

@Component
public class SysPermissionServiceImpl implements ISysPermissionService {

    @Inject
    private ISysRoleService roleService;
    @Inject
    private ISysMenuService menuService;

    /**
     * 获取角色数据权限
     *
     * @param userId 用户id
     * @return 角色权限信息
     */
    @Override
    public Set<String> getRolePermission(Long userId) {
        Set<String> roles = new HashSet<>();
        // 管理员拥有所有权限
        if (LoginHelper.isSuperAdmin(userId)) {
            roles.add(TenantConstants.SUPER_ADMIN_ROLE_KEY);
        } else {
            roles.addAll(roleService.selectRolePermissionByUserId(userId));
        }
        return roles;
    }

    /**
     * 获取菜单数据权限
     *
     * @param userId 用户id
     * @return 菜单权限信息
     */
    @Override
    public Set<String> getMenuPermission(Long userId) {
        Set<String> perms = new HashSet<>();
        // 管理员拥有所有权限
        if (LoginHelper.isSuperAdmin(userId)) {
            perms.add("*:*:*");
        } else {
            perms.addAll(menuService.selectMenuPermsByUserId(userId));
        }
        return perms;
    }
}
