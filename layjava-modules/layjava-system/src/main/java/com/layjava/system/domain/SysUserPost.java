package com.layjava.system.domain;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
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
    @Id
    private Long userId;

    /**
     * 部门ID
     */
    private Long postId;

}
