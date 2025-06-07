package com.jimuqu.common.web.interceptor;

import com.jimuqu.common.core.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.collection.CollUtil;
import org.dromara.hutool.core.text.StrUtil;
import org.noear.solon.Solon;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Http;
import org.noear.solon.boot.web.uploadfile.HttpHeader;
import org.noear.solon.core.handle.Action;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;
import org.noear.solon.core.route.RouterInterceptor;
import org.noear.solon.core.route.RouterInterceptorChain;
import org.noear.solon.core.util.KeyValues;
import org.noear.solon.core.util.MultiMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static org.noear.solon.core.util.MimeType.APPLICATION_JSON_VALUE;

@Slf4j
@Component(index = -99)
public class TimeConsumingInterceptor implements RouterInterceptor {

    /**
     * 执行拦截
     *
     * @param ctx
     * @param mainHandler
     * @param chain
     */
    @Override
    public void doIntercept(Context ctx, Handler mainHandler, RouterInterceptorChain chain) throws Throwable {

        Action action = ctx.action();
        if (action == null){
            chain.doIntercept(ctx, mainHandler);
            return;
        }


        log.info("开始请求[{}] ,请求方式:[{}], 参数: [{}], Body: [{}]", action.fullName(), ctx.method(), ctx.paramMap().toValuesMap(), ctx.body());

        // 开始计时
        long startTime = System.currentTimeMillis();
        try {
            chain.doIntercept(ctx, mainHandler);
        }finally {
            // 结束计时
            long endTime = System.currentTimeMillis();
            // 计算执行时间
            long executionTime = endTime - startTime;
            // 输出执行时间
            log.info("结束请求[{}] ,耗时:[{}ms]", action.fullName(), executionTime);
        }
    }

}
