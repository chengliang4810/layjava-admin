package com.layjava.auth.service;


import com.layjava.auth.domain.vo.LoginVo;
import com.layjava.common.core.exception.ServiceException;
import com.layjava.system.domain.SysClient;
import org.noear.solon.Solon;

/**
 * 授权策略
 *
 * @author Michelle.Chung
 */
public interface IAuthStrategy {

    String BASE_NAME = "AuthStrategy";

    /**
     * 登录
     */
    static LoginVo login(String body, SysClient client, String grantType) {
        // 授权类型和客户端id
        String beanName = grantType + BASE_NAME;
        AuthStrategyService instance = Solon.context().getBean(beanName);
        if (instance == null) {
            throw new ServiceException("授权类型不正确!");
        }
        return instance.login(body, client);
    }

}
