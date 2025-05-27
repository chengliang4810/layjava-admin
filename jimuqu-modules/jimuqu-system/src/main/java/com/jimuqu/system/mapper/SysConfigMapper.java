package com.jimuqu.system.mapper;

import com.jimuqu.common.mybatis.core.mapper.BaseMapperPlus;
import com.jimuqu.system.domain.SysConfig;
import com.jimuqu.system.domain.vo.SysConfigVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 参数配置数据层
 * @author chengliang4810
 * @since 2025-05-27
 */
@Mapper
public interface SysConfigMapper extends BaseMapperPlus<SysConfig, SysConfigVo> {

}