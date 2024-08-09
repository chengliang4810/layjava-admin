package com.layjava.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.layjava.common.core.domain.R;
import com.layjava.common.core.validate.group.AddGroup;
import com.layjava.common.core.validate.group.QueryGroup;
import com.layjava.common.core.validate.group.UpdateGroup;
import com.layjava.common.mybatis.core.page.PageQuery;
import com.layjava.common.mybatis.core.page.TableDataInfo;
import com.layjava.common.log.annotation.Log;
import com.layjava.common.log.enums.BusinessType;
import com.layjava.common.web.core.BaseController;
import com.layjava.system.domain.bo.SysOssConfigBo;
import com.layjava.system.domain.vo.SysOssConfigVo;
import com.layjava.system.service.ISysOssConfigService;
import org.noear.solon.annotation.*;
import org.noear.solon.validation.annotation.NoRepeatSubmit;
import org.noear.solon.validation.annotation.NotEmpty;
import org.noear.solon.validation.annotation.NotNull;
import org.noear.solon.validation.annotation.Validated;

import java.util.List;

/**
 * 对象存储配置
 *
 * @author Lion Li
 * @author 孤舟烟雨
 * @date 2021-08-13
 */
@Controller
@Mapping("/resource/oss/config")
public class SysOssConfigController extends BaseController {

    @Inject
    private ISysOssConfigService ossConfigService;

    /**
     * 查询对象存储配置列表
     */
    @Get
    @Mapping("/list")
    @SaCheckPermission("system:ossConfig:list")
    public TableDataInfo<SysOssConfigVo> list(@Validated(QueryGroup.class) SysOssConfigBo bo, PageQuery pageQuery) {
        return ossConfigService.queryPageList(bo, pageQuery);
    }

    /**
     * 获取对象存储配置详细信息
     *
     * @param ossConfigId OSS配置ID
     */
    @SaCheckPermission("system:ossConfig:list")
    @Get
    @Mapping("/{ossConfigId}")
    public R<SysOssConfigVo> getInfo(@NotNull(message = "主键不能为空")
                                     Long ossConfigId) {
        return R.ok(ossConfigService.queryById(ossConfigId));
    }

    /**
     * 新增对象存储配置
     */
    @Post
    @Mapping()
    @NoRepeatSubmit
    @Log(title = "对象存储配置", businessType = BusinessType.INSERT)
    @SaCheckPermission("system:ossConfig:add")
    public R<Void> add(@Validated(AddGroup.class) SysOssConfigBo bo) {
        return toAjax(ossConfigService.insertByBo(bo));
    }

    /**
     * 修改对象存储配置
     */
    @Put
    @Mapping()
    @NoRepeatSubmit
    @SaCheckPermission("system:ossConfig:edit")
    @Log(title = "对象存储配置", businessType = BusinessType.UPDATE)
    public R<Void> edit(@Validated(UpdateGroup.class) SysOssConfigBo bo) {
        return toAjax(ossConfigService.updateByBo(bo));
    }

    /**
     * 删除对象存储配置
     *
     * @param ossConfigIds OSS配置ID串
     */
    @Delete
    @Mapping("/{ossConfigIds}")
    @SaCheckPermission("system:ossConfig:remove")
    @Log(title = "对象存储配置", businessType = BusinessType.DELETE)
    public R<Void> remove(@NotEmpty(message = "主键不能为空") Long[] ossConfigIds) {
        return toAjax(ossConfigService.deleteWithValidByIds(List.of(ossConfigIds), true));
    }

    /**
     * 状态修改
     */
    @Put
    @Mapping("/changeStatus")
    @SaCheckPermission("system:ossConfig:edit")
    @Log(title = "对象存储状态修改", businessType = BusinessType.UPDATE)
    public R<Void> changeStatus(SysOssConfigBo bo) {
        return toAjax(ossConfigService.updateOssConfigStatus(bo));
    }

}
