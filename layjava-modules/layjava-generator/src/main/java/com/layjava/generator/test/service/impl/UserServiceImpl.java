package com.layjava.generator.test.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.layjava.generator.test.domain.User;
import com.layjava.generator.test.domain.bo.UserBo;
import com.layjava.generator.test.domain.vo.UserVo;
import com.layjava.generator.test.mapper.UserMapper;
import com.layjava.generator.test.service.UserService;
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
public class UserServiceImpl  implements UserService {

    @Db
    private UserMapper userMapper;

    @Override
    public List<UserVo> getUserVoList(UserBo userBo) {
        Wrapper<User> userWrapper = buildWrapper(userBo);
        return userMapper.selectVoList(userWrapper);
    }

    @Override
    public PageResult<UserVo> getUserVoList(UserBo userBo, PageQuery pageQuery) {
        Wrapper<User> userWrapper = buildWrapper(userBo);

        IPage<UserVo> voPage = userMapper.selectVoPage(pageQuery.build(), userWrapper);
        return PageResult.build(voPage);
    }

    @Override
    public UserVo getUserVoById(Long id) {
        return userMapper.selectVoById(id);
    }

    @Override
    public boolean saveUser(UserBo userBo) {
        // 参数校验
        Assert.notNull(userBo, "用户信息表不能为空");

        User user = MapstructUtils.convert(userBo, User.class);
        return userMapper.insert(user) > 0;
    }

    @Override
    public boolean updateUserById(UserBo userBo) {
        // 参数校验
        Assert.notNull(userBo, "用户信息表不能为空");
        Assert.notNull(userBo.getUserId(), "用户信息表ID不能为空" );

        User user = MapstructUtils.convert(userBo, User.class);
        return userMapper.updateById(user) > 0;
    }

    @Override
    public int deleteUserById(List<Long> idList) {
        // 参数校验
        Assert.notEmpty(idList, "用户信息表ID不能为空");

        return userMapper.deleteBatchIds(idList);
    }

    /**
     * 构建查询条件
     */
    private Wrapper<User> buildWrapper(UserBo userBo) {
        Map<String, Object> params = userBo.getParams();
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        // 条件构造
        queryWrapper.eq(userBo.getUserId() != null, User::getUserId, userBo.getUserId());
        queryWrapper.eq(StrUtil.isNotBlank(userBo.getTenantId()), User::getTenantId, userBo.getTenantId());
        queryWrapper.eq(userBo.getDeptId() != null, User::getDeptId, userBo.getDeptId());
        queryWrapper.eq(StrUtil.isNotBlank(userBo.getUserName()), User::getUserName, userBo.getUserName());
        queryWrapper.eq(StrUtil.isNotBlank(userBo.getNickName()), User::getNickName, userBo.getNickName());
        queryWrapper.eq(StrUtil.isNotBlank(userBo.getCertificateNumber()), User::getCertificateNumber, userBo.getCertificateNumber());
        queryWrapper.eq(StrUtil.isNotBlank(userBo.getUserType()), User::getUserType, userBo.getUserType());
        queryWrapper.eq(StrUtil.isNotBlank(userBo.getEmail()), User::getEmail, userBo.getEmail());
        queryWrapper.eq(StrUtil.isNotBlank(userBo.getPhonenumber()), User::getPhonenumber, userBo.getPhonenumber());
        queryWrapper.eq(StrUtil.isNotBlank(userBo.getSex()), User::getSex, userBo.getSex());
        queryWrapper.eq(userBo.getAvatar() != null, User::getAvatar, userBo.getAvatar());
        queryWrapper.eq(StrUtil.isNotBlank(userBo.getPassword()), User::getPassword, userBo.getPassword());
        queryWrapper.eq(StrUtil.isNotBlank(userBo.getStatus()), User::getStatus, userBo.getStatus());
        queryWrapper.eq(StrUtil.isNotBlank(userBo.getDelFlag()), User::getDelFlag, userBo.getDelFlag());
        queryWrapper.eq(StrUtil.isNotBlank(userBo.getLoginIp()), User::getLoginIp, userBo.getLoginIp());
        queryWrapper.eq(userBo.getLoginDate() != null, User::getLoginDate, userBo.getLoginDate());
        queryWrapper.eq(StrUtil.isNotBlank(userBo.getRemark()), User::getRemark, userBo.getRemark());
        return queryWrapper;
    }

}