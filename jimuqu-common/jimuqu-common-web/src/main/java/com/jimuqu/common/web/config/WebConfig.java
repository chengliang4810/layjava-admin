package com.jimuqu.common.web.config;

import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.web.cors.CrossInterceptor;

/**
 * web 通用配置
 *
 * @author chengliang
 * @since 2024/04/02
 */
@Configuration
public class WebConfig {

    /**
     * 跨域配置
     *
     * @return 跨域配置
     * @author chengliang
     * @since 2025/05/07
     */
    @Bean(index = -1)
    public CrossInterceptor crossInterceptor() {
        return new CrossInterceptor()
                // 设置访问源地址
                .allowedOrigins("*")
                // 设置访问源请求方法
                .allowedMethods("*")
                // 设置访问源请求头
                .allowedHeaders("*")
                // 是否支持用户凭据。
                .allowCredentials(true)
                // 有效期 1800秒
                .maxAge(3600);
    }

}
