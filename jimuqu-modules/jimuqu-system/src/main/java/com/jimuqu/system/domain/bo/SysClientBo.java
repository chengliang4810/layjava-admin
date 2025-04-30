package com.jimuqu.system.domain.bo;

import com.jimuqu.common.core.validate.group.AddGroup;
import com.jimuqu.common.core.validate.group.UpdateGroup;
import com.jimuqu.common.mybatis.core.entity.BoBaseEntity;
import com.jimuqu.system.domain.SysClient;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.noear.solon.validation.annotation.NotBlank;
import org.noear.solon.validation.annotation.NotNull;

import java.util.List;

/**
 * 授权管理业务对象 sys_client
 *
 * @author Michelle.Chung
 * @date 2023-05-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SysClient.class, reverseConvertGenerate = false)
public class SysClientBo extends BoBaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = {UpdateGroup.class})
    private Long id;

    /**
     * 客户端id
     */
    private String clientId;

    /**
     * 客户端key
     */
    @NotBlank(message = "客户端key不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String clientKey;

    /**
     * 客户端秘钥
     */
    @NotBlank(message = "客户端秘钥不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String clientSecret;

    /**
     * 授权类型
     */
    @NotNull(message = "授权类型不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private List<String> grantTypeList;

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
    private Long activeTimeout;

    /**
     * token固定超时时间
     */
    private Long timeout;

    /**
     * 状态（0正常 1停用）
     */
    private String status;


}
