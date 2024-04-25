package com.layjava.system.service;

import com.layjava.system.domain.SysClient;
import com.layjava.system.domain.bo.SysClientBo;
import com.layjava.system.domain.vo.SysClientVo;
import com.layjava.common.mybatis.core.page.PageQuery;
import com.layjava.common.mybatis.core.page.PageResult;

import java.util.List;

/**
 *
 * 系统授权表 实现类
 *
 * @author chengliang4810
 * @since 2024-04-25
 */
public interface SysClientService {

    /**
     * 查询系统授权表列表
     *
     * @param sysClientBo 系统授权表Bo
     * @return 系统授权表列表
     */
    List<SysClientVo> getSysClientVoList(SysClientBo sysClientBo);

    /**
     * 获取系统授权表分页列表
     *
     * @param sysClientBo    系统授权表Bo
     * @param pageQuery 分页查询条件
     * @return {@link List}<{@link SysClientVo}>
     */
    PageResult<SysClientVo> getSysClientVoList(SysClientBo sysClientBo, PageQuery pageQuery);

    /**
     * 通过id查询系统授权表Vo
     *
     * @param id 系统授权表id
     * @return {@link SysClientVo} 系统授权表
     */
    SysClientVo getSysClientVoById(Long id);

    /**
     * 查询客户端信息基于客户端id
     */
    SysClient getByClientId(String clientId);

    /**
     * 保存系统授权表
     *
     * @param sysClientBo 系统授权表
     * @return {@link boolean} 是否新增成功
     */
    boolean saveSysClient(SysClientBo sysClientBo);

    /**
     * 根据id更新系统授权表
     *
     * @param sysClientBo 系统授权表
     * @return {@link boolean} 是否更新成功
     */
    boolean updateSysClientById(SysClientBo sysClientBo);

    /**
     * 修改状态
     */
    boolean updateUserStatus(Long id, String status);

    /**
     * 根据id删除系统授权表
     *
     * @param idList {table.comment!}id列表
     * @return {@link boolean} 是否删除成功
     */
    int deleteSysClientById(List<Long> idList);

}