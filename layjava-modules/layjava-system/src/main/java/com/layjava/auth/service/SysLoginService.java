package com.layjava.auth.service;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.layjava.common.core.constant.Constants;
import com.layjava.common.core.constant.GlobalConstants;
import com.layjava.common.core.domain.dto.RoleDTO;
import com.layjava.common.core.domain.model.LoginUser;
import com.layjava.common.core.enums.LoginType;
import com.layjava.common.core.exception.user.UserException;
import com.layjava.common.core.utils.DateUtils;
import com.layjava.common.log.event.LogininforEvent;
import com.layjava.common.satoken.utils.LoginHelper;
import com.layjava.system.domain.SysUser;
import com.layjava.system.domain.bo.SysSocialBo;
import com.layjava.system.domain.vo.SysSocialVo;
import com.layjava.system.domain.vo.SysUserVo;
import com.layjava.system.mapper.SysUserMapper;
import com.layjava.system.service.ISysPermissionService;
import com.layjava.system.service.ISysSocialService;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthUser;
import org.apache.ibatis.solon.annotation.Db;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;
import org.noear.solon.core.event.EventBus;
import org.noear.solon.data.cache.CacheService;

import java.util.List;
import java.util.function.Supplier;

/**
 * 登录校验方法
 *
 * @author Lion Li
 */

@Slf4j
@Component
public class SysLoginService {

    @Inject(value = "${user.password.maxRetryCount}", required = false)
    private Integer maxRetryCount;
    @Inject(value = "${user.password.lockTime}", required = false)
    private Integer lockTime;
    @Inject
    private ISysPermissionService permissionService;
    @Inject
    private ISysSocialService sysSocialService;
    @Inject
    private CacheService cacheService;
    @Db
    private SysUserMapper userMapper;


    /**
     * 绑定第三方用户
     *
     * @param authUserData 授权响应实体
     * @return 统一响应实体
     */
    public void socialRegister(AuthUser authUserData) {
        String authId = authUserData.getSource() + authUserData.getUuid();
        // 第三方用户信息
        SysSocialBo bo = BeanUtil.toBean(authUserData, SysSocialBo.class);
        BeanUtil.copyProperties(authUserData.getToken(), bo);
        bo.setUserId(LoginHelper.getUserId());
        bo.setAuthId(authId);
        bo.setOpenId(authUserData.getUuid());
        bo.setUserName(authUserData.getUsername());
        bo.setNickName(authUserData.getNickname());
        // 查询是否已经绑定用户
        List<SysSocialVo> list = sysSocialService.selectByAuthId(authId);
        if (CollUtil.isEmpty(list)) {
            // 没有绑定用户, 新增用户信息
            sysSocialService.insertByBo(bo);
        } else {
            // 更新用户信息
            bo.setId(list.get(0).getId());
            sysSocialService.updateByBo(bo);
        }
    }


    /**
     * 退出登录
     */
    public void logout() {
        try {
            LoginUser loginUser = LoginHelper.getLoginUser();
            if (ObjectUtil.isNull(loginUser)) {
                return;
            }
            recordLogininfor(loginUser.getUsername(), Constants.LOGOUT, "退出成功");
        } catch (NotLoginException ignored) {
        } finally {
            try {
                StpUtil.logout();
            } catch (NotLoginException ignored) {
            }
        }
    }

    /**
     * 记录登录信息
     *
     * @param username 用户名
     * @param status   状态
     * @param message  消息内容
     */
    public void recordLogininfor(String username, String status, String message) {
        LogininforEvent logininforEvent = new LogininforEvent();
        logininforEvent.setUsername(username);
        logininforEvent.setStatus(status);
        logininforEvent.setMessage(message);
        // TODO getRequest
        // logininforEvent.setRequest(ServletUtils.getRequest());
        EventBus.publish(logininforEvent);
    }


    /**
     * 构建登录用户
     */
    public LoginUser buildLoginUser(SysUserVo user) {
        LoginUser loginUser = new LoginUser();
        loginUser.setTenantId(user.getTenantId());
        loginUser.setUserId(user.getUserId());
        loginUser.setDeptId(user.getDeptId());
        loginUser.setUsername(user.getUserName());
        loginUser.setNickname(user.getNickName());
        loginUser.setUserType(user.getUserType());
        loginUser.setMenuPermission(permissionService.getMenuPermission(user.getUserId()));
        loginUser.setRolePermission(permissionService.getRolePermission(user.getUserId()));
        loginUser.setDeptName(ObjectUtil.isNull(user.getDept()) ? "" : user.getDept().getDeptName());
        List<RoleDTO> roles = BeanUtil.copyToList(user.getRoles(), RoleDTO.class);
        loginUser.setRoles(roles);
        return loginUser;
    }

    /**
     * 记录登录信息
     *
     * @param userId 用户ID
     */
    public void recordLoginInfo(Long userId, String ip) {
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        sysUser.setLoginIp(ip);
        sysUser.setLoginDate(DateUtils.getNowDate());
        sysUser.setUpdateBy(userId);
        //todo
        // DataPermissionHelper.ignore(() -> userMapper.updateById(sysUser));
        userMapper.update(sysUser);
    }

    /**
     * 登录校验
     */
    public void checkLogin(LoginType loginType, String tenantId, String username, Supplier<Boolean> supplier) {
        String errorKey = GlobalConstants.PWD_ERR_CNT_KEY + username;
        String loginFail = Constants.LOGIN_FAIL;

        // 获取用户登录错误次数，默认为0 (可自定义限制策略 例如: key + username + ip)
        int errorNumber = ObjectUtil.defaultIfNull(cacheService.get(errorKey, Integer.class), 0);
        // 锁定时间内登录 则踢出
        if (errorNumber >= maxRetryCount) {
            recordLogininfor(username, loginFail, StrUtil.format(loginType.getRetryLimitExceed(), maxRetryCount, lockTime));
            throw new UserException(loginType.getRetryLimitExceed(), maxRetryCount, lockTime);
        }

        if (supplier.get()) {
            // 错误次数递增
            errorNumber++;
            cacheService.store(errorKey, errorNumber, lockTime * 1000 * 60 * 60);
            // 达到规定错误次数 则锁定登录
            if (errorNumber >= maxRetryCount) {
                recordLogininfor(username, loginFail, StrUtil.format(loginType.getRetryLimitExceed(), maxRetryCount, lockTime));
                throw new UserException(loginType.getRetryLimitExceed(), maxRetryCount, lockTime);
            } else {
                // 未达到规定错误次数
                recordLogininfor(username, loginFail, StrUtil.format(loginType.getRetryLimitCount(), errorNumber));
                throw new UserException(loginType.getRetryLimitCount(), errorNumber);
            }
        }

        // 登录成功 清空错误次数
        cacheService.remove(errorKey);
    }

}
