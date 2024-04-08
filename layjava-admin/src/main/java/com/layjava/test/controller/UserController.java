package com.layjava.test.controller;

import com.layjava.common.web.core.BaseController;
import com.layjava.test.service.UserService;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;

/**
 * 用户信息表 控制器
 *
 * @author chengliang4810
 * @since 2024-04-08
 */
@Controller
@Mapping("/test/user")
public class UserController extends BaseController {

    @Inject
    private UserService userService;

}