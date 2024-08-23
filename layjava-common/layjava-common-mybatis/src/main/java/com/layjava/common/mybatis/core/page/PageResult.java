package com.layjava.common.mybatis.core.page;

import com.layjava.common.core.domain.R;
import com.mybatisflex.core.paginate.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.dromara.hutool.http.meta.HttpStatus;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 表格分页数据对象
 *
 * @author chengliang
 * @date 2024/08/14
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PageResult<T> extends R<PageInfo<T>> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 分页
     *
     * @param list  列表数据
     * @param total 总记录数
     */
    public PageResult(List<T> list, long total) {
        setData(PageInfo.build(list, total));
    }

    public static <T> PageResult<T> build(Page<T> page) {
        PageResult<T> rspData = new PageResult<>();
        rspData.setCode(HttpStatus.HTTP_OK);
        rspData.setMsg("查询成功" );
        rspData.setData(PageInfo.build(page.getRecords(), page.getTotalRow()));
        return rspData;
    }

    public static <T> PageResult<T> build(List<T> list) {
        PageResult<T> rspData = new PageResult<>();
        rspData.setCode(HttpStatus.HTTP_OK);
        rspData.setMsg("查询成功" );
        rspData.setData(PageInfo.build(list, list.size()));
        return rspData;
    }

    public static <T> PageResult<T> build() {
        PageResult<T> rspData = new PageResult<>();
        rspData.setCode(HttpStatus.HTTP_OK);
        rspData.setMsg("查询成功" );
        return rspData;
    }

}
