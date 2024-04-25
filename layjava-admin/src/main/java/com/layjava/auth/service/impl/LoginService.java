package com.layjava.auth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.layjava.common.core.domain.dto.RoleDTO;
import com.layjava.common.core.domain.model.LoginUser;
import com.layjava.system.domain.vo.SysUserVo;
import org.noear.solon.annotation.Component;

import java.util.List;

/**
 * 登录服务
 *
 * @author chengliang
 * @since 2024/04/25
 */
@Component
public class LoginService {

    /**
     * 构建登录用户
     */
    public LoginUser buildLoginUser(SysUserVo user) {
        LoginUser loginUser = new LoginUser();
        loginUser.setId(user.getId());
        loginUser.setDeptId(user.getDeptId());
        loginUser.setUsername(user.getUserName());
        loginUser.setNickname(user.getNickName());
        loginUser.setUserType(user.getUserType());
        // loginUser.setMenuPermission(permissionService.getMenuPermission(user.getUserId()));
        // loginUser.setRolePermission(permissionService.getRolePermission(user.getUserId()));
        // loginUser.setDeptName(ObjectUtil.isNull(user.getDept()) ? "" : user.getDept().getDeptName());
        // List<RoleDTO> roles = BeanUtil.copyToList(user.getRoles(), RoleDTO.class);
        // loginUser.setRoles(roles);
        return loginUser;
    }

}
