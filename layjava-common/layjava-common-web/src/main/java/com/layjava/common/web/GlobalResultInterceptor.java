package com.layjava.common.web;

import org.noear.solon.annotation.Component;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;
import org.noear.solon.core.handle.Result;
import org.noear.solon.core.route.RouterInterceptor;
import org.noear.solon.core.route.RouterInterceptorChain;

/**
 * 全局结果拦截器
 * 统一处理返回结果
 * @author chengliang
 * @date 2024/02/26
 */
@Component
public class GlobalResultInterceptor implements RouterInterceptor {

    @Override
    public void doIntercept(Context ctx, Handler mainHandler, RouterInterceptorChain chain) throws Throwable {
        chain.doIntercept(ctx, mainHandler);
    }

    /**
     * 提交结果
     */
    @Override
    public Object postResult(Context ctx, Object result) throws Throwable {
        if(result instanceof Throwable){
            // 如果是异常，包装成Result返回
            return Result.failure(((Throwable) result).getMessage());
        } else if (result instanceof Result) {
            // 如果是Result类型，直接返回
            return result;
        }else{
            // 其他类型，包装成Result返回
            return Result.succeed(result);
        }
    }


}