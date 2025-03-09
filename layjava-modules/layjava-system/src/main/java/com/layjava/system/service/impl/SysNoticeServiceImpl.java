package com.layjava.system.service.impl;

import com.layjava.common.core.utils.MapstructUtil;
import com.layjava.common.core.utils.StringUtil;
import com.layjava.common.mybatis.core.page.PageQuery;
import com.layjava.common.mybatis.core.page.PageResult;
import com.layjava.system.domain.SysNotice;
import com.layjava.system.domain.bo.SysNoticeBo;
import com.layjava.system.domain.vo.SysNoticeVo;
import com.layjava.system.domain.vo.SysUserVo;
import com.layjava.system.mapper.SysNoticeMapper;
import com.layjava.system.mapper.SysUserMapper;
import com.layjava.system.service.ISysNoticeService;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import org.dromara.hutool.core.util.ObjUtil;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

import java.util.Arrays;
import java.util.List;

import static com.layjava.system.domain.table.SysNoticeTableDef.SYS_NOTICE;

/**
 * 公告 服务层实现
 *
 * @author Lion Li
 */

@Component
public class SysNoticeServiceImpl implements ISysNoticeService {

    @Inject
    private SysNoticeMapper baseMapper;
    @Inject
    private SysUserMapper userMapper;

    @Override
    public PageResult<SysNoticeVo> selectPageNoticeList(SysNoticeBo notice, PageQuery pageQuery) {
        QueryWrapper lqw = buildQueryWrapper(notice);
        Page<SysNoticeVo> page = baseMapper.paginateAs(pageQuery, lqw, SysNoticeVo.class);
        return PageResult.build(page);
    }

    /**
     * 查询公告信息
     *
     * @param noticeId 公告ID
     * @return 公告信息
     */
    @Override
    public SysNoticeVo selectNoticeById(Long noticeId) {
        return baseMapper.selectOneWithRelationsByIdAs(noticeId, SysNoticeVo.class);
    }

    /**
     * 查询公告列表
     *
     * @param notice 公告信息
     * @return 公告集合
     */
    @Override
    public List<SysNoticeVo> selectNoticeList(SysNoticeBo notice) {
        QueryWrapper lqw = buildQueryWrapper(notice);
        return baseMapper.selectListByQueryAs(lqw, SysNoticeVo.class);
    }

    private QueryWrapper buildQueryWrapper(SysNoticeBo bo) {
        QueryWrapper queryWrapper = QueryWrapper.create().from(SYS_NOTICE)
                .where(SYS_NOTICE.NOTICE_TITLE.like(bo.getNoticeTitle()))
                .and(SYS_NOTICE.NOTICE_TYPE.eq(bo.getNoticeType()));
        if (StringUtil.isNotBlank(bo.getCreateByName())) {
            SysUserVo sysUser = userMapper.selectUserByUserName(bo.getCreateByName());
            queryWrapper.and(SYS_NOTICE.CREATE_BY.eq(ObjUtil.isNotNull(sysUser) ? sysUser.getUserId() : null));
        }
        queryWrapper.orderBy(SYS_NOTICE.NOTICE_ID, true);
        return queryWrapper;
    }

    /**
     * 新增公告
     *
     * @param bo 公告信息
     * @return 结果
     */
    @Override
    public int insertNotice(SysNoticeBo bo) {
        SysNotice notice = MapstructUtil.convert(bo, SysNotice.class);
        return baseMapper.insert(notice, true);
    }

    /**
     * 修改公告
     *
     * @param bo 公告信息
     * @return 结果
     */
    @Override
    public int updateNotice(SysNoticeBo bo) {
        SysNotice notice = MapstructUtil.convert(bo, SysNotice.class);
        return baseMapper.update(notice);
    }

    /**
     * 删除公告对象
     *
     * @param noticeId 公告ID
     * @return 结果
     */
    @Override
    public int deleteNoticeById(Long noticeId) {
        return baseMapper.deleteById(noticeId);
    }

    /**
     * 批量删除公告信息
     *
     * @param noticeIds 需要删除的公告ID
     * @return 结果
     */
    @Override
    public int deleteNoticeByIds(Long[] noticeIds) {
        return baseMapper.deleteBatchByIds(Arrays.asList(noticeIds));
    }
}
