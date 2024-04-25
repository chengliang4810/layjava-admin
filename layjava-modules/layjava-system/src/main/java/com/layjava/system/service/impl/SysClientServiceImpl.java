package com.layjava.system.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.layjava.system.domain.SysClient;
import com.layjava.system.domain.bo.SysClientBo;
import com.layjava.system.domain.vo.SysClientVo;
import com.layjava.system.mapper.SysClientMapper;
import com.layjava.system.service.SysClientService;
import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.Component;
import org.apache.ibatis.solon.annotation.Db;
import com.layjava.common.mybatis.core.page.PageQuery;
import com.layjava.common.mybatis.core.page.PageResult;
import com.layjava.common.core.util.MapstructUtils;

import java.util.List;
import java.util.Map;

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
    private SysClientMapper sysClientMapper;

    @Override
    public List<SysClientVo> getSysClientVoList(SysClientBo sysClientBo) {
        Wrapper<SysClient> sysClientWrapper = buildWrapper(sysClientBo);
        return sysClientMapper.selectVoList(sysClientWrapper);
    }

    @Override
    public PageResult<SysClientVo> getSysClientVoList(SysClientBo sysClientBo, PageQuery pageQuery) {
        Wrapper<SysClient> sysClientWrapper = buildWrapper(sysClientBo);

        IPage<SysClientVo> voPage = sysClientMapper.selectVoPage(pageQuery.build(), sysClientWrapper);
        return PageResult.build(voPage);
    }

    @Override
    public SysClientVo getSysClientVoById(Long id) {
        SysClientVo sysClientVo = sysClientMapper.selectVoById(id);
        sysClientVo.setGrantTypeList(List.of(sysClientVo.getGrantType().split(",")));
        return sysClientVo;
    }

    @Override
    public SysClient getByClientId(String clientId) {
        return sysClientMapper.selectOne(new LambdaQueryWrapper<SysClient>()
                .eq(SysClient::getClientId, clientId)
        );
    }

    @Override
    public boolean saveSysClient(SysClientBo sysClientBo) {
        // 参数校验
        Assert.notNull(sysClientBo, "系统授权表不能为空");

        SysClient sysClient = MapstructUtils.convert(sysClientBo, SysClient.class);
        sysClient.setGrantType(String.join(",", sysClientBo.getGrantTypeList()));
        String clientKey = sysClientBo.getClientKey();
        String clientSecret = sysClientBo.getClientSecret();
        sysClient.setClientId(SecureUtil.md5(clientKey + clientSecret));
        return sysClientMapper.insert(sysClient) > 0;
    }

    @Override
    public boolean updateSysClientById(SysClientBo sysClientBo) {
        // 参数校验
        Assert.notNull(sysClientBo, "系统授权表不能为空");
        Assert.notNull(sysClientBo.getId(), "系统授权表ID不能为空" );

        SysClient sysClient = MapstructUtils.convert(sysClientBo, SysClient.class);
        sysClient.setGrantType(String.join(",", sysClientBo.getGrantTypeList()));
        return sysClientMapper.updateById(sysClient) > 0;
    }

    @Override
    public boolean updateUserStatus(Long id, String status) {
        return sysClientMapper.update(null,
                new LambdaUpdateWrapper<SysClient>()
                        .set(SysClient::getStatus, status)
                        .eq(SysClient::getId, id)) > 0;
    }

    @Override
    public int deleteSysClientById(List<Long> idList) {
        // 参数校验
        Assert.notEmpty(idList, "系统授权表ID不能为空");

        return sysClientMapper.deleteBatchIds(idList);
    }

    /**
     * 构建查询条件
     */
    private Wrapper<SysClient> buildWrapper(SysClientBo sysClientBo) {
        Map<String, Object> params = sysClientBo.getParams();
        LambdaQueryWrapper<SysClient> queryWrapper = Wrappers.lambdaQuery();
        // 条件构造
        queryWrapper.eq(StrUtil.isNotBlank(sysClientBo.getClientId()), SysClient::getClientId, sysClientBo.getClientId());
        queryWrapper.eq(StrUtil.isNotBlank(sysClientBo.getClientKey()), SysClient::getClientKey, sysClientBo.getClientKey());
        queryWrapper.eq(StrUtil.isNotBlank(sysClientBo.getClientSecret()), SysClient::getClientSecret, sysClientBo.getClientSecret());
        queryWrapper.eq(StrUtil.isNotBlank(sysClientBo.getStatus()), SysClient::getStatus, sysClientBo.getStatus());
        queryWrapper.orderByAsc(SysClient::getId);
        return queryWrapper;
    }

}