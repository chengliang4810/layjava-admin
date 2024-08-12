package com.layjava.common.mybatis.config;

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
     *
     * @param dataSource 数据源
     * @return 数据源
     */
    @Bean(value = "master", typed = true)
    public DataSource defaultDataSource(@Inject("${layjava.datasource.master}") HikariDataSource dataSource) {
        return dataSource;
    }

}
