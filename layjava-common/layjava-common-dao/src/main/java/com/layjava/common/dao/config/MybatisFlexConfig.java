package com.layjava.common.dao.config;

import com.layjava.common.dao.core.entity.BaseEntity;
import com.layjava.common.dao.listener.BaseEntityInsertListener;
import com.layjava.common.dao.listener.BaseEntityUpdateListener;
import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.audit.AuditManager;
import com.mybatisflex.core.mybatis.FlexConfiguration;
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
                logger.info("耗时:{}ms  SQL:{}", auditMessage.getFullSql(), auditMessage.getElapsedTime())
        );

        // BaseEntity数据填充
        globalConfig.registerInsertListener(new BaseEntityInsertListener(), BaseEntity.class);
        globalConfig.registerUpdateListener(new BaseEntityUpdateListener(), BaseEntity.class);
    }

}
