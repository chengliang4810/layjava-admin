package com.layjava.system.domain;

import cn.xbatis.db.annotations.TableId;
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
    @TableId
    private Long userId;

    /**
     * 角色ID
     */
    @TableId
    private Long roleId;

}
