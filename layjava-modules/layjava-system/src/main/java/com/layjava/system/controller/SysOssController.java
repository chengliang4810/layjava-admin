package com.layjava.system.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.convert.Convert;
import cn.zhxu.bs.BeanSearcher;
import cn.zhxu.bs.SearchResult;
import com.layjava.common.core.validate.group.AddGroup;
import com.layjava.common.core.validate.group.UpdateGroup;
import com.layjava.common.mybatis.core.page.PageQuery;
import com.layjava.common.mybatis.core.page.PageResult;
import com.layjava.system.domain.SysOss;
import com.layjava.system.domain.bo.SysOssBo;
import com.layjava.system.domain.vo.SysOssVo;
import com.layjava.system.service.SysOssService;
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
 * OSS对象存储表 控制器
 *
 * @author chengliang4810
 * @since 2024-04-30
 */
@Controller
@Mapping("/system/oss")
public class SysOssController extends BaseController {

    @Inject
    private SysOssService sysOssService;
    @Inject
    private BeanSearcher beanSearcher;

    /**
     * 查询OSS对象存储表列表
     *
     * @param sysOssBo OSS对象存储表查询条件
     * @return OSS对象存储表列表数据
     */
    @Get
    @Mapping("/list")
    public List<SysOssVo> list(SysOssBo sysOssBo) {
        List<SysOss> searchAll = beanSearcher.searchAll(SysOss.class);
        return MapstructUtils.convert(searchAll, SysOssVo.class);
    }

    /**
     * 分页查询OSS对象存储表列表
     *
     * @param pageQuery 分页查询条件
     * @return OSS对象存储表分页列表数据
     */
    @Get
    @Mapping("/list/{pageNum}/{pageSize}")
    public PageResult<SysOssVo> pageList(PageQuery pageQuery) {
        SearchResult<SysOss> search = beanSearcher.search(SysOss.class);
        return PageResult.build(search, SysOssVo.class);
    }

    /**
     * 根据ID查询OSS对象存储表
     *
     * @param id OSS对象存储表ID
     * @return OSS对象存储表数据
     */
    @Get
    @Mapping("/{id}")
    public SysOssVo get(@NotNull Long id) {
        return sysOssService.getSysOssVoById(id);
    }

    /**
     * 新增OSS对象存储表
     *
     * @param sysOssBo OSS对象存储表新增对象
     */
    @Post
    @Mapping
    public void save(@Validated(AddGroup.class) SysOssBo sysOssBo) {
        boolean result = sysOssService.saveSysOss(sysOssBo);
        Assert.isTrue(result, "新增OSS对象存储表失败");
    }

    /**
     * 根据ID更新OSS对象存储表信息
     *
     * @param sysOssBo OSS对象存储表更新对象
     */
    @Put
    @Mapping
    public void update(@Validated(UpdateGroup.class) SysOssBo sysOssBo) {
        boolean result = sysOssService.updateSysOssById(sysOssBo);
        Assert.isTrue(result, "更新OSS对象存储表失败");
    }

    /**
     * 根据ID删除OSS对象存储表
     *
     * @param ids OSS对象存储表ID
     */
    @Delete
    @Mapping("/{ids}")
    public void delete(@NotBlank(message = "ID不允许为空") String ids) {
        List<Long> idList = StringUtils.splitTo(ids, Convert::toLong);
        int result = sysOssService.deleteSysOssById(idList);
        Assert.isTrue(result > 0, "删除OSS对象存储表失败");
    }

}