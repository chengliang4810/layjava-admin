package com.layjava.system.domain;

import com.layjava.common.core.constant.Constants;
import com.layjava.common.core.constant.UserConstants;
import com.layjava.common.core.utils.StringUtils;
import com.layjava.common.dao.core.entity.BaseEntity;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单权限表 sys_menu
 *
 * @author Lion Li
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Table("sys_menu")
public class SysMenu extends BaseEntity {

    /**
     * 菜单ID
     */
    @Id
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
     * 显示顺序
     */
    private Integer orderNum;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 路由参数
     */
    private String queryParam;

    /**
     * 是否为外链（0是 1否）
     */
    private String isFrame;

    /**
     * 是否缓存（0缓存 1不缓存）
     */
    private String isCache;

    /**
     * 类型（M目录 C菜单 F按钮）
     */
    private String menuType;

    /**
     * 显示状态（0显示 1隐藏）
     */
    private String visible;

    /**
     * 菜单状态（0正常 1停用）
     */
    private String status;

    /**
     * 权限字符串
     */
    private String perms;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 备注
     */
    private String remark;

    /**
     * 路由名称
     */
    private String routerName;
    /**
     * 是否固定菜单
     */
    private Boolean pinTab;
    /**
     * 是否需要检查权限
     */
    private Boolean requiresAuth;
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
