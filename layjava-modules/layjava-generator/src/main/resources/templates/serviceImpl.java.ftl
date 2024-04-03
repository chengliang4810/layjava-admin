package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
<#if table.serviceInterface>
import ${package.Service}.${table.serviceName};
</#if>
import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

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

}