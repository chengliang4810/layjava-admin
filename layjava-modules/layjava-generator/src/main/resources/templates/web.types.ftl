/**
* ${table.comment!} 模块
*/
namespace ${package.ModuleName?cap_first } {
  /** ${table.comment!}Vo */
  interface ${entity}Vo {
<#list table.fields as field>
    <#if field.comment!?length gt 0>
    /** ${field.comment} */
    </#if>
<#-- 主键 -->
<#if field.propertyName?index_of("id") != -1 || field.propertyName?index_of("Id") != -1 >
    ${field.propertyName}?: string
<#else>
    <#if field.propertyType == "Boolean">
    ${field.propertyName}?: boolean
    <#elseif field.propertyType == "Long" || field.propertyType == "Integer" ||field.propertyType == "Double" ||field.propertyType == "Float" || field.propertyType == "BigDecimal" >
    ${field.propertyName}?: number
    <#else>
    ${field.propertyName}?: string
    </#if>
</#if>
</#list>
  }
  /** ${table.comment!}Query */
  interface ${entity}Query  extends PageQuery{
    <#list table.fields as field>
        <#if field.comment!?length gt 0>
    /** ${field.comment} */
        </#if>
    <#-- 主键 -->
<#if field.propertyName?index_of("id") != -1 || field.propertyName?index_of("Id") != -1 >
    ${field.propertyName}?: string
<#else>
        <#if field.propertyType == "Boolean">
    ${field.propertyName}?: boolean
        <#elseif field.propertyType == "Long" || field.propertyType == "Integer" ||field.propertyType == "Double" ||field.propertyType == "Float" || field.propertyType == "BigDecimal" >
    ${field.propertyName}?: number
        <#else>
    ${field.propertyName}?: string
        </#if>
</#if>
    </#list>
    /**
    * 拓展参数
    */
    params?: any;
  }
  /** ${table.comment!}Form */
  interface ${entity}Form  extends BaseEntity{
    <#list table.fields as field>
        <#if field.comment!?length gt 0>
    /** ${field.comment} */
        </#if>
    <#-- 主键 -->
<#if field.propertyName?index_of("id") != -1 || field.propertyName?index_of("Id") != -1 >
    ${field.propertyName}?: string
<#else>
        <#if field.propertyType == "Boolean">
    ${field.propertyName}?: boolean
        <#elseif field.propertyType == "Long" || field.propertyType == "Integer" ||field.propertyType == "Double" ||field.propertyType == "Float" || field.propertyType == "BigDecimal" >
    ${field.propertyName}?: number
        <#else>
    ${field.propertyName}?: string
        </#if>
</#if>
    </#list>
  }
}
