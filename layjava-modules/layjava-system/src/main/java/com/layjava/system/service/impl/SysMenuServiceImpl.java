package com.layjava.system.service.impl;
import com.layjava.common.core.constant.Constants;
import com.layjava.common.core.constant.UserConstants;
import com.layjava.system.domain.SysRole;
import java.util.Map;
import java.time.LocalDateTime;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.NumberUtil;
import com.easy.query.api.proxy.client.EasyEntityQuery;
import com.easy.query.api.proxy.entity.select.EntityQueryable;
import com.easy.query.solon.annotation.Db;
import com.layjava.common.security.utils.LoginHelper;
import com.layjava.system.domain.SysMenu;
import com.layjava.system.domain.bo.SysMenuBo;
import com.layjava.system.domain.proxy.SysMenuProxy;
import com.layjava.system.domain.vo.RouterVo;
import com.layjava.system.domain.vo.SysMenuVo;
import com.layjava.system.service.SysMenuService;
import org.noear.solon.annotation.Component;

import javax.swing.text.html.parser.Entity;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 菜单 业务层处理
 *
 * @author Lion Li
 */
@Component
public class SysMenuServiceImpl implements SysMenuService {

    @Db
    private EasyEntityQuery entityQuery;

    /**
     * 根据用户查询系统菜单列表
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    @Override
    public List<SysMenuVo> selectMenuList(Long userId) {
        return List.of();
    }

    /**
     * 根据用户查询系统菜单列表
     *
     * @param menu   菜单信息
     * @param userId 用户ID
     * @return 菜单列表
     */
    @Override
    public List<SysMenuVo> selectMenuList(SysMenuBo menu, Long userId) {
        return List.of();
    }

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectMenuPermsByUserId(Long userId) {
        return Set.of();
    }

    /**
     * 根据角色ID查询权限
     *
     * @param roleId 角色ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectMenuPermsByRoleId(Long roleId) {
        return Set.of();
    }

    /**
     * 根据用户ID查询菜单树信息
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    @Override
    public List<SysMenu> selectMenuTreeByUserId(Long userId) {
        if (LoginHelper.isSuperAdmin(userId)){
            return entityQuery.queryable(SysMenu.class).toList();
        }

        return entityQuery.queryable(SysMenu.class)
                .where(s -> {
                    s.status().eq("0");
                    //判断菜单下的角色存在角色的用户叫做小明的
                    s.roles().flatElement().users().flatElement().userId().eq(userId);//如果只有一个条件name可以这么写
                }).toList();
    }

    /**
     * 根据角色ID查询菜单树信息
     *
     * @param roleId 角色ID
     * @return 选中菜单列表
     */
    @Override
    public List<Long> selectMenuListByRoleId(Long roleId) {
        return List.of();
    }

    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    @Override
    public List<RouterVo> buildMenus(List<SysMenu> menus) {
        if (CollUtil.isEmpty(menus)){
            return CollUtil.newArrayList();
        }

        return menus.stream()
                // 排序
                .sorted((menu, menu2) -> NumberUtil.compare(menu.getOrderNum(), menu2.getOrderNum()))
                // 构建前端路由对象
                .map(menu -> {
                    RouterVo router = new RouterVo();
                    router.setId(menu.getMenuId());
                    router.setPid(menu.getParentId());
                    router.setName(menu.getRouteName());
                    router.setTitle(menu.getMenuName());
                    router.setHide("1".equals(menu.getVisible()));
                    router.setKeepAlive("0".equals(menu.getIsCache()));
                    router.setPath(menu.getRouterPath());
                    router.setMenuType("C".equals(menu.getMenuType()) ? "page" : "dir");
                    router.setIcon(menu.getIcon());
                    router.setOrder(menu.getOrderNum());
                    router.setRequiresAuth(true);
                    // router.setHref(menu.getRouterPath());
                    router.setComponentPath(menu.getComponent());
                    return router;
                }).toList();
    }

    /**
     * 构建前端所需要下拉树结构
     *
     * @param menus 菜单列表
     * @return 下拉树结构列表
     */
    @Override
    public List<Tree<Long>> buildMenuTreeSelect(List<SysMenuVo> menus) {
        return List.of();
    }

    /**
     * 根据菜单ID查询信息
     *
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    @Override
    public SysMenuVo selectMenuById(Long menuId) {
        return null;
    }

    /**
     * 是否存在菜单子节点
     *
     * @param menuId 菜单ID
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean hasChildByMenuId(Long menuId) {
        return false;
    }

    /**
     * 查询菜单是否存在角色
     *
     * @param menuId 菜单ID
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean checkMenuExistRole(Long menuId) {
        return false;
    }

    /**
     * 新增保存菜单信息
     *
     * @param bo 菜单信息
     * @return 结果
     */
    @Override
    public int insertMenu(SysMenuBo bo) {
        return 0;
    }

    /**
     * 修改保存菜单信息
     *
     * @param bo 菜单信息
     * @return 结果
     */
    @Override
    public int updateMenu(SysMenuBo bo) {
        return 0;
    }

    /**
     * 删除菜单管理信息
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public int deleteMenuById(Long menuId) {
        return 0;
    }

    /**
     * 校验菜单名称是否唯一
     *
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public boolean checkMenuNameUnique(SysMenuBo menu) {
        return false;
    }
}
