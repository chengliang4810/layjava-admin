package com.layjava.auth.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.layjava.auth.domain.bo.LoginBody;
import com.layjava.auth.service.AuthStrategy;
import com.layjava.common.core.constant.UserConstants;
import com.layjava.common.core.util.JsonUtil;
import com.layjava.common.core.util.ValidatorUtil;
import com.layjava.common.web.core.BaseController;
import com.layjava.auth.domain.vo.LoginVo;
import com.layjava.auth.service.AuthService;
import com.layjava.system.domain.SysClient;
import com.layjava.system.service.SysClientService;
import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.*;
import org.noear.solon.core.handle.Result;
import org.noear.solon.validation.BeanValidateInfo;
import org.noear.solon.validation.ValidatorManager;
import org.noear.solon.validation.annotation.NotBlank;
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

    @Inject
    private SysClientService clientService;

    /**
     * 登录
     *
     * @param body 登录信息
     * @return {@link String}
     */
    @Post
    @Mapping("/login")
    public Result<LoginVo> login(@Body String body) {
        // 验证参数
        LoginBody loginBody = JsonUtil.toObject(body, LoginBody.class);
        ValidatorUtil.validate(loginBody);

        // 授权类型和客户端id
        String clientId = loginBody.getClientId();
        String grantType = loginBody.getGrantType();
        SysClient client = clientService.getByClientId(clientId);

        // 查询不到 client 或 client 内不包含 grantType
        if (ObjectUtil.isNull(client) || !StrUtil.contains(client.getGrantType(), grantType)) {
            log.info("客户端id: {} 认证类型：{} 异常!.", clientId, grantType);
            return Result.failure("认证权限类型错误");
        } else if (!UserConstants.NORMAL.equals(client.getStatus())) {
            return  Result.failure("认证权限类型已禁用");
        }

        //登录
        return Result.succeed(AuthStrategy.login(body, client, grantType));
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
