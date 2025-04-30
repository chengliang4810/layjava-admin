package com.jimuqu.auth.domain.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.noear.solon.validation.annotation.Length;
import org.noear.solon.validation.annotation.NotBlank;

/**
 * 密码登录对象
 *
 * @author chengliang
 * @since 2024/04/24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PasswordLoginBody extends LoginBody {

    /**
     * 用户名
     */
    @NotBlank(message = "账号不能为空")
    @Length(min = 2, max = 20, message = "2到20个汉字、字母、数字或下划线组成，且必须以非数字开头")
    private String account;

    /**
     * 用户密码
     */
    @NotBlank(message = "密码不能为空")
    @Length(min = 2, max = 50, message = "密码长度 5-50个字符")
    private String password;

}
