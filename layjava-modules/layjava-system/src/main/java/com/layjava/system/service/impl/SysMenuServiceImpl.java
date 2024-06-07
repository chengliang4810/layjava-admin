package com.layjava.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.layjava.common.core.constant.UserConstants;
import com.layjava.common.core.util.MapstructUtils;
import com.layjava.common.core.util.StreamUtils;
import com.layjava.common.core.util.StringUtils;
import com.layjava.common.core.util.TreeBuildUtils;
import com.layjava.common.security.utils.LoginHelper;
import com.layjava.system.domain.SysMenu;
import com.layjava.system.domain.SysRole;
import com.layjava.system.domain.SysRoleMenu;
import com.layjava.system.domain.bo.SysMenuBo;
import com.layjava.system.domain.vo.MetaVo;
import com.layjava.system.domain.vo.RouterVo;
import com.layjava.system.domain.vo.SysMenuVo;
import com.layjava.system.mapper.SysMenuMapper;
import com.layjava.system.mapper.SysRoleMapper;
import com.layjava.system.mapper.SysRoleMenuMapper;
import com.layjava.system.service.SysMenuService;

import org.apache.ibatis.solon.annotation.Db;
import org.noear.solon.annotation.Component;

import java.util.*;

/**
 * 菜单 业务层处理
 *
 * @author Lion Li
 */
@Component
public class SysMenuServiceImpl implements SysMenuService {

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
        List<SysMenuVo> menuList = new ArrayList<>();
        // 管理员显示所有菜单信息
        if (LoginHelper.isSuperAdmin(userId)) {
            menuList = baseMapper.selectVoList(new LambdaQueryWrapper<SysMenu>()
                .like(StringUtils.isNotBlank(menu.getMenuName()), SysMenu::getMenuName, menu.getMenuName())
                .eq(StringUtils.isNotBlank(menu.getVisible()), SysMenu::getVisible, menu.getVisible())
                .eq(StringUtils.isNotBlank(menu.getStatus()), SysMenu::getStatus, menu.getStatus())
                .orderByAsc(SysMenu::getParentId)
                .orderByAsc(SysMenu::getOrderNum));
        } else {
            QueryWrapper<SysMenu> wrapper = Wrappers.query();
            wrapper.eq("sur.user_id", userId)
                .like(StringUtils.isNotBlank(menu.getMenuName()), "m.menu_name", menu.getMenuName())
                .eq(StringUtils.isNotBlank(menu.getVisible()), "m.visible", menu.getVisible())
                .eq(StringUtils.isNotBlank(menu.getStatus()), "m.status", menu.getStatus())
                .orderByAsc("m.parent_id")
                .orderByAsc("m.order_num");
//            List<SysMenu> list = baseMapper.selectMenuListByUserId(wrapper);
//            menuList = MapstructUtils.convert(list, SysMenuVo.class);
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
//        List<String> perms = baseMapper.selectMenuPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
//        for (String perm : perms) {
//            if (StringUtils.isNotEmpty(perm)) {
//                permsSet.addAll(StringUtils.splitList(perm.trim()));
//            }
//        }
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
        // List<String> perms = baseMapper.selectMenuPermsByRoleId(roleId);
        Set<String> permsSet = new HashSet<>();
//        for (String perm : perms) {
//            if (StringUtils.isNotEmpty(perm)) {
//                permsSet.addAll(StringUtils.splitList(perm.trim()));
//            }
//        }
        return permsSet;
    }

    /**
     * 根据用户ID查询菜单
     *
     * @param userId 用户名称
     * @return 菜单列表
     */
    @Override
    public List<SysMenu> selectMenuTreeByUserId(Long userId) {
        List<SysMenu> menus = new ArrayList<>();
        if (LoginHelper.isSuperAdmin(userId)) {
            menus = baseMapper.selectMenuTreeAll();
        } else {
            // menus = baseMapper.selectMenuTreeByUserId(userId);
        }
        return menus;
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
//        SysRole role = roleMapper.selectById(roleId);
//        return baseMapper.selectMenuListByRoleId(roleId, role.getMenuCheckStrictly());
    }

    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    @Override
    public List<RouterVo> buildMenus(List<SysMenu> menus) {
        List<RouterVo> routers = new LinkedList<>();
        for (SysMenu menu : menus) {
            RouterVo router = new RouterVo();
            router.setName(menu.getRouteName());
            router.setPath(menu.getRouterPath());
            router.setComponentPath(menu.getComponentInfo());
            router.setTitle(menu.getMenuName());
            router.setIcon(menu.getIcon());

            // 表达父子关系
            router.setId(menu.getMenuId());
            router.setPid(menu.getParentId());
            // 是否隐藏
            router.setHide("1".equals(menu.getVisible()));
            router.setKeepAlive(StrUtil.equals("1", menu.getIsCache()));
            router.setOrder(menu.getOrderNum());
            router.setComponentPath(menu.getRouterPath());
            router.setMenuType(UserConstants.TYPE_DIR.equals(menu.getMenuType()) ? "dir" : "page");
            // 是否校验权限
            router.setRequiresAuth(true);
            // 外链url
            router.setHref("");
            routers.add(router);
        }
        return routers;
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
        return baseMapper.selectVoById(menuId);
    }

    /**
     * 是否存在菜单子节点
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public boolean hasChildByMenuId(Long menuId) {
        return baseMapper.exists(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getParentId, menuId));
    }

    /**
     * 查询菜单使用数量
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public boolean checkMenuExistRole(Long menuId) {
        return roleMenuMapper.exists(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getMenuId, menuId));
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
        return baseMapper.insert(menu);
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
        return baseMapper.updateById(menu);
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
        boolean exist = baseMapper.exists(new LambdaQueryWrapper<SysMenu>()
            .eq(SysMenu::getMenuName, menu.getMenuName())
            .eq(SysMenu::getParentId, menu.getParentId())
            .ne(ObjectUtil.isNotNull(menu.getMenuId()), SysMenu::getMenuId, menu.getMenuId()));
        return !exist;
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
