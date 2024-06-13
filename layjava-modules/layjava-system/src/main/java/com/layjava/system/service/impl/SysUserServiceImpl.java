package com.layjava.system.service.impl;

import com.layjava.common.dao.core.page.PageQuery;
import com.layjava.common.dao.core.page.PageResult;
import com.layjava.system.domain.bo.SysUserBo;
import com.layjava.system.domain.vo.SysUserVo;
import com.layjava.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.Component;

import java.util.List;

/**
 *
 * 用户信息表 服务实现类
 *
 * @author chengliang4810
 * @since 2024-04-24
 */
@Slf4j
@Component
public class SysUserServiceImpl  implements SysUserService {

    /**
     * 查询用户信息表列表
     *
     * @param sysUserBo 用户信息表Bo
     * @return 用户信息表列表
     */
    @Override
    public List<SysUserVo> getSysUserVoList(SysUserBo sysUserBo) {
        return List.of();
    }

    /**
     * 获取用户信息表分页列表
     *
     * @param sysUserBo 用户信息表Bo
     * @param pageQuery 分页查询条件
     * @return {@link List}<{@link SysUserVo}>
     */
    @Override
    public PageResult<SysUserVo> getSysUserVoList(SysUserBo sysUserBo, PageQuery pageQuery) {
        return null;
    }

    /**
     * 通过id查询用户信息表Vo
     *
     * @param id 用户信息表id
     * @return {@link SysUserVo} 用户信息表
     */
    @Override
    public SysUserVo getSysUserVoById(Long id) {
        return null;
    }

    /**
     * 保存用户信息表
     *
     * @param sysUserBo 用户信息表
     * @return {@link boolean} 是否新增成功
     */
    @Override
    public boolean saveSysUser(SysUserBo sysUserBo) {
        return false;
    }

    /**
     * 根据id更新用户信息表
     *
     * @param sysUserBo 用户信息表
     * @return {@link boolean} 是否更新成功
     */
    @Override
    public boolean updateSysUserById(SysUserBo sysUserBo) {
        return false;
    }

    /**
     * 根据id删除用户信息表
     *
     * @param idList {table.comment!}id列表
     * @return {@link boolean} 是否删除成功
     */
    @Override
    public int deleteSysUserById(List<Long> idList) {
        return 0;
    }
}