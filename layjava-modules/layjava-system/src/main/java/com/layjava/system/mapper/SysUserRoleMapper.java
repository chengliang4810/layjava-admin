package com.layjava.system.mapper;


import com.layjava.common.dao.core.mapper.BaseMapperPlus;
import com.layjava.system.domain.SysUserRole;

import java.util.List;

/**
 * 用户与角色关联表 数据层
 *
 * @author Lion Li
 */
public interface SysUserRoleMapper extends BaseMapperPlus<SysUserRole> {

    List<Long> selectUserIdsByRoleId(Long roleId);

}
