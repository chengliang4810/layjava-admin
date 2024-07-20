package com.layjava.common.core.domain.model;

import lombok.Data;
import org.noear.solon.validation.annotation.NotBlank;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户登录对象
 *
 * @author Lion Li
 */

@Data
public class LoginBody implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 客户端id
     */
    @NotBlank(message = "客户端编号不能为空")
    private String clientId;

    /**
     * 授权类型
     */
    @NotBlank(message = "授权类型不能为空")
    private String grantType;

    /**
     * 验证码
     */
    private String code;

    /**
     * 唯一标识
     */
    private String uuid;

}
