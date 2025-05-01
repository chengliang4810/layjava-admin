package com.jimuqu.system.service.impl;

import com.jimuqu.common.mybatis.core.page.PageQuery;
import com.jimuqu.common.mybatis.core.page.PageResult;
import com.jimuqu.system.domain.bo.SysUserBo;
import com.jimuqu.system.domain.vo.SysUserVo;
import com.jimuqu.system.mapper.SysUserMapper;
import com.jimuqu.system.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SysUserServiceImpl implements ISysUserService {

    private final SysUserMapper sysUserMapper;

    @Override
    public PageResult<SysUserVo> selectPageUserList(SysUserBo user, PageQuery pageQuery) {
        return null;
    }

    @Override
    public List<SysUserVo> selectUserList(SysUserBo user) {
        return List.of();
    }

    @Override
    public PageResult<SysUserVo> selectAllocatedList(SysUserBo user, PageQuery pageQuery) {
        return null;
    }

    @Override
    public PageResult<SysUserVo> selectUnallocatedList(SysUserBo user, PageQuery pageQuery) {
        return null;
    }

    @Override
    public SysUserVo selectUserByUserName(String userName) {
        return null;
    }

    @Override
    public SysUserVo selectUserByPhonenumber(String phonenumber) {
        return null;
    }

    @Override
    public SysUserVo selectUserById(Long userId) {
        return sysUserMapper.selectUserById(userId);
    }

    @Override
    public String selectUserRoleGroup(String userName) {
        return "";
    }

    @Override
    public String selectUserPostGroup(String userName) {
        return "";
    }

    @Override
    public boolean checkUserNameUnique(SysUserBo user) {
        return false;
    }

    @Override
    public boolean checkPhoneUnique(SysUserBo user) {
        return false;
    }

    @Override
    public boolean checkEmailUnique(SysUserBo user) {
        return false;
    }

    @Override
    public void checkUserAllowed(Long userId) {

    }

    @Override
    public void checkUserDataScope(Long userId) {

    }

    @Override
    public int insertUser(SysUserBo user) {
        return 0;
    }

    @Override
    public boolean registerUser(SysUserBo user) {
        return false;
    }

    @Override
    public int updateUser(SysUserBo user) {
        return 0;
    }

    @Override
    public void insertUserAuth(Long userId, Long[] roleIds) {

    }

    @Override
    public boolean updateUserStatus(Long userId, String status) {
        return false;
    }

    @Override
    public int updateUserProfile(SysUserBo user) {
        return 0;
    }

    @Override
    public boolean updateUserAvatar(Long userId, Long avatar) {
        return false;
    }

    @Override
    public boolean resetUserPwd(Long userId, String password) {
        return false;
    }

    @Override
    public int deleteUserById(Long userId) {
        return 0;
    }

    @Override
    public int deleteUserByIds(Long[] userIds) {
        return 0;
    }

    @Override
    public List<SysUserVo> selectUserListByDept(Long deptId) {
        return List.of();
    }
}
