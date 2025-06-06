package com.jimuqu.system.domain.bo;

import com.jimuqu.common.core.validate.group.AddGroup;
import com.jimuqu.common.core.validate.group.UpdateGroup;
import com.jimuqu.common.mybatis.core.entity.BoBaseEntity;
import com.jimuqu.system.domain.SysMenu;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.noear.solon.validation.annotation.*;

/**
 * 菜单权限业务对象 menu
 *
 * @author chengliang4810
 * @since 2025-06-06
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SysMenu.class, reverseConvertGenerate = false)
public class SysMenuBo extends BoBaseEntity {

    /**
     * 菜单ID
     */
    @NotNull(message = "菜单ID不能为空", groups = { UpdateGroup.class })
    private Long id;
    /**
     * 父菜单ID
     */
    @NotNull(message = "父菜单ID不能为空", groups = { AddGroup.class, UpdateGroup.class })
    private Long parentId;
    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空", groups = { AddGroup.class, UpdateGroup.class })
    private String menuName;
    /**
     * 显示顺序
     */
    @NotNull(message = "显示顺序不能为空", groups = { AddGroup.class, UpdateGroup.class })
    private Integer orderNum;
    /**
     * 路由地址
     */
    @NotBlank(message = "路由地址不能为空", groups = { AddGroup.class, UpdateGroup.class })
    private String path;
    /**
     * 组件路径
     */
    @NotBlank(message = "组件路径不能为空", groups = { AddGroup.class, UpdateGroup.class })
    private String component;
    /**
     * 路由参数
     */
    @NotBlank(message = "路由参数不能为空", groups = { AddGroup.class, UpdateGroup.class })
    private String queryParam;
    /**
     * 是否为外链（0是 1否）
     */
    @NotNull(message = "是否为外链（0是 1否）不能为空", groups = { AddGroup.class, UpdateGroup.class })
    private String isFrame;
    /**
     * 是否缓存（0缓存 1不缓存）
     */
    @NotNull(message = "是否缓存（0缓存 1不缓存）不能为空", groups = { AddGroup.class, UpdateGroup.class })
    private String isCache;
    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    @NotBlank(message = "菜单类型（M目录 C菜单 F按钮）不能为空", groups = { AddGroup.class, UpdateGroup.class })
    private String menuType;
    /**
     * 显示状态（0显示 1隐藏）
     */
    @NotBlank(message = "显示状态（0显示 1隐藏）不能为空", groups = { AddGroup.class, UpdateGroup.class })
    private String visible;
    /**
     * 菜单状态（0正常 1停用）
     */
    @NotBlank(message = "菜单状态（0正常 1停用）不能为空", groups = { AddGroup.class, UpdateGroup.class })
    private String status;
    /**
     * 权限标识
     */
    @NotBlank(message = "权限标识不能为空", groups = { AddGroup.class, UpdateGroup.class })
    private String perms;
    /**
     * 菜单图标
     */
    @NotBlank(message = "菜单图标不能为空", groups = { AddGroup.class, UpdateGroup.class })
    private String icon;
    /**
     * 备注
     */
    private String remark;

}
