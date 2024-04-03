package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
<#if table.serviceInterface>
import ${package.Service}.${table.serviceName};
</#if>
import ${superServiceImplClassPackage};
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 * extends ${superServiceImplClass}<${table.mapperName}, ${entity}>
 * @author ${author}
 * @since ${date}
 */
@Slf4j
@Component
public class ${table.serviceImplName} <#if table.serviceInterface> implements ${table.serviceName}</#if> {

   @Inject
}