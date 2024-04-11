package com.layjava.common.doc.javadoc.properties;

import lombok.Data;

/**
 * 联系人/作者信息
 *
 * @author chengliang
 * @since 2024/04/11
 */
@Data
public class ContactProperty {
    /**
     * 姓名
     */
    private String name;
    /**
     * 电子邮件
     */
    private String email;
    /**
     * url
     */
    private String url;
}
