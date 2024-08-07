package com.layjava.common.json.utils;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.layjava.common.core.utils.StringUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.noear.snack.ONode;
import org.noear.solon.Solon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * JSON 工具类
 *
 * @author chengliang
 * @date 2024/08/07
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtils {

    public static String toJsonString(Object object) {
        if (ObjectUtil.isNull(object)) {
            return null;
        }
        return ONode.stringify(object);
    }

    public static <T> T parseObject(String text, Class<T> clazz) {
        if (StringUtils.isEmpty(text)) {
            return null;
        }
        return ONode.deserialize(text, clazz);
    }

    public static Dict parseMap(String text) {
        if (StringUtils.isBlank(text)) {
            return null;
        }
            return ONode.deserialize(text, Dict.class);

    }

}
