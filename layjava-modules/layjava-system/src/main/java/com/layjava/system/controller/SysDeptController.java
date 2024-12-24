package com.layjava.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.layjava.common.core.constant.UserConstants;
import com.layjava.common.core.domain.R;
import com.layjava.common.core.utils.StringUtil;
import com.layjava.common.log.annotation.Log;
import com.layjava.common.log.enums.BusinessType;
import com.layjava.common.web.core.BaseController;
import com.layjava.system.domain.bo.SysDeptBo;
import com.layjava.system.domain.vo.SysDeptVo;
import com.layjava.system.service.ISysDeptService;
import org.dromara.hutool.core.convert.ConvertUtil;
import org.noear.solon.annotation.*;

import java.util.List;

/**
 * 部门信息
 *
 * @author Lion Li
 */
@Controller
@Mapping("/system/dept" )
public class SysDeptController extends BaseController {

    @Inject
    private ISysDeptService deptService;

    /**
     * 获取部门列表
     */
    @SaCheckPermission("system:dept:list" )
    @Get
    @Mapping("/list" )
    public R<List<SysDeptVo>> list(SysDeptBo dept) {
        List<SysDeptVo> depts = deptService.selectDeptList(dept);
        return R.ok(depts);
    }

    /**
     * 查询部门列表（排除节点）
     *
     * @param deptId 部门ID
     */
    @SaCheckPermission("system:dept:list" )
    @Get
    @Mapping("/list/exclude/{deptId}" ) // @PathVariable(value = "deptId", required = false)
    public R<List<SysDeptVo>> excludeChild(Long deptId) {
        List<SysDeptVo> depts = deptService.selectDeptList(new SysDeptBo());
        depts.removeIf(d -> d.getDeptId().equals(deptId)
                || StringUtil.splitList(d.getAncestors()).contains(ConvertUtil.toStr(deptId)));
        return R.ok(depts);
    }

    /**
     * 根据部门编号获取详细信息
     *
     * @param deptId 部门ID
     */
    @SaCheckPermission("system:dept:query" )
    @Get
    @Mapping(value = "/{deptId}" )
    public R<SysDeptVo> getInfo(Long deptId) {
        deptService.checkDeptDataScope(deptId);
        return R.ok(deptService.selectDeptById(deptId));
    }

    /**
     * 新增部门
     */
    @SaCheckPermission("system:dept:add" )
    @Log(title = "部门管理", businessType = BusinessType.INSERT)
    @Post
    @Mapping
    public R<Void> add(SysDeptBo dept) {
        if (!deptService.checkDeptNameUnique(dept)) {
            return R.fail("新增部门'" + dept.getDeptName() + "'失败，部门名称已存在" );
        }
        return toAjax(deptService.insertDept(dept));
    }

    /**
     * 修改部门
     */
    @SaCheckPermission("system:dept:edit" )
    @Log(title = "部门管理", businessType = BusinessType.UPDATE)
    @Put
    @Mapping
    public R<Void> edit(SysDeptBo dept) {
        Long deptId = dept.getDeptId();
        deptService.checkDeptDataScope(deptId);
        if (!deptService.checkDeptNameUnique(dept)) {
            return R.fail("修改部门'" + dept.getDeptName() + "'失败，部门名称已存在" );
        } else if (dept.getParentId().equals(deptId)) {
            return R.fail("修改部门'" + dept.getDeptName() + "'失败，上级部门不能是自己" );
        } else if (StringUtil.equals(UserConstants.DEPT_DISABLE, dept.getStatus())) {
            if (deptService.selectNormalChildrenDeptById(deptId) > 0) {
                return R.fail("该部门包含未停用的子部门!" );
            } else if (deptService.checkDeptExistUser(deptId)) {
                return R.fail("该部门下存在已分配用户，不能禁用!" );
            }
        }
        return toAjax(deptService.updateDept(dept));
    }

    /**
     * 删除部门
     *
     * @param deptId 部门ID
     */
    @SaCheckPermission("system:dept:remove" )
    @Log(title = "部门管理", businessType = BusinessType.DELETE)
    @Delete
    @Mapping("/{deptId}" )
    public R<Void> remove(Long deptId) {
        if (deptService.hasChildByDeptId(deptId)) {
            return R.warn("存在下级部门,不允许删除" );
        }
        if (deptService.checkDeptExistUser(deptId)) {
            return R.warn("部门存在用户,不允许删除" );
        }
        deptService.checkDeptDataScope(deptId);
        return toAjax(deptService.deleteDeptById(deptId));
    }
}
