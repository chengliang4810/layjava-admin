package com.layjava.system.service.impl;

import com.layjava.common.core.utils.StreamUtil;
import com.layjava.common.mybatis.helper.DataBaseHelper;
import com.layjava.system.domain.SysDept;
import com.layjava.system.domain.SysRoleDept;
import com.layjava.system.mapper.SysDeptMapper;
import com.layjava.system.mapper.SysRoleDeptMapper;
import com.layjava.system.service.ISysDataScopeService;
import com.mybatisflex.core.query.QueryWrapper;
import org.dromara.hutool.core.collection.CollUtil;
import org.dromara.hutool.core.convert.ConvertUtil;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

import java.util.List;

import static com.layjava.system.domain.table.SysDeptTableDef.SYS_DEPT;
import static com.layjava.system.domain.table.SysRoleDeptTableDef.SYS_ROLE_DEPT;

/**
 * 数据权限 实现
 * <p>
 * 注意: 此Service内不允许调用标注`数据权限`注解的方法
 * 例如: deptMapper.selectList 此 selectList 方法标注了`数据权限`注解 会出现循环解析的问题
 *
 * @author Lion Li
 */

@Component("sdss" )
public class SysDataScopeServiceImpl implements ISysDataScopeService {

    @Inject
    private SysRoleDeptMapper roleDeptMapper;
    @Inject
    private SysDeptMapper deptMapper;

    @Override
    public String getRoleCustom(Long roleId) {
        List<SysRoleDept> list = roleDeptMapper.selectListByQuery(
                QueryWrapper.create().from(SYS_ROLE_DEPT).select(SYS_ROLE_DEPT.DEPT_ID)
                        .where(SYS_ROLE_DEPT.ROLE_ID.eq(roleId)));
        if (CollUtil.isNotEmpty(list)) {
            return StreamUtil.join(list, rd -> ConvertUtil.toStr(rd.getDeptId()));
        }
        return null;
    }

    @Override
    public String getDeptAndChild(Long deptId) {


        List<SysDept> deptList = deptMapper.selectListByQuery(QueryWrapper.create().from(SYS_DEPT).select(SYS_DEPT.DEPT_ID).where(DataBaseHelper.findInSet(deptId, "ancestors" )));
        List<Long> ids = StreamUtil.toList(deptList, SysDept::getDeptId);
        ids.add(deptId);
        if (CollUtil.isNotEmpty(ids)) {
            return StreamUtil.join(ids, ConvertUtil::toStr);
        }
        return null;
    }

}
