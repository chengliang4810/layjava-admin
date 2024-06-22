package com.layjava.system.domain;

import com.layjava.common.dao.core.entity.BaseEntity;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单权限表 sys_menu
 *
 * @author chengliang
 * @date 2024/06/13
 */

@Data
@FieldNameConstants
@Table("sys_menu")
@EqualsAndHashCode(callSuper = true)
public class SysMenu extends BaseEntity {

    /**
     * 菜单ID
     */
    @Id
    private Long id;

    /**
     * 父菜单ID
     */
    private Long pid;

    /**
     * 菜单名称
     */
    private String title;

    /**
     * 路由名称
     */
    private String name;

    /**
     * 组件路径
     */
    private String componentPath;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 显示顺序
     */
    private Integer order;

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


//    @Navigate(value = RelationTypeEnum.ManyToMany,
//            mappingClass = SysRoleMenu.class,
//            selfMappingProperty = "menuId",
//            targetMappingProperty = "roleId")
    private List<SysRole> roles;


    /**
     * 父菜单名称
     */
    @Column(ignore = true)
    private String parentName;

    /**
     * 子菜单
     */
    @Column(ignore = true)
    private List<SysMenu> children = new ArrayList<>();

}
