package com.layjava.system.service.impl;

import com.layjava.system.domain.SysMenu;
import com.layjava.system.domain.bo.SysMenuBo;
import com.layjava.system.domain.vo.RouterVo;
import com.layjava.system.domain.vo.SysMenuVo;
import com.layjava.system.service.ISysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.tree.MapTree;
import org.noear.solon.annotation.Component;

import java.util.List;
import java.util.Set;

@Slf4j
@Component
public class SysMenuServiceImpl implements ISysMenuService {

    @Override
    public List<SysMenuVo> selectMenuList(Long userId) {
        return List.of();
    }

    @Override
    public List<SysMenuVo> selectMenuList(SysMenuBo menu, Long userId) {
        return List.of();
    }

    @Override
    public Set<String> selectMenuPermsByUserId(Long userId) {
        return Set.of();
    }

    @Override
    public Set<String> selectMenuPermsByRoleId(Long roleId) {
        return Set.of();
    }

    @Override
    public List<SysMenu> selectMenuTreeByUserId(Long userId) {
        return List.of();
    }

    @Override
    public List<SysMenu> selectMenuByUserId(Long userId) {
        return List.of();
    }

    @Override
    public List<Long> selectMenuListByRoleId(Long roleId) {
        return List.of();
    }

    @Override
    public List<RouterVo> buildMenus(List<SysMenu> menus) {
        return List.of();
    }

    @Override
    public List<MapTree<Long>> buildMenuTreeSelect(List<SysMenuVo> menus) {
        return List.of();
    }

    @Override
    public SysMenuVo selectMenuById(Long menuId) {
        return null;
    }

    @Override
    public boolean hasChildByMenuId(Long menuId) {
        return false;
    }

    @Override
    public boolean checkMenuExistRole(Long menuId) {
        return false;
    }

    @Override
    public int insertMenu(SysMenuBo bo) {
        return 0;
    }

    @Override
    public int updateMenu(SysMenuBo bo) {
        return 0;
    }

    @Override
    public int deleteMenuById(Long menuId) {
        return 0;
    }

    @Override
    public boolean checkMenuNameUnique(SysMenuBo menu) {
        return false;
    }
}
