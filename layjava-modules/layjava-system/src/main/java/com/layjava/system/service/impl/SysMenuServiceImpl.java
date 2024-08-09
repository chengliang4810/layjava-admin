package com.layjava.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import com.layjava.common.core.constant.UserConstants;
import com.layjava.common.core.utils.MapstructUtils;
import com.layjava.common.core.utils.StreamUtils;
import com.layjava.common.core.utils.StringUtils;
import com.layjava.common.core.utils.TreeBuildUtils;
import com.layjava.common.satoken.utils.LoginHelper;
import com.layjava.system.domain.SysMenu;
import com.layjava.system.domain.SysRole;
import com.layjava.system.domain.bo.SysMenuBo;
import com.layjava.system.domain.vo.RouterVo;
import com.layjava.system.domain.vo.SysMenuVo;
import com.layjava.system.mapper.SysMenuMapper;
import com.layjava.system.mapper.SysRoleMapper;
import com.layjava.system.mapper.SysRoleMenuMapper;
import com.layjava.system.service.ISysMenuService;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.solon.annotation.Db;
import org.noear.solon.annotation.Component;

import java.util.*;

import static com.layjava.system.domain.table.SysMenuTableDef.SYS_MENU;
import static com.layjava.system.domain.table.SysRoleMenuTableDef.SYS_ROLE_MENU;
import static com.layjava.system.domain.table.SysRoleTableDef.SYS_ROLE;
import static com.layjava.system.domain.table.SysUserRoleTableDef.SYS_USER_ROLE;
import static com.mybatisflex.core.query.QueryMethods.distinct;

/**
 * 菜单 业务层处理
 *
 * @author Lion Li
 */
@Slf4j
@Component
public class SysMenuServiceImpl implements ISysMenuService {

    @Db
    private SysMenuMapper baseMapper;
    @Db
    private SysRoleMapper roleMapper;
    @Db
    private SysRoleMenuMapper roleMenuMapper;

    /**
     * 根据用户查询系统菜单列表
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    @Override
    public List<SysMenuVo> selectMenuList(Long userId) {
        return selectMenuList(new SysMenuBo(), userId);
    }

    /**
     * 查询系统菜单列表
     *
     * @param menu 菜单信息
     * @return 菜单列表
     */
    @Override
    public List<SysMenuVo> selectMenuList(SysMenuBo menu, Long userId) {
        List<SysMenuVo> menuList;
        // 管理员显示所有菜单信息
        if (LoginHelper.isSuperAdmin(userId)) {
            menuList = baseMapper.selectListByQueryAs(
                    QueryWrapper.create().from(SYS_MENU)
                            .where(SYS_MENU.MENU_NAME.like(menu.getMenuName()))
                            .and(SYS_MENU.VISIBLE.eq(menu.getVisible()))
                            .and(SYS_MENU.STATUS.eq(menu.getStatus()))
                            .orderBy(SYS_MENU.PARENT_ID, true)
                            .orderBy(SYS_MENU.ORDER_NUM, true),
                    SysMenuVo.class
            );
        } else {
            QueryWrapper queryWrapper = QueryWrapper.create()
                    .select(distinct(SYS_MENU.ALL_COLUMNS))
                    .from(SYS_MENU)
                    .leftJoin(SYS_ROLE_MENU).on(SYS_MENU.MENU_ID.eq(SYS_ROLE_MENU.MENU_ID))
                    .leftJoin(SYS_USER_ROLE).on(SYS_ROLE_MENU.ROLE_ID.eq(SYS_USER_ROLE.ROLE_ID))
                    .leftJoin(SYS_ROLE).on(SYS_USER_ROLE.ROLE_ID.eq(SYS_ROLE.ROLE_ID))
                    .where(SYS_USER_ROLE.USER_ID.eq(userId))
                    .and(SYS_MENU.MENU_NAME.like(menu.getMenuName()))
                    .and(SYS_MENU.VISIBLE.like(menu.getVisible()))
                    .and(SYS_MENU.STATUS.like(menu.getStatus()))
                    .orderBy(SYS_MENU.PARENT_ID, true)
                    .orderBy(SYS_MENU.ORDER_NUM, true);

            List<SysMenu> list = baseMapper.selectListByQueryAs(queryWrapper, SysMenu.class);
            menuList = MapstructUtils.convert(list, SysMenuVo.class);
        }
        return menuList;
    }

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectMenuPermsByUserId(Long userId) {
        List<String> perms = baseMapper.selectMenuPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotEmpty(perm)) {
                permsSet.addAll(StringUtils.splitList(perm.trim()));
            }
        }
        return permsSet;
    }

    /**
     * 根据角色ID查询权限
     *
     * @param roleId 角色ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectMenuPermsByRoleId(Long roleId) {
        List<String> perms = baseMapper.selectMenuPermsByRoleId(roleId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotEmpty(perm)) {
                permsSet.addAll(StringUtils.splitList(perm.trim()));
            }
        }
        return permsSet;
    }

    /**
     * 根据用户ID查询菜单
     *
     * @param userId 用户名称
     * @return 菜单列表
     */
    @Override
    public List<SysMenu> selectMenuByUserId(Long userId) {
        List<SysMenu> menus;
        if (LoginHelper.isSuperAdmin(userId)) {
            menus = baseMapper.selectMenuTreeAll();
        } else {
            menus = baseMapper.selectMenuTreeByUserId(userId);
        }
        return menus;
    }

    /**
     * 根据用户ID查询菜单
     *
     * @param userId 用户名称
     * @return 菜单列表
     */
    @Override
    public List<SysMenu> selectMenuTreeByUserId(Long userId) {
        List<SysMenu> sysMenus = this.selectMenuByUserId(userId);
        return getChildPerms(sysMenus, 0);
    }

    /**
     * 根据角色ID查询菜单树信息
     *
     * @param roleId 角色ID
     * @return 选中菜单列表
     */
    @Override
    public List<Long> selectMenuListByRoleId(Long roleId) {
        SysRole role = roleMapper.selectOneById(roleId);
        return baseMapper.selectMenuListByRoleId(roleId, role.getMenuCheckStrictly());
    }

    /**
     * 构建前端路由所需要的菜单路由
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    @Override
    public List<RouterVo> buildMenus(List<SysMenu> menus) {
        if (CollUtil.isEmpty(menus)){
            return Collections.emptyList();
        }

        return menus.stream().map(menu -> {
            RouterVo router = new RouterVo();

            router.setId(menu.getMenuId());
            router.setPid(menu.getParentId());

            router.setComponentPath(menu.getComponent());
            // 后端 M目录 C菜单  前端 dir page
            router.setMenuType("M".equals(menu.getMenuType()) ? "dir" : "page");
            router.setName(menu.getRouterName());
            if (UserConstants.NO_FRAME.equals(menu.getIsFrame())){
                router.setPath(menu.getPath());
            } else {
                router.setHref(menu.getPath());
            }
            // 固定所有菜单都需要权限
            router.setRequiresAuth(menu.getRequiresAuth());
            router.setTitle(menu.getMenuName());
            router.setIcon(menu.getIcon());
            router.setPinTab(menu.getPinTab());
            router.setKeepAlive("0".equals(menu.getIsCache()));
            router.setHide("1".equals(menu.getVisible()));
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
        if (CollUtil.isEmpty(menus)) {
            return CollUtil.newArrayList();
        }
        return TreeBuildUtils.build(menus, (menu, tree) ->
                tree.setId(menu.getMenuId())
                        .setParentId(menu.getParentId())
                        .setName(menu.getMenuName())
                        .setWeight(menu.getOrderNum()));
    }

    /**
     * 根据菜单ID查询信息
     *
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    @Override
    public SysMenuVo selectMenuById(Long menuId) {
        return baseMapper.selectOneWithRelationsByIdAs(menuId, SysMenuVo.class);
    }

    /**
     * 是否存在菜单子节点
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public boolean hasChildByMenuId(Long menuId) {
        return baseMapper.selectCountByQuery(QueryWrapper.create().from(SYS_MENU).where(SYS_MENU.PARENT_ID.eq(menuId))) > 0;
    }

    /**
     * 查询菜单使用数量
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public boolean checkMenuExistRole(Long menuId) {
        return baseMapper.selectCountByQuery(QueryWrapper.create().from(SYS_ROLE_MENU).where(SYS_ROLE_MENU.MENU_ID.eq(menuId))) > 0;
    }

    /**
     * 新增保存菜单信息
     *
     * @param bo 菜单信息
     * @return 结果
     */
    @Override
    public int insertMenu(SysMenuBo bo) {
        SysMenu menu = MapstructUtils.convert(bo, SysMenu.class);
        return baseMapper.insert(menu, true);
    }

    /**
     * 修改保存菜单信息
     *
     * @param bo 菜单信息
     * @return 结果
     */
    @Override
    public int updateMenu(SysMenuBo bo) {
        SysMenu menu = MapstructUtils.convert(bo, SysMenu.class);
        return baseMapper.update(menu);
    }

    /**
     * 删除菜单管理信息
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public int deleteMenuById(Long menuId) {
        return baseMapper.deleteById(menuId);
    }

    /**
     * 校验菜单名称是否唯一
     *
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public boolean checkMenuNameUnique(SysMenuBo menu) {
        return baseMapper.selectCountByQuery(QueryWrapper.create().from(SYS_MENU).where(SYS_MENU.MENU_NAME.eq(menu.getMenuName()))
                .and(SYS_MENU.PARENT_ID.eq(menu.getParentId()))
                .and(SYS_MENU.MENU_ID.ne(menu.getMenuId()))) == 0;
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list     分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    private List<SysMenu> getChildPerms(List<SysMenu> list, int parentId) {
        List<SysMenu> returnList = new ArrayList<>();
        for (SysMenu t : list) {
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == parentId) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<SysMenu> list, SysMenu t) {
        // 得到子节点列表
        List<SysMenu> childList = StreamUtils.filter(list, n -> n.getParentId().equals(t.getMenuId()));
        t.setChildren(childList);
        for (SysMenu tChild : childList) {
            // 判断是否有子节点
            if (list.stream().anyMatch(n -> n.getParentId().equals(tChild.getMenuId()))) {
                recursionFn(list, tChild);
            }
        }
    }

}
