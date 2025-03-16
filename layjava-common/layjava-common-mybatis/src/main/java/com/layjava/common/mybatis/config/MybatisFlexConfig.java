package com.layjava.common.mybatis.config;

import com.layjava.common.mybatis.core.entity.BaseEntity;
import com.layjava.common.mybatis.listener.BaseEntityInsertListener;
import com.layjava.common.mybatis.listener.BaseEntityUpdateListener;
import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.query.QueryColumnBehavior;
import com.mybatisflex.solon.MyBatisFlexCustomizer;
import org.noear.solon.annotation.Component;

/**
 * ORM框架配置
 *
 * @author chengliang
 * @since 2024/02/27
 */
@Component
public class MybatisFlexConfig implements MyBatisFlexCustomizer {

    static {
        // 全局自动忽略策略 null
        QueryColumnBehavior.setIgnoreFunction(QueryColumnBehavior.IGNORE_NULL);
        // 当 IN(...) 条件只有 1 个参数时，自动把的内容转换为相等。
        QueryColumnBehavior.setSmartConvertInToEquals(true);
    }

    @Override
    public void customize(FlexGlobalConfig flexGlobalConfig) {
        // BaseEntity数据填充
        flexGlobalConfig.registerInsertListener(new BaseEntityInsertListener(), BaseEntity.class);
        flexGlobalConfig.registerUpdateListener(new BaseEntityUpdateListener(), BaseEntity.class);
    }
}
