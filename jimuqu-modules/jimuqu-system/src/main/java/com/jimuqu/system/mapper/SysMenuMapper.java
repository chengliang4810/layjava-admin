package com.jimuqu.system.mapper;

import cn.xbatis.core.sql.executor.chain.QueryChain;
import com.jimuqu.common.core.constant.UserConstants;
import com.jimuqu.common.mybatis.core.mapper.BaseMapperPlus;
import com.jimuqu.system.domain.*;
import com.jimuqu.system.domain.vo.SysMenuVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单表 数据层
 *
 * @author Lion Li,chengliang4810
 */
public interface SysMenuMapper extends BaseMapperPlus<SysMenu, SysMenuVo> {

    /**
     * 根据用户所有权限
     *
     * @return 权限列表
     */
    List<String> selectMenuPerms();


    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    List<String> selectMenuPermsByUserId(Long userId);

    /**
     * 根据角色ID查询权限
     *
     * @param roleId 角色ID
     * @return 权限列表
     */
    List<String> selectMenuPermsByRoleId(Long roleId);

    /**
     * 根据用户ID查询菜单
     *
     * @return 菜单列表
     */
    default List<SysMenu> selectMenuAll() {
        return QueryChain.of(this)
                .from(SysMenu.class)
                .in(SysMenu::getMenuType, UserConstants.TYPE_DIR, UserConstants.TYPE_MENU)
                .eq(SysMenu::getStatus, UserConstants.MENU_NORMAL)
                .orderBy(SysMenu::getParentId, SysMenu::getOrderNum)
                .list();
    }

    /**
     * 根据用户ID查询菜单
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    default List<SysMenu> selectMenuByUserId(Long userId){
        return QueryChain.of(this)
                .selectDistinct()
                .select(SysMenu.class)
                .from(SysMenu.class)
                .leftJoin(SysMenu::getId, SysRoleMenu::getMenuId)
                .leftJoin(SysRoleMenu::getRoleId, SysUserRole::getRoleId)
                .leftJoin(SysRole::getId, SysUserRole::getRoleId)
                .leftJoin(SysUserRole::getUserId, SysUser::getId)
                .in(SysMenu::getMenuType, UserConstants.TYPE_DIR, UserConstants.TYPE_MENU)
                .eq(SysMenu::getStatus, UserConstants.MENU_NORMAL)
                .eq(SysRole::getStatus, UserConstants.ROLE_NORMAL)
                .eq(SysUser::getId, userId)
                .orderBy(SysMenu::getParentId, SysMenu::getOrderNum)
                .list();
    }

    /**
     * 根据角色ID查询菜单树信息
     *
     * @param roleId            角色ID
     * @param menuCheckStrictly 菜单树选择项是否关联显示
     * @return 选中菜单列表
     */
    List<Long> selectMenuListByRoleId(@Param("roleId") Long roleId, @Param("menuCheckStrictly") boolean menuCheckStrictly);

}
