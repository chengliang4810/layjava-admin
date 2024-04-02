package com.layjava.generator.controller;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;

@Controller
@Mapping("/code")
public class CodeController {

    @Mapping("/gen")
    public String gen() {

        FastAutoGenerator.create("url", "username", "password")
                .globalConfig(builder -> {
                    builder.author("baomidou") // 设置作者
                            .enableSpringdoc() // SpringDoc配置
                            .outputDir("D://"); // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
//                    JdbcType typeCode = metaInfo.getJdbcType();
//                    if (typeCode == Types.SMALLINT) {
//                        // 自定义类型转换
//                        return DbColumnType.INTEGER;
//                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
//                .packageConfig(builder -> {
//                    builder.parent("com.baomidou.mybatisplus.samples.generator") // 设置父包名
//                            .moduleName("system") // 设置父包模块名
//                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D://")); // 设置mapperXml生成路径
//                })
                .strategyConfig(builder -> {
                    builder.addInclude("t_simple") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

        return "生成代码";
    }

    public static void main(String[] args) {
        // 获取当前项目的路径
        String projectPath = System.getProperty("user.dir");

        FastAutoGenerator.create("url", "username", "password")
                .globalConfig(builder -> {
                    builder.author("chengliang4810") // 设置作者
                            .dateType(DateType.TIME_PACK) // 设置时间类型对应策略
                            .enableSpringdoc() // SpringDoc配置
                            .outputDir(projectPath); // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
//                    JdbcType typeCode = metaInfo.getJdbcType();
//                    if (typeCode == Types.SMALLINT) {
//                        // 自定义类型转换
//                        return DbColumnType.INTEGER;
//                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
//                .packageConfig(builder -> {
//                    builder.parent("com.baomidou.mybatisplus.samples.generator") // 设置父包名
//                            .moduleName("system") // 设置父包模块名
//                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D://")); // 设置mapperXml生成路径
//                })
                .strategyConfig(builder -> {
                    builder.addInclude("t_simple") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
