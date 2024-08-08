package com.layjava.plugin.mapstruct.plus;

import org.noear.solon.core.AppContext;
import org.noear.solon.core.Plugin;

public class MapStructPlusPlugin implements Plugin {
    @Override
    public void start(final AppContext context) throws Throwable {
        // 设置扫描路径
        context.beanScan("io.github.linpeilie");
        context.beanMake(MapStructPlusConfiguration.class);
    }
}
