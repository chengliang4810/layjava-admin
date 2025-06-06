package com.jimuqu.system.mapper;


import com.jimuqu.common.mybatis.core.mapper.BaseMapperPlus;
import com.jimuqu.system.domain.SysRoleMenu;

import java.util.List;

/**
 * 角色与菜单关联表 数据层
 *
 * @author Lion Li,chengliang4810
 */
public interface SysRoleMenuMapper extends BaseMapperPlus<SysRoleMenu, SysRoleMenu> {

    /**
     * 根据菜单ID删除角色和菜单关联
     *
     * @param menuIdList 菜单ID集合
     * @return 结果
     */
    default Integer deleteByMenuIds(List<Long> menuIdList){
        return this.delete(where -> where.in(SysRoleMenu::getMenuId, menuIdList));
    }
}
