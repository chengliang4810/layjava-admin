package com.layjava.system.mapper;

import com.layjava.common.mybatis.core.mapper.BaseMapperPlus;
import com.layjava.system.domain.SysDictData;
import com.layjava.system.domain.vo.SysDictDataVo;
import com.mybatisflex.core.query.QueryWrapper;

import java.util.List;

import static com.layjava.system.domain.table.SysDictDataTableDef.SYS_DICT_DATA;

/**
 * 字典表 数据层
 *
 * @author Lion Li
 */
public interface SysDictDataMapper extends BaseMapperPlus<SysDictData> {

    default List<SysDictDataVo> selectDictDataByType(String dictType) {
        return selectListByQueryAs(
                QueryWrapper.create().select().from(SYS_DICT_DATA)
                        .where(SYS_DICT_DATA.DICT_TYPE.eq(dictType))
                        .orderBy(SYS_DICT_DATA.DICT_SORT, true),
                SysDictDataVo.class);
    }
}
