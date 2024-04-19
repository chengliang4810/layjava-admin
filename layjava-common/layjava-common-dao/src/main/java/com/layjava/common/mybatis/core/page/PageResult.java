package com.layjava.common.mybatis.core.page;

import cn.zhxu.bs.SearchResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.layjava.common.core.util.MapstructUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 分页数据对象
 *
 * @author chengliang
 * @since  2024/04/17
 */
@Data
@NoArgsConstructor
public class PageResult<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 总记录数
     */
    private long total;

    /**
     * 列表数据
     */
    private List<T> rows;

    /**
     * 分页
     *
     * @param list  列表数据
     * @param total 总记录数
     */
    public PageResult(List<T> list, long total) {
        this.rows = list;
        this.total = total;
    }

    public static <T> PageResult<T> build(IPage<T> page) {
        PageResult<T> rspData = new PageResult<>();
        rspData.setRows(page.getRecords());
        rspData.setTotal(page.getTotal());
        return rspData;
    }

    public static <T> PageResult<T> build(List<T> list) {
        PageResult<T> rspData = new PageResult<>();
        rspData.setRows(list);
        rspData.setTotal(list.size());
        return rspData;
    }

    public static <T> PageResult<T> build() {
        return new PageResult<>();
    }

    public static <T> PageResult<T> build(SearchResult<T> search) {
        PageResult<T> rspData = new PageResult<>();
        rspData.setRows(search.getDataList());
        rspData.setTotal(search.getTotalCount().intValue());
        return rspData;
    }

    public static <V> PageResult<V> build(SearchResult<?> search, Class<V> toClass) {
        List<V> convertDataList = MapstructUtils.convert(search.getDataList(), toClass);
        if (convertDataList == null) {
            return new PageResult<>();
        }
        PageResult<V> rspData = new PageResult<>();
        rspData.setRows(convertDataList);
        rspData.setTotal(search.getTotalCount().intValue());
        return rspData;
    }

}
