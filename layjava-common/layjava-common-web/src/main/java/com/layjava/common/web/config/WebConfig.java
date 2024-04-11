package com.layjava.common.web.config;

import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Import;

/**
 * web 通用配置
 * @author chengliang
 * @date 2024/04/02
 */
@Configuration
@Import(profilesIfAbsent = "classpath:common-web.yml")
public class WebConfig {
}
