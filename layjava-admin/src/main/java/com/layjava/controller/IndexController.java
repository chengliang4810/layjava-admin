package com.layjava.controller;

import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;

/**
 * 后端默认 Controller，可用于监测服务是否启动成功
 * @author chengliang
 * @since 2024/03/27
 */
@Controller
public class IndexController {

    @Mapping("/")
    public String index(){
        return "欢迎使用LayJava-Admin";
    }

}
