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
@Table("sys_post")
public class SysPost {

    /**
     * 部门ID
     */
    @Id
    private Long postId;


    /**
     * 部门名称
     */
    private String postName;

}
