package com.layjava.auth.domain.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("access_token")
    private String token;

    /**
     * 授权令牌 access_token 的有效期
     */
    @JsonProperty("expire_in")
    private Long expireIn;

    /**
     * 应用id
     */
    @JsonProperty("client_id")
    private String clientId;

    /**
     * 令牌权限
     */
    private String scope;

    /**
     * 用户 openid
     */
    private String openid;


}
