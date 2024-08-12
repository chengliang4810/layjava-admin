package com.layjava.generator.mapper;

import com.layjava.common.mybatis.core.mapper.BaseMapperPlus;
import com.layjava.generator.domain.GenTable;
import com.mybatisflex.core.paginate.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 业务 数据层
 *
 * @author Lion Li
 */
public interface GenTableMapper extends BaseMapperPlus<GenTable> {

    /**
     * 查询据库列表
     *
     * @param genTable 查询条件
     * @return 数据库表集合
     */
    Page<GenTable> selectPageDbTableList(@Param("page") Page<GenTable> page, @Param("genTable") GenTable genTable);

    /**
     * 查询据库列表
     *
     * @param tableNames 表名称组
     * @return 数据库表集合
     */
    List<GenTable> selectDbTableListByNames(String[] tableNames);

    /**
     * 查询所有表信息
     *
     * @return 表信息集合
     */
    List<GenTable> selectGenTableAll();


    /**
     * 查询表名称业务信息
     *
     * @param tableName 表名称
     * @return 业务信息
     */
    GenTable selectGenTableByName(String tableName);

    List<String> selectTableNameList(String dataName);
}
