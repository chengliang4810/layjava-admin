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
     * 根据id获取用户信息
     *
     * @param id 用户id
     * @return {@link UserVo} 用户信息
     */
    UserVo getUserVoById(Long id);

    /**
     * 保存用户
     *
     * @param userBo 用户信息
     * @return boolean 是否新增成功
     */
    boolean saveUser(UserBo userBo);

    /**
     * 根据id更新用户
     *
     * @param userBo 用户信息
     * @return boolean 是否更新成功
     */
    boolean updateUserById(UserBo userBo);

    /**
     * 根据id删除用户
     *
     * @param idList id列表
     * @return int 删除行数
     */
    int deleteUserById(List<Long> idList);
}