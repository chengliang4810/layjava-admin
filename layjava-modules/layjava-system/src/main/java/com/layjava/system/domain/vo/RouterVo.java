package com.layjava.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * 路由配置信息
 *
 * @author Lion Li
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RouterVo {

    /**
     * 主键
     */
    private Long id;

    /**
     * 父级主键
     */
    private Long pid;

    /**
     * 路由名字
     */
    private String name;

    /**
     * 设置该路由在侧边栏和面包屑中展示的名字
     */
    private String title;

    /**
     * 是否隐藏路由，当设置 true 的时候该路由不会再侧边栏出现
     */
    private Boolean hide;
    /**
     * 是否缓存
     */
    private Boolean keepAlive;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 菜单类型 (dir/page)
     */
    private String menuType;

    /**
     * 设置该路由的图标，对应路径src/assets/icons/svg
     */
    private String icon;

    /**
     * 排序
     */
    private Integer order;

    /**
     * 是否必须验证
     */
    private boolean requiresAuth;

    /**
     * 外链接地址
     */
    private String href;

    /**
     * 组件地址
     */
    private String componentPath;

}
