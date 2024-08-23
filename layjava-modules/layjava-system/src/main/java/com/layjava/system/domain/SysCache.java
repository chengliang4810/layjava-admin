package com.layjava.system.domain;

import com.layjava.common.core.utils.StringUtil;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 缓存信息
 *
 * @author Lion Li
 */
@Data
@NoArgsConstructor
public class SysCache {

    /**
     * 缓存名称
     */
    private String cacheName = "";

    /**
     * 缓存键名
     */
    private String cacheKey = "";

    /**
     * 缓存内容
     */
    private String cacheValue = "";

    /**
     * 备注
     */
    private String remark = "";

    public SysCache(String cacheName, String remark) {
        this.cacheName = cacheName;
        this.remark = remark;
    }

    public SysCache(String cacheName, String cacheKey, String cacheValue) {
        this.cacheName = StringUtil.replace(cacheName, ":", "" );
        this.cacheKey = StringUtil.replace(cacheKey, cacheName, "" );
        this.cacheValue = cacheValue;
    }

}
