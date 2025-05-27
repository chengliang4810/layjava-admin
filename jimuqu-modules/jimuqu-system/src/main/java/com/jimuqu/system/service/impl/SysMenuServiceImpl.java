package com.jimuqu.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.jimuqu.common.core.constant.UserConstants;
import com.jimuqu.common.core.utils.StreamUtil;
import com.jimuqu.common.satoken.utils.LoginHelper;
import com.jimuqu.system.domain.SysMenu;
import com.jimuqu.system.domain.bo.SysMenuBo;
import com.jimuqu.system.domain.vo.MetaVo;
import com.jimuqu.system.domain.vo.RouterVo;
import com.jimuqu.system.domain.vo.SysMenuVo;
import com.jimuqu.system.mapper.SysMenuMapper;
import com.jimuqu.system.service.ISysMenuService;
import jodd.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.tree.MapTree;
import org.noear.solon.annotation.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class SysMenuServiceImpl implements ISysMenuService {

    private final SysMenuMapper menuMapper;

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
        List<RouterVo> routers = new LinkedList<>();
        for (SysMenu menu : menus) {
            RouterVo router = new RouterVo();
            router.setHidden("1".equals(menu.getVisible()));
            router.setName(menu.getRouteName());
            router.setPath(menu.getRouterPath());
            router.setComponent(menu.getComponentInfo());
            router.setQuery(menu.getQueryParam());
            router.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), StringUtil.equals("1", menu.getIsCache()), menu.getPath()));
            List<SysMenu> cMenus = menu.getChildren();
            if (CollUtil.isNotEmpty(cMenus) && UserConstants.TYPE_DIR.equals(menu.getMenuType())) {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            } else if (menu.isMenuFrame()) {
                router.setMeta(null);
                List<RouterVo> childrenList = new ArrayList<>();
                RouterVo children = new RouterVo();
                children.setPath(menu.getPath());
                children.setComponent(menu.getComponent());
                children.setName(StringUtil.capitalize(menu.getPath()));
                children.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), StringUtil.equals("1", menu.getIsCache()), menu.getPath()));
                children.setQuery(menu.getQueryParam());
                childrenList.add(children);
                router.setChildren(childrenList);
            } else if (menu.getParentId().intValue() == 0 && menu.isInnerLink()) {
                router.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon()));
                router.setPath("/");
                List<RouterVo> childrenList = new ArrayList<>();
                RouterVo children = new RouterVo();
                String routerPath = SysMenu.innerLinkReplaceEach(menu.getPath());
                children.setPath(routerPath);
                children.setComponent(UserConstants.INNER_LINK);
                children.setName(StringUtil.capitalize(routerPath));
                children.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), menu.getPath()));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
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
        List<SysMenu> childList = StreamUtil.filter(list, n -> n.getParentId().equals(t.getId()));
        t.setChildren(childList);
        for (SysMenu tChild : childList) {
            // 判断是否有子节点
            if (list.stream().anyMatch(n -> n.getParentId().equals(tChild.getId()))) {
                recursionFn(list, tChild);
            }
        }
    }
}
