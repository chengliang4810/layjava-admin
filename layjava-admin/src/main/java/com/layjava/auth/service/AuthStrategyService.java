package com.layjava.auth.service;

import com.layjava.auth.domain.vo.LoginVo;
import com.layjava.system.domain.SysClient;

/**
 * 授权策略统一接口
 */
public interface AuthStrategyService {

    /**
     * 登录
     */
    LoginVo login(String body, SysClient client);

}
