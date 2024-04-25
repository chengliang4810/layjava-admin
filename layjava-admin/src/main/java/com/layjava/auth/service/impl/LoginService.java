package com.layjava.auth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.layjava.common.core.domain.dto.RoleDTO;
import com.layjava.common.core.domain.model.LoginUser;
import com.layjava.system.domain.vo.SysUserVo;
import com.layjava.system.service.SysPermissionService;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

import java.util.List;

/**
 * 登录服务
 *
 * @author chengliang
 * @since 2024/04/25
 */
@Component
public class LoginService {

    @Inject
    private SysPermissionService permissionService;

    /**
     * 构建登录用户
     */
    public LoginUser buildLoginUser(SysUserVo user) {
        LoginUser loginUser = new LoginUser();
        loginUser.setId(user.getId());
        loginUser.setDeptId(user.getDeptId());
        loginUser.setAccount(user.getAccount());
        loginUser.setName(user.getName());
        loginUser.setUserType(user.getUserType());
        loginUser.setMenuPermission(permissionService.getMenuPermission(user.getId()));
        loginUser.setRolePermission(permissionService.getRolePermission(user.getId()));
        loginUser.setDeptName(ObjectUtil.isNull(user.getDept()) ? "" : user.getDept().getDeptName());
        List<RoleDTO> roles = BeanUtil.copyToList(user.getRoles(), RoleDTO.class);
        loginUser.setRoles(roles);
        return loginUser;
    }

}
