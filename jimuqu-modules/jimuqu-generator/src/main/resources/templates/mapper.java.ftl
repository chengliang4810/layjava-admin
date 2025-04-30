package ${package.Mapper};

import ${package.Entity}.${entity};
import ${package.Entity}.vo.${entity}Vo;
import ${superMapperClassPackage};
<#if mapperAnnotationClass??>
import ${mapperAnnotationClass.name};
</#if>

/**
 *
 * ${table.comment!} Mapper 接口
 *
 * @author ${author}
 * @since ${date}
 */
<#if mapperAnnotationClass??>
@${mapperAnnotationClass.simpleName}
</#if>
public interface ${table.mapperName} extends ${superMapperClass}<${entity}, ${entity}Vo> {

}