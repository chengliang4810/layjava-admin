package com.layjava.system.test.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.layjava.system.test.domain.Test;
import com.layjava.system.test.mapper.TestMapper;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Get;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;

import java.util.List;

/**
 * 测试控制器
 *
 * @author chengliang
 * @date 2024/02/26
 */
@Controller
@Mapping("/hello")
public class TestController {

    @Inject
    private TestMapper testMapper;

    @Get
    @Mapping("/demo")
    public String test() {
        List<Test> tests = testMapper.selectList(null);
        System.out.println(tests);
        return "test";
    }

}
