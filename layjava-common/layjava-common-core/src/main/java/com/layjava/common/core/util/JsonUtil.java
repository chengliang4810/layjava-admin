package com.layjava.common.core.util;

import cn.hutool.json.JSONUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * json工具类, 代码中统一使用该工具进行转换,方便用户进行全局替换
 *
 * @author chengliang
 * @since  2024/04/24
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtil {

    /**
     * 将对象转换为 JSON 字符串
     *
     * @param obj 对象
     * @return JSON 字符串
     */
    public static String toJsonString(Object obj) {
        return JSONUtil.toJsonStr(obj);
    }

    /**
     * 将 JSON 字符串转换为对象
     *
     * @param json  JSON 字符串
     * @param clazz 对象类型
     * @param <T>   对象类型
     * @return 对象
     */
    public static <T> T toObject(String json, Class<T> clazz) {
        return JSONUtil.toBean(json, clazz);
    }

}
