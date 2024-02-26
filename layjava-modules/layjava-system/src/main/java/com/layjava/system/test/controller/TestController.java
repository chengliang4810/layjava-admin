package com.layjava.system.test.controller;

import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Get;
import org.noear.solon.annotation.Mapping;

/**
 * 测试控制器
 *
 * @author chengliang
 * @date 2024/02/26
 */
@Controller
@Mapping("/test")
public class TestController {

    @Get
    @Mapping("/demo")
    public String test() {
        return "test";
    }

}
