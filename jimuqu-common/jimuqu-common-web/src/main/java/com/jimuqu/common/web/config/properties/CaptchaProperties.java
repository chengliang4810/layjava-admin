package com.jimuqu.common.web.config.properties;

import lombok.Data;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;

/**
 * 验证码 配置属性
 *
 * @author Lion Li,chengliang4810
 */
@Data
@Configuration
@Inject(value = "${captcha}", required = false)
public class CaptchaProperties {

    private Boolean enable;

    /**
     * 数字验证码位数
     */
    private Integer numberLength;

    /**
     * 字符验证码长度
     */
    private Integer charLength;
}
