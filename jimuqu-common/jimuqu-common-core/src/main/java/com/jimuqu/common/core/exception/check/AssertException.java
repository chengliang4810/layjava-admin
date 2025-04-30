package com.jimuqu.common.core.exception.check;

import com.jimuqu.common.core.exception.base.BaseException;

/**
 * 断言异常
 *
 * @author chengliang
 * @date 2024/08/23
 */
public class AssertException extends BaseException {

    private Integer code;

    public AssertException(Integer code, String message) {
        super("assert", code, message);
        this.code = code;
    }

    public AssertException(String message) {
        super("assert", 500, message);
    }


}
