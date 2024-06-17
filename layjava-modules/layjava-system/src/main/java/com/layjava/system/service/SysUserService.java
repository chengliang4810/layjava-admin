package com.layjava.system.service;

import com.layjava.system.domain.bo.SysUserBo;
import com.layjava.system.domain.vo.SysUserVo;
import com.layjava.common.dao.core.page.PageQuery;
import com.layjava.common.dao.core.page.PageResult;

import java.util.List;

/**
 *
 * 用户信息表 实现类
 *
 * @author chengliang4810
 * @since 2024-04-24
 */
public interface SysUserService {

    /**
     * 查询用户信息表列表
     *
     * @param sysUserBo 用户信息表Bo
     * @return 用户信息表列表
     */
    List<SysUserVo> getSysUserVoList(SysUserBo sysUserBo);

    /**
     * 获取用户信息表分页列表
     *
     * @param sysUserBo    用户信息表Bo
     * @param pageQuery 分页查询条件
     * @return {@link List}<{@link SysUserVo}>
     */
    PageResult<SysUserVo> getSysUserVoList(SysUserBo sysUserBo, PageQuery pageQuery);

    /**
     * 通过id查询用户信息表Vo
     *
     * @param id 用户信息表id
     * @return {@link SysUserVo} 用户信息表
     */
    SysUserVo getSysUserVoById(Long id);

    /**
     * 保存用户信息表
     *
     * @param sysUserBo 用户信息表
     * @return {@link boolean} 是否新增成功
     */
    boolean saveSysUser(SysUserBo sysUserBo);

    /**
     * 根据id更新用户信息表
     *
     * @param sysUserBo 用户信息表
     * @return {@link boolean} 是否更新成功
     */
    boolean updateSysUserById(SysUserBo sysUserBo);

    /**
     * 根据id删除用户信息表
     *
     * @param idList {table.comment!}id列表
     * @return {@link boolean} 是否删除成功
     */
    long deleteSysUserById(List<Long> idList);

    /**
     * 根据账号查询用户
     *
     * @param account 账号
     * @return 用户信息
     */
    SysUserVo selectUserByAccount(String account);
}