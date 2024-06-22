package com.layjava.system.domain;

import com.layjava.common.dao.core.entity.BaseEntity;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * 系统授权表
 *
 * @author chengliang4810
 * @since 2024-04-25
 */
@Data
@Table("sys_client")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class SysClient extends BaseEntity {

    /**
     * id
     */
    private Long id;

    /**
     * 客户端id
     */
    private String clientId;

    /**
     * 客户端key
     */
    private String clientKey;

    /**
     * 客户端秘钥
     */
    private String clientSecret;

    /**
     * 授权类型
     */
    private String grantType;

    /**
     * 设备类型
     */
    private String deviceType;

    /**
     * token活跃超时时间
     */
    private Integer activeTimeout;

    /**
     * token固定超时
     */
    private Integer timeout;

    /**
     * 状态（0正常 1停用）
     */
    private String status;
}
