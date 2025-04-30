package com.jimuqu.system.mapper;

import cn.xbatis.core.mybatis.mapper.context.Pager;
import cn.xbatis.core.sql.executor.Where;
import cn.xbatis.core.sql.executor.chain.UpdateChain;
import com.jimuqu.common.mybatis.core.mapper.BaseMapperPlus;
import com.jimuqu.common.mybatis.core.page.PageQuery;
import com.jimuqu.system.domain.SysUser;
import com.jimuqu.system.domain.vo.SysUserVo;
import org.dromara.hutool.core.collection.ListUtil;

import java.util.List;


/**
 * 用户表 数据层
 *
 * @author Lion Li,chengliang4810
 */
public interface SysUserMapper extends BaseMapperPlus<SysUser, SysUserVo> {

    default Pager<SysUserVo> selectPageUserList(PageQuery pageQuery, Where queryWrapper) {
        return null;
//        return this.paging(pageQuery, queryWrapper, SysUserVo.class, DataColumn.of("deptName", "u.dept_id"), DataColumn.of("userName", "u.user_id"));
    }

    /**
     * 根据条件分页查询用户列表
     *
     * @param queryWrapper 查询条件
     * @return 用户信息集合信息
     */


    default List<SysUserVo> selectUserList(Where queryWrapper) {
        return ListUtil.zero();
//        return this.selectListWithRelationsByQueryAs(queryWrapper, SysUserVo.class, DataPermission.of(
//                        DataColumn.of("deptName", "u.dept_id"),
//                        DataColumn.of("userName", "u.user_id")
//                )
//        );
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
//        Where where = Where.create().eq(SysUser::getUserName, userName);
//        return selectOneWithRelationsByQueryAs(queryWrapper, SysUserVo.class);
        return null;
    }

    /**
     * 通过手机号查询用户
     *
     * @param phonenumber 手机号
     * @return 用户对象信息
     */
    default SysUserVo selectUserByPhonenumber(String phonenumber) {
//        QueryWrapper queryWrapper = QueryWrapper.create().where(SYS_USER.PHONENUMBER.eq(phonenumber));
//        return selectOneWithRelationsByQueryAs(queryWrapper, SysUserVo.class);
        return null;
    }

    /**
     * 通过邮箱查询用户
     *
     * @param email 邮箱
     * @return 用户对象信息
     */
    default SysUserVo selectUserByEmail(String email) {
//        QueryWrapper queryWrapper = QueryWrapper.create().where(SYS_USER.EMAIL.eq(email));
//        return selectOneWithRelationsByQueryAs(queryWrapper, SysUserVo.class);
        return null;
    }

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */

    default SysUserVo selectUserById(Long userId) {
//        QueryWrapper queryWrapper = QueryWrapper.create().where(SysUser::getUserId).eq(userId);
//        return selectOneWithRelationsByQueryAs(queryWrapper, SysUserVo.class, DataColumn.of("deptName", "dept_id"), DataColumn.of("userName", "user_id"));
        return null;
    }


    default boolean update(UpdateChain updateChain) {
        return true;
//        return this.update(updateChain, DataColumn.of("deptName", "dept_id"), DataColumn.of("userName", "user_id"));
    }


}
