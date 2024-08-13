package com.layjava.common.json.utils;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import com.layjava.common.core.utils.StringUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.noear.snack.ONode;

/**
 * JSON 工具类
 *
 * @author chengliang
 * @date 2024/08/07
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtils {

    /**
     * 对象转 JsonString
     *
     * @param object 实体对象
     * @return JsonString
     */
    public static String toJsonString(Object object) {
        if (ObjectUtil.isNull(object)) {
            return null;
        }
        return ONode.stringify(object);
    }

    /**
     * Json字符串转对象
     *
     * @param jsonString json 字符串
     * @param clazz      类型
     * @return 实体类型
     */
    public static <T> T parseObject(String jsonString, Class<T> clazz) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        return ONode.deserialize(jsonString, clazz);
    }

    /**
     * json 转 map
     *
     * @param jsonString json 字符串
     * @return map 对象
     */
    public static Dict parseMap(String jsonString) {
        if (StringUtils.isBlank(jsonString)) {
            return null;
        }
        return ONode.deserialize(jsonString, Dict.class);
    }

}
