package com.layjava.system.test;

import com.layjava.Application;
import com.layjava.system.domain.SysClient;
import com.layjava.system.mapper.SysClientMapper;
import org.apache.ibatis.solon.annotation.Db;
import org.junit.jupiter.api.Test;
import org.noear.solon.annotation.Import;
import org.noear.solon.test.SolonTest;

import java.util.List;

@SolonTest(Application.class)
@Import(profiles = "classpath:app.yml")
public class ClientMapperTest {

    @Db
    private SysClientMapper clientMapper;

    @Test
    public void getClientListTest(){
        List<SysClient> sysClients = clientMapper.selectAll();
        System.out.println(sysClients);
    }

}
