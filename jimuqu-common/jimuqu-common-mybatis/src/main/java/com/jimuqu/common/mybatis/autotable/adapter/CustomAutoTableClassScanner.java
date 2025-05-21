package com.jimuqu.common.mybatis.autotable.adapter;

import cn.xbatis.db.annotations.Table;
import org.dromara.autotable.core.AutoTableClassScanner;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * 自定义自动表类扫描器
 * 扫描 xbatis 的注解
 * @author chengliang
 * @date 2025/05/21
 */
public class CustomAutoTableClassScanner extends AutoTableClassScanner {

    @Override
    protected Set<Class<? extends Annotation>> getIncludeAnnotations() {
        Set<Class<? extends Annotation>> includeAnnotations = super.getIncludeAnnotations();
        includeAnnotations.add(Table.class);
//        includeAnnotations.add(AutoTable.class);
        return includeAnnotations;
    }

}
