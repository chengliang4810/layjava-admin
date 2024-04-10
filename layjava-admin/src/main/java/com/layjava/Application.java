package com.layjava;

import org.noear.solon.Solon;
import org.noear.solon.annotation.SolonMain;

/**
 * 应用启动类
 *
 * @author chengliang
 * @since 2024/02/26
 */
@SolonMain
public class Application {

    public static void main(String[] args) {
        Solon.start(Application.class, args);
    }

}
