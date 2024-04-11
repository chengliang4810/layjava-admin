package com.layjava.common.doc.javadoc.properties;

import lombok.Data;

/**
 * 分组配置
 *
 * @author chengliang
 * @since 2024/04/11
 */
@Data
public class GroupProperty {

    /**
     * 分组名称
     */
    private String group;
    /**
     * 分组包路径
     */
    private String packageName;

}
