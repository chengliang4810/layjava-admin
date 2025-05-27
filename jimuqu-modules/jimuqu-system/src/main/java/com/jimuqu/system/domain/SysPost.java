package com.jimuqu.system.domain;

import cn.xbatis.db.annotations.LogicDelete;
import cn.xbatis.db.annotations.Table;
import com.jimuqu.common.mybatis.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.dromara.autotable.annotation.AutoColumn;

import java.io.Serial;

/**
 * 岗位表 sys_post
 *
 * @author Lion Li,chengliang4810
 */

@Data
@NoArgsConstructor
@FieldNameConstants
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_post")
public class SysPost extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 岗位编码
     */
    private String postCode;

    /**
     * 岗位名称
     */
    private String postName;

    /**
     * 岗位排序
     */
    @AutoColumn(comment = "岗位排序", length = -1, defaultValue = "", type = "")
    private Integer postSort;

    /**
     * 状态（0正常 1停用）
     */
    @LogicDelete(beforeValue = "0", afterValue = "1")
    private String status;

    /**
     * 备注
     */
    private String remark;

}
