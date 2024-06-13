package com.layjava.common.dao.interceptor;

import com.easy.query.core.basic.extension.interceptor.EntityInterceptor;
import com.easy.query.core.basic.extension.interceptor.UpdateSetInterceptor;
import com.easy.query.core.expression.parser.core.base.ColumnSetter;
import com.easy.query.core.expression.sql.builder.EntityInsertExpressionBuilder;
import com.easy.query.core.expression.sql.builder.EntityUpdateExpressionBuilder;
import com.layjava.common.core.domain.model.LoginUser;
import com.layjava.common.dao.core.entity.BaseEntity;
import com.layjava.common.security.utils.LoginHelper;
import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class InjectionBaseFieldInterceptor implements EntityInterceptor, UpdateSetInterceptor {


    @Override
    public void configureInsert(Class<?> entityClass, EntityInsertExpressionBuilder entityInsertExpressionBuilder, Object entity) {
        BaseEntity baseEntity = (BaseEntity) entity;

        // 设置创建时间
        LocalDateTime nowTime = baseEntity.getCreateTime() == null ? LocalDateTime.now() : baseEntity.getCreateTime();
        baseEntity.setCreateTime(nowTime);
        baseEntity.setUpdateTime(nowTime);

        LoginUser loginUser = getLoginUser();
        if (loginUser == null) {
            return;
        }

        Long userId = baseEntity.getCreateBy() == null ? loginUser.getUserId() : baseEntity.getCreateBy();
        // 设置创建人
        baseEntity.setCreateBy(userId);
        baseEntity.setUpdateBy(userId);

        // 创建部门
        Long deptId = baseEntity.getCreateDept() != null ? baseEntity.getCreateDept() : loginUser.getDeptId();
        baseEntity.setCreateDept(deptId);
    }

    /**
     * 添加更新对象参数
     */
    @Override
    public void configureUpdate(Class<?> entityClass, EntityUpdateExpressionBuilder entityUpdateExpressionBuilder, Object entity) {
        BaseEntity baseEntity = (BaseEntity) entity;

        // 更新时间
        baseEntity.setUpdateTime(LocalDateTime.now());

        LoginUser loginUser = getLoginUser();
        if (loginUser == null) {
            return;
        }

        // 未指定则使用当前登录人
        Long userId = baseEntity.getUpdateBy() == null ? loginUser.getUserId() : baseEntity.getUpdateBy();
        baseEntity.setUpdateBy(userId);
    }

    /**
     * 表达式更新set参数添加
     */
    @Override
    public void configure(Class<?> entityClass, EntityUpdateExpressionBuilder entityUpdateExpressionBuilder, ColumnSetter<Object> columnSetter) {
        //是否已经set了 如果你觉得你程序里面不会手动去修改这两个值那么也可以不加这个判断
        if (!entityUpdateExpressionBuilder.getSetColumns().containsOnce(entityClass, BaseEntity.Fields.updateTime)) {
            columnSetter.set(BaseEntity.Fields.updateTime, LocalDateTime.now());
        }

        LoginUser loginUser = getLoginUser();
        if (loginUser == null) {
            return;
        }

        if (!entityUpdateExpressionBuilder.getSetColumns().containsOnce(entityClass, BaseEntity.Fields.updateBy)) {
            columnSetter.set( BaseEntity.Fields.updateBy, loginUser.getUserId());
        }
    }

    @Override
    public String name() {
        return "InjectionBaseFieldInterceptor";
    }

    @Override
    public boolean apply(Class<?> entityClass) {
        return BaseEntity.class.isAssignableFrom(entityClass);
    }

    /**
     * 获取登录用户
     */
    private LoginUser getLoginUser() {
        LoginUser loginUser;
        try {
            loginUser = LoginHelper.getLoginUser();
        } catch (Exception e) {
            log.warn("自动注入警告 => 用户未登录");
            return null;
        }
        return loginUser;
    }
}
