package com.layjava.common.mybatis.config;

import com.layjava.common.mybatis.core.entity.BaseEntity;
import com.layjava.common.mybatis.listener.BaseEntityInsertListener;
import com.layjava.common.mybatis.listener.BaseEntityUpdateListener;
import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.query.QueryColumnBehavior;
import org.apache.ibatis.solon.annotation.Db;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;

/**
 * ORM框架配置
 *
 * @author chengliang
 * @since 2024/02/27
 */
@Configuration
public class MybatisFlexConfig {

    static {
        // 全局自动忽略策略 null
        QueryColumnBehavior.setIgnoreFunction(QueryColumnBehavior.IGNORE_NULL);
        // 当 IN(...) 条件只有 1 个参数时，自动把的内容转换为相等。
        QueryColumnBehavior.setSmartConvertInToEquals(true);
    }

    /**
     * MybatisFlex配置
     *
     * @param globalConfig globalConfig
     */
    @Bean
    public FlexGlobalConfig configuration(@Db FlexGlobalConfig globalConfig) {
        // BaseEntity数据填充
        globalConfig.registerInsertListener(new BaseEntityInsertListener(), BaseEntity.class);
        globalConfig.registerUpdateListener(new BaseEntityUpdateListener(), BaseEntity.class);
        return globalConfig;
    }

}
