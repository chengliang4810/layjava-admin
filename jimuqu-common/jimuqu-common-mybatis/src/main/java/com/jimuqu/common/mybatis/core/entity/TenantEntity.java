package com.jimuqu.common.mybatis.core.entity;

import cn.xbatis.db.annotations.TenantId;

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
    protected Long tenantId;

}
