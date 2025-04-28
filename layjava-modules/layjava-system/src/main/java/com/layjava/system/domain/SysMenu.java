package com.layjava.system.domain;

import cn.xbatis.db.annotations.Ignore;
import cn.xbatis.db.annotations.Table;
import cn.xbatis.db.annotations.TableId;
import com.layjava.common.mybatis.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单权限表 sys_menu
 *
 * @author Lion Li,chengliang4810
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Table("sys_menu")
public class SysMenu extends BaseEntity {

    /**
     * 菜单ID
     */
    @TableId
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
    @Ignore
    private String parentName;

    /**
     * 子菜单
     */
    @Ignore
    private List<SysMenu> children = new ArrayList<>();

}
