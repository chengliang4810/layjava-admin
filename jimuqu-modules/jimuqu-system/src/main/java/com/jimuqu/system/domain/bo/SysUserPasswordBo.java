package com.jimuqu.system.domain.bo;


import lombok.Data;
import org.noear.solon.validation.annotation.NotBlank;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户密码修改bo
 */
@Data
public class SysUserPasswordBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 旧密码
     */
    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;

    /**
     * 新密码
     */
    @NotBlank(message = "新密码不能为空")
    private String newPassword;
}
