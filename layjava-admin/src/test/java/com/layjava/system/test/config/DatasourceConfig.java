package com.layjava.system.test.config;

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
    @Bean(value = "db1", typed = true)
    public DataSource defaultDataSource(@Inject("${datasource.db1}") HikariDataSource dataSource) {
        return dataSource;
    }

}
