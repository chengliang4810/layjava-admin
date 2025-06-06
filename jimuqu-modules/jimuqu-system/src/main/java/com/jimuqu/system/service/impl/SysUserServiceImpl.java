package com.jimuqu.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.xbatis.core.sql.executor.chain.QueryChain;
import com.jimuqu.common.core.exception.ServiceException;
import com.jimuqu.common.core.utils.MapstructUtil;
import com.jimuqu.common.mybatis.core.Page;
import com.jimuqu.common.mybatis.core.page.PageQuery;
import com.jimuqu.common.satoken.utils.LoginHelper;
import com.jimuqu.system.domain.SysUser;
import com.jimuqu.system.domain.bo.SysUserBo;
import com.jimuqu.system.domain.query.SysUserQuery;
import com.jimuqu.system.domain.vo.SysUserVo;
import com.jimuqu.system.mapper.SysDeptMapper;
import com.jimuqu.system.mapper.SysRoleMapper;
import com.jimuqu.system.mapper.SysUserMapper;
import com.jimuqu.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.util.ObjUtil;
import org.noear.solon.annotation.Component;

import java.util.Collection;
import java.util.List;


/**
 * 用户信息Service业务层处理
 *
 * @author chengliang4810
 * @since 2025-06-05
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SysUserServiceImpl implements SysUserService {

    private final SysUserMapper sysUserMapper;
    private final SysDeptMapper sysDeptMapper;
    private final SysRoleMapper sysRoleMapper;

    /**
     * 查询用户信息
     */
    @Override
    public SysUserVo queryById(Long id) {
        SysUserVo userVo = sysUserMapper.getVoById(id);
        if (ObjUtil.isNull(userVo)) {
            return null;
        }
        userVo.setRoles(sysRoleMapper.selectRolesByUserId(id));
        return userVo;
    }

    /**
     * 查询用户信息分页列表
     */
    @Override
    public Page<SysUserVo> queryPageList(SysUserQuery query, PageQuery pageQuery) {
        return buildQueryChain(query)
                .select(SysUser.class)
                .returnType(SysUserVo.class)
                .paging(pageQuery.build());
    }

    /**
     * 查询用户信息列表
     */
    @Override
    public List<SysUserVo> queryList(SysUserQuery query) {
        QueryChain<SysUser> queryChain = buildQueryChain(query);
        return queryChain.returnType(SysUserVo.class).list();
    }

    /**
     * 构建查询条件
     *
     * @param query 查询对象
     * @return 查询条件对象
     */
    private QueryChain<SysUser> buildQueryChain(SysUserQuery query) {
        QueryChain<SysUser> queryChain = QueryChain.of(sysUserMapper)
                .forSearch(true)
                .where(query);

        if (ObjUtil.isNotNull(query.getDeptId())) {
            List<Long> deptIdList = sysDeptMapper.selectListByParentId(query.getDeptId());
            deptIdList.add(query.getDeptId());
            queryChain.in(SysUser::getDeptId, deptIdList);
        }

        return queryChain;
    }

    /**
     * 新增用户信息
     */
    @Override
    public Boolean insertByBo(SysUserBo bo) {
        SysUser sysUser = MapstructUtil.convert(bo, SysUser.class);
        boolean flag = sysUserMapper.save(sysUser) > 0;
        bo.setId(sysUser.getId());
        return flag;
    }

    /**
     * 修改用户信息
     */
    @Override
    public Boolean updateByBo(SysUserBo bo) {
        SysUser sysUser = MapstructUtil.convert(bo, SysUser.class);
        return sysUserMapper.update(sysUser) > 0;
    }

    /**
     * 修改用户基本信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUserProfile(SysUserBo user) {
        return 0;
    }

    /**
     * 批量删除用户信息
     */
    @Override
    public Integer deleteByIds(Collection<Long> ids) {
        return sysUserMapper.deleteByIds(ids);
    }

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public SysUserVo selectUserByUserName(String userName) {
        return null;
    }

    /**
     * 通过手机号查询用户
     *
     * @param phonenumber 手机号
     * @return 用户对象信息
     */
    @Override
    public SysUserVo selectUserByPhonenumber(String phonenumber) {
        return null;
    }

    /**
     * 根据用户ID查询用户所属角色组
     *
     * @param userName 用户名
     * @return 结果
     */
    @Override
    public String selectUserRoleGroup(String userName) {
        return "";
    }

    /**
     * 根据用户ID查询用户所属岗位组
     *
     * @param userName 用户名
     * @return 结果
     */
    @Override
    public String selectUserPostGroup(String userName) {
        return "";
    }

    /**
     * 根据条件分页查询已分配用户角色列表
     *
     * @param user      用户信息
     * @param pageQuery
     * @return 用户信息集合信息
     */
    @Override
    public Page<SysUserVo> selectAllocatedList(SysUserBo user, PageQuery pageQuery) {
        return null;
    }

    /**
     * 根据条件分页查询未分配用户角色列表
     *
     * @param user      用户信息
     * @param pageQuery
     * @return 用户信息集合信息
     */
    @Override
    public Page<SysUserVo> selectUnallocatedList(SysUserBo user, PageQuery pageQuery) {
        return null;
    }

    /**
     * 通过部门id查询当前部门所有用户
     *
     * @param deptId 部门id
     * @return {@link List }<{@link SysUserVo }> 用户信息列表
     */
    @Override
    public List<SysUserVo> selectUserListByDept(Long deptId) {
        return QueryChain.of(sysUserMapper)
                .select(SysUser.class)
                .eq(SysUser::getDeptId, deptId)
                .orderBy(SysUser::getId)
                .returnType(SysUserVo.class).list();
    }

    /**
     * 注册用户信息
     *
     * @param bo 用户信息
     * @return 结果
     */
    @Override
    public boolean registerUser(SysUserBo bo) {
        return false;
    }

    /**
     * 用户授权角色
     *
     * @param userId  用户ID
     * @param roleIds 角色组
     */
    @Override
    public void insertUserAuth(Long userId, Long[] roleIds) {

    }

    /**
     * 修改用户状态
     *
     * @param userId 用户ID
     * @param status 帐号状态
     * @return 结果
     */
    @Override
    public boolean updateUserStatus(Long userId, String status) {
        return sysUserMapper.update(new SysUser().setId(userId).setStatus(status)) > 0;
    }

    /**
     * 修改用户头像
     *
     * @param userId 用户ID
     * @param avatar 头像地址
     * @return 结果
     */
    @Override
    public boolean updateUserAvatar(Long userId, Long avatar) {
        return sysUserMapper.update(new SysUser().setId(userId).setAvatar(avatar)) > 0;
    }

    /**
     * 重置用户密码
     *
     * @param userId   用户ID
     * @param password 密码
     * @return 结果
     */
    @Override
    public boolean resetUserPwd(Long userId, String password) {
        return sysUserMapper.update(new SysUser().setId(userId).setPassword(password)) > 0;
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public boolean checkUserNameUnique(SysUserBo user) {
        return !sysUserMapper.exists(where -> where
                .eq(SysUser::getUserName, user.getUserName())
                .ne(ObjectUtil.isNotNull(user.getId()), SysUser::getId, user.getId())
        );
    }

    /**
     * 校验手机号码是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public boolean checkPhoneUnique(SysUserBo user) {
        return !sysUserMapper.exists(where -> where
                .eq(SysUser::getPhonenumber, user.getPhonenumber())
                .ne(ObjectUtil.isNotNull(user.getId()), SysUser::getId, user.getId())
        );
    }

    /**
     * 校验email是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public boolean checkEmailUnique(SysUserBo user) {
        return !sysUserMapper.exists(where -> where
                .eq(SysUser::getEmail, user.getEmail())
                .ne(ObjectUtil.isNotNull(user.getId()), SysUser::getId, user.getId())
        );
    }

    /**
     * 校验用户是否允许操作
     *
     * @param userId 用户ID
     */
    @Override
    public void checkUserAllowed(Long userId) {
        if (ObjectUtil.isNotNull(userId) && LoginHelper.isSuperAdmin(userId)) {
            throw new ServiceException("不允许操作超级管理员用户");
        }
    }

    /**
     * 校验用户是否有数据权限
     *
     * @param userId 用户id
     */
    @Override
    public void checkUserDataScope(Long userId) {
        if (ObjectUtil.isNull(userId)) {
            return;
        }
        if (LoginHelper.isSuperAdmin()) {
            return;
        }
        if (ObjectUtil.isNull(sysUserMapper.selectUserById(userId))) {
            throw new ServiceException("没有权限访问用户数据！");
        }
    }
}
