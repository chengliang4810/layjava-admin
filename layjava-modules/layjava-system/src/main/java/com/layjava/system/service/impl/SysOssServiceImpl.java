package com.layjava.system.service.impl;

import com.layjava.common.core.exception.ServiceException;
import com.layjava.common.core.service.OssService;
import com.layjava.common.core.utils.StreamUtil;
import com.layjava.common.core.utils.StringUtil;
import com.layjava.common.mybatis.core.page.PageQuery;
import com.layjava.common.mybatis.core.page.PageResult;
import com.layjava.system.domain.SysOss;
import com.layjava.system.domain.bo.SysOssBo;
import com.layjava.system.domain.vo.SysOssVo;
import com.layjava.system.mapper.SysOssMapper;
import com.layjava.system.service.ISysOssService;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import org.dromara.hutool.core.convert.ConvertUtil;
import org.dromara.hutool.core.util.ObjUtil;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;
import org.noear.solon.core.handle.DownloadedFile;
import org.noear.solon.core.handle.UploadedFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.layjava.system.domain.table.SysOssTableDef.SYS_OSS;

/**
 * 文件上传 服务层实现
 *
 * @author Lion Li
 */

@Component
public class SysOssServiceImpl implements ISysOssService, OssService {

    @Inject
    private SysOssMapper baseMapper;

    @Override
    public PageResult<SysOssVo> queryPageList(SysOssBo bo, PageQuery pageQuery) {
        QueryWrapper lqw = buildQueryWrapper(bo);
        Page<SysOssVo> result = baseMapper.paginateAs(pageQuery, lqw, SysOssVo.class);
        List<SysOssVo> filterResult = StreamUtil.toList(result.getRecords(), this::matchingUrl);
        result.setRecords(filterResult);
        return PageResult.build(result);
    }

    @Override
    public List<SysOssVo> listByIds(Collection<Long> ossIds) {
        List<SysOssVo> list = new ArrayList<>();
        for (Long id : ossIds) {
            SysOssVo vo = this.getById(id);
            if (ObjUtil.isNotNull(vo)) {
                try {
                    list.add(this.matchingUrl(vo));
                } catch (Exception ignored) {
                    // 如果oss异常无法连接则将数据直接返回
                    list.add(vo);
                }
            }
        }
        return list;
    }

    @Override
    public String selectUrlByIds(String ossIds) {
        List<String> list = new ArrayList<>();
        for (Long id : StringUtil.splitTo(ossIds, ConvertUtil::toLong)) {
            SysOssVo vo = this.getById(id);
            if (ObjUtil.isNotNull(vo)) {
                try {
                    list.add(this.matchingUrl(vo).getUrl());
                } catch (Exception ignored) {
                    // 如果oss异常无法连接则将数据直接返回
                    list.add(vo.getUrl());
                }
            }
        }
        return String.join(StringUtil.SEPARATOR, list);
    }

    private QueryWrapper buildQueryWrapper(SysOssBo bo) {
        Map<String, Object> params = bo.getParams();
        return QueryWrapper.create().from(SYS_OSS)
                .where(SYS_OSS.FILE_NAME.like(bo.getFileName()))
                .and(SYS_OSS.ORIGINAL_NAME.like(bo.getOriginalName()))
                .and(SYS_OSS.FILE_SUFFIX.eq(bo.getFileSuffix()))
                .and(SYS_OSS.URL.eq(bo.getUrl()))
                .and(SYS_OSS.CREATE_TIME.between(params.get("beginCreateTime" ), params.get("endCreateTime" ), params.get("beginCreateTime" ) != null && params.get("endCreateTime" ) != null))
                .and(SYS_OSS.CREATE_BY.eq(bo.getCreateBy()))
                .and(SYS_OSS.SERVICE.eq(bo.getService()))
                .orderBy(SYS_OSS.OSS_ID, true);
    }

    // @Cacheable(cacheNames = CacheNames.SYS_OSS, key = "#ossId")
    @Override
    public SysOssVo getById(Long ossId) {
        return baseMapper.selectOneWithRelationsByIdAs(ossId, SysOssVo.class);
    }

    @Override
    public DownloadedFile download(Long ossId) throws IOException {
        SysOssVo sysOss = this.getById(ossId);
        if (ObjUtil.isNull(sysOss)) {
            throw new ServiceException("文件数据不存在!" );
        }
//        FileUtils.setAttachmentResponseHeader(response, sysOss.getOriginalName());
//        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE + "; charset=UTF-8");
//        OssClient storage = OssFactory.instance(sysOss.getService());
//        try (InputStream inputStream = storage.getObjectContent(sysOss.getUrl())) {
//            int available = inputStream.available();
//            IoUtil.copy(inputStream, response.getOutputStream(), available);
//            response.setContentLength(available);
//        } catch (Exception e) {
//            throw new ServiceException(e.getMessage());
//        }
        return null;
    }

    @Override
    public SysOssVo upload(UploadedFile file) {
//        String originalfileName = file.getOriginalFilename();
//        String suffix = StringUtils.substring(originalfileName, originalfileName.lastIndexOf("."), originalfileName.length());
//        OssClient storage = OssFactory.instance();
//        UploadResult uploadResult;
//        try {
//            uploadResult = storage.uploadSuffix(file.getBytes(), suffix, file.getContentType());
//        } catch (IOException e) {
//            throw new ServiceException(e.getMessage());
//        }
//        // 保存文件信息
//        return buildResultEntity(originalfileName, suffix, storage.getConfigKey(), uploadResult);

        return null;
    }

    @Override
    public SysOssVo upload(File file) {
//        String originalfileName = file.getName();
//        String suffix = StringUtils.substring(originalfileName, originalfileName.lastIndexOf("."), originalfileName.length());
//        OssClient storage = OssFactory.instance();
//        UploadResult uploadResult = storage.uploadSuffix(file, suffix);
//        // 保存文件信息
//        return buildResultEntity(originalfileName, suffix, storage.getConfigKey(), uploadResult);
        return null;
    }

//    private SysOssVo buildResultEntity(String originalfileName, String suffix, String configKey, UploadResult uploadResult) {
//        SysOss oss = new SysOss();
//        oss.setUrl(uploadResult.getUrl());
//        oss.setFileSuffix(suffix);
//        oss.setFileName(uploadResult.getFilename());
//        oss.setOriginalName(originalfileName);
//        oss.setService(configKey);
//        baseMapper.insert(oss, true);
//        SysOssVo sysOssVo = MapstructUtils.convert(oss, SysOssVo.class);
//        return this.matchingUrl(sysOssVo);
//    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            // 做一些业务上的校验,判断是否需要校验
        }
        List<SysOss> list = baseMapper.selectListByIds(ids);
        for (SysOss sysOss : list) {
            // OssClient storage = OssFactory.instance(sysOss.getService());
            // storage.delete(sysOss.getUrl());
        }
        return baseMapper.deleteBatchByIds(ids) > 0;
    }

    /**
     * 匹配Url
     *
     * @param oss OSS对象
     * @return oss 匹配Url的OSS对象
     */
    private SysOssVo matchingUrl(SysOssVo oss) {
//        OssClient storage = OssFactory.instance(oss.getService());
//        // 仅修改桶类型为 private 的URL，临时URL时长为120s
//        if (AccessPolicyType.PRIVATE == storage.getAccessPolicy()) {
//            oss.setUrl(storage.getPrivateUrl(oss.getFileName(), 120));
//        }
        return oss;
    }
}
