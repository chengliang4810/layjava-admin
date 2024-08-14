package com.layjava.common.satoken.config;

import cn.dev33.satoken.solon.dao.SaTokenDaoOfRedissonJackson;
import cn.dev33.satoken.stp.StpInterface;
import com.layjava.common.satoken.core.SaPermissionImpl;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Condition;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;
import org.redisson.api.RedissonClient;

@Configuration
public class SaTokenConfig {

    /**
     * 权限接口实现(使用bean注入方便用户替换)
     */
    @Bean
    public StpInterface stpInterface() {
        return new SaPermissionImpl();
    }

    /**
     * Sa-Token 持久层实现 [ Redisson客户端、Redis存储、Jackson序列化 ]
     *
     * @param redissonClient Redisson客户端
     * @return Sa-Token 持久层实现
     */
    @Bean
    @Condition(onBean = RedissonClient.class)
    public SaTokenDaoOfRedissonJackson saTokenDaoInit(@Inject RedissonClient redissonClient) {
        return new SaTokenDaoOfRedissonJackson(redissonClient);
    }
}
