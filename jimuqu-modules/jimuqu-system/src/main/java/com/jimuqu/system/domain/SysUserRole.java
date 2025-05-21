package com.jimuqu.system.domain;

import cn.xbatis.db.annotations.Table;
import lombok.Data;

/**
 * 用户和角色关联 sys_user_role
 *
 * @author Lion Li,chengliang4810
 */

@Data
@Table("sys_user_role")
public class SysUserRole {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

}
