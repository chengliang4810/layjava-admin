package com.layjava.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.layjava.common.core.domain.R;
import com.layjava.common.core.validate.group.AddGroup;
import com.layjava.common.core.validate.group.UpdateGroup;
import com.layjava.common.log.annotation.Log;
import com.layjava.common.log.enums.BusinessType;
import com.layjava.common.mybatis.core.page.PageQuery;
import com.layjava.common.mybatis.core.page.PageResult;
import com.layjava.common.web.core.BaseController;
import com.layjava.system.domain.bo.SysClientBo;
import com.layjava.system.domain.vo.SysClientVo;
import com.layjava.system.service.ISysClientService;
import org.noear.solon.annotation.*;
import org.noear.solon.core.handle.DownloadedFile;
import org.noear.solon.validation.annotation.NoRepeatSubmit;
import org.noear.solon.validation.annotation.NotEmpty;
import org.noear.solon.validation.annotation.NotNull;
import org.noear.solon.validation.annotation.Validated;

import java.util.List;

/**
 * 客户端管理
 *
 * @author Michelle.Chung
 * @date 2023-06-18
 */
@Controller
@Mapping("/system/client")
public class SysClientController extends BaseController {

    @Inject
    private ISysClientService sysClientService;

    /**
     * 查询客户端管理列表
     */
    @Get
    @Mapping("/list")
    @SaCheckPermission("system:client:list")
    public PageResult<SysClientVo> list(SysClientBo bo, PageQuery pageQuery) {
        return sysClientService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出客户端管理列表
     */
    @Post
    @Mapping("/export")
    @SaCheckPermission("system:client:export")
    @Log(title = "客户端管理", businessType = BusinessType.EXPORT)
    public DownloadedFile export(SysClientBo bo) {
//        List<SysClientVo> list = sysClientService.queryList(bo);
//        return ExcelUtil.exportExcel(list, "客户端管理", SysClientVo.class);
        return null;
    }

    /**
     * 获取客户端管理详细信息
     *
     * @param id 主键
     */
    @Get
    @Mapping("/{id}")
    @SaCheckPermission("system:client:query")
    public R<SysClientVo> getInfo(@NotNull(message = "主键不能为空") Long id) {
        return R.ok(sysClientService.queryById(id));
    }

    /**
     * 新增客户端管理
     */
    @Post
    @Mapping()
    @NoRepeatSubmit
    @SaCheckPermission("system:client:add")
    @Log(title = "客户端管理", businessType = BusinessType.INSERT)
    public R<Void> add(@Validated(AddGroup.class) SysClientBo bo) {
        return toAjax(sysClientService.insertByBo(bo));
    }

    /**
     * 修改客户端管理
     */
    @Put
    @Mapping()
    @NoRepeatSubmit
    @SaCheckPermission("system:client:edit")
    @Log(title = "客户端管理", businessType = BusinessType.UPDATE)
    public R<Void> edit(@Validated(UpdateGroup.class) SysClientBo bo) {
        return toAjax(sysClientService.updateByBo(bo));
    }

    /**
     * 状态修改
     */
    @Put
    @Mapping("/changeStatus")
    @SaCheckPermission("system:client:edit")
    @Log(title = "客户端管理", businessType = BusinessType.UPDATE)
    public R<Void> changeStatus(SysClientBo bo) {
        return toAjax(sysClientService.updateUserStatus(bo.getId(), bo.getStatus()));
    }

    /**
     * 删除客户端管理
     *
     * @param ids 主键串
     */
    @Delete
    @Mapping("/{ids}")
    @SaCheckPermission("system:client:remove")
    @Log(title = "客户端管理", businessType = BusinessType.DELETE)
    public R<Void> remove(@NotEmpty(message = "主键不能为空") Long[] ids) {
        return toAjax(sysClientService.deleteWithValidByIds(List.of(ids), true));
    }
}
