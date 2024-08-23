package com.layjava.common.mybatis.handler;

import com.layjava.common.core.utils.DateUtil;
import com.layjava.common.core.utils.StringUtil;
import com.mybatisflex.core.audit.AuditManager;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Condition;
import org.noear.solon.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * sql打印配置
 */
@Configuration
@Condition(onProperty = "${layjava.sql.print: false} = true" )
public class SqlPrintHandler {

    @Bean
    public void sqlPrint() {
        //开启审计功能
        AuditManager.setAuditEnable(true);
        //设置 SQL 审计收集器
        AuditManager.setMessageCollector(auditMessage -> {
                    System.err.println(StringUtil.format(" Consume Time: {} ms {}", auditMessage.getElapsedTime(), LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateUtil.YYYY_MM_DD_HH_MM_SS))));
                    System.err.println(StringUtil.format(" Execute SQL : {}", auditMessage.getFullSql()));
                    System.err.println();
                }
        );
    }

}
