package ${package.Entity};

import ${package.Entity}.${entity};
import io.github.linpeilie.annotations.AutoMapper;
import org.noear.solon.validation.annotation.NotNull;
import org.noear.solon.validation.annotation.NotBlank;
import com.layjava.common.core.validate.group.AddGroup;
import com.layjava.common.core.validate.group.UpdateGroup;
<#list table.importPackages as pkg>
import ${pkg};
</#list>
<#if entityLombokModel>
import lombok.Data;
import lombok.EqualsAndHashCode;
    <#if chainModel>
import lombok.experimental.Accessors;
    </#if>
</#if>


/**
 *
 * ${table.comment!}
 *
 * @author ${author}
 * @since ${date}
 */
<#if entityLombokModel>
@Data
    <#if chainModel>
@Accessors(chain = true)
    </#if>
</#if>
<#if superEntityClass??>
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ${entity}.class, reverseConvertGenerate = false)
public class ${entity}Bo extends ${superEntityClass} {
<#else>
@AutoMapper(target = ${entity}.class, reverseConvertGenerate = false)
public class ${entity}Bo {
</#if>

<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>

    <#if field.comment!?length gt 0>
    /**
     * ${field.comment}
     */
    </#if>
    <#-- 普通字段 -->
    <#if field.keyFlag>
    @NotNull(message = "${field.comment}不能为空", groups = { UpdateGroup.class })
    <#else>
        <#if field.propertyType == "String">
    @NotBlank(message = "${field.comment}不能为空", groups = { AddGroup.class, UpdateGroup.class })
        <#else>
    @NotNull(message = "${field.comment}不能为空", groups = { AddGroup.class, UpdateGroup.class })
        </#if>
    </#if>
    private ${field.propertyType} ${field.propertyName};
</#list>
<#------------  END 字段循环遍历  ---------->

}
