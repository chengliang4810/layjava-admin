package ${package.Controller};

import org.dromara.hutool.core.lang.Assert;
import org.dromara.hutool.core.convert.Convert;
import cn.zhxu.bs.BeanSearcher;
import cn.zhxu.bs.SearchResult;
import com.jimuqu.common.core.validate.group.AddGroup;
import com.jimuqu.common.core.validate.group.UpdateGroup;
import ${package.Entity}.${entity};
import ${package.Entity}.bo.${entity}Bo;
import ${package.Entity}.vo.${entity}Vo;
import ${package.Service}.${table.serviceName};
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Get;
import org.noear.solon.annotation.Post;
import org.noear.solon.annotation.Put;
import org.noear.solon.annotation.Delete;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Get;
import org.noear.solon.annotation.Post;
import org.noear.solon.annotation.Put;
import org.noear.solon.annotation.Delete;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.*;
import org.noear.solon.validation.annotation.NotBlank;
import org.noear.solon.validation.annotation.NotNull;
import org.noear.solon.validation.annotation.Validated;
import com.jimuqu.common.core.util.MapstructUtils;
import com.jimuqu.common.core.util.StringUtils;
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

import java.util.List;

/**
 *
 * ${table.comment!} 控制器
 *
 * @author ${author}
 * @since ${date}
 */
@Controller
@Mapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    @Inject
    private ${table.serviceName} ${table.entityPath}Service;
    @Inject
    private BeanSearcher beanSearcher;

    /**
     * 查询${table.comment!}列表
     *
     * @param ${table.entityPath}Bo ${table.comment!}查询条件
     * @return ${table.comment!}列表数据
     */
    @Get
    @Mapping("/list")
    public List<${entity}Vo> list(${entity}Bo ${table.entityPath}Bo) {
        List<${entity}> searchAll = beanSearcher.searchAll(${entity}.class);
        return MapstructUtils.convert(searchAll, ${entity}Vo.class);
    }

    /**
     * 分页查询${table.comment!}列表
     *
     * @param pageQuery 分页查询条件
     * @return ${table.comment!}分页列表数据
     */
    @Get
    @Mapping("/list/{pageNum}/{pageSize}")
    public PageResult<${entity}Vo> pageList(PageQuery pageQuery) {
        SearchResult<${entity}> search = beanSearcher.search(${entity}.class);
        return PageResult.build(search, ${entity}Vo.class);
    }

    /**
     * 根据ID查询${table.comment!}
     *
     * @param id ${table.comment!}ID
     * @return ${table.comment!}数据
     */
    @Get
    @Mapping("/{id}")
    public ${entity}Vo get(@NotNull Long id) {
        return ${table.entityPath}Service.get${entity}VoById(id);
    }

    /**
     * 新增${table.comment!}
     *
     * @param ${table.entityPath}Bo ${table.comment!}新增对象
     */
    @Post
    @Mapping
    public void save((AddGroup.class) ${entity}Bo ${table.entityPath}Bo) {
        boolean result = ${table.entityPath}Service.save${entity}(${table.entityPath}Bo);
        Assert.isTrue(result, "新增${table.comment!}失败");
    }

    /**
     * 根据ID更新${table.comment!}信息
     *
     * @param ${table.entityPath}Bo ${table.comment!}更新对象
     */
    @Put
    @Mapping
    public void update((UpdateGroup.class) ${entity}Bo ${table.entityPath}Bo) {
        boolean result = ${table.entityPath}Service.update${entity}ById(${table.entityPath}Bo);
        Assert.isTrue(result, "更新${table.comment!}失败");
    }

    /**
     * 根据ID删除${table.comment!}
     *
     * @param ids ${table.comment!}ID
     */
    @Delete
    @Mapping("/{ids}")
    public void delete(@NotBlank(message = "ID不允许为空") String ids) {
        List<Long> idList = StringUtils.splitTo(ids, Convert::toLong);
        int result = ${table.entityPath}Service.delete${entity}ById(idList);
        Assert.isTrue(result > 0, "删除${table.comment!}失败");
    }

}