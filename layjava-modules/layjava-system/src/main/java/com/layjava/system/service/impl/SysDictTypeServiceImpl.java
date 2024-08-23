package com.layjava.system.service.impl;

import cn.dev33.satoken.context.SaHolder;
import org.dromara.hutool.core.collection.CollUtil;
import org.dromara.hutool.core.util.ObjUtil;
import com.layjava.common.core.constant.CacheConstants;
import com.layjava.common.core.exception.ServiceException;
import com.layjava.common.core.service.DictService;
import com.layjava.common.core.utils.MapstructUtil;
import com.layjava.common.core.utils.StreamUtil;
import com.layjava.common.core.utils.StringUtil;
import com.layjava.common.mybatis.core.page.PageQuery;
import com.layjava.common.mybatis.core.page.PageResult;
import com.layjava.system.domain.SysDictData;
import com.layjava.system.domain.SysDictType;
import com.layjava.system.domain.bo.SysDictTypeBo;
import com.layjava.system.domain.vo.SysDictDataVo;
import com.layjava.system.domain.vo.SysDictTypeVo;
import com.layjava.system.mapper.SysDictDataMapper;
import com.layjava.system.mapper.SysDictTypeMapper;
import com.layjava.system.service.ISysDictTypeService;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.update.UpdateChain;
import org.apache.ibatis.solon.annotation.Db;
import org.noear.solon.annotation.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.layjava.system.domain.table.SysDictTypeTableDef.SYS_DICT_TYPE;

/**
 * 字典 业务层处理
 *
 * @author Lion Li
 */

@Component
public class SysDictTypeServiceImpl implements ISysDictTypeService, DictService {

    @Db
    private SysDictTypeMapper baseMapper;
    @Db
    private SysDictDataMapper dictDataMapper;

    @Override
    public PageResult<SysDictTypeVo> selectPageDictTypeList(SysDictTypeBo dictType, PageQuery pageQuery) {
        QueryWrapper lqw = buildQueryWrapper(dictType);
        Page<SysDictTypeVo> page = baseMapper.paginateAs(pageQuery, lqw, SysDictTypeVo.class);
        return PageResult.build(page);
    }

    /**
     * 根据条件分页查询字典类型
     *
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    @Override
    public List<SysDictTypeVo> selectDictTypeList(SysDictTypeBo dictType) {
        QueryWrapper lqw = buildQueryWrapper(dictType);
        return baseMapper.selectListByQueryAs(lqw, SysDictTypeVo.class);
    }

    private QueryWrapper buildQueryWrapper(SysDictTypeBo bo) {
        Map<String, Object> params = bo.getParams();
        return QueryWrapper.create().from(SYS_DICT_TYPE)
                .where(SYS_DICT_TYPE.DICT_NAME.like(bo.getDictName()))
                .and(SYS_DICT_TYPE.DICT_TYPE.like(bo.getDictType()))
                .and(SYS_DICT_TYPE.CREATE_TIME.between(params.get("beginTime" ), params.get("endTime" ), params.get("beginTime" ) != null && params.get("endTime" ) != null))
                .orderBy(SYS_DICT_TYPE.DICT_ID, true);
    }

    /**
     * 根据所有字典类型
     *
     * @return 字典类型集合信息
     */
    @Override
    public List<SysDictTypeVo> selectDictTypeAll() {
        return baseMapper.selectListByQueryAs(new QueryWrapper(), SysDictTypeVo.class);
    }

    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    // @Cacheable(cacheNames = CacheNames.SYS_DICT, key = "#dictType")
    @Override
    public List<SysDictDataVo> selectDictDataByType(String dictType) {
        List<SysDictDataVo> dictDatas = dictDataMapper.selectDictDataByType(dictType);
        if (CollUtil.isNotEmpty(dictDatas)) {
            return dictDatas;
        }
        return null;
    }

    /**
     * 根据字典类型ID查询信息
     *
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    @Override
    public SysDictTypeVo selectDictTypeById(Long dictId) {
        return baseMapper.selectOneWithRelationsByIdAs(dictId, SysDictTypeVo.class);
    }

    /**
     * 根据字典类型查询信息
     *
     * @param dictType 字典类型
     * @return 字典类型
     */
    @Override
    public SysDictTypeVo selectDictTypeByType(String dictType) {
        return baseMapper.selectOneByQueryAs(QueryWrapper.create().from(SYS_DICT_TYPE).where(SYS_DICT_TYPE.DICT_TYPE.eq(dictType)), SysDictTypeVo.class);
    }

    /**
     * 批量删除字典类型信息
     *
     * @param dictIds 需要删除的字典ID
     */
    @Override
    public void deleteDictTypeByIds(Long[] dictIds) {
        for (Long dictId : dictIds) {
            SysDictType dictType = baseMapper.selectOneById(dictId);
            if (dictDataMapper.selectCountByQuery(QueryWrapper.create().from(SYS_DICT_TYPE).where(SYS_DICT_TYPE.DICT_TYPE.eq(dictType.getDictType()))) > 0) {
                throw new ServiceException(String.format("%1$s已分配,不能删除", dictType.getDictName()));
            }
            // CacheUtils.evict(CacheNames.SYS_DICT, dictType.getDictType());
        }
        baseMapper.deleteBatchByIds(Arrays.asList(dictIds));
    }

    /**
     * 重置字典缓存数据
     */
    @Override
    public void resetDictCache() {
        // CacheUtils.clear(CacheNames.SYS_DICT);
    }

    /**
     * 新增保存字典类型信息
     *
     * @param bo 字典类型信息
     * @return 结果
     */
    // @CachePut(cacheNames = CacheNames.SYS_DICT, key = "#bo.dictType")
    @Override
    public List<SysDictDataVo> insertDictType(SysDictTypeBo bo) {
        SysDictType dict = MapstructUtil.convert(bo, SysDictType.class);
        int row = baseMapper.insert(dict, true);
        if (row > 0) {
            // 新增 type 下无 data 数据 返回空防止缓存穿透
            return new ArrayList<>();
        }
        throw new ServiceException("操作失败" );
    }

    /**
     * 修改保存字典类型信息
     *
     * @param bo 字典类型信息
     * @return 结果
     */
    // @CachePut(cacheNames = CacheNames.SYS_DICT, key = "#bo.dictType")
    @Override
    //@Transactional(rollbackFor = Exception.class)
    public List<SysDictDataVo> updateDictType(SysDictTypeBo bo) {
        SysDictType dict = MapstructUtil.convert(bo, SysDictType.class);
        SysDictType oldDict = baseMapper.selectOneById(dict.getDictId());
        UpdateChain.of(SysDictData.class)
                .set(SysDictData::getDictType, dict.getDictType())
                .where(SysDictData::getDictType).eq(oldDict.getDictType())
                .update();
        int row = baseMapper.update(dict);
        if (row > 0) {
            // CacheUtils.evict(CacheNames.SYS_DICT, oldDict.getDictType());
            return dictDataMapper.selectDictDataByType(dict.getDictType());
        }
        throw new ServiceException("操作失败" );
    }

    /**
     * 校验字典类型称是否唯一
     *
     * @param dictType 字典类型
     * @return 结果
     */
    @Override
    public boolean checkDictTypeUnique(SysDictTypeBo dictType) {
        boolean exist = baseMapper.selectCountByQuery(
                QueryWrapper.create().from(SYS_DICT_TYPE).where(SYS_DICT_TYPE.DICT_TYPE.eq(dictType.getDictType()))
                        .and(SYS_DICT_TYPE.DICT_ID.ne(dictType.getDictId()))) > 0;
        return !exist;
    }

    /**
     * 根据字典类型和字典值获取字典标签
     *
     * @param dictType  字典类型
     * @param dictValue 字典值
     * @param separator 分隔符
     * @return 字典标签
     */
    @SuppressWarnings("unchecked cast" )
    @Override
    public String getDictLabel(String dictType, String dictValue, String separator) {
        // 优先从本地缓存获取
        List<SysDictDataVo> datas = (List<SysDictDataVo>) SaHolder.getStorage().get(CacheConstants.SYS_DICT_KEY + dictType);
        if (ObjUtil.isNull(datas)) {
            datas = this.selectDictDataByType(dictType);
            SaHolder.getStorage().set(CacheConstants.SYS_DICT_KEY + dictType, datas);
        }

        Map<String, String> map = StreamUtil.toMap(datas, SysDictDataVo::getDictValue, SysDictDataVo::getDictLabel);
        if (StringUtil.containsAny(dictValue, separator)) {
            return Arrays.stream(dictValue.split(separator))
                    .map(v -> map.getOrDefault(v, StringUtil.EMPTY))
                    .collect(Collectors.joining(separator));
        } else {
            return map.getOrDefault(dictValue, StringUtil.EMPTY);
        }
    }

    /**
     * 根据字典类型和字典标签获取字典值
     *
     * @param dictType  字典类型
     * @param dictLabel 字典标签
     * @param separator 分隔符
     * @return 字典值
     */
    @SuppressWarnings("unchecked cast" )
    @Override
    public String getDictValue(String dictType, String dictLabel, String separator) {
        // 优先从本地缓存获取
        List<SysDictDataVo> datas = (List<SysDictDataVo>) SaHolder.getStorage().get(CacheConstants.SYS_DICT_KEY + dictType);
        if (ObjUtil.isNull(datas)) {
            datas = this.selectDictDataByType(dictType);
            SaHolder.getStorage().set(CacheConstants.SYS_DICT_KEY + dictType, datas);
        }

        Map<String, String> map = StreamUtil.toMap(datas, SysDictDataVo::getDictLabel, SysDictDataVo::getDictValue);
        if (StringUtil.containsAny(dictLabel, separator)) {
            return Arrays.stream(dictLabel.split(separator))
                    .map(l -> map.getOrDefault(l, StringUtil.EMPTY))
                    .collect(Collectors.joining(separator));
        } else {
            return map.getOrDefault(dictLabel, StringUtil.EMPTY);
        }
    }

    @Override
    public Map<String, String> getAllDictByDictType(String dictType) {
        List<SysDictDataVo> list = selectDictDataByType(dictType);
        return StreamUtil.toMap(list, SysDictDataVo::getDictValue, SysDictDataVo::getDictLabel);
    }

}
