package com.layjava.common.web.config.properties;

import com.layjava.common.web.enums.CaptchaCategory;
import com.layjava.common.web.enums.CaptchaType;
import lombok.Data;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;

/**
 * 验证码 配置属性
 *
 * @author Lion Li
 */
@Data
@Configuration
@Inject(value = "${captcha}", required = false)
public class CaptchaProperties {

    private Boolean enable;

    /**
     * 验证码类型
     */
    private CaptchaType type;

    /**
     * 验证码类别
     */
    private CaptchaCategory category;

    /**
     * 数字验证码位数
     */
    private Integer numberLength;

    /**
     * 字符验证码长度
     */
    private Integer charLength;
}
