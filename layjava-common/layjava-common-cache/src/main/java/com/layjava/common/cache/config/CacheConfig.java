package com.layjava.common.cache.config;

import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;
import org.noear.solon.cache.redisson.RedissonCacheService;
import org.noear.solon.core.util.LogUtil;
import org.noear.solon.data.cache.CacheService;
import org.noear.solon.data.cache.CacheServiceSupplier;
import org.redisson.api.RedissonClient;

@Configuration
public class CacheConfig {

    /**
     * 根据配置加载缓存服务
     *
     * @param supplier 缓存供应商
     * @return 缓存服务
     */
    @Bean(value = "cacheMaster", typed = true)
    public CacheService cacheService(@Inject("${layjava.cache}") CacheServiceSupplier supplier) {
        LogUtil.global().info("Cache: " + supplier.get().getClass().getSimpleName());
        return supplier.get();
    }

    /**
     * redissonClient
     *
     * @return RedissonClient
     */
    @Bean(typed = true)
    public RedissonClient redissonClient(@Inject(value = "cacheMaster", required = false) RedissonCacheService redissonCacheService) {
        if (redissonCacheService == null) {
            return null;
        }
        return redissonCacheService.client();
    }

}
