package com.layjava.common.doc.javadoc.controller;

import com.layjava.common.doc.javadoc.OpenApi2Utils;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.core.handle.Context;

import java.io.IOException;

/**
 * 开放式api控制器
 *
 * @author chengliang
 * @since 2024/04/11
 */
public class OpenApiController {

    @Mapping("swagger/v2")
    public String api(Context ctx, String group) throws IOException {
        String apiJson = OpenApi2Utils.getApiJson(ctx, "");
        System.out.println("apiJson:" + apiJson);
        return apiJson;
    }

}
