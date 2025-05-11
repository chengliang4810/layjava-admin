package com.jimuqu.common.mybatis.core.entity;

import cn.xbatis.db.annotations.TenantId;
import org.dromara.autotable.annotation.AutoColumn;

/**
 * 租户实体抽象类
 *
 * @author chengliang
 * @since 2025/05/01
 */
public class TenantEntity extends BaseEntity {

    /**
     * 租户Id
     */
    @TenantId
    @AutoColumn(comment = "租户Id")
    protected Long tenantId;

}
