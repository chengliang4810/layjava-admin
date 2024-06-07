package com.layjava.common.web.filter;

import com.layjava.common.core.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.noear.solon.Solon;
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
 * @since 2024/02/26
 */
@Slf4j
@Component(index = 1)
public class GlobalExceptionFilter implements Filter {

    @Override
    public void doFilter(Context ctx, FilterChain chain) throws Throwable {
        try {
            chain.doFilter(ctx);

            if (!ctx.getHandled()) {
                ctx.render(Result.failure(404, "资源不存在"));
            }
        }
        // 参数验证异常
        catch (ValidatorException e) {
            ctx.render(Result.failure(e.getCode(), e.getMessage()));
        }
        // 权限异常
        catch (AuthException e) {
            ctx.render(Result.failure(e.getCode(), e.getMessage()));
        }
        // 其他异常
        catch (Throwable e) {
            log.error(e.getMessage());
            if ("dev".equals(Solon.cfg().getProperty("solon.env"))) {
                e.printStackTrace();
            }
            ctx.render(Result.failure(500, e.getMessage()));
        }
    }

}
