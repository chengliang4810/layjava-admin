package com.layjava.common.mybatis.listener;

import com.layjava.common.core.domain.model.LoginUser;
import com.layjava.common.mybatis.core.entity.BaseEntity;
import com.layjava.common.satoken.utils.LoginHelper;
import com.mybatisflex.annotation.AbstractUpdateListener;

import java.time.LocalDateTime;

/**
 * 更新数据自动填充
 *
 * @author chengliang
 * @date 2024/06/21
 */
public class BaseEntityUpdateListener extends AbstractUpdateListener<BaseEntity> {

    @Override
    public void doUpdate(BaseEntity entity) {
        // 修改时间
        entity.setUpdateTime(LocalDateTime.now());
        // 获取当前登录用户
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser != null) {
            // 创建人，用户录入优先
            Long updateBy = entity.getUpdateBy() != null ? entity.getUpdateBy() : loginUser.getUserId();
            entity.setUpdateBy(updateBy);
        }
    }

}
