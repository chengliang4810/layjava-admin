package com.layjava.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.layjava.common.core.domain.R;
import com.layjava.common.mybatis.core.page.PageQuery;
import com.layjava.common.mybatis.core.page.TableDataInfo;
import com.layjava.common.log.annotation.Log;
import com.layjava.common.log.enums.BusinessType;
import com.layjava.common.web.core.BaseController;
import com.layjava.system.domain.bo.SysConfigBo;
import com.layjava.system.domain.vo.SysConfigVo;
import com.layjava.system.service.ISysConfigService;
import org.noear.solon.annotation.*;

import java.util.List;

/**
 * 参数配置 信息操作处理
 *
 * @author Lion Li
 */
@Controller
@Mapping("/system/config")
public class SysConfigController extends BaseController {

    @Inject
    private ISysConfigService configService;

    /**
     * 获取参数配置列表
     */
    @SaCheckPermission("system:config:list")
    @Get
    @Mapping("/list")
    public TableDataInfo<SysConfigVo> list(SysConfigBo config, PageQuery pageQuery) {
        return configService.selectPageConfigList(config, pageQuery);
    }

    /**
     * 导出参数配置列表
     */
    @Log(title = "参数管理", businessType = BusinessType.EXPORT)
    @SaCheckPermission("system:config:export")
    @Post
    @Mapping("/export")
    public void export(SysConfigBo config) {
        List<SysConfigVo> list = configService.selectConfigList(config);
        // ExcelUtil.exportExcel(list, "参数数据", SysConfigVo.class, response);
    }

    /**
     * 根据参数编号获取详细信息
     *
     * @param configId 参数ID
     */
    @SaCheckPermission("system:config:query")
    @Get
    @Mapping(value = "/{configId}")
    public R<SysConfigVo> getInfo(Long configId) {
        return R.ok(configService.selectConfigById(configId));
    }

    /**
     * 根据参数键名查询参数值
     *
     * @param configKey 参数Key
     */
    @Get
    @Mapping(value = "/configKey/{configKey}")
    public R<String> getConfigKey(String configKey) {
        return R.ok("操作成功", configService.selectConfigByKey(configKey));
    }

    /**
     * 新增参数配置
     */
    @SaCheckPermission("system:config:add")
    @Log(title = "参数管理", businessType = BusinessType.INSERT)
    @Post
    @Mapping
    public R<Void> add(SysConfigBo config) {
        if (!configService.checkConfigKeyUnique(config)) {
            return R.fail("新增参数'" + config.getConfigName() + "'失败，参数键名已存在");
        }
        configService.insertConfig(config);
        return R.ok();
    }

    /**
     * 修改参数配置
     */
    @SaCheckPermission("system:config:edit")
    @Log(title = "参数管理", businessType = BusinessType.UPDATE)
    @Put
    @Mapping
    public R<Void> edit(SysConfigBo config) {
        if (!configService.checkConfigKeyUnique(config)) {
            return R.fail("修改参数'" + config.getConfigName() + "'失败，参数键名已存在");
        }
        configService.updateConfig(config);
        return R.ok();
    }

    /**
     * 根据参数键名修改参数配置
     */
    @SaCheckPermission("system:config:edit")
    @Log(title = "参数管理", businessType = BusinessType.UPDATE)
    @Put
    @Mapping("/updateByKey")
    public R<Void> updateByKey(SysConfigBo config) {
        configService.updateConfig(config);
        return R.ok();
    }

    /**
     * 删除参数配置
     *
     * @param configIds 参数ID串
     */
    @SaCheckPermission("system:config:remove")
    @Log(title = "参数管理", businessType = BusinessType.DELETE)
    @Delete
    @Mapping("/{configIds}")
    public R<Void> remove(Long[] configIds) {
        configService.deleteConfigByIds(configIds);
        return R.ok();
    }

    /**
     * 刷新参数缓存
     */
    @SaCheckPermission("system:config:remove")
    @Log(title = "参数管理", businessType = BusinessType.CLEAN)
    @Delete
    @Mapping("/refreshCache")
    public R<Void> refreshCache() {
        configService.resetConfigCache();
        return R.ok();
    }
}
