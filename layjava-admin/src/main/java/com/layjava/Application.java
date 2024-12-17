package com.layjava;

import org.noear.solon.Solon;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.SolonMain;

/**
 * 应用启动类
 *
 * @author chengliang
 * @since 2024/02/26
 */
@SolonMain
@Controller
public class Application {

    public static void main(String[] args) {
        Solon.start(Application.class, args);
    }


    @Mapping("/")
    public String index(){
        return "欢迎使用LayJava-Admin";
    }

    /**
     * 获取应用版本号
     * @return 版本号
     */
    @Mapping("/version")
    public String version(){
        return Solon.cfg().get("solon.app.version");
    }

}
