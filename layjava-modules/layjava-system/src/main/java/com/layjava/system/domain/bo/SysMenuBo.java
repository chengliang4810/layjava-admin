package com.layjava.system.domain.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.layjava.common.dao.core.entity.BaseEntity;
import com.layjava.system.domain.SysMenu;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.noear.solon.validation.annotation.Length;
import org.noear.solon.validation.annotation.NotBlank;
import org.noear.solon.validation.annotation.NotNull;

/**
 * 菜单权限业务对象 sys_menu
 *
 * @author Michelle.Chung
 */

@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SysMenu.class, reverseConvertGenerate = false)
public class SysMenuBo extends BaseEntity {

    /**
     * 菜单ID
     */
    private Long id;

    /**
     * 父菜单ID
     */
    private Long pid;

    /**
     * 路由名称
     */
    @NotBlank(message = "路由名称不能为空")
    @Length(min = 0, max = 50, message = "路由名称长度不能超过50个字符")
    private String name;

    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空")
    @Length(min = 0, max = 50, message = "菜单名称长度不能超过50个字符")
    private String title;

    /**
     * 显示顺序
     */
    @NotNull(message = "显示顺序不能为空")
    private Integer order;

    /**
     * 路由地址
     */
    @Length(min = 0, max = 200, message = "路由地址不能超过200个字符")
    private String path;

    /**
     * 组件路径
     */
    @Length(min = 0, max = 200, message = "组件路径不能超过{max}个字符")
    private String componentPath;

    /**
     * 菜单类型 dir' | 'page'
     */
    @NotBlank(message = "菜单类型不能为空")
    private String menuType;


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
     * 权限标识
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Length(min = 0, max = 100, message = "权限标识长度不能超过{max}个字符")
    private String permissions;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 备注
     */
    private String remark;


}
