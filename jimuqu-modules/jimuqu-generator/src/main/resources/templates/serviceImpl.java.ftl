package ${package.ServiceImpl};

import org.dromara.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import ${package.Entity}.${entity};
import ${package.Entity}.bo.${entity}Bo;
import ${package.Entity}.vo.${entity}Vo;
import ${package.Mapper}.${table.mapperName};
<#if table.serviceInterface>
import ${package.Service}.${table.serviceName};
</#if>
import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.Component;
import com.jimuqu.common.mybatis.core.page.PageQuery;
import com.jimuqu.common.mybatis.core.page.PageResult;
import com.jimuqu.common.core.util.MapstructUtils;

import java.util.List;
import java.util.Map;

/**
 *
 * ${table.comment!} 服务实现类
 *
 * @author ${author}
 * @since ${date}
 */
@Slf4j
@Component
public class ${table.serviceImplName} <#if table.serviceInterface> implements ${table.serviceName}</#if> {

    @Inject
    private ${table.mapperName} ${table.entityPath}Mapper;

    @Override
    public List<${entity}Vo> get${entity}VoList(${entity}Bo ${table.entityPath}Bo) {
        Wrapper<${entity}> ${table.entityPath}Wrapper = buildWrapper(${table.entityPath}Bo);
        return ${table.entityPath}Mapper.selectVoList(${table.entityPath}Wrapper);
    }

    @Override
    public PageResult<${entity}Vo> get${entity}VoList(${entity}Bo ${table.entityPath}Bo, PageQuery pageQuery) {
        Wrapper<${entity}> ${table.entityPath}Wrapper = buildWrapper(${table.entityPath}Bo);

        IPage<${entity}Vo> voPage = ${table.entityPath}Mapper.selectVoPage(pageQuery.build(), ${table.entityPath}Wrapper);
        return PageResult.build(voPage);
    }

    @Override
    public ${entity}Vo get${entity}VoById(Long id) {
        return ${table.entityPath}Mapper.selectVoById(id);
    }

    @Override
    public boolean save${entity}(${entity}Bo ${table.entityPath}Bo) {
        // 参数校验
        Assert.notNull(${table.entityPath}Bo, "${table.comment!}不能为空");

        ${entity} ${table.entityPath} = MapstructUtils.convert(${table.entityPath}Bo, ${entity}.class);
        return ${table.entityPath}Mapper.insert(${table.entityPath}) > 0;
    }

    @Override
    public boolean update${entity}ById(${entity}Bo ${table.entityPath}Bo) {
        // 参数校验
        Assert.notNull(${table.entityPath}Bo, "${table.comment!}不能为空");
            <#list table.fields as field>
                <#if field.keyFlag>
        Assert.notNull(${table.entityPath}Bo.get${field.capitalName}(), "${table.comment!}ID不能为空" );
                </#if>
            </#list>

        ${entity} ${table.entityPath} = MapstructUtils.convert(${table.entityPath}Bo, ${entity}.class);
        return ${table.entityPath}Mapper.updateById(${table.entityPath}) > 0;
    }

    @Override
    public int delete${entity}ById(List<Long> idList) {
        // 参数校验
        Assert.notEmpty(idList, "${table.comment!}ID不能为空");

        return ${table.entityPath}Mapper.deleteBatchIds(idList);
    }

    /**
     * 构建查询条件
     */
    private Wrapper<${entity}> buildWrapper(${entity}Bo ${table.entityPath}Bo) {
        Map<String, Object> params = ${table.entityPath}Bo.getParams();
        LambdaQueryWrapper<${entity}> queryWrapper = Wrappers.lambdaQuery();
        // 条件构造
        <#list table.fields as field>
            <#if field.propertyType == "String">
        queryWrapper.eq(StrUtil.isNotBlank(${table.entityPath}Bo.get${field.capitalName}()), ${entity}::get${field.capitalName}, ${table.entityPath}Bo.get${field.capitalName}());
            <#else>
        queryWrapper.eq(${table.entityPath}Bo.get${field.capitalName}() != null, ${entity}::get${field.capitalName}, ${table.entityPath}Bo.get${field.capitalName}());
            </#if>
        </#list>
        return queryWrapper;
    }

}