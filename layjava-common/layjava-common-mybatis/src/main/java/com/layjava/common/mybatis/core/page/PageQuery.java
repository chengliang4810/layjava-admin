package com.layjava.common.mybatis.core.page;

import cn.xbatis.core.mybatis.mapper.context.Pager;
import com.layjava.common.core.exception.ServiceException;
import com.layjava.common.core.utils.StringUtil;
import com.layjava.common.core.utils.sql.SqlUtil;
import db.sql.api.impl.cmd.Methods;
import db.sql.api.impl.cmd.basic.OrderByDirection;
import db.sql.api.impl.cmd.struct.query.OrderBy;
import lombok.Data;
import org.dromara.hutool.core.util.ObjUtil;

import java.io.Serial;
import java.io.Serializable;

/**
 * 分页查询实体类
 *
 * @author Lion Li
 */

@Data
public class PageQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 分页大小
     */
    private Integer pageSize;

    /**
     * 当前页数
     */
    private Integer pageNum;

    /**
     * 排序列
     */
    private String orderByColumn;

    /**
     * 排序的方向desc或者asc
     */
    private String isAsc;

    /**
     * 当前记录起始索引 默认值
     */
    public static final int DEFAULT_PAGE_NUM = 1;

    /**
     * 每页显示记录数 默认值 默认查全部
     */
    public static final int DEFAULT_PAGE_SIZE = Integer.MAX_VALUE;

    public <T> Pager<T> build() {
        Integer pageNum = ObjUtil.defaultIfNull(getPageNum(), DEFAULT_PAGE_NUM);
        Integer pageSize = ObjUtil.defaultIfNull(getPageSize(), DEFAULT_PAGE_SIZE);
        if (pageNum <= 0) {
            pageNum = DEFAULT_PAGE_NUM;
        }
        return new Pager<>(pageNum, pageSize);
    }

    /**
     * 构建排序
     * <p>
     * 支持的用法如下:
     * {isAsc:"asc",orderByColumn:"id"} order by id asc
     * {isAsc:"asc",orderByColumn:"id,createTime"} order by id asc,create_time asc
     * {isAsc:"desc",orderByColumn:"id,createTime"} order by id desc,create_time desc
     * {isAsc:"asc,desc",orderByColumn:"id,createTime"} order by id asc,create_time desc
     */
    public OrderBy[] buildOrderBy() {
        if (StringUtil.isBlank(orderByColumn) || StringUtil.isBlank(isAsc)) {
            return new OrderBy[]{};
        }

        String orderBy = SqlUtil.escapeOrderBySql(orderByColumn);
        orderBy = StringUtil.toUnderScoreCase(orderBy);

        // 兼容前端排序类型
        isAsc = StringUtil.replaceEach(isAsc, new String[]{"ascending", "descending"}, new String[]{"asc", "desc"});

        String[] orderByArr = orderBy.split(StringUtil.SEPARATOR);
        String[] isAscArr = isAsc.split(StringUtil.SEPARATOR);
        if (isAscArr.length != 1 && isAscArr.length != orderByArr.length) {
            throw new ServiceException("排序参数有误" );
        }
        OrderBy[] orderBys = new OrderBy[orderByArr.length];
        // 每个字段各自排序
        for (int i = 0; i < orderByArr.length; i++) {
            String orderByStr = orderByArr[i];
            String isAscStr = isAscArr.length == 1 ? isAscArr[0] : isAscArr[i];
            if ("asc".equals(isAscStr)) {
                orderBys[i] = new OrderBy().orderBy(OrderByDirection.ASC,  Methods.column(orderByStr));
            } else if ("desc".equals(isAscStr)) {
                orderBys[i] =  new OrderBy().orderBy(OrderByDirection.DESC, Methods.column(orderByStr));
            } else {
                throw new ServiceException("排序参数有误" );
            }
        }
        return orderBys;
    }


}
