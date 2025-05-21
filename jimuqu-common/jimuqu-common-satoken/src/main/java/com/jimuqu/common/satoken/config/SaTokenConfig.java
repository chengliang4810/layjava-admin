package com.jimuqu.common.satoken.config;

import cn.dev33.satoken.dao.SaTokenDaoForRedisson;
import cn.dev33.satoken.stp.StpInterface;
import com.jimuqu.common.satoken.core.SaPermissionImpl;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Condition;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;
import org.noear.solon.cache.redisson.RedissonCacheService;

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
     * Sa-Token 持久层实现 [ Redisson客户端、Redis存储、snack3序列化 ]
     *
     * @param redissonCacheService solon redis缓存服务
     * @return Sa-Token 持久层实现
     */
    @Bean
    @Condition(onBean = RedissonCacheService.class)
    public SaTokenDaoForRedisson saTokenDaoForRedissonInit(@Inject RedissonCacheService redissonCacheService) {
        return new SaTokenDaoForRedisson(redissonCacheService.client());
    }

}
