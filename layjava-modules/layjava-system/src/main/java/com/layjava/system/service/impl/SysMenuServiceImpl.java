package com.layjava.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import com.layjava.common.core.util.MapstructUtils;
import com.layjava.common.security.utils.LoginHelper;
import com.layjava.system.domain.SysMenu;
import com.layjava.system.domain.bo.SysMenuBo;
import com.layjava.system.domain.vo.SysMenuVo;
import com.layjava.system.mapper.SysMenuMapper;
import com.layjava.system.service.SysMenuService;
import com.mybatisflex.core.query.QueryWrapper;
import org.apache.ibatis.solon.annotation.Db;
import org.noear.solon.annotation.Component;

import java.util.List;
import java.util.Set;

/**
 * 菜单 业务层处理
 *
 * @author Lion Li
 */
@Component
public class SysMenuServiceImpl implements SysMenuService {

    @Db
    private SysMenuMapper menuMapper;

    /**
     * 根据用户查询系统菜单列表
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    @Override
    public List<SysMenuVo> selectMenuList(Long userId) {
        if (LoginHelper.isSuperAdmin(userId)) {
            return menuMapper.selectListByQueryAs(QueryWrapper.create(), SysMenuVo.class);
        }
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
        if (LoginHelper.isSuperAdmin(userId)) {
            return menuMapper.selectListByQueryAs(QueryWrapper.create(), SysMenuVo.class);
        }
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
        if (LoginHelper.isSuperAdmin(userId)) {
            return menuMapper.selectAll();
        }

        return null;
//        return entityQuery.queryable(SysMenu.class)
//                .where(s -> {
//                    s.status().eq(true);
//                    //判断菜单下的角色存在角色的用户叫做小明的
//                    s.roles().flatElement().users().flatElement().userId().eq(userId);//如果只有一个条件name可以这么写
//                }).toList();
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
        SysMenu menu = MapstructUtils.convert(bo, SysMenu.class);
        return menuMapper.insertSelective(menu);
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
        return menuMapper.update(menu, true);
    }

    /**
     * 删除菜单管理信息
     *
     * @param menuIds 菜单ID
     * @return 结果
     */
    @Override
    public int deleteMenuById(List<String> menuIds) {
        if (CollUtil.isEmpty(menuIds)){
            return 0;
        }
        return menuMapper.deleteBatchByIds(menuIds);
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
