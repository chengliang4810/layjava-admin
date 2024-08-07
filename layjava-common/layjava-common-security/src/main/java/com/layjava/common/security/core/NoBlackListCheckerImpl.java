package com.layjava.common.security.core;

import org.noear.solon.annotation.Component;
import org.noear.solon.core.handle.Context;
import org.noear.solon.validation.annotation.NotBlacklist;
import org.noear.solon.validation.annotation.NotBlacklistChecker;

/**
 * 非黑名单检查
 *
 * @author chengliang
 * @date 2024/08/07
 */
@Component
public class NoBlackListCheckerImpl implements NotBlacklistChecker {

    @Override
    public boolean check(NotBlacklist anno, Context ctx) {
        return true;
    }

}
