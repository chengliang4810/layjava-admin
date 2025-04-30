package com.jimuqu.system.mapper;

import cn.xbatis.core.sql.executor.Where;
import com.jimuqu.common.mybatis.core.mapper.BaseMapperPlus;
import com.jimuqu.system.domain.SysDept;
import com.jimuqu.system.domain.vo.SysDeptVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 部门管理 数据层
 *
 * @author Lion Li,chengliang4810
 */
public interface SysDeptMapper extends BaseMapperPlus<SysDept, SysDeptVo> {

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

}
