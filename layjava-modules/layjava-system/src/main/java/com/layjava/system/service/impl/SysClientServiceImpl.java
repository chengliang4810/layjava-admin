package com.layjava.system.service.impl;

import com.layjava.common.core.utils.MapstructUtil;
import com.layjava.common.mybatis.core.page.PageQuery;
import com.layjava.common.mybatis.core.page.PageResult;
import com.layjava.system.domain.SysClient;
import com.layjava.system.domain.bo.SysClientBo;
import com.layjava.system.domain.vo.SysClientVo;
import com.layjava.system.mapper.SysClientMapper;
import com.layjava.system.service.ISysClientService;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.update.UpdateChain;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.crypto.SecureUtil;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

import java.util.Collection;
import java.util.List;

import static com.layjava.system.domain.table.SysClientTableDef.SYS_CLIENT;

/**
 * 客户端管理Service业务层处理
 *
 * @author Michelle.Chung
 * @date 2023-06-18
 */
@Slf4j
@Component
public class SysClientServiceImpl implements ISysClientService {

    @Inject
    private SysClientMapper baseMapper;

    /**
     * 查询客户端管理
     */
    @Override
    public SysClientVo queryById(Long id) {
        SysClientVo vo = baseMapper.selectOneWithRelationsByIdAs(id, SysClientVo.class);
        vo.setGrantTypeList(List.of(vo.getGrantType().split("," )));
        return vo;
    }


    /**
     * 查询客户端管理
     */
    @Override
    public SysClient queryByClientId(String clientId) {
        return baseMapper.selectOneByQuery(QueryWrapper.create().from(SYS_CLIENT).where(SYS_CLIENT.CLIENT_ID.eq(clientId)));
    }

    /**
     * 查询客户端管理列表
     */
    @Override
    public PageResult<SysClientVo> queryPageList(SysClientBo bo, PageQuery pageQuery) {
        QueryWrapper lqw = buildQueryWrapper(bo);
        Page<SysClientVo> result = baseMapper.paginateAs(pageQuery, lqw, SysClientVo.class);
        result.getRecords().forEach(r -> r.setGrantTypeList(List.of(r.getGrantType().split("," ))));
        return PageResult.build(result);
    }

    /**
     * 查询客户端管理列表
     */
    @Override
    public List<SysClientVo> queryList(SysClientBo bo) {
        QueryWrapper lqw = buildQueryWrapper(bo);
        return baseMapper.selectListByQueryAs(lqw, SysClientVo.class);
    }

    private QueryWrapper buildQueryWrapper(SysClientBo bo) {
        return QueryWrapper.create()
                .from(SYS_CLIENT)
                .where(SYS_CLIENT.CLIENT_ID.eq(bo.getClientId()))
                .and(SYS_CLIENT.CLIENT_KEY.eq(bo.getClientKey()))
                .and(SYS_CLIENT.CLIENT_SECRET.eq(bo.getClientSecret()))
                .and(SYS_CLIENT.STATUS.eq(bo.getStatus()))
                .orderBy(SYS_CLIENT.ID, true);
    }

    /**
     * 新增客户端管理
     */
    @Override
    public Boolean insertByBo(SysClientBo bo) {
        SysClient add = MapstructUtil.convert(bo, SysClient.class);
        validEntityBeforeSave(add);
        add.setGrantType(String.join(",", bo.getGrantTypeList()));
        // 生成clientid
        String clientKey = bo.getClientKey();
        String clientSecret = bo.getClientSecret();
        add.setClientId(SecureUtil.md5(clientKey + clientSecret));
        boolean flag = baseMapper.insert(add, true) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改客户端管理
     */
    @Override
    public Boolean updateByBo(SysClientBo bo) {
        SysClient update = MapstructUtil.convert(bo, SysClient.class);
        validEntityBeforeSave(update);
        update.setGrantType(String.join(",", bo.getGrantTypeList()));
        return baseMapper.update(update) > 0;
    }

    /**
     * 修改状态
     */
    @Override
    public boolean updateUserStatus(Long id, String status) {
        return UpdateChain.of(SysClient.class)
                .set(SysClient::getStatus, status)
                .from(SysClient.class)
                .where(SysClient::getId).eq(id)
                .update();
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SysClient entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除客户端管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchByIds(ids) > 0;
    }
}
