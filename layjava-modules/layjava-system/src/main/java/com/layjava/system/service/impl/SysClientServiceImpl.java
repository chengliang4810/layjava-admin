package com.layjava.system.service.impl;

import com.easy.query.api.proxy.client.EasyEntityQuery;
import com.easy.query.solon.annotation.Db;
import com.layjava.common.dao.core.page.PageQuery;
import com.layjava.common.dao.core.page.PageResult;
import com.layjava.system.domain.SysClient;
import com.layjava.system.domain.bo.SysClientBo;
import com.layjava.system.domain.vo.SysClientVo;
import com.layjava.system.service.SysClientService;
import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.Component;

import java.util.List;

/**
 *
 * 系统授权表 服务实现类
 *
 * @author chengliang4810
 * @since 2024-04-25
 */
@Slf4j
@Component
public class SysClientServiceImpl  implements SysClientService {

    @Db
    private EasyEntityQuery entityQuery;

    /**
     * 查询系统授权表列表
     *
     * @param sysClientBo 系统授权表Bo
     * @return 系统授权表列表
     */
    @Override
    public List<SysClientVo> getSysClientVoList(SysClientBo sysClientBo) {
        return List.of();
    }

    /**
     * 获取系统授权表分页列表
     *
     * @param sysClientBo 系统授权表Bo
     * @param pageQuery   分页查询条件
     * @return {@link List}<{@link SysClientVo}>
     */
    @Override
    public PageResult<SysClientVo> getSysClientVoList(SysClientBo sysClientBo, PageQuery pageQuery) {
        return null;
    }

    /**
     * 通过id查询系统授权表Vo
     *
     * @param id 系统授权表id
     * @return {@link SysClientVo} 系统授权表
     */
    @Override
    public SysClientVo getSysClientVoById(Long id) {
        return null;
    }

    /**
     * 查询客户端信息基于客户端id
     *
     * @param clientId 客户端id
     */
    @Override
    public SysClient getByClientId(String clientId) {
        return entityQuery.queryable(SysClient.class)
                .where(sysClient -> sysClient.clientId().eq(clientId))
                .firstOrNull();
    }

    /**
     * 保存系统授权表
     *
     * @param sysClientBo 系统授权表
     * @return {@link boolean} 是否新增成功
     */
    @Override
    public boolean saveSysClient(SysClientBo sysClientBo) {
        return false;
    }

    /**
     * 根据id更新系统授权表
     *
     * @param sysClientBo 系统授权表
     * @return {@link boolean} 是否更新成功
     */
    @Override
    public boolean updateSysClientById(SysClientBo sysClientBo) {
        return false;
    }

    /**
     * 修改状态
     *
     * @param id
     * @param status
     */
    @Override
    public boolean updateUserStatus(Long id, String status) {
        return false;
    }

    /**
     * 根据id删除系统授权表
     *
     * @param idList {table.comment!}id列表
     * @return {@link boolean} 是否删除成功
     */
    @Override
    public int deleteSysClientById(List<Long> idList) {
        return 0;
    }
}