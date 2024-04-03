package com.layjava.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.layjava.common.core.domain.model.LoginBody;
import com.layjava.common.web.core.BaseController;
import com.layjava.domain.vo.LoginVo;
import com.layjava.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.validation.annotation.Validated;

import java.util.HashMap;
import java.util.Map;

/**
 * 身份验证控制器
 *
 * @author chengliang
 * @date 2024/04/02
 */
@Slf4j
@Controller
@Mapping("/auth")
public class AuthController extends BaseController {

    @Inject
    private AuthService authService;

    /**
     * 登录
     *
     * @param loginBody 登录信息
     * @return {@link String}
     */
    @Mapping("/login")
    public LoginVo login(@Validated LoginBody loginBody) {
        return authService.login(loginBody);
    }

    @Mapping("/user-info")
    public Map<String, Object> userInfo() {
        Map<String, Object> result = new HashMap<>();
        result.put("userId", "1");
        result.put("userName", "admin");
        result.put("roles", new String[]{"admin"});
        result.put("buttons", new String[]{"add", "edit", "delete"});
        return result;
    }

    @Mapping("/logout")
    public void logout() {
        StpUtil.logout();
    }

}
