package com.layjava.common.mybatis.enums;

import com.layjava.common.core.utils.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据库类型
 *
 * @author Lion Li,chengliang4810
 */
@Getter
@AllArgsConstructor
public enum DataBaseType {

    /**
     * MySQL
     */
    MY_SQL("MySQL" ),

    /**
     * Oracle
     */
    ORACLE("Oracle" ),

    /**
     * PostgreSQL
     */
    POSTGRE_SQL("PostgreSQL" ),

    /**
     * SQLite
     */
    SQLITE("SQLite" ),

    /**
     * SQL Server
     */
    SQL_SERVER("Microsoft SQL Server" );

    private final String type;

    public static DataBaseType find(String databaseProductName) {
        if (StringUtil.isBlank(databaseProductName)) {
            return null;
        }
        for (DataBaseType type : values()) {
            if (type.getType().equals(databaseProductName)) {
                return type;
            }
        }
        return null;
    }
}
