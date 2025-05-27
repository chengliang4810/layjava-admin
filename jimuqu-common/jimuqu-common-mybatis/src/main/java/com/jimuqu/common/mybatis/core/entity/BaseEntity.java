package com.jimuqu.common.mybatis.core.entity;

import cn.xbatis.core.incrementer.IdentifierGeneratorType;
import cn.xbatis.db.IdAutoType;
import cn.xbatis.db.annotations.TableField;
import cn.xbatis.db.annotations.TableId;
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
public class BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 创建部门
     */
    @TableField(defaultValue = "{CURRENT_DEPT_ID}")
    protected Long createDept;

    /**
     * 创建者
     */
    @TableField(defaultValue = "{CURRENT_USER_ID}")
    protected Long createBy;

    /**
     * 创建时间
     */
    @TableField(defaultValue = "{NOW}")
    protected Date createTime;

    /**
     * 更新者
     */
    @TableField(defaultValue = "{CURRENT_USER_ID}", updateDefaultValue = "{CURRENT_USER_ID}")
    protected Long updateBy;

    /**
     * 更新时间
     */
    @TableField(defaultValue = "{NOW}", updateDefaultValue = "{NOW}")
    protected Date updateTime;

}
