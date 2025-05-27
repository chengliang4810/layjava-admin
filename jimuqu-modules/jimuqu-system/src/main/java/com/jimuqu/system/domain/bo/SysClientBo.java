package com.jimuqu.system.domain.bo;

import com.jimuqu.common.core.validate.group.AddGroup;
import com.jimuqu.common.core.validate.group.UpdateGroup;
import com.jimuqu.common.mybatis.core.entity.BoBaseEntity;
import com.jimuqu.system.domain.SysClient;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.noear.solon.validation.annotation.*;

/**
 * 授权管理对象 sys_client业务对象 sys_client
 *
 * @author chengliang4810
 * @since 2025-05-27
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SysClient.class, reverseConvertGenerate = false)
public class SysClientBo extends BoBaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { UpdateGroup.class })
    private Long id;
    /**
     * 客户端id
     */
    @NotBlank(message = "客户端id不能为空", groups = { AddGroup.class, UpdateGroup.class })
    private String clientId;
    /**
     * 客户端key
     */
    @NotBlank(message = "客户端key不能为空", groups = { AddGroup.class, UpdateGroup.class })
    private String clientKey;
    /**
     * 客户端秘钥
     */
    @NotBlank(message = "客户端秘钥不能为空", groups = { AddGroup.class, UpdateGroup.class })
    private String clientSecret;
    /**
     * 授权类型
     */
    @NotBlank(message = "授权类型不能为空", groups = { AddGroup.class, UpdateGroup.class })
    private String grantType;
    /**
     * 设备类型
     */
    @NotBlank(message = "设备类型不能为空", groups = { AddGroup.class, UpdateGroup.class })
    private String deviceType;
    /**
     * token活跃超时时间
     */
    @NotNull(message = "token活跃超时时间不能为空", groups = { AddGroup.class, UpdateGroup.class })
    private Long activeTimeout;
    /**
     * token固定超时时间
     */
    @NotNull(message = "token固定超时时间不能为空", groups = { AddGroup.class, UpdateGroup.class })
    private Long timeout;
    /**
     * 状态（0正常 1停用）
     */
    @NotBlank(message = "状态（0正常 1停用）不能为空", groups = { AddGroup.class, UpdateGroup.class })
    private String status;

}
