package com.layjava.common.web.core;

import cn.hutool.core.util.StrUtil;
import com.layjava.common.core.exception.ServiceException;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.ModelAndView;
import org.noear.solon.core.handle.Render;
import org.noear.solon.core.handle.Result;
import org.noear.solon.validation.annotation.Valid;

/**
 * Base控制器
 * 统一处理响应格式
 * 提供通用方法
 * 如果想自定义相应格式参考下方render内容进行调整与替换
 * @author chengliang
 * @date 2024/06/23
 */
@Valid
public class BaseController implements Render {

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

    /**
     * 通过BaseController 统一处理响应格式
     * @param obj 数据
     * @param ctx  上下文
     * @throws Throwable
     */
    @Override
    public void render(Object obj, Context ctx) throws Throwable {

        if (obj == null) {
            ctx.render(Result.succeed());
            return;
        }

        if (obj instanceof String) {
            //普通字符串，封装result 返回
            ctx.render(Result.succeed(obj));
        } else if (obj instanceof ModelAndView) {
            //视图模型，直接渲染
            ctx.render(obj);
        } else {
            //此处是重点，把一些特别的类型进行标准化转换
            if (obj instanceof Throwable err) {
                if (obj instanceof ServiceException exception){
                    obj = Result.failure(exception.getCode(), exception.getMessage());
                } else {
                    // 非手动校验或抛出的ServiceException
                    obj = Result.failure(StrUtil.format("服务端异常, 请联系管理员, 异常信息: [{}]",  err.getMessage()));
                }
            } else if(!(obj instanceof Result)){
                //非Result结构构建为Result
                obj = Result.succeed(obj);
            }

            ctx.render(obj);
            //或者调用特定接口直接输出：ctx.outputAsJson(JSON.toJson(obj));
        }
    }
}
