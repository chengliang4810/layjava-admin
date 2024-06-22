package com.layjava.system.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.layjava.common.security.utils.LoginHelper;
import com.layjava.common.web.core.BaseController;
import com.layjava.system.domain.bo.SysMenuBo;
import com.layjava.system.domain.vo.SysMenuVo;
import com.layjava.system.service.SysMenuService;
import org.noear.solon.annotation.*;
import org.noear.solon.validation.annotation.NotBlank;
import org.noear.solon.validation.annotation.NotNull;
import org.noear.solon.validation.annotation.Validated;

import java.util.List;

/**
 *
 * 用户信息表 控制器
 *
 * @author chengliang4810
 * @since 2024-04-24
 */
@Controller
@Mapping("/system/menu")
public class SysMenuController extends BaseController {

    @Inject
    private SysMenuService sysMenuService;

    /**
     * 查询用户信息表列表
     *
     * @param SysMenuBo 用户信息表查询条件
     * @return 用户信息表列表数据
     */
    @Get
    @Mapping("/list")
    public List<SysMenuVo> list(SysMenuBo SysMenuBo) {
        return sysMenuService.selectMenuList(LoginHelper.getUserId());
    }

    /**
     * 根据ID查询用户信息表
     *
     * @param id 用户信息表ID
     * @return 用户信息表数据
     */
    @Get
    @Mapping("/{id}")
    public SysMenuVo get(@NotNull Long id) {
        return sysMenuService.selectMenuById(id);
    }

    /**
     * 新增用户信息表
     *
     * @param SysMenuBo 用户信息表新增对象
     */
    @Post
    @Mapping
    public void save(@Validated SysMenuBo SysMenuBo) {
        int result = sysMenuService.insertMenu(SysMenuBo);
        Assert.isTrue(result > 0, "新增用户信息表失败");
    }

    /**
     * 根据ID更新用户信息表信息
     *
     * @param SysMenuBo 用户信息表更新对象
     */
    @Put
    @Mapping
    public void update(@Validated SysMenuBo SysMenuBo) {
        int result = sysMenuService.updateMenu(SysMenuBo);
        Assert.isTrue(result > 0, "更新用户信息表失败");
    }

    /**
     * 根据ID删除用户信息表
     *
     * @param ids 用户信息表ID
     */
    @Delete
    @Mapping("/{ids}")
    public void delete(@NotBlank(message = "ID不允许为空") String ids) {
        System.out.println(ids);
        List<String> strIds = StrUtil.split(ids, ",");
        long result = sysMenuService.deleteMenuById(strIds);
        Assert.isTrue(result > 0, "删除用户信息表失败");
    }

}