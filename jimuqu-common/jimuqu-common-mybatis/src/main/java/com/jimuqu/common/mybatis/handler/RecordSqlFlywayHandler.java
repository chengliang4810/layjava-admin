package com.jimuqu.common.mybatis.handler;

import lombok.extern.slf4j.Slf4j;
import org.dromara.autotable.core.AutoTableGlobalConfig;
import org.dromara.autotable.core.config.PropertyConfig;
import org.dromara.autotable.core.recordsql.AutoTableExecuteSqlLog;
import org.dromara.autotable.core.recordsql.RecordSqlFileHandler;
import org.dromara.autotable.core.utils.StringUtils;
import org.noear.solon.annotation.Component;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * 记录sql flyway处理程序
 *
 * @author chengliang4810
 * @since 2025/05/23
 */
@Slf4j
@Component
public class RecordSqlFlywayHandler extends RecordSqlFileHandler {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    @Override
    protected Path getFilePath(AutoTableExecuteSqlLog autoTableExecuteSqlLog) {

        PropertyConfig.RecordSqlProperties recordSql = AutoTableGlobalConfig.getAutoTableProperties().getRecordSql();

        String folderPath = recordSql.getFolderPath();

        if (StringUtils.noText(folderPath)) {
            log.error("没有指定SQL日志文件目录，无法记录SQL执行记录");
            return null;
        }

        String sqlFilename = "V{version}_{time}__{table}.sql"
                .replace("{version}", autoTableExecuteSqlLog.getVersion())
                .replace("{time}", LocalDateTime.ofInstant(Instant.ofEpochMilli(autoTableExecuteSqlLog.getExecutionTime()), ZoneId.systemDefault()).format(dateTimeFormatter))
                .replace("{table}", autoTableExecuteSqlLog.getTableName());


        return Paths.get(folderPath, sqlFilename);
    }

}
