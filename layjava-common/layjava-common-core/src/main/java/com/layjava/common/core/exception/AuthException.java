package com.layjava.common.core.exception;

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
public class AuthException extends ServiceException {

    private Integer code;

    public AuthException(Integer code, String message){
        super(message);
        this.code = code;
    }

    public AuthException(String message){
        super(message);
    }

}
