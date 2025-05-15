package com.jimuqu.system.mapper;

import com.jimuqu.common.mybatis.core.mapper.BaseMapperPlus;
import com.jimuqu.system.domain.SysRole;
import com.jimuqu.system.domain.vo.SysRoleVo;


/**
 * 角色表 数据层
 *
 * @author Lion Li,chengliang4810
 */
public interface SysRoleMapper extends BaseMapperPlus<SysRole, SysRoleVo> {

//    default Page<SysRoleVo> selectPageRoleList(@Param("pageQuery") PageQuery pageQuery, QueryWrapper queryWrapper) {
////        selectRoleVo(queryWrapper);
////        return paginateAs(pageQuery, queryWrapper, SysRoleVo.class, DataColumn.of("deptName", "d.dept_id"), DataColumn.of("userName", "r.create_by"));
//        return null;
//    }

//    /**
//     * 根据条件分页查询角色数据
//     *
//     * @return 角色数据集合信息
//     */
//    default List<SysRoleVo> selectRoleList(QueryWrapper queryWrapper) {
//        selectRoleVo(queryWrapper);
//        return this.selectListByQueryAs(queryWrapper, SysRoleVo.class, DataColumn.of("deptName", "d.dept_id"), DataColumn.of("userName", "r.create_by"));
//    }
//
//    private static void selectRoleVo(QueryWrapper queryWrapper) {
//        queryWrapper.select(QueryMethods.distinct(SYS_ROLE.ROLE_ID), SYS_ROLE.ROLE_NAME, SYS_ROLE.ROLE_KEY, SYS_ROLE.ROLE_SORT, SYS_ROLE.DATA_SCOPE, SYS_ROLE.MENU_CHECK_STRICTLY,
//                        SYS_ROLE.DEPT_CHECK_STRICTLY, SYS_ROLE.STATUS, SYS_ROLE.DEL_FLAG, SYS_ROLE.CREATE_TIME, SYS_ROLE.REMARK
//                )
//                .from(SYS_ROLE.as("r"))
//                .leftJoin(SYS_USER_ROLE).as("sur").on(SYS_USER_ROLE.ROLE_ID.eq(SYS_ROLE.ROLE_ID))
//                .leftJoin(SYS_USER).as("u").on(SYS_USER.USER_ID.eq(SYS_USER_ROLE.USER_ID))
//                .leftJoin(SYS_DEPT).as("d").on(SYS_USER.DEPT_ID.eq(SYS_DEPT.DEPT_ID));
//    }
//
//
//    default SysRoleVo selectRoleById(@Param("roleId") Long roleId) {
//        QueryWrapper queryWrapper = QueryWrapper.create().where(SYS_ROLE.ROLE_ID.eq(roleId));
//        selectRoleVo(queryWrapper);
//        return selectOneByQueryAs(queryWrapper, SysRoleVo.class, DataColumn.of("deptName", "d.dept_id"), DataColumn.of("userName", "r.create_by"));
//    }
//
//    /**
//     * 根据用户ID查询角色
//     *
//     * @param userId 用户ID
//     * @return 角色列表
//     */
//    default List<SysRoleVo> selectRolePermissionByUserId(Long userId) {
//        QueryWrapper queryWrapper = QueryWrapper.create().where(SYS_USER_ROLE.USER_ID.eq(userId));
//        selectRoleVo(queryWrapper);
//        return selectListByQueryAs(queryWrapper, SysRoleVo.class);
//    }
//
//
//    /**
//     * 根据用户ID获取角色选择框列表
//     *
//     * @param userId 用户ID
//     * @return 选中角色ID列表
//     */
//    default List<Long> selectRoleListByUserId(Long userId) {
//        QueryWrapper queryWrapper = QueryWrapper.create().select(SYS_ROLE.ROLE_ID).from(SYS_ROLE.as("r"))
//                .leftJoin(SYS_USER_ROLE).as("sur").on(SYS_USER_ROLE.ROLE_ID.eq(SYS_ROLE.ROLE_ID))
//                .leftJoin(SYS_USER).as("u").on(SYS_USER.USER_ID.eq(SYS_USER_ROLE.USER_ID))
//                .where(SYS_USER.USER_ID.eq(userId));
//        return selectListByQueryAs(queryWrapper, Long.class);
//    }
//
//    /**
//     * 根据用户ID查询角色
//     *
//     * @param userName 用户名
//     * @return 角色列表
//     */
//    default List<SysRoleVo> selectRolesByUserName(String userName) {
//        QueryWrapper queryWrapper = QueryWrapper.create().select(SYS_ROLE.ROLE_ID, SYS_ROLE.ROLE_NAME, SYS_ROLE.ROLE_KEY, SYS_ROLE.ROLE_SORT).from(SYS_ROLE.as("r"))
//                .leftJoin(SYS_USER_ROLE).as("sur").on(SYS_USER_ROLE.ROLE_ID.eq(SYS_ROLE.ROLE_ID))
//                .leftJoin(SYS_USER).as("u").on(SYS_USER.USER_ID.eq(SYS_USER_ROLE.USER_ID))
//                .where(SYS_USER.USER_NAME.eq(userName));
//        return selectListByQueryAs(queryWrapper, SysRoleVo.class);
//
//    }

}
