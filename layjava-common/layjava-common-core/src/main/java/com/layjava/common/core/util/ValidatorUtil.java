package com.layjava.common.core.util;

import cn.hutool.core.collection.CollUtil;
import com.layjava.common.core.exception.ParameterException;
import com.layjava.common.core.exception.ServiceException;
import org.noear.solon.annotation.Bean;
import org.noear.solon.core.handle.Result;
import org.noear.solon.validation.BeanValidateInfo;
import org.noear.solon.validation.ValidatorManager;

import java.util.List;

/**
 * 验证器工具类
 *
 * @author chengliang
 * @date 2024/04/24
 */
public class ValidatorUtil {


    /**
     * 实体验证
     *
     * @param entity 实体
     */
    public static void validate(Object entity, Class<?>... groups) {
        Result result = ValidatorManager.validateOfEntity(entity, groups);
        if (result.getCode() == Result.SUCCEED_CODE) {
            return;
        }
        Object data = result.getData();
        if (data instanceof BeanValidateInfo beanValidateInfo) {
            throw new ParameterException(beanValidateInfo.message);
        } else if (data instanceof List beanValidateInfoList) {
            if (CollUtil.isEmpty(beanValidateInfoList)) {
                return;
            }
            StringBuilder stringBuilder = new StringBuilder();
            beanValidateInfoList.forEach(o -> {
                if (o instanceof BeanValidateInfo beanValidateInfo) {
                    stringBuilder.append(beanValidateInfo.message).append(";");
                }
            });
            throw new ParameterException(stringBuilder.toString());
        } else {
            throw new ServiceException();
        }
    }


}
