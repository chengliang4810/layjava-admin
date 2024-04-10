package com.layjava.test.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.layjava.test.domain.User;
import com.layjava.test.domain.bo.UserBo;
import com.layjava.test.domain.vo.UserVo;
import com.layjava.test.mapper.UserMapper;
import com.layjava.test.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.solon.annotation.Db;
import org.noear.solon.annotation.Component;

import java.util.List;
import java.util.Map;

/**
 * 用户信息表 服务实现类
 *
 * @author chengliang4810
 * @since 2024-04-08
 */
@Slf4j
@Component
public class UserServiceImpl implements UserService {

    @Db
    private UserMapper userMapper;

    @Override
    public List<UserVo> getUserVoList(UserBo userBo) {
        Wrapper<User> userWrapper = buildWrapper(userBo);
        return userMapper.selectVoList(userWrapper);
    }

    /**
     * 构建查询条件
     *
     * @param userBo 用户信息BO
     * @return Wrapper<User> 查询条件
     */
    private Wrapper<User> buildWrapper(UserBo userBo) {
        Map<String, Object> params = userBo.getParams();
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();

        queryWrapper.eq(userBo.getUserId() != null, User::getUserId, userBo.getUserId());

        return queryWrapper;
    }

    @Override
    public UserVo getUserVoById(Long id) {
        return null;
    }

    @Override
    public boolean saveUser(UserBo userBo) {
        return false;
    }

    @Override
    public boolean updateUserById(UserBo userBo) {
        return false;
    }

    @Override
    public boolean deleteUserById(Long id) {
        return false;
    }
}