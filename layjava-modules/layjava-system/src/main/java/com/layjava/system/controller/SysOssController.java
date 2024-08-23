package com.layjava.system.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import org.dromara.hutool.core.util.ObjUtil;
import com.layjava.common.core.domain.R;
import com.layjava.common.core.validate.group.QueryGroup;
import com.layjava.common.mybatis.core.page.PageQuery;
import com.layjava.common.log.annotation.Log;
import com.layjava.common.log.enums.BusinessType;
import com.layjava.common.mybatis.core.page.PageResult;
import com.layjava.common.web.core.BaseController;
import com.layjava.system.domain.bo.SysOssBo;
import com.layjava.system.domain.vo.SysOssUploadVo;
import com.layjava.system.domain.vo.SysOssVo;
import com.layjava.system.service.ISysOssService;
import org.noear.solon.annotation.*;
import org.noear.solon.core.handle.DownloadedFile;
import org.noear.solon.core.handle.UploadedFile;
import org.noear.solon.validation.annotation.NotEmpty;
import org.noear.solon.validation.annotation.Validated;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 文件上传 控制层
 *
 * @author Lion Li
 */
@Controller
@Mapping("/resource/oss" )
public class SysOssController extends BaseController {

    @Inject
    private ISysOssService ossService;

    /**
     * 查询OSS对象存储列表
     */
    @Get
    @Mapping("/list" )
    @SaCheckPermission("system:oss:list" )
    public PageResult<SysOssVo> list(@Validated(QueryGroup.class) SysOssBo bo, PageQuery pageQuery) {
        return ossService.queryPageList(bo, pageQuery);
    }

    /**
     * 查询OSS对象基于id串
     *
     * @param ossIds OSS对象ID串
     */
    @Get
    @Mapping("/listByIds/{ossIds}" )
    @SaCheckPermission("system:oss:list" )
    public R<List<SysOssVo>> listByIds(@NotEmpty(message = "主键不能为空" ) Long[] ossIds) {
        List<SysOssVo> list = ossService.listByIds(Arrays.asList(ossIds));
        return R.ok(list);
    }

    /**
     * 上传OSS对象存储
     *
     * @param file 文件
     */
    @Post
    @Mapping(value = "/upload" )
    @SaCheckPermission("system:oss:upload" )
    @Log(title = "OSS对象存储", businessType = BusinessType.INSERT)
    public R<SysOssUploadVo> upload(UploadedFile file) {
        if (ObjUtil.isNull(file)) {
            return R.fail("上传文件不能为空" );
        }
        SysOssVo oss = ossService.upload(file);
        SysOssUploadVo uploadVo = new SysOssUploadVo();
        uploadVo.setUrl(oss.getUrl());
        uploadVo.setFileName(oss.getOriginalName());
        uploadVo.setOssId(oss.getOssId().toString());
        return R.ok(uploadVo);
    }

    /**
     * 下载OSS对象
     *
     * @param ossId OSS对象ID
     */
    @Get
    @Mapping("/download/{ossId}" )
    @SaCheckPermission("system:oss:download" )
    public DownloadedFile download(Long ossId) throws IOException {
        return ossService.download(ossId);
    }

    /**
     * 删除OSS对象存储
     *
     * @param ossIds OSS对象ID串
     */
    @Delete
    @Mapping("/{ossIds}" )
    @SaCheckPermission("system:oss:remove" )
    @Log(title = "OSS对象存储", businessType = BusinessType.DELETE)
    public R<Void> remove(@NotEmpty(message = "主键不能为空" ) Long[] ossIds) {
        return toAjax(ossService.deleteWithValidByIds(List.of(ossIds), true));
    }

}
