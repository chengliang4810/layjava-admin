package com.jimuqu.system.domain;

import cn.xbatis.core.incrementer.IdentifierGeneratorType;
import cn.xbatis.db.IdAutoType;
import cn.xbatis.db.annotations.*;
import com.jimuqu.common.mybatis.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.dromara.autotable.annotation.AutoColumn;

import java.io.Serial;

/**
 * 授权管理对象 sys_client
 * @author chengliang4810
 * @since 2025-05-27
 */
@Data
@NoArgsConstructor
@FieldNameConstants
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_client")
public class SysClient extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = IdAutoType.GENERATOR, generatorName = IdentifierGeneratorType.DEFAULT)
    @AutoColumn(comment = "主键", length = -1, defaultValue = "", type = "")
    private Long id;
    /**
     * 客户端id
     */
    @AutoColumn(comment = "客户端id", length = -1, defaultValue = "", type = "")
    private String clientId;
    /**
     * 客户端key
     */
    @AutoColumn(comment = "客户端key", length = -1, defaultValue = "", type = "")
    private String clientKey;
    /**
     * 客户端秘钥
     */
    @AutoColumn(comment = "客户端秘钥", length = -1, defaultValue = "", type = "")
    private String clientSecret;
    /**
     * 授权类型
     */
    @AutoColumn(comment = "授权类型", length = -1, defaultValue = "", type = "")
    private String grantType;
    /**
     * 设备类型
     */
    @AutoColumn(comment = "设备类型", length = -1, defaultValue = "", type = "")
    private String deviceType;
    /**
     * token活跃超时时间
     */
    @AutoColumn(comment = "token活跃超时时间", length = -1, defaultValue = "", type = "")
    private Long activeTimeout;
    /**
     * token固定超时时间
     */
    @AutoColumn(comment = "token固定超时时间", length = -1, defaultValue = "", type = "")
    private Long timeout;
    /**
     * 状态（0正常 1停用）
     */
    @AutoColumn(comment = "状态（0正常 1停用）", length = -1, defaultValue = "", type = "")
    private String status;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @AutoColumn(comment = "删除标志（0代表存在 2代表删除）", length = -1, defaultValue = "", type = "")
    private String delFlag;

}
