package com.layjava.common.mybatis.config;

import com.layjava.common.mybatis.core.entity.BaseEntity;
import com.layjava.common.mybatis.listener.BaseEntityInsertListener;
import com.layjava.common.mybatis.listener.BaseEntityUpdateListener;
import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.audit.AuditManager;
import com.mybatisflex.core.mybatis.FlexConfiguration;
import com.mybatisflex.core.query.QueryColumnBehavior;
import org.apache.ibatis.solon.annotation.Db;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ORM框架配置
 *
 * @author chengliang
 * @since 2024/02/27
 */
@Configuration
public class MybatisFlexConfig {

    private static final Logger logger = LoggerFactory.getLogger("mybatis-flex-sql");

    static {
        // 全局自动忽略策略
        QueryColumnBehavior.setIgnoreFunction(QueryColumnBehavior.IGNORE_BLANK);
        // 当 IN(...) 条件只有 1 个参数时，自动把的内容转换为相等。
        QueryColumnBehavior.setSmartConvertInToEquals(true);
    }

    /**
     * MybatisFlex配置
     *
     * @param flexConfiguration flexConfiguration
     * @param globalConfig      globalConfig
     */
    @Bean
    public void configuration(@Db("default") FlexConfiguration flexConfiguration
            , @Db("default") FlexGlobalConfig globalConfig) {

        //开启审计功能
        AuditManager.setAuditEnable(true);
        //设置 SQL 审计收集器
        AuditManager.setMessageCollector(auditMessage ->
                logger.info("耗时: {}ms  SQL: {}", auditMessage.getElapsedTime(), auditMessage.getFullSql())
        );

        // BaseEntity数据填充
        globalConfig.registerInsertListener(new BaseEntityInsertListener(), BaseEntity.class);
        globalConfig.registerUpdateListener(new BaseEntityUpdateListener(), BaseEntity.class);
    }

}
