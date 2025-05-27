package com.jimuqu.system.service.impl;

import com.jimuqu.common.satoken.utils.LoginHelper;
import com.jimuqu.system.domain.SysMenu;
import com.jimuqu.system.domain.bo.SysMenuBo;
import com.jimuqu.system.domain.vo.RouterVo;
import com.jimuqu.system.domain.vo.SysMenuVo;
import com.jimuqu.system.mapper.SysMenuMapper;
import com.jimuqu.system.service.ISysMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.tree.MapTree;
import org.noear.solon.annotation.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class SysMenuServiceImpl implements ISysMenuService {

    private SysMenuMapper menuMapper;

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
        List<SysMenu> menus;
        if (LoginHelper.isSuperAdmin(userId)) {
            menus = menuMapper.selectMenuTreeAll();
        } else {
            menus = menuMapper.selectMenuTreeByUserId(userId);
        }
        return getChildPerms(menus, 0);
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
//        List<SysMenu> childList = StreamUtils.filter(list, n -> n.getParentId().equals(t.getMenuId()));
//        t.setChildren(childList);
//        for (SysMenu tChild : childList) {
//            // 判断是否有子节点
//            if (list.stream().anyMatch(n -> n.getParentId().equals(tChild.getMenuId()))) {
//                recursionFn(list, tChild);
//            }
//        }
    }
}
