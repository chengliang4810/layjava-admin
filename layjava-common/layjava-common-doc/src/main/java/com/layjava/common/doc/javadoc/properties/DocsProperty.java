package com.layjava.common.doc.javadoc.properties;

import lombok.Data;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;

import java.util.List;

@Data
@Configuration
@Inject("${solon.docs}")
public class DocsProperty {

    /**
     * 是否开启接口文档
     */
    private Boolean enabled;
    /**
     * 标题
     */
    private String title;
    /**
     * 描述
     */
    private String description;
    /**
     * 版本
     */
    private String version;
    /**
     * 作者信息
     */
    private ContactProperty contact;
    /**
     * 分组配置
     */
    private List<GroupProperty> groupConfigs;

    /**
     * 是不是开启的
     * 默认为开启
     *
     * @return boolean
     */
    public boolean isEnable() {
        if (this.enabled == null) {
            return true;
        }
        return this.enabled;
    }

}
