package com.layjava.common.dao.listener;

import com.layjava.common.core.domain.model.LoginUser;
import com.layjava.common.dao.core.entity.BaseEntity;
import com.layjava.common.satoken.utils.LoginHelper;
import com.mybatisflex.annotation.AbstractInsertListener;

import java.time.LocalDateTime;

/**
 * 新增数据自动填充
 *
 * @author chengliang
 * @date 2024/06/21
 */
public class BaseEntityInsertListener extends AbstractInsertListener<BaseEntity> {

    @Override
    public void doInsert(BaseEntity entity) {
        LocalDateTime now = LocalDateTime.now();
        // 获取当前登录用户
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser != null) {
            // 创建人，用户录入优先
            Long createBy = entity.getCreateBy() != null ? entity.getCreateBy() : loginUser.getUserId();
            entity.setCreateBy(createBy);
            entity.setUpdateBy(createBy);

            // 创建部门
            entity.setCreateDept(entity.getCreateDept() != null ? entity.getCreateDept() : loginUser.getDeptId());
        }

        // 时间
        entity.setCreateTime(now);
        entity.setUpdateTime(now);
    }

}
