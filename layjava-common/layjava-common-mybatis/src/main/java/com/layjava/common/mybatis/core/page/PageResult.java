package com.layjava.common.mybatis.core.page;

import cn.hutool.core.collection.CollUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 统一分页结果对象
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

    public static <T> PageResult<T> build(List<T> list) {
        PageResult<T> rspData = new PageResult<>();
        rspData.setRows(list);
        rspData.setTotal(list.size());
        return rspData;
    }

    public static <T> PageResult<T> build() {
        return new PageResult<>();
    }

    public static <T> PageResult<T> build(List<T> data, long total) {
        return new PageResult<>(data, total);
    }

    public static <T> PageResult<T> build(long total) {
        return new PageResult<>(CollUtil.newArrayList(), total);
    }

}
