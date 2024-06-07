package com.layjava.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.layjava.system.domain.SysUser;
import com.layjava.system.domain.bo.SysUserBo;
import com.layjava.system.domain.vo.SysUserVo;
import com.layjava.system.mapper.SysUserMapper;
import com.layjava.system.service.SysUserService;
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
 * 用户信息表 服务实现类
 *
 * @author chengliang4810
 * @since 2024-04-24
 */
@Slf4j
@Component
public class SysUserServiceImpl  implements SysUserService {

    @Db
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUserVo> getSysUserVoList(SysUserBo sysUserBo) {
        Wrapper<SysUser> sysUserWrapper = buildWrapper(sysUserBo);
        return sysUserMapper.selectVoList(sysUserWrapper);
    }

    @Override
    public PageResult<SysUserVo> getSysUserVoList(SysUserBo sysUserBo, PageQuery pageQuery) {
        Wrapper<SysUser> sysUserWrapper = buildWrapper(sysUserBo);

        IPage<SysUserVo> voPage = sysUserMapper.selectVoPage(pageQuery.build(), sysUserWrapper);
        return PageResult.build(voPage);
    }

    @Override
    public SysUserVo getSysUserVoById(Long id) {
        return sysUserMapper.selectUserById(id);
    }

    @Override
    public boolean saveSysUser(SysUserBo sysUserBo) {
        // 参数校验
        Assert.notNull(sysUserBo, "用户信息表不能为空");

        SysUser sysUser = MapstructUtils.convert(sysUserBo, SysUser.class);
        return sysUserMapper.insert(sysUser) > 0;
    }

    @Override
    public boolean updateSysUserById(SysUserBo sysUserBo) {
        // 参数校验
        Assert.notNull(sysUserBo, "用户信息表不能为空");
        Assert.notNull(sysUserBo.getUserId(), "用户信息表ID不能为空" );

        SysUser sysUser = MapstructUtils.convert(sysUserBo, SysUser.class);
        return sysUserMapper.updateById(sysUser) > 0;
    }

    @Override
    public int deleteSysUserById(List<Long> idList) {
        // 参数校验
        Assert.notEmpty(idList, "用户信息表ID不能为空");

        return sysUserMapper.deleteBatchIds(idList);
    }

    /**
     * 构建查询条件
     */
    private Wrapper<SysUser> buildWrapper(SysUserBo sysUserBo) {
        Map<String, Object> params = sysUserBo.getParams();
        LambdaQueryWrapper<SysUser> queryWrapper = Wrappers.lambdaQuery();
        // 条件构造
        queryWrapper.eq(sysUserBo.getUserId() != null, SysUser::getUserId, sysUserBo.getUserId());
        queryWrapper.eq(sysUserBo.getDeptId() != null, SysUser::getDeptId, sysUserBo.getDeptId());
        queryWrapper.eq(StrUtil.isNotBlank(sysUserBo.getUserType()), SysUser::getUserType, sysUserBo.getUserType());
        queryWrapper.eq(StrUtil.isNotBlank(sysUserBo.getEmail()), SysUser::getEmail, sysUserBo.getEmail());
        queryWrapper.eq(sysUserBo.getAvatar() != null, SysUser::getAvatar, sysUserBo.getAvatar());
        queryWrapper.eq(StrUtil.isNotBlank(sysUserBo.getPassword()), SysUser::getPassword, sysUserBo.getPassword());
        queryWrapper.eq(StrUtil.isNotBlank(sysUserBo.getStatus()), SysUser::getStatus, sysUserBo.getStatus());
        queryWrapper.eq(StrUtil.isNotBlank(sysUserBo.getLoginIp()), SysUser::getLoginIp, sysUserBo.getLoginIp());
        queryWrapper.eq(sysUserBo.getLoginDate() != null, SysUser::getLoginDate, sysUserBo.getLoginDate());
        queryWrapper.eq(StrUtil.isNotBlank(sysUserBo.getRemark()), SysUser::getRemark, sysUserBo.getRemark());
        return queryWrapper;
    }

}