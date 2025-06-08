package com.jimuqu.common.mybatis.handler;

import lombok.extern.slf4j.Slf4j;
import org.dromara.autotable.core.recordsql.AutoTableExecuteSqlLog;
import org.dromara.autotable.core.recordsql.RecordSqlFileHandler;
import org.dromara.autotable.core.recordsql.RecordSqlHandler;
import org.noear.solon.annotation.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * 记录sql flyway处理程序
 * 可按需要修改
 * @author chengliang4810
 * @since 2025/05/23
 */
@Slf4j
@Component
public class RecordSqlFlywayHandler extends RecordSqlFileHandler implements RecordSqlHandler {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    /**
     * 希望自定义文件名称的话，可以重写此方法
     *
     * @param autoTableExecuteSqlLog 执行sql日志
     */
    @Override
    protected String getFileName(AutoTableExecuteSqlLog autoTableExecuteSqlLog) {
        return "V{version}_{time}__{table}.sql"
                .replace("{version}", autoTableExecuteSqlLog.getVersion())
                .replace("{time}", LocalDateTime.ofInstant(Instant.ofEpochMilli(autoTableExecuteSqlLog.getExecutionTime()), ZoneId.systemDefault()).format(dateTimeFormatter))
                .replace("{table}", autoTableExecuteSqlLog.getTableName());
    }
}
