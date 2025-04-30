package com.jimuqu.auth.domain.bo;

import lombok.Data;
import org.noear.solon.validation.annotation.NotBlank;

import java.io.Serial;
import java.io.Serializable;

/**
 * 登录主体
 *
 * @author chengliang
 * @sice 2024/04/24
 */
@Data
public class LoginBody implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 客户端id
     */
    @NotBlank(message = "认证客户端id不能为空")
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