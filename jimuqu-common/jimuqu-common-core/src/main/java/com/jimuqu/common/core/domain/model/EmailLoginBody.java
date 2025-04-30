package com.jimuqu.common.core.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.noear.solon.validation.annotation.Email;
import org.noear.solon.validation.annotation.NotBlank;

/**
 * 邮件登录对象
 *
 * @author Lion Li,chengliang4810
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class EmailLoginBody extends LoginBody {

    /**
     * 邮箱
     */
    @NotBlank(message = "{user.email.not.blank}")
    @Email(message = "{user.email.not.valid}")
    private String email;

    /**
     * 邮箱code
     */
    @NotBlank(message = "{email.code.not.blank}")
    private String emailCode;

}
