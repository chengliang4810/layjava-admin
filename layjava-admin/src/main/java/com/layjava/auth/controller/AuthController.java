package com.layjava.auth.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.layjava.auth.domain.bo.LoginBody;
import com.layjava.auth.service.AuthStrategy;
import com.layjava.common.core.util.JsonUtil;
import com.layjava.common.core.util.ValidatorUtil;
import com.layjava.common.web.core.BaseController;
import com.layjava.auth.domain.vo.LoginVo;
import com.layjava.auth.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.core.handle.Result;
import org.noear.solon.validation.BeanValidateInfo;
import org.noear.solon.validation.ValidatorManager;
import org.noear.solon.validation.annotation.Validated;

import java.util.HashMap;
import java.util.Map;

/**
 * 身份验证控制器
 *
 * @author chengliang
 * @since 2024/04/02
 */
@Slf4j
@Controller
@Mapping("/auth")
public class AuthController extends BaseController {

    /**
     * 登录
     *
     * @param body 登录信息
     * @return {@link String}
     */
    @Mapping("/login")
    public LoginVo login(@Validated String body) {
        // 验证参数
        LoginBody loginBody = JsonUtil.toObject(body, LoginBody.class);
        ValidatorUtil.validate(loginBody);
   
        return AuthStrategy.login(null, null, null);
    }

    /**
     * 用户信息
     *
     * @return {@link Map}<{@link String}, {@link Object}>
     */
    @Mapping("/user-info")
    public Map<String, Object> userInfo() {
        Map<String, Object> result = new HashMap<>();
        result.put("userId", "1");
        result.put("userName", "admin");
        result.put("roles", new String[]{"admin"});
        result.put("buttons", new String[]{"add", "edit", "delete"});
        return result;
    }

    /**
     * 注销
     */
    @Mapping("/logout")
    public void logout() {
        StpUtil.logout();
    }

}
