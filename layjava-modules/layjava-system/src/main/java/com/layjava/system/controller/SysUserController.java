package com.layjava.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.secure.BCrypt;
import com.layjava.common.core.constant.UserConstants;
import com.layjava.common.core.domain.R;
import com.layjava.common.core.domain.model.LoginUser;
import com.layjava.common.core.utils.MapstructUtil;
import com.layjava.common.core.utils.StreamUtil;
import com.layjava.common.core.utils.StringUtil;
import com.layjava.common.log.annotation.Log;
import com.layjava.common.log.enums.BusinessType;
import com.layjava.common.mybatis.core.page.PageQuery;
import com.layjava.common.mybatis.core.page.PageResult;
import com.layjava.common.satoken.utils.LoginHelper;
import com.layjava.common.web.core.BaseController;
import com.layjava.system.domain.bo.SysDeptBo;
import com.layjava.system.domain.bo.SysPostBo;
import com.layjava.system.domain.bo.SysRoleBo;
import com.layjava.system.domain.bo.SysUserBo;
import com.layjava.system.domain.vo.*;
import com.layjava.system.service.ISysDeptService;
import com.layjava.system.service.ISysPostService;
import com.layjava.system.service.ISysRoleService;
import com.layjava.system.service.ISysUserService;
import org.dromara.hutool.core.array.ArrayUtil;
import org.dromara.hutool.core.tree.MapTree;
import org.dromara.hutool.core.util.ObjUtil;
import org.noear.solon.annotation.*;
import org.noear.solon.core.handle.UploadedFile;
import org.noear.solon.validation.annotation.NotNull;

import java.util.List;

/**
 * 用户信息
 *
 * @author Lion Li,chengliang4810
 */
@Controller
@Mapping("/system/user" )
public class SysUserController extends BaseController {

    @Inject
    private ISysUserService userService;
    @Inject
    private ISysRoleService roleService;
    @Inject
    private ISysPostService postService;
    @Inject
    private ISysDeptService deptService;

    /**
     * 获取用户列表
     */
    @Get
    @Mapping("/list" )
    @SaCheckPermission("system:user:list" )
    public PageResult<SysUserVo> list(SysUserBo user, PageQuery pageQuery) {
        return userService.selectPageUserList(user, pageQuery);
    }

    /**
     * 导出用户列表
     */
    @Post
    @Mapping("/export" )
    @SaCheckPermission("system:user:export" )
    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    public void export(SysUserBo user) {
        List<SysUserVo> list = userService.selectUserList(user);
        List<SysUserExportVo> listVo = MapstructUtil.convert(list, SysUserExportVo.class);
        // ExcelUtil.exportExcel(listVo, "用户数据", SysUserExportVo.class, response);
    }

    /**
     * 导入数据
     *
     * @param file          导入文件
     * @param updateSupport 是否更新已存在数据
     */
    @Post
    @Mapping(value = "/importData" )
    @SaCheckPermission("system:user:import" )
    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    public R<Void> importData(UploadedFile file, boolean updateSupport) throws Exception {
        // ExcelResult<SysUserImportVo> result = ExcelUtil.importExcel(file.getInputStream(), SysUserImportVo.class, new SysUserImportListener(updateSupport));
        // return R.ok(result.getAnalysis());
        return R.ok();
    }

    /**
     * 获取导入模板
     */
    @Post
    @Mapping("/importTemplate" )
    public void importTemplate() {
        // ExcelUtil.exportExcel(new ArrayList<>(), "用户数据", SysUserImportVo.class, response);
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @Get
    @Mapping("/info" )
    public R<UserInfoVo> getInfo() {
        UserInfoVo userInfoVo = new UserInfoVo();
        LoginUser loginUser = LoginHelper.getLoginUser();
        SysUserVo user = userService.selectUserById(loginUser.getUserId());
        if (ObjUtil.isNull(user)) {
            return R.fail("没有权限访问用户数据!" );
        }
        userInfoVo.setUser(user);
        userInfoVo.setPermissions(loginUser.getMenuPermission());
        userInfoVo.setRoles(loginUser.getRolePermission());
        return R.ok(userInfoVo);
    }

    /**
     * 根据用户编号获取详细信息
     *
     * @param userId 用户ID
     */
    @Get
    @Mapping(value = "/{userId}" )
    @SaCheckPermission("system:user:query" )
    public R<SysUserInfoVo> getInfo(Long userId) {
        userService.checkUserDataScope(userId);
        SysUserInfoVo userInfoVo = new SysUserInfoVo();
        SysRoleBo roleBo = new SysRoleBo();
        roleBo.setStatus(UserConstants.ROLE_NORMAL);
        SysPostBo postBo = new SysPostBo();
        postBo.setStatus(UserConstants.POST_NORMAL);
        List<SysRoleVo> roles = roleService.selectRoleList(roleBo);
        userInfoVo.setRoles(LoginHelper.isSuperAdmin(userId) ? roles : StreamUtil.filter(roles, r -> !r.isSuperAdmin()));
        userInfoVo.setPosts(postService.selectPostList(postBo));
        SysUserVo sysUser = userService.selectUserById(userId);
        userInfoVo.setUser(sysUser);
        userInfoVo.setRoleIds(StreamUtil.toList(sysUser.getRoles(), SysRoleVo::getRoleId));
        userInfoVo.setPostIds(postService.selectPostListByUserId(userId));
        return R.ok(userInfoVo);
    }

    /**
     * 新增用户
     */
    @Post
    @Mapping
    @SaCheckPermission("system:user:add" )
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    public R<Void> add(SysUserBo user) {
        deptService.checkDeptDataScope(user.getDeptId());
        if (!userService.checkUserNameUnique(user)) {
            return R.fail("新增用户'" + user.getUserName() + "'失败，登录账号已存在" );
        } else if (StringUtil.isNotEmpty(user.getPhonenumber()) && !userService.checkPhoneUnique(user)) {
            return R.fail("新增用户'" + user.getUserName() + "'失败，手机号码已存在" );
        } else if (StringUtil.isNotEmpty(user.getEmail()) && !userService.checkEmailUnique(user)) {
            return R.fail("新增用户'" + user.getUserName() + "'失败，邮箱账号已存在" );
        }
        user.setPassword(BCrypt.hashpw(user.getPassword()));
        return toAjax(userService.insertUser(user));
    }

    /**
     * 修改用户
     */
    @Put
    @Mapping
    @SaCheckPermission("system:user:edit" )
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    public R<Void> edit(SysUserBo user) {
        userService.checkUserAllowed(user.getUserId());
        userService.checkUserDataScope(user.getUserId());
        deptService.checkDeptDataScope(user.getDeptId());
        if (!userService.checkUserNameUnique(user)) {
            return R.fail("修改用户'" + user.getUserName() + "'失败，登录账号已存在" );
        } else if (StringUtil.isNotEmpty(user.getPhonenumber()) && !userService.checkPhoneUnique(user)) {
            return R.fail("修改用户'" + user.getUserName() + "'失败，手机号码已存在" );
        } else if (StringUtil.isNotEmpty(user.getEmail()) && !userService.checkEmailUnique(user)) {
            return R.fail("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在" );
        }
        return toAjax(userService.updateUser(user));
    }

    /**
     * 删除用户
     *
     * @param userIds 角色ID串
     */
    @Delete
    @Mapping("/{userIds}" )
    @SaCheckPermission("system:user:remove" )
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    public R<Void> remove(Long[] userIds) {
        if (ArrayUtil.contains(userIds, LoginHelper.getUserId())) {
            return R.fail("当前用户不能删除" );
        }
        return toAjax(userService.deleteUserByIds(userIds));
    }

    /**
     * 重置密码
     */
    // @ApiEncrypt
    @Put
    @Mapping("/resetPwd" )
    @SaCheckPermission("system:user:resetPwd" )
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    public R<Void> resetPwd(SysUserBo user) {
        userService.checkUserAllowed(user.getUserId());
        userService.checkUserDataScope(user.getUserId());
        user.setPassword(BCrypt.hashpw(user.getPassword()));
        return toAjax(userService.resetUserPwd(user.getUserId(), user.getPassword()));
    }

    /**
     * 状态修改
     */
    @Put
    @Mapping("/changeStatus" )
    @SaCheckPermission("system:user:edit" )
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    public R<Void> changeStatus(SysUserBo user) {
        userService.checkUserAllowed(user.getUserId());
        userService.checkUserDataScope(user.getUserId());
        return toAjax(userService.updateUserStatus(user.getUserId(), user.getStatus()));
    }

    /**
     * 根据用户编号获取授权角色
     *
     * @param userId 用户ID
     */
    @Get
    @Mapping("/authRole/{userId}" )
    @SaCheckPermission("system:user:query" )
    public R<SysUserInfoVo> authRole(Long userId) {
        SysUserVo user = userService.selectUserById(userId);
        List<SysRoleVo> roles = roleService.selectRolesByUserId(userId);
        SysUserInfoVo userInfoVo = new SysUserInfoVo();
        userInfoVo.setUser(user);
        userInfoVo.setRoles(LoginHelper.isSuperAdmin(userId) ? roles : StreamUtil.filter(roles, r -> !r.isSuperAdmin()));
        return R.ok(userInfoVo);
    }

    /**
     * 用户授权角色
     *
     * @param userId  用户Id
     * @param roleIds 角色ID串
     */
    @Put
    @Mapping("/authRole" )
    @SaCheckPermission("system:user:edit" )
    @Log(title = "用户管理", businessType = BusinessType.GRANT)
    public R<Void> insertAuthRole(Long userId, Long[] roleIds) {
        userService.checkUserDataScope(userId);
        userService.insertUserAuth(userId, roleIds);
        return R.ok();
    }

    /**
     * 获取部门树列表
     */
    @Get
    @Mapping("/deptTree" )
    @SaCheckPermission("system:user:list" )
    public R<List<MapTree<Long>>> deptTree(SysDeptBo dept) {
        return R.ok(deptService.selectDeptTreeList(dept));
    }

    /**
     * 获取部门下的所有用户信息
     */
    @Get
    @Mapping("/list/dept/{deptId}" )
    @SaCheckPermission("system:user:list" )
    public R<List<SysUserVo>> listByDept(@NotNull Long deptId) {
        return R.ok(userService.selectUserListByDept(deptId));
    }
}
