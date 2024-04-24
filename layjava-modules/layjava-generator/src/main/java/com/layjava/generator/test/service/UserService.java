package com.layjava.generator.test.service;

import com.layjava.generator.test.domain.bo.UserBo;
import com.layjava.generator.test.domain.vo.UserVo;
import com.layjava.common.mybatis.core.page.PageQuery;
import com.layjava.common.mybatis.core.page.PageResult;

import java.util.List;

/**
 *
 * 用户信息表 实现类
 *
 * @author chengliang4810
 * @since 2024-04-24
 */
public interface UserService {

    /**
     * 查询用户信息表列表
     *
     * @param userBo 用户信息表Bo
     * @return 用户信息表列表
     */
    List<UserVo> getUserVoList(UserBo userBo);

    /**
     * 获取用户信息表分页列表
     *
     * @param userBo    用户信息表Bo
     * @param pageQuery 分页查询条件
     * @return {@link List}<{@link UserVo}>
     */
    PageResult<UserVo> getUserVoList(UserBo userBo, PageQuery pageQuery);

    /**
     * 通过id查询用户信息表Vo
     *
     * @param id 用户信息表id
     * @return {@link UserVo} 用户信息表
     */
    UserVo getUserVoById(Long id);

    /**
     * 保存用户信息表
     *
     * @param userBo 用户信息表
     * @return {@link boolean} 是否新增成功
     */
    boolean saveUser(UserBo userBo);

    /**
     * 根据id更新用户信息表
     *
     * @param userBo 用户信息表
     * @return {@link boolean} 是否更新成功
     */
    boolean updateUserById(UserBo userBo);

    /**
     * 根据id删除用户信息表
     *
     * @param idList {table.comment!}id列表
     * @return {@link boolean} 是否删除成功
     */
    int deleteUserById(List<Long> idList);

}