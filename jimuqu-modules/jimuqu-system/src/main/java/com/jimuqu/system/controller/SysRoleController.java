package com.jimuqu.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.jimuqu.common.core.checker.Assert;
import com.jimuqu.common.core.domain.R;
import com.jimuqu.common.core.validate.group.AddGroup;
import com.jimuqu.common.core.validate.group.UpdateGroup;
import com.jimuqu.common.log.annotation.Log;
import com.jimuqu.common.log.enums.BusinessType;
import com.jimuqu.common.mybatis.core.Page;
import com.jimuqu.common.mybatis.core.page.PageQuery;
import com.jimuqu.common.web.core.BaseController;
import com.jimuqu.system.domain.SysUserRole;
import com.jimuqu.system.domain.bo.SysRoleBo;
import com.jimuqu.system.domain.bo.SysUserBo;
import com.jimuqu.system.domain.query.SysDeptQuery;
import com.jimuqu.system.domain.query.SysRoleQuery;
import com.jimuqu.system.domain.vo.DeptTreeSelectVo;
import com.jimuqu.system.domain.vo.SysRoleVo;
import com.jimuqu.system.domain.vo.SysUserVo;
import com.jimuqu.system.service.SysDeptService;
import com.jimuqu.system.service.SysRoleService;
import com.jimuqu.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.noear.solon.annotation.*;
import org.noear.solon.validation.annotation.NoRepeatSubmit;
import org.noear.solon.validation.annotation.NotEmpty;
import org.noear.solon.validation.annotation.NotNull;
import org.noear.solon.validation.annotation.Validated;

import java.util.List;

/**
 * 角色信息 Controller
 *
 * @author chengliang4810
 * @since 2025-06-05
 */
@Post
@Controller
@RequiredArgsConstructor
@Mapping("/system/role")
public class SysRoleController extends BaseController {

    private final SysUserService userService;
    private final SysDeptService deptService;
    private final SysRoleService sysRoleService;

    /**
     * 查询角色信息列表
     */
    @Get
    @Mapping("/list")
    @SaCheckPermission("system:role:list")
    public Page<SysRoleVo> list(SysRoleQuery query, PageQuery pageQuery) {
        return sysRoleService.queryPageList(query, pageQuery);
    }

    /**
     * 获取角色信息详细信息
     *
     * @param roleId 角色信息主键
     */
    @Get
    @Mapping("/{roleId}")
    @SaCheckPermission("system:role:query")
    public SysRoleVo getInfo(@NotNull(message = "角色信息主键不能为空") Long roleId) {
        sysRoleService.checkRoleDataScope(roleId);
        return sysRoleService.queryById(roleId);
    }

    /**
     * 获取角色选择框列表
     */
    @SaCheckPermission("system:role:query")
    @Get
    @Mapping("/optionselect")
    public R<List<SysRoleVo>> optionselect() {
        return R.ok(sysRoleService.selectRoleAll());
    }

    /**
     * 查询已分配用户角色列表
     */
    @SaCheckPermission("system:role:list")
    @Get
    @Mapping("/authUser/allocatedList")
    public Page<SysUserVo> allocatedList(SysUserBo user, PageQuery pageQuery) {
        return userService.selectAllocatedList(user, pageQuery);
    }

    /**
     * 查询未分配用户角色列表
     */
    @SaCheckPermission("system:role:list")
    @Get
    @Mapping("/authUser/unallocatedList")
    public Page<SysUserVo> unallocatedList(SysUserBo user, PageQuery pageQuery) {
        return userService.selectUnallocatedList(user, pageQuery);
    }

    /**
     * 新增角色信息
     */
    @Mapping("/add")
    @NoRepeatSubmit
    @SaCheckPermission("system:role:add")
    @Log(title = "新增角色信息", businessType = BusinessType.ADD)
    public R<Long> add(@Validated(AddGroup.class) SysRoleBo role) {
        sysRoleService.checkRoleAllowed(role);
        if (!sysRoleService.checkRoleNameUnique(role)) {
            return R.fail("新增角色'" + role.getRoleName() + "'失败，角色名称已存在");
        } else if (!sysRoleService.checkRoleKeyUnique(role)) {
            return R.fail("新增角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        boolean result = sysRoleService.insertByBo(role);
        Assert.isTrue(result, "新增角色信息失败");
        return R.ok(role.getId());
    }

    /**
     * 更新角色信息
     */
    @NoRepeatSubmit
    @Mapping("/update")
    @SaCheckPermission("system:role:update")
    @Log(title = "更新角色信息", businessType = BusinessType.UPDATE)
    public R<Void> edit(@Validated(UpdateGroup.class) SysRoleBo role) {
        sysRoleService.checkRoleAllowed(role);
        sysRoleService.checkRoleDataScope(role.getId());
        if (!sysRoleService.checkRoleNameUnique(role)) {
            return R.fail("修改角色'" + role.getRoleName() + "'失败，角色名称已存在");
        } else if (!sysRoleService.checkRoleKeyUnique(role)) {
            return R.fail("修改角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }

        if (sysRoleService.updateByBo(role)) {
            sysRoleService.cleanOnlineUserByRole(role.getId());
            return R.ok();
        }
        return R.fail("修改角色'" + role.getRoleName() + "'失败，请联系管理员");
    }

    /**
     * 取消授权用户
     */
    @Mapping("/authUser/cancel")
    @SaCheckPermission("system:role:edit")
    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    public R<Void> cancelAuthUser(SysUserRole userRole) {
        return toAjax(sysRoleService.deleteAuthUser(userRole));
    }

    /**
     * 修改保存数据权限
     */
    @Mapping("/dataScope")
    @SaCheckPermission("system:role:edit")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    public R<Void> dataScope(SysRoleBo role) {
        sysRoleService.checkRoleAllowed(role);
        sysRoleService.checkRoleDataScope(role.getId());
        return toAjax(sysRoleService.authDataScope(role));
    }

    /**
     * 状态修改
     */
    @Mapping("/changeStatus")
    @SaCheckPermission("system:role:edit")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    public R<Void> changeStatus(SysRoleBo role) {
        sysRoleService.checkRoleAllowed(role);
        sysRoleService.checkRoleDataScope(role.getId());
        return toAjax(sysRoleService.updateRoleStatus(role.getId(), role.getStatus()));
    }

    /**
     * 批量取消授权用户
     *
     * @param roleId  角色ID
     * @param userIds 用户ID串
     */
    @Mapping("/authUser/cancelAll")
    @SaCheckPermission("system:role:edit")
    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    public R<Void> cancelAuthUserAll(Long roleId, Long[] userIds) {
        return toAjax(sysRoleService.deleteAuthUsers(roleId, userIds));
    }

    /**
     * 批量选择用户授权
     *
     * @param roleId  角色ID
     * @param userIds 用户ID串
     */
    @Mapping("/authUser/selectAll")
    @SaCheckPermission("system:role:edit")
    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    public R<Void> selectAuthUserAll(Long roleId, Long[] userIds) {
        sysRoleService.checkRoleDataScope(roleId);
        return toAjax(sysRoleService.insertAuthUsers(roleId, userIds));
    }

    /**
     * 删除角色信息
     */
    @Mapping("/delete/{ids}")
    @SaCheckPermission("system:role:delete")
    @Log(title = "删除角色信息", businessType = BusinessType.DELETE)
    public Integer delete(@NotEmpty(message = "主键不能为空") List<Long> ids) {
        Integer num = sysRoleService.deleteByIds(ids);
        Assert.gtZero(num, "删除角色信息失败");
        return num;
    }

    /**
     * 获取对应角色部门树列表
     *
     * @param roleId 角色ID
     */
    @Get
    @SaCheckPermission("system:role:list")
    @Mapping(value = "/dept/tree/{roleId}")
    public R<DeptTreeSelectVo> roleDeptTreeSelect(Long roleId) {
        DeptTreeSelectVo selectVo = new DeptTreeSelectVo();
        selectVo.setCheckedKeys(deptService.selectDeptListByRoleId(roleId));
        selectVo.setDepts(deptService.selectDeptTreeList(new SysDeptQuery()));
        return R.ok(selectVo);
    }
}
