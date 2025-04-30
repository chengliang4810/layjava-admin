package com.jimuqu.generator.service;

import com.jimuqu.common.mybatis.core.page.PageQuery;
import com.jimuqu.common.mybatis.core.page.PageResult;
import com.jimuqu.generator.domain.bo.GenTemplateBo;
import com.jimuqu.generator.domain.vo.GenTemplateVo;

import java.util.Collection;
import java.util.List;

/**
 * 代码生成模板Service接口
 *
 * @author chengliang4810
 * @date 2025-01-05
 */
public interface IGenTemplateService {

    /**
     * 查询代码生成模板
     */
    GenTemplateVo queryById(Long id);

    /**
     * 查询代码生成模板列表
     */
    PageResult<GenTemplateVo> queryPageList(GenTemplateBo bo, PageQuery pageQuery);

    /**
     * 查询代码生成模板列表
     */
    List<GenTemplateVo> queryList(GenTemplateBo bo);

    /**
     * 新增代码生成模板
     */
    Boolean insertByBo(GenTemplateBo bo);

    /**
     * 修改代码生成模板
     */
    Boolean updateByBo(GenTemplateBo bo);

    /**
     * 校验并批量删除代码生成模板信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
