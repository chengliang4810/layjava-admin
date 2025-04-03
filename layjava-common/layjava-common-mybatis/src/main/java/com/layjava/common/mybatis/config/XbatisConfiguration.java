package com.layjava.common.mybatis.config;

import cn.xbatis.core.XbatisConfig;
import com.layjava.common.core.exception.ServiceException;
import com.layjava.common.mybatis.core.mapper.BaseMapper;
import com.layjava.common.satoken.utils.LoginHelper;
import org.dromara.hutool.core.text.StrUtil;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;

@Configuration
public class XbatisConfiguration {

    @Bean
    public void init() {
        // 租户ID获取器
        // TenantContext.registerTenantGetter(LoginHelper::getTenantId);

        // 单Mapper
        XbatisConfig.setSingleMapperClass(BaseMapper.class);

        // 当前登录用户的ID
        XbatisConfig.setDefaultValue("{CURRENT_USER_ID}", (entity, type) -> {
            if (type == Long.class) {
                return LoginHelper.getUserId();
            } else if (type == String.class) {
                return StrUtil.toStringOrNull(LoginHelper.getUserId());
            }
            throw new ServiceException("{CURRENT_USER_ID} 不支持的字段类型");
        });

        // 当前登录用户的部门Id
        XbatisConfig.setDefaultValue("{CURRENT_DEPT_ID}", (entity, type) -> {
            if (type == Long.class) {
                return LoginHelper.getDeptId();
            } else if (type == String.class) {
                return StrUtil.toStringOrNull(LoginHelper.getDeptId());
            }
            throw new ServiceException("{CURRENT_DEPT_ID} 不支持的字段类型");
        });
    }

}
