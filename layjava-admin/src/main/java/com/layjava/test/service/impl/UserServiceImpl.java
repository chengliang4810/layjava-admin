package com.layjava.test.service.impl;

import com.layjava.test.mapper.UserMapper;
import com.layjava.test.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

/**
 *
 * 用户信息表 服务实现类
 *
 * @author chengliang4810
 * @since 2024-04-03
 */
@Slf4j
@Component
public class UserServiceImpl  implements UserService {

    @Inject
    private UserMapper userMapper;

}