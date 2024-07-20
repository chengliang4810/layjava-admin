package com.layjava.common.dao.core.entity;

import com.mybatisflex.annotation.Column;
import lombok.Data;

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
// @FieldNameConstants(onlyExplicitlyIncluded = true)
public class BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 创建部门
     */
    private Long createDept;

    /**
     * 创建者
     */
    private Long createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    private Long updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 请求参数
     */
    // @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(ignore = true)
    private Map<String, Object> params = new HashMap<>();

}
