package com.layjava.system.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Assert;
import com.layjava.common.core.validate.group.AddGroup;
import com.layjava.common.core.validate.group.UpdateGroup;
import com.layjava.common.dao.core.page.PageQuery;
import com.layjava.common.dao.core.page.PageResult;
import com.layjava.system.domain.bo.SysClientBo;
import com.layjava.system.domain.vo.SysClientVo;
import com.layjava.system.service.SysClientService;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.*;
import org.noear.solon.validation.annotation.NotBlank;
import org.noear.solon.validation.annotation.NotNull;
import org.noear.solon.validation.annotation.Validated;
import com.layjava.common.core.util.StringUtils;
import com.layjava.common.web.core.BaseController;

import java.util.List;

/**
 *
 * 系统授权表 控制器
 *
 * @author chengliang4810
 * @since 2024-04-25
 */
@Controller
@Mapping("/system/client")
public class SysClientController extends BaseController {

    @Inject
    private SysClientService sysClientService;

    /**
     * 分页查询系统授权表列表
     *
     * @param pageQuery 分页查询条件
     * @return 系统授权表分页列表数据
     */
    @Get
    @Mapping("/list/{pageNum}/{pageSize}")
    public PageResult<SysClientVo> pageList(SysClientBo sysClientBo, PageQuery pageQuery) {
        return sysClientService.getSysClientVoList(sysClientBo, pageQuery);
    }

    /**
     * 根据ID查询系统授权表
     *
     * @param id 系统授权表ID
     * @return 系统授权表数据
     */
    @Get
    @Mapping("/{id}")
    public SysClientVo get(@NotNull Long id) {
        return sysClientService.getSysClientVoById(id);
    }

    /**
     * 新增系统授权表
     *
     * @param sysClientBo 系统授权表新增对象
     */
    @Post
    @Mapping
    public void save(@Validated(AddGroup.class) SysClientBo sysClientBo) {
        boolean result = sysClientService.saveSysClient(sysClientBo);
        Assert.isTrue(result, "新增系统授权表失败");
    }

    /**
     * 根据ID更新系统授权表信息
     *
     * @param sysClientBo 系统授权表更新对象
     */
    @Put
    @Mapping
    public void update(@Validated(UpdateGroup.class) SysClientBo sysClientBo) {
        boolean result = sysClientService.updateSysClientById(sysClientBo);
        Assert.isTrue(result, "更新系统授权表失败");
    }

    /**
     * 根据ID删除系统授权表
     *
     * @param ids 系统授权表ID
     */
    @Delete
    @Mapping("/{ids}")
    public void delete(@NotBlank(message = "ID不允许为空") String ids) {
        List<Long> idList = StringUtils.splitTo(ids, Convert::toLong);
        int result = sysClientService.deleteSysClientById(idList);
        Assert.isTrue(result > 0, "删除系统授权表失败");
    }

}