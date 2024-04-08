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

}