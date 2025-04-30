package com.jimuqu.auth.service;

import com.jimuqu.auth.domain.vo.LoginVo;
import com.jimuqu.system.domain.SysClient;

/**
 * 授权策略统一接口
 */
public interface AuthStrategyService {

    /**
     * 登录
     */
    LoginVo login(String body, SysClient client);

}
