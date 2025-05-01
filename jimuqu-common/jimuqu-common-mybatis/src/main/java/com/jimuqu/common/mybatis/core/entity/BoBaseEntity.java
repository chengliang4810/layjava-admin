package com.jimuqu.common.mybatis.core.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * BoEntity基类
 *
 * @author chengliang
 * @date 2025/03/16
 */
@Data
public class BoBaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 自由拓展参数
     */
    private Map<String, Object> params = new HashMap<>();
}
