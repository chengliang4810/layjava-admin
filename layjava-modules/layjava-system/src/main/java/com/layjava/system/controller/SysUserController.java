package com.layjava.system.controller;

import cn.hutool.core.convert.Convert;
import cn.zhxu.bs.BeanSearcher;
import cn.zhxu.bs.SearchResult;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.layjava.common.mybatis.core.page.PageQuery;
import com.layjava.common.mybatis.core.page.PageResult;
import com.layjava.system.domain.SysUser;
import com.layjava.system.domain.bo.SysUserBo;
import com.layjava.system.domain.vo.SysUserVo;
import com.layjava.system.service.SysUserService;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.*;
import org.noear.solon.validation.annotation.NotBlank;
import org.noear.solon.validation.annotation.NotNull;
import org.noear.solon.validation.annotation.Validated;
import com.layjava.common.core.util.MapstructUtils;
import com.layjava.common.core.util.StringUtils;
import com.layjava.common.web.core.BaseController;

import java.util.List;

/**
 *
 * 用户信息表 控制器
 *
 * @author chengliang4810
 * @since 2024-04-24
 */
@Controller
@Mapping("/system/user")
public class SysUserController extends BaseController {

    @Inject
    private SysUserService sysUserService;
    @Inject
    private BeanSearcher beanSearcher;

    /**
     * 查询用户信息表列表
     *
     * @param sysUserBo 用户信息表查询条件
     * @return 用户信息表列表数据
     */
    @Get
    @Mapping("/list")
    public List<SysUserVo> list(SysUserBo sysUserBo) {
        List<SysUser> searchAll = beanSearcher.searchAll(SysUser.class);
        return MapstructUtils.convert(searchAll, SysUserVo.class);
    }

    /**
     * 分页查询用户信息表列表
     *
     * @param pageQuery 分页查询条件
     * @return 用户信息表分页列表数据
     */
    @Get
    @Mapping("/list/{pageNum}/{pageSize}")
    public PageResult<SysUserVo> pageList(PageQuery pageQuery) {
        SearchResult<SysUser> search = beanSearcher.search(SysUser.class);
        return PageResult.build(search, SysUserVo.class);
    }

    /**
     * 根据ID查询用户信息表
     *
     * @param id 用户信息表ID
     * @return 用户信息表数据
     */
    @Get
    @Mapping("/{id}")
    public SysUserVo get(@NotNull Long id) {
        return sysUserService.getSysUserVoById(id);
    }

    /**
     * 新增用户信息表
     *
     * @param sysUserBo 用户信息表新增对象
     */
    @Post
    @Mapping
    public void save(@Validated SysUserBo sysUserBo) {
        boolean result = sysUserService.saveSysUser(sysUserBo);
        Assert.isTrue(result, "新增用户信息表失败");
    }

    /**
     * 根据ID更新用户信息表信息
     *
     * @param sysUserBo 用户信息表更新对象
     */
    @Put
    @Mapping
    public void update(@Validated SysUserBo sysUserBo) {
        boolean result = sysUserService.updateSysUserById(sysUserBo);
        Assert.isTrue(result, "更新用户信息表失败");
    }

    /**
     * 根据ID删除用户信息表
     *
     * @param ids 用户信息表ID
     */
    @Delete
    @Mapping("/{ids}")
    public void delete(@NotBlank(message = "ID不允许为空") String ids) {
        List<Long> idList = StringUtils.splitTo(ids, Convert::toLong);
        int result = sysUserService.deleteSysUserById(idList);
        Assert.isTrue(result > 0, "删除用户信息表失败");
    }

}