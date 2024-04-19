package com.layjava.generator.test.service.impl;

import com.layjava.generator.test.domain.User;
import com.layjava.generator.test.mapper.UserMapper;
import com.layjava.generator.test.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

/**
 *
 * 用户信息表 服务实现类
 *
 * @author chengliang4810
 * @since 2024-04-19
 */
@Slf4j
@Component
public class UserServiceImpl  implements UserService {

    @Inject
    private UserMapper userMapper;

}