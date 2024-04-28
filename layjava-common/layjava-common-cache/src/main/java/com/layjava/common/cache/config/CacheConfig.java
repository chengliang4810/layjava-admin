package com.layjava.common.cache.config;

import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Condition;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;
import org.noear.solon.cache.redisson.RedissonCacheService;
import org.noear.solon.data.cache.CacheService;
import org.noear.solon.data.cache.CacheServiceSupplier;
import org.noear.solon.data.cache.impl.JsonSerializer;
import org.redisson.api.RedissonClient;

/**
 * 缓存配置
 *
 * @author chengliang
 * @since 2024/04/28
 */
@Configuration
public class CacheConfig {

    /**
     * 缓存服务
     * 根据 driverType 自动构建缓存服务
     * @param supplier 供应商
     * @return {@link CacheService}
     */
    @Bean(value = "layjava-cache", typed = true)
    public CacheService cacheService(@Inject("${layjava.cache}") CacheServiceSupplier supplier){
        return supplier.get();
    }


    /**
     * Redisson缓存
     * 增加序列化配置
     * @param cache 隐藏物
     * @return {@link CacheService}
     */
    @Bean
    @Condition(onProperty = "${layjava.cache.driverType} = redis")
    public CacheService redissonCache(@Inject("layjava-cache") RedissonCacheService cache){
        return cache.serializer(JsonSerializer.instance);
    }

    /**
     * Redisson缓存
     * 增加序列化配置
     * @param cache 隐藏物
     * @return {@link CacheService}
     */
    @Bean
    @Condition(onProperty = "${layjava.cache.driverType} = redis")
    public RedissonClient redissonClient(@Inject("layjava-cache") RedissonCacheService cache){
        return cache.client();
    }

}
