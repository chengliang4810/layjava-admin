package com.layjava.common.dao.config;

import cn.hutool.core.net.NetUtil;
import com.easy.query.solon.annotation.Db;
import com.zaxxer.hikari.HikariDataSource;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;

import javax.sql.DataSource;

/**
 * ORM框架配置
 *
 * @author chengliang
 * @since 2024/02/27
 */
@Configuration
public class EasyQueryConfig {

//    @Bean
//    public void mybatisPlusConfig(@Db("default") MybatisConfiguration cfg) {
//        // 配置插件
//        MybatisPlusInterceptor plusInterceptor = new MybatisPlusInterceptor();
//        plusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
//        plusInterceptor.addInnerInterceptor(optimisticLockerInnerInterceptor());
//        cfg.addInterceptor(plusInterceptor);
//    }
//
//    /**
//     * 使用网卡信息绑定雪花生成器
//     * 防止集群雪花ID重复
//     */
//    @Bean
//    public IdentifierGenerator idGenerator() {
//        return new DefaultIdentifierGenerator(NetUtil.getLocalhost());
//    }
//
//    /**
//     * 乐观锁插件
//     */
//    public OptimisticLockerInnerInterceptor optimisticLockerInnerInterceptor() {
//        return new OptimisticLockerInnerInterceptor();
//    }


}
