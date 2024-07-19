package com.layjava.common.social.config.properties;

import lombok.Data;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;

import java.util.Map;


/**
 * Social 配置属性
 *
 * @author thiszhc
 */
@Data
@Component
@Configuration
@Inject("${justauth}")
public class SocialProperties {

    /**
     * 是否启用
     */
    private Boolean enabled;

    /**
     * 授权类型
     */
    private Map<String, SocialLoginConfigProperties> type;

}
