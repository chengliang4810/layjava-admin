package com.layjava.common.core.utils;

import com.layjava.common.core.utils.reflect.ReflectUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.dromara.hutool.core.collection.CollUtil;
import org.dromara.hutool.core.tree.MapTree;
import org.dromara.hutool.core.tree.TreeNodeConfig;
import org.dromara.hutool.core.tree.TreeUtil;
import org.dromara.hutool.core.tree.parser.NodeParser;

import java.util.List;

/**
 * 扩展 hutool TreeUtil 封装系统树构建
 *
 * @author Lion Li
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TreeBuildUtil extends TreeUtil {

    /**
     * 根据前端定制差异化字段
     */
    public static final TreeNodeConfig DEFAULT_CONFIG = TreeNodeConfig.DEFAULT_CONFIG.setNameKey("label" );

    public static <T, K> List<MapTree<K>> build(List<T> list, NodeParser<T, K> nodeParser) {
        if (CollUtil.isEmpty(list)) {
            return null;
        }
        K k = ReflectUtil.invokeGetter(list.get(0), "parentId" );
        return TreeUtil.build(list, k, DEFAULT_CONFIG, nodeParser);
    }

}
