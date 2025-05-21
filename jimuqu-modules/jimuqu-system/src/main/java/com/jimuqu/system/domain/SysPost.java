package com.jimuqu.system.domain;

import cn.xbatis.db.annotations.Table;
import com.jimuqu.common.mybatis.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 岗位表 sys_post
 *
 * @author Lion Li,chengliang4810
 */

@Data
@Table("sys_post")
@EqualsAndHashCode(callSuper = true)
public class SysPost extends BaseEntity {

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
    private Integer postSort;

    /**
     * 状态（0正常 1停用）
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

}
