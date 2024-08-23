package com.layjava.system.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.layjava.common.core.utils.StringUtil;
import com.layjava.common.satoken.utils.LoginHelper;
import org.noear.solon.annotation.Component;


/**
 * 脱敏服务
 * 默认管理员不过滤
 * 需自行根据业务重写实现
 *
 * @author Lion Li
 * @version 3.6.0
 */
@Component
public class SysSensitiveServiceImpl { // implements SensitiveService

    /**
     * 是否脱敏
     */
    public boolean isSensitive(String roleKey, String perms) {
        if (!LoginHelper.isLogin()) {
            return true;
        }
        boolean roleExist = StringUtil.isNotBlank(roleKey);
        boolean permsExist = StringUtil.isNotBlank(perms);
        if (roleExist && permsExist) {
            if (StpUtil.hasRole(roleKey) && StpUtil.hasPermission(perms)) {
                return false;
            }
        } else if (roleExist && StpUtil.hasRole(roleKey)) {
            return false;
        } else if (permsExist && StpUtil.hasPermission(perms)) {
            return false;
        }

        return !LoginHelper.isSuperAdmin();
    }

}
