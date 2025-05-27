package com.jimuqu.system.service.impl;

import cn.xbatis.core.sql.executor.chain.QueryChain;
import com.jimuqu.common.core.utils.MapstructUtil;
import com.jimuqu.common.mybatis.core.Page;
import com.jimuqu.common.mybatis.core.page.PageQuery;
import com.jimuqu.system.domain.SysClient;
import com.jimuqu.system.domain.bo.SysClientBo;
import com.jimuqu.system.domain.query.SysClientQuery;
import com.jimuqu.system.domain.vo.SysClientVo;
import com.jimuqu.system.mapper.SysClientMapper;
import com.jimuqu.system.service.SysClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.Component;

import java.util.Collection;
import java.util.List;


/**
 * 授权管理对象 sys_clientService业务层处理
 *
 * @author chengliang4810
 * @since 2025-05-27
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SysClientServiceImpl implements SysClientService {

    private final SysClientMapper sysClientMapper;

    /**
     * 查询授权管理对象 sys_client
     */
    @Override
    public SysClientVo queryById(Long id) {
        return sysClientMapper.getVoById(id);
    }

    /**
     * 查询客户端信息基于客户端id
     *
     * @param clientId 客户端Id
     */
    @Override
    public SysClient queryByClientId(String clientId) {
        return sysClientMapper.get(where -> where.eq(SysClient::getClientId, clientId));
    }

    /**
     * 查询授权管理对象 sys_client分页列表
     */
    @Override
    public Page<SysClientVo> queryPageList(SysClientQuery query, PageQuery pageQuery) {
        return buildQueryChain(query)
                .returnType(SysClientVo.class)
                .paging(pageQuery.build());
    }

    /**
     * 查询授权管理对象 sys_client列表
     */
    @Override
    public List<SysClientVo> queryList(SysClientQuery query) {
        QueryChain<SysClient> queryChain = buildQueryChain(query);
        return queryChain.returnType(SysClientVo.class).list();
    }

    /**
     * 构建查询条件
     * @param query 查询对象
     * @return 查询条件对象
     */
    private QueryChain<SysClient> buildQueryChain(SysClientQuery query) {
        return QueryChain.of(sysClientMapper)
                .forSearch(true)
                .where(query);
    }

    /**
     * 新增授权管理对象 sys_client
     */
    @Override
    public Boolean insertByBo(SysClientBo bo) {
        SysClient sysClient = MapstructUtil.convert(bo, SysClient.class);
        boolean flag = sysClientMapper.save(sysClient) > 0;
        bo.setId(sysClient.getId());
        return flag;
    }

    /**
     * 修改授权管理对象 sys_client
     */
    @Override
    public Boolean updateByBo(SysClientBo bo) {
        SysClient sysClient = MapstructUtil.convert(bo, SysClient.class);
        return sysClientMapper.update(sysClient) > 0;
    }

    /**
     * 修改状态
     *
     * @param id 主键
     * @param status 状态
     */
    @Override
    public boolean updateUserStatus(Long id, String status) {
        return sysClientMapper.update(new SysClient().setId(id).setStatus(status)) > 0;
    }

    /**
     * 批量删除授权管理对象 sys_client
     */
    @Override
    public Integer deleteByIds(Collection<Long> ids) {
        return sysClientMapper.deleteByIds(ids);
    }
}
