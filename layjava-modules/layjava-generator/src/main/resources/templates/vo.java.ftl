package ${package.Entity};

import ${package.Entity}.${entity};
import java.io.Serializable;
<#list table.importPackages as pkg>
import ${pkg};
</#list>
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * ${table.comment!}
 *
 * @author ${author}
 * @since ${date}
 */
@Data
@Accessors(chain = true)
@AutoMapper(target = ${entity}.class, convertGenerate = false)
public class ${entity}Vo implements Serializable {

<#if entitySerialVersionUID>
    private static final long serialVersionUID = 1L;
</#if>
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if field.keyFlag>
        <#assign keyPropertyName="${field.propertyName}"/>
    </#if>
    <#if field.comment!?length gt 0>
    /**
     * ${field.comment}
     */
    </#if>
    private ${field.propertyType} ${field.propertyName};

</#list>
<#------------  END 字段循环遍历  ---------->
}
