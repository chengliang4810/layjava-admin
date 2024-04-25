package com.layjava.generator;

import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.util.*;

/**
 * 代码生成器启动类
 * 后续做成接口
 *
 * @author chengliang
 * @since 2024/04/03
 */
public class GeneratorStarter {

    private final static List<String> ENTITY_SUPER_CLASS_COMMONS = List.of("create_dept", "create_by", "create_time", "update_by", "update_time");

    // 获取当前项目的路径
    private static final String projectPath = System.getProperty("user.dir");

    public static void main(String[] args) {

        // 临时文件目录
        File tempDir = FileUtil.file(projectPath + "/temp/");
        FileUtil.del(tempDir);
        // Java目录
        File javaDir = FileUtil.file(tempDir, "/src/main/java/");
        // mapper xml文件目录
        File xmlDir = FileUtil.file(tempDir, "/src/main/resources/mapper/");

        FastAutoGenerator.create("jdbc:mysql://localhost:3306/layjava_db?serverTimezone=Asia/Shanghai", "root", "P@ssw0rd")
                .globalConfig(builder -> {
                    builder.author("chengliang4810") // 设置作者
                            .dateType(DateType.TIME_PACK) // 设置时间类型对应策略
                            //.disableOpenDir() // 生成完后不打开输出目录
                            .outputDir(javaDir.getAbsolutePath()); // 指定输出目录
                })
                // domain
                // 包配置
                .packageConfig(builder -> {
                    builder.parent("com.layjava") // 设置包名
                            .moduleName("system") // 设置模块名
                            .entity("domain") // 设置entity包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, xmlDir.getAbsolutePath())); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("sys_client") // 设置需要生成的表名
                            .addTablePrefix("iot_") // 设置过滤表前缀
                            // Entity配置
                            .entityBuilder()
                            .superClass("com.layjava.common.mybatis.core.entity.BaseEntity") // 设置实体父类
                            .enableFileOverride() // 文件覆盖
                            .enableChainModel() // 开启链式调用
                            .enableLombok() // 开启lombok模型
                            .versionColumnName("version")
                            .logicDeleteColumnName("deleted")
                            .addIgnoreColumns(ENTITY_SUPER_CLASS_COMMONS) // 设置公共字段
                            .disableSerialVersionUID() // 取消生成serialVersionUID
                            // Controller
                            .controllerBuilder().enableFileOverride()
                            .superClass("com.layjava.common.web.core.BaseController")
                            // mapper
                            .mapperBuilder().superClass("com.layjava.common.mybatis.core.mapper.BaseMapperPlus").enableFileOverride() //
                            // Service
                            .serviceBuilder() // 设置 service 层代码策略
                            .enableFileOverride() // 开启覆盖已生成文件
                            .formatServiceFileName("%sService"); // 个人不喜欢IService的模式

                })
                .templateEngine(new FreemarkerTemplateEngine())
                .injectionConfig(consumer -> {
                    List<CustomFile> customFiles = new ArrayList<>();
                    customFiles.add(new CustomFile.Builder().fileName("Bo.java").templatePath("/templates/bo.java.ftl").packageName("domain.bo").enableFileOverride().build());
                    customFiles.add(new CustomFile.Builder().fileName("Vo.java").templatePath("/templates/vo.java.ftl").packageName("domain.vo").enableFileOverride().build());
                    consumer.customFile(customFiles);
                })
                .execute();
    }

}
