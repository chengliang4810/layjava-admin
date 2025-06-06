package com.jimuqu.system.domain.vo;

import cn.xbatis.db.annotations.ResultEntity;
import com.jimuqu.system.domain.SysPost;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import java.io.Serial;
import java.io.Serializable;

/**
 * 岗位信息视图对象
 * @author chengliang4810
 * @since 2025-06-04
 */
@Data
@FieldNameConstants
@Accessors(chain = true)
@ResultEntity(SysPost.class)
@AutoMapper(target = SysPost.class)
public class SysPostVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 岗位ID
     */
    private Long postId;
    /**
     * 部门id
     */
    private Long deptId;
    /**
     * 岗位编码
     */
    private String postCode;
    /**
     * 岗位类别编码
     */
    private String postCategory;
    /**
     * 岗位名称
     */
    private String postName;
    /**
     * 显示顺序
     */
    private Long postSort;
    /**
     * 状态（0正常 1停用）
     */
    private String status;
    /**
     * 备注
     */
    private String remark;

}
