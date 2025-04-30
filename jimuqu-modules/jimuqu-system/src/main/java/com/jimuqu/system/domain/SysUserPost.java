package com.jimuqu.system.domain;

import cn.xbatis.db.annotations.Table;
import cn.xbatis.db.annotations.TableId;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

/**
 * 用户和角色关联 sys_user_role
 *
 * @author chengliang
 * @date 2024/06/13
 */
@Data
@FieldNameConstants
@Table("sys_user_post")
public class SysUserPost {

    /**
     * 用户ID
     */
    @TableId
    private Long userId;

    /**
     * 部门ID
     */
    private Long postId;

}
