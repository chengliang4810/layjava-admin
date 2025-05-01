package com.jimuqu.system.mapper;

import cn.xbatis.core.mybatis.mapper.context.Pager;
import cn.xbatis.core.sql.executor.Where;
import cn.xbatis.core.sql.executor.chain.QueryChain;
import cn.xbatis.core.sql.executor.chain.UpdateChain;
import com.jimuqu.common.mybatis.core.mapper.BaseMapperPlus;
import com.jimuqu.common.mybatis.core.page.PageQuery;
import com.jimuqu.system.domain.SysDept;
import com.jimuqu.system.domain.SysRole;
import com.jimuqu.system.domain.SysUser;
import com.jimuqu.system.domain.SysUserRole;
import com.jimuqu.system.domain.vo.SysUserVo;

import java.util.List;


/**
 * 用户表 数据层
 *
 * @author Lion Li,chengliang4810
 */
public interface SysUserMapper extends BaseMapperPlus<SysUser, SysUserVo> {

    default Pager<SysUserVo> selectPageUserList(PageQuery pageQuery, Where queryWrapper) {
        return QueryChain.of(this, queryWrapper)
                .leftJoin(SysUser::getDeptId, SysDept::getDeptId)
                .leftJoin(SysUser::getId, SysUserRole::getUserId)
                .leftJoin(SysUserRole::getRoleId, SysRole::getRoleId)
                .where(queryWrapper)
                .returnType(SysUserVo.class)
                .paging(pageQuery.build());
    }

    /**
     * 根据条件分页查询用户列表
     *
     * @param queryWrapper 查询条件
     * @return 用户信息集合信息
     */


    default List<SysUserVo> selectUserList(Where queryWrapper) {
        return QueryChain.of(this, queryWrapper)
                .leftJoin(SysUser::getDeptId, SysDept::getDeptId)
                .leftJoin(SysUser::getId, SysUserRole::getUserId)
                .leftJoin(SysUserRole::getRoleId, SysRole::getRoleId)
                .where(queryWrapper)
                .returnType(SysUserVo.class).list();
    }

    /**
     * 根据条件分页查询已配用户角色列表
     *
     * @param queryWrapper 查询条件
     * @return 用户信息集合信息
     */
    default Pager<SysUserVo> selectAllocatedList(PageQuery page, Where queryWrapper) {
        return new Pager<>();
//        return this.paginateAs(page, queryWrapper, SysUserVo.class, DataColumn.of("deptName", "d.dept_id"), DataColumn.of("userName", "u.user_id"));
    }


    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    default SysUserVo selectUserByUserName(String userName) {
        //     //@RelationOneToOne(selfField = "deptId", joinSelfColumn = "dept_id", targetField = "deptId", joinTargetColumn = "dept_id", targetTable = "sys_dept")
        return QueryChain.of(this)
                .leftJoin(SysUser::getDeptId, SysDept::getDeptId)
                .leftJoin(SysUser::getId, SysUserRole::getUserId)
                .leftJoin(SysUserRole::getRoleId, SysRole::getRoleId)
                .eq(SysUser::getUserName, userName)
                .returnType(SysUserVo.class).get();
    }

    /**
     * 通过手机号查询用户
     *
     * @param phonenumber 手机号
     * @return 用户对象信息
     */
    default SysUserVo selectUserByPhonenumber(String phonenumber) {
        return QueryChain.of(this)
                .leftJoin(SysUser::getDeptId, SysDept::getDeptId)
                .leftJoin(SysUser::getId, SysUserRole::getUserId)
                .leftJoin(SysUserRole::getRoleId, SysRole::getRoleId)
                .eq(SysUser::getPhonenumber, phonenumber)
                .returnType(SysUserVo.class).get();
    }

    /**
     * 通过邮箱查询用户
     *
     * @param email 邮箱
     * @return 用户对象信息
     */
    default SysUserVo selectUserByEmail(String email) {
        return QueryChain.of(this)
                .leftJoin(SysUser::getDeptId, SysDept::getDeptId)
                .leftJoin(SysUser::getId, SysUserRole::getUserId)
                .leftJoin(SysUserRole::getRoleId, SysRole::getRoleId)
                .eq(SysUser::getEmail, email)
                .returnType(SysUserVo.class).get();
    }

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */

    default SysUserVo selectUserById(Long userId) {
        return QueryChain.of(this)
                .leftJoin(SysUser::getDeptId, SysDept::getDeptId)
                .leftJoin(SysUser::getId, SysUserRole::getUserId)
                .leftJoin(SysUserRole::getRoleId, SysRole::getRoleId)
                .eq(SysUser::getId, userId)
                .returnType(SysUserVo.class).get();
    }


    default boolean update(UpdateChain updateChain) {
        return true;
//        return this.update(updateChain, DataColumn.of("deptName", "dept_id"), DataColumn.of("userName", "user_id"));
    }


}
