package com.jimuqu.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.jimuqu.common.core.checker.Assert;
import com.jimuqu.common.core.validate.group.AddGroup;
import com.jimuqu.common.core.validate.group.UpdateGroup;
import com.jimuqu.common.log.annotation.Log;
import com.jimuqu.common.log.enums.BusinessType;
import com.jimuqu.common.mybatis.core.Page;
import com.jimuqu.common.mybatis.core.page.PageQuery;
import com.jimuqu.common.web.core.BaseController;
import com.jimuqu.system.domain.bo.SysClientBo;
import com.jimuqu.system.domain.query.SysClientQuery;
import com.jimuqu.system.domain.vo.SysClientVo;
import com.jimuqu.system.service.SysClientService;
import lombok.RequiredArgsConstructor;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Get;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Post;
import org.noear.solon.validation.annotation.NoRepeatSubmit;
import org.noear.solon.validation.annotation.NotEmpty;
import org.noear.solon.validation.annotation.NotNull;
import org.noear.solon.validation.annotation.Validated;

import java.util.List;

/**
 * 授权管理对象 sys_client Controller
 *
 * @author chengliang4810
 * @since 2025-05-27
 */
@Post
@Controller
@RequiredArgsConstructor
@Mapping("/system/client")
public class SysClientController extends BaseController {

    private final SysClientService sysClientService;

    /**
     * 查询授权管理对象 sys_client列表
     */
    @Get
    @Mapping("/list")
    @SaCheckPermission("system:client:list")
    public Page<SysClientVo> list(SysClientQuery query, PageQuery pageQuery) {
        return sysClientService.queryPageList(query, pageQuery);
    }

    /**
     * 获取授权管理对象 sys_client详细信息
     *
     * @param id 授权管理对象 sys_client主键
     */
    @Get
    @Mapping("/{id}")
    @SaCheckPermission("system:client:query")
    public SysClientVo getInfo(@NotNull(message = "授权管理对象 sys_client主键不能为空") Long id) {
        return sysClientService.queryById(id);
    }

    /**
     * 新增授权管理对象 sys_client
     */
    @Mapping("/add")
    @NoRepeatSubmit
    @SaCheckPermission("system:client:add")
    @Log(title = "新增授权管理对象 sys_client", businessType = BusinessType.ADD)
    public Long add(@Validated(AddGroup.class) SysClientBo bo) {
        boolean result = sysClientService.insertByBo(bo);
        Assert.isTrue(result, "新增授权管理对象 sys_client失败");
        return bo.getId();
    }

    /**
     * 更新授权管理对象 sys_client
     */
    @NoRepeatSubmit
    @Mapping("/update")
    @SaCheckPermission("system:client:update")
    @Log(title = "更新授权管理对象 sys_client", businessType = BusinessType.UPDATE)
    public void edit(@Validated(UpdateGroup.class) SysClientBo bo) {
        boolean result = sysClientService.updateByBo(bo);
        Assert.isTrue(result, "更新授权管理对象 sys_client失败");
    }

    /**
     * 删除授权管理对象 sys_client
     */
    @Mapping("/delete/{ids}")
    @SaCheckPermission("system:client:delete")
    @Log(title = "删除授权管理对象 sys_client", businessType = BusinessType.DELETE)
    public Integer delete(@NotEmpty(message = "主键不能为空") List<Long> ids) {
        Integer num = sysClientService.deleteByIds(ids);
        Assert.gtZero(num, "删除授权管理对象 sys_client失败");
        return num;
    }

}