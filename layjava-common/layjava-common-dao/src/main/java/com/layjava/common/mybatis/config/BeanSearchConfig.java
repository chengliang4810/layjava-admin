package com.layjava.common.mybatis.config;

import cn.zhxu.bs.BeanMeta;
import cn.zhxu.bs.ParamFilter;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.core.handle.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * BeanSearch配置
 *
 * @author chengliang
 * @since 2024/04/17
 */
@Configuration
public class BeanSearchConfig {

    @Bean
    public ParamFilter currentRequestParamFilter() {
        return new ParamFilter() {
            @Override
            public <T> Map<String, Object> doFilter(BeanMeta<T> beanMeta, Map<String, Object> paraMap) {
                // 获取当前请求的所有参数
                Map<String, Object> params = new HashMap<>(Context.current().paramMap());
                // 用户传入的参数优先级更高
                params.putAll(paraMap);
                return params;
            }
        };
    }

}
