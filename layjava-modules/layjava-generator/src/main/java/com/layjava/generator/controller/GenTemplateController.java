package com.layjava.generator.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.layjava.common.core.domain.R;
import com.layjava.common.core.validate.group.AddGroup;
import com.layjava.common.core.validate.group.UpdateGroup;
import com.layjava.common.log.annotation.Log;
import com.layjava.common.log.enums.BusinessType;
import com.layjava.common.mybatis.core.page.PageQuery;
import com.layjava.common.mybatis.core.page.PageResult;
import com.layjava.common.web.core.BaseController;
import com.layjava.generator.domain.bo.GenTemplateBo;
import com.layjava.generator.domain.vo.GenTemplateVo;
import com.layjava.generator.service.IGenTemplateService;
import lombok.RequiredArgsConstructor;
import org.noear.solon.annotation.*;
import org.noear.solon.validation.annotation.NoRepeatSubmit;
import org.noear.solon.validation.annotation.NotEmpty;
import org.noear.solon.validation.annotation.NotNull;
import org.noear.solon.validation.annotation.Validated;

import java.util.List;

/**
 * 代码生成模板
 *
 * @author chengliang4810
 * @date 2025-01-05
 */
@Controller
@RequiredArgsConstructor
@Mapping("/generator/template")
public class GenTemplateController extends BaseController {

    private final IGenTemplateService genTemplateService;

    /**
     * 查询代码生成模板列表
     */
    @Get
    @Mapping("/list")
    @SaCheckPermission("generator:template:list")
    public PageResult<GenTemplateVo> list(GenTemplateBo bo, PageQuery pageQuery) {
        return genTemplateService.queryPageList(bo, pageQuery);
    }

    /**
     * 获取代码生成模板详细信息
     *
     * @param id 主键
     */
    @Get
    @Mapping("/{id}")
    @SaCheckPermission("generator:template:query")
    public GenTemplateVo getInfo(@NotNull(message = "主键不能为空") Long id) {
        return genTemplateService.queryById(id);
    }

    /**
     * 新增代码生成模板
     */
    @Post
    @Mapping
    @NoRepeatSubmit
    @SaCheckPermission("generator:template:add")
    @Log(title = "代码生成模板", businessType = BusinessType.INSERT)
    public R<Void> add(@Validated(AddGroup.class) GenTemplateBo bo) {
        return toAjax(genTemplateService.insertByBo(bo));
    }

    /**
     * 修改代码生成模板
     */
    @Put
    @Mapping
    @NoRepeatSubmit
    @SaCheckPermission("generator:template:edit")
    @Log(title = "代码生成模板", businessType = BusinessType.UPDATE)
    public R<Void> edit(@Validated(UpdateGroup.class) GenTemplateBo bo) {
        return toAjax(genTemplateService.updateByBo(bo));
    }

    /**
     * 删除代码生成模板
     *
     * @param ids 主键串
     */
    @Delete
    @Mapping("/{ids}")
    @SaCheckPermission("generator:template:remove")
    @Log(title = "代码生成模板", businessType = BusinessType.DELETE)
    public R<Void> remove(@NotEmpty(message = "主键不能为空") Long[] ids) {
        return toAjax(genTemplateService.deleteWithValidByIds(List.of(ids), true));
    }
}
