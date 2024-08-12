package com.layjava.generator.config;


import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;

/**
 * 读取代码生成相关配置
 *
 * @author ruoyi
 */
@Configuration
@Inject("${classpath:generator.yml}")
public class GenConfig {

    /**
     * 作者
     */
    public static String author;

    /**
     * 生成包路径
     */
    public static String packageName;

    /**
     * 自动去除表前缀，默认是false
     */
    public static boolean autoRemovePre;

    /**
     * 表前缀(类名不会包含表前缀)
     */
    public static String tablePrefix;

    /**
     * 使用平台
     */
    public static String platform;

    public static String getAuthor() {
        return author;
    }

    // @Value("${author}")
    public void setAuthor(String author) {
        GenConfig.author = author;
    }

    public static String getPackageName() {
        return packageName;
    }

    // @Value("${packageName}")
    public void setPackageName(String packageName) {
        GenConfig.packageName = packageName;
    }

    public static boolean getAutoRemovePre() {
        return autoRemovePre;
    }

    // @Value("${autoRemovePre}")
    public void setAutoRemovePre(boolean autoRemovePre) {
        GenConfig.autoRemovePre = autoRemovePre;
    }

    public static String getTablePrefix() {
        return tablePrefix;
    }

    // @Value("${tablePrefix}")
    public void setTablePrefix(String tablePrefix) {
        GenConfig.tablePrefix = tablePrefix;
    }

    // @Value("${platform}")
    public void setPlatform(String platform) {
        GenConfig.platform = platform;
    }

    public static String getPlatform() {
        return platform;
    }
}
