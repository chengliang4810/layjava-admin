package com.layjava.system.runner;

import com.layjava.system.service.ISysOssConfigService;
import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.Inject;
import org.noear.solon.core.bean.LifecycleBean;

/**
 * 初始化 system 模块对应业务数据
 *
 * @author Lion Li
 */
@Slf4j

public class SystemApplicationRunner implements LifecycleBean {

    @Inject
    private ISysOssConfigService ossConfigService;

    @Override
    public void start() {
        ossConfigService.init();
        log.info("初始化OSS配置成功");
    }

}
