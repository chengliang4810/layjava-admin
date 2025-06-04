package com.jimuqu.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.jimuqu.common.core.checker.Assert;
import com.jimuqu.common.core.constant.UserConstants;
import com.jimuqu.common.core.domain.R;
import com.jimuqu.common.core.validate.group.AddGroup;
import com.jimuqu.common.core.validate.group.UpdateGroup;
import com.jimuqu.common.log.annotation.Log;
import com.jimuqu.common.log.enums.BusinessType;
import com.jimuqu.common.mybatis.core.Page;
import com.jimuqu.common.mybatis.core.page.PageQuery;
import com.jimuqu.common.web.core.BaseController;
import com.jimuqu.system.domain.bo.SysPostBo;
import com.jimuqu.system.domain.vo.SysPostVo;
import com.jimuqu.system.domain.query.SysPostQuery;
import com.jimuqu.system.service.SysPostService;
import lombok.RequiredArgsConstructor;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Get;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Post;
import org.noear.solon.validation.annotation.NoRepeatSubmit;
import org.noear.solon.validation.annotation.NotEmpty;
import org.noear.solon.validation.annotation.NotNull;
import org.noear.solon.validation.annotation.Validated;

import java.util.List;

/**
 * 岗位信息 Controller
 *
 * @author chengliang4810
 * @since 2025-06-04
 */
@Post
@Controller
@RequiredArgsConstructor
@Mapping("/system/post")
public class SysPostController extends BaseController {

    private final SysPostService sysPostService;

    /**
     * 查询岗位信息列表
     */
    @Get
    @Mapping("/list")
    @SaCheckPermission("system:post:list")
    public Page<SysPostVo> list(SysPostQuery query, PageQuery pageQuery) {
        return sysPostService.queryPageList(query, pageQuery);
    }

    /**
     * 获取岗位信息详细信息
     *
     * @param postId 岗位信息主键
     */
    @Get
    @Mapping("/{postId}")
    @SaCheckPermission("system:post:query")
    public SysPostVo getInfo(@NotNull(message = "岗位信息主键不能为空") Long postId) {
        return sysPostService.queryById(postId);
    }

    /**
     * 新增岗位信息
     */
    @Mapping("/add")
    @NoRepeatSubmit
    @SaCheckPermission("system:post:add")
    @Log(title = "新增岗位信息", businessType = BusinessType.ADD)
    public Long add(@Validated(AddGroup.class) SysPostBo post) {
        if (!sysPostService.checkPostNameUnique(post)) {
            Assert.fail("修改岗位'{}'失败，岗位名称已存在", post.getPostName());
        } else if (!sysPostService.checkPostCodeUnique(post)) {
            Assert.fail("修改岗位'{}'失败，岗位编码已存在", post.getPostName());
        }
        boolean result = sysPostService.insertByBo(post);
        Assert.isTrue(result, "新增岗位信息失败");
        return post.getPostId();
    }

    /**
     * 更新岗位信息
     */
    @NoRepeatSubmit
    @Mapping("/update")
    @SaCheckPermission("system:post:update")
    @Log(title = "更新岗位信息", businessType = BusinessType.UPDATE)
    public void edit(@Validated(UpdateGroup.class) SysPostBo post) {
        if (!sysPostService.checkPostNameUnique(post)) {
            Assert.fail("修改岗位'{}'失败，岗位名称已存在", post.getPostName());
        } else if (!sysPostService.checkPostCodeUnique(post)) {
            Assert.fail("修改岗位'{}'失败，岗位编码已存在", post.getPostName());
        } else if (UserConstants.POST_DISABLE.equals(post.getStatus()) && sysPostService.countUserPostById(post.getPostId()) > 0) {
            Assert.fail("该岗位下存在已分配用户，不能禁用!");
        }
        boolean result = sysPostService.updateByBo(post);
        Assert.isTrue(result, "更新岗位信息失败");
    }

    /**
     * 删除岗位信息
     */
    @Mapping("/delete/{ids}")
    @SaCheckPermission("system:post:delete")
    @Log(title = "删除岗位信息", businessType = BusinessType.DELETE)
    public Integer delete(@NotEmpty(message = "主键不能为空") List<Long> ids) {
        Integer num = sysPostService.deleteByIds(ids);
        Assert.gtZero(num, "删除岗位信息失败");
        return num;
    }

    /**
     * 获取岗位列表
     */
    @Get
    @Mapping("/all")
    public R<List<SysPostVo>> all() {
        SysPostQuery sysPostQuery = new SysPostQuery();
        sysPostQuery.setStatus(UserConstants.POST_NORMAL);
        List<SysPostVo> posts = sysPostService.queryList(sysPostQuery);
        return R.ok(posts);
    }

}