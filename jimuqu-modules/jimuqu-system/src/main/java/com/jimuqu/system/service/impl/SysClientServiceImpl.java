package com.jimuqu.system.service.impl;

import com.jimuqu.common.mybatis.core.page.PageQuery;
import com.jimuqu.common.mybatis.core.page.PageResult;
import com.jimuqu.system.domain.SysClient;
import com.jimuqu.system.domain.bo.SysClientBo;
import com.jimuqu.system.domain.vo.SysClientVo;
import com.jimuqu.system.service.ISysClientService;
import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.Component;

import java.util.Collection;
import java.util.List;

@Slf4j
@Component
public class SysClientServiceImpl implements ISysClientService {

    @Override
    public SysClientVo queryById(Long id) {
        return null;
    }

    @Override
    public SysClient queryByClientId(String clientId) {
        return null;
    }

    @Override
    public PageResult<SysClientVo> queryPageList(SysClientBo bo, PageQuery pageQuery) {
        return null;
    }

    @Override
    public List<SysClientVo> queryList(SysClientBo bo) {
        return List.of();
    }

    @Override
    public Boolean insertByBo(SysClientBo bo) {
        return null;
    }

    @Override
    public Boolean updateByBo(SysClientBo bo) {
        return null;
    }

    @Override
    public boolean updateUserStatus(Long id, String status) {
        return false;
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        return null;
    }
}
