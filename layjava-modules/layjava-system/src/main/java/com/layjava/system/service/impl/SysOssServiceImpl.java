package com.layjava.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.layjava.system.domain.SysOss;
import com.layjava.system.domain.bo.SysOssBo;
import com.layjava.system.domain.vo.SysOssVo;
import com.layjava.system.mapper.SysOssMapper;
import com.layjava.system.service.SysOssService;
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
 * OSS对象存储表 服务实现类
 *
 * @author chengliang4810
 * @since 2024-04-30
 */
@Slf4j
@Component
public class SysOssServiceImpl  implements SysOssService {

    @Db
    private SysOssMapper sysOssMapper;

    @Override
    public List<SysOssVo> getSysOssVoList(SysOssBo sysOssBo) {
        Wrapper<SysOss> sysOssWrapper = buildWrapper(sysOssBo);
        return sysOssMapper.selectVoList(sysOssWrapper);
    }

    @Override
    public PageResult<SysOssVo> getSysOssVoList(SysOssBo sysOssBo, PageQuery pageQuery) {
        Wrapper<SysOss> sysOssWrapper = buildWrapper(sysOssBo);

        IPage<SysOssVo> voPage = sysOssMapper.selectVoPage(pageQuery.build(), sysOssWrapper);
        return PageResult.build(voPage);
    }

    @Override
    public SysOssVo getSysOssVoById(Long id) {
        return sysOssMapper.selectVoById(id);
    }

    @Override
    public boolean saveSysOss(SysOssBo sysOssBo) {
        // 参数校验
        Assert.notNull(sysOssBo, "OSS对象存储表不能为空");

        SysOss sysOss = MapstructUtils.convert(sysOssBo, SysOss.class);
        return sysOssMapper.insert(sysOss) > 0;
    }

    @Override
    public boolean updateSysOssById(SysOssBo sysOssBo) {
        // 参数校验
        Assert.notNull(sysOssBo, "OSS对象存储表不能为空");
        Assert.notNull(sysOssBo.getOssId(), "OSS对象存储表ID不能为空" );

        SysOss sysOss = MapstructUtils.convert(sysOssBo, SysOss.class);
        return sysOssMapper.updateById(sysOss) > 0;
    }

    @Override
    public int deleteSysOssById(List<Long> idList) {
        // 参数校验
        Assert.notEmpty(idList, "OSS对象存储表ID不能为空");

        return sysOssMapper.deleteBatchIds(idList);
    }

    /**
     * 构建查询条件
     */
    private Wrapper<SysOss> buildWrapper(SysOssBo sysOssBo) {
        Map<String, Object> params = sysOssBo.getParams();
        LambdaQueryWrapper<SysOss> queryWrapper = Wrappers.lambdaQuery();
        // 条件构造
        queryWrapper.eq(sysOssBo.getOssId() != null, SysOss::getOssId, sysOssBo.getOssId());
        queryWrapper.eq(StrUtil.isNotBlank(sysOssBo.getFileName()), SysOss::getFileName, sysOssBo.getFileName());
        queryWrapper.eq(StrUtil.isNotBlank(sysOssBo.getOriginalName()), SysOss::getOriginalName, sysOssBo.getOriginalName());
        queryWrapper.eq(StrUtil.isNotBlank(sysOssBo.getFileSuffix()), SysOss::getFileSuffix, sysOssBo.getFileSuffix());
        queryWrapper.eq(StrUtil.isNotBlank(sysOssBo.getUrl()), SysOss::getUrl, sysOssBo.getUrl());
        queryWrapper.eq(sysOssBo.getSize() != null, SysOss::getSize, sysOssBo.getSize());
        queryWrapper.eq(StrUtil.isNotBlank(sysOssBo.getService()), SysOss::getService, sysOssBo.getService());
        return queryWrapper;
    }

}