package com.layjava.common.mybatis.core.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * Entity基类
 *
 * @author chengliang
 * @date 2024/06/13
 */

@Data
// @FieldNameConstants(onlyExplicitlyIncluded = true)
public class BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 创建部门
     */
    protected Long createDept;

    /**
     * 创建者
     */
    protected Long createBy;

    /**
     * 创建时间
     */
    protected Date createTime;

    /**
     * 更新者
     */
    protected Long updateBy;

    /**
     * 更新时间
     */
    protected Date updateTime;

}
