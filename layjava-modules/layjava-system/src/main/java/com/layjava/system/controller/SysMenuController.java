package com.layjava.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import cn.hutool.core.lang.tree.Tree;
import com.layjava.common.core.constant.TenantConstants;
import com.layjava.common.core.constant.UserConstants;
import com.layjava.common.core.domain.R;
import com.layjava.common.core.utils.StringUtils;
import com.layjava.common.log.annotation.Log;
import com.layjava.common.log.enums.BusinessType;
import com.layjava.common.satoken.utils.LoginHelper;
import com.layjava.common.web.core.BaseController;
import com.layjava.system.domain.SysMenu;
import com.layjava.system.domain.bo.SysMenuBo;
import com.layjava.system.domain.vo.MenuTreeSelectVo;
import com.layjava.system.domain.vo.RouterVo;
import com.layjava.system.domain.vo.SysMenuVo;
import com.layjava.system.service.ISysMenuService;
import org.noear.solon.annotation.*;
import org.noear.solon.validation.annotation.Validated;

import java.util.List;

/**
 * 菜单信息
 *
 * @author Lion Li
 */
@Controller
@Mapping("/system/menu")
public class SysMenuController extends BaseController {

    @Inject
    private ISysMenuService menuService;

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @Get
    @Mapping("/routers")
    public R<List<RouterVo>> getRouters() {
        List<SysMenu> menus = menuService.selectMenuByUserId(LoginHelper.getUserId());
        return R.ok(menuService.buildMenus(menus));
    }

    /**
     * 获取菜单列表
     */
    @Get
    @Mapping("/list")
    @SaCheckPermission("system:menu:list")
    @SaCheckRole(value = TenantConstants.SUPER_ADMIN_ROLE_KEY)
    public R<List<SysMenuVo>> list(SysMenuBo menu) {
        List<SysMenuVo> menus = menuService.selectMenuList(menu, LoginHelper.getUserId());
        return R.ok(menus);
    }

    /**
     * 根据菜单编号获取详细信息
     *
     * @param menuId 菜单ID
     */
    @Get
    @Mapping(value = "/{menuId}")
    @SaCheckRole(value = {
            TenantConstants.SUPER_ADMIN_ROLE_KEY,
            TenantConstants.TENANT_ADMIN_ROLE_KEY
    }, mode = SaMode.OR)
    @SaCheckPermission("system:menu:query")
    public R<SysMenuVo> getInfo(Long menuId) {
        return R.ok(menuService.selectMenuById(menuId));
    }

    /**
     * 获取菜单下拉树列表
     */
    @Get
    @SaCheckPermission("system:menu:query")
    @Mapping("/treeselect")
    public R<List<Tree<Long>>> treeselect(SysMenuBo menu) {
        List<SysMenuVo> menus = menuService.selectMenuList(menu, LoginHelper.getUserId());
        return R.ok(menuService.buildMenuTreeSelect(menus));
    }

    /**
     * 加载对应角色菜单列表树
     *
     * @param roleId 角色ID
     */
    @Get
    @SaCheckPermission("system:menu:query")
    @Mapping(value = "/roleMenuTreeselect/{roleId}")
    public R<MenuTreeSelectVo> roleMenuTreeselect(Long roleId) {
        List<SysMenuVo> menus = menuService.selectMenuList(LoginHelper.getUserId());
        MenuTreeSelectVo selectVo = new MenuTreeSelectVo();
        selectVo.setCheckedKeys(menuService.selectMenuListByRoleId(roleId));
        selectVo.setMenus(menuService.buildMenuTreeSelect(menus));
        return R.ok(selectVo);
    }

    /**
     * 新增菜单
     */
    @Post
    @SaCheckRole(TenantConstants.SUPER_ADMIN_ROLE_KEY)
    @SaCheckPermission("system:menu:add")
    @Log(title = "菜单管理", businessType = BusinessType.INSERT)
    @Mapping
    public R<Void> add(@Validated SysMenuBo menu) {
        if (!menuService.checkMenuNameUnique(menu)) {
            return R.fail("新增菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        } else if (UserConstants.YES_FRAME.equals(menu.getIsFrame()) && !StringUtils.ishttp(menu.getPath())) {
            return R.fail("新增菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
        }
        return toAjax(menuService.insertMenu(menu));
    }

    /**
     * 修改菜单
     */
    @Put
    @Mapping
    @Log(title = "菜单管理", businessType = BusinessType.UPDATE)
    @SaCheckRole(TenantConstants.SUPER_ADMIN_ROLE_KEY)
    @SaCheckPermission("system:menu:edit")
    public R<Void> edit(@Validated SysMenuBo menu) {
        if (!menuService.checkMenuNameUnique(menu)) {
            return R.fail("修改菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        } else if (UserConstants.YES_FRAME.equals(menu.getIsFrame()) && !StringUtils.ishttp(menu.getPath())) {
            return R.fail("修改菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
        } else if (menu.getMenuId().equals(menu.getParentId())) {
            return R.fail("修改菜单'" + menu.getMenuName() + "'失败，上级菜单不能选择自己");
        }
        return toAjax(menuService.updateMenu(menu));
    }

    /**
     * 删除菜单
     *
     * @param menuId 菜单ID
     */
    @Delete
    @Mapping("/{menuId}")
    @SaCheckRole(TenantConstants.SUPER_ADMIN_ROLE_KEY)
    @SaCheckPermission("system:menu:remove")
    @Log(title = "菜单管理", businessType = BusinessType.DELETE)
    public R<Void> remove(Long menuId) {
        if (menuService.hasChildByMenuId(menuId)) {
            return R.warn("存在子菜单,不允许删除");
        }
        if (menuService.checkMenuExistRole(menuId)) {
            return R.warn("菜单已分配,不允许删除");
        }
        return toAjax(menuService.deleteMenuById(menuId));
    }

}
