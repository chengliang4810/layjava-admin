package com.layjava.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.layjava.common.core.domain.R;
import com.layjava.common.core.service.DictService;
import com.layjava.common.mybatis.core.page.PageQuery;
import com.layjava.common.mybatis.core.page.TableDataInfo;
import com.layjava.common.log.annotation.Log;
import com.layjava.common.log.enums.BusinessType;
import com.layjava.common.web.core.BaseController;
import com.layjava.system.domain.bo.SysNoticeBo;
import com.layjava.system.domain.vo.SysNoticeVo;
import com.layjava.system.service.ISysNoticeService;
import org.noear.solon.annotation.*;

/**
 * 公告 信息操作处理
 *
 * @author Lion Li
 */
@Controller
@Mapping("/system/notice")
public class SysNoticeController extends BaseController {

    @Inject
    private ISysNoticeService noticeService;
    @Inject
    private DictService dictService;

    /**
     * 获取通知公告列表
     */
    @Get
    @Mapping("/list")
    @SaCheckPermission("system:notice:list")
    public TableDataInfo<SysNoticeVo> list(SysNoticeBo notice, PageQuery pageQuery) {
        return noticeService.selectPageNoticeList(notice, pageQuery);
    }

    /**
     * 根据通知公告编号获取详细信息
     *
     * @param noticeId 公告ID
     */
    @Get
    @Mapping(value = "/{noticeId}")
    @SaCheckPermission("system:notice:query")
    public R<SysNoticeVo> getInfo(Long noticeId) {
        return R.ok(noticeService.selectNoticeById(noticeId));
    }

    /**
     * 新增通知公告
     */
    @Post
    @Mapping
    @SaCheckPermission("system:notice:add")
    @Log(title = "通知公告", businessType = BusinessType.INSERT)
    public R<Void> add(SysNoticeBo notice) {
        int rows = noticeService.insertNotice(notice);
        if (rows <= 0) {
            return R.fail();
        }
        String type = dictService.getDictLabel("sys_notice_type", notice.getNoticeType());
        // WebSocketUtils.publishAll("[" + type + "] " + notice.getNoticeTitle());
        return R.ok();
    }

    /**
     * 修改通知公告
     */
    @Put
    @Mapping
    @SaCheckPermission("system:notice:edit")
    @Log(title = "通知公告", businessType = BusinessType.UPDATE)
    public R<Void> edit(SysNoticeBo notice) {
        return toAjax(noticeService.updateNotice(notice));
    }

    /**
     * 删除通知公告
     *
     * @param noticeIds 公告ID串
     */
    @Delete
    @Mapping("/{noticeIds}")
    @SaCheckPermission("system:notice:remove")
    @Log(title = "通知公告", businessType = BusinessType.DELETE)
    public R<Void> remove(Long[] noticeIds) {
        return toAjax(noticeService.deleteNoticeByIds(noticeIds));
    }
}
