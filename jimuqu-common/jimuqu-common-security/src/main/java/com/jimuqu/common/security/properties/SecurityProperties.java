package com.jimuqu.common.security.properties;

import lombok.Data;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;

/**
 * Security 配置属性
 *
 * @author chengliang
 * @since 2024/03/27
 */
@Data
@Configuration
@Inject(value = "${security}", required = false)
public class SecurityProperties {

    /**
     * 排除路径
     */
    private String[] excludes;


}
