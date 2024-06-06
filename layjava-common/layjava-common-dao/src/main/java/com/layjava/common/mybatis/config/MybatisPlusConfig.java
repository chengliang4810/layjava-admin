package com.layjava.common.mybatis.config;

import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.BooleanUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.solon.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.solon.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.solon.plugins.inner.PaginationInnerInterceptor;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.solon.annotation.Db;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Import;
import org.noear.solon.annotation.Inject;

import javax.sql.DataSource;

/**
 * 数据源配置
 *
 * @author chengliang
 * @since 2024/02/27
 */
@Configuration
@Import(profilesIfAbsent = "classpath:common-mybatis-plus.yml")
public class MybatisPlusConfig {

    @Bean(value = "default", typed = true)
    public DataSource defaultDataSource(@Inject("${layjava.datasource.default}") HikariDataSource dataSource, @Inject(value = "${layjava.datasource.default.p6spy}", required = false) Boolean p6spy) {
        if (BooleanUtil.isTrue(p6spy)) {
            dataSource.setDriverClassName("com.p6spy.engine.spy.P6SpyDriver");
        }
        return dataSource;
    }

    @Bean
    public void mybatisPlusConfig(@Db("default") MybatisConfiguration cfg) {
        // 配置插件
        MybatisPlusInterceptor plusInterceptor = new MybatisPlusInterceptor();
        plusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        plusInterceptor.addInnerInterceptor(optimisticLockerInnerInterceptor());
        cfg.addInterceptor(plusInterceptor);
    }

    /**
     * 使用网卡信息绑定雪花生成器
     * 防止集群雪花ID重复
     */
    @Bean
    public IdentifierGenerator idGenerator() {
        return new DefaultIdentifierGenerator(NetUtil.getLocalhost());
    }

    /**
     * 乐观锁插件
     */
    public OptimisticLockerInnerInterceptor optimisticLockerInnerInterceptor() {
        return new OptimisticLockerInnerInterceptor();
    }
}
