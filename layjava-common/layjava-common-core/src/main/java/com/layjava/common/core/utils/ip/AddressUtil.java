package com.layjava.common.core.utils.ip;

import com.layjava.common.core.utils.StringUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.net.NetUtil;
import org.dromara.hutool.http.html.HtmlUtil;


/**
 * 获取地址类
 *
 * @author Lion Li
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressUtil {

    // 未知地址
    public static final String UNKNOWN = "XX XX";

    public static String getRealAddressByIP(String ip) {
        if (StringUtil.isBlank(ip)) {
            return UNKNOWN;
        }
        // 内网不查询
        ip = StringUtil.contains(ip, "0:0:0:0:0:0:0:1" ) ? "127.0.0.1" : HtmlUtil.cleanHtmlTag(ip);
        if (NetUtil.isInnerIP(ip)) {
            return "内网IP";
        }
        return RegionUtils.getCityInfo(ip);
    }
}
