package com.layjava.common.core.exception;

/**
 * 参数异常
 *
 * @author chengliang
 * @since  2024/04/16
 */
public class ParameterException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "参数错误";

    public ParameterException(){
        super(DEFAULT_MESSAGE);
    }

    public ParameterException(String message){
        super(message);
    }

}
