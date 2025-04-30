package com.jimuqu.common.core.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.noear.solon.validation.annotation.NotBlank;

/**
 * 三方登录对象
 *
 * @author Lion Li,chengliang4810
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class XcxLoginBody extends LoginBody {

    /**
     * 小程序id(多个小程序时使用)
     */
    private String appid;

    /**
     * 小程序code
     */
    @NotBlank(message = "{xcx.code.not.blank}")
    private String xcxCode;

}
