package com.layjava.generator.test.controller;

import com.layjava.generator.test.service.UserService;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Controller;

import com.layjava.common.web.core.BaseController;

/**
 *
 * 用户信息表 控制器
 *
 * @author chengliang4810
 * @since 2024-04-19
 */
@Controller
@Mapping("/test/user")
public class UserController extends BaseController {

    @Inject
    private UserService userService;

}