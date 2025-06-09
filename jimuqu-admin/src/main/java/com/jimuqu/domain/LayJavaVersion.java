package com.jimuqu.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class LayJavaVersion implements Serializable {

    /**
     * 名称
     */
    private String name;

    /**
     * 版本号
     */
    private String version;

}
