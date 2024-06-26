package com.layjava.auth.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.layjava.auth.domain.bo.PasswordLoginBody;
import com.layjava.auth.domain.vo.LoginVo;
import com.layjava.auth.service.AuthStrategy;
import com.layjava.auth.service.AuthStrategyService;
import com.layjava.common.core.domain.model.LoginUser;
import com.layjava.common.core.enums.UserStatus;
import com.layjava.common.core.exception.ServiceException;
import com.layjava.common.core.util.JsonUtil;
import com.layjava.common.core.util.ValidatorUtil;
import com.layjava.common.security.utils.LoginHelper;
import com.layjava.system.domain.SysClient;
import com.layjava.system.domain.SysUser;
import com.layjava.system.domain.vo.SysUserVo;
import com.layjava.system.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.solon.annotation.Db;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

/**
 * 密码认证策略
 *
 * @author chengliang
 * @since 2024/04/24
 */
@Slf4j
@Component("password" + AuthStrategy.BASE_NAME)
public class PasswordAuthStrategy implements AuthStrategyService {

    @Db
    private SysUserMapper userMapper;
    @Inject
    private LoginService loginService;

    @Override
    public LoginVo login(String body, SysClient client) {
        // 校验参数
        PasswordLoginBody loginBody = JsonUtil.toObject(body, PasswordLoginBody.class);
        ValidatorUtil.validate(loginBody);

        String account = loginBody.getAccount();
        String password = loginBody.getPassword();
        String code = loginBody.getCode();
        String uuid = loginBody.getUuid();

//        boolean captchaEnabled = captchaProperties.getEnable();
//        // 验证码开关
//        if (captchaEnabled) {
//            validateCaptcha(tenantId, username, code, uuid);
//        }

        SysUserVo user = loadUserByAccount(account);

        // 校验密码
        if (!BCrypt.checkpw(password, user.getPassword())){
            throw new ServiceException("账号/密码错误");
        }
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
        return userMapper.selectUserByAccount(account);
    }

}
