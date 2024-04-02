package com.layjava.common.core.domain.model;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 登录主体
 *
 * @author chengliang
 * @date 2024/04/02
 */
@Data
public class LoginBody implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 账户
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 客户端id 预留字段
     */
    // @NotBlank(message = "客户端编号不能为空")
    private String clientId;

    /**
     * 授权类型 预留字段
     */
    // @NotBlank(message = "授权类型不能为空")
    private String grantType;

    /**
     * 验证码
     */
    private String verifyCode;

    /**
     * 唯一标识
     */
    private String uuid;

}
