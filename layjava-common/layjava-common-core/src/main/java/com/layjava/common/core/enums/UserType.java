package com.layjava.common.core.enums;

import com.layjava.common.core.utils.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 设备类型
 * 针对多套 用户体系
 *
 * @author Lion Li,chengliang4810
 */
@Getter
@AllArgsConstructor
public enum UserType {

    /**
     * pc端
     */
    PC_USER("pc_user" ),

    /**
     * app端
     */
    APP_USER("app_user" );

    private final String userType;

    public static UserType getUserType(String str) {
        for (UserType value : values()) {
            if (StringUtil.contains(str, value.getUserType())) {
                return value;
            }
        }
        throw new RuntimeException("'UserType' not found By " + str);
    }
}
