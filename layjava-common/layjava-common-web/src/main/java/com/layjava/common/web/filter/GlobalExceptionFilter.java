package com.layjava.common.web.filter;

import org.noear.solon.annotation.Component;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Filter;
import org.noear.solon.core.handle.FilterChain;
import org.noear.solon.core.handle.Result;
import org.noear.solon.validation.ValidatorException;

/**
 * 全局异常筛选器
 *
 * @author chengliang
 * @date 2024/02/26
 */
@Component
public class GlobalExceptionFilter implements Filter {

    @Override
    public void doFilter(Context ctx, FilterChain chain) throws Throwable {
        try {
            chain.doFilter(ctx);

            if(!ctx.getHandled()){
                ctx.render(Result.failure(404, "资源不存在"));
            }
        } catch (ValidatorException e){
            // //e.getResult().getDescription()
            ctx.render(Result.failure(e.getCode(), e.getMessage()));
        } catch (Throwable e) {
            ctx.render(Result.failure(500, "服务端运行出错"));
        }
    }

}
