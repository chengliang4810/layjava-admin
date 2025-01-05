package com.layjava.generator.service.impl;

import com.layjava.common.core.utils.MapstructUtil;
import com.layjava.common.mybatis.core.page.PageQuery;
import com.layjava.common.mybatis.core.page.PageResult;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.noear.solon.annotation.Component;
import com.layjava.generator.domain.bo.GenTemplateBo;
import com.layjava.generator.domain.vo.GenTemplateVo;
import com.layjava.generator.domain.GenTemplate;
import com.layjava.generator.mapper.GenTemplateMapper;
import com.layjava.generator.service.IGenTemplateService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

import static com.layjava.generator.domain.table.GenTemplateTableDef.GEN_TEMPLATE;


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
        return baseMapper.selectOneWithRelationsByIdAs(id, GenTemplateVo.class);
    }

    /**
     * 查询代码生成模板列表
     */
    @Override
    public PageResult<GenTemplateVo> queryPageList(GenTemplateBo bo, PageQuery pageQuery) {
        QueryWrapper qw = buildQueryWrapper(bo);
        Page<GenTemplateVo> result = baseMapper.paginateAs(pageQuery, qw, GenTemplateVo.class);
        return PageResult.build(result);
    }

    /**
     * 查询代码生成模板列表
     */
    @Override
    public List<GenTemplateVo> queryList(GenTemplateBo bo) {
        QueryWrapper qw = buildQueryWrapper(bo);
        return baseMapper.selectListByQueryAs(qw, GenTemplateVo.class);
    }

    private QueryWrapper buildQueryWrapper(GenTemplateBo bo) {
        Map<String, Object> params = bo.getParams();
        return QueryWrapper.create()
                .from(GEN_TEMPLATE)
                .where(GEN_TEMPLATE.CATEGORY.eq(bo.getCategory()))
                .and(GEN_TEMPLATE.NAME.like(bo.getName()))
                .and(GEN_TEMPLATE.PATH.eq(bo.getPath()))
                .and(GEN_TEMPLATE.DB_TYPE.eq(bo.getDbType()))
                .and(GEN_TEMPLATE.CONTENT.eq(bo.getContent()));
    }

    /**
     * 新增代码生成模板
     */
    @Override
    public Boolean insertByBo(GenTemplateBo bo) {
        GenTemplate add = MapstructUtil.convert(bo, GenTemplate.class);
        boolean flag = baseMapper.insert(add, true) > 0;
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
        return baseMapper.deleteBatchByIds(ids) > 0;
    }
}
