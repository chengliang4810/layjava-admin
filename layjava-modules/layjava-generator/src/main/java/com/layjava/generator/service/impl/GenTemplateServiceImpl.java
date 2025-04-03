package com.layjava.generator.service.impl;

import cn.xbatis.core.mybatis.mapper.context.Pager;
import cn.xbatis.core.sql.executor.Where;
import com.layjava.common.core.utils.MapstructUtil;
import com.layjava.common.mybatis.core.page.PageQuery;
import com.layjava.common.mybatis.core.page.PageResult;
import com.layjava.generator.domain.GenTemplate;
import com.layjava.generator.domain.bo.GenTemplateBo;
import com.layjava.generator.domain.vo.GenTemplateVo;
import com.layjava.generator.mapper.GenTemplateMapper;
import com.layjava.generator.service.IGenTemplateService;
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
        Where qw = buildQueryWrapper(bo);
        Pager<GenTemplateVo> result = baseMapper.paging(pageQuery.build(), qw, GenTemplateVo.class);
        return PageResult.build(result);
    }

    /**
     * 查询代码生成模板列表
     */
    @Override
    public List<GenTemplateVo> queryList(GenTemplateBo bo) {
        Where qw = buildQueryWrapper(bo);
        return baseMapper.list(qw, GenTemplateVo.class);
    }

    private Where buildQueryWrapper(GenTemplateBo bo) {
        Map<String, Object> params = bo.getParams();
        return Where.create();
//                .from(GEN_TEMPLATE)
//                .where(GEN_TEMPLATE.CATEGORY.eq(bo.getCategory()))
//                .and(GEN_TEMPLATE.NAME.like(bo.getName()))
//                .and(GEN_TEMPLATE.PATH.eq(bo.getPath()))
//                .and(GEN_TEMPLATE.DB_TYPE.eq(bo.getDbType()))
//                .and(GEN_TEMPLATE.CONTENT.eq(bo.getContent()));
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
