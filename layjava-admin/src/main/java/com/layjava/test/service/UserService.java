package com.layjava.test.service;

import com.layjava.test.domain.bo.UserBo;
import com.layjava.test.domain.vo.UserVo;

import java.util.List;

/**
 * 用户信息表 服务类
 *
 * @author chengliang4810
 * @since 2024-04-08
 */
public interface UserService {

    /**
     * 查询用户信息表
     *
     * @param userBo 用户信息BO
     * @return 用户信息表
     */
    List<UserVo> getUserVoList(UserBo userBo);

    /**
     * 按id获取用户信息
     *
     * @param id 用户id
     * @return {@link UserVo}
     */
    UserVo getUserVoById(Integer id);


    /**
     * 保存用户
     *
     * @param userBo 用户bo
     * @return boolean
     */
    boolean saveUser(UserBo userBo);


    /**
     * 按id更新用户
     *
     * @param userBo 用户bo
     * @return boolean
     */
    boolean updateUserById(UserBo userBo);


    /**
     * 按id删除用户
     *
     * @param id 身份证件
     * @return boolean
     */
    boolean deleteUserById(Integer id);

}