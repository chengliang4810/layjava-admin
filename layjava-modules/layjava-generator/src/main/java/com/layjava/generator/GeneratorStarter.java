package com.layjava.generator;

import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.layjava.common.mybatis.core.entity.BaseEntity;

import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * 代码生成器启动类
 * 后续做成接口
 * @author chengliang
 * @date 2024/04/03
 */
public class GeneratorStarter {

    private final static List<String> ENTITY_SUPER_CLASS_COMMONS = List.of("create_dept","create_by", "create_time", "update_by", "update_time");

    public static void main(String[] args) {
        // 获取当前项目的路径
        String projectPath = System.getProperty("user.dir");
        // 临时文件目录
        File tempDir = FileUtil.file(projectPath + "/temp/");
        // Java目录
        File javaDir = FileUtil.file(tempDir, "/src/main/java/");
        // mapper xml文件目录
        File xmlDir = FileUtil.file(tempDir, "/src/main/resources/mapper/");

        FastAutoGenerator.create("jdbc:mysql://10.0.0.16:3306/ry-vue?serverTimezone=Asia/Shanghai", "root", "P@ssw0rd")
                .globalConfig(builder -> {
                    builder.author("chengliang4810") // 设置作者
                            .dateType(DateType.TIME_PACK) // 设置时间类型对应策略
                            //.disableOpenDir() // 生成完后不打开输出目录
                            .outputDir(javaDir.getAbsolutePath()); // 指定输出目录
                })
                // domain
                // 包配置
                .packageConfig(builder -> {
                    builder.parent("com.layjava.generator") // 设置包名
                            .moduleName("test") // 设置模块名
                            .entity("domain") // 设置entity包名
                            .pathInfo(Collections.singletonMap( OutputFile.xml, xmlDir.getAbsolutePath())); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("sys_user") // 设置需要生成的表名
                            .addTablePrefix("sys_", "iot_") // 设置过滤表前缀
                            .entityBuilder()
                            .superClass(BaseEntity.class)
                            .enableFileOverride() // 文件覆盖
                            .enableChainModel() // 开启链式调用
                            .enableLombok() // 开启lombok模型
                            .versionColumnName("version")
                            .logicDeleteColumnName("deleted")
                            .addIgnoreColumns(ENTITY_SUPER_CLASS_COMMONS) // 设置公共字段
                            .disableSerialVersionUID() // 取消生成serialVersionUID
                            .controllerBuilder().enableFileOverride()
                            .superClass("com.layjava.common.web.core.BaseController")
                            .mapperBuilder().enableFileOverride() //
                            .serviceBuilder() // 设置 service 层代码策略
                            .enableFileOverride() // 开启覆盖已生成文件
                            .formatServiceFileName("%sService"); // 个人不喜欢IService的模式
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

}