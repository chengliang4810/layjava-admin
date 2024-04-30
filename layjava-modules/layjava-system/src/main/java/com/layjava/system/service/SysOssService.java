package com.layjava.system.service;

import com.layjava.system.domain.bo.SysOssBo;
import com.layjava.system.domain.vo.SysOssVo;
import com.layjava.common.mybatis.core.page.PageQuery;
import com.layjava.common.mybatis.core.page.PageResult;

import java.util.List;

/**
 *
 * OSS对象存储表 实现类
 *
 * @author chengliang4810
 * @since 2024-04-30
 */
public interface SysOssService {

    /**
     * 查询OSS对象存储表列表
     *
     * @param sysOssBo OSS对象存储表Bo
     * @return OSS对象存储表列表
     */
    List<SysOssVo> getSysOssVoList(SysOssBo sysOssBo);

    /**
     * 获取OSS对象存储表分页列表
     *
     * @param sysOssBo    OSS对象存储表Bo
     * @param pageQuery 分页查询条件
     * @return {@link List}<{@link SysOssVo}>
     */
    PageResult<SysOssVo> getSysOssVoList(SysOssBo sysOssBo, PageQuery pageQuery);

    /**
     * 通过id查询OSS对象存储表Vo
     *
     * @param id OSS对象存储表id
     * @return {@link SysOssVo} OSS对象存储表
     */
    SysOssVo getSysOssVoById(Long id);

    /**
     * 保存OSS对象存储表
     *
     * @param sysOssBo OSS对象存储表
     * @return {@link boolean} 是否新增成功
     */
    boolean saveSysOss(SysOssBo sysOssBo);

    /**
     * 根据id更新OSS对象存储表
     *
     * @param sysOssBo OSS对象存储表
     * @return {@link boolean} 是否更新成功
     */
    boolean updateSysOssById(SysOssBo sysOssBo);

    /**
     * 根据id删除OSS对象存储表
     *
     * @param idList {table.comment!}id列表
     * @return {@link boolean} 是否删除成功
     */
    int deleteSysOssById(List<Long> idList);

}