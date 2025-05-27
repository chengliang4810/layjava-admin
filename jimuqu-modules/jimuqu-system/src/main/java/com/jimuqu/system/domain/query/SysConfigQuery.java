package com.jimuqu.system.domain.query;

import cn.xbatis.core.sql.ObjectConditionLifeCycle;
import cn.xbatis.db.annotations.Condition;
import cn.xbatis.db.annotations.ConditionTarget;
import com.jimuqu.system.domain.SysConfig;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.io.Serial;
import java.io.Serializable;

import static cn.xbatis.db.annotations.Condition.Type.*;

/**
 * 参数配置查询条件对象
 * @author chengliang4810
 * @since 2025-05-27
 */
@Data
@FieldNameConstants
@ConditionTarget(SysConfig.class)
public class SysConfigQuery implements Serializable, ObjectConditionLifeCycle {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 参数主键
     */
    @Condition(value = EQ)
    private Long id;
    /**
     * 参数名称
     */
    @Condition(value = EQ)
    private String configName;
    /**
     * 参数键名
     */
    @Condition(value = EQ)
    private String configKey;
    /**
     * 参数键值
     */
    @Condition(value = EQ)
    private String configValue;
    /**
     * 系统内置（Y是 N否）
     */
    @Condition(value = EQ)
    private String configType;
    /**
     * 备注
     */
    @Condition(value = EQ)
    private String remark;

    /**
     * 条件构建前执行
     */
    @Override
    public void beforeBuildCondition() {
        
    }

}
