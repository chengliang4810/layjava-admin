package com.jimuqu.generator.service.impl;

import cn.xbatis.core.mybatis.mapper.context.Pager;
import cn.xbatis.core.sql.executor.chain.QueryChain;
import com.jimuqu.common.core.utils.MapstructUtil;
import com.jimuqu.common.mybatis.core.page.PageQuery;
import com.jimuqu.common.mybatis.core.page.PageResult;
import com.jimuqu.generator.domain.GenTemplate;
import com.jimuqu.generator.domain.bo.GenTemplateBo;
import com.jimuqu.generator.domain.vo.GenTemplateVo;
import com.jimuqu.generator.mapper.GenTemplateMapper;
import com.jimuqu.generator.service.IGenTemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * 代码生成模板Service业务层处理
 *
 * @author chengliang4810
 * @date 2025-01-05
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class GenTemplateServiceImpl implements IGenTemplateService {

    private final GenTemplateMapper baseMapper;

    /**
     * 查询代码生成模板
     */
    @Override
    public GenTemplateVo queryById(Long id) {
        return baseMapper.getVoById(id);
    }

    /**
     * 查询代码生成模板列表
     */
    @Override
    public PageResult<GenTemplateVo> queryPageList(GenTemplateBo bo, PageQuery pageQuery) {
        Pager<GenTemplateVo> paging = buildQueryChain(bo).returnType(GenTemplateVo.class).paging(pageQuery.build());
        return PageResult.build(paging);
    }

    /**
     * 查询代码生成模板列表
     */
    @Override
    public List<GenTemplateVo> queryList(GenTemplateBo bo) {
        QueryChain<GenTemplate> queryChain = buildQueryChain(bo);
        return queryChain.returnType(GenTemplateVo.class).list();
    }

    private QueryChain<GenTemplate> buildQueryChain(GenTemplateBo bo) {
        Map<String, Object> params = bo.getParams();
        return QueryChain.of(baseMapper)
                .forSearch(true)
                .like(GenTemplate::getName, bo.getName())
                .eq(GenTemplate::getCategory, bo.getCategory())
                .eq(GenTemplate::getEnable, bo.getEnable());
    }

    /**
     * 新增代码生成模板
     */
    @Override
    public Boolean insertByBo(GenTemplateBo bo) {
        GenTemplate add = MapstructUtil.convert(bo, GenTemplate.class);
        boolean flag = baseMapper.save(add, true) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改代码生成模板
     */
    @Override
    public Boolean updateByBo(GenTemplateBo bo) {
        GenTemplate update = MapstructUtil.convert(bo, GenTemplate.class);
        return baseMapper.update(update) > 0;
    }

    /**
     * 批量删除代码生成模板
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            /// 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }
}
