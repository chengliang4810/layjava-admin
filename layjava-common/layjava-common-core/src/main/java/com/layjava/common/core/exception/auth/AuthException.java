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

    private String code;

    // TODO 整合完成后重新处理异常类
    public AuthException(Integer code, String message) {
        super(message);
        this.code = code + "";
    }

    public AuthException(String message) {
        super(message);
    }

}
