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
public class ServiceException extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "服务器发生异常";

    public ServiceException(){
        super(DEFAULT_MESSAGE);
    }

    public ServiceException(String message, Object... params){
        super(StrUtil.format(message, params));
    }

}
