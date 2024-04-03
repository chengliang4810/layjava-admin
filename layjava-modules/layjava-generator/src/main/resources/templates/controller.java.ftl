package ${package.Controller};

import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Controller;

<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

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

}