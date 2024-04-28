package com.layjava.common.security.config;

import cn.dev33.satoken.dao.SaTokenDao;
import cn.dev33.satoken.dao.SaTokenDaoRedissonJackson;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Condition;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;
import org.redisson.api.RedissonClient;

/**
 * 令牌dao配置
 * 当使用redis时使用,将sa-token信息存储到reids中
 * 默认情况存储到本地
 * @author chengliang
 * @since  2024/04/28
 */
@Configuration
@Condition(onProperty = "${layjava.cache.driverType} = redis")
public class TokenDaoConfig {

    /**
     * sa token dao
     * @param redissonClient Redisson客户端
     * @return {@link SaTokenDao}
     */
    @Bean
    public SaTokenDao saTokenDao(@Inject RedissonClient redissonClient) {
        return new SaTokenDaoRedissonJackson(redissonClient);
    }

}
