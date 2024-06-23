package com.layjava.common.core.exception;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 服务异常
 *
 * @author chengliang
 * @since  2024/04/24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ServiceException extends RuntimeException {

    /**
     * 错误码
     */
    private Integer code = 500;
    private static final String DEFAULT_MESSAGE = "服务器发生异常";

    public ServiceException(){
        super(DEFAULT_MESSAGE);
    }

    public ServiceException(Integer code){
        super(DEFAULT_MESSAGE);
        this.code = code;
    }

    public ServiceException(String message, Object... params){
        super(StrUtil.format(message, params));
    }

    public ServiceException(Integer code, String message, Object... params){
        super(StrUtil.format(message, params));
        this.code = code;
    }


}
