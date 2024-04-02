package com.layjava.common.web.core;

import org.noear.solon.core.handle.Result;
import org.noear.solon.validation.annotation.Valid;

@Valid
public class BaseController {

    /**
     * 根据影响行数返回结果
     *
     * @param rows 影响行数
     * @return {@link Result}<{@link Void}>
     */
    protected Result<Void> render(int rows) {
        return this.render(rows > 0);
    }

    /**
     * 根据结果返回结果
     *
     * @param result 结果
     * @return {@link Result}<{@link Void}>
     */
    protected Result<Void> render(boolean result) {
        return result ? Result.succeed() : Result.failure();
    }

}
