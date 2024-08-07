package com.layjava.common.core.exception.file;


import com.layjava.common.core.exception.base.BaseException;

import java.io.Serial;

/**
 * 文件信息异常类
 *
 * @author ruoyi
 */
public class FileException extends BaseException {

    @Serial
    private static final long serialVersionUID = 1L;

    public FileException(String message, Object[] args) {
        super("file", 500, message, args);
    }

}
