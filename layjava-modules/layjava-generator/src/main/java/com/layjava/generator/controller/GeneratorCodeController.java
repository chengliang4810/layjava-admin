package com.layjava.generator.controller;

import com.layjava.generator.domain.GeneratorConfig;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Get;
import org.noear.solon.annotation.Post;
import org.noear.solon.annotation.Put;
import org.noear.solon.annotation.Delete;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Get;
import org.noear.solon.annotation.Post;
import org.noear.solon.annotation.Put;
import org.noear.solon.annotation.Delete;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Post;

/**
 * 生成器代码控制器
 *
 * @author chengliang
 * @since 2024/04/24
 */
@Controller
@Mapping("/generator/code")
public class GeneratorCodeController {


    /**
     * 生成代码并下载
     */
    @Post
    @Mapping("")
    public void generatorCodeDownload(GeneratorConfig generatorConfig) {

    }

}
