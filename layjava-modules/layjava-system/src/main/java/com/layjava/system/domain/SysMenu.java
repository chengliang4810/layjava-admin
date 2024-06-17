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

    /**
     * 获取路由名称
     */
    public String getRouteName() {
//        String routerName = "";//StringUtils.capitalize(path);
//        // 非外链并且是一级目录（类型为目录）
//        if (isMenuFrame()) {
//            routerName = StringUtils.EMPTY;
//        }
        return path;
    }

    /**
     * 获取路由地址
     */
    public String getRouterPath() {
        return this.path;
    }

    /**
     * 是否为菜单内部跳转
     */
    public boolean isMenuFrame() {
        return getParentId() == 0L && UserConstants.TYPE_MENU.equals(menuType) && isFrame.equals(UserConstants.NO_FRAME);
    }

    /**
     * 是否为内链组件
     */
    public boolean isInnerLink() {
        return isFrame.equals(UserConstants.NO_FRAME);//&& StringUtils.ishttp(path);
    }

    /**
     * 是否为parent_view组件
     */
    public boolean isParentView() {
        return getParentId() != 0L && UserConstants.TYPE_DIR.equals(menuType);
    }

    /**
     * 内链域名特殊字符替换
     */
    public static String innerLinkReplaceEach(String path) {
        return path;// StringUtils.replaceEach(path, new String[]{Constants.HTTP, Constants.HTTPS, Constants.WWW, ".", ":"},
        //new String[]{"", "", "", "/", "/"});
    }
}
