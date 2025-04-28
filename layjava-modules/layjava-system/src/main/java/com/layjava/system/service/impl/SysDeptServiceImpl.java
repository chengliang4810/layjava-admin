package com.layjava.system.service.impl;

import com.layjava.system.domain.bo.SysDeptBo;
import com.layjava.system.domain.vo.SysDeptVo;
import com.layjava.system.service.ISysDeptService;
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

    @Override
    public List<MapTree<Long>> buildDeptTreeSelect(List<SysDeptVo> depts) {
        return List.of();
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
