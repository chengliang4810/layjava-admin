package com.jimuqu.system.mapper;

import cn.xbatis.core.sql.executor.Where;
import cn.xbatis.core.sql.executor.chain.QueryChain;
import com.jimuqu.common.mybatis.core.mapper.BaseMapperPlus;
import com.jimuqu.system.domain.SysDept;
import com.jimuqu.system.domain.vo.SysDeptVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 部门数据层
 * @author chengliang4810
 * @since 2025-06-04
 */
@Mapper
public interface SysDeptMapper extends BaseMapperPlus<SysDept, SysDeptVo> {

    /**
     * 根据父部门ID查询其所有子部门的ID列表
     *
     * @param parentId 父部门ID
     * @return 包含子部门的列表
     */
    default List<Long> selectListByParentId(Long parentId) {
        return QueryChain.of(this)
                .select(SysDept::getId)
                .and(SysDept::getAncestors, dept-> dept.mysql().findInSet(parentId))
                .returnType(Long.class)
                .list();
    }


    /**
     * 查询部门管理数据
     *
     * @param queryWrapper 查询条件
     * @return 部门信息集合
     */

    default List<SysDeptVo> selectDeptList(Where queryWrapper) {
//        return selectListByQueryAs(queryWrapper, SysDeptVo.class, DataPermission.of(
//                DataColumn.of("deptName", "dept_id")
//        ));
        return null;
    }

    default SysDeptVo selectDeptById(Long deptId) {
//        QueryWrapper queryWrapper = QueryWrapper.create().where(SYS_DEPT.DEPT_ID.eq(deptId));
//        return selectOneByQueryAs(queryWrapper, SysDeptVo.class, DataColumn.of("deptName", "dept_id"));
        return null;
    }


    /**
     * 根据角色ID查询部门树信息
     *
     * @param roleId            角色ID
     * @param deptCheckStrictly 部门树选择项是否关联显示
     * @return 选中部门列表
     */
    List<Long> selectDeptListByRoleId(@Param("roleId") Long roleId, @Param("deptCheckStrictly") boolean deptCheckStrictly);

    /**
     * 统计部门数
     * @param deptId 部门ID
     * @return 结果
     */
    default int countDeptById(Long deptId){
        return this.count(Where.create().eq(SysDept::getDelFlag, 0).eq(SysDept::getId, deptId));
    }
}