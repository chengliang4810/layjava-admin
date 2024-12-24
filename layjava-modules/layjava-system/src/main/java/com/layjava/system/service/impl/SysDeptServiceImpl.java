package com.layjava.system.service.impl;

import org.dromara.hutool.core.collection.CollUtil;
import org.dromara.hutool.core.collection.ListUtil;
import org.dromara.hutool.core.convert.ConvertUtil;
import org.dromara.hutool.core.tree.MapTree;
import org.dromara.hutool.core.util.ObjUtil;
import com.layjava.common.core.constant.UserConstants;
import com.layjava.common.core.exception.ServiceException;
import com.layjava.common.core.service.DeptService;
import com.layjava.common.core.utils.MapstructUtil;
import com.layjava.common.core.utils.StringUtil;
import com.layjava.common.core.utils.TreeBuildUtil;
import com.layjava.common.mybatis.helper.DataBaseHelper;
import com.layjava.common.satoken.utils.LoginHelper;
import com.layjava.system.domain.SysDept;
import com.layjava.system.domain.SysRole;
import com.layjava.system.domain.bo.SysDeptBo;
import com.layjava.system.domain.vo.SysDeptVo;
import com.layjava.system.mapper.SysDeptMapper;
import com.layjava.system.mapper.SysRoleMapper;
import com.layjava.system.mapper.SysUserMapper;
import com.layjava.system.service.ISysDeptService;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.update.UpdateWrapper;
import org.apache.ibatis.solon.annotation.Db;
import org.noear.solon.annotation.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.layjava.system.domain.table.SysDeptTableDef.SYS_DEPT;
import static com.layjava.system.domain.table.SysUserTableDef.SYS_USER;


/**
 * 部门管理 服务实现
 *
 * @author Lion Li
 */

@Component
public class SysDeptServiceImpl implements ISysDeptService, DeptService {

    @Db
    private SysDeptMapper baseMapper;
    @Db
    private SysRoleMapper roleMapper;
    @Db
    private SysUserMapper userMapper;

    /**
     * 查询部门管理数据
     *
     * @param dept 部门信息
     * @return 部门信息集合
     */
    @Override
    public List<SysDeptVo> selectDeptList(SysDeptBo dept) {
        return baseMapper.selectDeptList(buildQueryWrapper(dept));
    }

    /**
     * 查询部门树结构信息
     *
     * @param bo 部门信息
     * @return 部门树信息集合
     */
    @Override
    public List<MapTree<Long>> selectDeptTreeList(SysDeptBo bo) {
        // 只查询未禁用部门
        bo.setStatus(UserConstants.DEPT_NORMAL);
        List<SysDeptVo> depts = baseMapper.selectDeptList(buildQueryWrapper(bo));
        return buildDeptTreeSelect(depts);
    }

    private QueryWrapper buildQueryWrapper(SysDeptBo bo) {
        return QueryWrapper.create()
                .select()
                .from(SYS_DEPT)
                .where(SYS_DEPT.DEPT_ID.eq(bo.getDeptId()))
                .and(SYS_DEPT.PARENT_ID.eq(bo.getParentId()))
                .and(SYS_DEPT.DEPT_NAME.like(bo.getDeptName()))
                .and(SYS_DEPT.STATUS.eq(bo.getStatus()))
                .orderBy(SYS_DEPT.DEPT_ID, true)
                .orderBy(SYS_DEPT.PARENT_ID, true)
                .orderBy(SYS_DEPT.ORDER_NUM, true);
    }

    /**
     * 构建前端所需要下拉树结构
     *
     * @param depts 部门列表
     * @return 下拉树结构列表
     */
    @Override
    public List<MapTree<Long>> buildDeptTreeSelect(List<SysDeptVo> depts) {
        if (CollUtil.isEmpty(depts)) {
            return ListUtil.zero();
        }
        return TreeBuildUtil.build(depts, (dept, tree) ->
                tree.setId(dept.getDeptId())
                        .setParentId(dept.getParentId())
                        .setName(dept.getDeptName())
                        .setWeight(dept.getOrderNum()));
    }

    /**
     * 根据角色ID查询部门树信息
     *
     * @param roleId 角色ID
     * @return 选中部门列表
     */
    @Override
    public List<Long> selectDeptListByRoleId(Long roleId) {
        SysRole role = roleMapper.selectOneById(roleId);
        return baseMapper.selectDeptListByRoleId(roleId, role.getDeptCheckStrictly());
    }

    /**
     * 根据部门ID查询信息
     *
     * @param deptId 部门ID
     * @return 部门信息
     */
    // @Cacheable(cacheNames = CacheNames.SYS_DEPT, key = "#deptId")
    @Override
    public SysDeptVo selectDeptById(Long deptId) {
        SysDeptVo dept = baseMapper.selectOneWithRelationsByIdAs(deptId, SysDeptVo.class);
        if (ObjUtil.isNull(dept)) {
            return null;
        }
        SysDeptVo parentDept = baseMapper.selectOneByQueryAs(QueryWrapper.create()
                        .from(SYS_DEPT)
                        .select(SYS_DEPT.DEPT_NAME)
                        .where(SYS_DEPT.DEPT_ID.eq(dept.getParentId())),
                SysDeptVo.class);
        dept.setParentName(ObjUtil.isNotNull(parentDept) ? parentDept.getDeptName() : null);
        return dept;
    }

    /**
     * 通过部门ID查询部门名称
     *
     * @param deptIds 部门ID串逗号分隔
     * @return 部门名称串逗号分隔
     */
    @Override
    public String selectDeptNameByIds(String deptIds) {
        List<String> list = new ArrayList<>();
        for (Long id : StringUtil.splitTo(deptIds, ConvertUtil::toLong)) {
            SysDeptVo vo = this.selectDeptById(id);
            if (ObjUtil.isNotNull(vo)) {
                list.add(vo.getDeptName());
            }
        }
        return String.join(StringUtil.SEPARATOR, list);
    }

    /**
     * 根据ID查询所有子部门数（正常状态）
     *
     * @param deptId 部门ID
     * @return 子部门数
     */
    @Override
    public long selectNormalChildrenDeptById(Long deptId) {
        return baseMapper.selectCountByQuery(QueryWrapper.create()
                .from(SYS_DEPT)
                .where(SYS_DEPT.STATUS.eq(UserConstants.DEPT_NORMAL)
                        .and(DataBaseHelper.findInSet(deptId, "ancestors" ))));
    }

    /**
     * 是否存在子节点
     *
     * @param deptId 部门ID
     * @return 结果
     */
    @Override
    public boolean hasChildByDeptId(Long deptId) {
        return baseMapper.selectCountByQuery(QueryWrapper.create().from(SYS_DEPT).where(SYS_DEPT.PARENT_ID.eq(deptId))) > 0;
    }

    /**
     * 查询部门是否存在用户
     *
     * @param deptId 部门ID
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean checkDeptExistUser(Long deptId) {
        return userMapper.selectCountByQuery(QueryWrapper.create().from(SYS_USER).where(SYS_USER.DEPT_ID.eq(deptId))) > 0;
    }

    /**
     * 校验部门名称是否唯一
     *
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public boolean checkDeptNameUnique(SysDeptBo dept) {
        return baseMapper.selectCountByQuery(QueryWrapper.create()
                .from(SYS_DEPT)
                .where(SYS_DEPT.DEPT_NAME.eq(dept.getDeptName())
                        .and(SYS_DEPT.PARENT_ID.eq(dept.getParentId())
                                .and(SYS_DEPT.DEPT_ID.ne(dept.getDeptId()))))) <= 0;
    }

    /**
     * 校验部门是否有数据权限
     *
     * @param deptId 部门id
     */
    @Override
    public void checkDeptDataScope(Long deptId) {
        if (ObjUtil.isNull(deptId)) {
            return;
        }
        if (LoginHelper.isSuperAdmin()) {
            return;
        }
        SysDeptVo dept = baseMapper.selectDeptById(deptId);

        if (ObjUtil.isNull(dept)) {
            throw new ServiceException("没有权限访问部门数据！" );
        }
    }

    /**
     * 新增保存部门信息
     *
     * @param bo 部门信息
     * @return 结果
     */
    @Override
    public int insertDept(SysDeptBo bo) {
        SysDept info = baseMapper.selectOneById(bo.getParentId());
        // 如果父节点不为正常状态,则不允许新增子节点
        if (!UserConstants.DEPT_NORMAL.equals(info.getStatus())) {
            throw new ServiceException("部门停用，不允许新增" );
        }
        SysDept dept = MapstructUtil.convert(bo, SysDept.class);
        dept.setAncestors(info.getAncestors() + StringUtil.SEPARATOR + dept.getParentId());
        return baseMapper.insert(dept, true);
    }

    /**
     * 修改保存部门信息
     *
     * @param bo 部门信息
     * @return 结果
     */
    // @CacheEvict(cacheNames = CacheNames.SYS_DEPT, key = "#bo.deptId")
    @Override
    public int updateDept(SysDeptBo bo) {
        SysDept dept = MapstructUtil.convert(bo, SysDept.class);
        SysDept oldDept = baseMapper.selectOneById(dept.getDeptId());
        if (!oldDept.getParentId().equals(dept.getParentId())) {
            // 如果是新父部门 则校验是否具有新父部门权限 避免越权
            this.checkDeptDataScope(dept.getParentId());
            SysDept newParentDept = baseMapper.selectOneById(dept.getParentId());
            if (ObjUtil.isNotNull(newParentDept) && ObjUtil.isNotNull(oldDept)) {
                String newAncestors = newParentDept.getAncestors() + StringUtil.SEPARATOR + newParentDept.getDeptId();
                String oldAncestors = oldDept.getAncestors();
                dept.setAncestors(newAncestors);
                updateDeptChildren(dept.getDeptId(), newAncestors, oldAncestors);
            }
        }
        int result = baseMapper.update(dept);
        if (UserConstants.DEPT_NORMAL.equals(dept.getStatus()) && StringUtil.isNotEmpty(dept.getAncestors())
                && !StringUtil.equals(UserConstants.DEPT_NORMAL, dept.getAncestors())) {
            // 如果该部门是启用状态，则启用该部门的所有上级部门
            updateParentDeptStatusNormal(dept);
        }
        return result;
    }

    /**
     * 修改该部门的父级部门状态
     *
     * @param dept 当前部门
     */
    private void updateParentDeptStatusNormal(SysDept dept) {
        String ancestors = dept.getAncestors();
        Long[] deptIds = ConvertUtil.toLongArray(ancestors);
        SysDept sysDept = UpdateWrapper.of(SysDept.class)
                .set(SysDept::getStatus, UserConstants.DEPT_NORMAL).toEntity();
        baseMapper.updateByQuery(sysDept, QueryWrapper.create().from(SYS_DEPT).where(SYS_DEPT.DEPT_ID.in(Arrays.asList(deptIds))));
    }

    /**
     * 修改子元素关系
     *
     * @param deptId       被修改的部门ID
     * @param newAncestors 新的父ID集合
     * @param oldAncestors 旧的父ID集合
     */
    private void updateDeptChildren(Long deptId, String newAncestors, String oldAncestors) {
        List<SysDept> children = baseMapper.selectListByQuery(QueryWrapper.create().from(SYS_DEPT)
                .where(DataBaseHelper.findInSet(deptId, "ancestors" )));
        List<SysDept> list = new ArrayList<>();
        for (SysDept child : children) {
            SysDept dept = new SysDept();
            dept.setDeptId(child.getDeptId());
            dept.setAncestors(child.getAncestors().replaceFirst(oldAncestors, newAncestors));
            list.add(dept);
        }
        if (CollUtil.isNotEmpty(list)) {
//            if (Db.updateEntitiesBatch(list) > 0) {
//                // TODO 重要未修改
//                // list.forEach(dept -> CacheUtils.evict(CacheNames.SYS_DEPT, dept.getDeptId()));
//            }
        }
    }

    /**
     * 删除部门管理信息
     *
     * @param deptId 部门ID
     * @return 结果
     */
    // @CacheEvict(cacheNames = CacheNames.SYS_DEPT, key = "#deptId")
    @Override
    public int deleteDeptById(Long deptId) {
        return baseMapper.deleteById(deptId);
    }


}
