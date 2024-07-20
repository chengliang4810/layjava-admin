package com.layjava.auth.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.layjava.auth.domain.vo.LoginVo;
import com.layjava.auth.service.AuthStrategy;
import com.layjava.auth.service.AuthStrategyService;
import com.layjava.auth.service.SysLoginService;
import com.layjava.common.core.constant.Constants;
import com.layjava.common.core.constant.GlobalConstants;
import com.layjava.common.core.domain.model.LoginUser;
import com.layjava.common.core.domain.model.PasswordLoginBody;
import com.layjava.common.core.enums.LoginType;
import com.layjava.common.core.enums.UserStatus;
import com.layjava.common.core.exception.user.CaptchaException;
import com.layjava.common.core.exception.user.CaptchaExpireException;
import com.layjava.common.core.exception.user.UserException;
import com.layjava.common.core.utils.StringUtils;
import com.layjava.common.core.utils.ValidatorUtils;
import com.layjava.common.satoken.utils.LoginHelper;
import com.layjava.common.web.config.properties.CaptchaProperties;
import com.layjava.system.domain.SysClient;
import com.layjava.system.domain.SysUser;
import com.layjava.system.domain.vo.SysUserVo;
import com.layjava.system.mapper.SysUserMapper;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;
import org.noear.solon.data.cache.CacheService;

import static com.layjava.system.domain.table.SysUserTableDef.SYS_USER;

/**
 * 密码认证策略
 *
 * @author Michelle.Chung
 */
@Slf4j
@Component("password" + AuthStrategy.BASE_NAME)
public class PasswordAuthStrategy implements AuthStrategyService {

    @Inject
    private CaptchaProperties captchaProperties;
    @Inject
    private CacheService cacheService;
    @Inject
    private SysLoginService loginService;
    @Inject
    private SysUserMapper userMapper;

    @Override
    public LoginVo login(String body, SysClient client) {
        PasswordLoginBody loginBody = JSONUtil.toBean(body, PasswordLoginBody.class);
        ValidatorUtils.validate(loginBody);
        String username = loginBody.getUsername();
        String password = loginBody.getPassword();
        String code = loginBody.getCode();
        String uuid = loginBody.getUuid();

        boolean captchaEnabled = captchaProperties.getEnable();
        // 验证码开关
        if (captchaEnabled) {
            validateCaptcha(username, code, uuid);
        }

        SysUserVo user = loadUserByUsername(username);
        loginService.checkLogin(LoginType.PASSWORD, username, () -> !BCrypt.checkpw(password, user.getPassword()));
        // 此处可根据登录用户的数据不同 自行创建 loginUser
        LoginUser loginUser = loginService.buildLoginUser(user);
        loginUser.setClientKey(client.getClientKey());
        loginUser.setDeviceType(client.getDeviceType());
        SaLoginModel model = new SaLoginModel();
        model.setDevice(client.getDeviceType());
        // 自定义分配 不同用户体系 不同 token 授权时间 不设置默认走全局 yml 配置
        // 例如: 后台用户30分钟过期 app用户1天过期
        model.setTimeout(client.getTimeout());
        model.setActiveTimeout(client.getActiveTimeout());
        model.setExtra(LoginHelper.CLIENT_KEY, client.getClientId());
        // 生成token
        LoginHelper.login(loginUser, model);

        LoginVo loginVo = new LoginVo();
        loginVo.setAccessToken(StpUtil.getTokenValue());
        loginVo.setExpireIn(StpUtil.getTokenTimeout());
        loginVo.setClientId(client.getClientId());
        return loginVo;
    }

    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code     验证码
     * @param uuid     唯一标识
     */
    private void validateCaptcha(String username, String code, String uuid) {
        String verifyKey = GlobalConstants.CAPTCHA_CODE_KEY + StringUtils.defaultString(uuid, "");
        String captcha = cacheService.get(verifyKey, String.class);
        cacheService.remove(verifyKey);
        if (captcha == null) {
            loginService.recordLogininfor(username, Constants.LOGIN_FAIL, "验证码过期");
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha)) {
            loginService.recordLogininfor(username, Constants.LOGIN_FAIL, "验证码错误");
            throw new CaptchaException();
        }
    }

    private SysUserVo loadUserByUsername(String username) {
        SysUser user = userMapper.selectOneByQuery(
                QueryWrapper.create()
                        .select(SYS_USER.USER_NAME, SYS_USER.STATUS)
                        .from(SYS_USER)
                        .and(SYS_USER.USER_NAME.eq(username)));
        if (ObjectUtil.isNull(user)) {
            log.info("登录用户：{} 不存在.", username);
            throw new UserException("user.not.exists", username);
        } else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            log.info("登录用户：{} 已被停用.", username);
            throw new UserException("user.blocked", username);
        }
        return userMapper.selectUserByUserName(username);
    }

}
