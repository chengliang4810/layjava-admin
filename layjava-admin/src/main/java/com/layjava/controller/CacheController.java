package com.layjava.controller;

import cn.hutool.core.util.IdUtil;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.data.annotation.Cache;

@Controller
public class CacheController {

    @Cache
    @Mapping("/cache")
    public String test() {
        return IdUtil.getSnowflakeNextIdStr();
    }
}
