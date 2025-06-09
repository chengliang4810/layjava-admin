package com.jimuqu;

import com.jimuqu.domain.SystemVersion;
import org.dromara.autotable.solon.annotation.EnableAutoTable;
import org.noear.solon.Solon;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Get;
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
@EnableAutoTable
public class Application {

    public static void main(String[] args) {
        Solon.start(Application.class, args);
    }

    /**
     * 获取应用版本号
     * @return 版本号
     */
    @Get
    @Mapping
    public SystemVersion version(){
        return SystemVersion.builder()
                .name("LayJava-Admin开源管理系统")
                .version(Solon.cfg().get("solon.app.version"))
                .build();
    }

}
