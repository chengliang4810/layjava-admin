package com.layjava.system.service.impl;

import com.layjava.system.service.ISysPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.Component;

import java.util.Set;

@Slf4j
@Component
public class SysPermissionServiceImpl implements ISysPermissionService {

    @Override
    public Set<String> getRolePermission(Long userId) {
        return Set.of();
    }

    @Override
    public Set<String> getMenuPermission(Long userId) {
        return Set.of();
    }
}
