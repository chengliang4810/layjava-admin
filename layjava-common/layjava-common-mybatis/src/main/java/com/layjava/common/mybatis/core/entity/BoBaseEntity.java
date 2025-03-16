package com.layjava.common.mybatis.core.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

/**
 * BoEntity基类
 *
 * @author chengliang
 * @date 2025/03/16
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BoBaseEntity extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 拓展参数
     */
    private Map<String, Object> params = new HashMap<>();
}
