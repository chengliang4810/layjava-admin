package com.jimuqu.system.service.impl;

import cn.xbatis.core.sql.executor.chain.QueryChain;
import com.jimuqu.common.core.utils.MapstructUtil;
import com.jimuqu.common.mybatis.core.Page;
import com.jimuqu.common.mybatis.core.page.PageQuery;
import com.jimuqu.system.domain.SysDictType;
import com.jimuqu.system.domain.bo.SysDictTypeBo;
import com.jimuqu.system.domain.vo.SysDictTypeVo;
import com.jimuqu.system.domain.query.SysDictTypeQuery;
import com.jimuqu.system.mapper.SysDictTypeMapper;
import com.jimuqu.system.service.SysDictTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.Component;

import java.util.Collection;
import java.util.List;


/**
 * 字典类型Service业务层处理
 *
 * @author chengliang4810
 * @since 2025-05-27
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SysDictTypeServiceImpl implements SysDictTypeService {

    private final SysDictTypeMapper sysDictTypeMapper;

    /**
     * 查询字典类型
     */
    @Override
    public SysDictTypeVo queryById(Long id) {
        return sysDictTypeMapper.getVoById(id);
    }

    /**
     * 查询字典类型分页列表
     */
    @Override
    public Page<SysDictTypeVo> queryPageList(SysDictTypeQuery query, PageQuery pageQuery) {
        return buildQueryChain(query)
                .returnType(SysDictTypeVo.class)
                .paging(pageQuery.build());
    }

    /**
     * 查询字典类型列表
     */
    @Override
    public List<SysDictTypeVo> queryList(SysDictTypeQuery query) {
        QueryChain<SysDictType> queryChain = buildQueryChain(query);
        return queryChain.returnType(SysDictTypeVo.class).list();
    }

    /**
     * 构建查询条件
     * @param query 查询对象
     * @return 查询条件对象
     */
    private QueryChain<SysDictType> buildQueryChain(SysDictTypeQuery query) {
        return QueryChain.of(sysDictTypeMapper)
                .forSearch(true)
                .where(query);
    }

    /**
     * 新增字典类型
     */
    @Override
    public Boolean insertByBo(SysDictTypeBo bo) {
        SysDictType sysDictType = MapstructUtil.convert(bo, SysDictType.class);
        boolean flag = sysDictTypeMapper.save(sysDictType) > 0;
        bo.setDictId(sysDictType.getDictId());
        return flag;
    }

    /**
     * 修改字典类型
     */
    @Override
    public Boolean updateByBo(SysDictTypeBo bo) {
        SysDictType sysDictType = MapstructUtil.convert(bo, SysDictType.class);
        return sysDictTypeMapper.update(sysDictType) > 0;
    }

    /**
     * 批量删除字典类型
     */
    @Override
    public Integer deleteByIds(Collection<Long> ids) {
        return sysDictTypeMapper.deleteByIds(ids);
    }
}
