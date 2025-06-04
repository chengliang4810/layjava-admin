package com.jimuqu.system.domain.query;

import cn.xbatis.core.sql.ObjectConditionLifeCycle;
import cn.xbatis.db.annotations.Condition;
import cn.xbatis.db.annotations.ConditionTarget;
import com.jimuqu.system.domain.SysPost;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import java.io.Serial;
import java.io.Serializable;

import static cn.xbatis.db.annotations.Condition.Type.EQ;

/**
 * 岗位信息查询条件对象
 * @author chengliang4810
 * @since 2025-06-04
 */
@Data
@FieldNameConstants
@Accessors(chain = true)
@ConditionTarget(SysPost.class)
public class SysPostQuery implements Serializable, ObjectConditionLifeCycle {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 岗位ID
     */
    @Condition(value = EQ)
    private Long postId;
    /**
     * 部门id
     */
    @Condition(value = EQ)
    private Long deptId;
    /**
     * 岗位编码
     */
    @Condition(value = EQ)
    private String postCode;
    /**
     * 岗位类别编码
     */
    @Condition(value = EQ)
    private String postCategory;
    /**
     * 岗位名称
     */
    @Condition(value = EQ)
    private String postName;
    /**
     * 显示顺序
     */
    @Condition(value = EQ)
    private Long postSort;
    /**
     * 状态（0正常 1停用）
     */
    @Condition(value = EQ)
    private String status;
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
