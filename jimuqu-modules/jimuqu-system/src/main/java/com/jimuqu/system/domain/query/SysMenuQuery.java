package com.jimuqu.system.domain.query;

import cn.xbatis.core.sql.ObjectConditionLifeCycle;
import cn.xbatis.db.annotations.Condition;
import cn.xbatis.db.annotations.ConditionTarget;
import com.jimuqu.system.domain.SysMenu;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.io.Serial;
import java.io.Serializable;

import static cn.xbatis.db.annotations.Condition.Type.*;

/**
 * 菜单权限查询条件对象
 * @author chengliang4810
 * @since 2025-06-06
 */
@Data
@FieldNameConstants
@ConditionTarget(SysMenu.class)
public class SysMenuQuery implements Serializable, ObjectConditionLifeCycle {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @Condition(value = EQ)
    private Long id;
    /**
     * 父菜单ID
     */
    @Condition(value = EQ)
    private Long parentId;
    /**
     * 菜单名称
     */
    @Condition(value = LIKE)
    private String menuName;
    /**
     * 显示顺序
     */
    @Condition(value = EQ)
    private Integer orderNum;
    /**
     * 路由地址
     */
    @Condition(value = EQ)
    private String path;
    /**
     * 组件路径
     */
    @Condition(value = EQ)
    private String component;
    /**
     * 是否为外链（0是 1否）
     */
    @Condition(value = EQ)
    private String isFrame;
    /**
     * 是否缓存（0缓存 1不缓存）
     */
    @Condition(value = EQ)
    private String isCache;
    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    @Condition(value = EQ)
    private String menuType;
    /**
     * 显示状态（0显示 1隐藏）
     */
    @Condition(value = EQ)
    private String visible;
    /**
     * 菜单状态（0正常 1停用）
     */
    @Condition(value = EQ)
    private String status;
    /**
     * 权限标识
     */
    @Condition(value = EQ)
    private String perms;

    /**
     * 条件构建前执行
     */
    @Override
    public void beforeBuildCondition() {
        
    }

}
