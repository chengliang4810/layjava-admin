package com.layjava.system.service.impl;

import com.layjava.common.core.exception.ServiceException;
import com.layjava.common.core.utils.MapstructUtil;
import com.layjava.common.mybatis.core.page.PageQuery;
import com.layjava.common.mybatis.core.page.PageResult;
import com.layjava.system.domain.SysDictData;
import com.layjava.system.domain.bo.SysDictDataBo;
import com.layjava.system.domain.vo.SysDictDataVo;
import com.layjava.system.mapper.SysDictDataMapper;
import com.layjava.system.service.ISysDictDataService;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

import java.util.List;

import static com.layjava.system.domain.table.SysDictDataTableDef.SYS_DICT_DATA;

/**
 * 字典 业务层处理
 *
 * @author Lion Li
 */

@Component
public class SysDictDataServiceImpl implements ISysDictDataService {

    @Inject
    private SysDictDataMapper baseMapper;

    @Override
    public PageResult<SysDictDataVo> selectPageDictDataList(SysDictDataBo dictData, PageQuery pageQuery) {
        QueryWrapper lqw = buildQueryWrapper(dictData);
        Page<SysDictDataVo> page = baseMapper.paginateAs(pageQuery, lqw, SysDictDataVo.class);
        return PageResult.build(page);
    }

    /**
     * 根据条件分页查询字典数据
     *
     * @param dictData 字典数据信息
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDictDataVo> selectDictDataList(SysDictDataBo dictData) {
        QueryWrapper lqw = buildQueryWrapper(dictData);
        return baseMapper.selectListByQueryAs(lqw, SysDictDataVo.class);
    }

    private QueryWrapper buildQueryWrapper(SysDictDataBo bo) {
        return QueryWrapper.create()
                .from(SYS_DICT_DATA)
                .where(SYS_DICT_DATA.DICT_SORT.eq(bo.getDictSort()))
                .and(SYS_DICT_DATA.DICT_LABEL.like(bo.getDictLabel()))
                .and(SYS_DICT_DATA.DICT_TYPE.eq(bo.getDictType()))
                .orderBy(SYS_DICT_DATA.DICT_SORT, true);
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType  字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    @Override
    public String selectDictLabel(String dictType, String dictValue) {
        return baseMapper.selectOneByQuery(QueryWrapper.create()
                        .select(SYS_DICT_DATA.DICT_LABEL)
                        .from(SYS_DICT_DATA)
                        .where(SYS_DICT_DATA.DICT_TYPE.eq(dictType))
                        .and(SYS_DICT_DATA.DICT_VALUE.eq(dictValue)))
                .getDictLabel();
    }

    /**
     * 根据字典数据ID查询信息
     *
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    @Override
    public SysDictDataVo selectDictDataById(Long dictCode) {
        return baseMapper.selectOneWithRelationsByIdAs(dictCode, SysDictDataVo.class);
    }

    /**
     * 批量删除字典数据信息
     *
     * @param dictCodes 需要删除的字典数据ID
     */
    @Override
    public void deleteDictDataByIds(Long[] dictCodes) {
        for (Long dictCode : dictCodes) {
            SysDictData data = baseMapper.selectOneById(dictCode);
            baseMapper.deleteById(dictCode);
            // CacheUtils.evict(CacheNames.SYS_DICT, data.getDictType());
        }
    }

    /**
     * 新增保存字典数据信息
     *
     * @param bo 字典数据信息
     * @return 结果
     */
    // @CachePut(cacheNames = CacheNames.SYS_DICT, key = "#bo.dictType")
    @Override
    public List<SysDictDataVo> insertDictData(SysDictDataBo bo) {
        SysDictData data = MapstructUtil.convert(bo, SysDictData.class);
        int row = baseMapper.insert(data, true);
        if (row > 0) {
            return baseMapper.selectDictDataByType(data.getDictType());
        }
        throw new ServiceException("操作失败" );
    }

    /**
     * 修改保存字典数据信息
     *
     * @param bo 字典数据信息
     * @return 结果
     */
    // @CachePut(cacheNames = CacheNames.SYS_DICT, key = "#bo.dictType")
    @Override
    public List<SysDictDataVo> updateDictData(SysDictDataBo bo) {
        SysDictData data = MapstructUtil.convert(bo, SysDictData.class);
        int row = baseMapper.update(data);
        if (row > 0) {
            return baseMapper.selectDictDataByType(data.getDictType());
        }
        throw new ServiceException("操作失败" );
    }

}
