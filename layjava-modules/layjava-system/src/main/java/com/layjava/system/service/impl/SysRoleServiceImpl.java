package com.layjava.system.service.impl;

import com.layjava.common.mybatis.core.page.PageQuery;
import com.layjava.common.mybatis.core.page.PageResult;
import com.layjava.system.domain.SysUserRole;
import com.layjava.system.domain.bo.SysRoleBo;
import com.layjava.system.domain.vo.SysRoleVo;
import com.layjava.system.service.ISysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.Component;

import java.util.List;
import java.util.Set;

@Slf4j
@Component
public class SysRoleServiceImpl implements ISysRoleService {

    @Override
    public PageResult<SysRoleVo> selectPageRoleList(SysRoleBo role, PageQuery pageQuery) {
        return null;
    }

    @Override
    public List<SysRoleVo> selectRoleList(SysRoleBo role) {
        return List.of();
    }

    @Override
    public List<SysRoleVo> selectRolesByUserId(Long userId) {
        return List.of();
    }

    @Override
    public Set<String> selectRolePermissionByUserId(Long userId) {
        return Set.of();
    }

    @Override
    public List<SysRoleVo> selectRoleAll() {
        return List.of();
    }

    @Override
    public List<Long> selectRoleListByUserId(Long userId) {
        return List.of();
    }

    @Override
    public SysRoleVo selectRoleById(Long roleId) {
        return null;
    }

    @Override
    public boolean checkRoleNameUnique(SysRoleBo role) {
        return false;
    }

    @Override
    public boolean checkRoleKeyUnique(SysRoleBo role) {
        return false;
    }

    @Override
    public void checkRoleAllowed(SysRoleBo role) {

    }

    @Override
    public void checkRoleDataScope(Long roleId) {

    }

    @Override
    public long countUserRoleByRoleId(Long roleId) {
        return 0;
    }

    @Override
    public int insertRole(SysRoleBo bo) {
        return 0;
    }

    @Override
    public int updateRole(SysRoleBo bo) {
        return 0;
    }

    @Override
    public boolean updateRoleStatus(Long roleId, String status) {
        return false;
    }

    @Override
    public int authDataScope(SysRoleBo bo) {
        return 0;
    }

    @Override
    public int deleteRoleById(Long roleId) {
        return 0;
    }

    @Override
    public int deleteRoleByIds(Long[] roleIds) {
        return 0;
    }

    @Override
    public int deleteAuthUser(SysUserRole userRole) {
        return 0;
    }

    @Override
    public int deleteAuthUsers(Long roleId, Long[] userIds) {
        return 0;
    }

    @Override
    public int insertAuthUsers(Long roleId, Long[] userIds) {
        return 0;
    }

    @Override
    public void cleanOnlineUserByRole(Long roleId) {

    }
}
