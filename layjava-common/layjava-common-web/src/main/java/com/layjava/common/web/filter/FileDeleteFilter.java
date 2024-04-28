package com.layjava.common.web.filter;

import org.noear.solon.annotation.Component;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Filter;
import org.noear.solon.core.handle.FilterChain;

/**
 * 自动删除临时文件
 *
 * @author chengliang
 * @since 2024/04/28
 */
@Component
public class FileDeleteFilter implements Filter {

    @Override
    public void doFilter(Context ctx, FilterChain chain) throws Throwable {
        chain.doFilter(ctx);

        if (ctx.isMultipartFormData()) {
            ctx.filesDelete();
        }
    }

}
