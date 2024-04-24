package com.layjava.system.domain;

import com.layjava.common.mybatis.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SysClient extends BaseEntity {

    private String clientId;

    private String deviceType;

}