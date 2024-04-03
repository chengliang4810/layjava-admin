package com.layjava.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

@Data
@Accessors(chain = true)
public class LoginVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 授权令牌
     */
    private String token;

    /**
     * 授权令牌 access_token 的有效期
     */
    private Long expireIn;


}
