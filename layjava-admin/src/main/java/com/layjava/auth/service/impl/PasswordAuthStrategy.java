package com.layjava.auth.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.anji.captcha.properties.AjCaptchaProperties;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.layjava.auth.domain.bo.PasswordLoginBody;
import com.layjava.auth.domain.vo.LoginVo;
import com.layjava.auth.service.AuthStrategyService;
import com.layjava.auth.service.IAuthStrategy;
import com.layjava.common.core.enums.UserStatus;
import com.layjava.common.core.exception.ServiceException;
import com.layjava.common.core.util.JsonUtil;
import com.layjava.system.domain.SysClient;
import com.layjava.system.domain.SysUser;
import com.layjava.system.domain.vo.SysUserVo;
import com.layjava.system.mapper.SysUserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.Component;
import org.noear.solon.core.handle.Result;
import org.noear.solon.validation.ValidatorManager;

/**
 * 密码认证策略
 *
 * @author Michelle.Chung
 */
@Slf4j
@Component("password" + IAuthStrategy.BASE_NAME)
@RequiredArgsConstructor
public class PasswordAuthStrategy implements AuthStrategyService {

    //private final AjCaptchaProperties captchaProperties;
    // private final SysLoginService loginService;
    private final SysUserMapper userMapper;

    @Override
    public LoginVo login(String body, SysClient client) {
        PasswordLoginBody loginBody = JsonUtil.toObject(body, PasswordLoginBody.class);
        Result<?> result = ValidatorManager.validateOfEntity(loginBody, null);
        String username = loginBody.getAccount();
        String password = loginBody.getPassword();
        String code = loginBody.getCode();
        String uuid = loginBody.getUuid();

//        boolean captchaEnabled = captchaProperties.getEnable();
//        // 验证码开关
//        if (captchaEnabled) {
//            validateCaptcha(tenantId, username, code, uuid);
//        }

        SysUserVo user = loadUserByAccount(username);
        // loginService.checkLogin(LoginType.PASSWORD, username, () -> !BCrypt.checkpw(password, user.getPassword()));
        // 此处可根据登录用户的数据不同 自行创建 loginUser
//        LoginUser loginUser = loginService.buildLoginUser(user);
//        loginUser.setClientKey(client.getClientKey());
//        loginUser.setDeviceType(client.getDeviceType());
        SaLoginModel model = new SaLoginModel();
        model.setDevice(client.getDeviceType());
        // 自定义分配 不同用户体系 不同 token 授权时间 不设置默认走全局 yml 配置
        // 例如: 后台用户30分钟过期 app用户1天过期
        // model.setTimeout(client.getTimeout());
        // model.setActiveTimeout(client.getActiveTimeout());
        // model.setExtra(LoginHelper.CLIENT_KEY, client.getClientId());
        // 生成token
        //LoginHelper.login(loginUser, model);

        LoginVo loginVo = new LoginVo();
        loginVo.setToken(StpUtil.getTokenValue());
        loginVo.setExpireIn(StpUtil.getTokenTimeout());
        loginVo.setClientId(client.getClientId());
        return loginVo;
    }

//    /**
//     * 校验验证码
//     *
//     * @param username 用户名
//     * @param code     验证码
//     * @param uuid     唯一标识
//     */
//    private void validateCaptcha(String tenantId, String username, String code, String uuid) {
//        String verifyKey = GlobalConstants.CAPTCHA_CODE_KEY + StringUtils.defaultString(uuid, "");
//        String captcha = RedisUtils.getCacheObject(verifyKey);
//        RedisUtils.deleteObject(verifyKey);
//        if (captcha == null) {
//            loginService.recordLogininfor(tenantId, username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire"));
//            throw new CaptchaExpireException();
//        }
//        if (!code.equalsIgnoreCase(captcha)) {
//            loginService.recordLogininfor(tenantId, username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error"));
//            throw new CaptchaException();
//        }
//    }

    private SysUserVo loadUserByAccount(String account) {
        SysUser user = userMapper.selectOne(new LambdaQueryWrapper<SysUser>()
                .select(SysUser::getAccount, SysUser::getStatus)
                .eq(SysUser::getAccount, account));
        if (ObjectUtil.isNull(user)) {
            log.info("登录用户：{} 不存在.", account);
            throw new ServiceException("登录用户：{} 不存在.", account);
        } else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            log.info("登录用户：{} 已被停用.", account);
            throw new ServiceException("登录用户：{} 已被停用.", account);
        }
        return userMapper.selectVoOne(Wrappers.lambdaQuery(SysUser.class).eq(SysUser::getAccount, account));
    }

}
