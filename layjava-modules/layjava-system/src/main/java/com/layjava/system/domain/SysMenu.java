package com.layjava.system.domain;

import com.easy.query.core.annotation.*;
import com.easy.query.core.enums.RelationTypeEnum;
import com.easy.query.core.proxy.ProxyEntityAvailable;
import com.layjava.common.core.constant.UserConstants;
import com.layjava.common.dao.core.entity.BaseEntity;
import com.layjava.system.domain.proxy.SysMenuProxy;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;
import org.smartboot.http.common.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单权限表 sys_menu
 *
 * @author chengliang
 * @date 2024/06/13
 */

@Data
@EntityProxy
@FieldNameConstants
@Table("sys_menu")
@EasyAlias("sysMenu")
@EqualsAndHashCode(callSuper = true)
public class SysMenu extends BaseEntity implements ProxyEntityAvailable<SysMenu , SysMenuProxy> {

    /**
     * 菜单ID
     */
    @Column(primaryKey = true)
    private Long menuId;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 路由名称
     */
    private String routeName;

    /**
     * 组件路径
     */
    private String componentPath;

    /**
     * 路由地址
     */
    private String routePath;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 显示顺序
     */
    private Integer orderNum;

    /**
     * 路由重定向
     */
    private String redirect;

    /**
     * 显示状态（0显示 1隐藏）
     */
    private Boolean hide;

    /**
     * 是否缓存（0缓存 1不缓存）
     */
    private Boolean keepAlive;

    /**
     * 菜单状态（0正常 1停用）
     */
    private Boolean status;

    /**
     * 是否需要登录权限
     */
    private Boolean requiresAuth;

    /**
     * 当前路由是否会被添加到Tab中
     */
    private Boolean withoutTab;

    /**
     * 当前路由是否会被固定在Tab中,用于一些常驻页面
     */
    private Boolean pinTab;

    /**
     * 外链URL地址
     */
    private String href;

    /**
     * 权限
     */
    private String permissions;

    /**
     * 菜单类型 dir' | 'page'
     */
    private String menuType;

    /**
     * 备注
     */
    private String remark;


    /**
     * 高亮菜单
     */
    private String activeMenu;


    @Navigate(value = RelationTypeEnum.ManyToMany,
            mappingClass = SysRoleMenu.class,
            selfMappingProperty = "menuId",
            targetMappingProperty = "roleId")
    private List<SysRole> roles;


    /**
     * 父菜单名称
     */
    @ColumnIgnore
    private String parentName;

    /**
     * 子菜单
     */
    @ColumnIgnore
    private List<SysMenu> children = new ArrayList<>();

}
