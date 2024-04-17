package com.layjava.test.controller;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.layjava.common.core.util.StringUtils;
import com.layjava.common.mybatis.core.page.PageQuery;
import com.layjava.common.mybatis.core.page.PageResult;
import com.layjava.common.web.core.BaseController;
import com.layjava.test.domain.bo.UserBo;
import com.layjava.test.domain.vo.UserVo;
import com.layjava.test.service.UserService;
import org.noear.solon.annotation.*;
import org.noear.solon.validation.annotation.NotBlank;
import org.noear.solon.validation.annotation.NotNull;
import org.noear.solon.validation.annotation.Validated;

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

    /**
     * 查询用户列表
     *
     * @param userBo 用户信息查询条件
     * @return 用户信息列表数据
     */
    @Get
    @Mapping("/list")
    public List<UserVo> list(UserBo userBo) {
        return userService.getUserVoList(userBo);
    }

    /**
     * 分页查询用户列表
     *
     * @param userBo    用户信息查询条件
     * @param pageQuery 分页查询条件
     * @return 用户信息分页列表数据
     */
    @Get
    @Mapping("/list/{pageNum}/{pageSize}")
    public PageResult<UserVo> pageList(UserBo userBo, PageQuery pageQuery) {
        return userService.getUserVoList(userBo, pageQuery);
    }

    /**
     * 根据Id查询用户信息
     *
     * @param id Id主键
     * @return 用户信息
     */
    @Get
    @Mapping("/{id}")
    public UserVo get(@NotNull Long id) {
        return userService.getUserVoById(id);
    }

    /**
     * 新增用户信息
     *
     * @param userBo 用户信息
     */
    @Post
    @Mapping
    public void save(@Validated(Addition.class) UserBo userBo) {
        boolean result = userService.saveUser(userBo);
        Assert.isTrue(result, "新增失败");
    }

    /**
     * 根据Id更新用户信息
     *
     * @param userBo 用户信息
     */
    @Put
    @Mapping
    public void update(UserBo userBo) {
        boolean result = userService.updateUserById(userBo);
        Assert.isTrue(result, "更新失败");
    }

    /**
     * 批量删除用户信息
     *
     * @param ids id列表
     */
    @Delete
    @Mapping("/{ids}")
    public void delete(@NotBlank(message = "ID不允许为空") String ids) {
        List<Long> idList = StringUtils.splitTo(ids, Convert::toLong);
        int result = userService.deleteUserById(idList);
        Assert.isTrue(result > 0, "删除失败");
    }

}