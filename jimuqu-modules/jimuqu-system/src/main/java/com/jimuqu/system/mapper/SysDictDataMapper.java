package com.jimuqu.system.mapper;

import com.jimuqu.common.mybatis.core.mapper.BaseMapperPlus;
import com.jimuqu.system.domain.SysDictData;
import com.jimuqu.system.domain.vo.SysDictDataVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 字典数据数据层
 * @author chengliang4810
 * @since 2025-05-27
 */
@Mapper
public interface SysDictDataMapper extends BaseMapperPlus<SysDictData, SysDictDataVo> {

}