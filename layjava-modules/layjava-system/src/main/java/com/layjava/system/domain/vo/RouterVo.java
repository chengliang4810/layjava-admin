package com.layjava.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 路由配置信息
 *
 * @author chengliang
 * @date 2024/08/08
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RouterVo {

    /**
     * 主键
     */
    private Long id;
    /**
     * 父级Id
     */
    private Long  pid;
    /**
     * 组件路径
     */
    private String componentPath;
    /**
     * 类型: dir,page
     */
    private String menuType;
    /**
     * 路由名称
     */
    private String name;
    /**
     * 路由路径
     */
    private String path;
    /**
     * 认证权限
     */
    private Boolean requiresAuth;
    /**
     * 显示名称
     */
    private String title;
    /**
     * 图标
     */
    private String icon;
    /**
     * 常驻页面
     */
    private Boolean pinTab;
    /**
     * 缓存页面
     */
    private Boolean keepAlive;
    /**
     * 外链url
     */
    private String href;
    /**
     * 是否隐藏路由，当设置 true 的时候该路由不会再侧边栏出现
     */
    private boolean hide;
}
