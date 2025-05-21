package com.jimuqu.common.mybatis.autotable.adapter;

import cn.xbatis.db.IdAutoType;
import cn.xbatis.db.annotations.Ignore;
import cn.xbatis.db.annotations.Ignores;
import cn.xbatis.db.annotations.TableId;
import com.github.therapi.runtimejavadoc.ClassJavadoc;
import com.github.therapi.runtimejavadoc.CommentFormatter;
import com.github.therapi.runtimejavadoc.FieldJavadoc;
import com.github.therapi.runtimejavadoc.RuntimeJavadoc;
import org.dromara.autotable.core.AutoTableMetadataAdapter;
import org.dromara.autotable.solon.util.AnnotatedElementUtilsPlus;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * 自定义自动表元数据适配器
 *
 * @author chengliang
 * @date 2025/05/21
 */
public class CustomAutoTableMetadataAdapter implements AutoTableMetadataAdapter {

    private static final CommentFormatter formatter = new CommentFormatter();

    /**
     * 忽略字段
     *
     * @param field 场
     * @param clazz clazz
     * @return boolean
     */
    @Override
    public boolean isIgnoreField(Field field, Class<?> clazz) {

        Ignore ignore = AnnotatedElementUtilsPlus.findDeepMergedAnnotation(field, Ignore.class);
        if (ignore != null) {
            return true;
        }

        // 通过Ignores判断是否忽略
        Ignores ignoresAnnotation = AnnotatedElementUtilsPlus.findDeepMergedAnnotation(field, Ignores.class);
        if (ignoresAnnotation != null) {
            return Arrays.stream(ignoresAnnotation.value()).anyMatch(property -> property.equals(field.getName()));
        }
        return false;
    }

    /**
     * 主键
     *
     * @param field 字段
     * @param clazz 类型
     * @return boolean
     */
    @Override
    public boolean isPrimary(Field field, Class<?> clazz) {
        if (AnnotatedElementUtilsPlus.findDeepMergedAnnotation(field, TableId.class) != null) {
            return true;
        }

        return "id".equals(field.getName());
    }

    /**
     * 是自动递增
     *
     * @param field 字段
     * @param clazz 类型
     * @return boolean
     */
    @Override
    public boolean isAutoIncrement(Field field, Class<?> clazz) {

        if (!isPrimary(field, clazz)) {
            return false;
        }

        TableId tableId = AnnotatedElementUtilsPlus.findDeepMergedAnnotation(field, TableId.class);
        return tableId != null && tableId.value() == IdAutoType.AUTO;
    }

    /**
     * 表注释
     * @param clazz 实体类
     * @return
     */
    @Override
    public String getTableComment(Class<?> clazz) {
        ClassJavadoc classDoc = RuntimeJavadoc.getJavadoc(clazz);
        if (classDoc != null && !classDoc.isEmpty()) {
            return formatter.format(classDoc.getComment());
        }
        return null;
    }

    /**
     * 字段注释
     * @param field 字段
     * @param clazz 实体类
     * @return 字段注释
     */
    @Override
    public String getColumnComment(Field field, Class<?> clazz) {
        FieldJavadoc fieldJavadoc = RuntimeJavadoc.getJavadoc(field);
        if (fieldJavadoc!= null &&!fieldJavadoc.isEmpty()) {
            return formatter.format(fieldJavadoc.getComment());
        }
        return null;
    }
}
