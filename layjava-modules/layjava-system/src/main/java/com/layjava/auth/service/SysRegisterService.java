package com.layjava.auth.service;

import cn.dev33.satoken.secure.BCrypt;
import com.layjava.common.core.constant.Constants;
import com.layjava.common.core.constant.GlobalConstants;
import com.layjava.common.core.domain.model.RegisterBody;
import com.layjava.common.core.enums.UserType;
import com.layjava.common.core.exception.user.CaptchaException;
import com.layjava.common.core.exception.user.CaptchaExpireException;
import com.layjava.common.core.exception.user.UserException;
import com.layjava.common.core.utils.StringUtils;
import com.layjava.common.log.event.LogininforEvent;
import com.layjava.common.web.config.properties.CaptchaProperties;
import com.layjava.system.domain.bo.SysUserBo;
import com.layjava.system.mapper.SysUserMapper;
import com.layjava.system.service.ISysUserService;
import com.mybatisflex.core.query.QueryWrapper;
import org.apache.ibatis.solon.annotation.Db;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;
import org.noear.solon.core.event.EventBus;
import org.noear.solon.data.cache.CacheService;

import static com.layjava.system.domain.table.SysUserTableDef.SYS_USER;


/**
 * 注册校验方法
 *
 * @author Lion Li
 */

@Component
public class SysRegisterService {

    @Inject
    private ISysUserService userService;
    @Inject
    private CacheService cacheService;
    @Db
    private SysUserMapper userMapper;
    private CaptchaProperties captchaProperties;

    /**
     * 注册
     */
    public void register(RegisterBody registerBody) {
        String tenantId = registerBody.getTenantId();
        String username = registerBody.getUsername();
        String password = registerBody.getPassword();
        // 校验用户类型是否存在
        String userType = UserType.getUserType(registerBody.getUserType()).getUserType();

        boolean captchaEnabled = captchaProperties.getEnable();
        // 验证码开关
        if (captchaEnabled) {
            validateCaptcha(tenantId, username, registerBody.getCode(), registerBody.getUuid());
        }
        SysUserBo sysUser = new SysUserBo();
        sysUser.setUserName(username);
        sysUser.setNickName(username);
        sysUser.setPassword(BCrypt.hashpw(password));
        sysUser.setUserType(userType);


        boolean exist = userMapper.selectCountByQuery(QueryWrapper.create()
                .from(SYS_USER)
                .and(SYS_USER.USER_NAME.eq(sysUser.getUserName()))
                .and(SYS_USER.USER_ID.ne(sysUser.getUserId()))) > 0;
        if (exist) {
            throw new UserException("保存用户 {} 失败，注册账号已存在", username);
        }
        boolean regFlag = userService.registerUser(sysUser, tenantId);
        if (!regFlag) {
            throw new UserException("user.register.error");
        }
        recordLogininfor(tenantId, username, Constants.REGISTER, "注册成功");
    }

    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code     验证码
     * @param uuid     唯一标识
     */
    public void validateCaptcha(String tenantId, String username, String code, String uuid) {
        String verifyKey = GlobalConstants.CAPTCHA_CODE_KEY + StringUtils.defaultString(uuid, "");
        String captcha = cacheService.get(verifyKey, String.class);
        cacheService.remove(verifyKey);
        if (captcha == null) {
            recordLogininfor(tenantId, username, Constants.REGISTER, "验证码已失效");
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha)) {
            recordLogininfor(tenantId, username, Constants.REGISTER, "验证码错误");
            throw new CaptchaException();
        }
    }

    /**
     * 记录登录信息
     *
     * @param tenantId 租户ID
     * @param username 用户名
     * @param status   状态
     * @param message  消息内容
     * @return
     */
    private void recordLogininfor(String tenantId, String username, String status, String message) {
        LogininforEvent logininforEvent = new LogininforEvent();
        logininforEvent.setTenantId(tenantId);
        logininforEvent.setUsername(username);
        logininforEvent.setStatus(status);
        logininforEvent.setMessage(message);
        // TODO 待定Request参数
        // logininforEvent.setRequest(ServletUtils.getRequest());
        EventBus.publish(logininforEvent);
    }

}
