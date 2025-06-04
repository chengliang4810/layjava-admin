package com.jimuqu.system.mapper;

import com.jimuqu.common.mybatis.core.mapper.BaseMapperPlus;
import com.jimuqu.system.domain.SysPost;
import com.jimuqu.system.domain.vo.SysPostVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 岗位信息数据层
 * @author chengliang4810
 * @since 2025-06-04
 */
@Mapper
public interface SysPostMapper extends BaseMapperPlus<SysPost, SysPostVo> {

}