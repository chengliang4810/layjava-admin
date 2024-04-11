package com.layjava.test.controller;

import cn.hutool.core.util.StrUtil;
import com.layjava.common.web.core.BaseController;
import com.layjava.test.domain.User;
import com.layjava.test.domain.bo.UserBo;
import com.layjava.test.domain.vo.UserVo;
import com.layjava.test.mapper.UserMapper;
import com.layjava.test.service.UserService;
import org.apache.ibatis.solon.annotation.Db;
import org.noear.solon.annotation.*;

import java.util.List;

/**
 * 用户信息表 控制器
 *
 * @author chengliang4810
 * @since 2024-04-08
 */
@Controller
@Mapping("/test/user")
public class UserController extends BaseController {


    @Inject
    private UserService userService;

    @Db
    private UserMapper userMapper;

    /**
     * 查询用户列表
     *
     * @param userBo 用户bo
     * @return {@link List}<{@link UserVo}>
     */
    @Get
    @Mapping("/list")
    public List<User> list(UserBo userBo) {
        return userMapper.selectList();
    }

    /**
     * 根据Id查询用户信息
     *
     * @param id 身份证件
     * @return {@link UserVo}
     */
    @Get
    @Mapping("/{id}")
    public UserVo get(Long id) {
        return userService.getUserVoById(id);
    }

    /**
     * 新增用户信息
     *
     * @param userBo 用户bo
     * @return boolean
     */
    @Post
    @Mapping
    public boolean save(UserBo userBo) {
        return userService.saveUser(userBo);
    }

    /**
     * 根据Id更新用户信息
     *
     * @param userBo 用户bo
     * @return boolean
     */
    @Put
    @Mapping
    public boolean update(UserBo userBo) {
        return userService.updateUserById(userBo);
    }

    /**
     * 删除用户信息
     *
     * @param ids ids
     * @return boolean
     */
    @Delete
    @Mapping("/{ids}")
    public boolean delete(String ids) {
        List<String> split = StrUtil.split(ids, ",");
        return userService.deleteUserById(1L);
    }

}