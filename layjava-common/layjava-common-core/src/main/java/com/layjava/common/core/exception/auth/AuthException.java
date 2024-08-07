package com.layjava.common.core.exception.auth;

import com.layjava.common.core.exception.base.BaseException;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 身份验证异常
 *
 * @author chengliang
 * @since 2024/03/28
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AuthException extends BaseException {

    private Integer code;

    public AuthException(Integer code, String message) {
        super("auth", code, message);
        this.code = code;
    }

    public AuthException(String message) {
        super("auth", 403, message);
    }

}
