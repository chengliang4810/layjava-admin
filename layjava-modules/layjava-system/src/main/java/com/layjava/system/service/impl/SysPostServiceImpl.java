package com.layjava.system.service.impl;

import com.layjava.common.core.exception.ServiceException;
import com.layjava.common.core.utils.MapstructUtil;
import com.layjava.common.mybatis.core.page.PageQuery;
import com.layjava.common.mybatis.core.page.PageResult;
import com.layjava.system.domain.SysPost;
import com.layjava.system.domain.bo.SysPostBo;
import com.layjava.system.domain.vo.SysPostVo;
import com.layjava.system.mapper.SysPostMapper;
import com.layjava.system.mapper.SysUserPostMapper;
import com.layjava.system.service.ISysPostService;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import org.apache.ibatis.solon.annotation.Db;
import org.noear.solon.annotation.Component;

import java.util.Arrays;
import java.util.List;

import static com.layjava.system.domain.table.SysPostTableDef.SYS_POST;

/**
 * 岗位信息 服务层处理
 *
 * @author Lion Li
 */

@Component
public class SysPostServiceImpl implements ISysPostService {

    @Db
    private SysPostMapper baseMapper;
    @Db
    private SysUserPostMapper userPostMapper;

    @Override
    public PageResult<SysPostVo> selectPagePostList(SysPostBo post, PageQuery pageQuery) {
        QueryWrapper lqw = buildQueryWrapper(post);
        Page<SysPostVo> page = baseMapper.paginateAs(pageQuery, lqw, SysPostVo.class);
        return PageResult.build(page);
    }

    /**
     * 查询岗位信息集合
     *
     * @param post 岗位信息
     * @return 岗位信息集合
     */
    @Override
    public List<SysPostVo> selectPostList(SysPostBo post) {
        QueryWrapper lqw = buildQueryWrapper(post);
        return baseMapper.selectListByQueryAs(lqw, SysPostVo.class);
    }

    private QueryWrapper buildQueryWrapper(SysPostBo bo) {
        return QueryWrapper.create().from(SYS_POST)
                .where(SYS_POST.POST_CODE.like(bo.getPostCode()))
                .and(SYS_POST.POST_NAME.like(bo.getPostName()))
                .and(SYS_POST.STATUS.eq(bo.getStatus()))
                .orderBy(SYS_POST.POST_SORT, true);
    }

    /**
     * 查询所有岗位
     *
     * @return 岗位列表
     */
    @Override
    public List<SysPostVo> selectPostAll() {
        return baseMapper.selectListByQueryAs(new QueryWrapper().from(SYS_POST), SysPostVo.class);
    }

    /**
     * 通过岗位ID查询岗位信息
     *
     * @param postId 岗位ID
     * @return 角色对象信息
     */
    @Override
    public SysPostVo selectPostById(Long postId) {
        return baseMapper.selectOneWithRelationsByIdAs(postId, SysPostVo.class);
    }

    /**
     * 根据用户ID获取岗位选择框列表
     *
     * @param userId 用户ID
     * @return 选中岗位ID列表
     */
    @Override
    public List<Long> selectPostListByUserId(Long userId) {
        return baseMapper.selectPostListByUserId(userId);
    }

    /**
     * 校验岗位名称是否唯一
     *
     * @param post 岗位信息
     * @return 结果
     */
    @Override
    public boolean checkPostNameUnique(SysPostBo post) {
        boolean exist = baseMapper.selectCountByQuery(
                QueryWrapper.create().from(SYS_POST).where(SYS_POST.POST_NAME.eq(post.getPostName()))
                        .and(SYS_POST.POST_ID.ne(post.getPostId()))) > 0;
        return !exist;
    }

    /**
     * 校验岗位编码是否唯一
     *
     * @param post 岗位信息
     * @return 结果
     */
    @Override
    public boolean checkPostCodeUnique(SysPostBo post) {
        boolean exist = baseMapper.selectCountByQuery(
                QueryWrapper.create().from(SYS_POST).where(SYS_POST.POST_CODE.eq(post.getPostCode()))
                        .and(SYS_POST.POST_ID.ne(post.getPostId()))) > 0;
        return !exist;
    }

    /**
     * 通过岗位ID查询岗位使用数量
     *
     * @param postId 岗位ID
     * @return 结果
     */
    @Override
    public long countUserPostById(Long postId) {
        return userPostMapper.selectCountByQuery(QueryWrapper.create().from(SYS_POST).where(SYS_POST.POST_ID.eq(postId)));
    }

    /**
     * 删除岗位信息
     *
     * @param postId 岗位ID
     * @return 结果
     */
    @Override
    public int deletePostById(Long postId) {
        return baseMapper.deleteById(postId);
    }

    /**
     * 批量删除岗位信息
     *
     * @param postIds 需要删除的岗位ID
     * @return 结果
     */
    @Override
    public int deletePostByIds(Long[] postIds) {
        for (Long postId : postIds) {
            SysPost post = baseMapper.selectOneById(postId);
            if (countUserPostById(postId) > 0) {
                throw new ServiceException(String.format("%1$s已分配，不能删除!", post.getPostName()));
            }
        }
        return baseMapper.deleteBatchByIds(Arrays.asList(postIds));
    }

    /**
     * 新增保存岗位信息
     *
     * @param bo 岗位信息
     * @return 结果
     */
    @Override
    public int insertPost(SysPostBo bo) {
        SysPost post = MapstructUtil.convert(bo, SysPost.class);
        return baseMapper.insert(post, true);
    }

    /**
     * 修改保存岗位信息
     *
     * @param bo 岗位信息
     * @return 结果
     */
    @Override
    public int updatePost(SysPostBo bo) {
        SysPost post = MapstructUtil.convert(bo, SysPost.class);
        return baseMapper.update(post);
    }
}
