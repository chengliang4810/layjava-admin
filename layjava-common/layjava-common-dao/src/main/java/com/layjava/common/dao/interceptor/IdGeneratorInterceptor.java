package com.layjava.common.dao.interceptor;

import cn.hutool.core.collection.CollUtil;
import com.easy.query.core.basic.extension.interceptor.EntityInterceptor;
import com.easy.query.core.expression.sql.builder.EntityInsertExpressionBuilder;
import com.easy.query.core.expression.sql.builder.EntityUpdateExpressionBuilder;
import com.easy.query.core.metadata.ColumnMetadata;
import com.easy.query.core.metadata.EntityMetadata;
import com.layjava.common.core.util.IdUtil;
import org.noear.solon.annotation.Component;

import java.util.Collection;

/**
 * Id主键生成拦截器
 */
@Component
public class IdGeneratorInterceptor implements EntityInterceptor {

    @Override
    public void configureInsert(Class<?> entityClass, EntityInsertExpressionBuilder entityInsertExpressionBuilder, Object entity) {

        // 主键填充
        EntityMetadata entityMetadata = entityInsertExpressionBuilder.getRuntimeContext().getEntityMetadataManager().getEntityMetadata(entityClass);
        // 找到所有主键
        Collection<String> keyProperties = entityMetadata.getKeyProperties();
        if (CollUtil.isEmpty(keyProperties)){
            return;
        }

        keyProperties.forEach(key -> {
            ColumnMetadata columnMetadata = entityMetadata.getColumnOrNull(key);
            if(columnMetadata == null){
                return;
            }
            Object value = columnMetadata.getGetterCaller().apply(entity);
            if(value == null) {
                columnMetadata.getSetterCaller().call(entity, IdUtil.getId());
            }
        });



    }

    @Override
    public void configureUpdate(Class<?> aClass, EntityUpdateExpressionBuilder entityUpdateExpressionBuilder, Object o) {

    }

    @Override
    public String name() {
        return "IdGeneratorInterceptor";
    }

    @Override
    public boolean apply(Class<?> aClass) {
        return true;
    }

    /**
     * 前置Id生成器拦截器的执行顺序
     */
    @Override
    public int order() {
        return 10;
    }

}
