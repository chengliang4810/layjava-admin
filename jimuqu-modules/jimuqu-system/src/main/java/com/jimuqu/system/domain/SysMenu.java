package com.jimuqu.system.domain;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.text.StrPool;
import cn.xbatis.db.annotations.Table;
import com.jimuqu.common.core.constant.Constants;
import com.jimuqu.common.core.constant.UserConstants;
import com.jimuqu.common.core.utils.StringUtil;
import com.jimuqu.common.mybatis.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.zhyd.oauth.utils.StringUtils;
import org.dromara.autotable.annotation.Ignore;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单权限表 sys_menu
 *
 * @author Lion Li,chengliang4810
 */

@Data
@Table("sys_menu")
@EqualsAndHashCode(callSuper = true)
public class SysMenu extends BaseEntity {

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
     * 父菜单名称
     */
    @Ignore
    @cn.xbatis.db.annotations.Ignore
    private String parentName;

    /**
     * 子菜单
     */
    @Ignore
    @cn.xbatis.db.annotations.Ignore
    private List<SysMenu> children = new ArrayList<>();

    /**
     * 内链域名特殊字符替换
     */
    public static String innerLinkReplaceEach(String path) {
        path = StringUtil.removeAll(path, Constants.HTTP, Constants.HTTPS, Constants.WWW);
        path = StringUtil.replace(path,StrPool.COLON , StrPool.SLASH);
        path = StringUtil.replace(path,StrPool.DOT , StrPool.SLASH);
//        return  StringUtil.replace(path, new String[]{Constants.HTTP, Constants.HTTPS, Constants.WWW, ".", ":"},
//                new String[]{"", "", "", "/", "/"});
        return path;
    }

    /**
     * 获取路由名称
     */
    public String getRouteName() {
        String routerName = StringUtil.upperFirst(path);
        // 非外链并且是一级目录（类型为目录）
        if (isMenuFrame()) {
            routerName = StringUtil.EMPTY;
        }
        return routerName;
    }

    /**
     * 获取路由地址
     */
    public String getRouterPath() {
        String routerPath = this.path;
        // 内链打开外网方式
        if (getParentId() != 0L && isInnerLink()) {
            routerPath = innerLinkReplaceEach(routerPath);
        }
        // 非外链并且是一级目录（类型为目录）
        if (0L == getParentId() && UserConstants.TYPE_DIR.equals(getMenuType())
                && UserConstants.NO_FRAME.equals(getIsFrame())) {
            routerPath = "/" + this.path;
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isMenuFrame()) {
            routerPath = "/";
        }
        return routerPath;
    }

    /**
     * 获取组件信息
     */
    public String getComponentInfo() {
        String component = UserConstants.LAYOUT;
        if (StringUtils.isNotEmpty(this.component) && !isMenuFrame()) {
            component = this.component;
        } else if (StringUtils.isEmpty(this.component) && getParentId() != 0L && isInnerLink()) {
            component = UserConstants.INNER_LINK;
        } else if (StringUtils.isEmpty(this.component) && isParentView()) {
            component = UserConstants.PARENT_VIEW;
        }
        return component;
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
        return isFrame.equals(UserConstants.NO_FRAME) && Validator.isUrl(path);
    }

    /**
     * 是否为parent_view组件
     */
    public boolean isParentView() {
        return getParentId() != 0L && UserConstants.TYPE_DIR.equals(menuType);
    }

}
