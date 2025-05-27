package com.jimuqu.system.mapper;

import com.jimuqu.common.mybatis.core.mapper.BaseMapperPlus;
import com.jimuqu.system.domain.SysClient;
import com.jimuqu.system.domain.vo.SysClientVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 授权管理对象 sys_client数据层
 * @author chengliang4810
 * @since 2025-05-27
 */
@Mapper
public interface SysClientMapper extends BaseMapperPlus<SysClient, SysClientVo> {

}