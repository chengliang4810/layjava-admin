package com.layjava.common.mybatis.config;

import com.p6spy.engine.spy.P6DataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;

import javax.sql.DataSource;

/**
 * 数据源配置
 *
 * @author chengliang
 * @date 2024/06/13
 */
@Configuration
public class DatasourceConfig {

    /**
     * 默认数据源
     * @param dataSource 数据源
     * @return 数据源
     */
    @Bean(value = "default", typed = true)
    public DataSource defaultDataSource(@Inject("${layjava.datasource.default}") HikariDataSource dataSource
            , @Inject(value = "${layjava.datasource.default.p6spy: false}", required = false) Boolean p6spy) {
        if(Boolean.TRUE.equals(p6spy)){
            return new P6DataSource(dataSource);
        }
        return dataSource;
    }

}
