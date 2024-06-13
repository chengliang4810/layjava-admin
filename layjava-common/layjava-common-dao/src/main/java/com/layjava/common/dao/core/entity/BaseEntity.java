package com.layjava.common.dao.core.entity;

import com.easy.query.core.annotation.ColumnIgnore;
import com.easy.query.core.annotation.UpdateIgnore;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Entity基类
 *
 * @author chengliang
 * @date 2024/06/13
 */


@Data
@FieldNameConstants(onlyExplicitlyIncluded = true)
public class BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 搜索值
     */
    @ColumnIgnore
    private transient String searchValue;

    /**
     * 创建部门
     */
    @UpdateIgnore
    private Long createDept;

    /**
     * 创建者
     */
    @UpdateIgnore
    private Long createBy;

    /**
     * 创建时间
     */
    @UpdateIgnore
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    @FieldNameConstants.Include
    private Long updateBy;

    /**
     * 更新时间
     */
    @FieldNameConstants.Include
    private LocalDateTime updateTime;

    /**
     * 请求参数
     */
    @ColumnIgnore
    private Map<String, Object> params = new HashMap<>();

}
