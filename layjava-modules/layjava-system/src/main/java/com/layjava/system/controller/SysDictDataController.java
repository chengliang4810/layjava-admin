package com.layjava.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.util.ObjectUtil;
import com.layjava.common.core.domain.R;
import com.layjava.common.dao.core.page.PageQuery;
import com.layjava.common.dao.core.page.TableDataInfo;
import com.layjava.common.excel.utils.ExcelUtil;
import com.layjava.common.log.annotation.Log;
import com.layjava.common.log.enums.BusinessType;
import com.layjava.common.web.core.BaseController;
import com.layjava.system.domain.bo.SysDictDataBo;
import com.layjava.system.domain.vo.SysDictDataVo;
import com.layjava.system.service.ISysDictDataService;
import com.layjava.system.service.ISysDictTypeService;
import org.noear.solon.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据字典信息
 *
 * @author Lion Li
 */


@Controller
@Mapping("/system/dict/data")
public class SysDictDataController extends BaseController {

    @Inject
    private ISysDictDataService dictDataService;
    @Inject
    private ISysDictTypeService dictTypeService;

    /**
     * 查询字典数据列表
     */
    @Get
    @Mapping("/list")
    @SaCheckPermission("system:dict:list")
    public TableDataInfo<SysDictDataVo> list(SysDictDataBo dictData, PageQuery pageQuery) {
        return dictDataService.selectPageDictDataList(dictData, pageQuery);
    }

    /**
     * 导出字典数据列表
     */
    @Post
    @Mapping("/export")
    @Log(title = "字典数据", businessType = BusinessType.EXPORT)
    @SaCheckPermission("system:dict:export")
    public void export(SysDictDataBo dictData) {
        List<SysDictDataVo> list = dictDataService.selectDictDataList(dictData);
        // ExcelUtil.exportExcel(list, "字典数据", SysDictDataVo.class, response);
    }

    /**
     * 查询字典数据详细
     *
     * @param dictCode 字典code
     */
    @Get
    @Mapping(value = "/{dictCode}")
    @SaCheckPermission("system:dict:query")
    public R<SysDictDataVo> getInfo(Long dictCode) {
        return R.ok(dictDataService.selectDictDataById(dictCode));
    }

    /**
     * 根据字典类型查询字典数据信息
     *
     * @param dictType 字典类型
     */
    @Get
    @Mapping(value = "/type/{dictType}")
    public R<List<SysDictDataVo>> dictType(String dictType) {
        List<SysDictDataVo> data = dictTypeService.selectDictDataByType(dictType);
        if (ObjectUtil.isNull(data)) {
            data = new ArrayList<>();
        }
        return R.ok(data);
    }

    /**
     * 新增字典类型
     */
    @Post
    @Mapping
    @SaCheckPermission("system:dict:add")
    @Log(title = "字典数据", businessType = BusinessType.INSERT)
    public R<Void> add(SysDictDataBo dict) {
        dictDataService.insertDictData(dict);
        return R.ok();
    }

    /**
     * 修改保存字典类型
     */
    @Put
    @Mapping
    @SaCheckPermission("system:dict:edit")
    @Log(title = "字典数据", businessType = BusinessType.UPDATE)
    public R<Void> edit(SysDictDataBo dict) {
        dictDataService.updateDictData(dict);
        return R.ok();
    }

    /**
     * 删除字典类型
     *
     * @param dictCodes 字典code串
     */
    @Delete
    @Mapping("/{dictCodes}")
    @SaCheckPermission("system:dict:remove")
    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    public R<Void> remove(Long[] dictCodes) {
        dictDataService.deleteDictDataByIds(dictCodes);
        return R.ok();
    }
}
