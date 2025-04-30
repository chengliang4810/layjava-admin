package com.jimuqu.common.web.validation;

import org.noear.solon.annotation.Component;
import org.noear.solon.core.handle.Context;
import org.noear.solon.validation.annotation.NoRepeatSubmit;
import org.noear.solon.validation.annotation.NoRepeatSubmitChecker;

/**
 * 重复提交检查器
 */
@Component
public class LocalNoRepeatSubmitChecker implements NoRepeatSubmitChecker {

    @Override
    public boolean check(NoRepeatSubmit anno, Context ctx, String submitHash, int limitSeconds) {
        return true;
    }

}
