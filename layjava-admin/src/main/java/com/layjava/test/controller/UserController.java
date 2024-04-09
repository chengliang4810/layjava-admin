package com.layjava.test.controller;

import com.layjava.common.web.core.BaseController;
import com.layjava.test.domain.bo.UserBo;
import com.layjava.test.domain.vo.UserVo;
import com.layjava.test.service.UserService;
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


    @Get
    @Mapping("/list")
    public List<UserVo> list(UserBo userBo) {
        return userService.getUserVoList(userBo);
    }

    @Get
    @Mapping("/{id}")
    public UserVo get(Integer id) {
        return userService.getUserVoById(id);
    }

    @Post
    @Mapping
    public boolean save(UserBo userBo) {
        return userService.saveUser(userBo);
    }

    @Put
    @Mapping
    public boolean update(UserBo userBo) {
        return userService.updateUserById(userBo);
    }

    @Delete
    @Mapping("/{id}")
    public boolean delete(Integer id) {
        return userService.deleteUserById(id);
    }

}