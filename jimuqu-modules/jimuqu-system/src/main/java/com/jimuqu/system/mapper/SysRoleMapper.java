package com.jimuqu.system.mapper;

import com.jimuqu.common.mybatis.core.mapper.BaseMapperPlus;
import com.jimuqu.system.domain.SysRole;
import com.jimuqu.system.domain.vo.SysRoleVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色信息数据层
 * @author chengliang4810
 * @since 2025-06-05
 */
@Mapper
public interface SysRoleMapper extends BaseMapperPlus<SysRole, SysRoleVo> {

}