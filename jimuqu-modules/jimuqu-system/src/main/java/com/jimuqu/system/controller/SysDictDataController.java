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
import com.jimuqu.system.domain.bo.SysDictDataBo;
import com.jimuqu.system.domain.vo.SysDictDataVo;
import com.jimuqu.system.domain.query.SysDictDataQuery;
import com.jimuqu.system.service.SysDictDataService;
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
 * 字典数据 Controller
 *
 * @author chengliang4810
 * @since 2025-05-27
 */
@Post
@Controller
@RequiredArgsConstructor
@Mapping("/system/dict/data")
public class SysDictDataController extends BaseController {

    private final SysDictDataService sysDictDataService;

    /**
     * 查询字典数据列表
     */
    @Get
    @Mapping("/list")
    @SaCheckPermission("system:dict:list")
    public Page<SysDictDataVo> list(SysDictDataQuery query, PageQuery pageQuery) {
        return sysDictDataService.queryPageList(query, pageQuery);
    }

    /**
     * 获取字典数据详细信息
     *
     * @param id 字典数据主键
     */
    @Get
    @Mapping("/{id}")
    @SaCheckPermission("system:dict:query")
    public SysDictDataVo getInfo(@NotNull(message = "字典数据主键不能为空") Long id) {
        return sysDictDataService.queryById(id);
    }

    /**
     * 获取字典数据详细信息
     *
     * @param dictTypeKey 字典类型标识
     */
    @Get
    @Mapping("/typeKey/{dictTypeKey}")
    @SaCheckPermission("system:dict:query")
    public List<SysDictDataVo> getListByDictTypeKey(@NotNull(message = "字典数据主键不能为空") String dictTypeKey) {
        return sysDictDataService.queryListByTypeKey(dictTypeKey);
    }

    /**
     * 新增字典数据
     */
    @Mapping("/add")
    @NoRepeatSubmit
    @SaCheckPermission("system:dict:add")
    @Log(title = "新增字典数据", businessType = BusinessType.ADD)
    public Long add(@Validated(AddGroup.class) SysDictDataBo bo) {
        boolean result = sysDictDataService.insertByBo(bo);
        Assert.isTrue(result, "新增字典数据失败");
        return bo.getId();
    }

    /**
     * 更新字典数据
     */
    @NoRepeatSubmit
    @Mapping("/update")
    @SaCheckPermission("system:dict:update")
    @Log(title = "更新字典数据", businessType = BusinessType.UPDATE)
    public void edit(@Validated(UpdateGroup.class) SysDictDataBo bo) {
        boolean result = sysDictDataService.updateByBo(bo);
        Assert.isTrue(result, "更新字典数据失败");
    }

    /**
     * 删除字典数据
     */
    @Mapping("/delete/{ids}")
    @SaCheckPermission("system:dict:delete")
    @Log(title = "删除字典数据", businessType = BusinessType.DELETE)
    public Integer delete(@NotEmpty(message = "主键不能为空") List<Long> ids) {
        Integer num = sysDictDataService.deleteByIds(ids);
        Assert.gtZero(num, "删除字典数据失败");
        return num;
    }

}