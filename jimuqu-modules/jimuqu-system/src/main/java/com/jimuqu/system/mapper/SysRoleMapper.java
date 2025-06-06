package com.jimuqu.system.mapper;

import cn.xbatis.core.sql.executor.SubQuery;
import cn.xbatis.core.sql.executor.chain.QueryChain;
import com.jimuqu.common.mybatis.core.mapper.BaseMapperPlus;
import com.jimuqu.system.domain.SysRole;
import com.jimuqu.system.domain.SysUser;
import com.jimuqu.system.domain.SysUserRole;
import com.jimuqu.system.domain.vo.SysRoleVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色信息数据层
 *
 * @author chengliang4810
 * @since 2025-06-05
 */
@Mapper
public interface SysRoleMapper extends BaseMapperPlus<SysRole, SysRoleVo> {

    default List<SysRoleVo> selectRolesByUserId(Long userId) {
        // select r.role_id,
        //               r.role_name,
        //               r.role_key,
        //               r.role_sort,
        //               r.data_scope,
        //               r.status
        //        from sys_role r
        //        WHERE r.del_flag = '0' and r.role_id in (select role_id from sys_user_role where user_id = #{userId})
        return QueryChain.of(this)
                .select(SysRole.class)
                .eq(SysRole::getDelFlag, "0")
//                .connect(sysRoleQueryChain -> {
//                    QueryChain.of(this)
//                       .select(SysUserRole::getRoleId)
//                       .eq(SysUserRole::getUserId, userId)
//                       .returnType(Long.class).list()
//                })
                .in(SysRole::getId, new SubQuery()
                        .select(SysUserRole::getRoleId)
                        .from(SysUserRole.class)
                        .eq(SysUserRole::getUserId, userId))
                .returnType(SysRoleVo.class).list();
    }

    /**
     * 根据用户ID查询角色
     *
     * @param roleId 角色 Id
     * @return 角色列表
     */
    default SysRoleVo selectRoleById(Long roleId) {
        return QueryChain.of(this)
                .select(SysRole.class)
                .leftJoin(SysUserRole::getRoleId, SysRole::getId)
                .leftJoin(SysUser::getId, SysUserRole::getUserId)
                .leftJoin(SysUser::getDeptId, SysUser::getDeptId)
                .eq(SysRole::getDelFlag, "0")
                .eq(SysRole::getId, roleId)
                .returnType(SysRoleVo.class)
                .get();
    }

}