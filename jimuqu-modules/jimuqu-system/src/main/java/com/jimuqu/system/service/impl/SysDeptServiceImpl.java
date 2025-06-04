package com.jimuqu.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.jimuqu.common.core.utils.StreamUtil;
import com.jimuqu.common.core.utils.TreeBuildUtil;
import com.jimuqu.system.domain.bo.SysDeptBo;
import com.jimuqu.system.domain.vo.SysDeptVo;
import com.jimuqu.system.service.ISysDeptService;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.tree.MapTree;
import org.noear.solon.annotation.Component;

import java.util.List;

@Slf4j
@Component
public class SysDeptServiceImpl implements ISysDeptService {

    @Override
    public List<SysDeptVo> selectDeptList(SysDeptBo dept) {
        return List.of();
    }

    @Override
    public List<MapTree<Long>> selectDeptTreeList(SysDeptBo dept) {
        return List.of();
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
            return CollUtil.newArrayList();
        }
        // 获取当前列表中每一个节点的parentId，然后在列表中查找是否有id与其parentId对应，若无对应，则表明此时节点列表中，该节点在当前列表中属于顶级节点
        List<MapTree<Long>> treeList = CollUtil.newArrayList();
        for (SysDeptVo d : depts) {
            Long parentId = d.getParentId();
            SysDeptVo sysDeptVo = StreamUtil.findFirst(depts, it -> it.getId().longValue() == parentId);
            if (ObjectUtil.isNull(sysDeptVo)) {
                List<MapTree<Long>> trees = TreeBuildUtil.build(depts, parentId, (dept, tree) ->
                        tree.setId(dept.getId())
                                .setParentId(dept.getParentId())
                                .setName(dept.getDeptName())
                                .setWeight(dept.getOrderNum())
                                .putExtra("disabled", "0".equals(dept.getStatus())));
                MapTree<Long> tree = StreamUtil.findFirst(trees, it -> it.getId().longValue() == d.getId());
                treeList.add(tree);
            }
        }
        return treeList;
    }

    @Override
    public List<Long> selectDeptListByRoleId(Long roleId) {
        return List.of();
    }

    @Override
    public SysDeptVo selectDeptById(Long deptId) {
        return null;
    }

    @Override
    public long selectNormalChildrenDeptById(Long deptId) {
        return 0;
    }

    @Override
    public boolean hasChildByDeptId(Long deptId) {
        return false;
    }

    @Override
    public boolean checkDeptExistUser(Long deptId) {
        return false;
    }

    @Override
    public boolean checkDeptNameUnique(SysDeptBo dept) {
        return false;
    }

    @Override
    public void checkDeptDataScope(Long deptId) {

    }

    @Override
    public int insertDept(SysDeptBo bo) {
        return 0;
    }

    @Override
    public int updateDept(SysDeptBo bo) {
        return 0;
    }

    @Override
    public int deleteDeptById(Long deptId) {
        return 0;
    }
}
